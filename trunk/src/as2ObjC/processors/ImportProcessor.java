package as2ObjC.processors;

import org.antlr.runtime.tree.Tree;

import as2ObjC.TreeElementProcessor;
import as2ObjC.code.AS3CodeVisitor;
import as2ObjC.lang.AS3Identifier;
import as2ObjC.lang.AS3ImportDeclaration;
import as2ObjC.tree.TreeIterator;
import flexprettyprint.handlers.AS3_exParser;

public class ImportProcessor extends TreeElementProcessor
{
	public ImportProcessor(AS3CodeVisitor visitor)
	{
		super(visitor);
		registerWithTypes(AS3_exParser.IMPORT);
	}

	@Override
	public void process(TreeIterator iter, Tree current)
	{
		AS3Identifier identifier = ProcessorHelper.extractIdentifier(iter);
		AS3ImportDeclaration declaration = new AS3ImportDeclaration(identifier);
		getVisitor().visitAS3ImportDeclaration(declaration);
	}

}
