package as2ObjC;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

import as2ObjC.lang.VisiblityModifier;
import as2ObjC.processors.ClassProcessor;
import as2ObjC.processors.ImportProcessor;
import as2ObjC.processors.PackageProcessor;
import as2ObjC.tree.TreeHelper;
import as2ObjC.tree.TreeIterator;

import flexprettyprint.handlers.AS3_exParser;

public class ObjCWriter
{
	private WriteDestination header;
	private WriteDestination implementation;

	private String filename;
	private File outputDir;

	private VisiblityModifier visiblityModifier;

	private Map<Integer, TreeElementProcessor> processors;

	public ObjCWriter(String filename, File outputDir)
	{
		this.filename = filename;
		this.outputDir = outputDir;

		initProcessors();
		initData();
	}

	private void initProcessors()
	{
		processors = new HashMap<Integer, TreeElementProcessor>();

		processors.put(AS3_exParser.PACKAGE, new PackageProcessor(this));
		processors.put(AS3_exParser.IMPORT, new ImportProcessor(this));
		processors.put(AS3_exParser.CLASS, new ClassProcessor(this));

		VisiblityProcessor visiblityProcessor = new VisiblityProcessor(this);
		processors.put(AS3_exParser.PUBLIC, visiblityProcessor);
		processors.put(AS3_exParser.PRIVATE, visiblityProcessor);
		processors.put(AS3_exParser.PROTECTED, visiblityProcessor);
	}

	private void initData()
	{
		setVisiblityModifier(VisiblityModifier.PUBLIC);
	}

	public void write(CommonTree tree)
	{
		TreeIterator iterator = new TreeIterator(tree);		
		while (iterator.hasNext())
		{
			Tree child = iterator.next();
			int type = child.getType();

			TreeElementProcessor processor = processors.get(type);
			if (processor != null)
			{
				processor.process(iterator, child);
			}
			System.out.println(TreeHelper.getTypeName(type));
		}
	}

	public void setVisiblityModifier(VisiblityModifier visiblityModifier)
	{
		this.visiblityModifier = visiblityModifier;
	}

	public VisiblityModifier getVisiblityModifier()
	{
		return visiblityModifier;
	}
}
