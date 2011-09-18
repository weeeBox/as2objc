package as2ObjC;

import org.antlr.runtime.tree.Tree;

import as2ObjC.code.AS3CodeVisitor;
import as2ObjC.processors.ProcessorsRegistry;
import as2ObjC.tree.TreeIterator;

public abstract class TreeElementProcessor 
{
	private AS3CodeVisitor visitor;
	
	public TreeElementProcessor(AS3CodeVisitor visitor) 
	{
		this.visitor = visitor;
	}
	
	protected void registerWithTypes(int... types)
	{
		ProcessorsRegistry.registerProcessor(this, types);
	}
	
	protected AS3CodeVisitor getVisitor() 
	{
		return visitor;
	}
	
	public abstract void process(TreeIterator iter, Tree current);
}
