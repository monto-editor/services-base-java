package monto.service.configuration;

import java.util.List;

import monto.service.types.ServiceID;

@SuppressWarnings("rawtypes")
public class ConfigurationMessage {

    private ServiceID serviceID;
	private List<Configuration> configurations;

    public ConfigurationMessage(ServiceID serviceID, List<Configuration> configurations) {
        this.serviceID = serviceID;
        this.configurations = configurations;
    }

    public ServiceID getServiceID() {
        return serviceID;
    }

    public List<Configuration> getConfigurations() {
        return configurations;
    }
    
    @Override
    public String toString() {
    	return String.format("ConfigurationMessage { service = %s, configurations = %s }", serviceID, configurations);
    }
}
