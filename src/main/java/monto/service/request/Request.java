package monto.service.request;

import java.util.List;
import java.util.Optional;
import monto.service.product.ProductMessage;
import monto.service.source.SourceMessage;
import monto.service.types.Language;
import monto.service.types.Message;
import monto.service.types.Messages;
import monto.service.types.Product;
import monto.service.types.Source;

/**
 * A request message is send from the broker to language services. It contains the source for which
 * the service should produce its result and all necessary source and product messages.
 */
public class Request {
  private Source source;
  private List<Message> requirements;

  public Request(Source source, List<Message> requirements) {
    this.source = source;
    this.requirements = requirements;
  }

  public Source getSource() {
    return source;
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
}
