package monto.service.launching.debug;

import java.util.List;

public class HitBreakpoint {
  private final Thread hitThread;
  private final List<Thread> otherThreads;

  public HitBreakpoint(Thread hitThread, List<Thread> otherThreads) {
    this.hitThread = hitThread;
    this.otherThreads = otherThreads;
  }

  public Thread getHitThread() {
    return hitThread;
  }

  public List<Thread> getOtherThreads() {
    return otherThreads;
  }
}
