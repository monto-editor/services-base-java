package monto.service.registration;

import java.util.function.Function;

import monto.service.types.Language;

public class SourceDependency implements Dependency {
	private Language language;
	
	public SourceDependency(Language language) {
		this.language = language;
	}

	@Override
	public <A> A match(Function<ProductDependency, A> serviceDependencyFun,
			Function<SourceDependency, A> sourceDependencyFun) {
		return sourceDependencyFun.apply(this);
	}

	public Language getSourceLanguage() {
		return language;
	}
}
