package monto.service.configuration;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import monto.service.message.ParseException;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class Options {
	
    public static <T> JSONObject encode(Option<T> option) {
        JSONObject jsonObject = new JSONObject();
        option.<Void>match(simpleOption -> {
	        	jsonObject.put("option_id", simpleOption.getOptionId());
	        	jsonObject.put("label", simpleOption.getLabel());
	        	jsonObject.put("type", simpleOption.getType());
	        	jsonObject.put("default_value", simpleOption.getDefaultValue());
	        	return null;
	        },
        	ignore -> { return null; }
        	);
        option.<Void>match(
        		booleanOption -> { return null; },
        		numberOption -> {
        	        jsonObject.put("from", numberOption.getFrom());
        	        jsonObject.put("to", numberOption.getTo());
        			return null;
        		},
        		textOption -> {
        	        jsonObject.put("regular_expression", textOption.getRegularExpression());
        			return null;
        		},
        		xorOption -> {
        	        JSONArray values = new JSONArray();
        	        for (String value : xorOption.getValues()) {
        	            values.add(value);
        	        }
        	        jsonObject.put("values", values);
        			return null;
        		},
        		optionGroup -> {
        	        jsonObject.put("required_option", optionGroup.getRequiredOption());
        	        JSONArray members = new JSONArray();
        	        for (Option conf : optionGroup.getMembers()) {
        	            members.add(encode(conf));
        	        }
        	        jsonObject.put("members", members);
        			return null;
        		});
        return jsonObject;
    }
    
    public static Option decode(JSONObject encoding) throws ParseException {
    	try {
    		if(encoding.containsKey("option_id"))
    			return decodeSimpleOption(encoding);
    		else
    			return decodeOptionGroup(encoding);
    	} catch(Exception e) {
    		throw new ParseException(encoding.toString(),e);
    	}
    }
    
    private static Option decodeSimpleOption(JSONObject encoding) throws ParseException {
    	String optionId = (String) encoding.get("option_id");
    	String label = (String) encoding.get("label");
    	switch((String)encoding.get("type")) {
    	case "boolean":
    		return new BooleanOption(optionId,label,(Boolean) encoding.get("default_value"));
    	case "text":
    		return new TextOption(optionId,label,
    				(String) encoding.get("default_value"),
    				(String) encoding.get("regular_expression"));
    	case "number":
    		return new NumberOption(optionId,label,
    				(Long) encoding.get("default_value"),
    				(Long) encoding.get("from"),
    				(Long) encoding.get("to"));
    	case "xor":
    		List<String> values = new ArrayList<>((JSONArray)encoding.get("values"));
    		return new XorOption(optionId,label,(String) encoding.get("default_value"),values);
    	default:
    		throw new ParseException("unrecognized option");
    	}    	
    }
    
    private static Option decodeOptionGroup(JSONObject encoding) throws ParseException {
    	String requiredOption = (String) encoding.get("required_option");
    	
    	JSONArray membersJSON = (JSONArray) encoding.get("members");
    	List<Option> members = new ArrayList<>(membersJSON.size());
    	for(Object member : membersJSON)
    		members.add(decode((JSONObject) member));
    	return new OptionGroup(requiredOption,members);
    		
    }
}
