package monto.service.product;

import java.util.Arrays;
import java.util.List;

import monto.service.filedependencies.Dependency;
import monto.service.types.Language;
import monto.service.types.LongKey;
import monto.service.types.Product;
import monto.service.types.ServiceID;
import monto.service.types.Source;

abstract public class AbstractProductMessage implements IProductMessage {
    protected final LongKey versionId;
    protected final Source source;
    protected final ServiceID serviceID;
    protected final Product product;
    protected final Language language;
    protected final List<Dependency> dependencies;

    public AbstractProductMessage(LongKey versionId, Source source, ServiceID serviceID, Product product, Language language, Dependency... dependencies) {
        this(versionId, source, serviceID, product, language, Arrays.asList(dependencies));
    }

    public AbstractProductMessage(LongKey versionId, Source source, ServiceID serviceID, Product product, Language language, List<Dependency> dependencies) {
        this.versionId = versionId;
        this.source = source;
        this.serviceID = serviceID;
        this.product = product;
        this.language = language;
        this.dependencies = dependencies;
    }

    /* (non-Javadoc)
	 * @see monto.service.message.IProductMessage#getVersionId()
	 */
    @Override
	public LongKey getVersionId() {
        return versionId;
    }

    /* (non-Javadoc)
	 * @see monto.service.message.IProductMessage#getSource()
	 */
    @Override
	public Source getSource() {
        return source;
    }

    /* (non-Javadoc)
	 * @see monto.service.message.IProductMessage#getServiceID()
	 */
    @Override
	public ServiceID getServiceID() {
	return serviceID;
    }

    /* (non-Javadoc)
	 * @see monto.service.message.IProductMessage#getProduct()
	 */
    @Override
	public Product getProduct() {
        return product;
    }

    /* (non-Javadoc)
	 * @see monto.service.message.IProductMessage#getLanguage()
	 */
    @Override
	public Language getLanguage() {
        return language;
    }

//    public Optional<Integer> getContentsKey() {
//    	return contentsKey;
//    }

    /* (non-Javadoc)
	 * @see monto.service.message.IProductMessage#getDependencies()
	 */
    @Override
	public List<Dependency> getDependencies() {
        return dependencies;
    }

}
