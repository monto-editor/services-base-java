package monto.service.token;

import monto.service.product.ProductMessage;
import monto.service.region.Region;
import monto.service.region.Regions;
import monto.service.types.ParseException;
import monto.service.types.PartialFunction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Tokens {

    @SuppressWarnings("unchecked")
    public static JSONObject encodeColor(Color color) {
        JSONObject encoding = new JSONObject();
        encoding.put("red", color.getRed());
        encoding.put("green", color.getGreen());
        encoding.put("blue", color.getBlue());
        return encoding;
    }

    @SuppressWarnings("unchecked")
    public static JSONObject encodeFont(Font font) {
        JSONObject encoding = new JSONObject();
        font.getColor().ifPresent(color -> encoding.put("color", encodeColor(color)));
        font.getBgcolor().ifPresent(color -> encoding.put("bgcolor", encodeColor(color)));
        font.getFamily().ifPresent(family -> encoding.put("family", family));
        font.getSize().ifPresent(size -> encoding.put("size", size));
        font.getStyle().ifPresent(style -> encoding.put("style", style));
        font.getVariant().ifPresent(variant -> encoding.put("variant", variant));
        font.getWeight().ifPresent(weight -> encoding.put("weight", weight));
        return encoding;
    }

    @SuppressWarnings("unchecked")
    public static JSONObject encodeToken(Token token) {
        JSONObject encoding = new JSONObject();
        encoding.putAll(Regions.encode(token));
        encoding.put("font", encodeFont(token.getFont()));
        encoding.put("category", token.getCategory().toString());
        return encoding;
    }

    @SuppressWarnings("unchecked")
    public static JSONArray encodeTokens(List<Token> tokens) {
        final JSONArray tokenArray = new JSONArray();
        for (Token token : tokens)
            tokenArray.add(encodeToken(token));
        return tokenArray;
    }


    public static Color decodeColor(JSONObject encoding) throws ParseException {
        try {
            int red = intValue(encoding.get("red"));
            int green = intValue(encoding.get("green"));
            int blue = intValue(encoding.get("blue"));
            return new Color(red, green, blue);
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }

    public static Font decodeFont(JSONObject encoding) throws ParseException {
        try {

            Optional<Color> color = encoding.containsKey("color") ? Optional.of(decodeColor((JSONObject) encoding.get("color"))) : Optional.empty();
            Optional<Color> bgcolor = encoding.containsKey("bgcolor") ? Optional.of(decodeColor((JSONObject) encoding.get("bgcolor"))) : Optional.empty();
            Optional<Integer> size = encoding.containsKey("size") ? Optional.of(intValue(encoding.get("size"))) : Optional.empty();
            Optional<String> family = encoding.containsKey("family") ? Optional.of((String) encoding.get("family")) : Optional.empty();
            Optional<String> style = encoding.containsKey("style") ? Optional.of((String) encoding.get("style")) : Optional.empty();
            Optional<String> weight = encoding.containsKey("weight") ? Optional.of((String) encoding.get("weight")) : Optional.empty();
            Optional<String> variant = encoding.containsKey("variant") ? Optional.of((String) encoding.get("variant")) : Optional.empty();
            return new Font(color, bgcolor, family, style, variant, weight, size);
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }

    public static Token decodeToken(JSONObject encoding) throws ParseException {
        try {
            Region region = Regions.decode(encoding);
            String category = (String) encoding.get("category");
            Font font = decodeFont((JSONObject) encoding.get("font"));
            return new Token(region.getStartOffset(), region.getLength(), Enum.valueOf(TokenCategory.class, category), font);
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }

    public static List<Token> decodeTokens(JSONArray tokenArray) throws ParseException {
        try {
            List<Token> tokens = new ArrayList<>();
            for (Object token : tokenArray)
                tokens.add(decodeToken((JSONObject) token));
            return tokens;
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }

    public static List<Token> decodeTokenMessage(ProductMessage message) throws ParseException {
        try {
            return decodeTokens((JSONArray) message.getContents());
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <A, B> Optional<B> ifPresent(JSONObject obj, String key, PartialFunction<A, B, ParseException> f) throws ParseException {
        return obj.containsKey(obj) ? Optional.of(f.apply((A) obj.get(key))) : Optional.empty();
    }


    public static String decodeString(Object object) throws ParseException {
        try {
            return (String) object;
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }

    private static int intValue(Object obj) {
        return obj instanceof Long ? ((Long) obj).intValue() : ((Integer) obj).intValue();
    }

}
