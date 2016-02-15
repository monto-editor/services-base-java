package monto.service.product;

import java.util.Arrays;
import java.util.List;

import monto.service.distributor.Visitor;
import monto.service.filedependencies.Dependency;
import monto.service.types.Language;
import monto.service.types.LongKey;
import monto.service.types.Product;
import monto.service.types.ServiceID;
import monto.service.types.Source;


public class ProductMessageWithContents extends AbstractProductMessage implements ProductMessage {

    private final Object contents;

    public ProductMessageWithContents(LongKey versionId, Source source, ServiceID serviceID, Product product, Language language, Object contents, Dependency... dependencies) {
        this(versionId, source, serviceID, product, language, contents, Arrays.asList(dependencies));
    }

    public ProductMessageWithContents(LongKey versionId, Source source, ServiceID serviceID, Product product, Language language, Object contents, List<Dependency> dependencies) {
	super(versionId, source, serviceID, product, language, dependencies);
	this.contents = contents;
    }

    @Override
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
                + "  dependencies: %s\n"
                + "}", versionId, source, serviceID, product, language, contents, dependencies);
    }

	@Override
	public <A, E extends Throwable> A accept(Visitor<A, E> visitor) throws E{
		return visitor.visitProductMessageWithContents(this);

	}

}
