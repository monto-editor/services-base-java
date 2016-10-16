package monto.service.types;

public class Command implements Comparable<Command> {
  private String command;

  public Command(String command) {
    this.command = command;
  }

  public String getCommand() {
    return command;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Command command1 = (Command) o;
    return command.equals(command1.command);
  }

  @Override
  public int hashCode() {
    return command.hashCode();
  }

  @Override
  public int compareTo(Command other) {
    return this.command.compareTo(other.command);
  }

  @Override
  public String toString() {
    return command;
  }
}
