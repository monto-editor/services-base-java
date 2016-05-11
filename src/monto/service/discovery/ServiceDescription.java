package monto.service.discovery;

import monto.service.configuration.Option;
import monto.service.types.ServiceId;

import java.util.List;

@SuppressWarnings("rawtypes")
public class ServiceDescription {
    private ServiceId serviceId;
    private List<Option> options;
    private String description;
    private String label;

    public ServiceDescription(ServiceId serviceId, List<Option> options, String description, String label) {
        this.serviceId = serviceId;
        this.options = options;
        this.description = description;
        this.label = label;
    }

    public ServiceId getServiceId() {
        return serviceId;
    }

    public List<Option> getOptions() {
        return options;
    }

    public String getDescription() {
        return description;
    }

    public String getLabel() {
        return label;
    }
}
