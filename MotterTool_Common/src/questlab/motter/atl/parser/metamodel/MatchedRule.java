package questlab.motter.atl.parser.metamodel;
import java.util.ArrayList;


public class MatchedRule extends Rule{

	
	public MatchedRule()
	{
		
	}
	
	public void setName(String name)
	{
		this.name = name;	
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public void setElements(ArrayList<Element> InPatternArray,ArrayList<Element> OutPatternArray)
	{
		InPattern = InPatternArray;
	    this.OutPatternElements = OutPatternArray;
	}
	
	public ArrayList<Element> InPattern;

	public String getName()
	{
		return this.name;
	}
	
	public String getType()
	{
		return this.type;
	}
	
	public ArrayList<Element> getOutPatternsElements()
	{
		return this.OutPatternElements;
	}
	
	public ArrayList <Element> getInPatternElements()
	{
		return this.InPattern;
	}
	
	public void SetActionBlock(ArrayList<String> actionBlockStatements)
	{
	//	this.ActionBlockStatements=actionBlockStatements;
	}
}
