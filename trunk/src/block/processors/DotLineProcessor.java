package block.processors;

import static block.RegexHelp.ANY;
import static block.RegexHelp.DOT;
import static block.RegexHelp.IDENTIFIER;
import static block.RegexHelp.LPAR;
import static block.RegexHelp.MBSPACE;
import static block.RegexHelp.RPAR;
import static block.RegexHelp.SPACE;

import static block.RegexHelp.mb;
import static block.RegexHelp.group;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import as2ObjC.CodeHelper;

public class DotLineProcessor extends LineProcessor
{
	private static final String MBNEW = mb("new" + SPACE);
	
	private Pattern pattern = Pattern.compile(group(mb(DOT) + MBNEW + IDENTIFIER + MBSPACE + LPAR));

	private static final int GR_DOT = 2;
	private static final int GR_NEW = 3;
	private static final int GR_IDENTIFIER = 4;

	private static final String separators = "()[]\b\f\t\n\r|&^+-*/!%=~\\?<>:;{}&@#`\"";
	
	@Override
	public String process(String line)
	{
		
		Matcher matcher = pattern.matcher(line); 
		while (matcher.find())
		{
			String identifier = matcher.group(GR_IDENTIFIER);
			if (CodeHelper.isFlowOperator(identifier))
			{
				continue;
			}

			boolean isConstructor = matcher.group(GR_NEW) != null;
			boolean hasCallTarget = matcher.group(GR_DOT) != null;
			
			String argsStr = LineProcHelp.parenthesisVal(line, matcher.end() - 1);
			
			StringBuilder argsBuf = new StringBuilder();
			if (argsStr.length() > 0)
			{
				List<String> args = LineProcHelp.splitArgs(argsStr, ',');
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
			}
			
			String oldCode;
			String newCode;
			
			if (isConstructor)
			{
				oldCode = line.substring(matcher.start(), matcher.end() + argsStr.length() + 1);
				newCode = createCall("[" + identifier + " alloc]", "init", argsBuf.toString());
			}
			else if (hasCallTarget)
			{
				int targetStart = findTargetStart(line, matcher.start() - 1);
				String target = line.substring(targetStart, matcher.start()).trim();
				
				newCode = createCall(target, identifier, argsBuf.toString());
				oldCode = line.substring(targetStart, matcher.end() + argsStr.length() + 1);
			}
			else
			{
				newCode = createCall("self", identifier, argsBuf.toString());
				oldCode = line.substring(matcher.start(), matcher.end() + argsStr.length() + 1);
			}
			line = line.replace(oldCode, newCode);
			matcher = pattern.matcher(line);
		}
		
		return line;
	}
	
	private String createCall(String target, String message, String args)
	{
		String newCode = "[" + target + " " + message + args + "]";
		return newCode;
	}

	private int findTargetStart(String line, int endPos)
	{
		int lastNonSpaceIndex = endPos;
		for (int i = endPos; i >= 0; --i)
		{
			char chr = line.charAt(i);
			
			if (separators.indexOf(chr) != -1)
			{
				return lastNonSpaceIndex;
			}
			
			if (chr != ' ')
			{
				lastNonSpaceIndex = i;
			}
		}
		
		return 0;
	}

}
