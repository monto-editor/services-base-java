package monto.service.dependency;

import monto.service.types.ServiceID;
import monto.service.types.Source;

import java.util.Set;

public class DynamicDependency {

    private Source source;
    private ServiceID serviceID;
    private Set<Edge> edges;

    public DynamicDependency(Source source, ServiceID serviceID, Set<Edge> edges) {
        this.source = source;
        this.serviceID = serviceID;
        this.edges = edges;
    }

    public Source getSource() {
        return source;
    }

    public ServiceID getServiceID() {
        return serviceID;
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DynamicDependency that = (DynamicDependency) o;

        if (source != null ? !source.equals(that.source) : that.source != null) return false;
        if (serviceID != null ? !serviceID.equals(that.serviceID) : that.serviceID != null) return false;
        return edges != null ? edges.equals(that.edges) : that.edges == null;

    }

    @Override
    public int hashCode() {
        int result = source != null ? source.hashCode() : 0;
        result = 31 * result + (serviceID != null ? serviceID.hashCode() : 0);
        result = 31 * result + (edges != null ? edges.hashCode() : 0);
        return result;
    }
}