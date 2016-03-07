package monto.service.dependency;

import org.json.simple.JSONObject;

public class Edges {

    public static JSONObject encode(Edge edge) {
        JSONObject obj = new JSONObject();
        obj.put("product", edge.getProduct().toString());
        obj.put("language", edge.getLanguage().toString());
        return obj;
    }
}
