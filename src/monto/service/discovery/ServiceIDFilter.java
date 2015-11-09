package monto.service.discovery;

import java.util.function.Function;

public class ServiceIDFilter implements Filter {

	private String serviceID;

	public ServiceIDFilter(String serviceID) {
		this.serviceID = serviceID;
	}

	@Override
	public <A> A match(Function<ServiceIDFilter, A> f, Function<ProductFilter, A> g, Function<LanguageFilter, A> h) {
		return f.apply(this);
	}

	public String getServiceID() {
		return serviceID;
	}
}
