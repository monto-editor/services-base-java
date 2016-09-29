package monto.service.launching;

import monto.service.command.CommandMessage;
import monto.service.gson.GsonMonto;
import monto.service.types.ServiceId;

public class TerminateProcess {
  public static final String TAG = "terminateProcess";

  public static CommandMessage createCommandMessage(int session, int id, ServiceId serviceId) {
    return new CommandMessage(
        session, id, serviceId, TerminateProcess.TAG, GsonMonto.toJsonTree(null));
  }

  // fromCommandMessage() method is not necessary, because TerminateProcess doesn't hold any
  // information, that need to be parsed by Gson
}
