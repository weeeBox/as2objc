package block.processors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static block.RegexHelp.SPACE;
import static block.RegexHelp.MBSPACE;
import static block.RegexHelp.IDENTIFIER;;

public class VarProcessor extends LineProcessor
{
	private Pattern pattern = Pattern.compile("var" + SPACE + IDENTIFIER + MBSPACE + "\\:" + MBSPACE + IDENTIFIER);
	
	@Override
	public String process(String line)
	{
		Matcher matcher = pattern.matcher(line);
		if (matcher.find())
		{
			System.out.println(matcher.group());
		}
		return line;
	}
}
