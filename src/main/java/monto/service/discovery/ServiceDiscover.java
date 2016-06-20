package monto.service.discovery;

import monto.service.types.Language;
import monto.service.types.ServiceId;
import monto.service.types.Source;

public class ServiceDiscover {
  private ServiceId serviceId;
  private Language language;
  private Source source;

  public ServiceDiscover(ServiceId serviceId, Language language, Source source) {
    this.serviceId = serviceId;
    this.language = language;
    this.source = source;
  }

  public ServiceId getServiceId() {
    return serviceId;
  }

  public Language getLanguage() {
    return language;
  }

  public Source getSource() {
    return source;
  }
}
