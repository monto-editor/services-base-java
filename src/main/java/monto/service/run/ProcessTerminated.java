package monto.service.run;

public class ProcessTerminated {
  private int exitCode;
  private int session;

  public ProcessTerminated(int exitCode, int session) {
    this.exitCode = exitCode;
    this.session = session;
  }

  public int getExitCode() {
    return exitCode;
  }

  public int getSession() {
    return session;
  }
}
