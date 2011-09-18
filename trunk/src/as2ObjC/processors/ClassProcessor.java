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
		String name = ProcessorHelper.identifier(iter);
		
		Tree element = iter.next();
		String implement = null;
		if (TreeHelper.isImplements(element))
		{
			implement = ProcessorHelper.identifier(iter);
		}
		
		System.out.println(visiblity + " class " + name + (implement == null ? "" : " implements " + implement));
	}

}
