package monto.service.gson;

import com.google.gson.*;
import monto.service.types.Language;

import java.lang.reflect.Type;

class LanguageDeserializer implements JsonDeserializer<Language> {
  @Override
  public Language deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    if (!json.isJsonPrimitive() || !((JsonPrimitive) json).isString()) {
      throw new JsonParseException("JsonElement of a Language object must be a String primitive");
    }
    return new Language(json.getAsString());
  }
}
