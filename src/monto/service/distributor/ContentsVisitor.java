package monto.service.distributor;


import monto.service.product.ProductMessage;
import monto.service.product.ProductMessageWithContents;
import monto.service.product.ProductMessageWithKey;
import monto.service.product.ProductMessages;

public class ContentsVisitor extends Visitor<ProductMessage, InvalidKeyException>{

	private Distributor distributor;


	public ContentsVisitor(Distributor distributor) {
		super();
		this.distributor = distributor;
	}

	@Override
	public ProductMessage visitProductMessageWithContents(ProductMessageWithContents prd) {
		return prd;
	}

	@Override
	public ProductMessage visitProductMessageWithKey(ProductMessageWithKey kPrd) throws InvalidKeyException {
			Object contents = distributor.get(kPrd.getContentsKey());
			return ProductMessages.constructMsgWithContents(kPrd, contents);
	}

}
