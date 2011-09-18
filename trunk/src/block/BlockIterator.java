package block;

import java.util.ArrayList;
import java.util.List;

public class BlockIterator
{
	private List<String> lines;
	private int position;
	
	public BlockIterator(String code)
	{
		lines = spliteLines(code);
		position = -1;
	}
	
	public boolean hasNext()
	{
		return position < lines.size() - 1;
	}
	
	public String next()
	{
		return lines.get(++position);
	}

	private List<String> spliteLines(String code)
	{
		List<String> lines = new ArrayList<String>();
		
		StringBuilder lineBuffer = new StringBuilder();
		for (int charIndex = 0; charIndex < code.length(); charIndex++)
		{
			char chr = code.charAt(charIndex);
			if (chr == '\n' || chr == '\r' || chr == '\t')
			{
				continue;
			}

			if (chr == ';')
			{
				lineBuffer.append(chr);
			}
			
			if (chr == ';' || chr == '{' || chr == '}')
			{
				String line = lineBuffer.toString().trim();
				lineBuffer.setLength(0);
				if (line.length() > 0)
				{
					lines.add(line);
				}
				if (chr != ';')
				{
					lines.add("" + chr);
				}
			}
			else
			{
				lineBuffer.append(chr);
			}
			
		}		
		
		return lines;
	}
}
