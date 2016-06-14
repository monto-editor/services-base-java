package monto.service.highlighting;

import monto.service.gson.GsonMonto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Optional;

public class Font {
  private Color color, bgcolor;
  private String family, style, variant, weight;
  private Integer size;

  public Font(
      Color color,
      Color bgcolor,
      String family,
      String style,
      String variant,
      String weight,
      Integer size) {
    this.color = color;
    this.bgcolor = bgcolor;
    this.family = family;
    this.style = style;
    this.variant = variant;
    this.weight = weight;
    this.size = size;
  }

  public Font(Color clr) {
    this(clr, null, null, null, null, null, null);
  }

  public Optional<Color> getColor() {
    return Optional.ofNullable(color);
  }

  public Font setColor(Color color) {
    return new Font(color, bgcolor, family, style, variant, weight, size);
  }

  public Optional<Color> getBgcolor() {
    return Optional.ofNullable(bgcolor);
  }

  public Font setBgcolor(Color bgcolor) {
    return new Font(color, bgcolor, family, style, variant, weight, size);
  }

  public Optional<String> getFamily() {
    return Optional.ofNullable(family);
  }

  public Font setFamily(String family) {
    return new Font(color, bgcolor, family, style, variant, weight, size);
  }

  public Optional<String> getStyle() {
    return Optional.ofNullable(style);
  }

  public Font setStyle(String style) {
    return new Font(color, bgcolor, family, style, variant, weight, size);
  }

  public Optional<String> getVariant() {
    return Optional.ofNullable(variant);
  }

  public Font setVariant(String variant) {
    return new Font(color, bgcolor, family, style, variant, weight, size);
  }

  public Optional<String> getWeight() {
    return Optional.ofNullable(weight);
  }

  public Font setWeight(String weight) {
    return new Font(color, bgcolor, family, style, variant, weight, size);
  }

  public Optional<Integer> getSize() {
    return Optional.ofNullable(size);
  }

  public Font setSize(int size) {
    return new Font(color, bgcolor, family, style, variant, weight, size);
  }

  public int hashCode() {
    return new HashCodeBuilder(19, 43)
        .append(color)
        .append(bgcolor)
        .append(family)
        .append(style)
        .append(variant)
        .append(weight)
        .append(size)
        .toHashCode();
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
    Font other = (Font) obj;
    return new EqualsBuilder()
        .appendSuper(super.equals(obj))
        .append(color, other.color)
        .append(bgcolor, other.bgcolor)
        .append(family, other.family)
        .append(style, other.style)
        .append(variant, other.variant)
        .append(weight, other.weight)
        .append(size, other.size)
        .isEquals();
  }

  @Override
  public String toString() {
    return GsonMonto.toJson(this);
  }
}
