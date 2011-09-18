package actionscriptinfocollector;

import java.util.ArrayList;
import java.util.List;

public class CodeBlock extends SourceItem
{
	private List<SourceItem> items;
	
	public CodeBlock()
	{
		items = new ArrayList<SourceItem>();
	}
}
