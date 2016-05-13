package monto.service.gson;

import com.google.gson.*;
import monto.service.types.Source;

import java.lang.reflect.Type;

class SourceDeserializer implements JsonDeserializer<Source> {
    @Override
    public Source deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!json.isJsonPrimitive() || !((JsonPrimitive) json).isString()) {
            throw new JsonParseException("JsonElement of a Source object must be a String primitive");
        }
        return new Source(json.getAsString());
    }
}
