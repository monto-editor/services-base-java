package monto.service.launching;

public class StreamOutput {
  private SourceStream sourceStream;
  private String data;
  private int session;

  public StreamOutput(SourceStream sourceStream, String data, int session) {
    this.sourceStream = sourceStream;
    this.data = data;
    this.session = session;
  }

  public SourceStream getSourceStream() {
    return sourceStream;
  }

  public String getData() {
    return data;
  }

  public int getSession() {
    return session;
  }

  public enum SourceStream {
    OUT,
    ERR
  }

  @Override
  public String toString() {
    return String.format("StreamOutput {%s (%d): %s}\n", sourceStream, session, data);
  }
}
