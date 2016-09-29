package monto.service.launching.debug;

public class Variable {
  public static final String KIND_LOCAL = "local";
  public static final String KIND_THIS = "this";
  public static final String KIND_ARGUMENT = "argument";

  private final String name;
  private final String type;
  private final String value;
  private final String kind;
  //TODO kind besser als image/icon  darstellen

  public Variable(String name, String type, String value, String kind) {
    this.name = name;
    this.type = type;
    this.value = value;
    this.kind = kind;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public String getValue() {
    return value;
  }

  public String getKind() {
    return kind;
  }

  @Override
  public String toString() {
    return String.format("Variable {name: %s, value: %s}\n", name, value);
  }
}
