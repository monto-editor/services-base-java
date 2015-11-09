package monto.service.configuration;

import java.util.List;

@SuppressWarnings("rawtypes")
public class ServiceConfiguration {

	private String serviceId;
	private List<Configuration> configurations;

	public ServiceConfiguration(String serviceId, List<Configuration> configurations) {
		this.serviceId = serviceId;
		this.configurations = configurations;
	}

	public String getServiceID() {
		return serviceId;
	}

	public List<Configuration> getConfigurations() {
		return configurations;
	}
}
