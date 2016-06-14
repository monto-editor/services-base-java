package monto.service.dependency;

import monto.service.types.Language;
import monto.service.types.Product;
import monto.service.types.ServiceId;
import monto.service.types.Source;

public class DynamicDependency {

  private Source source;
  private ServiceId serviceId;
  private Product product;
  private Language language;

  public DynamicDependency(Source source, ServiceId serviceId, Product product, Language language) {
    this.source = source;
    this.serviceId = serviceId;
    this.product = product;
    this.language = language;
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

  public static DynamicDependency sourceDependency(Source source, Language language) {
    return new DynamicDependency(source, new ServiceId("source"), new Product("source"), language);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    DynamicDependency that = (DynamicDependency) o;

    if (source != null ? !source.equals(that.source) : that.source != null) return false;
    if (serviceId != null ? !serviceId.equals(that.serviceId) : that.serviceId != null)
      return false;
    if (product != null ? !product.equals(that.product) : that.product != null) return false;
    return language != null ? language.equals(that.language) : that.language == null;
  }

  @Override
  public int hashCode() {
    int result = source != null ? source.hashCode() : 0;
    result = 31 * result + (serviceId != null ? serviceId.hashCode() : 0);
    result = 31 * result + (product != null ? product.hashCode() : 0);
    result = 31 * result + (language != null ? language.hashCode() : 0);
    return result;
  }
}
