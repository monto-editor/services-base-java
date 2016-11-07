package monto.service;

import java.net.MalformedURLException;
import java.net.URL;
import org.zeromq.ZContext;

public class ZMQConfiguration {
  private ZContext context;
  private String serviceAddress;
  private String registrationAddress;
  private int resourcePort;

  public ZMQConfiguration(
      ZContext context, String serviceAddress, String registrationAddress, int resourcePort) {
    this.context = context;
    this.serviceAddress = serviceAddress;
    this.registrationAddress = registrationAddress;
    this.resourcePort = resourcePort;
  }

  public ZContext getContext() {
    return context;
  }

  public String getServiceAddress() {
    return serviceAddress;
  }

  public String getRegistrationAddress() {
    return registrationAddress;
  }

  public int getResourcePort() {
    return resourcePort;
  }

  public URL getResourceURL(String name) {
    try {
      return new URL(String.format("http://localhost:%d/%s", resourcePort, name));
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String toString() {
    return String.format(
        "ZMQ Configuration:\n"
            + "  Service Address: %s\n"
            + "  Registration Address: %s\n"
            + "  Resource Port: %d",
        getServiceAddress(), getRegistrationAddress(), getResourcePort());
  }
}
