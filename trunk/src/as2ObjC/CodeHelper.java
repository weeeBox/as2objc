package as2ObjC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import actionscriptinfocollector.DeclRecord;
import actionscriptinfocollector.TextItem;

public class CodeHelper
{
	private static Map<String, String> basicTypesLookup;
	private static List<String> flowOperators;
	private static List<String> systemReserved;
	
	static
	{
		basicTypesLookup = new HashMap<String, String>();
		basicTypesLookup.put("void", "void");
		basicTypesLookup.put("int", "int");
		basicTypesLookup.put("float", "float");
		basicTypesLookup.put("double", "double");
		basicTypesLookup.put("Boolean", "BOOL");
		basicTypesLookup.put("Number", "float");
		basicTypesLookup.put("uint", "int");
		basicTypesLookup.put("String", "NSString");
		
		flowOperators = new ArrayList<String>();
		flowOperators.add("if");
		flowOperators.add("while");
		flowOperators.add("for");
		flowOperators.add("switch");
		flowOperators.add("do");
		flowOperators.add("each");
		
		systemReserved = new ArrayList<String>();
		systemReserved.add("NSLog");
		systemReserved.add("NSAssert");
	}
	
	private static String findBasic(String type)
	{
		return basicTypesLookup.get(type);
	}
	
	public static boolean isFlowOperator(String identifier)
	{
		return flowOperators.contains(identifier);
	}
	
	public static boolean isSystemReserved(String identifier)
	{
		return systemReserved.contains(identifier);
	}
	
	public static String literal(String str)
	{
		return "\"" + str + "\"";
	}
	
	public static String type(TextItem item)
	{
		return type(item.getText());
	}

	public static String type(String type)
	{
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
	
	public static void writeDeclaration(WriteDestination dest, DeclRecord record)
	{
		dest.write(CodeHelper.type(record.getType()) + " " + CodeHelper.identifier(record.getName()));
	}
	
	public static void writeMethodParam(WriteDestination dest, DeclRecord record)
	{
		dest.write(":(" + CodeHelper.type(record.getType()) + ")" + CodeHelper.identifier(record.getName()));
	}
}
