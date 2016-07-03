package monto.service.discovery;

import java.util.List;

public class DiscoveryResponse {
  /*
   * In reality a DiscoveryResponse is a JsonArray of ServiceDescriptions. So there is no JsonObject
   * with one serviceDescriptions property, but Gson seems to realize that on deserialization.
   */
  private List<ServiceDescription> serviceDescriptions;

  public DiscoveryResponse(List<ServiceDescription> serviceDescriptions) {
    this.serviceDescriptions = serviceDescriptions;
  }

  public List<ServiceDescription> get() {
    return serviceDescriptions;
  }

  @Override
  public String toString() {
    return serviceDescriptions.toString();
  }
}
