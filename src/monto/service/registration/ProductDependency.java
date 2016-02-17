package monto.service.registration;

import java.util.function.Function;

import monto.service.types.Language;
import monto.service.types.Product;
import monto.service.types.ServiceID;

public class ProductDependency implements Dependency {

	private ServiceID serviceID;
	private Language language;
	private Product product;

	public ProductDependency(ServiceID serviceID, Product product, Language language) {
		this.serviceID = serviceID;
		this.language = language;
		this.product = product;
	}

	@Override
	public <A> A match(Function<ProductDependency, A> f, Function<SourceDependency, A> g) {
		return f.apply(this);
	}

	public ServiceID getServiceID() {
		return serviceID;
	}

	public Language getLanguage() {
		return language;
	}

	public Product getProduct() {
		return product;
	}

}
