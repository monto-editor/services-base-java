package monto.service;

import com.google.gson.JsonElement;

import monto.service.dependency.RegisterDynamicDependencies;
import monto.service.gson.GsonMonto;
import monto.service.product.ProductMessage;

public class ServiceSend {
  private String tag;
  private JsonElement contents;

  private ServiceSend(String tag, JsonElement contents) {
    super();
    this.tag = tag;
    this.contents = contents;
  }

  public static ServiceSend product(ProductMessage msg) {
    return new ServiceSend("product", GsonMonto.toJsonTree(msg));
  }

  public static ServiceSend dyndep(RegisterDynamicDependencies msg) {
    return new ServiceSend("dependency", GsonMonto.toJsonTree(msg));
  }

  public String getTag() {
    return tag;
  }

  public Object getContents() {
    return contents;
  }
}
