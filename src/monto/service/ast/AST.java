package monto.service.ast;

import java.util.function.Function;

import monto.service.region.IRegion;

public interface AST extends IRegion {
    void accept(ASTVisitor visitor);
    <A> A match(Function<NonTerminal,A> f, Function<Terminal,A> g);
}
