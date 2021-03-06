

/**
 * Auto Generated Code for Environment Simulator
 * Date Generated: 18/5/2015
 * Time: 9:59:50
 */ 
 
 
package simula.artificial_problems.booting_new.env;
    
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
	
	    key = "Sensor";
	    states = new LinkedList<String>();
	   	
		states.add(SensorBootingState.getQualifiedStateName());
		
		states.add(SensorWaitingState.getQualifiedStateName());
		
		states.add(SensorConnectedState.getQualifiedStateName());
		
		states.add(SensorEnvironmentErrorState.getQualifiedStateName());
		
		states.add(SensorFailureState.getQualifiedStateName());
		
	   
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
    