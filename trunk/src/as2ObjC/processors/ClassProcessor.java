package as2ObjC.processors;

import org.antlr.runtime.tree.Tree;

import as2ObjC.TreeElementProcessor;
import as2ObjC.code.AS3CodeVisitor;
import as2ObjC.lang.AS3Identifier;
import as2ObjC.tree.TreeHelper;
import as2ObjC.tree.TreeIterator;
import flexprettyprint.handlers.AS3_exParser;

public class ClassProcessor extends TreeElementProcessor
{

	public ClassProcessor(AS3CodeVisitor visitor)
	{
		super(visitor);
		registerWithTypes(AS3_exParser.CLASS);
	}

	@Override
	public void process(TreeIterator iter, Tree current)
	{
		AS3Identifier name = ProcessorHelper.extractIdentifier(iter);

		Tree element = iter.next();
		AS3Identifier implement = null;
		if (TreeHelper.isImplements(element))
		{
			implement = ProcessorHelper.extractIdentifier(iter);
		}
	}

}
