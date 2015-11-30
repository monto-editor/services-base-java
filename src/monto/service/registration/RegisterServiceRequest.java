package monto.service.registration;

import monto.service.configuration.Option;
import monto.service.message.Language;
import monto.service.message.Product;
import monto.service.message.ServiceID;

@SuppressWarnings("rawtypes")
public class RegisterServiceRequest {

    private final ServiceID serviceID;
    private final String label;
    private final String description;
    private final Language language;
    private final Product product;
	private final Option[] options;
    private final Dependency[] dependencies;

    public RegisterServiceRequest(ServiceID serviceID, String label, String description, Language language, Product product, Option[] options, Dependency... dependencies) {
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

    public Dependency[] getDependencies() {
        return dependencies;
    }
}
