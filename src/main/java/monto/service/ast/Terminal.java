package monto.service.ast;

import monto.service.region.IRegion;

import java.util.function.Function;

public class Terminal implements AST, IRegion {
  private int offset;
  private int length;

  public Terminal(int offset, int length) {
    this.offset = offset;
    this.length = length;
  }

  @Override
  public void accept(ASTVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public <A> A match(Function<NonTerminal, A> f, Function<Terminal, A> g) {
    return g.apply(this);
  }

  @Override
  public int getStartOffset() {
    return offset;
  }

  @Override
  public int getLength() {
    return length;
  }

  public boolean inRange(IRegion region) {
    return getStartOffset() >= region.getStartOffset()
        && getStartOffset() + getLength() <= region.getStartOffset() + region.getLength();
  }

  @Override
  public String toString() {
    return String.format("{offset: %d, length: %d}", offset, length);
  }
}
