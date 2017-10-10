

/**
 * Auto Generated Code for Environment Simulator
 * Date Generated: 18/5/2015
 * Time: 9:59:49
 */
 
 
 
package simula.artificial_problems.booting_new.env;

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
	  
  	
	int max_ndv = 4;
	
	ArrayList<NonDeterministicVariable> ndv = new ArrayList<NonDeterministicVariable>();	
	
	int sm_instance = 0;

	for (int i=0; i<instances[0];i++)
	{
	  
		//for <<nondeterministic>> attributes
		ndv.add(new IntegerNDV((sm_instance*max_ndv)+0, 100,  3000));
            			         	
		ndv.add(new TimeProbability((sm_instance*max_ndv)+1, true));
            			         	
		ndv.add(new TimeProbability((sm_instance*max_ndv)+2, true));
            			         	
		ndv.add(new TimeProbability((sm_instance*max_ndv)+3, true));

		sm_instance++;
	}

	   	return ndv.toArray(new NonDeterministicVariable[ndv.size()]);
	}
	
}
	