package block.processors;

import block.RegexHelp;

public abstract class LineProcessor
{
	protected static final String QUOTE = RegexHelp.QUOTE;
	protected static final String SPACE = RegexHelp.SPACE;
	protected static final String MBSPACE = RegexHelp.MBSPACE;
	protected static final String IDENTIFIER = RegexHelp.IDENTIFIER;
	
	protected static final String group(String line) { return RegexHelp.group(line); }
	
	public abstract String process(String line);
}
