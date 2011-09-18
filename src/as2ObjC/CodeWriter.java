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
import actionscriptinfocollector.TopLevelItemRecord;

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

		writeClassBody(classRecord);

		writeln(hdr, "@end");
		writeln(hdr);

		writeln(impl, "@end");
		writeln(impl);
	}

	private void writeClassBody(ClassRecord classRecord)
	{
		writeProperties(classRecord);
		writeFunctions(classRecord);
	}

	private void writeProperties(ClassRecord classRecord)
	{
		List<PropertyLine> properties = classRecord.getProperties();

		if (properties.size() > 0)
		{
			writeBlockOpen(hdr);
			for (PropertyLine propertyLine : properties)
			{
				writeProperty(hdr, propertyLine);
			}
			writeBlockClose(hdr);
		}
	}

	private void writeProperty(WriteDestination dest, PropertyLine propertyLine)
	{
		List<DeclRecord> properties = propertyLine.getProperties();
		for (DeclRecord declRecord : properties)
		{
			CodeHelper.writeDeclaration(dest, declRecord);
			dest.writeln(";");
		}
	}

	private void writeFunctions(ClassRecord classRecord)
	{
		List<FunctionRecord> functions = classRecord.getFunctions();
		for (FunctionRecord functionRecord : functions)
		{
			writeFunction(functionRecord);
		}
	}

	private void writeFunction(FunctionRecord functionRecord)
	{
		int modifierFlags = functionRecord.getModifierFlags();

		TextItem returnType = functionRecord.getReturnType();
		boolean isConstructor = returnType == null;

		if (isConstructor)
		{
			System.out.println("Skip constructor: " + CodeHelper.identifier(functionRecord.getName()));
		}
		else
		{
			boolean isStatic = (modifierFlags & TopLevelItemRecord.ASDoc_Static) != 0;
			write(isStatic ? "+" : "-");
			write("(" + CodeHelper.type(returnType) + ")");
			write(CodeHelper.identifier(functionRecord.getName()));
			
			List<DeclRecord> parameters = functionRecord.getParameters();
			int paramIndex = 0;
			for (DeclRecord param : parameters)
			{
				CodeHelper.writeMethodParam(hdr, param);
				CodeHelper.writeMethodParam(impl, param);
				if (++paramIndex < parameters.size())
				{
					write(" ");
				}
			}
			
			writeln(hdr, ";");
			writeln(impl);
			
			writeBlockOpen(impl);
			writeFunctionBody(functionRecord);
			writeBlockClose(impl);
		}
	}

	private void writeFunctionBody(FunctionRecord functionRecord)
	{
		
	}

	void write(String line)
	{
		write(hdr, line);
		write(impl, line);
	}
	
	void writeln(String line)
	{
		writeln(hdr, line);
		writeln(impl, line);
	}
	
	void writeln()
	{
		writeln(hdr);
		writeln(impl);
	}
	
	void writeBlockOpen()
	{
		writeBlockOpen(hdr);
		writeBlockOpen(impl);
	}
	
	void writeBlockClose()
	{
		writeBlockClose(hdr);
		writeBlockClose(impl);
	}
	
	void write(WriteDestination dest, String line)
	{
		dest.write(line);
	}

	void writeln(WriteDestination dest, String line)
	{
		dest.writeln(line);
	}

	void writeln(WriteDestination dest)
	{
		dest.writeln();
	}

	void writeBlockOpen(WriteDestination dest)
	{
		dest.writeln("{");
		dest.incTab();
	}

	void writeBlockClose(WriteDestination dest)
	{
		dest.decTab();
		dest.writeln("}");
	}
}
