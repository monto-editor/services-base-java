package monto.service.message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ProductMessage implements Message {

    private final LongKey versionId;
    private final Source source;
    private final ServiceID serviceID;
    private final Product product;
    private final Language language;
    private final Object contents;
    private final List<Dependency> invalid;
    private final List<Dependency> dependencies;

    public ProductMessage(LongKey versionId, Source source, ServiceID serviceID, Product product, Language language, Object contents, Dependency... dependencies) {
        this(versionId, source, serviceID, product, language, contents, new ArrayList<>(), Arrays.asList(dependencies));
    }

    public ProductMessage(LongKey versionId, Source source, ServiceID serviceID, Product product, Language language, Object contents, List<Dependency> invalid2, List<Dependency> dependencies) {
        this.versionId = versionId;
        this.source = source;
        this.serviceID = serviceID;
        this.product = product;
        this.language = language;
        this.contents = contents;
        this.invalid = invalid2;
        this.dependencies = dependencies;
    }

    public LongKey getVersionId() {
        return versionId;
    }

    public Source getSource() {
        return source;
    }
    
    public ServiceID getServiceID() {
    	return serviceID;
    }

    public Product getProduct() {
        return product;
    }

    public Language getLanguage() {
        return language;
    }

    public Object getContents() {
        return contents;
    }

    public List<Dependency> getInvalid() {
        return invalid;
    }

    public List<Dependency> getDependencies() {
        return dependencies;
    }

    @Override
    public String toString() {
        return String.format("{"
                + "  vid: %s,\n"
                + "  source: %s,\n"
                + "  serviceID: %s,\n"
                + "  product: %s,\n"
                + "  language: %s,\n"
                + "  contents: %s,\n"
                + "  dependencies: %s\n"
                + "}", versionId, source, serviceID, product, language, contents, dependencies);
    }

}
