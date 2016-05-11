package monto.service;

import org.zeromq.ZContext;

import java.net.MalformedURLException;
import java.net.URL;

public class ZMQConfiguration {
    private ZContext context;
    private String serviceAddress;
    private String registrationAddress;
    private String configurationAddress;
    private String dyndepAddress;
    private int resourcePort;

    public ZMQConfiguration(ZContext context, String serviceAddress, String registrationAddress, String configurationAddress, String dyndepAddress, int resourcePort) {
        this.context = context;
        this.serviceAddress = serviceAddress;
        this.registrationAddress = registrationAddress;
        this.configurationAddress = configurationAddress;
        this.dyndepAddress = dyndepAddress;
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

    public String getConfigurationAddress() {
        return configurationAddress;
    }

    public String getDyndepAddress() {
        return dyndepAddress;
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
                        + "  Configuration Address: %s"
                        + "  Dynamic Dependencies Address: %s"
                        + "  Resource Port: %d",
                getServiceAddress(),
                getRegistrationAddress(),
                getConfigurationAddress(),
                getDyndepAddress(),
                getResourcePort());
    }
}
