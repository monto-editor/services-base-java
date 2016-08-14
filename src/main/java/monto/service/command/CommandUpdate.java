package monto.service.command;

import com.google.gson.JsonElement;

import monto.service.types.ServiceId;

public class CommandUpdate {
  private int commandMessageSession;
  private int commandMessageId;
  private ServiceId sourceServiceId;
  private String tag;
  private JsonElement contents;

  public CommandUpdate(
      int commandMessageSession,
      int commandMessageId,
      ServiceId sourceServiceId,
      String tag,
      JsonElement contents) {
    this.commandMessageSession = commandMessageSession;
    this.commandMessageId = commandMessageId;
    this.sourceServiceId = sourceServiceId;
    this.tag = tag;
    this.contents = contents;
  }

  public int getCommandMessageSession() {
    return commandMessageSession;
  }

  public int getCommandMessageId() {
    return commandMessageId;
  }

  public ServiceId getSourceServiceId() {
    return sourceServiceId;
  }

  public String getTag() {
    return tag;
  }

  public JsonElement getContents() {
    return contents;
  }

  @Override
  public String toString() {
    return String.format(
        "CommandUpdate { commandMessageSession: %d, commandMessageId: %d, sourceServiceId: %s, tag: %s, contents: %s }",
        commandMessageSession,
        commandMessageId,
        sourceServiceId,
        tag,
        contents);
  }
}
