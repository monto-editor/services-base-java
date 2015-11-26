package monto.service.message;

import monto.service.configuration.Option;

@SuppressWarnings("rawtypes")
public class RegisterServiceRequest {

    private final ServiceID serviceID;
    private final String label;
    private final String description;
    private final Language language;
    private final Product product;
	private final Option[] options;
    private final String[] dependencies;


    public RegisterServiceRequest(ServiceID serviceID, String label, String description, Language language, Product product, Option[] options, String... dependencies) {
        this.serviceID = serviceID;
        this.label = label;
        this.description = description;
        this.language = language;
        this.product = product;
        this.options = options;
        this.dependencies = dependencies;
    }

    public ServiceID getServiceID() {
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

    public Option[] getOptions() {
        return options;
    }

    public String[] getDependencies() {
        return dependencies;
    }
}
