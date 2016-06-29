package monto.ide;

import java.util.function.Consumer;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import monto.service.discovery.DiscoveryResponse;
import monto.service.gson.GsonMonto;
import monto.service.gson.MessageToIde;
import monto.service.product.ProductMessage;
import monto.service.types.MessageUnavailableException;
import monto.service.types.UnrecongizedMessageException;

public class SinkSocket {
  private Socket socket;
  private String address;

  public SinkSocket(Context ctx, String address) {
    this.socket = ctx.socket(ZMQ.PAIR);
    this.address = address;
  }

  public void connect() {
    socket.connect(address);
  }

  public void receive(
      Consumer<ProductMessage> onProductMessage, Consumer<DiscoveryResponse> onDiscovery)
      throws UnrecongizedMessageException, MessageUnavailableException {
    String rawMsg = socket.recvStr();
    if (rawMsg != null) {
      GsonMonto.fromJson(rawMsg, MessageToIde.class).matchVoid(onProductMessage, onDiscovery);
    } else {
      throw new MessageUnavailableException();
    }
  }

  public void close() throws Exception {
    socket.close();
  }
}
