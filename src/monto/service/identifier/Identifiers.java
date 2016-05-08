package monto.service.identifier;

import monto.service.product.ProductMessage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.stream.Collectors;

public final class Identifiers {
    @SuppressWarnings("unchecked")
    public static JSONObject encode(Identifier identifier) {
        JSONObject json = new JSONObject();
        json.put("identifier", identifier.getIdentifier());
        json.put("type", identifier.getType().name());
        return json;
    }

    @SuppressWarnings("unchecked")
    public static JSONArray encode(List<Identifier> identifiers) {
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(identifiers.parallelStream().map(Identifiers::encode).collect(Collectors.toList()));
        return jsonArray;
    }

    @SuppressWarnings("unchecked")
    public static List<Identifier> decode(ProductMessage productMessage) {
        JSONArray jsonIdentifiers = (JSONArray) productMessage.getContents();
        return (List<Identifier>) jsonIdentifiers.stream().map(obj -> decode((JSONObject) obj)).collect(Collectors.toList());
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
