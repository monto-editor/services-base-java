package monto.service.types;

public interface PartialConsumer<T, E extends Exception> {
    public void accept(T t) throws E;
}
