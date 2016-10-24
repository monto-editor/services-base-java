package monto.service.launching.debug;

public class ThreadNotFoundException extends Exception {
  public ThreadNotFoundException(long id) {
    super(String.format("Thread with id %d can't be found", id));
  }

  public ThreadNotFoundException(String message) {
    super(message);
  }
}
