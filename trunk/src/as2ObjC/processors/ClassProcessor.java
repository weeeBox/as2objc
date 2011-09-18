package as2ObjC.processors;

import org.antlr.runtime.tree.Tree;

import flexprettyprint.handlers.AS3_exParser;

import as2ObjC.ObjCWriter;
import as2ObjC.TreeElementProcessor;
import as2ObjC.lang.AS3Identifier;
import as2ObjC.tree.TreeHelper;
import as2ObjC.tree.TreeIterator;

public class ClassProcessor extends TreeElementProcessor
{

	public ClassProcessor(ObjCWriter writer)
	{
		super(writer);
		registerWithTypes(AS3_exParser.CLASS);
	}

	@Override
	public void process(TreeIterator iter, Tree current)
	{
		String visiblity = getWriter().getVisiblityModifier().getName();
		AS3Identifier name = ProcessorHelper.extractIdentifier(iter);

		Tree element = iter.next();
		AS3Identifier implement = null;
		if (TreeHelper.isImplements(element))
		{
			implement = ProcessorHelper.extractIdentifier(iter);
		}

		log(visiblity + " class " + name + (implement == null ? "" : " implements " + implement));
	}

}
