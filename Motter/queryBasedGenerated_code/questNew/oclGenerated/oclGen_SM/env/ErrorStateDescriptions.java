

/**
 * Auto Generated Code for Environment Simulator
 * Date Generated: 29/9/2016
 * Time: 21:29:2
 */
 
 
 
package questNew.oclGenerated.oclGen_SM.env;

import java.util.ArrayList;

import simula.embt.commons.ErrorStateDescription;

public class ErrorStateDescriptions 
{
  /*
   *@param instances holds an array of count of instances of environment components
   */
  
	public static ErrorStateDescription[] getDescription(int instances[])
	{
	  
  	
	int maxErrorStates = 0;
	
	ArrayList<ErrorStateDescription> esd = new ArrayList<ErrorStateDescription>();	
	
	int sm_instance = 0;

	  	for (int i=0; i<instances[0];i++)
		{
		  
		  sm_instance++;
		}
		
	   	return esd.toArray(new ErrorStateDescription[esd.size()]);
	}
	
}
	