package monto.service.registration;

import monto.service.types.Command;
import monto.service.types.Language;

public class CommandDescription {
  private Command command;
  private Language language;

  public CommandDescription(Command command, Language language) {
    this.command = command;
    this.language = language;
  }

  public Command getCommand() {
    return command;
  }

  public Language getLanguage() {
    return language;
  }
}
