package as2ObjC.processors;

import org.antlr.runtime.tree.Tree;

import as2ObjC.ObjCWriter;
import as2ObjC.TreeElementProcessor;
import as2ObjC.tree.TreeHelper;
import as2ObjC.tree.TreeIterator;

public class ClassProcessor extends TreeElementProcessor {

	public ClassProcessor(ObjCWriter writer) {
		super(writer);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process(TreeIterator iter, Tree current) 
	{
		String visiblity = getWriter().getVisiblityModifier().getName();
		
		Tree element = iter.next();
		String name = element.getText();
		
		element = iter.next();
		if (TreeHelper.isImplements(element))
		{
			
		}
	}

}
