package monto.service.registration;

import java.util.List;

import monto.service.configuration.Option;
import monto.service.types.ServiceID;

@SuppressWarnings("rawtypes")
public class RegisterServiceRequest {

    private final ServiceID serviceID;
    private final String label;
    private final String description;
	private final List<Option> options;
	private final List<ProductDescription> products;
    private final List<Dependency> dependencies;

    public RegisterServiceRequest(ServiceID serviceID, String label, String description, List<ProductDescription> products, List<Option> options, List<Dependency> dependencies) {
        this.serviceID = serviceID;
        this.label = label;
        this.description = description;
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

    public List<ProductDescription> getProducts() {
        return products;
    }

    public List<Option> getOptions() {
        return options;
    }

    public List<Dependency> getDependencies() {
        return dependencies;
    }
}
