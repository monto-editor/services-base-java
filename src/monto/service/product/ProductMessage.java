package monto.service.product;

import monto.service.source.SourceMessage;
import monto.service.types.*;

import java.util.function.Function;


public class ProductMessage implements Message {

    private final LongKey id;
    private final Source source;
    private final ServiceId serviceId;
    private final Product product;
    private final Language language;
    private final Object contents;
    private final long time;

    public ProductMessage(LongKey id, Source source, ServiceId serviceId, Product product, Language language, Object contents, long time) {
        this.id = id;
        this.source = source;
        this.serviceId = serviceId;
        this.product = product;
        this.language = language;
        this.contents = contents;
        this.time = time;
    }

    public LongKey getId() {
        return id;
    }

    public Source getSource() {
        return source;
    }

    public ServiceId getServiceId() {
        return serviceId;
    }

    public Product getProduct() {
        return product;
    }

    public Language getLanguage() {
        return language;
    }

    public Object getContents() {
        return contents;
    }

    public long getTime() {
        return time;
    }

    @Override
    public String toString() {
        return String.format("{"
                + "  vid: %s,\n"
                + "  source: %s,\n"
                + "  serviceId: %s,\n"
                + "  product: %s,\n"
                + "  language: %s,\n"
                + "  contents: %s,\n"
                + "}", id, source, serviceId, product, language, contents);
    }

    @Override
    public <A> A match(Function<SourceMessage, A> f, Function<ProductMessage, A> g) {
        return g.apply(this);
    }

}
