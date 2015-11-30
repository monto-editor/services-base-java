package monto.service.registration;

import java.util.function.Function;

import monto.service.message.Language;

public class SourceDependency implements Dependency {
	private Language sourceLanguage;



	public SourceDependency(Language sourceLanguage) {
		this.sourceLanguage = sourceLanguage;
	}

	@Override
	public <A> A match(Function<ServiceDependency, A> serviceDependencyFun,
			Function<SourceDependency, A> sourceDependencyFun) {
		return sourceDependencyFun.apply(this);
	}

	public Language getSourceLanguage() {
		return sourceLanguage;
	}
}
