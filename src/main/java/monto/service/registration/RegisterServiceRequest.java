package monto.service.registration;

import java.util.List;
import monto.service.configuration.Option;
import monto.service.types.ServiceId;

public class RegisterServiceRequest {

  private final ServiceId serviceId;
  private final String label;
  private final String description;
  private final List<Option> options;
  private final List<ProductDescription> products;
  private final List<Dependency> dependencies;
  private final List<CommandDescription> commands;

  public RegisterServiceRequest(
      ServiceId serviceId,
      String label,
      String description,
      List<ProductDescription> products,
      List<Option> options,
      List<Dependency> dependencies,
      List<CommandDescription> commands) {
    this.serviceId = serviceId;
    this.label = label;
    this.description = description;
    this.products = products;
    this.options = options;
    this.dependencies = dependencies;
    this.commands = commands;
  }

  public ServiceId getServiceId() {
    return serviceId;
  }

  public String getLabel() {
    return label;
  }

  public String getDescription() {
    return description;
  }

  public List<ProductDescription> getProducts() {
    return products;
  }

  public List<Option> getOptions() {
    return options;
  }

  public List<Dependency> getDependencies() {
    return dependencies;
  }

  public List<CommandDescription> getCommands() {
    return commands;
  }
}
