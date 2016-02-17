package monto.service;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;

import monto.service.configuration.ConfigurationMessage;
import monto.service.configuration.ConfigurationMessages;
import monto.service.configuration.Option;
import monto.service.product.ProductMessage;
import monto.service.product.ProductMessages;
import monto.service.registration.Dependency;
import monto.service.registration.DeregisterService;
import monto.service.registration.ProductDescription;
import monto.service.registration.RegisterMessages;
import monto.service.registration.RegisterServiceRequest;
import monto.service.registration.RegisterServiceResponse;
import monto.service.request.Request;
import monto.service.request.Requests;
import monto.service.types.Language;
import monto.service.types.LongKey;
import monto.service.types.ParseException;
import monto.service.types.PartialConsumer;
import monto.service.types.PartialFunction;
import monto.service.types.Product;
import monto.service.types.ServiceID;
import monto.service.types.Source;

/**
 * Template for a monto service.
 */
@SuppressWarnings("rawtypes")
public abstract class MontoService {

    private ZMQConfiguration zmqConfig;
    private int port;
    private volatile boolean running;
    private boolean registered;

    protected volatile ServiceID serviceID;
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

    /**
     * Template for a monto service.
     *
     * @param context
     * @param fullServiceAddress             address of the service without port, e.g. "tcp://*"
     * @param registrationAddress registration address of the broker, e.g. "tcp://*:5004"
     * @param serviceID
     * @param product
     * @param language
     * @param dependencies
     */
    public MontoService(
    		ZMQConfiguration zmqConfig,
    		ServiceID serviceID,
    		String label,
    		String description,
    		List<ProductDescription> products,
    		List<Dependency> dependencies
    		) {
    	this.zmqConfig = zmqConfig;
        this.serviceID = serviceID;
        this.label = label;
        this.description = description;
        this.products = products;
        this.options = options();
        this.dependencies = dependencies;
        this.running = true;
        this.registered = false;
    }

    public MontoService(ZMQConfiguration zmqConfig, ServiceID serviceID, String label, String description, List<ProductDescription> products, List<Option> options, List<Dependency> dependencies) {
        this(zmqConfig, serviceID, label, description,  products, dependencies);
        this.options = options;
    }

    public MontoService(ZMQConfiguration zmqConfig, ServiceID serviceID, String label, String description, Language language, Product product, List<Option> options, List<Dependency> dependencies) {
	this(zmqConfig, serviceID, label, description, Arrays.asList(new ProductDescription(product, language)), dependencies);
        this.options = options;
    }

    public <MSG,Decoded> void handleMessage(Socket socket, PartialFunction<MSG,Decoded,ParseException> decodeMessage, PartialConsumer<Decoded, ? super Exception> onMessage) {
    	String rawMsg = socket.recvStr();
		try {
			// In case of subscription ignore topic and receive message body
			if(socket.getType() == ZMQ.SUB && rawMsg != null)
				rawMsg = socket.recvStr();

			if (rawMsg != null) {
				@SuppressWarnings("unchecked")
				MSG msg = (MSG) JSONValue.parseWithException(rawMsg);
				Decoded decoded = decodeMessage.apply(msg);
				onMessage.accept(decoded);
			}
		} catch (Exception e) {
			System.err.printf("An exception occured during handling the message %s\n",rawMsg);
			e.printStackTrace();
		}
    }
    
    public void start() throws Exception {
        registerService();
        if (isRegisterResponseOk()) {
        	running = true;
        	serviceSocket = zmqConfig.getContext().createSocket(ZMQ.PAIR);
        	serviceSocket.connect(zmqConfig.getServiceAddress() + ":" + port);
        	serviceSocket.setReceiveTimeOut(500);
        	MontoService that = this;
        	serviceThread = new Thread() {
        		@Override
        		public void run() {
        			while(running)
        				that.<JSONObject,Request>handleMessage (
        						serviceSocket,
        						request -> Requests.decode(request),
        						request -> serviceSocket.send(ProductMessages.encode(onRequest(request)).toJSONString()));
        		}
        	};
        	serviceThread.start();
        	
        	configSocket = zmqConfig.getContext().createSocket(ZMQ.SUB);
        	configSocket.connect(zmqConfig.getConfigurationAddress());
        	configSocket.subscribe(serviceID.toString().getBytes());
        	configSocket.setReceiveTimeOut(500);
        	configThread = new Thread() {
        		@Override
        		public void run() {
        			while(running) {
        				that.<JSONObject,ConfigurationMessage>handleMessage(
        						configSocket,
        						ConfigurationMessages::decode,
        						message -> onConfigurationMessage(message));
        			}
        		};
        	};
        	configThread.start();
        	System.out.println("connected: " + serviceID);
        }
    }

    public void stop() throws Exception {
        if (registered == true) {
            running = false;
            System.out.println("disconnecting: " + serviceID);
            System.out.println("deregistering: " + serviceID);
            try {
				serviceThread.join();
				configThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            registrationSocket.send(RegisterMessages.encode(new DeregisterService(serviceID)).toJSONString());
            
            // Sockets will be closed by ZContext.destroy()
            
            System.out.println("terminated: " + serviceID);
        }
    }

    private void registerService() {
        System.out.println("registering: " + serviceID + " on " + zmqConfig.getRegistrationAddress());
        registrationSocket = zmqConfig.getContext().createSocket(ZMQ.REQ);
        registrationSocket.connect(zmqConfig.getRegistrationAddress());
        registrationSocket.send(RegisterMessages.encode(new RegisterServiceRequest(serviceID, label, description, products, options, dependencies)).toJSONString());
    }

    private boolean isRegisterResponseOk() {
        JSONObject response = (JSONObject) JSONValue.parse(registrationSocket.recvStr());
        RegisterServiceResponse decodedResponse = RegisterMessages.decodeResponse(response);
        if (decodedResponse.getResponse().equals("ok") && decodedResponse.getBindOnPort() > -1) {
            port = decodedResponse.getBindOnPort();
            System.out.println("registered: " + serviceID + ", connecting on " + zmqConfig.getServiceAddress() + ":" + port);
            registered = true;
            return true;
        }
        System.out.printf("could not register service %s: %s\n", serviceID, decodedResponse.getResponse());
        return false;
    }
    
    protected ProductMessage productMessage(LongKey versionID, Source source, Product product, Language language, Object contents) {
        return new ProductMessage(
                versionID,
                source,
                getServiceID(),
                product,
                language,
                contents);
    }

    /**
     * handles request messages send by the broker and return a product message.
     */
    public abstract ProductMessage onRequest(Request request) throws Exception;

    /**
     * It handles the configuration messages from the broker and determines the response.
     */
    public void onConfigurationMessage(ConfigurationMessage message) throws Exception {
    	// By default ignore configuration messages.
    }

    public ServiceID getServiceID() {
        return serviceID;
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
}
