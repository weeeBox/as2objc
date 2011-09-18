package as2ObjC;

import java.util.HashMap;
import java.util.Map;

import actionscriptinfocollector.TextItem;

public class CodeHelper
{
	private static Map<String, String> basicTypesLookup;
	
	static
	{
		basicTypesLookup = new HashMap<String, String>();
		basicTypesLookup.put("Boolean", "BOOL");
	}
	
	private static String findBasic(String type)
	{
		return basicTypesLookup.get(type);
	}
	
	public static String literal(String str)
	{
		return "\"" + str + "\"";
	}
	
	public static String type(TextItem item)
	{
		String type = item.getText();
		String basicType = findBasic(type);
		return basicType == null ? type + "*" : basicType;
	}
	
	public static String identifier(TextItem item)
	{
		return item.getText();
	}
	
	public static void writeImport(WriteDestination dest, String name)
	{
		dest.writeln("#import " + literal(name + ".h"));
	}
}
