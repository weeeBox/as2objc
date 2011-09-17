import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

import flexprettyprint.handlers.AS3_exLexer;
import flexprettyprint.handlers.AS3_exParser.fileContents_return;
import flexprettyprint.handlers.ASPrettyPrinter;

public class as2ObjC 
{
	public static void main(String[] args) throws Exception 
	{
		File f = new File(args[0]);
		
		BufferedReader br=new BufferedReader(new FileReader(f));
		StringBuffer buffer=new StringBuffer();
		while (true)
		{
			String d=br.readLine();
			if (d==null)
				break;
			buffer.append(d);
			buffer.append("\n");
		}
		String data=buffer.toString();
		try
		{
			fileContents_return fileContents_return = new ASPrettyPrinter(true, data).print(0);
			CommonTree tree = (CommonTree) fileContents_return.getTree();
			treeWalk(tree);			
		}
		catch (Exception e)
		{
			System.out.println("Error pretty printing");
			e.printStackTrace();
		}
    }

	private static void treeWalk(CommonTree tree) 
	{
		for (int i = 0; i < tree.getChildCount(); i++) 
		{
			Tree child = tree.getChild(i);
			if (child.getType() == AS3_exLexer.IDENTIFIER)
			{
				System.out.println(child);
			}
		}
	}
}
