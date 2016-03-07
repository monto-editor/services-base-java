package monto.service.dependency;

import monto.service.types.ServiceID;
import monto.service.types.Source;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.Set;

public class RegisterDynamicDependencies {

    private Source source;
    private ServiceID serviceID;
    private Set<DynamicDependency> dependencies;

    public RegisterDynamicDependencies(Source source, ServiceID serviceID, Set<DynamicDependency> dependencies) {
        this.source = source;
        this.serviceID = serviceID;
        this.dependencies = dependencies;
    }

    public Source getSource() {
        return source;
    }

    public ServiceID getServiceID() {
        return serviceID;
    }

    public Set<DynamicDependency> getDependencies() {
        return dependencies;
    }

    public static JSONObject encode(RegisterDynamicDependencies dyndeps) {
        JSONObject obj = new JSONObject();
        obj.put("service_id", dyndeps.getServiceID().toString());
        obj.put("source", dyndeps.getSource().toString());
        JSONArray arr = new JSONArray();
        for(DynamicDependency d : dyndeps.getDependencies())
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
        if (serviceID != null ? !serviceID.equals(that.serviceID) : that.serviceID != null) return false;
        return dependencies != null ? dependencies.equals(that.dependencies) : that.dependencies == null;

    }

    @Override
    public int hashCode() {
        int result = source != null ? source.hashCode() : 0;
        result = 31 * result + (serviceID != null ? serviceID.hashCode() : 0);
        result = 31 * result + (dependencies != null ? dependencies.hashCode() : 0);
        return result;
    }
}