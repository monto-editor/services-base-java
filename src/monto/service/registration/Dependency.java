package monto.service.registration;

import java.util.function.Function;

public interface Dependency {
	<A> A match(
			Function<ServiceDependency, A> serviceDependencyFun,
			Function<SourceDependency, A> sourceDependencyFun
		);
}
