

/**
 * Auto Generated Code for Environment Simulator
 * Date Generated: 29/9/2016
 * Time: 21:29:2
 */
 
 
 
package questNew.oclGenerated.oclGen_SM.env;

import java.util.ArrayList;

import simula.embt.commons.ndv.IntegerNDV;
import simula.embt.commons.ndv.NonDeterministicVariable;
import simula.embt.commons.ndv.TimeProbability;

public class NonDeterministicVariables 
{
  /*
   *@param instances holds an array of count of instances of environment components
   */
  
	public static NonDeterministicVariable[] getDescription(int instances[])
	{
	  
  	
	int max_ndv = 0;
	
	ArrayList<NonDeterministicVariable> ndv = new ArrayList<NonDeterministicVariable>();	
	
	int sm_instance = 0;

	for (int i=0; i<instances[0];i++)
	{
	  
	  sm_instance++;
	}
		
	   	return ndv.toArray(new NonDeterministicVariable[ndv.size()]);
	}
	
}
	