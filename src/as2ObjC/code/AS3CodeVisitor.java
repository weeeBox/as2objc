package as2ObjC.code;

import as2ObjC.lang.AS3ImportDeclaration;
import as2ObjC.lang.AS3Visiblity;

public interface AS3CodeVisitor
{
	void visitAS3VisiblityModifier(AS3Visiblity modifier);
	void visitVisiblityModifier(AS3Visiblity visiblityModifier);
	
	void visitCurlyBraceOpen();
	void visitCurlyBraceClosed();
	void visitAS3ImportDeclaration(AS3ImportDeclaration declaration);
}
