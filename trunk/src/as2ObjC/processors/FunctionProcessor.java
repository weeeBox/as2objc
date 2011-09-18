package as2ObjC.processors;

import org.antlr.runtime.tree.Tree;

import as2ObjC.TreeElementProcessor;
import as2ObjC.code.AS3CodeVisitor;
import as2ObjC.lang.AS3Identifier;
import as2ObjC.tree.TreeIterator;
import flexprettyprint.handlers.AS3_exParser;

public class FunctionProcessor extends TreeElementProcessor
{

	public FunctionProcessor(AS3CodeVisitor visitor)
	{
		super(visitor);
		registerWithTypes(AS3_exParser.FUNCTION);
	}

	@Override
	public void process(TreeIterator iter, Tree current)
	{
		AS3Identifier name = ProcessorHelper.extractIdentifier(iter);
		ProcessorHelper.skipAndCheck(iter, AS3_exParser.LPAREN);
		ProcessorHelper.skipAndCheck(iter, AS3_exParser.RPAREN);
	}

}
