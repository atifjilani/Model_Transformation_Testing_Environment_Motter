

/**
 * Auto Generated Code for Environment Simulator
 * Date Generated: 17/2/2016
 * Time: 21:42:8
 */
 

package questNew.oclGenerated.oclGen_SM1.env; 

import simula.embt.simulator.core.*;
import simula.embt.simulator.core.time.*;
import simula.embt.simulator.util.*;
import simula.embt.commons.*;

import static simula.sbocl.SupportFunctions.*;

public class Region1StateContext extends ActiveObject
{	  

	private int instanceId;
	
	/* Class Constructor */		
   	public Region1StateContext(Package1-Cla-packagedElement context_)
   	{
   	  	this.instanceId = context_.getInstanceId();
		
	}
	
	public void init(Package1-Cla-packagedElement context_)
	{
	  
	}
	
	public int getInstanceId()
	{
		return instanceId;
	}
	
		   
	private Region1State region1State; //the currentState

	/* States */	    
	
    /* State Getters */
    
	/* Init State Method */
    
	public Region1State setInitState(Region1State toState, Package1-Cla-packagedElement context_)
	{
	  
	  	 
		region1State= toState;
		toState.onStateEntry(null, context_);
		
		return toState;
	}
     
     
	 
    /* Change State Method */
    
    public synchronized void changeState(Region1State fromState, Region1State toState, Package1-Cla-packagedElement context_){
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
		
		region1State = toState;
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
	     
    public void timeout(TimeInstance time, Boolean isRelative,Package1-Cla-packagedElement context_)
    {
		region1State.timeout(time, isRelative, context_);
			
	} 
	
	public void resetTimer()
	{
	  
	}
	
	/* Change Event Method */
	     
	public void executeChangeEvent(String condition, Package1-Cla-packagedElement context_)
	{
	region1State.executeChangeEvent(condition, context_);
			
	} 
	
	public String evaluateChangeEvents(Package1-Cla-packagedElement context_)
	{
	return region1State.evaluateChangeEvents(context_);
			
	} 
	
     /* Completion Event */
     
    public void executeCompletionEvent(Package1-Cla-packagedElement context_)
    {
		region1State.executeCompletionEvent(context_);
			
	} 
	
	
	public String[] getStateName()
	{
	  	
	  	return region1State.getStateName();
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
			if(stateName.equalsIgnoreCase(region1State.getStateNameSimple())){
			  return true;
			}
	   	}
	   	else
	   	{
		  String states[] = region1State.getStateName();
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
		    String state = region1State.getStateNameSimple();
		    
		    if(state.equalsIgnoreCase(stateName))
		    {
		      return true;
		    } 
		  }
		  else
		  {
		  
			  String states[] = region1State.getStateName();
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
	
		  	public Object[] exitState(IState toState, Package1-Cla-packagedElement context_)
		  	{
		  	  clearQueue(signalQueue);
		  	  		   
				 return region1State.onStateExit(toState, context_);
				
		  	  
		  	}
		  		
} // end of class Package1-Cla-packagedElement
