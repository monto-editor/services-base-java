package monto.service.gson;

import com.google.gson.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;

class OptionalSerializer<T> implements JsonSerializer<Optional<T>> {
    @Override
    public JsonElement serialize(Optional<T> src, Type typeOfSrc, JsonSerializationContext context) {
        JsonElement json = null;
        if (src.isPresent()) {
            json = context.serialize(src.get());
        }
        return json;
    }
}
