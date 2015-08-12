package monto.service;

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

public abstract class MontoService implements Runnable {

    private ZContext context;
    private Socket socket;
    private Socket registrationSocket;
    private Thread thread;
    private String address;
    private int port;
    private int registrationPort;
    private volatile boolean running;

    private volatile String serviceID;
    private volatile Language language;
    private volatile Product product;
    private volatile String[] dependencies;

    public MontoService(ZContext context, String address, int registrationPort, String serviceID, Product product, Language language, String[] dependencies) {
        this.context = context;
        this.address = address;
        this.registrationPort = registrationPort;
        this.serviceID = serviceID;
        this.language = language;
        this.product = product;
        this.dependencies = dependencies;
        running = true;
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("registering: " + serviceID + " on " + address + registrationPort);
        registrationSocket = context.createSocket(ZMQ.REQ);
        registrationSocket.connect(address + registrationPort);
        registrationSocket.send(RegisterMessages.encode(new RegisterServiceRequest(serviceID, language, product, dependencies)).toJSONString());
        JSONObject response = (JSONObject) JSONValue.parse(registrationSocket.recvStr());
        RegisterServiceResponse decodedResponse = RegisterMessages.decodeResponse(response);
        if (decodedResponse.getRespondToServiceID().equals(serviceID) && decodedResponse.getResponse().equals("ok")) {
            port = decodedResponse.getBindOnPort();
            System.out.println("registered: " + serviceID + ", connecting on " + address + port);
            socket = context.createSocket(ZMQ.PAIR);
            socket.connect(address + port);
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
                    thread.sleep(100);
                } catch (ZMQException e) {
                    e.printStackTrace();
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("could not register service " + serviceID + ": " + decodedResponse.getResponse());
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
}
