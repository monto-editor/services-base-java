package monto.service.launching;

import java.util.List;
import monto.service.launching.debug.Breakpoint;
import monto.service.types.Source;

public class DebugLaunchConfiguration extends LaunchConfiguration {

  private final List<Breakpoint> breakpoints;

  public DebugLaunchConfiguration(Source mainClassSource, List<Breakpoint> breakpoints) {
    super(mainClassSource);
    this.breakpoints = breakpoints;
  }

  public List<Breakpoint> getBreakpoints() {
    return breakpoints;
  }
}
