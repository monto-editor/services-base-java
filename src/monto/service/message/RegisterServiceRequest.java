package monto.service.message;

import monto.service.configuration.Configuration;

import java.util.List;

public class RegisterServiceRequest {

    private final String serviceID;
    private final String label;
    private final String description;
    private final Language language;
    private final Product product;
    private final List<Configuration> configuration;
    private final String[] dependencies;


    public RegisterServiceRequest(String serviceID, String label, String description, Language language, Product product, List<Configuration> configuration, String... dependencies) {
        this.serviceID = serviceID;
        this.label = label;
        this.description = description;
        this.language = language;
        this.product = product;
        this.configuration = configuration;
        this.dependencies = dependencies;
    }

    public String getServiceID() {
        return serviceID;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }

    public Language getLanguage() {
        return language;
    }

    public Product getProduct() {
        return product;
    }

    public List<Configuration> getConfiguration() {
        return configuration;
    }

    public String[] getDependencies() {
        return dependencies;
    }
}
