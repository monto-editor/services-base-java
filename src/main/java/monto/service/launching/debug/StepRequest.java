package monto.service.launching.debug;

public class StepRequest {
  private final Thread thread;
  private final StepRange range;

  public StepRequest(Thread thread, StepRange range) {
    this.thread = thread;
    this.range = range;
  }

  public Thread getThread() {
    return thread;
  }

  public StepRange getRange() {
    return range;
  }

  public enum StepRange {
    OVER,
    INTO,
    OUT
  }
}
