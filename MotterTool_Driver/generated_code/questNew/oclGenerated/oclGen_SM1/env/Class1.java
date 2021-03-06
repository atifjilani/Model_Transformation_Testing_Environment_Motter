

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

public class Class1Class1 extends SimulatedObject 
{

	/* Class Constructor */	
	int instanceId;
	public simula.embt.commons.DefaultAction action;
	IState[] states;
	
	public Class1(int id)
	{
	  	super(0);
	  	this.instanceId = id;
	  	
	  	//initialize non-deterministic values
	  	
	  	
	}
	
	public int getInstanceId()
	{
	  	return instanceId;
	}
	
	
	public String getComponentName()
	{
	  	return "Class1";
	}
	
	@Override
	
	public void startExecution(Object[] args){
	  	Logger.getLogger().println("E"+instanceId+" is starting");
		
		try
		{
			action = new simula.embt.commons.DefaultAction(args);
			action.startExecution(this);
		
			super.startExecution(args);
		}catch(Exception e)
		{
		  	exceptions.add(e);
		  	Logger.getLogger().error(e);
		}
		
		this.start();
		threads.add(this);
		
		
			states = new IState[3];
		
	    try
	    {
	    

	    }catch(Exception e)
	    {
	      	exceptions.add(e);
	      	Logger.getLogger().error(e);
	    }
	    -Reg-regionStateContext_ = new -Reg-regionStateContext(this);
		stateMachine1-Reg-regionStateContext_ = new StateMachine1-Reg-regionStateContext(this);
		-Reg-regionStateContext_.init(this);
	   			stateMachine1-Reg-regionStateContext_.init(this);
	   			-Reg-regionStateContext_.start();
		threads.add(-Reg-regionStateContext_);
	   			
	   			stateMachine1-Reg-regionStateContext_.start();
		threads.add(stateMachine1-Reg-regionStateContext_);
	   			
	   			-Reg-regionStateContext_.setInitState(getClass1Transition1-Sta-sourceState(), this);
		stateMachine1-Reg-regionStateContext_.setInitState(getClass1State(), this);
		-Reg-regionStateContext_.evaluateChangeEvents(this);
					
		stateMachine1-Reg-regionStateContext_.evaluateChangeEvents(this);
					
		
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
			   
	private -Reg-regionStateContext -Reg-regionStateContext_;
		   
	private StateMachine1-Reg-regionStateContext stateMachine1-Reg-regionStateContext_;

	/* Change State Method */
	
	public synchronized void change-Reg-regionState(-Reg-regionState fromState, -Reg-regionState toState)
	{
	  	Logger.getLogger().println("E"+instanceId+" changed from "+fromState + " to "+ toState);
		-Reg-regionStateContext_.changeState(fromState, toState,this);
	}
	   
	
	public synchronized void changeStateMachine1-Reg-regionState(StateMachine1-Reg-regionState fromState, StateMachine1-Reg-regionState toState)
	{
	  	Logger.getLogger().println("E"+instanceId+" changed from "+fromState + " to "+ toState);
		stateMachine1-Reg-regionStateContext_.changeState(fromState, toState,this);
	}
	   
	   
	 /* State getters */   
	 
	 
	 public -Reg-regionState getClass1-Fin-targetState()
	 {
	 	return -Reg-regionStateContext_.getClass1-Fin-targetState();
	 }	
	 
	 
	 public -Reg-regionState getClass1Transition1-Sta-targetState()
	 {
	 	return -Reg-regionStateContext_.getClass1Transition1-Sta-targetState();
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
				-Reg-regionStateContext_.receiveTimeEvent("timeout", new Object[]{time, isRelative, this}, false);
			} catch (Exception e) {				
				addException(e);
				Logger.getLogger().error(e);
			}
			
			try{
				stateMachine1-Reg-regionStateContext_.receiveTimeEvent("timeout", new Object[]{time, isRelative, this}, false);
			} catch (Exception e) {				
				addException(e);
				Logger.getLogger().error(e);
			}
				
	} 
	
     /* Change Event Method */
     
	public void executeChangeEvent(String condition)
	{
		try{
		  -Reg-regionStateContext_.receiveChangeEvent("executeChangeEvent", new Object[]{condition, this});
			} catch (Exception e) {				
			addException(e);
			Logger.getLogger().error(e);
		}	
		try{
		  stateMachine1-Reg-regionStateContext_.receiveChangeEvent("executeChangeEvent", new Object[]{condition, this});
			} catch (Exception e) {				
			addException(e);
			Logger.getLogger().error(e);
		}	
			
	} 

     /* Completion Event */
     
    public void executeCompletionEvent()
    {
		-Reg-regionStateContext_.executeCompletionEvent(this);
		stateMachine1-Reg-regionStateContext_.executeCompletionEvent(this);
			
	} 

    /* Attributes */
	
	/* Getters & Setters for Attributes/Association */
	
	
	
	
	
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
	 
		return (-Reg-regionStateContext_.oclInState(stateName) || stateMachine1-Reg-regionStateContext_.oclInState(stateName));
	}
		
 	@Override
	public java.util.LinkedList<Exception> getExceptions()
	{

		exceptions.addAll(-Reg-regionStateContext_.getExceptions());

		exceptions.addAll(stateMachine1-Reg-regionStateContext_.getExceptions());
		return exceptions;		
	}
 		
 	

} // end of class Class1
