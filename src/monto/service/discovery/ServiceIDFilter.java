package monto.service.discovery;

import monto.service.types.ServiceID;

import java.util.function.Function;

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
