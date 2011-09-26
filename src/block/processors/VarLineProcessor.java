package block.processors;

import static block.RegexHelp.IDENTIFIER;
import static block.RegexHelp.MBSPACE;
import static block.RegexHelp.SPACE;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import as2ObjC.CodeHelper;

public class VarLineProcessor extends LineProcessor
{
	private Pattern pattern = Pattern.compile("(var|const)" + SPACE + IDENTIFIER + MBSPACE + ":" + MBSPACE + IDENTIFIER);
	
	@Override
	public String process(String line)
	{
		Matcher m;
		if ((m = pattern.matcher(line)).find())
		{
			String type = m.group(3);
			String name = m.group(2);
			
			assert type != null : line;
			assert name != null : line;
			
			line = m.replaceFirst(CodeHelper.type(type) + " " + name);
		}
		
		return line;
	}

}
