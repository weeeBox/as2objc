package as2ObjC.processors;

import java.util.HashMap;
import java.util.Map;

import as2ObjC.TreeElementProcessor;
import as2ObjC.code.AS3CodeVisitor;

public class ProcessorsRegistry
{
	private static Map<Integer, TreeElementProcessor> processors = new HashMap<Integer, TreeElementProcessor>();
	
	public static void initProcessors(AS3CodeVisitor visitor)
	{
		new PackageProcessor(visitor);
		new ImportProcessor(visitor);
		new ClassProcessor(visitor);
		new VisiblityProcessor(visitor);		
		new BracesProcessor(visitor);
		new VarProcessor(visitor);
		new FunctionProcessor(visitor);
	}
	
	public static void registerProcessor(TreeElementProcessor processor, int... types)
	{
		for (int type : types)
		{
			processors.put(type, processor);
		}
	}

	public static TreeElementProcessor get(int type)
	{
		return processors.get(type);
	}
}
