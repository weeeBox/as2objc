package as2ObjC;

import java.io.File;
import java.io.IOException;
import java.util.List;

import actionscriptinfocollector.ASCollector;
import actionscriptinfocollector.ClassRecord;
import actionscriptinfocollector.DeclRecord;
import actionscriptinfocollector.PropertyLine;
import actionscriptinfocollector.TextItem;

public class CodeWriter
{
	private String moduleName;

	private WriteDestination hdr;
	private WriteDestination impl;

	public CodeWriter(String moduleName, File outputDir) throws IOException
	{
		this.moduleName = moduleName;

		hdr = new WriteDestination(new File(outputDir, moduleName + ".h"));
		impl = new WriteDestination(new File(outputDir, moduleName + ".mm"));
	}

	public void write(List<ASCollector> collectors) throws IOException
	{
		try
		{
			CodeHelper.writeImport(impl, moduleName);

			for (ASCollector collector : collectors)
			{
				writeCollector(collector);
			}
		}
		finally
		{
			hdr.close();
			impl.close();
		}
	}

	private void writeCollector(ASCollector collector)
	{
		List<ClassRecord> classRecords = collector.getClassRecords();
		for (ClassRecord classRecord : classRecords)
		{
			write(classRecord);
		}
	}

	private void write(ClassRecord classRecord)
	{
		write(hdr, "@interface " + CodeHelper.identifier(classRecord.getName()));

		TextItem extendsItem = classRecord.getExtends();
		String extendsName = extendsItem == null ? "NSObject" : CodeHelper.identifier(extendsItem);
		write(hdr, " : " + extendsName);
		writeln(hdr);
		
		writeln(impl, "@implementation " + CodeHelper.identifier(classRecord.getName()));
		
		writeHeaderClassBody(classRecord);
		
		writeln(hdr, "@end");
		writeln(hdr);
		
		writeln(impl, "@end");
		writeln(impl);
	}

	private void writeHeaderClassBody(ClassRecord classRecord)
	{
		List<PropertyLine> properties = classRecord.getProperties();

		if (properties.size() > 0)
		{
			writeBlockOpen(hdr);
			for (PropertyLine propertyLine : properties)
			{
				writeHeaderProperty(propertyLine);
			}
			writeBlockClose(hdr);
		}
	}

	private void writeHeaderProperty(PropertyLine propertyLine)
	{
		List<DeclRecord> properties = propertyLine.getProperties();
		for (DeclRecord declRecord : properties)
		{
			writeHeaderDeclRecord(declRecord);
		}
	}

	private void writeHeaderDeclRecord(DeclRecord declRecord)
	{
		writeln(hdr, CodeHelper.type(declRecord.getType()) + " " + CodeHelper.identifier(declRecord.getName()) + ";");
	}

	private void write(WriteDestination dest, String line)
	{
		dest.write(line);
	}
	
	private void writeln(WriteDestination dest, String line)
	{
		dest.writeln(line);
	}
	
	private void writeln(WriteDestination dest)
	{
		dest.writeln();
	}
	
	private void incTab(WriteDestination dest)
	{
		dest.incTab();
	}
	
	private void decTab(WriteDestination dest)
	{
		dest.decTab();
	}
	
	private void writeBlockOpen(WriteDestination dest)
	{
		dest.writeln("{");
		dest.incTab();
	}
	
	private void writeBlockClose(WriteDestination dest)
	{
		dest.decTab();
		dest.writeln("}");
	}
}
