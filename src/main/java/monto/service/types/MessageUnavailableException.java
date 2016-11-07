package monto.service.types;

public class MessageUnavailableException extends Exception {
  private static final long serialVersionUID = 2718694550371439369L;

  public MessageUnavailableException() {
    super("Message returned by the socket was null");
  }
}
