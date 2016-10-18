package monto.service.launching.debug;

import monto.service.types.Source;

public class Breakpoint {
  private final Source source;
  private final int lineNumber;

  public Breakpoint(Source source, int lineNumber) {
    this.source = source;
    this.lineNumber = lineNumber;
  }

  public Source getSource() {
    return source;
  }

  public int getLineNumber() {
    return lineNumber;
  }

  @Override
  public String toString() {
    return String.format("Breakpoint {source: %s, lineNumber: %d}\n", source, lineNumber);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Breakpoint that = (Breakpoint) o;

    return lineNumber == that.lineNumber && source.equals(that.source);
  }

  @Override
  public int hashCode() {
    int result = source.hashCode();
    result = 31 * result + lineNumber;
    return result;
  }
}
