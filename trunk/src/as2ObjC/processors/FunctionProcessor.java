package as2ObjC.processors;

import org.antlr.runtime.tree.Tree;

import flexprettyprint.handlers.AS3_exParser;

import as2ObjC.ObjCWriter;
import as2ObjC.TreeElementProcessor;
import as2ObjC.lang.AS3Identifier;
import as2ObjC.tree.TreeIterator;

public class FunctionProcessor extends TreeElementProcessor
{

	public FunctionProcessor(ObjCWriter writer)
	{
		super(writer);
		registerWithTypes(AS3_exParser.FUNCTION);
	}

	@Override
	public void process(TreeIterator iter, Tree current)
	{
		AS3Identifier name = ProcessorHelper.extractIdentifier(iter);
		log("function " + name);
		ProcessorHelper.skipAndCheck(iter, AS3_exParser.LPAREN);
		ProcessorHelper.skipAndCheck(iter, AS3_exParser.RPAREN);
	}

}
