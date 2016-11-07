package monto.service.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import java.lang.reflect.Type;
import monto.service.types.Command;

class CommandDeserializer implements JsonDeserializer<Command> {
  @Override
  public Command deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    if (!json.isJsonPrimitive() || !((JsonPrimitive) json).isString()) {
      throw new JsonParseException("JsonElement of a Command object must be a String primitive");
    }
    return new Command(json.getAsString());
  }
}
