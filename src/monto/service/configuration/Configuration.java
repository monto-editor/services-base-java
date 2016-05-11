package monto.service.configuration;

import monto.service.types.ServiceID;

import java.util.List;

@SuppressWarnings("rawtypes")
public class Configuration {

    private ServiceID serviceID;
    private List<Setting> configurations;

    public Configuration(ServiceID serviceID, List<Setting> configurations) {
        this.serviceID = serviceID;
        this.configurations = configurations;
    }

    public ServiceID getServiceID() {
        return serviceID;
    }

    public List<Setting> getConfigurations() {
        return configurations;
    }

    @Override
    public String toString() {
        return String.format("ConfigurationMessage { service = %s, configurations = %s }", serviceID, configurations);
    }
}
