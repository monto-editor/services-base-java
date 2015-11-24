package monto.service.message;

import monto.service.configuration.Configuration;

import java.util.List;

@SuppressWarnings("rawtypes")
public class ConfigurationMessage implements Message {

    private String serviceID;
	private List<Configuration> configurations;

    public ConfigurationMessage(String serviceID, List<Configuration> configurations) {
        this.serviceID = serviceID;
        this.configurations = configurations;
    }

    public String getServiceID() {
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
