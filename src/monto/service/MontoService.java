package monto.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import monto.service.configuration.Configuration;
import monto.service.configuration.Option;
import monto.service.dependency.RegisterDynamicDependencies;
import monto.service.gson.GsonMonto;
import monto.service.product.ProductMessage;
import monto.service.registration.*;
import monto.service.request.Request;
import monto.service.types.*;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * Template for a monto service.
 */
@SuppressWarnings("rawtypes")
public abstract class MontoService {

    private ZMQConfiguration zmqConfig;
    private int port;
    private volatile boolean running;
    private boolean registered;

    protected volatile ServiceId serviceId;
    protected volatile String label;
    protected volatile String description;
    protected volatile List<ProductDescription> products;
    protected volatile List<Option> options;
    protected volatile List<Dependency> dependencies;
    private Socket registrationSocket;
    private Socket serviceSocket;
    private Thread serviceThread;
    private Thread configThread;
    private Socket configSocket;
    private Socket dyndepSocket;
    protected boolean debug = false;

    /**
     * Template for a monto service.
     */
    public MontoService(
            ZMQConfiguration zmqConfig,
            ServiceId serviceId,
            String label,
            String description,
            List<ProductDescription> products,
            List<Dependency> dependencies) {
        this.zmqConfig = zmqConfig;
        this.serviceId = serviceId;
        this.label = label;
        this.description = description;
        this.products = products;
        this.options = options();
        this.dependencies = dependencies;
        this.running = true;
        this.registered = false;
    }

    public MontoService(ZMQConfiguration zmqConfig, ServiceId serviceId, String label, String description, List<ProductDescription> products, List<Option> options, List<Dependency> dependencies) {
        this(zmqConfig, serviceId, label, description, products, dependencies);
        this.options = options;
    }

    public MontoService(ZMQConfiguration zmqConfig, ServiceId serviceId, String label, String description, Language language, Product product, List<Option> options, List<Dependency> dependencies) {
        this(zmqConfig, serviceId, label, description, Arrays.asList(new ProductDescription(product, language)), dependencies);
        this.options = options;
    }

    public <MSG, Decoded> void handleMessage(Socket socket, PartialFunction<MSG, Decoded, ParseException> decodeMessage, PartialConsumer<Decoded, ? super Exception> onMessage) {
        String rawMsg = socket.recvStr();
        try {
            // In case of subscription ignore topic and receive message body
            if (socket.getType() == ZMQ.SUB && rawMsg != null)
                rawMsg = socket.recvStr();

            if (rawMsg != null) {
                // TODO remove MSG generic and replace with String?
                @SuppressWarnings("unchecked")
                MSG msg = (MSG) rawMsg;
                Decoded decoded = decodeMessage.apply(msg);
                onMessage.accept(decoded);
            }
        } catch (Throwable e) {
            if (debug) {
                System.err.printf("An error occured in the service %s\n", serviceId);
                e.printStackTrace(System.err);
            }
        }
    }

    public void start() throws Exception {
        registerService();
        if (isRegisterResponseOk()) {
            running = true;
            dyndepSocket = zmqConfig.getContext().createSocket(ZMQ.PUB);
            dyndepSocket.connect(zmqConfig.getDyndepAddress());
            dyndepSocket.setReceiveTimeOut(500);

            serviceSocket = zmqConfig.getContext().createSocket(ZMQ.PAIR);
            serviceSocket.connect(zmqConfig.getServiceAddress() + ":" + port);
            serviceSocket.setReceiveTimeOut(500);
            MontoService that = this;
            serviceThread = new Thread() {
                @Override
                public void run() {
                    while (running)
                        that.<String, Request>handleMessage(
                                serviceSocket,
                                requestJsonString -> GsonMonto.fromJson(requestJsonString, Request.class),
                                request -> onRequest(request));
                }
            };
            serviceThread.start();

            configSocket = zmqConfig.getContext().createSocket(ZMQ.SUB);
            configSocket.connect(zmqConfig.getConfigurationAddress());
            configSocket.subscribe(serviceId.toString().getBytes());
            configSocket.setReceiveTimeOut(500);
            configThread = new Thread() {
                @Override
                public void run() {
                    while (running) {
                        that.<String, Configuration>handleMessage(
                                configSocket,
                                configJsonString -> GsonMonto.fromJson(configJsonString, Configuration.class),
                                message -> onConfigurationMessage(message));
                    }
                }
            };
            configThread.start();
            System.out.println("connected: " + serviceId);
        }
    }

