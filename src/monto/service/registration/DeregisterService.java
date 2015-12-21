package monto.service.registration;

import monto.service.types.ServiceID;

public class DeregisterService {

    private final ServiceID deregisterServiceID;

    public DeregisterService(ServiceID deregisterServiceID) {
        this.deregisterServiceID = deregisterServiceID;
    }

    public ServiceID getDeregisterServiceID() {
        return deregisterServiceID;
    }
}
