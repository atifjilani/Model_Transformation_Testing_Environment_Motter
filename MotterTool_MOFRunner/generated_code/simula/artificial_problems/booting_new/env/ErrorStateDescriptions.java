

/**
 * Auto Generated Code for Environment Simulator
 * Date Generated: 12/1/2017
 * Time: 14:38:1
 */
 
 
 
package simula.artificial_problems.booting_new.env;

import java.util.ArrayList;

import simula.embt.commons.ErrorStateDescription;

public class ErrorStateDescriptions 
{
  /*
   *@param instances holds an array of count of instances of environment components
   */
  
	public static ErrorStateDescription[] getDescription(int instances[])
	{
	  
  	
	int maxErrorStates = 1;
	
	ArrayList<ErrorStateDescription> esd = new ArrayList<ErrorStateDescription>();	
	
	int sm_instance = 0;

	for (int i=0; i<instances[0];i++)
	{
	  
		esd.add(new ErrorStateDescription((sm_instance*maxErrorStates)+0,  1, 1));
		
		sm_instance++;
	}

	   	return esd.toArray(new ErrorStateDescription[esd.size()]);
	}
	
}
	