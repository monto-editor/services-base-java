package monto.service.run;

import monto.service.command.CommandMessage;
import monto.service.gson.GsonMonto;
import monto.service.types.ServiceId;

public class ProcessTerminateContent {
  public static final String TAG = "processTerminate";

  public static CommandMessage createCommandMessage(int session, int id, ServiceId serviceId) {
    return new CommandMessage(
        session, id, serviceId, ProcessTerminateContent.TAG, GsonMonto.toJsonTree(null));
  }

  public static ProcessTerminateContent fromCommandMessage(CommandMessage commandMessage) {
    return GsonMonto.fromJson(commandMessage.getContents(), ProcessTerminateContent.class);
  }
}
