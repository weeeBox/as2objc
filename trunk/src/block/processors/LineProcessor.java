package block.processors;

import block.RegexHelp;

public abstract class LineProcessor
{
	protected static final String group(String line) { return RegexHelp.group(line); }
	
	public abstract String process(String line);
}
