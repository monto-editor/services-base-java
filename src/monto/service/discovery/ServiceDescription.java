package monto.service.discovery;

import java.util.List;

import monto.service.configuration.Option;
import monto.service.message.ServiceID;

@SuppressWarnings("rawtypes")
public class ServiceDescription {
	private ServiceID serviceID;
	private String language;
	private String product;
	private List<Option> options;
	private String description;
	private String label;
	
	public ServiceDescription(ServiceID serviceID, String language, String product, List<Option> options2, String description, String label) {
		this.serviceID = serviceID;
		this.language = language;
		this.product = product;
		this.options = options2;
		this.description = description;
		this.label = label;
	}

	public ServiceID getServiceID() {
		return serviceID;
	}

	public String getLanguage() {
		return language;
	}

	public String getProduct() {
		return product;
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
