package monto.service.gson;

import com.google.gson.*;
import monto.service.configuration.*;

import java.lang.reflect.Type;

@SuppressWarnings("rawtypes")
class OptionDeserializer implements JsonDeserializer<Option> {
    @Override
    public Option deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!json.isJsonObject()) {
            throw new JsonParseException("JsonElement of a Option object must be a JsonObject");
        }
        JsonObject jsonObject = (JsonObject) json;
        if (jsonObject.has("type")) {
            // is a simple option
            switch (jsonObject.get("type").getAsString()) {
                case "boolean":
                    return context.deserialize(jsonObject, BooleanOption.class);
                case "number":
                    return context.deserialize(jsonObject, NumberOption.class);
                case "text":
                    return context.deserialize(jsonObject, TextOption.class);
                case "xor":
                    return context.deserialize(jsonObject, XorOption.class);
                default:
                    throw new JsonParseException(jsonObject.get("type").getAsString() + " is a invalid type for a Option");
            }
        } else if (jsonObject.has("members")) {
            // is OptionGroup
            return context.deserialize(jsonObject, OptionGroup.class);
        }
        throw new JsonParseException("Unrecognized json for a Option");
    }
}
