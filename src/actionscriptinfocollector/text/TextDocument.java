package actionscriptinfocollector.text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextDocument implements IDocument
{
	private String text;
	private Position[] positions;
	
	private TextDocument(String text, Position[] positions)
	{
		this.text = text;
		this.positions = positions;
	}
	
	@Override
	public String get()
	{
		return text;
	}

	@Override
	public String get(int offset, int length) throws BadLocationException
	{
		if (offset < 0 || offset + length > text.length())
			throw new BadLocationException(offset, length);
		
		return text.substring(offset, offset + length);
	}

	@Override
	public char getChar(int offset) throws BadLocationException
	{
		if (offset < 0 || offset > text.length() - 1)
			throw new BadLocationException(offset);
		
		return text.charAt(offset);
	}

	@Override
	public int getLineOfOffset(int offset) throws BadLocationException
	{
		if (offset <= 0 || offset > text.length() - 1)
			throw new BadLocationException(offset);
		
		for (int lineIndex = 0; lineIndex < positions.length; lineIndex++)
		{
			Position pos = positions[lineIndex];
			int lineStart = pos.getOffset();
			int lineEnd = lineStart + pos.getLength();
			if (offset >= lineStart && offset < lineEnd)
			{
				return lineIndex;
			}
		}
		return -1;
	}

	@Override
	public int getLineOffset(int line) throws BadLocationException
	{
		return positions[line].getOffset();
	}

	@Override
	public int getLength()
	{
		return text.length();
	}
	
	public static TextDocument read(File file) throws IOException
	{
		List<String> lines = readLines(file);
		Position[] positions = new Position[lines.size()];
		
		int lineStartOffset = 0;
		int lineIndex = 0;
		StringBuilder textBuffer = new StringBuilder();
		for (String line : lines)
		{
			int lineLength = line.length();
			positions[lineIndex] = new Position(lineStartOffset, lineLength);
			lineStartOffset += lineLength;
			++lineIndex;
			
			textBuffer.append(line);
			textBuffer.append('\n');
		}
		
		String text = textBuffer.toString();
		return new TextDocument(text, positions);
	}
	
	private static List<String> readLines(File file) throws IOException
	{
		BufferedReader reader = null;
		try
		{
			reader = new BufferedReader(new FileReader(file));
			List<String> lines = new ArrayList<String>();
			
			String line;
			while ((line = reader.readLine()) != null)
			{
				lines.add(line);
			}
			
			return lines;
		}
		finally
		{
			if (reader != null)
				reader.close();
		}
	}
}
