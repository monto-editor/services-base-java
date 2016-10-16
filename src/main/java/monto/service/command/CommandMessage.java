package monto.service.command;

import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import monto.service.product.ProductMessage;
import monto.service.source.SourceMessage;
import monto.service.types.Command;
import monto.service.types.Language;
import monto.service.types.Message;
import monto.service.types.Messages;
import monto.service.types.Product;
import monto.service.types.Source;

public class CommandMessage {
  private int session;
  private int id;
  private Command command;
  private Language language;
  private JsonElement contents;
  private List<Message> requirements;

  public CommandMessage(
      int session,
      int id,
      Command command,
      Language language,
      JsonElement contents,
      List<Message> requirements) {
    this.session = session;
    this.id = id;
    this.command = command;
    this.language = language;
    this.contents = contents;
    this.requirements = requirements;
  }

  public CommandMessage(
      int session, int id, Command command, Language language, JsonElement contents) {
    this.session = session;
    this.id = id;
    this.command = command;
    this.language = language;
    this.contents = contents;
    this.requirements = new ArrayList<>();
  }

  public int getSession() {
    return session;
  }

  public int getId() {
    return id;
  }

  public Command getCommand() {
    return command;
  }

  public Language getLanguage() {
    return language;
  }

  public JsonElement getContents() {
    return contents;
  }

  public List<Message> getRequirements() {
    return requirements;
  }

  public Optional<SourceMessage> getSourceMessage() {
    return Messages.getSourceMessage(requirements);
  }

  public Optional<SourceMessage> getSourceMessage(Source source) {
    return Messages.getSourceMessage(requirements, source);
  }

  public Optional<ProductMessage> getProductMessage(Product product, Language lang) {
    return Messages.getProductMessage(requirements, product, lang);
  }

  public Optional<ProductMessage> getProductMessage(Source source, Product product, Language lang) {
    return Messages.getProductMessage(requirements, source, product, lang);
  }

  @Override
  public String toString() {
    return String.format(
        "CommandMessage { session: %d, id: %d, command: %s, language: %s, contents: %s, requirements: %s }",
        session,
        id,
        command,
        language,
        contents,
        requirements);
  }
}
