package block;

import java.util.ArrayList;
import java.util.List;

import actionscriptinfocollector.ASCollector;
import actionscriptinfocollector.ClassRecord;
import actionscriptinfocollector.DeclRecord;
import actionscriptinfocollector.PropertyLine;
import block.processors.ArrayLiteralProcessor;
import block.processors.ArrayProcessor;
import block.processors.DoubleToFloat;
import block.processors.FieldVarProcessor;
import block.processors.FunctionCallProcessor;
import block.processors.LineProcessor;

public class ClassParser 
{
	private FieldVarProcessor varProcessor;
	
	private List<LineProcessor> processors;

	private List<ASCollector> collectors;
	
	public ClassParser(List<ASCollector> collectors)
	{
		this.collectors = collectors;
		
		processors = new ArrayList<LineProcessor>();
		processors.add(new ArrayLiteralProcessor());
		processors.add(new ArrayProcessor());
		processors.add(new FunctionCallProcessor());
		processors.add(new DoubleToFloat());
		processors.add(varProcessor = new FieldVarProcessor());
	}
	
	public void parse(String code)
	{
		BlockIterator iterator = new BlockIterator(code);
		
		int counter = 0;
		while (iterator.hasNext()) 
		{
			String line = iterator.next();
			counter += countParentnessis(line);
			if (counter == 2)
			{
				for (LineProcessor processor : processors) 
				{
					processor.process(line);
				}
			}
		}
		
		processInitializer();
	}

	private int countParentnessis(String line)
	{
		int counter = 0;
		for (int i = 0; i < line.length(); i++) 
		{
			char chr = line.charAt(i);
			if (chr == '{')
				counter++;
			else if (chr == '}')
				counter--;
		}
		return counter;
	}
	
	private void processInitializer() 
	{
		for (ASCollector collector : collectors) 
		{
			List<ClassRecord> classRecords = collector.getClassRecords();
			for (ClassRecord classRecord : classRecords) 
			{
				List<PropertyLine> propertiesLines = classRecord.getProperties();
				
				if (propertiesLines.size() > 0)
				{
					for (PropertyLine propertyLine : propertiesLines)
					{
						List<DeclRecord> props = propertyLine.getProperties();
						for (DeclRecord declRecord : props)
						{
							String fieldName = declRecord.getName().getText();
							String initializer = findInitializer(fieldName);
							declRecord.setInitializer(initializer);
						}
					}
				}
			}
		}
	}
	
	private String findInitializer(String field)
	{
		List<FieldDeclaration> vars = varProcessor.getVariables();
		for (FieldDeclaration var : vars) 
		{
			if (var.getName().equals(field))
				return var.getInitializer();
		}
		return null;
	}
}
