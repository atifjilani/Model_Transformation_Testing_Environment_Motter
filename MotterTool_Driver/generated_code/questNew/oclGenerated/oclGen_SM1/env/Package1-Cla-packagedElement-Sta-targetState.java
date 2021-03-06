

/**
 * Auto Generated Code for Environment Simulator
 * Date Generated: 17/2/2016
 * Time: 21:42:8
 */ 
 
 
package questNew.oclGenerated.oclGen_SM1.env;



import java.util.ArrayList;

import simula.embt.simulator.core.*;
import simula.embt.simulator.core.time.*;
import simula.embt.simulator.core.time.TimeInstance.Unit;
import simula.embt.simulator.util.*;
import simula.embt.commons.*;

import static simula.sbocl.SupportFunctions.*;

public class Package1-Cla-packagedElement-Sta-targetState extends -Reg-regionState
{	 
  	
  	private State-Reg-regionStateContext state-Reg-regionStateContext; 
  	
  	private State1-Reg-regionStateContext state1-Reg-regionStateContext; 
  	
	
	/**
	 * The TimeInstances are obtained from the state machine and specify all the possible 
	 * time instances (that force time triggers going out) of the state
	 */

	private boolean resetTimers;
	private boolean emptyEventQueue;

	public Package1-Cla-packagedElement-Sta-targetState(Package1-Cla-packagedElement context_)
	{
		state-Reg-regionStateContext = new State-Reg-regionStateContext(context_);
      	state1-Reg-regionStateContext = new State1-Reg-regionStateContext(context_);
      	
		state-Reg-regionStateContext.start();
    	  	context_.addThread(state-Reg-regionStateContext);
	  	state1-Reg-regionStateContext.start();
    	  	context_.addThread(state1-Reg-regionStateContext);
	  	
	  	  
	}
	
	
	 
	 public Package1-Cla-packagedElementTransition1-Sta-targetState getPackage1-Cla-packagedElementTransition1-Sta-targetState()
	 {
	 	return state-Reg-regionStateContext.getPackage1-Cla-packagedElementTransition1-Sta-targetState();
	 }	
	 
	 
	 public Package1-Cla-packagedElementRegion1-Sta-subvertexState getPackage1-Cla-packagedElementRegion1-Sta-subvertexState()
	 {
	 	return state1-Reg-regionStateContext.getPackage1-Cla-packagedElementRegion1-Sta-subvertexState();
	 }	
	  
   /* Signals */
   
public void init(Package1-Cla-packagedElement context_)
	{
state-Reg-regionStateContext.init(context_);			
		
		state1-Reg-regionStateContext.init(context_);			
		
		
		initializeApproachLevels(context_);
		
	}

	public void initializeApproachLevels(Package1-Cla-packagedElement context_)
	{
		int errorId;
		int level;
	
	
	}
	
	
	
	
	  	java.util.Hashtable errorStateApproachLevel = new java.util.Hashtable(0);
 	
	
  	public java.util.Hashtable getErrorStateApproachLevel()
  	{
  	  	return errorStateApproachLevel;
  	}
  	
  	
	
	

	public void onStateEntry(IState fromState, final Package1-Cla-packagedElement context_)
	{
	  	state1-Reg-regionStateContext.setInitState(getPackage1-Cla-packagedElementRegion1-Sta-subvertexState(), context_);
	  	state-Reg-regionStateContext.setInitState(getPackage1-Cla-packagedElementTransition1-Sta-targetState(), context_);
	  	
	  	state-Reg-regionStateContext.evaluateChangeEvents(context_);
	  	state1-Reg-regionStateContext.evaluateChangeEvents(context_);
	  	
	  	
	}
	
	/* Change State Method */
	
	public void changeState(State-Reg-regionState fromState, State-Reg-regionState toState, Package1-Cla-packagedElement context_)
	{
	  	    	state-Reg-regionStateContext.changeState((State-Reg-regionState)fromState, (State-Reg-regionState)toState, context_);
		    	
	}
	
	public void changeState(State1-Reg-regionState fromState, State1-Reg-regionState toState, Package1-Cla-packagedElement context_)
	{
	  	    	state1-Reg-regionStateContext.changeState((State1-Reg-regionState)fromState, (State1-Reg-regionState)toState, context_);
		    	
	}
	
	public Object[] onStateExit(IState toState, Package1-Cla-packagedElement context_)
	{
		state-Reg-regionStateContext.exitState(toState, context_);
		state1-Reg-regionStateContext.exitState(toState, context_);
		
	  return new Object[]{true};
	}
	
	
	public void executeCompletionEvent(Package1-Cla-packagedElement context_)
    {
		state-Reg-regionStateContext.executeCompletionEvent(context_);
		state1-Reg-regionStateContext.executeCompletionEvent(context_);
		
	}
	
	public String getStateNameSimple()
	{
	  return "-Sta-target";
	}
	
	public static String getQualifiedStateName()
	{
	  return "-Sta-target";
	}
	
	public String[] getStateName()
	{
	 
		int stateSize = 0;
	
	 	 String str0[] = state-Reg-regionStateContext.getStateName(); 
	 	 stateSize = stateSize + str0.length;
	 	 
	 	 String str1[] = state1-Reg-regionStateContext.getStateName(); 
	 	 stateSize = stateSize + str1.length;
	 	 
		String stateNames[] = new String[stateSize];
		int i =0;	
	  	
	  	
	  	for(String st:str0)
	  	{
	  	  stateNames[i] =  st;
	  	  i++;
	  	}
	  	
	  	
	  	for(String st:str1)
	  	{
	  	  stateNames[i] =  st;
	  	  i++;
	  	}
	  	
	  	return stateNames;
	}
	
	public void stopExecution() throws Exception
	{
	  	
	state-Reg-regionStateContext.interrupt();state-Reg-regionStateContext.join(2000);
      	state1-Reg-regionStateContext.interrupt();state1-Reg-regionStateContext.join(2000);
      	getPackage1-Cla-packagedElementTransition1-Sta-targetState().stopExecution();
    	  getPackage1-Cla-packagedElementRegion1-Sta-subvertexState().stopExecution();
    	  	  	  
	}
	
	
	public String evaluateChangeEvents(Package1-Cla-packagedElement context_)
	{
	  	return state-Reg-regionStateContext.evaluateChangeEvents( context_);
	  			  	
	}
	
	
	public void executeChangeEvent(String condition, Package1-Cla-packagedElement context_)
	{
	  	state-Reg-regionStateContext.executeChangeEvent(condition, context_);
	  	  state1-Reg-regionStateContext.executeChangeEvent(condition, context_);
	  	  
	}
	
	
} //end class
