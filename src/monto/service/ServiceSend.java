package monto.service;

import monto.service.dependency.RegisterDynamicDependencies;
import monto.service.product.ProductMessage;

public class ServiceSend {
	private String tag;
	private Object contents;
	private ServiceSend(String tag, Object contents) {
		super();
		this.tag = tag;
		this.contents = contents;
	}

	public static ServiceSend product(ProductMessage msg) {
		return new ServiceSend("product", msg);
	}

	public static ServiceSend dyndep(RegisterDynamicDependencies msg) {
		return new ServiceSend("dependency", msg);
	}

	public String getTag() {
		return tag;
	}

	public Object getContents() {
		return contents;
	}
}