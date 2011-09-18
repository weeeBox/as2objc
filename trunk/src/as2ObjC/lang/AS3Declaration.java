package as2ObjC.lang;

public class AS3Declaration extends AS3NamedElement
{
	private AS3Type type;

	public AS3Declaration(AS3Type type, String name)
	{
		super(name);
		this.type = type;
	}
	
	public AS3Type getType()
	{
		return type;
	}
}
