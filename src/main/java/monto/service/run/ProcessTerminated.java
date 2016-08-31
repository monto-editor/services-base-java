package monto.service.run;

public class ProcessTerminated {
  private int exitCode;

  public ProcessTerminated(int exitCode) {
    this.exitCode = exitCode;
  }

  public int getExitCode() {
    return exitCode;
  }
}
