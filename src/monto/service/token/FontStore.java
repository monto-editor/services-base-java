package monto.service.token;

import java.util.HashMap;
import java.util.Map;

public class FontStore {
	public Map<Color,Font> store = new HashMap<>();
	
	public Font getFont(Color color) {
		return store.computeIfAbsent(color, clr -> new Font(clr));
	}
}
