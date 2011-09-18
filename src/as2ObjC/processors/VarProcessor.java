package as2ObjC.processors;

import org.antlr.runtime.tree.Tree;

import flexprettyprint.handlers.AS3_exParser;

import as2ObjC.ObjCWriter;
import as2ObjC.TreeElementProcessor;
import as2ObjC.lang.AS3Identifier;
import as2ObjC.lang.AS3Type;
import as2ObjC.tree.TreeIterator;

public class VarProcessor extends TreeElementProcessor
{

	public VarProcessor(ObjCWriter writer)
	{
		super(writer);
		registerWithTypes(AS3_exParser.VAR);
	}

	@Override
	public void process(TreeIterator iter, Tree current)
	{
		String visiblity = getWriter().getVisiblityModifier().getName();
		
		AS3Identifier name = ProcessorHelper.extractIdentifier(iter);
		ProcessorHelper.skipAndCheck(iter, AS3_exParser.COLON);
		AS3Type type = ProcessorHelper.extractType(iter);
		
		log(visiblity + " " + type + " " + name);
	}

}
