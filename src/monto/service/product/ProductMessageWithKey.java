package monto.service.product;

import java.util.List;

import monto.service.distributor.Visitor;
import monto.service.filedependencies.Dependency;
import monto.service.types.Language;
import monto.service.types.LongKey;
import monto.service.types.Product;
import monto.service.types.ServiceID;
import monto.service.types.Source;

public class ProductMessageWithKey extends AbstractProductMessage {

	private final Integer contentsKey;

	public ProductMessageWithKey(LongKey versionId, Source source, ServiceID serviceID, Product product,
			Language language, int contentsKey, Dependency... dependencies) {
		super(versionId, source, serviceID, product, language, dependencies);
		this.contentsKey = contentsKey;
	}

	public ProductMessageWithKey(LongKey versionId, Source source, ServiceID serviceID, Product product,
			Language language, int contentsKey, List<Dependency> dependencies) {
		super(versionId, source, serviceID, product, language, dependencies);
		this.contentsKey = contentsKey;
	}

	public Integer getContentsKey() {
		return contentsKey;
	}

    @Override
    public String toString() {
        return String.format("{"
                + "  vid: %s,\n"
                + "  source: %s,\n"
                + "  serviceID: %s,\n"
                + "  product: %s,\n"
                + "  language: %s,\n"
                + "  contentsKey: %s,\n"
                + "  dependencies: %s\n"
                + "}", versionId, source, serviceID, product, language, contentsKey, dependencies);
    }

	@Override
	public <A, E extends Throwable> A accept(Visitor<A, E> visitor) throws E{
		return visitor.visitProductMessageWithKey(this);
	}

}
