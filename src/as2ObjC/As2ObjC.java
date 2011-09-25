package as2ObjC;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import block.ClassParser;

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
			
			List<String> lines = readLines(asSourceFile);
			ClassParser classParser = new ClassParser(collectors);
			classParser.parse(lines);
			
			String moduleName = extractFileNameNoExt(asSourceFile);
			CodeWriter writer = new CodeWriter(doc, moduleName, outputDir);
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
	
	private static List<String> readLines(File file) throws IOException
	{
		BufferedReader reader = null;
		try
		{
			reader = new BufferedReader(new FileReader(file));
			List<String> lines = new ArrayList<String>();
			
			String line;
			while ((line = reader.readLine()) != null)
			{
				lines.add(line);
			}
			
			return lines;
		}
		finally
		{
			if (reader != null)
				reader.close();
		}
	}
}
