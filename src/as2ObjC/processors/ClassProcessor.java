package as2ObjC.processors;

import org.antlr.runtime.tree.Tree;

import as2ObjC.TreeElementProcessor;
import as2ObjC.code.AS3CodeVisitor;
import as2ObjC.lang.AS3ClassDeclaration;
import as2ObjC.lang.AS3Identifier;
import as2ObjC.lang.AS3Type;
import as2ObjC.tree.TreeHelper;
import as2ObjC.tree.TreeIterator;
import flexprettyprint.handlers.AS3_exParser;

public class ClassProcessor extends TreeElementProcessor
{

	public ClassProcessor(AS3CodeVisitor visitor)
	{
		super(visitor);
		registerWithTypes(AS3_exParser.CLASS);
	}

	@Override
	public void process(TreeIterator iter, Tree current)
	{
		AS3Identifier name = ProcessorHelper.extractIdentifier(iter);

		AS3ClassDeclaration declaration = new AS3ClassDeclaration(name);
		
		Tree element;
		while (!TreeHelper.isCurlyClosed((element = iter.next())))
		{
			if (TreeHelper.isImplements(element))
			{
				AS3Type implementsType = ProcessorHelper.extractType(iter);
				declaration.addImplementsType(implementsType);
			}
			else if (TreeHelper.isExtends(element))
			{
				AS3Type extendsType = ProcessorHelper.extractType(iter);
				declaration.setExtendsType(extendsType);
			}
		}
		iter.prev();
		
	}

}
