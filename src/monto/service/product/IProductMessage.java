package monto.service.product;

import java.util.List;

import monto.service.distributor.Visitor;
import monto.service.filedependencies.Dependency;
import monto.service.types.Language;
import monto.service.types.LongKey;
import monto.service.types.Message;
import monto.service.types.Product;
import monto.service.types.ServiceID;
import monto.service.types.Source;

public interface IProductMessage extends Message {

	LongKey getVersionId();

	Source getSource();

	ServiceID getServiceID();

	Product getProduct();

	Language getLanguage();

	List<Dependency> getDependencies();

	<A,E extends Throwable> A accept(Visitor<A,E> visitor) throws E;

}