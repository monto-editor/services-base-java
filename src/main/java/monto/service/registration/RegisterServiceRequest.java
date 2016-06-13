package monto.service.registration;

import monto.service.configuration.Option;
import monto.service.types.ServiceId;

import java.util.List;

@SuppressWarnings("rawtypes")
public class RegisterServiceRequest {

    private final ServiceId serviceId;
    private final String label;
    private final String description;
    private final List<Option> options;
    private final List<ProductDescription> products;
    private final List<Dependency> dependencies;

    public RegisterServiceRequest(ServiceId serviceId, String label, String description, List<ProductDescription> products, List<Option> options, List<Dependency> dependencies) {
        this.serviceId = serviceId;
        this.label = label;
        this.description = description;
        this.products = products;
        this.options = options;
        this.dependencies = dependencies;
    }

    public ServiceId getServiceId() {
        return serviceId;
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
