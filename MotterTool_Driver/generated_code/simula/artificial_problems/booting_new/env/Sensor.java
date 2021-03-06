

/**
 * Auto Generated Code for Environment Simulator
 * Date Generated: 3/8/2016
 * Time: 12:47:8
 */
 

package simula.artificial_problems.booting_new.env; 

import simula.embt.simulator.core.*;
import simula.embt.simulator.core.time.*;
import simula.embt.simulator.util.*;
import simula.embt.commons.*;

import static simula.sbocl.SupportFunctions.*;

public class SensorSensor extends SimulatedObject 
{

	/* Class Constructor */	
	int instanceId;
	public Booting_newActionCode action;
	IState[] states;
	
	public Sensor(int id)
	{
	  	super(1);
	  	this.instanceId = id;
	  	
	  	//initialize non-deterministic values
	  	
	  	isConnected = false;
		  		
	}
	
	public int getInstanceId()
	{
	  	return instanceId;
	}
	
	
	public String getComponentName()
	{
	  	return "Sensor";
	}
	
	@Override
	
	public void startExecution(Object[] args){
	  	Logger.getLogger().println("E"+instanceId+" is starting");
		
		try
		{
			action = new Booting_newActionCode(args);
			action.startExecution(this);
		
			super.startExecution(args);
		}catch(Exception e)
		{
		  	exceptions.add(e);
		  	Logger.getLogger().error(e);
		}
		
		this.start();
		threads.add(this);
		
		
			states = new IState[5];
		
	    try
	    {
	    
	    }catch(Exception e)
	    {
	      	exceptions.add(e);
	      	Logger.getLogger().error(e);
	    }
	    sensorBootingState = new SensorBootingState(this);
				 	sensorBootingState.init(this);
				 	
		sensorWaitingState = new SensorWaitingState(this);
				 	sensorWaitingState.init(this);
				 	
		sensorConnectedState = new SensorConnectedState(this);
				 	sensorConnectedState.init(this);
				 	
		sensorEnvironmentErrorState = new SensorEnvironmentErrorState(this);
				 	sensorEnvironmentErrorState.init(this);
				 	
		sensorFailureState = new SensorFailureState(this);
				 	sensorFailureState.init(this);
				 	
		sensorState = setInitState(getSensorBootingState());
		sensorState.evaluateChangeEvents(this);					
		states[0] = getSensorBootingState();
	    states[1] = getSensorWaitingState();
	    states[2] = getSensorConnectedState();
	    states[3] = getSensorEnvironmentErrorState();
	    states[4] = getSensorFailureState();
	    
	}
		
	public void stopExecution()
	{
	  if(states != null)
	  {
	  	for(IState state: states){
	  	  if(state != null)
	  	  {	  	    
	  	    try{	  	      
	  	  		state.stopExecution();
	  	    }catch(Exception e)
	  	    {
	  	      addException(e);
	  	      Logger.getLogger().error(e);
	  	    }
	  	  }
	  	}
	  }
	  	super.stopExecution();
	  	//then deallocate resources that might lead to deadlocks
		if(action != null)
		{
			try
			{
				action.stopExecution();
			}
			catch(Exception e)
			{
				exceptions.add(e);
				Logger.getLogger().error(e);
			}
		}
	  	Logger.getLogger().println("E"+instanceId+" is terminated");
	  	
	}
	
	
	public ExternalCode getAction()
	{
	  	return this.action;
	}
			   
	private SensorState sensorState; //the currentState

	/* States */
	private SensorBootingState sensorBootingState;
	private SensorWaitingState sensorWaitingState;
	private SensorConnectedState sensorConnectedState;
	private SensorEnvironmentErrorState sensorEnvironmentErrorState;
	private SensorFailureState sensorFailureState;
	
    /* State Getters  */
    
	public SensorBootingState getSensorBootingState()
	{
		return sensorBootingState;
    }
	        
	
	public SensorWaitingState getSensorWaitingState()
	{
		return sensorWaitingState;
    }
	        
	
	public SensorConnectedState getSensorConnectedState()
	{
		return sensorConnectedState;
    }
	        
	
	public SensorEnvironmentErrorState getSensorEnvironmentErrorState()
	{
		return sensorEnvironmentErrorState;
    }
	        
	
	public SensorFailureState getSensorFailureState()
	{
		return sensorFailureState;
    }
	        
	
	/* Init State Method */
    
	private SensorState setInitState(SensorState toState)
	{
	  	sensorState = toState;
		toState.onStateEntry(null, this);
		return toState;
	}
     
     
	 
    /* Change State Method */
    
    
    
    public synchronized void changeState(SensorState fromState, SensorState toState){
		Logger.getLogger().println("E"+instanceId+" changed from "+fromState + " to "+ toState);
	
	   	  if(fromState != null)
			{
				Object retValues[] = fromState.onStateExit(toState, this);
			
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
			
			sensorState = toState;
			
			toState.onStateEntry(fromState, this);
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
	 
	public void timeout(TimeInstance time, Boolean isRelative)
	{
		
			try{
				sensorState.timeout(time, isRelative, this);
			} catch (Exception e) {
			  	addException(e);				
				Logger.getLogger().error(e);
			}
				
	} 
	
     /* Change Event Method */
     
	public void executeChangeEvent(String condition)
	{
		sensorState.executeChangeEvent(condition, this);
			
	} 

     /* Completion Event */
     
    public void executeCompletionEvent()
    {
		sensorState.executeCompletionEvent(this);
			
	} 

    /* Attributes */
	
	 Integer sensorBootTime;
	
	 Boolean isConnected;
	
	/* Getters & Setters for Attributes/Association */
	
	
	
	public Integer getSensorBootTime()
	{
		return sensorBootTime;
	}	    	
	
	public void setSensorBootTime(Integer val)
	{	
		this.sensorBootTime = val;		
		String condition;
		if(this.isAlive())
		{
			
			condition = sensorState.evaluateChangeEvents(this);
			if(condition != null)
			{
		  //if a change event is satisfied add the corresponding event in the queue
		 
				try{
			  
				  receiveChangeEvent("executeChangeEvent", new Object[]{condition});
				  

				} catch (Exception e) {				
					addException(e);
					Logger.getLogger().error(e);
				}
			}
		
		
		}
	}
	public Boolean getIsConnected()
	{
		return isConnected;
	}	    	
	
	public void setIsConnected(Boolean val)
	{	
		this.isConnected = val;		
		String condition;
		if(this.isAlive())
		{
			
			condition = sensorState.evaluateChangeEvents(this);
			if(condition != null)
			{
		  //if a change event is satisfied add the corresponding event in the queue
		 
				try{
			  
				  receiveChangeEvent("executeChangeEvent", new Object[]{condition});
				  

				} catch (Exception e) {				
					addException(e);
					Logger.getLogger().error(e);
				}
			}
		
		
		}
	}
	
	public String[] getStateName()
	{
	  	
	  	return sensorState.getStateName();
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
			if(stateName.equalsIgnoreCase(sensorState.getStateNameSimple())){
			  return true;
			}
	   	}
	   	else
	   	{
		  String states[] = sensorState.getStateName();
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
		    String state = sensorState.getStateNameSimple();
		    
		    if(state.equalsIgnoreCase(stateName))
		    {
		      return true;
		    } 
		  }
		  else
		  {
		  
			  String states[] = sensorState.getStateName();
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
		

} // end of class Sensor
