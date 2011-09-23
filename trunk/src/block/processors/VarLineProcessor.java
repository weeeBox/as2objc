package block.processors;

import static block.RegexHelp.IDENTIFIER;
import static block.RegexHelp.MBSPACE;
import static block.RegexHelp.SPACE;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import as2ObjC.CodeHelper;

public class VarLineProcessor extends LineProcessor
{
	private Pattern pattern = Pattern.compile("var" + SPACE + IDENTIFIER + MBSPACE + ":" + MBSPACE + IDENTIFIER);
	
	@Override
	public String process(String line)
	{
		Matcher m;
		if ((m = pattern.matcher(line)).find())
		{
			line = m.replaceFirst(CodeHelper.type(m.group(2)) + " " + m.group(1));
		}
		
		return line;
	}

}
