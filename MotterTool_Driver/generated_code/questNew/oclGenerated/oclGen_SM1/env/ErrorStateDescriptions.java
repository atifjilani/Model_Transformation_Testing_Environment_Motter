

/**
 * Auto Generated Code for Environment Simulator
 * Date Generated: 3/7/2016
 * Time: 11:9:6
 */
 
 
 
package questNew.oclGenerated.oclGen_SM1.env;

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
	