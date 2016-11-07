package monto.service.types;

import java.util.List;
import java.util.Optional;
import monto.service.product.ProductMessage;
import monto.service.source.SourceMessage;

public final class Messages {
  private Messages() {}

  public static Optional<SourceMessage> getSourceMessage(List<Message> messages) {
    return messages
        .stream()
        .<Optional<SourceMessage>>map(
            msg -> msg.match(src -> Optional.of(src), prd -> Optional.empty()))
        .filter(opt -> opt.isPresent())
        .findFirst()
        .flatMap(r -> r);
  }

  public static Optional<SourceMessage> getSourceMessage(List<Message> messages, Source source) {
    return messages
        .stream()
        .<Optional<SourceMessage>>map(
            msg ->
                msg.match(
                    src -> source.equals(src.getSource()) ? Optional.of(src) : Optional.empty(),
                    prd -> Optional.empty()))
        .filter(opt -> opt.isPresent())
        .findFirst()
        .flatMap(r -> r);
  }

  public static Optional<ProductMessage> getProductMessage(
      List<Message> messages, Product product, Language lang) {
    return messages
        .stream()
        .<Optional<ProductMessage>>map(
            msg ->
                msg.match(
                    src -> Optional.empty(),
                    prd ->
                        prd.getProduct().equals(product) && prd.getLanguage().equals(lang)
                            ? Optional.of(prd)
                            : Optional.empty()))
        .filter(opt -> opt.isPresent())
        .findFirst()
        .flatMap(r -> r);
  }

  public static Optional<ProductMessage> getProductMessage(
      List<Message> messages, Source source, Product product, Language lang) {
    return messages
        .stream()
        .<Optional<ProductMessage>>map(
            msg ->
                msg.match(
                    src -> Optional.empty(),
                    prd ->
                        prd.getProduct().equals(product)
                                && prd.getLanguage().equals(lang)
                                && prd.getSource().equals(source)
                            ? Optional.of(prd)
                            : Optional.empty()))
        .filter(opt -> opt.isPresent())
        .findFirst()
        .flatMap(r -> r);
  }
}
