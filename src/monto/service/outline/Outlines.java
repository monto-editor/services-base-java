package monto.service.outline;

import monto.service.message.ParseException;
import monto.service.message.ProductMessage;
import monto.service.region.Region;
import monto.service.region.Regions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Outlines {

    @SuppressWarnings("unchecked")
    public static JSONArray encode(Outline outline) {
        JSONArray a = new JSONArray();
        a.add(encodeSingle(outline));
        return a;
    }

    @SuppressWarnings("unchecked")
    public static JSONObject encodeSingle(Outline outline) {
        JSONObject encoding = new JSONObject();

        encoding.put("description", outline.getDescription());
        encoding.put("identifier", Regions.encode(outline.getIdentifier()));

        if (!outline.isLeaf()) {
            JSONArray children = new JSONArray();
            outline.getChildren().forEach(child -> children.add(encodeSingle(child)));
            encoding.put("children", children);
        }

        if (outline.getIcon().isPresent()) {
            encoding.put("icon", outline.getIcon().get());
        }

        return encoding;
    }

    public static Outline decode(ProductMessage message) throws ParseException {
        return decode(message.getContents());
    }

    public static Outline decode(JSONArray array) throws ParseException {
        try {
            return decode((JSONObject) array.get(0));
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }

    public static Outline decode(JSONObject encoding) throws ParseException {
        try {
            String description = (String) encoding.get("description");
            Region identifier = Regions.decode((JSONObject) encoding.get("identifier"));

            String icon = null;
            if (encoding.containsKey("icon")) {
                icon = (String) encoding.get("icon");
            }

            List<Outline> children = new ArrayList<>();
            if (encoding.containsKey("children")) {
                for (Object child : (JSONArray) encoding.get("children"))
                    children.add(decode((JSONObject) child));
            }

            return new Outline(description, identifier, icon, children);

        } catch (Exception e) {
            throw new ParseException(e);
        }
    }
}
