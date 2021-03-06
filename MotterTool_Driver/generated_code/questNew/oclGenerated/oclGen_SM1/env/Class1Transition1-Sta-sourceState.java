

/**
 * Auto Generated Code for Environment Simulator
 * Date Generated: 3/7/2016
 * Time: 11:9:6
 */ 
 
 
package questNew.oclGenerated.oclGen_SM1.env;



import java.util.ArrayList;

import simula.embt.simulator.core.*;
import simula.embt.simulator.core.time.*;
import simula.embt.simulator.core.time.TimeInstance.Unit;
import simula.embt.simulator.util.*;
import simula.embt.commons.*;

import static simula.sbocl.SupportFunctions.*;

public class Class1Transition1-Sta-sourceState extends -Reg-regionState implements simula.embt.simulator.core.CompositeState
{	 
  	
	private -Reg-regionState -Reg-regionState;	
	
	private IState states[];		
	private boolean resetTimers;
	private boolean emptyEventQueue;	
	
	
	public boolean contains(IState state)
	{
	  for(IState st:states)
	  {
	    if(st == state)
	    {
	      return true;
	    }
	  }
	  return false;
	}
	
	public Class1Transition1-Sta-sourceState(Class1 context_)
	{
	  states = new IState[0];  
	}
	
	
	
	
	public java.util.Hashtable getErrorStateApproachLevel()
	{
	  	return errorStateApproachLevel;
	}
	

	public boolean leadsToErrorState(int errorId, java.util.Hashtable selfHt, java.util.Hashtable targetHt)
	{
	  Integer selfDistance = (Integer)selfHt.get(errorId);
	  Integer targetDistance = (Integer)targetHt.get(errorId);
	  
	  if(targetDistance == null || targetDistance >= selfDistance)
	  {
	   	return false; 
	  }
	  else
	  {
	    return true;
	  }
	  
	}

	
	
	public void init(Class1 context_)
	{

		initializeApproachLevels(context_);
		
	}

	public void initializeApproachLevels(Class1 context_)
	{
		int errorId;
		int level;
	
	}
	
	
	
	
	
	
	
	public void setCurrentState(-Reg-regionState toState)
	{
	  	-Reg-regionState = toState;
	}	
	
	public IState[] getSubStates()
	{
		return states;
	}
	 
   /* Signals */
    
	 /* Completion Event method */ 

	public void executeCompletionEvent( Class1 context_)
	{
	 
	}
	
	public void onStateEntry(IState fromState, final SimulatedObject context_, IState toState)
	{
		onStateEntry(fromState, (Class1)context_, toState);
	}
	
	public void onStateEntry(IState fromState, final Class1 context_)
	{
	  onStateEntry(fromState, context_, getClass1State());
	}
	
	public void onStateEntry(IState fromState, final Class1 context_, IState toState)
	{
	  
		java.util.Enumeration en = errorStateApproachLevel.keys(); 
  		while(en.hasMoreElements())
  		{
        	int errorId = (Integer)en.nextElement();
        	int level = (Integer)errorStateApproachLevel.get(errorId);
        	simula.embt.commons.HeuristicTrace.getTrace().reportApproachLevel(errorId, level);
  		}
	
      	resetTimers = true;	//reset the timers - default case
   		emptyEventQueue = true;	//default case - empty the event queues   
   	 
	  
	  
	  
	  if(fromState == null)
	  {
	  
	  	context_.changeState(null, (-Reg-regionState)toState);
	  
	  }
	  else
	  {
	  	context_.changeState((-Reg-regionState)fromState, (-Reg-regionState)toState);
	  }
	  -Reg-regionState =  (-Reg-regionState)toState;
		  	  
	}
	
	public void changeState(IState fromState, IState toState , SimulatedObject context_)
	{
		changeState((-Reg-regionState )fromState, (-Reg-regionState )toState, (Class1)context_);
	}
	
	public void changeState(-Reg-regionState fromState, -Reg-regionState toState, Class1 context_)
	{
	  	if(fromState != null)
	  	{
	  	  	
	  	  	fromState.onStateExit(toState, context_);
	  	  	
	  	}
	  	
	  	-Reg-regionState = toState;
	  	toState.onStateEntry(fromState, context_);
	  	
	}
	
	  	java.util.Hashtable errorStateApproachLevel = new java.util.Hashtable(0);
 	
	
	public Object[] onStateExit(IState toState, SimulatedObject context_)
	{
	  return onStateExit(toState, (Class1)context_);
	}
	
	public Object[] onStateExit(IState toState, Class1 context_)
	{
	  
	  return null;
	}
	
	public String getStateNameSimple()
	{
	  return "Transition1-Sta-source";
	}

	public String[] getStateName()
	{
	  return -Reg-regionState.getStateName();
	}
	
	public static String getQualifiedStateName()
	{
	  return "Transition1-Sta-source";
	}
	
	public void stopExecution() throws Exception
	{
	  if(states != null)
	  {
	  	for(IState state: states){
	  	  if(state != null)
	  	  {	  	    
	  	  	state.stopExecution();
	  	  }
	  	}
	  }	  
	  
	}
	
	
	public void executeChangeEvent(String condition, Class1 context_)
	{
		-Reg-regionState.executeChangeEvent(condition, context_);
	}
	
	public String evaluateChangeEvents(	Class1 context_)
	{
	  	return -Reg-regionState.evaluateChangeEvents( context_);
	}
	
	  
	public void timeout(TimeInstance time, boolean isRelative, Class1 context_)
	{
	  	-Reg-regionState.timeout(time, isRelative, context_);
	}
	
	
	
	public -Reg-regionState get-Reg-regionState()
	{
	  	return -Reg-regionState;
	}	
	
	public boolean oclInState(String stateName)
	{
	  
		String states[] = -Reg-regionState.getStateName();
		  
		for(int i=0; i< states.length; i++)
	  	{
		  	if(stateName.equalsIgnoreCase(states[i]))
		  	{
		  	  	return true;
			}
		}
	 	return false;
	
		  
	}
	
	
} //end class
