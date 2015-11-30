package monto.service.discovery;

import java.util.function.Function;

import monto.service.message.Language;

public class LanguageFilter implements Filter {

	private Language language;

	public LanguageFilter(Language language) {
		this.language = language;
	}

	@Override
	public <A> A match(Function<ServiceIDFilter, A> f, Function<ProductFilter, A> g, Function<LanguageFilter, A> h) {
		return h.apply(this);
	}

	public Language getLanguage() {
		return language;
	}

}
