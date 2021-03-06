package monto.service.highlighting;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Color {
  private final int red, green, blue;

  public Color(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  public int getRed() {
    return red;
  }

  public int getGreen() {
    return green;
  }

  public int getBlue() {
    return blue;
  }

  public Color setRed(int red) {
    return new Color(red, this.green, this.blue);
  }

  public Color setGreen(int green) {
    return new Color(this.red, green, this.blue);
  }

  public Color setBlue(int blue) {
    return new Color(this.red, this.green, blue);
  }

  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(red).append(green).append(blue).toHashCode();
  }

  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    if (obj.getClass() != getClass()) {
      return false;
    }
    Color other = (Color) obj;
    return new EqualsBuilder()
        .appendSuper(super.equals(obj))
        .append(red, other.red)
        .append(green, other.green)
        .append(blue, other.blue)
        .isEquals();
  }
}
