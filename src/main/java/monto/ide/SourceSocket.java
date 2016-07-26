package monto.ide;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import monto.service.gson.GsonMonto;
import monto.service.gson.MessageFromIde;

public class SourceSocket {
  private Socket socket;
  private String address;

  public SourceSocket(Context ctx, String address) {
    this.address = address;
    this.socket = ctx.socket(ZMQ.PAIR);
    socket.setLinger(1000);
    // linger = # of ms, that should be waited after closing,
    // while trying to send messages in memory buffer
  }

  public void connect() {
    socket.connect(address);
  }

  public void send(MessageFromIde message) {
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
