package monto.service.gson;

import com.google.gson.JsonElement;

import monto.service.configuration.Configuration;
import monto.service.discovery.DiscoveryRequest;
import monto.service.source.SourceMessage;

public class MessageFromIde {
  private String tag;
  private JsonElement contents;

  private MessageFromIde(String tag, JsonElement contents) {
    this.tag = tag;
    this.contents = contents;
  }

  public static MessageFromIde source(SourceMessage msg) {
    return new MessageFromIde("source", GsonMonto.toJsonTree(msg));
  }

  public static MessageFromIde config(Configuration config) {
    return new MessageFromIde("configuration", GsonMonto.toJsonTree(config));
  }

  public static MessageFromIde discover(DiscoveryRequest request) {
    return new MessageFromIde("discovery", GsonMonto.toJsonTree(request));
  }

  public String getTag() {
    return tag;
  }

  public Object getContents() {
    return contents;
  }
}
