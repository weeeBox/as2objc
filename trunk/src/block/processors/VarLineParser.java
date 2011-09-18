package block.processors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VarLineParser extends LineProcessor
{
	private Pattern pattern = Pattern.compile("var" + SPACE + IDENTIFIER + MBSPACE + ":" + MBSPACE + IDENTIFIER);
	
	@Override
	public String process(String line)
	{
		Matcher m = pattern.matcher(line);
		if (m.find())
		{
			return m.replaceFirst(m.group(2) + " " + m.group(1));
		}
		
		return line;
	}

}
