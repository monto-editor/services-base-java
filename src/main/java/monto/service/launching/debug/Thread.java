package monto.service.launching.debug;

import java.util.List;

public class Thread {
  private final long id;
  private final String name;
  private final List<StackFrame> stackFrames;
  private final Breakpoint suspendingBreakpoint;

  public Thread(
      long id, String name, List<StackFrame> stackFrames, Breakpoint suspendingBreakpoint) {
    this.id = id;
    this.name = name;
    this.stackFrames = stackFrames;
    this.suspendingBreakpoint = suspendingBreakpoint;
  }

  public long getId() {
    return id;
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
        "Thread {id: %d, name: %s, stackFrames: %s, suspendingBreakpoint: %s}\n",
        id,
        name,
        stackFrames,
        suspendingBreakpoint);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (id ^ (id >>> 32));
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Thread other = (Thread) obj;
    if (id != other.id) return false;
    return true;
  }
}
