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
	
	protected void registerWithTypes(int... types)
	{
		getWriter().registerProcessor(this, types);
	}
	
	protected ObjCWriter getWriter() 
	{
		return writer;
	}
	
	protected void log(String message)
	{
		getWriter().log(message);
	}
	
	public abstract void process(TreeIterator iter, Tree current);
}
