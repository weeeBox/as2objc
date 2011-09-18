package as2ObjC.processors;

import org.antlr.runtime.tree.Tree;

import as2ObjC.TreeElementProcessor;
import as2ObjC.code.AS3CodeVisitor;
import as2ObjC.tree.TreeHelper;
import as2ObjC.tree.TreeIterator;
import flexprettyprint.handlers.AS3_exParser;

public class BracesProcessor extends TreeElementProcessor
{
	public BracesProcessor(AS3CodeVisitor visitor)
	{
		super(visitor);
		registerWithTypes(AS3_exParser.LCURLY, AS3_exParser.RCURLY);
	}

	@Override
	public void process(TreeIterator iter, Tree current)
	{
		if (TreeHelper.isCurlyOpen(current))
		{
			getVisitor().visitCurlyBraceOpen();
		}
		else if (TreeHelper.isCurlyClosed(current))
		{
			getVisitor().visitCurlyBraceClosed();
		}
	}
}
