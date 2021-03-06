

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

public class Package1-Cla-packagedElementPackage1-Cla-packagedElement extends SimulatedObject 
{

	/* Class Constructor */	
	int instanceId;
	public  action;
	IState[] states;
	
	public Package1-Cla-packagedElement(int id)
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
	  	return "Package1-Cla-packagedElement";
	}
	
	@Override
	
	public void startExecution(Object[] args){
	  	Logger.getLogger().println("E"+instanceId+" is starting");
		
		try
		{
			action = new (args);
			action.startExecution(this);
		
			super.startExecution(args);
		}catch(Exception e)
		{
		  	exceptions.add(e);
		  	Logger.getLogger().error(e);
		}
		
		this.start();
		threads.add(this);
		
		
			states = new IState[2];
		
	    try
	    {
	    
	    }catch(Exception e)
	    {
	      	exceptions.add(e);
	      	Logger.getLogger().error(e);
	    }
	    -Reg-regionStateContext_ = new -Reg-regionStateContext(this);
		region1StateContext_ = new Region1StateContext(this);
		-Reg-regionStateContext_.init(this);
	   			region1StateContext_.init(this);
	   			-Reg-regionStateContext_.start();
		threads.add(-Reg-regionStateContext_);
	   			
	   			region1StateContext_.start();
		threads.add(region1StateContext_);
	   			
	   			-Reg-regionStateContext_.setInitState(getPackage1-Cla-packagedElement-Sta-targetState(), this);
		region1StateContext_.setInitState(getPackage1-Cla-packagedElementState(), this);
		-Reg-regionStateContext_.evaluateChangeEvents(this);
					
		region1StateContext_.evaluateChangeEvents(this);
					
		
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
		   
	private Region1StateContext region1StateContext_;

	/* Change State Method */
	
	public synchronized void change-Reg-regionState(-Reg-regionState fromState, -Reg-regionState toState)
	{
	  	Logger.getLogger().println("E"+instanceId+" changed from "+fromState + " to "+ toState);
		-Reg-regionStateContext_.changeState(fromState, toState,this);
	}
	   
	
	public synchronized void changeRegion1State(Region1State fromState, Region1State toState)
	{
	  	Logger.getLogger().println("E"+instanceId+" changed from "+fromState + " to "+ toState);
		region1StateContext_.changeState(fromState, toState,this);
	}
	   
	   
	 /* State getters */   
	 
	 
	 public State-Reg-regionState getPackage1-Cla-packagedElementTransition1-Sta-targetState()
	 {
	 	return state-Reg-regionStateContext_.getPackage1-Cla-packagedElementTransition1-Sta-targetState();
	 }	
	 
	 
	 public State1-Reg-regionState getPackage1-Cla-packagedElementRegion1-Sta-subvertexState()
	 {
	 	return state1-Reg-regionStateContext_.getPackage1-Cla-packagedElementRegion1-Sta-subvertexState();
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
				region1StateContext_.receiveTimeEvent("timeout", new Object[]{time, isRelative, this}, false);
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
		  region1StateContext_.receiveChangeEvent("executeChangeEvent", new Object[]{condition, this});
			} catch (Exception e) {				
			addException(e);
			Logger.getLogger().error(e);
		}	
			
	} 

     /* Completion Event */
     
    public void executeCompletionEvent()
    {
		-Reg-regionStateContext_.executeCompletionEvent(this);
		region1StateContext_.executeCompletionEvent(this);
			
	} 

    /* Attributes */
	
	 String -Pro-ownedAttribute;
	
	/* Getters & Setters for Attributes/Association */
	
	
	
	public String get-Pro-ownedAttribute()
	{
		return -Pro-ownedAttribute;
	}	    	
	
	public void set-Pro-ownedAttribute(String val)
	{	
		this.-Pro-ownedAttribute = val;		
		String condition;
		if(this.isAlive())
		{
			
			condition = -Reg-regionStateContext_.evaluateChangeEvents(this);
			if(condition != null)
			{
		  //if a change event is satisfied add the corresponding event in the queue
		 
				try{
			  -Reg-regionStateContext_.receiveChangeEvent("executeChangeEvent", new Object[]{condition, this});
				    

				} catch (Exception e) {				
					addException(e);
					Logger.getLogger().error(e);
				}
			}
		
		
			condition = region1StateContext_.evaluateChangeEvents(this);
			if(condition != null)
			{
		  //if a change event is satisfied add the corresponding event in the queue
		 
				try{
			  region1StateContext_.receiveChangeEvent("executeChangeEvent", new Object[]{condition, this});
				    

				} catch (Exception e) {				
					addException(e);
					Logger.getLogger().error(e);
				}
			}
		
		
		}
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
	 
		return (-Reg-regionStateContext_.oclInState(stateName) || region1StateContext_.oclInState(stateName));
	}
		
 	@Override
	public java.util.LinkedList<Exception> getExceptions()
	{

		exceptions.addAll(-Reg-regionStateContext_.getExceptions());

		exceptions.addAll(region1StateContext_.getExceptions());
		return exceptions;		
	}
 		
 	

} // end of class Package1-Cla-packagedElement
