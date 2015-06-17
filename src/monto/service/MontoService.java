package monto.service;

import monto.service.message.Message;
import monto.service.message.ProductMessage;
import monto.service.message.ProductMessages;
import monto.service.message.VersionMessages;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;
import org.zeromq.ZMQException;

import java.util.ArrayList;
import java.util.List;

public abstract class MontoService implements Runnable {

    private String address;
    private Context context;
    private Socket socket;
    private Thread thread;

    public MontoService(String address, Context context) {
        this.address = address;
        this.context = context;
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        socket = context.socket(ZMQ.PAIR);
        socket.connect(address);
        while (!thread.isInterrupted()) {
            JSONArray messages;
            try {
                messages = (JSONArray) JSONValue.parse(socket.recvStr());
                List<Message> decodedMessages = new ArrayList<>();
                for (Object object : messages) {
                    JSONObject message = (JSONObject) object;
                    Message decoded = message.containsKey("product") ? ProductMessages.decode(message) : VersionMessages.decode(message);
                    decodedMessages.add(decoded);
                }
                socket.send(ProductMessages.encode(onMessage(decodedMessages)).toJSONString());
            } catch (ZMQException e) {
                break;
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        System.out.println("de.tudarmstadt.stg.monto.service on " + address + " closing...");
        socket.close();
    }

    public void stop() {
        thread.interrupt();
    }

    public abstract ProductMessage onMessage(List<Message> messages) throws Exception;

}
