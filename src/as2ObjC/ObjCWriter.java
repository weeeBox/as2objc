package as2ObjC;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

import as2ObjC.lang.VisiblityModifier;
import as2ObjC.processors.BracesProcessor;
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

		registerProcessor(new PackageProcessor(this), AS3_exParser.PACKAGE);
		registerProcessor(new ImportProcessor(this), AS3_exParser.IMPORT);
		registerProcessor(new ClassProcessor(this), AS3_exParser.CLASS);

		registerProcessor(new VisiblityProcessor(this), AS3_exParser.PUBLIC, AS3_exParser.PRIVATE, AS3_exParser.PROTECTED);		
	}
	
	private void registerProcessor(TreeElementProcessor processor, Integer... types)
	{
		for (Integer type : types)
		{
			processors.put(type, processor);
		}
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

	/** Called from {@link TreeElementProcessor} when visiblity modificator found */
	public void setVisiblityModifier(VisiblityModifier visiblityModifier)
	{
		this.visiblityModifier = visiblityModifier;
	}

	/** Returns last found visiblity modificator */
	public VisiblityModifier getVisiblityModifier()
	{
		return visiblityModifier;
	}

	/** Called from {@link TreeElementProcessor} when left curly brace is found */
	public void curlyBraceOpened()
	{
		System.out.println("{");
	}
	
	/** Called from {@link TreeElementProcessor} when right curly brace is found */
	public void curlyBraceClosed()
	{
		System.out.println("}");
	}
}
