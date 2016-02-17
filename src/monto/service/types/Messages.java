package monto.service.types;

import java.util.List;

import monto.service.configuration.ConfigurationMessage;
import monto.service.product.ProductMessage;
import monto.service.source.SourceMessage;


public class Messages {
    public static SourceMessage getVersionMessage(List<Message> messages) {
        if (messages == null)
            throw new IllegalArgumentException("Message list was null");
        SourceMessage versionMessage = messages.stream()
                .filter(msg -> msg instanceof SourceMessage)
                .findFirst()
                .map(msg -> (SourceMessage) msg).get();
        if (versionMessage == null) {
            throw new IllegalArgumentException("VersionMessage missing");
        }
        return versionMessage;
    }

    public static ProductMessage getProductMessage(List<Message> messages, Product product, Language language) {
        if (messages == null)
            throw new IllegalArgumentException("Message list was null");
        ProductMessage productMessage = messages.stream()
                .filter(msg -> {
                    if (msg instanceof ProductMessage) {
                        ProductMessage msg1 = (ProductMessage) msg;
                        return msg1.getProduct().equals(product) && msg1.getLanguage().equals(language);
                    } else {
                        return false;
                    }
                }).findAny()
                .map(msg -> (ProductMessage) msg).get();
        if (productMessage == null) {
            throw new IllegalArgumentException(String.format("ProductMessage missing: %s, %s", product, language));
        }
        return productMessage;
    }

    public static ConfigurationMessage getConfigurationMessage(List<Message> messages) {
        if (messages == null) {
            throw new IllegalArgumentException("Message list was null");
        }
        ConfigurationMessage configurationMessage = messages.stream()
                .filter(msg -> msg instanceof ConfigurationMessage).findAny()
                .map(msg -> (ConfigurationMessage) msg).get();
        if (configurationMessage == null) {
            throw new IllegalArgumentException("ConfigurationMessage missing");
        }
        return configurationMessage;
    }
}
