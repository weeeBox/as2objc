package as2ObjC.processors;

import org.antlr.runtime.tree.Tree;

import flexprettyprint.handlers.AS3_exParser;

import as2ObjC.TreeElementProcessor;
import as2ObjC.code.AS3CodeVisitor;
import as2ObjC.lang.AS3Visiblity;
import as2ObjC.tree.TreeIterator;

public class VisiblityProcessor extends TreeElementProcessor
{
	public VisiblityProcessor(AS3CodeVisitor visitor)
	{
		super(visitor);
		registerWithTypes(AS3_exParser.PUBLIC, AS3_exParser.PRIVATE, AS3_exParser.PROTECTED);
	}

	@Override
	public void process(TreeIterator iter, Tree current)
	{
		AS3Visiblity visiblityModifier;
		
		String visiblity = current.getText();
		if (visiblity.equals("private"))
			visiblityModifier = AS3Visiblity.PRIVATE;
		else if (visiblity.equals("protected"))
			visiblityModifier = AS3Visiblity.PROTECTED;
		else
			visiblityModifier = AS3Visiblity.PUBLIC;
		
		getVisitor().visitVisiblityModifier(visiblityModifier);
	}

}
