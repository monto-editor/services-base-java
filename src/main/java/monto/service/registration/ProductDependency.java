package monto.service.registration;

import java.util.function.Function;
import monto.service.types.Language;
import monto.service.types.Product;
import monto.service.types.ServiceId;

public class ProductDependency implements Dependency {

  private ServiceId serviceId;
  private Language language;
  private Product product;

  public ProductDependency(ServiceId serviceId, Product product, Language language) {
    this.serviceId = serviceId;
    this.language = language;
    this.product = product;
  }

  @Override
  public <A> A match(Function<ProductDependency, A> f, Function<SourceDependency, A> g) {
    return f.apply(this);
  }

  public ServiceId getServiceId() {
    return serviceId;
  }

  public Language getLanguage() {
    return language;
  }

  public Product getProduct() {
    return product;
  }
}
