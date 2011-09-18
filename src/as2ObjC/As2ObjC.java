package as2ObjC;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.antlr.runtime.tree.CommonTree;


import flexprettyprint.handlers.AS3_exParser.fileContents_return;
import flexprettyprint.handlers.ASPrettyPrinter;

public class As2ObjC 
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
			ObjCWriter writer = new ObjCWriter(f.getName(), new File("d:/dev/tmp/converted"));
			writer.write(tree);
		}
		catch (Exception e)
		{
			System.out.println("Error pretty printing");
			e.printStackTrace();
		}
    }
}
