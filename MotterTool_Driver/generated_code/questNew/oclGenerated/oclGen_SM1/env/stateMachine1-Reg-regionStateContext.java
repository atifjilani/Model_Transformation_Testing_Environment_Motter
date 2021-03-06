

/**
 * Auto Generated Code for Environment Simulator
 * Date Generated: 3/7/2016
 * Time: 11:9:6
 */
 

package questNew.oclGenerated.oclGen_SM1.env; 

import simula.embt.simulator.core.*;
import simula.embt.simulator.core.time.*;
import simula.embt.simulator.util.*;
import simula.embt.commons.*;

import static simula.sbocl.SupportFunctions.*;

public class stateMachine1-Reg-regionStateContext extends ActiveObject
{	  

	private int instanceId;
	
	/* Class Constructor */		
   	public stateMachine1-Reg-regionStateContext(Class1 context_)
   	{
   	  	this.instanceId = context_.getInstanceId();
		
	}
	
	public void init(Class1 context_)
	{
	  
	}
	
	public int getInstanceId()
	{
		return instanceId;
	}
	
		   
	private StateMachine1-Reg-regionState stateMachine1-Reg-regionState; //the currentState

	/* States */	    
	
    /* State Getters */
    
	/* Init State Method */
    
	public StateMachine1-Reg-regionState setInitState(StateMachine1-Reg-regionState toState, Class1 context_)
	{
	  
	  	 
		stateMachine1-Reg-regionState= toState;
		toState.onStateEntry(null, context_);
		
		return toState;
	}
     
     
	 
    /* Change State Method */
    
    public synchronized void changeState(StateMachine1-Reg-regionState fromState, StateMachine1-Reg-regionState toState, Class1 context_){
		Logger.getLogger().println("E"+instanceId+" changed from "+fromState + " to "+ toState);
		if(fromState != null) // for internal states
		{
			Object retValues[] = fromState.onStateExit(toState, context_);
		
			boolean emptyQueue = true;
			if(retValues != null)
			{
				emptyQueue = (Boolean)retValues[0];
			}
		
			if(emptyQueue)
			{
				clearQueue(signalQueue);
			}
		}
		
		stateMachine1-Reg-regionState = toState;
		toState.onStateEntry(fromState, context_);
			
		HeuristicTrace.getTrace().reportStateChange();
		
	}
    
    
    /* Signals */
    
    /* Run Method */
    
	public void run()
	{
		try
		{
    	    while(!this.isInterrupted())
    	    {
				SignalInvocation signal = null;

				boolean read = false;
					
				while(!read)
				{
					synchronized(signalQueue) 
					{
						if(signalQueue.size() == 0) 
						{
							try
							{
								signalQueue.wait();
							}
							catch(InterruptedException e) 
							{ 	addException(e);
							 Logger.getLogger().error(e);
							 
								return;
							}
						}
						else
						{
							read = true;
							signal = super.readSignal();
						}
					}
				}
					
				super.acceptSignal(signal);	
			}
    	}catch(Exception e){
    	  	
    		addException(e);
    		Logger.getLogger().error(e);
    		
    		Logger.getLogger().error(e);
    	}    
    }
	
	/* Timeout Method */
	     
    public void timeout(TimeInstance time, Boolean isRelative,Class1 context_)
    {
		stateMachine1-Reg-regionState.timeout(time, isRelative, context_);
			
	} 
	
	public void resetTimer()
	{
	  
	}
	
	/* Change Event Method */
	     
	public void executeChangeEvent(String condition, Class1 context_)
	{
	stateMachine1-Reg-regionState.executeChangeEvent(condition, context_);
			
	} 
	
	public String evaluateChangeEvents(Class1 context_)
	{
	return stateMachine1-Reg-regionState.evaluateChangeEvents(context_);
			
	} 
	
     /* Completion Event */
     
    public void executeCompletionEvent(Class1 context_)
    {
		stateMachine1-Reg-regionState.executeCompletionEvent(context_);
			
	} 
	
	
	public String[] getStateName()
	{
	  	
	  	return stateMachine1-Reg-regionState.getStateName();
	}
	public int distanceOclInState(String stateName)
	{
		if(oclInState(stateName))
		{
		  	return 0;
		}
		else
		{
		  	return 1;
		}  
	}	
		
	public boolean oclInState(String stateName)
	{
	  
	  try
	  {
	   
	   
	   	if(stateName.indexOf(".") < 0)
	   	{
			if(stateName.equalsIgnoreCase(stateMachine1-Reg-regionState.getStateNameSimple())){
			  return true;
			}
	   	}
	   	else
	   	{
		  String states[] = stateMachine1-Reg-regionState.getStateName();
		  for(int i=0;i<states.length;i++)
		  {
		    if(states[i].startsWith(stateName))
		    {
		      return true;
		    }
		  }
	   	}
	
		  if(stateName.indexOf(".") < 0)
		  {
		    String state = stateMachine1-Reg-regionState.getStateNameSimple();
		    
		    if(state.equalsIgnoreCase(stateName))
		    {
		      return true;
		    } 
		  }
		  else
		  {
		  
			  String states[] = stateMachine1-Reg-regionState.getStateName();
			  for(int i=0;i<states.length;i++)
			  {
			    if(states[i].startsWith(stateName))
			    {
			      return true;
			    }
			  }
		  }	    	
	   	
	   	
	   	
	
	   
	  }catch(Exception e)
	  {
	    Logger.getLogger().error(e);
	    addException(e);
	    return false;
	  }
		return false;  
	}
	
		  	public Object[] exitState(IState toState, Class1 context_)
		  	{
		  	  clearQueue(signalQueue);
		  	  		   
				 return stateMachine1-Reg-regionState.onStateExit(toState, context_);
				
		  	  
		  	}
		  		
} // end of class Class1
