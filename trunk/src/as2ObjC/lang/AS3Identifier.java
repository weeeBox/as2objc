package as2ObjC.lang;

public class AS3Identifier
{
	private String name;

	public AS3Identifier(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}
	
	@Override
	public String toString()
	{
		return getName();
	}
}
