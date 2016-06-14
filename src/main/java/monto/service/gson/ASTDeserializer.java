package monto.service.gson;

import com.google.gson.*;
import monto.service.ast.AST;
import monto.service.ast.NonTerminal;
import monto.service.ast.Terminal;

import java.lang.reflect.Type;

class ASTDeserializer implements JsonDeserializer<AST> {
  @Override
  public AST deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    if (!json.isJsonObject()) {
      throw new JsonParseException("JsonElement of a AST object must be a JsonObject");
    }
    JsonObject jsonObject = (JsonObject) json;
    if (jsonObject.has("children")) {
      return context.deserialize(json, NonTerminal.class);
    } else {
      return context.deserialize(json, Terminal.class);
    }
  }
}
