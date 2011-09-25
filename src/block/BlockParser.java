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

public class BlockParser
{
	private List<LineProcessor> processors;
	
	public BlockParser()
	{
		processors = new ArrayList<LineProcessor>();
		processors.add(new StringLiteralProcessor());
		processors.add(new ArrayLiteralProcessor());
		processors.add(new ArrayProcessor());
		processors.add(new ReplaceTokensProcessor());
		processors.add(new VarLineProcessor());
		processors.add(new FunctionCallProcessor());
		processors.add(new StaticFieldProcessor());
		processors.add(new DoubleToFloat());
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
}
