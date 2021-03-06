

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

public class SensorWaitingState extends SensorState implements simula.embt.simulator.core.ErrorState
{	
	
	
	/**
	 * The TimeInstances are obtained from the state machine and specify all the possible 
	 * time instances (that force time triggers going out) of the state
	 */

	private  TimeInstance t0;

	private boolean resetTimers;
	private boolean emptyEventQueue;

	private  TimeInstance t1;

	/**
	 * Responsible for starting the timers
	 */
	TimeStateService timeService;

	/* Constructor */
	
	public SensorWaitingState(Sensor context_)
	{ 

		resetTimers = true;
		emptyEventQueue = true;

		timeService = new TimeStateService();

	}
 
	 /* Timeout methods */ 

	public void timeout(TimeInstance time, boolean isRelative, Sensor context_)
	{
		if(time.equals(t0))
			{
				afterT0(context_);  
			}
else if(time.equals(t1))
			{
				afterT1(context_);  
			}

	}

	public void afterT0(Sensor context_)
	{

			ArrayList<Double> branchDistances = new ArrayList<Double>();
			
			context_.changeState(context_.getSensorWaitingState(), context_.getSensorEnvironmentErrorState());

 		HeuristicTrace.getTrace().reportTransition(context_.getInstanceId(), "Waiting",
 		 "after(5, s)", "Environment Error", ""+ context_.getInstanceId() + "-" + this.getQualifiedStateName());
		
  			ArrayList<IState> targetStatesList = new ArrayList<IState>();
			

	  		targetStatesList.add(context_.getSensorEnvironmentErrorState());


 			if(branchDistances.size() > 0)
	  		{
	  			reportBranchDistance(targetStatesList, branchDistances);
	  		}


	}

	public void afterT1(Sensor context_)
	{

			context_.changeState(context_.getSensorWaitingState(), context_.getSensorFailureState());

 		HeuristicTrace.getTrace().reportTransition(context_.getInstanceId(), "Waiting","TimeProbability", "Failure", ""+ context_.getInstanceId() + "-" + this.getQualifiedStateName());		
		
		
	}
 
   /* Signals */
   
	/* Change Events */

	public String evaluateChangeEvents(Sensor context_)
	{
	  String result = null;
	  //check each condition and return the condition if it satisfied
		
	  	boolean constraintVar0 = false;
	  	
	  	double constraintVar0_bd = Double.MAX_VALUE;
	  	
	  	{
	  	
	  	
	  	
	  		constraintVar0_bd = distanceTrue(context_.isConnected);
	  	
	  		constraintVar0 = constraintVar0_bd == 0d;
	  	
	  	}
	  	
		if(constraintVar0)	
		{
       	    result = "context_.isConnected";
		}
		else
       	{ //else case open
       	  
        	  //for each error state "reachable" - report branch distance
			ArrayList<IState> targetStatesList;
			java.util.Enumeration en;
			targetStatesList = new ArrayList<IState>();
	  		targetStatesList.add(context_.getSensorConnectedState());

    	  	
    	  	for(IState targetState:targetStatesList)
            {
              	en = errorStateApproachLevel.keys();
                java.util.Hashtable targetStateApproachLevel = targetState.getErrorStateApproachLevel(); 
      			while(en.hasMoreElements())
      			{
            		int errorId = (Integer)en.nextElement();
                	boolean leadsToError = leadsToErrorState(errorId, this.getErrorStateApproachLevel(), targetStateApproachLevel);
            		if(leadsToError)
            		{
    					int level = (Integer)errorStateApproachLevel.get(errorId);
						simula.embt.commons.HeuristicTrace.getTrace().reportBranchDistanceAtLevel(errorId, level, constraintVar0_bd);
            		}
      			}
    		}
  
        }

   		return result; 
	}

	public void executeChangeEvent(String condition, Sensor context_)
	{

		if(condition.equals("context_.isConnected"))
		{
		
 		context_.changeState(context_.getSensorWaitingState(), context_.getSensorConnectedState());
 		
 		HeuristicTrace.getTrace().reportTransition(context_.getInstanceId(), "Waiting","when [[self.isConnected]]", "Connected", ""+ context_.getInstanceId() + "-" + this.getQualifiedStateName());
		
		}
		
		
	}
 
	 /* Completion Event method */ 

	public void executeCompletionEvent( Sensor context_)
	{
	 
	}
	
	  private long stateEntryTime;

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
		
		
	
