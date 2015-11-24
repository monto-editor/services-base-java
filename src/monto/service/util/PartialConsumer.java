package monto.service.util;

public interface PartialConsumer<T,E extends Exception> {
	public void accept(T t) throws E;
}
