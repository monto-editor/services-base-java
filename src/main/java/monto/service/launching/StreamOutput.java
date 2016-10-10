package monto.service.launching;

public class StreamOutput {
  private SourceStream sourceStream;
  private String data;

  public StreamOutput(SourceStream sourceStream, String data) {
    this.sourceStream = sourceStream;
    this.data = data;
  }

  public SourceStream getSourceStream() {
    return sourceStream;
  }

  public String getData() {
    return data;
  }

  public enum SourceStream {
    OUT,
    ERR
  }

  @Override
  public String toString() {
    return String.format("StreamOutput {%s: %s}\n", sourceStream, data);
  }
}
