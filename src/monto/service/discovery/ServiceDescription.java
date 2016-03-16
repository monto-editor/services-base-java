package monto.service.discovery;

import java.util.List;

import monto.service.configuration.Option;
import monto.service.types.ServiceID;

@SuppressWarnings("rawtypes")
public class ServiceDescription {
	private ServiceID serviceID;
	private List<Option> options;
	private String description;
	private String label;
	
	public ServiceDescription(ServiceID serviceID, List<Option> options, String description, String label) {
		this.serviceID = serviceID;
		this.options = options;
		this.description = description;
		this.label = label;
	}

	public ServiceID getServiceID() {
		return serviceID;
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
