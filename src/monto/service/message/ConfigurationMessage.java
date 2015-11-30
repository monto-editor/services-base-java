package monto.service.message;

import monto.service.configuration.Configuration;

import java.util.List;

@SuppressWarnings("rawtypes")
public class ConfigurationMessage implements Message {

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
