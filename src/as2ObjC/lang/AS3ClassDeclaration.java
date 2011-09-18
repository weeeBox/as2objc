package as2ObjC.lang;

import java.util.ArrayList;
import java.util.List;

import as2ObjC.lang.base.AS3IdentifieredElement;

public class AS3ClassDeclaration extends AS3IdentifieredElement
{
	private AS3Type extendsType;
	private List<AS3Type> implementsTypeList;
	
	public AS3ClassDeclaration(AS3Identifier name)
	{
		super(name);
		implementsTypeList = new ArrayList<AS3Type>();
	}
	
	public void setExtendsType(AS3Type extendsType)
	{
		this.extendsType = extendsType;
	}
	
	public AS3Type getExtendsType()
	{
		return extendsType;
	}
	
	public boolean isExtendsType()
	{
		return extendsType != null;
	}
	
	public void addImplementsType(AS3Type type)
	{
		implementsTypeList.add(type);
	}
	
	public List<AS3Type> getImplementsTypeList()
	{
		return implementsTypeList;
	}
}
