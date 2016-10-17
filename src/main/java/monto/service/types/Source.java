package monto.service.types;

import java.util.Optional;

public class Source {

  private String physicalName;
  private String logicalName;

  public Source(String physicalName) {
    this(physicalName, null);
  }

  public Source(String physicalName, String logicalName) {
    this.physicalName = physicalName;
    this.logicalName = logicalName;
  }

  public String getPhysicalName() {
    return physicalName;
  }

  public Optional<String> getLogicalName() {
    return Optional.ofNullable(logicalName);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Source)) {
      return false;
    }

    Source source = (Source) o;
    // TODO: also include logical name?
    return physicalName.equals(source.physicalName);
  }

  @Override
  public int hashCode() {
    return physicalName.hashCode();
  }

  @Override
  public String toString() {
    return String.format("Source {physicalName: %s, logicalName: %s}", physicalName, logicalName);
  }
}
