package as2ObjC.lang;

public enum VisiblityModifier 
{
	PUBLIC("public"),
	PRIVATE("private"),
	PROTECTED("protected");
	
	private String name;
	
	private VisiblityModifier(String name)
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
}