		  errorId = SensorEnvironmentErrorState.getErrorId(context_.getInstanceId()); 
		  level = 1;
		  errorStateApproachLevel.put(errorId, level);
	
	
	}
	
	
  /* State Entry/Exit Method */
  
	public void onStateEntry(IState fromState, final Sensor context_)
 	{
 	 
 		if(resetTimers)
 		{//dont do this for non-leaving transitions with resettimers = false
 		
 	  	stateEntryTime = System.currentTimeMillis();
 	  	
	  // report state as risky if adjascent state is an error state
	  
	  	simula.embt.commons.HeuristicTrace.getTrace().reportEnteredRiskyState(SensorEnvironmentErrorState.getErrorId(context_.getInstanceId()));
	  
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
   	 
   
   	//if timeout transition, then call corresponding method of timeService
   	
		ArrayList<TimeEvent> events = new ArrayList<TimeEvent>();

		ArrayList<IState> targetStatesList;
		
		ArrayList<String> guards;

		targetStatesList = new ArrayList<IState>();
		
		guards = new ArrayList<String>();

	  	targetStatesList.add(context_.getSensorEnvironmentErrorState());

		guards.add("");

	if(t0 == null || (t0 != null && !timeService.eventAlreadyScheduled(new TimeEvent(t0, true, false))))
	{

	

	t0 = new TimeInstance(5, Unit.seconds);
	
	events.add(new TimeEvent(t0, true, false, targetStatesList, guards));

	}

		if( t1 == null || timeService.getEventsInterestedIn() == null)
		{
		  	t1 = new TimeInstance((Integer)TestCaseHandler.getTestCase().getNextNonDeterministicValue(context_.getInstanceId()*4 + 2), Unit.milliSeconds);
			events.add(new TimeEvent(t1, true, true));	  
		}
				
		timeService.onStateEntry(fromState,  events, context_, this);
 
   	//perform entry actions of state
   	
   
   	//perform do actions on state
   	
   	
		new Thread() 
	   	{
			public void run() {
				//perform the activity
				try{
				  
				
		try{
			context_.setIsConnected ( ((Booting_newActionCode)context_.getAction()).connectToServer());
		}catch(Exception e){
		 context_.addException(e);
Logger.getLogger().error(e); 
	}

				}catch(Exception e){
					context_.addException(e);
					Logger.getLogger().error(e);
				}
		   }
		   
		}.start();
	
   	
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
  
  
  	java.util.Hashtable errorStateApproachLevel = new java.util.Hashtable(1);
 	
  
  
  public Object[] onStateExit(IState toState, Sensor context_)
  {
    
/*
 * Code for calculating & reporting heuristics
 */
 
 	if(resetTimers)
 	{

        ArrayList<TimeEvent> events = timeService.getEventsInterestedIn();
        long stateExitTime = System.currentTimeMillis();
        for(TimeEvent event:events)
        {
            if(event.getTargetState() != toState && !(event.isTimeProbable()))
            {
                java.util.Enumeration en = errorStateApproachLevel.keys();
                java.util.List<IState> targetStatesList = event.getTargetState();
                int index = 0;
                for(IState targetState:targetStatesList)
                {
                    java.util.Hashtable targetStateApproachLevel = targetState.getErrorStateApproachLevel(); 
                    while (en.hasMoreElements()) {
                        int errorId = (Integer) en.nextElement();
                        boolean leadsToError = leadsToErrorState(errorId, this.getErrorStateApproachLevel(), targetStateApproachLevel);
                        int level = (Integer) this.getErrorStateApproachLevel().get(errorId);
                        if(leadsToError)
                        {
                            //report time remaining of this event
                            long timeInState = stateExitTime - stateEntryTime;
                            long timeRequiredByEvent = event.getTimeDurationInMillis();
                            
                            String guard = event.getGuards().get(index);
                          	
                          	boolean constraintVar = false;
        					double constraintVar_bd = Double.MAX_VALUE;
        	
                            if(guard != null && guard.length() > 0)
                            {
                            	constraintVar_bd = calculateBranchDistance(guard, context_);            				
        					}
        					else
        					{
        					  	constraintVar_bd = 0;
        					}

                           //check branch distance of the transition here
                            HeuristicTrace.getTrace().reportTimeDistanceAtLevel(errorId, level, (int)(timeRequiredByEvent - timeInState), constraintVar_bd);
                        }
                    }     
                    index++;
                }
            }
        }     

	  	simula.embt.commons.HeuristicTrace.getTrace().reportTimeSpentInRiskyState(((SensorEnvironmentErrorState)context_.getSensorEnvironmentErrorState()).getErrorId(context_.getInstanceId()), (int)(System.currentTimeMillis() - stateEntryTime));
	  
  	}
	
	/*
     * - End - Code for reporting heuristics
     */
	
   
   //if timeout transition, then call corresponding method of timeService
   	timeService.onStateExit(toState, resetTimers);
   
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
	  	return "Waiting";
	  	
	}
	
	public String[] getStateName()
	{
		return new String[]{"Waiting"};
	}

	public static String getQualifiedStateName()
	{
		return "Waiting";
	}

	public void stopExecution()
	{
		timeService.stopExecution();

	}

    public static int getErrorId(int instanceId)
    {
      
      return instanceId*1 + ;
    }
      
} //end class
