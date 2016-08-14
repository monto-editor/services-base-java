package monto.service.run;

import monto.service.command.CommandMessage;
import monto.service.gson.GsonMonto;
import monto.service.types.ServiceId;
import monto.service.types.Source;

public class ProcessRunContent {
  public static final String TAG = "processRun";

  private Source mainClassSource;
  // TODO: future fields: arguments, env vars, working dir, language specific configs as JsonElement

  public ProcessRunContent(Source mainClassSource) {
    this.mainClassSource = mainClassSource;
  }

  public Source getMainClassSource() {
    return mainClassSource;
  }

  public static CommandMessage createCommandMessage(
      int session, int id, ServiceId serviceId, Source mainClassSource) {
    return new CommandMessage(
        session,
        id,
        serviceId,
        ProcessRunContent.TAG,
        GsonMonto.toJsonTree(new ProcessRunContent(mainClassSource)));
  }

  public static ProcessRunContent fromCommandMessage(CommandMessage commandMessage) {
    return GsonMonto.fromJson(commandMessage.getContents(), ProcessRunContent.class);
  }
}
