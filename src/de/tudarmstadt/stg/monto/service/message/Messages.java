package de.tudarmstadt.stg.monto.service.message;

import java.util.List;


public class Messages {
    public static VersionMessage getVersionMessage(List<Message> messages) {
        if (messages == null)
            throw new IllegalArgumentException("Message list was null");
        messages.stream()
                .filter(msg -> msg instanceof VersionMessage)
                .findFirst()
                .map(msg -> (VersionMessage) msg);
        throw new IllegalArgumentException("VersionMessage missing");
    }

    public static ProductMessage getProductMessage(List<Message> messages, Product product, Language language) {
        if (messages == null)
            throw new IllegalArgumentException("Message list was null");
        messages.stream()
                .filter(msg -> {
                    if (msg instanceof ProductMessage) {
                        ProductMessage msg1 = (ProductMessage) msg;
                        return msg1.getProduct().equals(product) && msg1.getLanguage().equals(language);
                    } else {
                        return false;
                    }
                }).findAny()
                .map(msg -> (ProductMessage) msg);
        throw new IllegalArgumentException(String.format("ProductMessage missing: %s, %s", product, language));
    }
}
