package monto.service.launching.debug;

import java.util.List;

public class StackFrame {
  private final String name;
  private final List<Variable> variables;

  public StackFrame(String name, List<Variable> variables) {
    this.name = name;
    this.variables = variables;
  }

  public String getName() {
    return name;
  }

  public List<Variable> getVariables() {
    return variables;
  }

  @Override
  public String toString() {
    return String.format("StackFrame {variables: %s}\n", variables);
  }
}
