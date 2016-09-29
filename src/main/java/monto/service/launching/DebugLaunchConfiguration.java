package monto.service.launching;

import java.util.List;
import monto.service.command.CommandMessage;
import monto.service.gson.GsonMonto;
import monto.service.launching.debug.Breakpoint;
import monto.service.types.ServiceId;
import monto.service.types.Source;

public class DebugLaunchConfiguration extends LaunchConfiguration {
  public static final String TAG = "debugLaunchConfig";

  private final List<Breakpoint> breakpoints;

  public DebugLaunchConfiguration(
      String mode, Source mainClassSource, List<Breakpoint> breakpoints) {
    super(mode, mainClassSource);
    this.breakpoints = breakpoints;
  }

  public List<Breakpoint> getBreakpoints() {
    return breakpoints;
  }

  public static CommandMessage createCommandMessage(
      int session,
      int id,
      ServiceId serviceId,
      String mode,
      Source mainClassSource,
      List<Breakpoint> breakpoints) {
    return new CommandMessage(
        session,
        id,
        serviceId,
        DebugLaunchConfiguration.TAG,
        GsonMonto.toJsonTree(new DebugLaunchConfiguration(mode, mainClassSource, breakpoints)));
  }

  @Deprecated
  public static CommandMessage createCommandMessage(
      int session, int id, ServiceId serviceId, String mode, Source mainClassSource) {
    throw new UnsupportedOperationException(
        "use createCommandMessage(int session, int id, ServiceId serviceId, String mode, "
            + "Source mainClassSource, List<Breakpoint> breakpoints) instead");
  }

  public static DebugLaunchConfiguration fromCommandMessage(CommandMessage commandMessage) {
    return GsonMonto.fromJson(commandMessage.getContents(), DebugLaunchConfiguration.class);
  }
}
