package monto.service.request;

import monto.service.gson.GsonMonto;
import monto.service.product.ProductMessages;
import monto.service.source.SourceMessage;
import monto.service.types.Message;
import monto.service.types.ParseException;
import monto.service.types.Source;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Requests {

    public static Request decode(JSONObject encoding) throws ParseException {
        try {
            final Source source = new Source((String) encoding.get("source"));
            final List<Message> requirements = decodeRequirements((JSONArray) encoding.get("requirements"));
            return new Request(source, requirements);
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }

    private static List<Message> decodeRequirements(JSONArray messages) throws ParseException {
        List<Message> decoded = new ArrayList<>(messages.size());
        for (Object message : messages) {
            JSONObject requirement = (JSONObject) message;
            if (requirement.containsKey("product"))
                decoded.add(ProductMessages.decode(requirement));
            else
                decoded.add(GsonMonto.fromJson(requirement.toJSONString(), SourceMessage.class));
        }
        return decoded;
    }
}
