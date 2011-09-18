package as2ObjC;

import java.io.File;

import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

import as2ObjC.code.AbstractAS3CodeVisitor;
import as2ObjC.lang.AS3ImportDeclaration;
import as2ObjC.lang.AS3Visiblity;
import as2ObjC.processors.ProcessorsRegistry;
import as2ObjC.tree.TreeHelper;
import as2ObjC.tree.TreeIterator;

public class ObjCWriter extends AbstractAS3CodeVisitor
{
	private WriteDestination header;
	private WriteDestination implementation;

	private String filename;
	private File outputDir;

	public ObjCWriter(String filename, File outputDir)
	{
		this.filename = filename;
		this.outputDir = outputDir;

		initData();
	}

	private void initData()
	{
		ProcessorsRegistry.initProcessors(this);
		setVisiblityModifier(AS3Visiblity.PUBLIC);
	}

	public void write(CommonTree tree)
	{
		TreeIterator iterator = new TreeIterator(tree);		
		while (iterator.hasNext())
		{
			Tree child = iterator.next();
			int type = child.getType();

			TreeElementProcessor processor = ProcessorsRegistry.get(type);
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
	
	public void log(String message)
	{
		for (int i = 0; i < getCurlyBracesCounter(); i++)
		{
			System.out.print('\t');
		}
		System.out.println(message);
	}
}
