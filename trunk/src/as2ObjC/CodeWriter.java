package as2ObjC;

import java.io.File;
import java.io.IOException;
import java.util.List;

import actionscriptinfocollector.ASCollector;
import actionscriptinfocollector.ClassRecord;
import actionscriptinfocollector.DeclRecord;
import actionscriptinfocollector.FunctionRecord;
import actionscriptinfocollector.PropertyLine;
import actionscriptinfocollector.TextItem;

public class CodeWriter
{
	private String moduleName;

	private WriteDestination headerDest;
	private WriteDestination implementationDest;
	private WriteDestination currentDestination;

	public CodeWriter(String moduleName, File outputDir) throws IOException
	{
		this.moduleName = moduleName;

		File headerFile = new File(outputDir, moduleName + ".h");
		File implementatinFile = new File(outputDir, moduleName + ".mm");
		headerDest = new WriteDestination(headerFile);
		implementationDest = new WriteDestination(implementatinFile);
	}

	public void write(List<ASCollector> collectors) throws IOException
	{
		try
		{
			CodeHelper.writeImport(implementationDest, moduleName);

			for (ASCollector collector : collectors)
			{
				writeCollector(collector);
			}
		}
		finally
		{
			headerDest.close();
			implementationDest.close();
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
		writeClassHeader(classRecord);
		writeClassImplementation(classRecord);
	}

	private void writeClassHeader(ClassRecord classRecord)
	{
		setWriteToHeader();

		write("@interface " + CodeHelper.identifier(classRecord.getName()));

		TextItem extendsItem = classRecord.getExtends();
		String extendsName = extendsItem == null ? "NSObject" : CodeHelper.identifier(extendsItem);
		write(" : " + extendsName);
		writeln();

		writeHeaderClassBody(classRecord);

		writeln("@end");
		writeln();
	}

	private void writeHeaderClassBody(ClassRecord classRecord)
	{
		List<PropertyLine> properties = classRecord.getProperties();

		if (properties.size() > 0)
		{
			writeBlockOpen();
			for (PropertyLine propertyLine : properties)
			{
				writeHeaderProperty(propertyLine);
			}
			writeBlockClose();
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
		writeln(CodeHelper.type(declRecord.getType()) + " " + CodeHelper.identifier(declRecord.getName()) + ";");
	}

	private void writeClassImplementation(ClassRecord classRecord)
	{
		setWriteToImplementation();

		writeln("@implementation " + CodeHelper.identifier(classRecord.getName()));
		writeln("@end");
		writeln();
	}

	private void write(String line)
	{
		currentDestination.write(line);
	}

	private void writeln(String line)
	{
		currentDestination.writeln(line);
	}

	private void writeln()
	{
		currentDestination.writeln();
	}

	private void writeBlockOpen()
	{
		writeln("{");
		incTab();
	}

	private void writeBlockClose()
	{
		decTab();
		writeln("}");
	}

	private void incTab()
	{
		currentDestination.incTab();
	}

	private void decTab()
	{
		currentDestination.decTab();
	}

	private void setWriteToHeader()
	{
		setWriteDestination(headerDest);
	}

	private void setWriteToImplementation()
	{
		setWriteDestination(implementationDest);
	}

	private void setWriteDestination(WriteDestination dest)
	{
		currentDestination = dest;
	}
}
