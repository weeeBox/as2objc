package actionscriptinfocollector.text;

public class Position
{
	private int offset;
	private int length;

	public Position(int offset, int length)
	{
		this.offset = offset;
		this.length = length;
	}
	
	public int getOffset()
	{
		return offset;
	}
	
	public int getLength()
	{
		return length;
	}
}
