package monto.service.token;

import monto.service.region.Region;

public class Token extends Region {
	
	private Font font;
	private TokenCategory category;

    public Token(int offset, int length, TokenCategory category, Font font) {
        super(offset, length);
        this.font = font;
        this.category = category;
    }

    public Font getFont() {
    	return font;
    }

    public TokenCategory getCategory() {
		return category;
	}

	@Override
    public String toString() {
        return String.format("(%d,%d,%s)", getStartOffset(), getLength(), font);
    }
}
