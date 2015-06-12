package de.tudarmstadt.stg.monto.service.ast;

import de.tudarmstadt.stg.monto.service.region.IRegion;

public interface AST extends IRegion {
    void accept(ASTVisitor visitor);
}
