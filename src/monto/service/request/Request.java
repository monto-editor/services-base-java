package monto.service.request;

import monto.service.types.Source;

import java.util.List;
import java.util.Optional;

import monto.service.product.ProductMessage;
import monto.service.source.SourceMessage;
import monto.service.types.Language;
import monto.service.types.Message;
import monto.service.types.Product;

/**
 * A request message is send from the broker to language services.
 * It contains the source for which the service should produce
 * its result and all necessary source and product messages.
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
        return requirements
                .stream()
                .<Optional<SourceMessage>>map(msg -> msg.match(src -> Optional.of(src), prd -> Optional.empty()))
                .filter(opt -> opt.isPresent())
                .findFirst()
                .flatMap(r -> r);
    }

    public Optional<SourceMessage> getSourceMessage(Source source) {
        return requirements
                .stream()
                .<Optional<SourceMessage>>map(msg -> msg.match(src -> source.equals(src.getSource())
                        ? Optional.of(src)
                        : Optional.empty(),
                        prd -> Optional.empty()))
                .filter(opt -> opt.isPresent())
                .findFirst()
                .flatMap(r -> r);
    }

    public Optional<ProductMessage> getProductMessage(Product product, Language lang) {
        return requirements
                .stream()
                .<Optional<ProductMessage>>map(msg -> msg.match(src -> Optional.empty(), prd ->
                        prd.getProduct().equals(product) && prd.getLanguage().equals(lang)
                                ? Optional.of(prd)
                                : Optional.empty()))
                .filter(opt -> opt.isPresent())
                .findFirst()
                .flatMap(r -> r);
    }

    public Optional<ProductMessage> getProductMessage(Source source, Product product, Language lang) {
        return requirements
                .stream()
                .<Optional<ProductMessage>>map(msg -> msg.match(src -> Optional.empty(), prd ->
                        prd.getProduct().equals(product) && prd.getLanguage().equals(lang) && prd.getSource().equals(source)
                                ? Optional.of(prd)
                                : Optional.empty()))
                .filter(opt -> opt.isPresent())
                .findFirst()
                .flatMap(r -> r);
    }
}
