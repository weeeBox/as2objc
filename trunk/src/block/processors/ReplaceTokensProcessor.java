package block.processors;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ReplaceTokensProcessor extends LineProcessor
{
	private static Map<String, String> lookup;
	
	static
	{
		lookup = new HashMap<String, String>();
		lookup.put(boundary("String"), "NSString");
		lookup.put(boundary("Number"), "float");
		lookup.put(boundary("Boolean"), "BOOL");
		lookup.put(boundary("Object"), "NSObject");
		lookup.put(boundary("true"), "YES");
		lookup.put(boundary("false"), "NO");
		lookup.put(boundary("this"), "self");
		lookup.put(boundary("null"), "nil");
		
		lookup.put(boundary("trace"), "NSLog");
	}
	
	@Override
	public String process(String line)
	{
		Set<Entry<String, String>> entries = lookup.entrySet();
		for (Entry<String, String> e : entries)
		{
			line = line.replaceAll(e.getKey(), e.getValue());
		}
		return line;
	}
	
	private static String boundary(String str)
	{
		return "\\b" + str + "\\b";
	}

}
