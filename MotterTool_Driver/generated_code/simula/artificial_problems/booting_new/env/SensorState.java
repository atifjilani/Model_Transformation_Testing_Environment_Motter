

/**
 * Auto Generated Code for Environment Simulator
 * Date Generated: 3/8/2016
 * Time: 12:47:8
 */
 
package simula.artificial_problems.booting_new.env;

import simula.embt.simulator.core.IState;
import simula.embt.simulator.core.time.TimeInstance;
import simula.embt.commons.Logger;

import java.util.ArrayList;


public abstract class SensorState implements IState 
{
	
	
  	/* Signal Methods */
  	
    /* Misc. Methods */
    public void onStateEntry(IState fromState, Sensor context_) 
    {
		// 
		
	}

	public Object[] onStateExit(IState toState, Sensor context_) 
	{
		// 
		return null;
		
	}
	
	public String toString()
	{
		return this.getClass().getSimpleName();
	}
	

	abstract public String[] getStateName();
	abstract public String getStateNameSimple();

	
	
	/* Timeout Method */
	     
    public void timeout(TimeInstance time, boolean isRelative, Sensor context_)
    {
		//empty implementation - a check can be added to remove the method if no time event exist in the sub classes
		Logger.getLogger().println("timeout event ignored :: " + time + " for class :: " +this.getClass().getName() );
	} 
	
	public void resetTimer()
	{
	}
	
	     /* Change Event Method */
	     
	public void executeChangeEvent(String condition, Sensor context_)
	{
		//empty implementation - a check can be added to remove the method if no change event exist in the sub classes
	
			
	} 
	
	public String evaluateChangeEvents(Sensor context_)
	{
		//empty implementation - a check can be added to remove the method if no change event exist in the sub classes
		return null;
			
	} 
	
     /* Completion Event */
     
     abstract public void executeCompletionEvent(Sensor context_);

protected void reportBranchDistance(ArrayList<IState> targetStatesList, ArrayList<Double> branchDistances)
 	{
		java.util.Enumeration en;

 	  	for(int index = 0; index < targetStatesList.size(); index ++ )
        {
          	IState targetState = targetStatesList.get(index);
          	en = getErrorStateApproachLevel().keys();
            java.util.Hashtable targetStateApproachLevel = targetState.getErrorStateApproachLevel(); 
  			while(en.hasMoreElements())
  			{
        		int errorId = (Integer)en.nextElement();
            	boolean leadsToError = leadsToErrorState(errorId, this.getErrorStateApproachLevel(), targetStateApproachLevel);
        		if(leadsToError)
        		{
					int level = (Integer)getErrorStateApproachLevel().get(errorId);
					simula.embt.commons.HeuristicTrace.getTrace().reportBranchDistanceAtLevel(errorId, level, branchDistances.get(index));
        		}
  			}
		}
 	}
	
	protected boolean leadsToErrorState(int errorId, java.util.Hashtable selfHt, java.util.Hashtable targetHt)
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


        		      		
} // end of abstract class SensorState

