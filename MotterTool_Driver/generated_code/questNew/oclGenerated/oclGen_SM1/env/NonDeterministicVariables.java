

/**
 * Auto Generated Code for Environment Simulator
 * Date Generated: 3/7/2016
 * Time: 11:9:6
 */
 
 
 
package questNew.oclGenerated.oclGen_SM1.env;

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
	  			
		ndv.add(new TimeProbability((sm_instance*max_ndv)+0));
			
		ndv.add(new TimeProbability((sm_instance*max_ndv)+1));
            			         	
		ndv.add(new TimeProbability((sm_instance*max_ndv)+2, true));

		//ndv with NDChoice stereotype
		ndv.add(new IntegerNDV((sm_instance*max_ndv) +3, 0, 0));

		sm_instance++;
	}

	   	return ndv.toArray(new NonDeterministicVariable[ndv.size()]);
	}
	
}
	