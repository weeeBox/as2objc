import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import actionscriptinfocollector.ASCollector;
import actionscriptinfocollector.text.IDocument;
import actionscriptinfocollector.text.TextDocument;


public class Main
{
	public static void main(String[] args)
	{
		try
		{
			List<ASCollector> collectors = new ArrayList<ASCollector>();
			IDocument doc = TextDocument.read(new File(args[0]));
			ASCollector.parse(doc, collectors);
			
			System.out.println(collectors.size());
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
