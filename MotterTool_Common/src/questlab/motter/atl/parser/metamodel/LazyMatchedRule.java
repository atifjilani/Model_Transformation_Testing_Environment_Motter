package questlab.motter.atl.parser.metamodel;
import java.io.IOException;
import java.util.ArrayList;


public class LazyMatchedRule extends Rule{

	
	public LazyMatchedRule()
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
	
	void print() throws IOException
	{
//		int i=0,k=0;
//		String newLine = System.getProperty("line.separator");
//		FileWriter Rule = new FileWriter("../LazyMatchedRules.txt");
		
	}
	
	public void setElements(ArrayList<Element> InPatternArray,ArrayList<Element> OutPatternArray)
	{
		InPatternElements = InPatternArray;
	    this.OutPatternElements = OutPatternArray;
	}

	//public ArrayList<Element> InPatternElements;
	
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
		return this.InPatternElements;
	}
	
	public void SetActionBlock(ArrayList<String> actionBlockStatements)
	{
		//this.ActionBlockStatements = actionBlockStatements;
	}
}
