package monto.service.gson;

import com.google.gson.JsonElement;

import monto.service.dependency.RegisterCommandMessageDependencies;
import monto.service.dependency.RegisterDynamicDependencies;
import monto.service.product.ProductMessage;

public class MessageFromService {
  private String tag;
  private JsonElement contents;

  private MessageFromService(String tag, JsonElement contents) {
    this.tag = tag;
    this.contents = contents;
  }

  public static MessageFromService product(ProductMessage msg) {
    return new MessageFromService("product", GsonMonto.toJsonTree(msg));
  }

  public static MessageFromService dynamicDependencies(RegisterDynamicDependencies msg) {
    return new MessageFromService("dyndep", GsonMonto.toJsonTree(msg));
  }

  public static MessageFromService commandMessageDependencies(
      RegisterCommandMessageDependencies msg) {
    return new MessageFromService("cmddep", GsonMonto.toJsonTree(msg));
  }

  public String getTag() {
    return tag;
  }

  public Object getContents() {
    return contents;
  }
}
