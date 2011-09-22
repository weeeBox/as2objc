package block.processors;

import java.util.regex.Pattern;

import static block.RegexHelp.SPACE;
import static block.RegexHelp.IDENTIFIER;;

public class FunctionCallProcessor extends LineProcessor
{
	private static Pattern pattern = Pattern.compile("new" + SPACE + IDENTIFIER);

	public FunctionCallProcessor()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public String process(String line)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
