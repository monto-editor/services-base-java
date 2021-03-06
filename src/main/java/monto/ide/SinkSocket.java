package monto.ide;

import java.util.function.Consumer;
import monto.service.discovery.DiscoveryResponse;
import monto.service.gson.GsonMonto;
import monto.service.gson.MessageToIde;
import monto.service.product.ProductMessage;
import monto.service.types.UnrecognizedMessageException;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class SinkSocket {
  private Socket socket;
  private String address;

  public SinkSocket(Context ctx, String address) {
    this.socket = ctx.socket(ZMQ.PAIR);
    this.address = address;
    this.socket.setReceiveTimeOut(1000);
  }

  public void connect() {
    socket.connect(address);
  }

  public void receive(
      Consumer<ProductMessage> onProductMessage, Consumer<DiscoveryResponse> onDiscovery)
      throws UnrecognizedMessageException {
    String rawMsg = socket.recvStr();
    if (rawMsg != null) {
      GsonMonto.fromJson(rawMsg, MessageToIde.class).matchVoid(onProductMessage, onDiscovery);
    }
  }

  public void close() throws Exception {
    socket.close();
  }
}
