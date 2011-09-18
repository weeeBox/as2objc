package as2ObjC.tree;
import org.antlr.runtime.tree.Tree;

public class TreeIterator 
{
	private Tree tree;
	private int currentChild;

	public TreeIterator(Tree tree)
	{
		this.tree = tree;
		currentChild = -1;
	}	
	
	public boolean hasNext() 
	{
		return currentChild < tree.getChildCount() - 1;
	}
	
	public boolean hasPrev() 
	{
		return currentChild > 0;
	}

	public Tree next() 
	{
		return getChild(++currentChild);
	}


	public Tree prev() 
	{
		return getChild(--currentChild);
	}
	
	private Tree getChild(int index) 
	{
		if (index < 0 || index > tree.getChildCount() - 1)
			return null;
		
		return tree.getChild(index);
	}
}
