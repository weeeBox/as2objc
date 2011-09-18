package actionscriptinfocollector.text;

public interface IDocument
{
	/** Returns this document's complete text. */
	public String get();

	/** Returns this document's text for the specified range. */
	public String get(int offset, int length) throws BadLocationException;

	/** Returns the character at the given document offset in this document. */
	char getChar(int offset) throws BadLocationException;

	/**
	 * Returns the number of the line at which the character of the specified
	 * position is located. The first line has the line number 0. A new line
	 * starts directly after a line delimiter. (offset == document length) is a
	 * valid argument although there is no corresponding character.
	 */
	int getLineOfOffset(int offset) throws BadLocationException;

	/** Determines the offset of the first character of the given line. */
	int getLineOffset(int line) throws BadLocationException;

	/** Returns the number of characters in this document. */
	public int getLength();

}
