package monto.service.gson;

import com.google.gson.*;
import monto.service.product.ProductMessage;
import monto.service.source.SourceMessage;
import monto.service.types.Message;

import java.lang.reflect.Type;

class MessageDeserializer implements JsonDeserializer<Message> {
  @Override
  public Message deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    if (!json.isJsonObject()) {
      throw new JsonParseException(
          "To deserialize a Message object, the Json element must be an JsonObject");
    }
    JsonObject jsonObject = (JsonObject) json;
    if (jsonObject.has("product")) {
      return context.deserialize(jsonObject, ProductMessage.class);
    } else {
      return context.deserialize(json, SourceMessage.class);
    }
  }
}
