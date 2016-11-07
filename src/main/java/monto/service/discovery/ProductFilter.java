package monto.service.discovery;

import java.util.function.Function;
import monto.service.types.Product;

public class ProductFilter implements Filter {

  private Product product;

  public ProductFilter(Product product) {
    this.product = product;
  }

  @Override
  public <A> A match(
      Function<ServiceIdFilter, A> f, Function<ProductFilter, A> g, Function<LanguageFilter, A> h) {
    return g.apply(this);
  }

  public Product getProduct() {
    return product;
  }
}
