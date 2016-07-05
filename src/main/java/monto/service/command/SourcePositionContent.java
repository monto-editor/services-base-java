package monto.service.command;

import monto.service.region.Region;
import monto.service.types.Source;

public class SourcePositionContent {
  private Source source;
  private Region selection;

  public SourcePositionContent(Source source, Region selection) {
    this.source = source;
    this.selection = selection;
  }

  public Source getSource() {
    return source;
  }

  public Region getSelection() {
    return selection;
  }
}
