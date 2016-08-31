package monto.service.run;

import monto.service.command.CommandMessage;
import monto.service.gson.GsonMonto;
import monto.service.types.ServiceId;

public class TerminateProcess {
  public static final String TAG = "terminateProcess";

  public static CommandMessage createCommandMessage(int session, int id, ServiceId serviceId) {
    return new CommandMessage(
        session, id, serviceId, TerminateProcess.TAG, GsonMonto.toJsonTree(null));
  }

  public static TerminateProcess fromCommandMessage(CommandMessage commandMessage) {
    return GsonMonto.fromJson(commandMessage.getContents(), TerminateProcess.class);
  }
}
