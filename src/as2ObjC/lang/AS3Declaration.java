package as2ObjC.lang;

import as2ObjC.lang.base.AS3IdentifieredElement;


public class AS3Declaration extends AS3IdentifieredElement
{
	private AS3Type type;

	public AS3Declaration(AS3Type type, AS3Identifier name)
	{
		super(name);
		this.type = type;
	}
	
	public AS3Type getType()
	{
		return type;
	}
}
