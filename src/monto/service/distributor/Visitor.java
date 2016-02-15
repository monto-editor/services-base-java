package monto.service.distributor;

import monto.service.product.ProductMessageWithContents;
import monto.service.product.ProductMessageWithKey;

abstract public class Visitor<T,E extends Throwable> {

	abstract public T visitProductMessageWithContents(ProductMessageWithContents prd) throws E;
	abstract public T visitProductMessageWithKey(ProductMessageWithKey kPrd) throws E;
}
