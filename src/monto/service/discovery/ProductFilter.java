package monto.service.discovery;

import java.util.function.Function;

public class ProductFilter implements Filter {

	private String product;

	public ProductFilter(String product) {
		this.product = product;
	}

	@Override
	public <A> A match(Function<ServiceIDFilter, A> f, Function<ProductFilter, A> g, Function<LanguageFilter, A> h) {
		return g.apply(this);
	}

	public String getProduct() {
		return product;
	}

}
