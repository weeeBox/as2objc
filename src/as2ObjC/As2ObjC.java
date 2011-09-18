package as2ObjC;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import actionscriptinfocollector.ASCollector;
import actionscriptinfocollector.text.IDocument;
import actionscriptinfocollector.text.TextDocument;

public class As2ObjC 
{
	public static void main(String[] args) 
	{
		File asSourceFile = new File(args[0]);
		File outputDir = new File(args[1]);
		
		try
		{
			List<ASCollector> collectors = new ArrayList<ASCollector>();
			IDocument doc = TextDocument.read(asSourceFile);
			ASCollector.parse(doc, collectors);
			
			String moduleName = extractFileNameNoExt(asSourceFile);
			CodeWriter writer = new CodeWriter(moduleName, outputDir);
			writer.write(collectors);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
    }

	private static String extractFileNameNoExt(File file)
	{
		String filename = file.getName();
		int dotIndex = filename.lastIndexOf('.');
		return dotIndex == -1 ? filename : filename.substring(0, dotIndex);
	}
}