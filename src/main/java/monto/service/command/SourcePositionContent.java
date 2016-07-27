package monto.service.command;

import monto.service.gson.GsonMonto;
import monto.service.region.Region;
import monto.service.types.ServiceId;
import monto.service.types.Source;

public class SourcePositionContent {
  public static final String TAG_SOURCE_POSITION = "sourcePosition";

  private Source source;
  private Region selection;

  public SourcePositionContent(Source source, Region selection) {
    this.source = source;
    this.selection = selection;
  }

  public Source getSource() {
    return source;
  }

  public Region getSelection() {
    return selection;
  }

  public static CommandMessage createCommandMessage(
      int id, int session, ServiceId serviceId, Source source, Region selection) {
    return new CommandMessage(
        id,
        session,
        serviceId,
        SourcePositionContent.TAG_SOURCE_POSITION,
        GsonMonto.toJsonTree(new SourcePositionContent(source, selection)));
  }

  public static SourcePositionContent fromCommandMessage(CommandMessage commandMessage) {
    return GsonMonto.fromJson(commandMessage.getContents(), SourcePositionContent.class);
  }
}
