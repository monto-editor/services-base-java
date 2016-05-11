package monto.service.dependency;

import monto.service.types.ServiceId;
import monto.service.types.Source;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Set;

public class RegisterDynamicDependencies {

    private Source source;
    private ServiceId serviceId;
    private Set<DynamicDependency> dependencies;

    public RegisterDynamicDependencies(Source source, ServiceId serviceId, Set<DynamicDependency> dependencies) {
        this.source = source;
        this.serviceId = serviceId;
        this.dependencies = dependencies;
    }

    public Source getSource() {
        return source;
    }

    public ServiceId getServiceId() {
        return serviceId;
    }

    public Set<DynamicDependency> getDependencies() {
        return dependencies;
    }

    public static JSONObject encode(RegisterDynamicDependencies dyndeps) {
        JSONObject obj = new JSONObject();
        obj.put("service_id", dyndeps.getServiceId().toString());
        obj.put("source", dyndeps.getSource().toString());
        JSONArray arr = new JSONArray();
        for (DynamicDependency d : dyndeps.getDependencies())
            arr.add(DynamicDependencies.encode(d));
        obj.put("dependencies", arr);
        return obj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegisterDynamicDependencies that = (RegisterDynamicDependencies) o;

        if (source != null ? !source.equals(that.source) : that.source != null) return false;
        if (serviceId != null ? !serviceId.equals(that.serviceId) : that.serviceId != null) return false;
        return dependencies != null ? dependencies.equals(that.dependencies) : that.dependencies == null;

    }

    @Override
    public int hashCode() {
        int result = source != null ? source.hashCode() : 0;
        result = 31 * result + (serviceId != null ? serviceId.hashCode() : 0);
        result = 31 * result + (dependencies != null ? dependencies.hashCode() : 0);
        return result;
    }
}