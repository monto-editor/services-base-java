package monto.service.gson;

import com.google.gson.JsonElement;
import java.util.function.Consumer;
import monto.service.command.CommandMessage;
import monto.service.configuration.Configuration;
import monto.service.request.Request;
import monto.service.types.PartialConsumer;
import monto.service.types.PartialFunction;
import monto.service.types.UnrecognizedMessageException;

@SuppressWarnings("Duplicates")
public class MessageToService {
  private String tag;
  private JsonElement contents;

  private MessageToService(String tag, JsonElement contents) {
    super();
    this.tag = tag;
    this.contents = contents;
  }

  public <A, E extends Exception> A match(
      PartialFunction<Request, A, E> onRequest,
      PartialFunction<Configuration, A, E> onConfiguration,
      PartialFunction<CommandMessage, A, E> onCommandMessage)
      throws UnrecognizedMessageException, E {
    switch (tag) {
      case "request":
        return onRequest.apply(GsonMonto.fromJson(contents, Request.class));
      case "configuration":
        return onConfiguration.apply(GsonMonto.fromJson(contents, Configuration.class));
      case "commandMessage":
        return onCommandMessage.apply(GsonMonto.fromJson(contents, CommandMessage.class));
      default:
        throw new RuntimeException(String.format("unrecognized message type %s\n", tag));
    }
  }

  public void matchVoid(
      Consumer<Request> onRequest,
      Consumer<Configuration> onConfiguration,
      Consumer<CommandMessage> onCommandMessage)
      throws UnrecognizedMessageException {
    this.<Void, UnrecognizedMessageException>match(
        req -> {
          onRequest.accept(req);
          return null;
        },
        conf -> {
          onConfiguration.accept(conf);
          return null;
        },
        cmdMsg -> {
          onCommandMessage.accept(cmdMsg);
          return null;
        });
  }

  public <E extends Exception> void matchExc(
      PartialConsumer<Request, E> onRequest,
      PartialConsumer<Configuration, E> onConfiguration,
      PartialConsumer<CommandMessage, E> onCommandMessage)
      throws UnrecognizedMessageException, E {
    this.<Void, E>match(
        req -> {
          onRequest.accept(req);
          return null;
        },
        conf -> {
          onConfiguration.accept(conf);
          return null;
        },
        cmdMsg -> {
          onCommandMessage.accept(cmdMsg);
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
