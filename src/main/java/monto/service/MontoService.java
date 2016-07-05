package monto.service;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

import monto.service.configuration.Configuration;
import monto.service.configuration.Option;
import monto.service.dependency.RegisterDynamicDependencies;
import monto.service.gson.GsonMonto;
import monto.service.gson.MessageToService;
import monto.service.gson.MessageFromService;
import monto.service.product.ProductMessage;
import monto.service.registration.Dependency;
import monto.service.registration.DeregisterService;
import monto.service.registration.ProductDescription;
import monto.service.registration.RegisterServiceRequest;
import monto.service.registration.RegisterServiceResponse;
import monto.service.request.Request;
import monto.service.types.Language;
import monto.service.types.LongKey;
import monto.service.types.Product;
import monto.service.types.ServiceId;
import monto.service.types.Source;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import monto.service.types.UnrecognizedMessageException;

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

  public MontoService(
      ZMQConfiguration zmqConfig,
      ServiceId serviceId,
      String label,
      String description,
      List<ProductDescription> products,
      List<Option> options,
      List<Dependency> dependencies) {
    this(zmqConfig, serviceId, label, description, products, dependencies);
    this.options = options;
  }

  public MontoService(
      ZMQConfiguration zmqConfig,
      ServiceId serviceId,
      String label,
      String description,
      Language language,
      Product product,
      List<Option> options,
      List<Dependency> dependencies) {
    this(
        zmqConfig,
        serviceId,
        label,
        description,
        Arrays.asList(new ProductDescription(product, language)),
        dependencies);
    this.options = options;
  }

  public void start() throws Exception {
    registerService();
    if (isRegisterResponseOk()) {
      running = true;

      serviceSocket = zmqConfig.getContext().createSocket(ZMQ.PAIR);
      serviceSocket.connect(zmqConfig.getServiceAddress() + ":" + port);
      serviceSocket.setReceiveTimeOut(500);
      MontoService that = this;
      serviceThread =
          new Thread() {
            @Override
            public void run() {
              while (running) {
                try {
                  String rawMsg = serviceSocket.recvStr();
                  if (rawMsg != null) {
                    GsonMonto.fromJson(rawMsg, MessageToService.class)
                        .matchExc(
                            that::onRequest, that::onConfigurationMessage, that::onCommandMessage);
                  }
                } catch (UnrecognizedMessageException ex) {
                  System.err.println(ex.getMessage());
                  ex.printStackTrace(System.err);
                } catch (Throwable e) {
                  if (debug) {
                    System.err.printf("An error occured in the service %s\n", serviceId);
                    e.printStackTrace(System.err);
                  }
                }
              }
            }
          };
      serviceThread.start();
    }
  }

  public void stop() throws Exception {
    if (registered) {
      running = false;
      System.out.println("disconnecting: " + serviceId);
      System.out.println("deregistering: " + serviceId);
      try {
        serviceThread.join();
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
    registrationSocket.send(
        GsonMonto.toJson(
            new RegisterServiceRequest(
                serviceId, label, description, products, options, dependencies)));
  }

  private boolean isRegisterResponseOk() {
    RegisterServiceResponse decodedResponse =
        GsonMonto.fromJson(registrationSocket.recvStr(), RegisterServiceResponse.class);
    // TODO decodedResponse.getConnectToPort() > -1 should be >= -1
    // TODO missing decodedResponse.getConnectToPort() never happend
    if (decodedResponse.getResponse().equals("ok") && decodedResponse.getConnectToPort() > -1) {
      port = decodedResponse.getConnectToPort();
      System.out.println(
          "registered: "
              + serviceId
              + ", connecting on "
              + zmqConfig.getServiceAddress()
              + ":"
              + port);
      registered = true;
      return true;
    }
    System.out.printf(
        "could not register service %s: %s\n", serviceId, decodedResponse.getResponse());
    return false;
  }

  protected void sendProductMessage(
      LongKey versionID, Source source, Product product, Language language, JsonElement contents) {
    sendProductMessage(versionID, source, product, language, contents, 0);
  }

  protected void sendProductMessage(
      LongKey versionID,
      Source source,
      Product product,
      Language language,
      JsonElement contents,
      long time) {
    serviceSocket.send(
        GsonMonto.toJson(
            MessageFromService.product(
                new ProductMessage(
                    versionID, source, getServiceId(), product, language, contents, time))));
  }

  protected void sendProductMessageNotAvailable(
      LongKey versionID,
      Source source,
      Product product,
      Language language,
      Exception exception,
      long time) {
    JsonObject contents = new JsonObject();
    String reason = null;
    if (exception != null) {
      reason = exception.getClass().getName() + ": " + exception.getMessage();
    }
    contents.addProperty("notAvailable", reason);
    if (debug) {
      System.err.printf(
          "%s is sending a notAvailable ProductMessage for %s %s %s:%n%s",
          getServiceId(),
          source,
          product,
          language,
          reason);
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
    if (serviceSocket != null) {
      serviceSocket.send(GsonMonto.toJson(MessageFromService.dyndep(dyndeps)));
    }
  }
}
