package monto.service.dependency;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DynamicDependencies {

    public static JSONObject encode(DynamicDependency dyndep) {
        JSONObject obj = new JSONObject();
        obj.put("service_id", dyndep.getServiceID().toString());
        obj.put("source", dyndep.getSource().toString());
        JSONArray arr = new JSONArray();
        for(Edge e : dyndep.getEdges())
            arr.add(Edges.encode(e));
        obj.put("edges", arr);
        return obj;
    }
}
