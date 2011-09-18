package as2ObjC.processors;

import org.antlr.runtime.tree.Tree;

import as2ObjC.tree.TreeHelper;
import as2ObjC.tree.TreeIterator;

public class ProcessorHelper
{
	public static String identifier(TreeIterator iter)
	{
		StringBuilder result = new StringBuilder();
		
		while (iter.hasNext())
		{
			Tree element = iter.next();
			if (TreeHelper.isIdentifier(element))
			{
				result.append(identifier(element));
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

	private static String identifier(Tree element)
	{
		if (!TreeHelper.isIdentifier(element))
			throw new IllegalArgumentException("Cannot create identifier from: " + TreeHelper.getTypeName(element));
		return element.getText();
	}
}
