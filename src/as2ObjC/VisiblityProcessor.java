package as2ObjC;

import org.antlr.runtime.tree.Tree;

import as2ObjC.lang.VisiblityModifier;
import as2ObjC.tree.TreeIterator;

public class VisiblityProcessor extends TreeElementProcessor
{
	public VisiblityProcessor(ObjCWriter writer)
	{
		super(writer);
	}

	@Override
	public void process(TreeIterator iter, Tree current)
	{
		VisiblityModifier visiblityModifier;
		
		String visiblity = current.getText();
		if (visiblity.equals("private"))
			visiblityModifier = VisiblityModifier.PRIVATE;
		else if (visiblity.equals("protected"))
			visiblityModifier = VisiblityModifier.PROTECTED;
		else
			visiblityModifier = VisiblityModifier.PUBLIC;
		
		getWriter().setVisiblityModifier(visiblityModifier);
	}

}
