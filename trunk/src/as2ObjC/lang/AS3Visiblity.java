package as2ObjC.lang;

public enum AS3Visiblity 
{
	PUBLIC("public"),
	PRIVATE("private"),
	PROTECTED("protected");
	
	private String name;
	
	private AS3Visiblity(String name)
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
}
