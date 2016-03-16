package monto.service.token;

import java.util.Optional;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Font {
	private Optional<Color> color, bgcolor;
	private Optional<String> family, style, variant, weight;
	private Optional<Integer> size;
	
	public Font(Optional<Color> color, Optional<Color> bgcolor, Optional<String> family, Optional<String> style,
			Optional<String> variant, Optional<String> weight, Optional<Integer> size) {
		this.color = color;
		this.bgcolor = bgcolor;
		this.family = family;
		this.style = style;
		this.variant = variant;
		this.weight = weight;
		this.size = size;
	}

	public Font(Color clr) {
		this(Optional.of(clr), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
	}

	public Optional<Color> getColor() {
		return color;
	}

	public Optional<Color> getBgcolor() {
		return bgcolor;
	}

	public Optional<String> getFamily() {
		return family;
	}

	public Optional<String> getStyle() {
		return style;
	}

	public Optional<String> getVariant() {
		return variant;
	}

	public Optional<String> getWeight() {
		return weight;
	}

	public Optional<Integer> getSize() {
		return size;
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
		if (obj == null) { return false; }
		if (obj == this) { return true; }
		if (obj.getClass() != getClass()) {
			return false;
		}
		Font other = (Font) obj;
		return new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.append(color, other.color)
				.append(bgcolor, other.bgcolor)
				.append(style, other.style)
				.append(variant, other.variant)
				.append(weight, other.weight)
				.append(size, other.size)
				.isEquals();
	}
	
	@Override
	public String toString() {
		return Tokens.encodeFont(this).toJSONString();
	}
}
