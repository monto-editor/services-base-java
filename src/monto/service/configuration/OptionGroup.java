package monto.service.configuration;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public class OptionGroup implements Configuration {

    private String requiredOption;
    private Configuration[] members;

    public OptionGroup(String requiredOption, Configuration[] members) {
        this.requiredOption = requiredOption;
        this.members = members;
    }

    public String getRequiredOption() {
        return requiredOption;
    }

    public Configuration[] getMembers() {
        return members;
    }

    @SuppressWarnings("unchecked")
    @Override
    public JSONObject encode() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("required_option", getRequiredOption());
        JSONArray members = new JSONArray();
        for (Configuration conf : getMembers()) {
            members.add(conf.encode());
        }
        jsonObject.put("members", members);
        return jsonObject;
    }
}
