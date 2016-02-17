package monto.service.product;

import java.util.function.Function;

import monto.service.source.SourceMessage;
import monto.service.types.Language;
import monto.service.types.LongKey;
import monto.service.types.Message;
import monto.service.types.Product;
import monto.service.types.ServiceID;
import monto.service.types.Source;


public class ProductMessage implements Message {

    private final LongKey id;
    private final Source source;
    private final ServiceID serviceID;
    private final Product product;
    private final Language language;
    private final Object contents;

    public ProductMessage(LongKey id, Source source, ServiceID serviceID, Product product, Language language, Object contents) {
        this.id = id;
        this.source = source;
        this.serviceID = serviceID;
        this.product = product;
        this.language = language;
        this.contents = contents;
    }

    public LongKey getId() {
        return id;
    }

    public Source getSource() {
        return source;
    }
    
    public ServiceID getServiceID() {
    	return serviceID;
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

    @Override
    public String toString() {
        return String.format("{"
                + "  vid: %s,\n"
                + "  source: %s,\n"
                + "  serviceID: %s,\n"
                + "  product: %s,\n"
                + "  language: %s,\n"
                + "  contents: %s,\n"
                + "}", id, source, serviceID, product, language, contents);
    }

	@Override
	public <A> A match(Function<SourceMessage, A> f, Function<ProductMessage, A> g) {
		return g.apply(this);
	}

}
