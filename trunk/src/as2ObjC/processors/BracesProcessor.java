package as2ObjC.processors;

import org.antlr.runtime.tree.Tree;

import flexprettyprint.handlers.AS3_exParser;

import as2ObjC.ObjCWriter;
import as2ObjC.TreeElementProcessor;
import as2ObjC.tree.TreeHelper;
import as2ObjC.tree.TreeIterator;

public class BracesProcessor extends TreeElementProcessor
{
	public BracesProcessor(ObjCWriter writer)
	{
		super(writer);
		registerWithTypes(AS3_exParser.LCURLY, AS3_exParser.RCURLY);
	}

	@Override
	public void process(TreeIterator iter, Tree current)
	{
		if (TreeHelper.isLCurly(current))
		{
			getWriter().curlyBraceOpened();
		}
		else if (TreeHelper.isRCurly(current))
		{
			getWriter().curlyBraceClosed();
		}
	}
}
