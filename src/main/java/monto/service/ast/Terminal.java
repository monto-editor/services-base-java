package monto.service.ast;

import java.util.function.Function;

import monto.service.region.Region;

public class Terminal extends Region implements AST {

  public Terminal(int offset, int length) {
    super(offset, length);
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
  public String toString() {
    return "Terminal " + super.toString();
  }
}
