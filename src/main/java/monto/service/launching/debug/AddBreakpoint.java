package monto.service.launching.debug;

import monto.service.command.CommandMessage;
import monto.service.gson.GsonMonto;
import monto.service.types.ServiceId;

public class AddBreakpoint {
  public static final String TAG = "addBreakpoint";

  private final Breakpoint breakpoint;

  public AddBreakpoint(Breakpoint breakpoint) {
    this.breakpoint = breakpoint;
  }

  public Breakpoint getBreakpoint() {
    return breakpoint;
  }

  public static CommandMessage createCommandMessage(
      int session, int id, ServiceId serviceId, Breakpoint breakpoint) {
    return new CommandMessage(
        session,
        id,
        serviceId,
        AddBreakpoint.TAG,
        GsonMonto.toJsonTree(new AddBreakpoint(breakpoint)));
  }

  public static AddBreakpoint fromCommandMessage(CommandMessage commandMessage) {
    return GsonMonto.fromJson(commandMessage.getContents(), AddBreakpoint.class);
  }
}
