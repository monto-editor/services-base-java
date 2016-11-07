package monto.service.gson;

import com.google.gson.*;
import java.lang.reflect.Type;
import monto.service.types.Product;

class ProductDeserializer implements JsonDeserializer<Product> {
  @Override
  public Product deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    if (!json.isJsonPrimitive() || !((JsonPrimitive) json).isString()) {
      throw new JsonParseException("JsonElement of a Product object must be a String primitive");
    }
    return new Product(json.getAsString());
  }
}
