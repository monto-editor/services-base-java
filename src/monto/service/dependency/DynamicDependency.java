package monto.service.dependency;

import monto.service.types.Language;
import monto.service.types.Product;
import monto.service.types.ServiceID;
import monto.service.types.Source;

public class DynamicDependency {

    private Source source;
    private ServiceID serviceID;
    private Product product;
    private Language language;

    public DynamicDependency(Source source, ServiceID serviceID, Product product, Language language) {
        this.source = source;
        this.serviceID = serviceID;
        this.product = product;
        this.language = language;
    }

    public Source getSource() {
        return source;
    }

    public ServiceID getServiceID() {
        return serviceID;
    }

    public Product getProduct() {
        return product;
    }

    public Language getLanguage() {
        return language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DynamicDependency that = (DynamicDependency) o;

        if (source != null ? !source.equals(that.source) : that.source != null) return false;
        if (serviceID != null ? !serviceID.equals(that.serviceID) : that.serviceID != null) return false;
        if (product != null ? !product.equals(that.product) : that.product != null) return false;
        return language != null ? language.equals(that.language) : that.language == null;

    }

    @Override
    public int hashCode() {
        int result = source != null ? source.hashCode() : 0;
        result = 31 * result + (serviceID != null ? serviceID.hashCode() : 0);
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        return result;
    }
}