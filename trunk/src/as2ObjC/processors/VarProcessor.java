package as2ObjC.processors;

import org.antlr.runtime.tree.Tree;

import as2ObjC.TreeElementProcessor;
import as2ObjC.code.AS3CodeVisitor;
import as2ObjC.lang.AS3Declaration;
import as2ObjC.lang.AS3Identifier;
import as2ObjC.lang.AS3Type;
import as2ObjC.tree.TreeIterator;
import flexprettyprint.handlers.AS3_exParser;

public class VarProcessor extends TreeElementProcessor
{

	public VarProcessor(AS3CodeVisitor visitor)
	{
		super(visitor);
		registerWithTypes(AS3_exParser.VAR);
	}

	@Override
	public void process(TreeIterator iter, Tree current)
	{
		AS3Declaration declaration = ProcessorHelper.extractVariableDeclaration(iter);
		getVisitor().visitVarDeclaration(declaration);
	}

}
