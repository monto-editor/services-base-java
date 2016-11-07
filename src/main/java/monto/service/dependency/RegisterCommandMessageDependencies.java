package monto.service.dependency;

import java.util.Set;
import monto.service.command.CommandMessage;

public class RegisterCommandMessageDependencies {

  private CommandMessage commandMessage;
  private Set<DynamicDependency> dependencies;

  public RegisterCommandMessageDependencies(
      CommandMessage commandMessage, Set<DynamicDependency> dependencies) {
    this.commandMessage = commandMessage;
    this.dependencies = dependencies;
  }

  public CommandMessage getCommandMessage() {
    return commandMessage;
  }

  public Set<DynamicDependency> getDependencies() {
    return dependencies;
  }

  @Override
  public String toString() {
    return String.format(
        "RegisterCommandMessageDependencies { commandMessage: %s, dependencies: %s }",
        commandMessage, dependencies);
  }
}
