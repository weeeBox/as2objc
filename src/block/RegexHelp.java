package block;

import java.util.regex.Pattern;

public class RegexHelp
{
	public static String QUOTE = "\"";
	
	public static String group(String str)
	{
		return "(" + str + ")";
	}
	
	private static String IDENTIFIER = "([\\w\\d_]+)";
	private static Pattern strLiteral = Pattern.compile(group(QUOTE + group(".*") + QUOTE));
}
