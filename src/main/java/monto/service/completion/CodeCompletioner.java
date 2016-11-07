package monto.service.completion;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import monto.service.MontoService;
import monto.service.ZMQConfiguration;
import monto.service.command.CommandMessage;
import monto.service.command.Commands;
import monto.service.completion.Completion;
import monto.service.completion.CompletionRequest;
import monto.service.dependency.DynamicDependency;
import monto.service.dependency.RegisterCommandMessageDependencies;
import monto.service.gson.GsonMonto;
import monto.service.identifier.Identifier;
import monto.service.product.ProductMessage;
import monto.service.product.Products;
import monto.service.region.Region;
import monto.service.registration.CommandDescription;
import monto.service.registration.ProductDescription;
import monto.service.source.SourceMessage;
import monto.service.types.Language;
import monto.service.types.ServiceId;

public class CodeCompletioner extends MontoService {

  protected final ServiceId serviceId;
  protected final Language language;
  protected final ServiceId identifierFinderServiceId;

  public CodeCompletioner(
      ZMQConfiguration zmqConfig,
      ServiceId serviceId,
      String title,
      String description,
      Language language,
      ServiceId identifierFinderServiceId) {
    super(
        zmqConfig,
        serviceId,
        title,
        description,
        productDescriptions(new ProductDescription(Products.COMPLETIONS, language)),
        options(),
        dependencies(),
        commands(new CommandDescription(Commands.COMPLETE_CODE, language)));
    this.serviceId = serviceId;
    this.language = language;
    this.identifierFinderServiceId = identifierFinderServiceId;
  }

  @Override
  public void onCommandMessage(CommandMessage commandMessage) {
    long start = System.nanoTime();

    if (commandMessage.getCommand().equals(Commands.COMPLETE_CODE)) {
      CompletionRequest completionRequest =
          GsonMonto.fromJson(commandMessage.getContents(), CompletionRequest.class);

      Optional<SourceMessage> maybeSourceMessage =
          commandMessage.getSourceMessage(completionRequest.getSource());
      Optional<ProductMessage> maybeProductMessage =
          commandMessage.getProductMessage(
              completionRequest.getSource(), Products.IDENTIFIER, language);
      if (maybeSourceMessage.isPresent() && maybeProductMessage.isPresent()) {

        // System.out.println("CommandMessage contains all dependencies.");

        SourceMessage sourceMessage = maybeSourceMessage.get();
        List<Identifier> identifiers =
            GsonMonto.fromJsonArray(maybeProductMessage.get(), Identifier[].class);

        int cursorPosition = completionRequest.getSelection().getStartOffset();

        // Find last non alphanumerical character before cursor position
        String textBeforeCursor =
            new Region(0, cursorPosition).extract(sourceMessage.getContents());
        int startOfCurrentWord = 0;
        Matcher matcher = Pattern.compile("\\W").matcher(textBeforeCursor);
        while (matcher.find()) {
          startOfCurrentWord = matcher.start();
        }

        String toBeCompleted =
            sourceMessage.getContents().substring(startOfCurrentWord, cursorPosition).trim();
        // System.out.println(toBeCompleted);

        int deleteBeginOffset = startOfCurrentWord + 1;
        List<Completion> relevant =
            identifiers
                .stream()
                .filter(identifier -> identifier.getIdentifier().startsWith(toBeCompleted))
                .map(
                    identifier
                        -> new Completion(
                            identifier.getIdentifier(),
                            identifier.getIdentifier(),
                            deleteBeginOffset,
                            cursorPosition - deleteBeginOffset,
                            identifierTypeToIcon(identifier.getType())))
                .collect(Collectors.toList());

        // System.out.printf("Relevant: %s\n", relevant);

        long end = System.nanoTime();
        sendProductMessage(
            sourceMessage.getId(),
            sourceMessage.getSource(),
            Products.COMPLETIONS,
            language,
            GsonMonto.toJsonTree(relevant),
            end - start);
      } else {
        // Request dependencies
        Set<DynamicDependency> dependencies = new HashSet<>();
        dependencies.add(
            DynamicDependency.sourceDependency(completionRequest.getSource(), language));
        dependencies.add(
            new DynamicDependency(
                completionRequest.getSource(),
                identifierFinderServiceId,
                Products.IDENTIFIER,
                language));

        System.out.println(
            "Registering new CommandMessage dependencies: " + dependencies.toString());

        registerCommandMessageDependencies(
            new RegisterCommandMessageDependencies(commandMessage, dependencies));
      }
    }
  }

  private URL identifierTypeToIcon(String identifierType) {
    switch (identifierType) {
      case "import":
        return getResource("package.png");
      case "class":
        return getResource("class.png");
      case "interface":
        return getResource("interface.png");
      case "enum":
        return getResource("enum.png");
      case "method":
        return getResource("method.png");
      case "field":
        return getResource("field.png");
      case "variable":
        return getResource("variable.png");
      case "constant":
        return getResource("variable.png");
      default:
        return null;
    }
  }
}
