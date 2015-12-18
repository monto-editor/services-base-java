package monto.service.registration;

import java.util.List;

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
    private final List<Product> products;
	private final List<Option> options;
    private final List<Dependency> dependencies;

    public RegisterServiceRequest(ServiceID serviceID, String label, String description, Language language, List<Product> products, List<Option> options, List<Dependency> dependencies) {
        this.serviceID = serviceID;
        this.label = label;
        this.description = description;
        this.language = language;
        this.products = products;
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

    public List<Product> getProducts() {
        return products;
    }

    public List<Option> getOptions() {
        return options;
    }

    public List<Dependency> getDependencies() {
        return dependencies;
    }
}
