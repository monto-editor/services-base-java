package monto.service.discovery;

import java.util.List;

public class DiscoveryResponse {
    List<ServiceDescription> services;

    public DiscoveryResponse(List<ServiceDescription> services) {
        this.services = services;
    }

    public List<ServiceDescription> getServices() {
        return services;
    }
}
