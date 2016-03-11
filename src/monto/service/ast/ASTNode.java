package monto.service.ast;

import java.util.List;

import monto.service.region.IRegion;

public class ASTNode implements IRegion {
    private String name;
    private List<ASTNode> children;
    private int offset;
    private int length;

    public ASTNode(String name, List<ASTNode> children, int offset, int length) {
		this.name = name;
		this.children = children;
		this.offset = offset;
		this.length = length;
	}

	public String getName() {
		return name;
	}

	public List<ASTNode> getChildren() {
		return children;
	}

	public int getOffset() {
		return offset;
	}

	public int getLength() {
		return length;
	}

	public void addChild(ASTNode node) {
		children.add(node);
	}
	
	@Override
	public String toString() {
		return String.format("%s%s",name,children);
	}

	public ASTNode getChild(int i) {
		return children.get(i);
	}

	@Override
	public int getStartOffset() {
		return getOffset();
	}

	public void accept(ASTNodeVisitor visitor) {
		visitor.visit(this);
	}
}
