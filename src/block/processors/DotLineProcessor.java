package block.processors;

import static block.RegexHelp.ANY;
import static block.RegexHelp.DOT;
import static block.RegexHelp.IDENTIFIER;
import static block.RegexHelp.LPAR;
import static block.RegexHelp.MBSPACE;
import static block.RegexHelp.RPAR;
import static block.RegexHelp.SPACE;
import static block.RegexHelp.mb;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import as2ObjC.CodeHelper;

public class DotLineProcessor extends LineProcessor
{
	private static final String MBNEW = mb("new" + SPACE);
	
	private Pattern pattern = Pattern.compile(mb(DOT) + MBNEW + IDENTIFIER + MBSPACE + LPAR + ANY + RPAR);

	private static final int GR_DOT = 1;
	private static final int GR_NEW = 2;
	private static final int GR_IDENTIFIER = 3;
	private static final int GR_ARGS = 4;

	private static final String separators = "()[]\b\f\t\n\r|&^+-*/!%=~\\?<>:;{}&@#`\"";
	
	@Override
	public String process(String line)
	{
		String temp = line;
		
		Matcher matcher = pattern.matcher(temp); 
		if (matcher.find())
		{
			line = processLine(line, matcher);
		}
		
		return line;
	}

	private String processLine(String line, Matcher matcher)
	{
		String identifier = matcher.group(GR_IDENTIFIER);
		if (CodeHelper.isFlowOperator(identifier))
		{
			return line;
		}

		boolean isConstructor = matcher.group(GR_NEW) != null;
		boolean hasCallTarget = matcher.group(GR_DOT) != null;
		
		String argsStr = LineProcHelp.parenthesisVal("(" + matcher.group(GR_ARGS) + ")", 0);
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
		
		if (isConstructor)
		{
			return matcher.replaceAll(createCall("[" + identifier + " alloc]", "init", argsBuf.toString()));
		}
		else if (hasCallTarget)
		{
			int targetStart = findTargetStart(line, matcher.start() - 1);
			String target = line.substring(targetStart, matcher.start()).trim();
			
			String newCode = createCall(target, identifier, argsBuf.toString());
			String oldCode = line.substring(targetStart, matcher.end());
			
			return line.replace(oldCode, newCode);
		}
		else
		{
			return matcher.replaceAll(createCall("self", identifier, argsBuf.toString()));
		}
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
