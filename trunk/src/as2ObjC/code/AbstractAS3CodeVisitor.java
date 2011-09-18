package as2ObjC.code;

import as2ObjC.TreeElementProcessor;
import as2ObjC.lang.AS3IdentifierElement;
import as2ObjC.lang.AS3Visiblity;

public abstract class AbstractAS3CodeVisitor implements AS3CodeVisitor
{
	/** Contains opened/closed curly braces ratio */
	private int curlyBracesCounter;
	
	/** Contains last found visiblity modifier */
	private AS3Visiblity visiblityModifier;
	
	/** Called from {@link TreeElementProcessor} when visiblity modificator found */
	public void setVisiblityModifier(AS3Visiblity visiblityModifier)
	{
		this.visiblityModifier = visiblityModifier;
	}

	/** Returns last found visiblity modificator */
	public AS3Visiblity getVisiblityModifier()
	{
		return visiblityModifier;
	}

	@Override
	public void visitCurlyBraceOpen()
	{
		curlyBracesCounter++;
	}
	
	@Override
	public void visitCurlyBraceClosed()
	{
		curlyBracesCounter--;
	}
	
	/** Return current opened/closed curly braces ratio */
	public int getCurlyBracesCounter()
	{
		return curlyBracesCounter;
	}
	
	@Override
	public void visitAS3VisiblityModifier(AS3Visiblity modifier)
	{
		
	}

	@Override
	public void visitVisiblityModifier(AS3Visiblity visiblityModifier)
	{
		
	}

	@Override
	public void visitAS3ImportDeclaration(AS3IdentifierElement declaration)
	{
		
	}
}
