package monto.service.discovery;

import java.util.List;

public class DiscoveryRequest {
  private List<ServiceDiscover> discoverServices;

  public DiscoveryRequest(List<ServiceDiscover> discoverServices) {
    this.discoverServices = discoverServices;
  }

  public List<ServiceDiscover> getDiscoverServices() {
    return discoverServices;
  }
}
