package monto.service.gson;

import com.google.gson.*;
import monto.service.types.ServiceId;

import java.lang.reflect.Type;

class ServiceIdDeserializer implements JsonDeserializer<ServiceId> {
    @Override
    public ServiceId deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!json.isJsonPrimitive() || !((JsonPrimitive) json).isString()) {
            throw new JsonParseException("JsonElement of a ServiceId object must be a String primitive");
        }
        return new ServiceId(json.getAsString());
    }
}
