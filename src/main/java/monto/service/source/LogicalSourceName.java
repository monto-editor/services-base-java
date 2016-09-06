package monto.service.source;

import monto.service.types.Source;

public class LogicalSourceName {
  private Source sourceWithLogicalName;
  private String originalContents;

  public LogicalSourceName(Source sourceWithLogicalName, String originalContents) {
    this.sourceWithLogicalName = sourceWithLogicalName;
    this.originalContents = originalContents;
  }

  public Source getSourceWithLogicalName() {
    return sourceWithLogicalName;
  }

  public String getOriginalContents() {
    return originalContents;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof LogicalSourceName)) {
      return false;
    }

    LogicalSourceName that = (LogicalSourceName) o;

    if (sourceWithLogicalName != null
        ? !sourceWithLogicalName.equals(that.sourceWithLogicalName)
        : that.sourceWithLogicalName != null) {
      return false;
    }
    return originalContents != null
        ? originalContents.equals(that.originalContents)
        : that.originalContents == null;
  }

  @Override
  public int hashCode() {
    int result = sourceWithLogicalName != null ? sourceWithLogicalName.hashCode() : 0;
    result = 31 * result + (originalContents != null ? originalContents.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return String.format("LogicalSourceName {sourceWithLogicalName: %s}", sourceWithLogicalName);
  }
}
