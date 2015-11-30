package monto.service.discovery;

import java.util.function.Function;

import monto.service.message.ServiceID;

public class ServiceIDFilter implements Filter {

	private ServiceID serviceID;

	public ServiceIDFilter(ServiceID serviceID) {
		this.serviceID = serviceID;
	}

	@Override
	public <A> A match(Function<ServiceIDFilter, A> f, Function<ProductFilter, A> g, Function<LanguageFilter, A> h) {
		return f.apply(this);
	}

	public ServiceID getServiceID() {
		return serviceID;
	}
}
