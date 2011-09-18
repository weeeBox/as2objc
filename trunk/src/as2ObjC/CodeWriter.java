package as2ObjC;

import java.io.File;
import java.util.List;

import actionscriptinfocollector.ASCollector;

public class CodeWriter
{
	private WriteDestination header;
	private WriteDestination implementation;

	public CodeWriter(String moduleName, File outputDir)
	{
		File headerFile = new File(outputDir, moduleName + ".h");
		File implementatinFile = new File(outputDir, moduleName + ".mm");
		header = new WriteDestination(headerFile);
		implementation = new WriteDestination(implementatinFile);
	}

	public void write(List<ASCollector> collectors)
	{
		for (ASCollector collector : collectors)
		{
			writeCollector(collector);
		}
	}
	
	private void writeCollector(ASCollector collector)
	{
		
	}

	private void writeHeader(String line)
	{
		header.addLine(line);
	}
	
	private void writeImplementation(String line)
	{
		implementation.addLine(line);
	}
}
