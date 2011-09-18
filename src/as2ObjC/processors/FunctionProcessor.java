package as2ObjC.processors;

import java.util.List;

import org.antlr.runtime.tree.Tree;

import as2ObjC.TreeElementProcessor;
import as2ObjC.code.AS3CodeVisitor;
import as2ObjC.lang.AS3ConstructorDeclaration;
import as2ObjC.lang.AS3Declaration;
import as2ObjC.lang.AS3FunctionDeclaration;
import as2ObjC.lang.AS3Identifier;
import as2ObjC.lang.AS3Type;
import as2ObjC.tree.TreeHelper;
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
		List<AS3Declaration> paramsList = ProcessorHelper.extractParamsList(iter);
		
		Tree element = iter.next();
		if (TreeHelper.isColon(element))
		{
			AS3Type returnType = ProcessorHelper.extractType(iter);
			AS3FunctionDeclaration declaration = new AS3FunctionDeclaration(name, paramsList, returnType);
			getVisitor().visitAS3FunctionDeclaration(declaration);
		}
		else
		{
			iter.prev();
			AS3ConstructorDeclaration declaration = new AS3ConstructorDeclaration(name, paramsList);
			getVisitor().visitAS3ConstructorDeclaration(declaration);
		}
		
	}

}
