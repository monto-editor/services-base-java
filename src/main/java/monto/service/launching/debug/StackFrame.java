package monto.service.launching.debug;

import java.util.List;
import java.util.Optional;
import monto.service.region.Region;
import monto.service.types.Source;

public class StackFrame {
  private final Source source;
  private final int lineNumber;
  private final Region region;
  private final List<Variable> variables;

  public StackFrame(Source source, int lineNumber, Region region, List<Variable> variables) {
    this.source = source;
    this.lineNumber = lineNumber;
    this.region = region;
    this.variables = variables;
  }

  public Source getSource() {
    return source;
  }

  public int getLineNumber() {
    return lineNumber;
  }

  public Optional<Region> getRegion() {
    return Optional.ofNullable(region);
  }

  public List<Variable> getVariables() {
    return variables;
  }

  @Override
  public String toString() {
    return String.format("StackFrame {variables: %s}\n", variables);
  }
}
