package monto.service.token;

import java.util.function.Function;

public enum TokenCategory {
	COMMENT(theme -> theme.base01),
	CONSTANT(theme -> theme.cyan),
	STRING(theme -> TokenCategory.CONSTANT.getColor(theme)),
	CHARACTER(theme -> TokenCategory.CONSTANT.getColor(theme)),
	NUMBER(theme -> TokenCategory.CONSTANT.getColor(theme)),
	BOOLEAN(theme -> TokenCategory.CONSTANT.getColor(theme)),
	FLOAT(theme -> TokenCategory.CONSTANT.getColor(theme)),
	IDENTIFIER(theme -> theme.blue),
	STATEMENT(theme -> theme.green),
	CONDITIONAL(theme -> TokenCategory.STATEMENT.getColor(theme)),
	REPEAT(theme -> TokenCategory.STATEMENT.getColor(theme)),
	LABEL(theme -> TokenCategory.STATEMENT.getColor(theme)),
	OPERATOR(theme -> TokenCategory.STATEMENT.getColor(theme)),
	KEYWORD(theme -> TokenCategory.STATEMENT.getColor(theme)),
	EXCEPTION(theme -> TokenCategory.STATEMENT.getColor(theme)),
	META(theme -> theme.orange),
	TYPE(theme -> theme.yellow),
	MODIFIER(theme -> TokenCategory.TYPE.getColor(theme)),
	STRUCTURE(theme -> TokenCategory.TYPE.getColor(theme)),
	UNKNOWN(theme -> theme.red),
	NORMAL(theme -> theme.base0),
	DELIMITER(theme -> TokenCategory.NORMAL.getColor(theme)),
	PARENTHESIS(theme -> TokenCategory.NORMAL.getColor(theme)),
	WHITESPACE(theme -> theme.white);
	
	private Function<ColorTheme,Color> color;
	TokenCategory(Function<ColorTheme,Color> getColor) {
		this.color = getColor;
	}
	
	public Color getColor(ColorTheme solarized) {
		return color.apply(solarized);
	}
}
