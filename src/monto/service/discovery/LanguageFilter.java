package monto.service.discovery;

import java.util.function.Function;

public class LanguageFilter implements Filter {

	public LanguageFilter(String language) {
		this.language = language;
	}

	private String language;

	@Override
	public <A> A match(Function<ServiceIDFilter, A> f, Function<ProductFilter, A> g, Function<LanguageFilter, A> h) {
		return h.apply(this);
	}

	public String getLanguage() {
		return language;
	}

}