    public void stop() throws Exception {
        if (registered) {
            running = false;
            System.out.println("disconnecting: " + serviceId);
            System.out.println("deregistering: " + serviceId);
            try {
                serviceThread.join();
                configThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            registrationSocket.send(GsonMonto.toJson(new DeregisterService(serviceId)));

            // Sockets will be closed by ZContext.destroy()

            System.out.println("terminated: " + serviceId);
        }
    }

    public MontoService enableDebugging() {
        debug = true;
        return this;
    }

    private void registerService() {
        System.out.println("registering: " + serviceId + " on " + zmqConfig.getRegistrationAddress());
        registrationSocket = zmqConfig.getContext().createSocket(ZMQ.REQ);
        registrationSocket.connect(zmqConfig.getRegistrationAddress());
        registrationSocket.send(GsonMonto.toJson(
                new RegisterServiceRequest(serviceId, label, description, products, options, dependencies)
        ));
    }

    private boolean isRegisterResponseOk() {
        RegisterServiceResponse decodedResponse = GsonMonto.fromJson(registrationSocket.recvStr(), RegisterServiceResponse.class);
        // TODO decodedResponse.getConnectToPort() > -1 should be >= -1
        // TODO missing decodedResponse.getConnectToPort() never happend
        if (decodedResponse.getResponse().equals("ok") && decodedResponse.getConnectToPort() > -1) {
            port = decodedResponse.getConnectToPort();
            System.out.println("registered: " + serviceId + ", connecting on " + zmqConfig.getServiceAddress() + ":" + port);
            registered = true;
            return true;
        }
        System.out.printf("could not register service %s: %s\n", serviceId, decodedResponse.getResponse());
        return false;
    }

    protected void sendProductMessage(LongKey versionID, Source source, Product product, Language language, JsonElement contents) {
        sendProductMessage(versionID, source, product, language, contents, 0);
    }

    protected void sendProductMessage(LongKey versionID, Source source, Product product, Language language, JsonElement contents, long time) {
        serviceSocket.send(GsonMonto.toJson(
                new ProductMessage(versionID, source, getServiceId(), product, language, contents, time)
        ));
    }

    protected void sendProductMessageNotAvailable(LongKey versionID, Source source, Product product, Language language, Exception exception, long time) {
        JsonObject contents = new JsonObject();
        String reason = null;
        if (exception != null) {
            reason = exception.getClass().getName() + ": " + exception.getMessage();
        }
        contents.addProperty("notAvailable", reason);
        if (debug) {
            System.err.printf("%s is sending a notAvailable ProductMessage for %s %s %s:%n%s",
                    getServiceId(), source, product, language, reason);
        }
        sendProductMessage(versionID, source, product, language, contents, time);
    }

    /**
     * Handles request messages send by the broker and usually sends a product message.
     */
    public abstract void onRequest(Request request) throws Exception;

    /**
     * It handles the configuration messages from the broker and determines the response.
     */
    public void onConfigurationMessage(Configuration message) throws Exception {
        // By default ignore configuration messages.
    }

    public ServiceId getServiceId() {
        return serviceId;
    }

    public List<ProductDescription> getProducts() {
        return products;
    }

    public List<Dependency> getDependencies() {
        return dependencies;
    }

    public List<Option> getOptions() {
        return options;
    }

    public static List<Option> options(Option... options) {
        return Arrays.asList(options);
    }

    public static List<Dependency> dependencies(Dependency... dependencies) {
        return Arrays.asList(dependencies);
    }

    public URL getResource(String name) {
        return zmqConfig.getResourceURL(name);
    }

    protected void registerDynamicDependencies(RegisterDynamicDependencies dyndeps) {
        if (dyndepSocket != null) {
            dyndepSocket.send(GsonMonto.toJson(dyndeps));
        }
    }
}
