package monto.service.registration;

import monto.service.types.Language;
import monto.service.types.Product;

public class ProductDescription {
	private Product product;
	private Language language;

	public ProductDescription(Product product, Language language) {
		this.product = product;
		this.language = language;
	}

	public Product getProduct() {
		return product;
	}

	public Language getLanguage() {
		return language;
	}
}
