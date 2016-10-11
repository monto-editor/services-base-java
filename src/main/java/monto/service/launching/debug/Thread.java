package monto.service.launching.debug;

import java.util.List;

public class Thread {
  private final String name;
  private final List<StackFrame> stackFrames;
  private final Breakpoint suspendingBreakpoint;

  public Thread(String name, List<StackFrame> stackFrames, Breakpoint suspendingBreakpoint) {
    this.name = name;
    this.stackFrames = stackFrames;
    this.suspendingBreakpoint = suspendingBreakpoint;
  }

  public String getName() {
    return name;
  }

  public List<StackFrame> getStackFrames() {
    return stackFrames;
  }

  public Breakpoint getSuspendingBreakpoint() {
    return suspendingBreakpoint;
  }

  @Override
  public String toString() {
    return String.format(
        "Thread {stackFrames: %s, suspendingBreakpoint: %s}\n", stackFrames, suspendingBreakpoint);
  }
}
