package block;

import java.util.ArrayList;
import java.util.List;

public class BlockParser
{
	public List<String> parse(String body)
	{
		BlockIterator iter = new BlockIterator(body);
		
		List<String> lines = new ArrayList<String>();
		while (iter.hasNext())
		{
			lines.add(iter.next());
		}
		
		return lines;
	}
	
	private String createLine(String line, int tabs)
	{
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < tabs; i++)
		{
			result.append("\t");
		}
		result.append(line);
		result.append("\n");
		
		return result.toString();
	}
}
