package monto.service.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

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
