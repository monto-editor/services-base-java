package monto.service.source;

import monto.service.types.Source;

public class LogicalNameAbsentException extends Exception {
  public LogicalNameAbsentException(Source sourceWithAbsentLogicalName) {
    super("Logical name of " + sourceWithAbsentLogicalName + " was required, but not present");
  }
}
