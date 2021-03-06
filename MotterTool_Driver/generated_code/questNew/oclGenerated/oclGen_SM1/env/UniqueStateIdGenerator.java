

/**
 * Auto Generated Code for Environment Simulator
 * Date Generated: 3/7/2016
 * Time: 11:9:6
 */ 
 
 
package questNew.oclGenerated.oclGen_SM1.env;
    
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
	
	    key = "-Cla-class";
	    states = new LinkedList<String>();
	   	
	   
	  	stateInfoByComponent.put(key, states);
	   	
	    key = "OpaqueBehavior1-Cla-class";
	    states = new LinkedList<String>();
	   	
	   
	  	stateInfoByComponent.put(key, states);
	   	
	    key = "Operation1-Cla-class";
	    states = new LinkedList<String>();
	   	
	   
	  	stateInfoByComponent.put(key, states);
	   	
	    key = "Property1-Cla-class";
	    states = new LinkedList<String>();
	   	
	   
	  	stateInfoByComponent.put(key, states);
	   	
	    key = "Reception1-Cla-class";
	    states = new LinkedList<String>();
	   	
	   
	  	stateInfoByComponent.put(key, states);
	   	
	    key = "Class1";
	    states = new LinkedList<String>();
	   	
		states.add(Class1-Fin-targetState.getQualifiedStateName());
		
		states.add(Class1Transition1-Sta-sourceState.getQualifiedStateName());
		
		states.add(Class1Transition1-Sta-targetState.getQualifiedStateName());
		
	   
	  	stateInfoByComponent.put(key, states);
	   	  
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
    