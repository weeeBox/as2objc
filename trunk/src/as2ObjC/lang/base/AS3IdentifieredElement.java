package as2ObjC.lang.base;

import as2ObjC.lang.AS3Identifier;

public abstract class AS3IdentifieredElement
{
	private AS3Identifier identifier;

	public AS3IdentifieredElement(AS3Identifier identifier)
	{
		this.identifier = identifier;
	}

	public AS3Identifier getIdentifier()
	{
		return identifier;
	}
}