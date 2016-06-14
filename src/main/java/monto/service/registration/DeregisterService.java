package monto.service.registration;

import monto.service.types.ServiceId;

public class DeregisterService {

  private final ServiceId deregisterServiceId;

  public DeregisterService(ServiceId deregisterServiceId) {
    this.deregisterServiceId = deregisterServiceId;
  }

  public ServiceId getDeregisterServiceId() {
    return deregisterServiceId;
  }
}
