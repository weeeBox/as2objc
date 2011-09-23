package block.processors;

import static block.RegexHelp.DOT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoubleToFloat extends LineProcessor
{
	private static Pattern pattern = Pattern.compile("[\\-+\\d]+" + DOT + "[\\d]+");
	
	@Override
	public String process(String line)
	{
		String temp = line;
		
		Matcher matcher = pattern.matcher(line);
		while (matcher.find())
		{
			String number = matcher.group();
			temp = temp.replace(number, number + "f");
		}
		return temp;
	}
}
