package as2ObjC.processors;

import org.antlr.runtime.tree.Tree;

import flexprettyprint.handlers.AS3_exParser;

import as2ObjC.ObjCWriter;
import as2ObjC.TreeElementProcessor;
import as2ObjC.code.AS3CodeVisitor;
import as2ObjC.lang.AS3Identifier;
import as2ObjC.tree.TreeIterator;


public class ImportProcessor extends TreeElementProcessor {

	public ImportProcessor(AS3CodeVisitor visitor) 
	{
		super(visitor);
		registerWithTypes(AS3_exParser.IMPORT);
	}

	@Override
	public void process(TreeIterator iter, Tree current) 
	{
		AS3Identifier identifier = ProcessorHelper.extractIdentifier(iter);
	}

}
