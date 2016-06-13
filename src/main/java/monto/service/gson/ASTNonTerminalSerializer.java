package monto.service.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import monto.service.ast.NonTerminal;

import java.lang.reflect.Type;

class ASTNonTerminalSerializer implements JsonSerializer<NonTerminal> {
    @Override
    public JsonElement serialize(NonTerminal src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        json.addProperty("name", src.getName());
        json.add("children", context.serialize(src.getChildren()));
        return json;
    }
}
