package monto.service.completion;

import java.net.URL;

public class Completion {

  private String description;
  private String replacement;
  private int deleteBeginOffset;
  private int deleteLength;
  private URL icon;

  public Completion(
      String description, String replacement, int deleteBeginOffset, int deleteLength, URL icon) {
    this.description = description;
    this.replacement = replacement;
    this.deleteBeginOffset = deleteBeginOffset;
    this.deleteLength = deleteLength;
    this.icon = icon;
  }

  public String getDescription() {
    return description;
  }

  public String getReplacement() {
    return replacement;
  }

  public URL getIcon() {
    return icon;
  }

  public int getDeleteBeginOffset() {
    return deleteBeginOffset;
  }

  public int getDeleteLength() {
    return deleteLength;
  }

  @Override
  public String toString() {
    return String.format("Completition {%s (%s)}", replacement, description);
  }
}
