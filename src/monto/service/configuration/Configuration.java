package monto.service.configuration;

import java.util.List;

import monto.service.types.ServiceID;

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
