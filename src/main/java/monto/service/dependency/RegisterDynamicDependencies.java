package monto.service.dependency;

import java.util.Set;
import monto.service.types.ServiceId;
import monto.service.types.Source;

public class RegisterDynamicDependencies {

  private Source source;
  private ServiceId serviceId;
  private Set<DynamicDependency> dependencies;

  public RegisterDynamicDependencies(
      Source source, ServiceId serviceId, Set<DynamicDependency> dependencies) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    RegisterDynamicDependencies that = (RegisterDynamicDependencies) o;

    if (source != null ? !source.equals(that.source) : that.source != null) {
      return false;
    }
    if (serviceId != null ? !serviceId.equals(that.serviceId) : that.serviceId != null) {
      return false;
    }
    return dependencies != null
        ? dependencies.equals(that.dependencies)
        : that.dependencies == null;
  }

  @Override
  public int hashCode() {
    int result = source != null ? source.hashCode() : 0;
    result = 31 * result + (serviceId != null ? serviceId.hashCode() : 0);
    result = 31 * result + (dependencies != null ? dependencies.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return String.format(
        "RegisterDynamicDependencies {source: %s, serviceId: %s, dependencies: %s}",
        source,
        serviceId,
        dependencies);
  }
}
