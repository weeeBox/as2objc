package block.processors;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static block.RegexHelp.SPACE;
import static block.RegexHelp.IDENTIFIER;;

public class ConstructorLineProcessor extends LineProcessor
{
	private static Pattern pattern = Pattern.compile("new" + SPACE + IDENTIFIER);
	
	@Override
	public String process(String line)
	{
		Matcher m;
		while ((m = pattern.matcher(line)).find())
		{
			String callString;
			
			String argsStr = LineProcHelp.parenthesisVal(line, m.end());
			if (argsStr.length() > 0)
			{
				List<String> args = LineProcHelp.splitArgs(argsStr, ',');
				StringBuilder argsBuf = new StringBuilder();
				int argIndex = 0;
				for (String arg : args)
				{
					argsBuf.append(":");
					argsBuf.append(arg);
					if (++argIndex < args.size())
					{
						argsBuf.append(" ");
					}
				}
				
				callString = String.format("[[%s alloc] init%s]", m.group(1), argsBuf);
			}
			else
			{
				callString = String.format("[[%s alloc] init]", m.group(1));
			}
				
			
			line = line.substring(0, m.start()) + callString + line.substring(m.end() + argsStr.length() + 2);
		}
		
		return line;
	}
}
