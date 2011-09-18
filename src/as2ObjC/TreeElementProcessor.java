package as2ObjC;

import org.antlr.runtime.tree.Tree;

import as2ObjC.tree.TreeIterator;

public abstract class TreeElementProcessor 
{
	private ObjCWriter writer;
	
	public TreeElementProcessor(ObjCWriter writer) 
	{
		this.writer = writer;
	}
	
	public ObjCWriter getWriter() 
	{
		return writer;
	}
	
	public abstract void process(TreeIterator iter, Tree current);
}
