package monto.service.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import monto.service.types.LongKey;

import java.lang.reflect.Type;

class LongKeySerializer implements JsonSerializer<LongKey> {
    @Override
    public JsonElement serialize(LongKey src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.longValue());
    }
}
