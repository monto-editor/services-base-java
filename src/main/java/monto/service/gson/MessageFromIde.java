package monto.service.gson;

import com.google.gson.JsonElement;

import java.util.List;
import monto.service.command.CommandMessage;
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

  public static MessageFromIde config(List<Configuration> configs) {
    return new MessageFromIde("configurations", GsonMonto.toJsonTree(configs));
  }

  public static MessageFromIde discover(DiscoveryRequest request) {
    return new MessageFromIde("discovery", GsonMonto.toJsonTree(request));
  }

  public static MessageFromIde command(CommandMessage commandMessage) {
    return new MessageFromIde("commandMessage", GsonMonto.toJsonTree(commandMessage));
  }

  public String getTag() {
    return tag;
  }

  public Object getContents() {
    return contents;
  }
}
