package actionscriptinfocollector.text;

@SuppressWarnings("serial")
public class BadLocationException extends Exception
{
	public BadLocationException(int offset, int length)
	{
		super("Bad location: offset=" + offset + " length=" + length);
	}

	public BadLocationException(int offset)
	{
		super("Bad location: offset=" + offset);
	}
}
