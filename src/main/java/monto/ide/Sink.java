package monto.ide;

import monto.service.discovery.DiscoveryResponse;
import monto.service.gson.GsonMonto;
import monto.service.product.ProductMessage;
import monto.service.types.MessageUnavailableException;
import monto.service.types.PartialFunction;
import monto.service.types.UnrecongizedMessageException;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class Sink {
  private Socket socket;
  private String address;

  public Sink(Context ctx, String address) {
    this.socket = ctx.socket(ZMQ.PAIR);
    this.address = address;
  }

  public void connect() {
    socket.connect(address);
  }

  public <A, E extends Exception> A receive(
      PartialFunction<ProductMessage, A, E> onProductMessages,
      PartialFunction<DiscoveryResponse, A, E> onDiscovery)
      throws UnrecongizedMessageException, MessageUnavailableException, E {
    String rawMsg = socket.recvStr();
    if (rawMsg != null) {
      return GsonMonto.fromJson(rawMsg, IDEReceive.class)
          .<A, E>match(prod -> onProductMessages.apply(prod), disc -> onDiscovery.apply(disc));
    } else {
      throw new MessageUnavailableException();
    }
  }

  public void close() throws Exception {
    socket.close();
  }
}
