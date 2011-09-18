package block;

import java.util.regex.Pattern;

public class RegexHelp
{
	public static final String QUOTE = "\"";
	public static final String SPACE = "\\s+";
	public static final String MBSPACE = "\\s?";
	public static final String IDENTIFIER = group("[\\w\\d_]+");
	
	public static String group(String str)
	{
		return "(" + str + ")";
	}
	
	private static Pattern strLiteral = Pattern.compile(group(QUOTE + group(".*") + QUOTE));
}
