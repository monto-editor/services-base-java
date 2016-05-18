package monto.service.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import monto.service.configuration.OptionGroup;

import java.lang.reflect.Type;

class OptionGroupSerializer implements JsonSerializer<OptionGroup> {
    @Override
    public JsonElement serialize(OptionGroup src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        json.addProperty("required_option", src.getRequiredOption());
        json.add("members", context.serialize(src.getMembers()));
        return json;
    }
}
