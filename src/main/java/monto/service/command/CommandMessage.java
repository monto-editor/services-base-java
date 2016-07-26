package monto.service.command;

import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import monto.service.gson.GsonMonto;
import monto.service.product.ProductMessage;
import monto.service.region.Region;
import monto.service.source.SourceMessage;
import monto.service.types.Language;
import monto.service.types.Message;
import monto.service.types.Messages;
import monto.service.types.Product;
import monto.service.types.ServiceId;
import monto.service.types.Source;

public class CommandMessage {
  public static final String TAG_SOURCE_POSITION = "sourcePosition";

  private int id;
  private int session;
  private ServiceId serviceId;
  private String tag;
  private JsonElement contents;
  private List<Message> requirements;

  public CommandMessage(
      int id,
      int session,
      ServiceId serviceId,
      String tag,
      JsonElement contents,
      List<Message> requirements) {
    this.id = id;
    this.session = session;
    this.serviceId = serviceId;
    this.tag = tag;
    this.contents = contents;
    this.requirements = requirements;
  }

  public int getId() {
    return id;
  }

  public int getSession() {
    return session;
  }

  public ServiceId getServiceId() {
    return serviceId;
  }

  public String getTag() {
    return tag;
  }

  public JsonElement getContents() {
    return contents;
  }

  public List<Message> getRequirements() {
    return requirements;
  }

  public Optional<SourceMessage> getSourceMessage() {
    return Messages.getSourceMessage(requirements);
  }

  public Optional<SourceMessage> getSourceMessage(Source source) {
    return Messages.getSourceMessage(requirements, source);
  }

  public Optional<ProductMessage> getProductMessage(Product product, Language lang) {
    return Messages.getProductMessage(requirements, product, lang);
  }

  public Optional<ProductMessage> getProductMessage(Source source, Product product, Language lang) {
    return Messages.getProductMessage(requirements, source, product, lang);
  }

  public static CommandMessage createSourcePosition(
      int id, int session, ServiceId serviceId, Source source, Region selection) {
    return new CommandMessage(
        id,
        session,
        serviceId,
        TAG_SOURCE_POSITION,
        GsonMonto.toJsonTree(new SourcePositionContent(source, selection)),
        new ArrayList<>());
  }

  public SourcePositionContent asSourcePosition() {
    return GsonMonto.fromJson(contents, SourcePositionContent.class);
  }

  @Override
  public String toString() {
    return String.format(
        "CommandMessage { id: %d, session: %d, serviceId: %s, tag: %s, contents: %s, requirements: %s }",
        id,
        session,
        serviceId,
        tag,
        contents,
        requirements);
  }
}
