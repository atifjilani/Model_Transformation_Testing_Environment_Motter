

/**
 * Auto Generated Code for Environment Simulator
 * Date Generated: 12/1/2017
 * Time: 14:38:1
 */ 
 
 
package simula.artificial_problems.booting_new.env;



import java.util.ArrayList;

import simula.embt.simulator.core.*;
import simula.embt.simulator.core.time.*;
import simula.embt.simulator.core.time.TimeInstance.Unit;
import simula.embt.simulator.util.*;
import simula.embt.commons.*;

import static simula.sbocl.SupportFunctions.*;

public class SensorFailureState extends SensorState implements simula.embt.simulator.core.ErrorState
{	
	
	
	/**
	 * The TimeInstances are obtained from the state machine and specify all the possible 
	 * time instances (that force time triggers going out) of the state
	 */

	private boolean resetTimers;
	private boolean emptyEventQueue;

	/* Constructor */
	
	public SensorFailureState(Sensor context_)
	{ 

		resetTimers = true;
		emptyEventQueue = true;

	}
 
   /* Signals */
   
	/* Change Events */

	public String evaluateChangeEvents(Sensor context_)
	{
	  String result = null;
	  //check each condition and return the condition if it satisfied
		
   		return result; 
	}

	public void executeChangeEvent(String condition, Sensor context_)
	{

	}
 
	 /* Completion Event method */ 

	public void executeCompletionEvent( Sensor context_)
	{
	 
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


	public void init(Sensor context_)
	{
	  		initializeApproachLevels(context_);	  
	}

	public void initializeApproachLevels(Sensor context_)
	{
		int errorId;
		int level;
		
		
	
	}
	
	
  /* State Entry/Exit Method */
  
	public void onStateEntry(IState fromState, final Sensor context_)
 	{
 	 
 		if(resetTimers)
 		{//dont do this for non-leaving transitions with resettimers = false
 		
		java.util.Enumeration en = errorStateApproachLevel.keys(); 
  		while(en.hasMoreElements())
  		{
        	int errorId = (Integer)en.nextElement();
        	int level = (Integer)errorStateApproachLevel.get(errorId);
        	simula.embt.commons.HeuristicTrace.getTrace().reportApproachLevel(errorId, level);
  		}
	
 		}//resetTimers
 	
      	resetTimers = true;	//reset the timers - default case
   		emptyEventQueue = true;	//default case - empty the event queues   
   	 
		//log if the entered state is a failure state 
		Logger.getLogger().println("Entered Failure state from " + fromState + " for object " + context_.getInstanceId());		
		
   
   	//if timeout transition, then call corresponding method of timeService
   	 
   	//perform entry actions of state
   	
   	try{
   	  	
		try{
			((Booting_newActionCode)context_.getAction()).closeConnection();
		}catch(Exception e){
		 context_.addException(e);
Logger.getLogger().error(e); 
	}
	}catch(Exception e)
   		{
			context_.addException(e);
			Logger.getLogger().error(e);
		
   		}
   	
   
   	//perform do actions on state
   	
   	//call the completion event
   
	executeCompletionEvent(context_);
   	
	String condition = this.evaluateChangeEvents(context_);
	if(condition != null)
	{
		try{
		context_.receiveChangeEvent("executeChangeEvent", new Object[]{condition});
	   	} catch (Exception e) {				
			context_.addException(e);
			Logger.getLogger().error(e);
		}	
	}
   	
  }
  
  
  	java.util.Hashtable errorStateApproachLevel = new java.util.Hashtable(0);
 	
  
  
  public Object[] onStateExit(IState toState, Sensor context_)
  {
    
/*
 * Code for calculating & reporting heuristics
 */
 
 	if(resetTimers)
 	{

  	}
	
	/*
     * - End - Code for reporting heuristics
     */
	
   
   //if timeout transition, then call corresponding method of timeService
   
   //perform exit actions of state
   
   		return new Object[]{emptyEventQueue};
    
  }
  
  private double calculateBranchDistance(String guard, Sensor context_)
  {
    /*
     the method contains translation of all guards on time transitions from OCL to Java. This is done to avoid
     parsing OCL expressions at runtime.
     */ 
    
    throw new RuntimeException("Problem with simulator - a guard cannot be recognized");
  }

	public String getStateNameSimple()
	{
	  	return "Failure";
	  	
	}
	
	public String[] getStateName()
	{
		return new String[]{"Failure"};
	}

	public static String getQualifiedStateName()
	{
		return "Failure";
	}

	public void stopExecution()
	{

	}

    public static int getErrorId(int instanceId)
    {
      
      return instanceId*1 + ;
    }
      
} //end class
