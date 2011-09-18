package as2ObjC.processors;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.tree.Tree;

import flexprettyprint.handlers.AS3_exParser;

import as2ObjC.lang.AS3Declaration;
import as2ObjC.lang.AS3Identifier;
import as2ObjC.lang.AS3Type;
import as2ObjC.tree.TreeHelper;
import as2ObjC.tree.TreeIterator;

public class ProcessorHelper
{
	// /////////////////////////////////////////////////////////////////////
	// Extractor

	public static List<AS3Declaration> extractParamsList(TreeIterator iter)
	{
		List<AS3Declaration> declarations = new ArrayList<AS3Declaration>();
		
		ProcessorHelper.skipAndCheck(iter, AS3_exParser.LPAREN);

		do
		{
			Tree element = iter.next();
			if (TreeHelper.isComma(element))
			{
				// do nothing
			}
			else if (TreeHelper.isParenthesisClosed(element))
			{
				break;
			}
			else
			{
				iter.prev();
				declarations.add(extractVariableDeclaration(iter));
			}
		}
		while (iter.hasNext());
		
		return declarations;
	}

	public static AS3Declaration extractVariableDeclaration(TreeIterator iter)
	{
		AS3Identifier name = ProcessorHelper.extractIdentifier(iter);
		ProcessorHelper.skipAndCheck(iter, AS3_exParser.COLON);
		AS3Type type = ProcessorHelper.extractType(iter);
		return new AS3Declaration(type, name);
	}

	public static AS3Type extractType(TreeIterator iter)
	{
		Tree element = iter.next();
		String name;
		if (TreeHelper.isIdentifier(element))
		{
			name = extractIdentifier(element);
		}
		else if (TreeHelper.isVoid(element))
		{
			name = "void";
		}
		else
		{
			throw new IllegalArgumentException("Cannot extract type from: " + TreeHelper.getTypeName(element));
		}
		return new AS3Type(name);
	}

	public static AS3Identifier extractIdentifier(TreeIterator iter)
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

		return new AS3Identifier(result.toString());
	}

	private static String extractIdentifier(Tree element)
	{
		if (!TreeHelper.isIdentifier(element))
			throw new IllegalArgumentException("Cannot create identifier from: " + TreeHelper.getTypeName(element));
		return element.getText();
	}

	// /////////////////////////////////////////////////////////////////////
	// Helpers

	/**
	 * Get next element from iterator and check it against some type. If
	 * expected element type doesn't match actual type - exception is thrown
	 */
	public static void skipAndCheck(TreeIterator iter, int type)
	{
		Tree element = iter.next();
		if (element.getType() != type)
			throw new IllegalArgumentException("Element skip check failed. Expected " + TreeHelper.getTypeName(type) + " but found " + TreeHelper.getTypeName(element.getType()));
	}
}
