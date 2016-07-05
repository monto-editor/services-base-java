package monto.service.command;

import com.google.gson.JsonElement;

import monto.service.gson.GsonMonto;
import monto.service.region.Region;
import monto.service.types.ServiceId;
import monto.service.types.Source;

public class CommandMessage {
  public static final String TAG_SOURCE_POSITION = "sourcePosition";

  private ServiceId serviceId;
  private String tag;
  private JsonElement contents;

  private CommandMessage(ServiceId serviceId, String tag, JsonElement contents) {
    this.serviceId = serviceId;
    this.tag = tag;
    this.contents = contents;
  }

  public ServiceId getServiceId() {
    return serviceId;
  }

  public String getTag() {
    return tag;
  }

  public static CommandMessage createSourcePosition(
      ServiceId serviceId, Source source, Region selection) {
    return new CommandMessage(
        serviceId,
        TAG_SOURCE_POSITION,
        GsonMonto.toJsonTree(new SourcePositionContent(source, selection)));
  }

  public SourcePositionContent asSourcePosition() {
    return GsonMonto.fromJson(contents, SourcePositionContent.class);
  }

  @Override
  public String toString() {
    return String.format(
        "CommandMessage { serviceId: %s, tag: %s, contents: %s }", serviceId, tag, contents);
  }
}
