package monto.service.discovery;

import monto.service.types.ServiceId;

import java.util.function.Function;

public class ServiceIdFilter implements Filter {

  private ServiceId serviceId;

  public ServiceIdFilter(ServiceId serviceId) {
    this.serviceId = serviceId;
  }

  @Override
  public <A> A match(
      Function<ServiceIdFilter, A> f, Function<ProductFilter, A> g, Function<LanguageFilter, A> h) {
    return f.apply(this);
  }

  public ServiceId getServiceId() {
    return serviceId;
  }
}
