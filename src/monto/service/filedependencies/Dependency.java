package monto.service.filedependencies;

import java.util.function.Function;

public interface Dependency {
    <A> A match(Function<VersionDependency, A> v, Function<ProductDependency, A> p);
}
