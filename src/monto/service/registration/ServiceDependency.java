package monto.service.registration;

import java.util.function.Function;

import monto.service.types.ServiceID;

public class ServiceDependency implements Dependency {
	private ServiceID serviceID;

	public ServiceDependency(ServiceID serviceID) {
		this.serviceID = serviceID;
	}

	@Override
	public <A> A match(Function<ServiceDependency, A> serviceDependencyFun, Function<SourceDependency, A> sourceDependencyFun) {
		return serviceDependencyFun.apply(this);
	}

	public ServiceID getServiceID() {
		return serviceID;
	}
}
