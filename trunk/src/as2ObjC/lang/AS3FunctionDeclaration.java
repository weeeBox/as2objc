package as2ObjC.lang;

import java.util.List;

import as2ObjC.lang.base.AS3IdentifieredElement;

public class AS3FunctionDeclaration extends AS3IdentifieredElement
{
	private List<AS3Declaration> paramsList;
	private AS3Type returnType;

	public AS3FunctionDeclaration(AS3Identifier name, List<AS3Declaration> paramsList, AS3Type returnType)
	{
		super(name);
		this.paramsList = paramsList;
		this.returnType = returnType;
	}

	public List<AS3Declaration> getParamsList()
	{
		return paramsList;
	}
	
	public AS3Type getReturnType()
	{
		return returnType;
	}
}
