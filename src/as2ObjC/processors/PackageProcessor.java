package as2ObjC.processors;

import org.antlr.runtime.tree.Tree;

import flexprettyprint.handlers.AS3_exParser;

import as2ObjC.ObjCWriter;
import as2ObjC.TreeElementProcessor;
import as2ObjC.tree.TreeHelper;
import as2ObjC.tree.TreeIterator;


public class PackageProcessor extends TreeElementProcessor 
{
	public PackageProcessor(ObjCWriter writer) 
	{
		super(writer);
		registerWithTypes(AS3_exParser.PACKAGE);
	}

	@Override
	public void process(TreeIterator iter, Tree current) 
	{
		String identifier = ProcessorHelper.extractIdentifier(iter);
	}
}
