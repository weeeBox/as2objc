package block.processors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static block.RegexHelp.IDENTIFIER;
import static block.RegexHelp.TIDENTIFIER;
import static block.RegexHelp.DOT;
import static block.RegexHelp.MBSPACE;

public class StaticFieldProcessor extends LineProcessor
{
	private static Pattern pattern = Pattern.compile(TIDENTIFIER + MBSPACE + DOT + MBSPACE + IDENTIFIER);
	
	@Override
	public String process(String line)
	{
		Matcher matcher;
		while ((matcher = pattern.matcher(line)).find())
		{
			String type = matcher.group(1);
			String field = matcher.group(2);
			line = matcher.replaceFirst("[" + type + " " + field + "]");
		}
		return line;
	}

}
