package monto.service.highlighting;

public enum TokenCategory {
    COMMENT(new Color(88, 110, 117)),
    CONSTANT(new Color(42, 161, 152)),
    STRING(TokenCategory.CONSTANT.getColor()),
    CHARACTER(TokenCategory.CONSTANT.getColor()),
    NUMBER(TokenCategory.CONSTANT.getColor()),
    BOOLEAN(TokenCategory.CONSTANT.getColor()),
    FLOAT(TokenCategory.CONSTANT.getColor()),
    IDENTIFIER(new Color(28, 139, 210)),
    STATEMENT(new Color(133, 153, 0)),
    CONDITIONAL(TokenCategory.STATEMENT.getColor()),
    REPEAT(TokenCategory.STATEMENT.getColor()),
    LABEL(TokenCategory.STATEMENT.getColor()),
    OPERATOR(TokenCategory.STATEMENT.getColor()),
    KEYWORD(TokenCategory.STATEMENT.getColor()),
    EXCEPTION(TokenCategory.STATEMENT.getColor()),
    META(new Color(203, 75, 22)),
    TYPE(new Color(181, 137, 0)),
    MODIFIER(TokenCategory.TYPE.getColor()),
    STRUCTURE(TokenCategory.TYPE.getColor()),
    UNKNOWN(new Color(220, 50, 47)),
    NORMAL(new Color(131, 148, 150)),
    DELIMITER(TokenCategory.NORMAL.getColor()),
    PARENTHESIS(TokenCategory.NORMAL.getColor()),
    WHITESPACE( new Color(256, 256, 256));

    private Font font;

    TokenCategory(Color color) {
        this.font = new Font(color);
    }

    public void setStyle(String style) {
		this.font = font.setStyle(style);
	}

	public Font getFont() {
		return font;
	}

	public Color getColor() {
        return font.getColor().orElse(new Color(0,0,0));
    }

    public void setColor(Color color) {
	font = this.font.setColor(color);
    }
}