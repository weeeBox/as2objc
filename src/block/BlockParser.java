package block;

import java.util.ArrayList;
import java.util.List;

import block.processors.ArrayLiteralProcessor;
import block.processors.ArrayProcessor;
import block.processors.DoubleToFloat;
import block.processors.FunctionCallProcessor;
import block.processors.LineProcessor;
import block.processors.ReplaceTokensProcessor;
import block.processors.StaticFieldProcessor;
import block.processors.StringLiteralProcessor;
import block.processors.VarLineProcessor;
import block.processors.XmlFakeProcessor;

public class BlockParser
{
	private List<LineProcessor> processors;
	
	private FunctionCallProcessor functionCallProcessor;

	private VarLineProcessor varLineProcessor;
	
	public BlockParser()
	{
		varLineProcessor = new VarLineProcessor();
		functionCallProcessor = new FunctionCallProcessor();
		
		processors = new ArrayList<LineProcessor>();
		processors.add(new StringLiteralProcessor());
		processors.add(new ArrayLiteralProcessor());
		processors.add(new ArrayProcessor());
		processors.add(varLineProcessor);
		processors.add(functionCallProcessor);
		processors.add(new ReplaceTokensProcessor());
		processors.add(new StaticFieldProcessor());
		processors.add(new DoubleToFloat());
		processors.add(new XmlFakeProcessor());
	}
	
	public List<String> parse(String body)
	{
		BlockIterator iter = new BlockIterator(body);
		
		List<String> lines = new ArrayList<String>();
		while (iter.hasNext())
		{
			String line = iter.next();
			lines.add(process(line));
		}
		
		return lines;
	}

	private String process(String line)
	{
		for (LineProcessor proc : processors)
		{
			line = proc.process(line);
		}
		
		return line;
	}
	
	public List<String> getTypes()
	{
		List<String> types = new ArrayList<String>();
		
		types.addAll(functionCallProcessor.getTypes());
		
		List<String> varTypes = varLineProcessor.getTypes();
		for (String type : varTypes) 
		{
			if (!types.contains(type))
			{
				types.add(type);
			}
		}
		
		return types;
	}
}
