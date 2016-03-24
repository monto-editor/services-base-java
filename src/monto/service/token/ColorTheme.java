package monto.service.token;

public class ColorTheme {
	public Color base0,base00,base01,base02,base03,	
		base1,base2,base3,blue,cyan,green,magenta,
		orange,red,violet,yellow,white;
	
	public static ColorTheme solarized() {
		ColorTheme theme = new ColorTheme();
		theme.base0 = new Color(131,148,150);
		theme.base00 = new Color(101,123,131);	
		theme.base01 = new Color(88,110,117);	
		theme.base02 = new Color(7,54,68);
		theme.base03 = new Color(0,43,54);
		theme.base1 = new Color(147,161,161);
		theme.base2 = new Color(238,232,213);
		theme.base3 = new Color(253,246,227);
		theme.blue = new Color(28,139,210);
		theme.cyan = new Color(42,161,152);
		theme.green = new Color(133,153,0);
		theme.magenta = new Color(211,54,130);
		theme.orange = new Color(203,75,22);
		theme.red = new Color(220,50,47);
		theme.violet = new Color(108,113,196);
		theme.yellow = new Color(181,137,0);
		theme.white = new Color(256,256,256);
		return theme;
	}
}
