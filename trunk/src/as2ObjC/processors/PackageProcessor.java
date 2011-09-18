package as2ObjC.processors;

import org.antlr.runtime.tree.Tree;

import as2ObjC.ObjCWriter;
import as2ObjC.TreeElementProcessor;
import as2ObjC.tree.TreeHelper;
import as2ObjC.tree.TreeIterator;


public class PackageProcessor extends TreeElementProcessor 
{
	public PackageProcessor(ObjCWriter writer) 
	{
		super(writer);
	}

	@Override
	public void process(TreeIterator iter, Tree current) 
	{
		StringBuilder nameBuffer = new StringBuilder();
		Tree element;
		do
		{
			element = iter.next();
			if (TreeHelper.isIdentifier(element))
			{
				nameBuffer.append(element);
			}
			else if (TreeHelper.isDot(element))
			{
				nameBuffer.append('.');
			}
			
		}
		while (!TreeHelper.isLCurly(element));
	}
}
