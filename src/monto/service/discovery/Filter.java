package monto.service.discovery;

import java.util.function.Function;

public interface Filter {
	public <A> A match(
			Function<ServiceIDFilter,A> f,
			Function<ProductFilter,A> g,
			Function<LanguageFilter,A> h);
}
