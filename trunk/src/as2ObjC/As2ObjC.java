package as2ObjC;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
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
		File outputDir = new File(args[0]);		
		
		try
		{
			for (int i = 0; i < args.length; ++i)
			{
				File asSourceFile = new File(args[i]);
				process(asSourceFile, outputDir);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
    }

	private static void process(File file, File outputDir) throws IOException
	{
		if (file.isDirectory())
		{
			File[] files = file.listFiles(new FileFilter() 
			{
				@Override
				public boolean accept(File pathname) 
				{
					String filename = pathname.getName();
					if (pathname.isDirectory())
						return !filename.equals(".svn");
					
					return filename.endsWith(".as");
				}
			});
			
			for (File child : files) 
			{
				process(child, outputDir);
			}
		}
		else
		{
			convert(file, outputDir);
		}
	}
	
	private static void convert(File source, File outputDir) throws IOException 
	{
		System.out.println("Converting: " + source);
		
		List<ASCollector> collectors = new ArrayList<ASCollector>();
		IDocument doc = TextDocument.read(source);
		ASCollector.parse(doc, collectors);
		
		String code = readCode(source);
		ClassParser classParser = new ClassParser(collectors);
		classParser.parse(code);
		
		String moduleName = extractFileNameNoExt(source);
		CodeWriter writer = new CodeWriter(doc, moduleName, outputDir);
		writer.write(collectors);
	}

	private static String extractFileNameNoExt(File file)
	{
		String filename = file.getName();
		int dotIndex = filename.lastIndexOf('.');
		return dotIndex == -1 ? filename : filename.substring(0, dotIndex);
	}
	
	private static String readCode(File file) throws IOException
	{
		BufferedReader reader = null;
		try
		{
			reader = new BufferedReader(new FileReader(file));
			StringBuilder code = new StringBuilder();
			
			String line;
			while ((line = reader.readLine()) != null)
			{
				code.append(line);
				code.append("\n");
			}
			
			return code.toString();
		}
		finally
		{
			if (reader != null)
				reader.close();
		}
	}
}