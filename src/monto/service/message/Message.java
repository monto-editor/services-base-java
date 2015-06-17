package monto.service.message;

import java.util.List;

public interface Message {
    LongKey getVersionId();

    Source getSource();

    List<Dependency> getInvalid();

    Language getLanguage();
}
