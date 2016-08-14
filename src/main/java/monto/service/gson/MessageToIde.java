package monto.service.gson;

import com.google.gson.JsonElement;

import java.util.function.Consumer;
import monto.service.command.CommandUpdate;
import monto.service.discovery.DiscoveryResponse;
import monto.service.product.ProductMessage;
import monto.service.types.PartialConsumer;
import monto.service.types.PartialFunction;
import monto.service.types.UnrecognizedMessageException;

public class MessageToIde {
  private String tag;
  private JsonElement contents;

  private MessageToIde(String tag, JsonElement contents) {
    this.tag = tag;
    this.contents = contents;
  }

  public <A, E extends Exception> A match(
      PartialFunction<ProductMessage, A, E> onProduct,
      PartialFunction<CommandUpdate, A, E> onCommandUpdate,
      PartialFunction<DiscoveryResponse, A, E> onDiscovery)
      throws UnrecognizedMessageException, E {
    switch (tag) {
      case "product":
        return onProduct.apply(GsonMonto.fromJson(contents, ProductMessage.class));
      case "commandUpdate":
        return onCommandUpdate.apply(GsonMonto.fromJson(contents, CommandUpdate.class));
      case "discovery":
        return onDiscovery.apply(GsonMonto.fromJson(contents, DiscoveryResponse.class));
      default:
        throw new RuntimeException(String.format("unrecognized message type %s\n", tag));
    }
  }

  public void matchVoid(
      Consumer<ProductMessage> onProduct,
      Consumer<CommandUpdate> onCommandUpdate,
      Consumer<DiscoveryResponse> onDiscovery)
      throws UnrecognizedMessageException {
    this
        .<Void, UnrecognizedMessageException>match(
            req -> {
              onProduct.accept(req);
              return null;
            },
            cmdUpd -> {
              onCommandUpdate.accept(cmdUpd);
              return null;
            },
            conf -> {
              onDiscovery.accept(conf);
              return null;
            });
  }

  public <E extends Exception> void matchExc(
      PartialConsumer<ProductMessage, E> onProduct,
      PartialConsumer<CommandUpdate, E> onCommandUpdate,
      PartialConsumer<DiscoveryResponse, E> onDiscovery)
      throws UnrecognizedMessageException, E {
    this
        .<Void, E>match(
            req -> {
              onProduct.accept(req);
              return null;
            },
            cmdUpd -> {
              onCommandUpdate.accept(cmdUpd);
              return null;
            },
            conf -> {
              onDiscovery.accept(conf);
              return null;
            });
  }

  public String getTag() {
    return tag;
  }

  public Object getContents() {
    return contents;
  }
}
