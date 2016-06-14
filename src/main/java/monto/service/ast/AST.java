package monto.service.ast;

import monto.service.region.IRegion;

import java.util.function.Function;

public interface AST extends IRegion {
  void accept(ASTVisitor visitor);

  <A> A match(Function<NonTerminal, A> f, Function<Terminal, A> g);
}
