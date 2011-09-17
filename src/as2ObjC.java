import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import flexprettyprint.handlers.ASPrettyPrinter;

public class as2ObjC 
{
	public static void main(String[] args) throws Exception 
	{
		File f = new File(args[0]);
		
		BufferedReader br=new BufferedReader(new FileReader(f));
		StringBuffer buffer=new StringBuffer();
		while (true)
		{
			String d=br.readLine();
			if (d==null)
				break;
			buffer.append(d);
			buffer.append("\n");
		}
		String data=buffer.toString();
		try
		{
			String result=null;
			result=new ASPrettyPrinter(true, data).print(0);
			System.out.println("*****************************************************");
			System.out.println(result);
		}
		catch (Exception e)
		{
			System.out.println("Error pretty printing");
			e.printStackTrace();
		}
    }
}
