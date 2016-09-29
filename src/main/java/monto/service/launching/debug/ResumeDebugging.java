package monto.service.launching.debug;

import monto.service.command.CommandMessage;
import monto.service.gson.GsonMonto;
import monto.service.types.ServiceId;

public class ResumeDebugging {
  public static final String TAG = "resumeProcess";

  public static CommandMessage createCommandMessage(int session, int id, ServiceId serviceId) {
    return new CommandMessage(
        session, id, serviceId, ResumeDebugging.TAG, GsonMonto.toJsonTree(null));
  }

  // fromCommandMessage() method is not necessary, because ResumeDebugging doesn't hold any
  // information, that need to be parsed by Gson
}
