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
import as2ObjC.processors.VarProcessor;
import as2ObjC.tree.TreeHelper;
import as2ObjC.tree.TreeIterator;

public class ObjCWriter
{
	private WriteDestination header;
	private WriteDestination implementation;

	private String filename;
	private File outputDir;

	/** Contains last found visiblity modifier */
	private VisiblityModifier visiblityModifier;

	/** Contains opened/closed curly braces ratio */
	private int curlyBracesCounter;

	private Map<Integer, TreeElementProcessor> processors = new HashMap<Integer, TreeElementProcessor>();

	public ObjCWriter(String filename, File outputDir)
	{
		this.filename = filename;
		this.outputDir = outputDir;

		initProcessors();
		initData();
	}

	private void initProcessors()
	{
		new PackageProcessor(this);
		new ImportProcessor(this);
		new ClassProcessor(this);

		new VisiblityProcessor(this);		
		new BracesProcessor(this);
		new VarProcessor(this);
	}
	
	public void registerProcessor(TreeElementProcessor processor, int... types)
	{
		for (int type : types)
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
			else
			{
				log("AS3_exParser." + TreeHelper.getTypeName(type));
			}
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
		log("{");
		curlyBracesCounter++;
	}
	
	/** Called from {@link TreeElementProcessor} when right curly brace is found */
	public void curlyBraceClosed()
	{
		curlyBracesCounter--;
		log("}");
	}
	
	public void log(String message)
	{
		for (int i = 0; i < curlyBracesCounter; i++)
		{
			System.out.print('\t');
		}
		System.out.println(message);
	}
}
