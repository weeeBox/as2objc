package as2ObjC.code;

import as2ObjC.TreeElementProcessor;
import as2ObjC.lang.AS3ConstructorDeclaration;
import as2ObjC.lang.AS3Declaration;
import as2ObjC.lang.AS3FunctionDeclaration;
import as2ObjC.lang.AS3ImportDeclaration;
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
	public void visitAS3ImportDeclaration(AS3ImportDeclaration declaration)
	{
		
	}
	
	@Override
	public void visitVarDeclaration(AS3Declaration declaration)
	{
		
	}
	
	@Override
	public void visitAS3ConstructorDeclaration(AS3ConstructorDeclaration declaration)
	{
		
	}
	
	@Override
	public void visitAS3FunctionDeclaration(AS3FunctionDeclaration declaration)
	{
		
	}
}
