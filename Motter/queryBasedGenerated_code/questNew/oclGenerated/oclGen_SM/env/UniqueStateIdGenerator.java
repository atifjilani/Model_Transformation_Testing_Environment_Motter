

/**
 * Auto Generated Code for Environment Simulator
 * Date Generated: 29/9/2016
 * Time: 21:29:2
 */ 
 
 
package questNew.oclGenerated.oclGen_SM.env;
    
import java.util.Hashtable;
import java.util.List;
import java.util.LinkedList;
    	
import simula.embt.commons.EnvironmentComponent;
import simula.embt.commons.HeuristicTrace;
    	
    
public class UniqueStateIdGenerator{
    
	private Hashtable<String, List<String>> stateInfoByComponent;
	// key: component name, value:List of unique names of states for the component  
    
    	
    
	public UniqueStateIdGenerator()
	{
	  	stateInfoByComponent = new Hashtable<String, List<String>>();
		initialize();    	  	
	}  
    	
	public void initialize()
	{
	  	String key = "";
	 	LinkedList states;
	  
	}
    
    public void generateIds(EnvironmentComponent[] components)
	{
		LinkedList<String> stateIds = new LinkedList<String>();
		
		for(EnvironmentComponent component:components)
		{
			String key = component.getComponentName();
			int instanceId = component.getInstanceId();
			LinkedList<String> states = (LinkedList<String>)stateInfoByComponent.get(key);
			
			for(String state:states)
			{
				String id = ""+instanceId + "-" + state;
				stateIds.add(id);
				
			}
		}
		HeuristicTrace.getTrace().initDataStructuresForStateTransitions(stateIds.toArray(new String[]{}));
	}
       
}
    