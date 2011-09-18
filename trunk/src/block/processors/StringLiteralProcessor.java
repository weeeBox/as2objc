package block.processors;

public class StringLiteralProcessor extends LineProcessor
{
	@Override
	public String process(String line)
	{
		if (line.contains(QUOTE))
		{
			boolean insideString = false;
			StringBuilder result = new StringBuilder(line);
			char prevChr = 0;
			for (int i = 0; i < result.length(); i++)
			{
				char chr = result.charAt(i);
				if (chr == '"' && prevChr != '\\')
				{
					insideString = !insideString;
					if (insideString)
					{
						result.insert(i, "@");
						i++;
					}
				}
				prevChr = chr;
			}
			
			return result.toString();
		}
		return line;
	}

}
