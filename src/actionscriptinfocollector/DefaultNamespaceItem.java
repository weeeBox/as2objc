package actionscriptinfocollector;

import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.Token;
import actionscriptinfocollector.text.BadLocationException;
import actionscriptinfocollector.text.BadPositionCategoryException;
import actionscriptinfocollector.text.IDocument;

public class DefaultNamespaceItem extends SourceItem implements ISourceElement {
	private TextItem mNamespace;
	
	public void setNamespace(Token t)
	{
		mNamespace=new SimpleTextItem(t);
	}
	
	public void setNamespace(ParserRuleReturnScope t)
	{
		mNamespace=new TreeTextItem(t);
	}
	
	public TextItem getNamespace()
	{
		return mNamespace;
	}

	@Override
	public boolean validateText(IDocument doc) throws BadLocationException
	{
		if (mNamespace!=null && !mNamespace.validateText(doc))
			return false;
		
		return super.validateText(doc);
	}

	@Override
	public void nailDownPositions()
	{
		if (mNamespace!=null)
			mNamespace.nailDownPositions();
		super.nailDownPositions();
	}

	@Override
	public void resetPositions(int delta, IDocument doc) throws BadLocationException, BadPositionCategoryException {
		if (mNamespace!=null)
			mNamespace.resetPositions(delta, doc);
		super.resetPositions(delta, doc);
	}

	@Override
	public void applyDocument(IDocument doc) throws BadLocationException, BadPositionCategoryException {
		if (mNamespace!=null)
			mNamespace.applyDocument(doc);
		super.applyDocument(doc);
	}
	
}
