package monto.service;

import org.zeromq.ZContext;

public class ZMQConfiguration {
	private ZContext context;
	private String serviceAddress;
	private String registrationAddress;
	private String configurationAdress;

	public ZMQConfiguration(ZContext context, String serviceAddress, String registrationAddress, String configurationAdress) {
		this.context = context;
		this.serviceAddress = serviceAddress;
		this.registrationAddress = registrationAddress;
		this.configurationAdress = configurationAdress;
	}

	public ZContext getContext() {
		return context;
	}

	public String getServiceAddress() {
		return serviceAddress;
	}

	public String getRegistrationAddress() {
		return registrationAddress;
	}

	public String getConfigurationAddress() {
		return configurationAdress;
	}
	
	@Override
	public String toString() {
		return String.format(
				"ZMQ Configuration:\n"
				+ "  Service Address: %s\n"
				+ "  Registration Address: %s\n"
				+ "  Configuration Address: %s",
				getServiceAddress(),
				getRegistrationAddress(),
				getConfigurationAddress());
	}
}
