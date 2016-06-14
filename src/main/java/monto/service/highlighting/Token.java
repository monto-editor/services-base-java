package monto.service.highlighting;

import monto.service.region.Region;

public class Token extends Region {

  private Font font;

  public Token(int offset, int length, Font font) {
    super(offset, length);
    this.font = font;
  }

  public Font getFont() {
    return font;
  }

  @Override
  public String toString() {
    return String.format("(%d,%d,%s)", getStartOffset(), getLength(), font);
  }
}
