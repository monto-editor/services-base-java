package monto.service.identifier;

import monto.service.product.ProductMessage;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.stream.Collectors;

public final class Identifiers {
    @SuppressWarnings("unchecked")
    public static JSONObject encode(Identifier identifier) {
        JSONObject json = new JSONObject();
        json.put("identifier", identifier.getIdentifier());
        json.put("type", identifier.getType());
        return json;
    }

    public static List<Identifier> decode(ProductMessage productMessage) {
        List<JSONObject> jsonObjects = (List<JSONObject>) productMessage.getContents();
        return jsonObjects.stream().map(Identifiers::decode).collect(Collectors.toList());
    }

    public static Identifier decode(JSONObject json) {
        return new Identifier(
                (String) json.get("identifier"),
                Identifier.IdentifierType.valueOf(Identifier.IdentifierType.class, (String) json.get("type"))
        );
    }

    private Identifiers() {
    }
}
