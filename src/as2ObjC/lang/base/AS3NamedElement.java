package as2ObjC.lang.base;

public abstract class AS3NamedElement
{
	private String name;

	public AS3NamedElement(String name)
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
		return name;
	}
}
