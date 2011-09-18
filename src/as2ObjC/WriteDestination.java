package as2ObjC;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class WriteDestination 
{
	private File file;
	private List<String> lines;

	public WriteDestination(File file) 
	{
		this.file = file;
		lines = new ArrayList<String>();
	}
	
	public void addLine(String line)
	{
		lines.add(line);
	}
	
	public void flush() throws IOException
	{
		PrintStream out = new PrintStream(file);
		for (String line : lines) 
		{
			out.println(line);
		}
		out.close();
	}
}
