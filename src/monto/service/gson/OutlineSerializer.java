package monto.service.gson;

import com.google.gson.*;
import monto.service.outline.Outline;
import monto.service.region.Region;

import java.lang.reflect.Type;

class OutlineSerializer implements JsonSerializer<Outline> {
    @Override
    public JsonElement serialize(Outline src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        json.addProperty("label", src.getLabel());
        json.add("link", context.serialize(src.getLink(), Region.class));

        src.getIcon().ifPresent(url -> json.addProperty("icon", url.toExternalForm()));

        if (!src.getChildren().isEmpty()) {
            JsonArray children = new JsonArray();
            src.getChildren().stream().forEach(child -> children.add(serialize(child, typeOfSrc, context)));
            json.add("children", children);
        }
        return json;
    }
}
