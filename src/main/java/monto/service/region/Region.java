package monto.service.region;

import java.util.Arrays;

public class Region implements IRegion {
  private int offset;
  private int length;

  public Region(int offset, int length) {
    this.offset = offset;
    this.length = length;
  }

  public Region(IRegion region) {
    this(region.getStartOffset(), region.getLength());
  }

  @Override
  public int getStartOffset() {
    return offset;
  }

  @Override
  public int getLength() {
    return length;
  }

  public String getText(String document) {
    return document.substring(getStartOffset(), getEndOffset());
  }

  public static Region fromLineNumber(String document, int startLine, int endLine) {
    int[] offsets = getLineOffsets(document);
    return fromLineNumberColumn(
        offsets, startLine, 0, endLine, offsets[endLine] - offsets[endLine - 1] - 1);
  }

  public static Region fromLineNumberColumn(
      int[] offsets, int startLine, int startColumn, int endLine, int endColumn) {
    try {
      int startOffset = offsets[startLine - 1] + startColumn - 1;
      int endOffset;
      if (endLine - 1 >= offsets.length) {
        endOffset = offsets[offsets.length - 1] + endColumn - 1;
      } else {
        endOffset = offsets[endLine - 1] + endColumn - 1;
      }
      return new Region(startOffset, endOffset - startOffset + 1);
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new RuntimeException(
          String.format("%d, %s", offsets.length, Arrays.toString(offsets)), e);
    }
  }

  public static int[] getLineOffsets(String document) {
    String lines[] = document.split("\\r?\\n");
    int[] offsets = new int[lines.length + 1];
    int offset = 0;
    offsets[0] = offset;
    int i = 1;
    for (String line : lines) {
      offset += line.length() + 1;
      offsets[i] = offset;
      i++;
    }
    return offsets;
  }

  @Override
  public String toString() {
    return String.format("{offset: %d, length: %d}", offset, length);
  }
}
