package monto.service.completion;

import monto.service.region.Region;
import monto.service.types.Source;

public class CompletionRequest {
  private Source source;
  private Region selection;

  public CompletionRequest(Source source, Region selection) {
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
