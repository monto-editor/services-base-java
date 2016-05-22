package monto.service.gson;

import com.google.gson.*;
import monto.service.configuration.*;

import java.lang.reflect.Type;

@SuppressWarnings("rawtypes")
class OptionSerializer implements JsonSerializer<Option> {
    @Override
    public JsonElement serialize(Option src, Type typeOfSrc, JsonSerializationContext context) {
        if (src instanceof OptionGroup) {
            OptionGroup optionGroup = (OptionGroup) src;
            JsonObject json = new JsonObject();
            json.addProperty("required_option", optionGroup.getRequiredOption());
            json.add("members", context.serialize(optionGroup.getMembers()));
            return json;
        } else if (src instanceof TextOption) {
            return context.serialize(src, TextOption.class);
        } else if (src instanceof XorOption) {
            return context.serialize(src, XorOption.class);
        } else if (src instanceof BooleanOption) {
            return context.serialize(src, BooleanOption.class);
        } else if (src instanceof NumberOption) {
            return context.serialize(src, NumberOption.class);
        } else {
            throw new JsonParseException("Option to serialize was not of an expected type.");
        }
    }
}
