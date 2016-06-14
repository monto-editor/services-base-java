package monto.service.gson;

import com.google.gson.*;
import monto.service.types.LongKey;

import java.lang.reflect.Type;

class LongKeyDeserializer implements JsonDeserializer<LongKey> {
  @Override
  public LongKey deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    if (!json.isJsonPrimitive() || !((JsonPrimitive) json).isNumber()) {
      throw new JsonParseException("JsonElement of a LongKey object must be a Number primitive");
    }
    return new LongKey(json.getAsLong());
  }
}
