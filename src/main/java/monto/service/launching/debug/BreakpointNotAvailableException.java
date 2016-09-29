package monto.service.launching.debug;

public class BreakpointNotAvailableException extends Exception {
  public BreakpointNotAvailableException(Breakpoint breakpoint) {
    super(
        String.format(
            "Breakpoint in %s:%d isn't available",
            breakpoint.getSource(),
            breakpoint.getLineNumber()));
  }

  public BreakpointNotAvailableException(String message) {
    super(message);
  }
}
