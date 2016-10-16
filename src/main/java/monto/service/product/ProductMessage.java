package monto.service.product;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import monto.service.source.SourceMessage;
import monto.service.types.*;

import java.util.Optional;
import java.util.function.Function;

public class ProductMessage implements Message {

  private final LongKey id;
  private final Source source;
  private final ServiceId serviceId;
  private final Product product;
  private final Language language;
  private final JsonElement contents;
  private final long time;

  public ProductMessage(
      LongKey id,
      Source source,
      ServiceId serviceId,
      Product product,
      Language language,
      JsonElement contents,
      long time) {
    this.id = id;
    this.source = source;
    this.serviceId = serviceId;
    this.product = product;
    this.language = language;
    this.contents = contents;
    this.time = time;
  }

  public LongKey getId() {
    return id;
  }

  public Source getSource() {
    return source;
  }

  public Optional<Integer> getSessionIdFromFakeSource() {
    String[] parts = source.getPhysicalName().split(":");
    if (parts.length != 3) {
      return Optional.empty();
    }
    return Optional.of(Integer.valueOf(parts[2]));
  }

  public Optional<String> getModeFromFakeSource() {
    String[] parts = source.getPhysicalName().split(":");
    if (parts.length != 3) {
      return Optional.empty();
    }
    return Optional.of(parts[1]);
  }

  public boolean matchesFakeSource(String mode, int sessionId) {
    String[] parts = source.getPhysicalName().split(":");
    if (parts.length != 3) {
      return false;
    }

    // todo replace with matching on sourcename

    return "session".equals(parts[0])
        && mode.equals(parts[1])
        && String.valueOf(sessionId).equals(parts[2]);
  }

  public ServiceId getServiceId() {
    return serviceId;
  }

  public Product getProduct() {
    return product;
  }

  public Language getLanguage() {
    return language;
  }

  public JsonElement getContents() {
    return contents;
  }

  public long getTime() {
    return time;
  }

  @Override
  public String toString() {
    return String.format(
        "{"
            + "  vid: %s,\n"
            + "  source: %s,\n"
            + "  serviceId: %s,\n"
            + "  product: %s,\n"
            + "  language: %s,\n"
            + "  contents: %s,\n"
            + "}",
        id,
        source,
        serviceId,
        product,
        language,
        contents);
  }

  @Override
  public <A> A match(Function<SourceMessage, A> f, Function<ProductMessage, A> g) {
    return g.apply(this);
  }

  public boolean isAvailable() {
    return contents.isJsonObject() && !((JsonObject) contents).has("notAvailable");
  }
}
