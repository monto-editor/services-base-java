package monto.service.configuration;

import java.util.List;
import monto.service.types.ServiceId;

@SuppressWarnings("rawtypes")
public class Configuration {

  private ServiceId serviceId;
  private List<Setting> settings;

  public Configuration(ServiceId serviceId, List<Setting> settings) {
    this.serviceId = serviceId;
    this.settings = settings;
  }

  public ServiceId getServiceId() {
    return serviceId;
  }

  public List<Setting> getSettings() {
    return settings;
  }

  @Override
  public String toString() {
    return String.format(
        "ConfigurationMessage { service = %s, settings = %s }", serviceId, settings);
  }
}
