package monto.service.types;

public class ServiceId implements Comparable<ServiceId> {

  private String serviceId;

  public ServiceId(String serviceId) {
    this.serviceId = serviceId;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj != null && obj.hashCode() == this.hashCode() && obj instanceof ServiceId) {
      ServiceId other = (ServiceId) obj;
      return this.serviceId.equals(other.serviceId);
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return serviceId.hashCode();
  }

  @Override
  public int compareTo(ServiceId other) {
    return this.serviceId.compareTo(other.serviceId);
  }

  @Override
  public String toString() {
    return serviceId;
  }
}
