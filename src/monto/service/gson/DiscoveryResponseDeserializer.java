package monto.service.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import monto.service.discovery.DiscoveryResponse;
import monto.service.discovery.ServiceDescription;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

class DiscoveryResponseDeserializer implements JsonDeserializer<DiscoveryResponse> {
    @Override
    public DiscoveryResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!json.isJsonArray()) {
            throw new JsonParseException("JsonElement of a DiscoveryResponse object must be a Json array");
        }
        List<ServiceDescription> serviceDescriptions = Arrays.asList(context.deserialize(json, ServiceDescription[].class));
        return new DiscoveryResponse(serviceDescriptions);
    }
}
