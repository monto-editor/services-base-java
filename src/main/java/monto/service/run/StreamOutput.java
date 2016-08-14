package monto.service.run;

public class StreamOutput {
  private SourceStream sourceStream;
  private byte[] data;

  public StreamOutput(SourceStream sourceStream, byte[] data) {
    this.sourceStream = sourceStream;
    this.data = data;
  }

  public SourceStream getSourceStream() {
    return sourceStream;
  }

  public byte[] getData() {
    return data;
  }

  public enum SourceStream {
    OUT,
    ERR
  }
}
