package as2ObjC.code;

import as2ObjC.lang.AS3ConstructorDeclaration;
import as2ObjC.lang.AS3Declaration;
import as2ObjC.lang.AS3FunctionDeclaration;
import as2ObjC.lang.AS3ImportDeclaration;
import as2ObjC.lang.AS3Visiblity;

public interface AS3CodeVisitor
{
	void visitAS3ImportDeclaration(AS3ImportDeclaration declaration);
	
	void visitAS3VisiblityModifier(AS3Visiblity modifier);
	void visitVisiblityModifier(AS3Visiblity visiblityModifier);
	
	void visitCurlyBraceOpen();
	void visitCurlyBraceClosed();
	void visitVarDeclaration(AS3Declaration declaration);
	void visitAS3FunctionDeclaration(AS3FunctionDeclaration declaration);
	void visitAS3ConstructorDeclaration(AS3ConstructorDeclaration declaration);
}
