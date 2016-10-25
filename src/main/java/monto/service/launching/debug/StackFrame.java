package monto.service.launching.debug;

import java.util.List;
import java.util.Optional;
import monto.service.types.Source;

public class StackFrame {
  private final Source source;
  private final int lineNumber;
  private final List<Variable> variables;

  public StackFrame(Source source, int lineNumber, List<Variable> variables) {
    this.source = source;
    this.lineNumber = lineNumber;
    this.variables = variables;
  }

  public Optional<Source> getSource() {
    return Optional.ofNullable(source);
  }

  public int getLineNumber() {
    return lineNumber;
  }

  public List<Variable> getVariables() {
    return variables;
  }

  @Override
  public String toString() {
    return String.format("StackFrame {variables: %s}\n", variables);
  }
}
