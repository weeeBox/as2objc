package as2ObjC.lang;

import java.util.List;

public class AS3ConstructorDeclaration extends AS3FunctionDeclaration
{

	public AS3ConstructorDeclaration(AS3Identifier name, List<AS3Declaration> paramsList)
	{
		super(name, paramsList, null);
	}

}
