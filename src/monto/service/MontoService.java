package monto.service;

import monto.service.configuration.Configuration;
import monto.service.message.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;
import org.zeromq.ZMQException;

import java.util.ArrayList;
import java.util.List;

/**
 * Template for a monto service.
 */
public abstract class MontoService implements Runnable {

    private ZContext context;
    private Socket registrationSocket;
    private String address;
    private String registrationAddress;
    private volatile boolean running;

    protected volatile String serviceID;
    protected volatile String label;
    protected volatile String description;
    protected volatile Language language;
    protected volatile Product product;
    protected volatile Configuration[] configuration;
    protected volatile String[] dependencies;

    /**
     * Template for a monto service.
     * @param context
     * @param address address of the service without port, e.g. "tcp://*"
     * @param registrationAddress registration address of the broker, e.g. "tcp://*:5004"
     * @param serviceID
     * @param product
     * @param language
     * @param dependencies
     */
    public MontoService(ZContext context, String address, String registrationAddress, String serviceID, String label, String description, Product product, Language language, String[] dependencies) {
        this.context = context;
        this.address = address;
        this.registrationAddress = registrationAddress;
        this.serviceID = serviceID;
        this.label = label;
        this.description = description;
        this.language = language;
        this.product = product;
        this.configuration = null;
        this.dependencies = dependencies;
        running = true;
    }

    /**
     * Template for a monto service with configuration.
     * @param context
     * @param address address of the service without port, e.g. "tcp://*"
     * @param registrationAddress registration address of the broker, e.g. "tcp://*:5004"
     * @param serviceID
     * @param label
     * @param description
     * @param language
     * @param product
     * @param configuration
     * @param dependencies
     */
    public MontoService(ZContext context, String address, String registrationAddress, String serviceID, String label, String description, Language language, Product product, Configuration[] configuration, String[] dependencies) {
        this(context, address, registrationAddress, serviceID, label, description, product, language, dependencies);
        this.configuration = configuration;
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("registering: " + serviceID + " on " + registrationAddress);
        registrationSocket = context.createSocket(ZMQ.REQ);
        registrationSocket.connect(registrationAddress);
        registrationSocket.send(RegisterMessages.encode(new RegisterServiceRequest(serviceID, label, description, language, product, configuration, dependencies)).toJSONString());
        JSONObject response = (JSONObject) JSONValue.parse(registrationSocket.recvStr());
        RegisterServiceResponse decodedResponse = RegisterMessages.decodeResponse(response);
        if (decodedResponse.getResponse().equals("ok")) {
            int port = decodedResponse.getBindOnPort();
            System.out.println("registered: " + serviceID + ", connecting on " + address + ":" + port);
            Socket socket = context.createSocket(ZMQ.PAIR);
            socket.connect(address + ":" + port);
            System.out.println("connected: " + serviceID);
            while (running) {
                JSONArray messages;
                String rawMsg;
                try {
                    rawMsg = socket.recvStr(ZMQ.NOBLOCK);
                    if (rawMsg != null && !rawMsg.equals("")) {
                        messages = (JSONArray) JSONValue.parse(rawMsg);
                        List<Message> decodedMessages = new ArrayList<>();
                        for (Object object : messages) {
                            JSONObject message = (JSONObject) object;
                            Message decoded = message.containsKey("product") ? ProductMessages.decode(message) : VersionMessages.decode(message);
                            decodedMessages.add(decoded);
                        }
                        socket.send(ProductMessages.encode(onMessage(decodedMessages)).toJSONString());
                    }
                    Thread.sleep(1);
                } catch (ZMQException e) {
                    e.printStackTrace();
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.printf("could not register service %s: %s%n", serviceID, decodedResponse.getResponse());
        }
    }

    public synchronized void stop() {
        running = false;
        System.out.println("disconnecting: " + serviceID);
        System.out.println("deregistering: " + serviceID);
        registrationSocket.send(RegisterMessages.encode(new DeregisterService(serviceID)).toJSONString());
        registrationSocket.recvStr();
        System.out.println("deregistered: " + serviceID);
        System.out.println("terminated: " + serviceID);
    }

    /**
     * This method is called by the run method.
     * It handles the messages that are received from the broker and determines the response back to the broker.
     * @param messages
     * @return
     * @throws Exception
     */
    public abstract ProductMessage onMessage(List<Message> messages) throws Exception;

    public synchronized String getServiceID() {
        return serviceID;
    }

    public synchronized Language getLanguage() {
        return language;
    }

    public synchronized Product getProduct() {
        return product;
    }

    public synchronized String[] getDependencies() {
        return dependencies;
    }

    public synchronized Configuration[] getConfiguration() {
        return configuration;
    }
}
