package monto.service.discovery;

import java.util.ArrayList;
import java.util.List;

public class DiscoveryRequest {
  private List<ServiceDiscover> discoverServices;

  public static DiscoveryRequest create() {
    return new DiscoveryRequest(new ArrayList<>());
  }

  public DiscoveryRequest(List<ServiceDiscover> discoverServices) {
    this.discoverServices = discoverServices;
  }

  public List<ServiceDiscover> getDiscoverServices() {
    return discoverServices;
  }
}
