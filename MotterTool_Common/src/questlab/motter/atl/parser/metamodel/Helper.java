package questlab.motter.atl.parser.metamodel;
import java.util.ArrayList;
import questlab.motter.atl.metamodel.Library;
import questlab.motter.atl.metamodel.ModuleElement;
import questlab.motter.atl.metamodel.Query;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Abdul Ali
 */
public class Helper extends ModuleElement{
    Library LibraryObj;
    Query QueryObj;
    
    private ArrayList<String> helperBlocks = new ArrayList<String>();
   	private ArrayList<String> arguments = new ArrayList< String>();
   	private String helperName = null;
   	private String returnType = null;
   	private String context = null;
   	
   	public void setHelperName(String name) {
   		this.helperName = name;
   	}
   	
   	public void  setHelperReturnType( String rType) {
   		this.returnType = rType;
   	}
   	
   	public void setHelperAruguments(ArrayList<String> args) {
   		this.arguments = args;
   	}
   	public void setHelperStatements(ArrayList<String> hBlocks){
   		this.helperBlocks = hBlocks;
   	}
   	
   	public void  setHelperContext( String cntxt) {
   		this.context = cntxt;
   	}
   	
   	public ArrayList<String > getBlocks()
   	{
   		return helperBlocks;
   	}
   	
   	public ArrayList<String > getArguments()
   	{
   		return arguments;
   	}
   	
   	public String getType()
   	{
   		return returnType;
   	}
   	
   	public String getName()
   	{
   		return helperName;
   	}
   	public String getContext()
   	{
   		return context;
   	}
}
