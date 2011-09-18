package as2ObjC.processors;

import org.antlr.runtime.tree.Tree;

import as2ObjC.tree.TreeHelper;
import as2ObjC.tree.TreeIterator;

public class ProcessorHelper
{
	///////////////////////////////////////////////////////////////////////
	// Extractor
	
	public static String extractType(TreeIterator iter)
	{
		String name = extractIdentifier(iter.next());
		return name;
	}
	
	public static String extractIdentifier(TreeIterator iter)
	{
		StringBuilder result = new StringBuilder();
		
		while (iter.hasNext())
		{
			Tree element = iter.next();
			if (TreeHelper.isIdentifier(element))
			{
				result.append(extractIdentifier(element));
			}
			else if (TreeHelper.isDot(element))
			{
				result.append('.');
			}	
			else
			{
				iter.prev();
				break;
			}
		}
		
		return result.toString();
	}

	private static String extractIdentifier(Tree element)
	{
		if (!TreeHelper.isIdentifier(element))
			throw new IllegalArgumentException("Cannot create identifier from: " + TreeHelper.getTypeName(element));
		return element.getText();
	}
	
	///////////////////////////////////////////////////////////////////////
	// Helpers

	/** Get next element from iterator and check it against some type. If expected element type doesn't match actual type - exception is thrown */
	public static void skipAndCheck(TreeIterator iter, int type)
	{
		Tree element = iter.next();
		if (element.getType() != type)
			throw new IllegalArgumentException("Element skip check failed. Expected " + TreeHelper.getTypeName(type) + " but found " + TreeHelper.getTypeName(element.getType()));
	}
}
