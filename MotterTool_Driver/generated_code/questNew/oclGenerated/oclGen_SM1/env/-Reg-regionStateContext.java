

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

public class -Reg-regionStateContext extends ActiveObject
{	  

	private int instanceId;
	
	/* Class Constructor */		
   	public -Reg-regionStateContext(Class1 context_)
   	{
   	  	this.instanceId = context_.getInstanceId();
		class1-Fin-targetState = new Class1-Fin-targetState(context_);
 		class1Transition1-Sta-sourceState = new Class1Transition1-Sta-sourceState(context_);
 		class1Transition1-Sta-targetState = new Class1Transition1-Sta-targetState(context_);
 		
	}
	
	public void init(Class1 context_)
	{
	  class1-Fin-targetState.init(context_);
 		class1Transition1-Sta-sourceState.init(context_);
 		class1Transition1-Sta-targetState.init(context_);
 		
	}
	
	public int getInstanceId()
	{
		return instanceId;
	}
	
		   
	private -Reg-regionState -Reg-regionState; //the currentState

	/* States */	    
	private Class1-Fin-targetState class1-Fin-targetState;
	private Class1Transition1-Sta-sourceState class1Transition1-Sta-sourceState;
	private Class1Transition1-Sta-targetState class1Transition1-Sta-targetState;
	
    /* State Getters */
    
	public Class1-Fin-targetState getClass1-Fin-targetState()
	{
		return class1-Fin-targetState;
    }
	
	public Class1Transition1-Sta-sourceState getClass1Transition1-Sta-sourceState()
	{
		return class1Transition1-Sta-sourceState;
    }
	
	public Class1Transition1-Sta-targetState getClass1Transition1-Sta-targetState()
	{
		return class1Transition1-Sta-targetState;
    }
	
	/* Init State Method */
    
	public -Reg-regionState setInitState(-Reg-regionState toState, Class1 context_)
	{
	
			 
  
	  	 
		-Reg-regionState= toState;
		toState.onStateEntry(null, context_);
		
		return toState;
	}
     
     
	 
    /* Change State Method */
    
    public synchronized void changeState(-Reg-regionState fromState, -Reg-regionState toState, Class1 context_){
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
		
		-Reg-regionState = toState;
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
		-Reg-regionState.timeout(time, isRelative, context_);
			
	} 
	
	public void resetTimer()
	{
	  class1-Fin-targetState.resetTimer();
 		class1Transition1-Sta-sourceState.resetTimer();
 		class1Transition1-Sta-targetState.resetTimer();
 		
	}
	
	/* Change Event Method */
	     
	public void executeChangeEvent(String condition, Class1 context_)
	{
	-Reg-regionState.executeChangeEvent(condition, context_);
			
	} 
	
	public String evaluateChangeEvents(Class1 context_)
	{
	return -Reg-regionState.evaluateChangeEvents(context_);
			
	} 
	
     /* Completion Event */
     
    public void executeCompletionEvent(Class1 context_)
    {
		-Reg-regionState.executeCompletionEvent(context_);
			
	} 
	
	
	public String[] getStateName()
	{
	  	
	  	return -Reg-regionState.getStateName();
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
			if(stateName.equalsIgnoreCase(-Reg-regionState.getStateNameSimple())){
			  return true;
			}
	   	}
	   	else
	   	{
		  String states[] = -Reg-regionState.getStateName();
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
		    String state = -Reg-regionState.getStateNameSimple();
		    
		    if(state.equalsIgnoreCase(stateName))
		    {
		      return true;
		    } 
		  }
		  else
		  {
		  
			  String states[] = -Reg-regionState.getStateName();
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
		  	  		   
				 return -Reg-regionState.onStateExit(toState, context_);
				
		  	  
		  	}
		  		
} // end of class Class1
