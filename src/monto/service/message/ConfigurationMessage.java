package monto.service.message;

import monto.service.configuration.Configuration;

import java.util.List;

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
}
