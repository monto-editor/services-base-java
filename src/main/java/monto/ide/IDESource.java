package monto.ide;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import monto.service.gson.GsonMonto;
import monto.service.source.SourceMessage;

public class IDESource {
	private Socket socket;
	private String address;

    public IDESource(Context ctx, String address) {
    	this.address = address;
        this.socket = ctx.socket(ZMQ.PAIR);
        socket.setLinger(2000); // 2 seconds
    }

    public void connect() {
    	socket.connect(address);
    }

    public void sendSource(SourceMessage message) {
        try {
            socket.send(GsonMonto.toJson(message));
        } catch (Exception e) {
            System.err.print(e);
        }
    }


    public void close() {
        socket.close();
    }
}
