package monto.service.ast;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import monto.service.message.ParseException;
import monto.service.message.ProductMessage;
import monto.service.region.Region;
import monto.service.region.Regions;


/**
 * The JSON AST format is inspired by this mailing list entry:
 * {@link https://mail.mozilla.org/pipermail/es-discuss/2009-December/010228.html}
 * <p>
 * The format uses nested arrays to represent nodes and branches in the ast.
 * The AST with non-terminals A and B and terminals s, t and u
 * <p>
 * <code>
 * A
 * / \
 * s   B
 * / \
 * t   u
 * </code>
 * <p>
 * becomes the following JSON:
 * <p>
 * <code>
 * ["A",
 * {offset:..., length:...},    // terminal s
 * ["B",
 * {offset:..., length:...}, // terminal t
 * {offset:..., length:...}  // terminal u
 * ]
 * ]
 * </code>
 */
public class ASTs {

    @SuppressWarnings("unchecked")
	public static JSONArray encode(AST ast) {
        Encoder encoder = new Encoder();
        ast.accept(encoder);
        if (encoder.getEncoding() instanceof JSONObject) {
            JSONArray a = new JSONArray();
            a.add(encoder.getEncoding());
            return a;
        } else if (encoder.getEncoding() instanceof JSONArray) {
            return (JSONArray) encoder.getEncoding();
        } else {
            return null;
        }
    }

    private static class Encoder implements ASTVisitor {

        private Object encoding;

        public Object getEncoding() {
            return encoding;
        }

        @SuppressWarnings("unchecked")
        @Override
        public void visit(NonTerminal node) {
            JSONArray jsonNode = new JSONArray();
            jsonNode.add(node.getName());
            for (AST child : node.getChildren()) {
                child.accept(this);
                jsonNode.add(encoding);
            }
            encoding = jsonNode;
        }

        @Override
        public void visit(Terminal token) {
            encoding = Regions.encode(token);
        }
    }

    public static AST decode(ProductMessage message) throws ParseException {
        try {
            return decode(message.getContents());
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }

    private static AST decode(Object json) throws ParseException {
        if (json instanceof JSONObject) {
            return decode((JSONObject) json);
        } else if (json instanceof JSONArray) {
            return decode((JSONArray) json);
        } else {
            return null;
        }
    }

    private static AST decode(JSONObject encoding) throws ParseException {
        Region region = Regions.decode(encoding);
        return new Terminal(region.getStartOffset(), region.getLength());
    }

    private static AST decode(JSONArray jsonArray) throws ParseException {
        String name = (String) jsonArray.remove(0);
        List<AST> childs = new ArrayList<>(jsonArray.size());

        for (Object object : jsonArray)
            childs.add(decode(object));

        return new NonTerminal(name, childs);
    }
}
