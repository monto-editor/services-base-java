package monto.service.types;

public class ServiceID implements Comparable<ServiceID> {

    private String serviceID;

    public ServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.hashCode() == this.hashCode() && obj instanceof ServiceID) {
            ServiceID other = (ServiceID) obj;
            return this.serviceID.equals(other.serviceID);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return serviceID.hashCode();
    }

    @Override
    public int compareTo(ServiceID other) {
        return this.serviceID.compareTo(other.serviceID);
    }

    @Override
    public String toString() {
        return serviceID;
    }
}
