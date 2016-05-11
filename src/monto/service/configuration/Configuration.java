package monto.service.configuration;

import monto.service.types.ServiceId;

import java.util.List;

@SuppressWarnings("rawtypes")
public class Configuration {

    private ServiceId serviceId;
    private List<Setting> configurations;

    public Configuration(ServiceId serviceId, List<Setting> configurations) {
        this.serviceId = serviceId;
        this.configurations = configurations;
    }

    public ServiceId getServiceId() {
        return serviceId;
    }

    public List<Setting> getConfigurations() {
        return configurations;
    }

    @Override
    public String toString() {
        return String.format("ConfigurationMessage { service = %s, configurations = %s }", serviceId, configurations);
    }
}
