package org.questlab.motter.mof.driver;

import simula.embt.simulator.CaseSpecificInputsReader;
import simula.embt.simulator.MofscriptParser;
import simula.embt.simulator.driver.MofScriptDriver;

public class MOFDriver {
	public static void main(String[] args) {
		System.out.println("\n-----------MOF Transformation Started----------\n");
          if(args.length==0){
		System.setProperty(
				CaseSpecificInputsReader.PROPERTY_FILE_NAME_PROPERTY,
				"booting_new/booting_new-user-config.properties");
		System.setProperty(MofscriptParser.TRANSFORMATION_DIR_PROPERTY,
				"../VERDE_SimulatorGenerator");
          }
          else
          {
		System.setProperty(
				CaseSpecificInputsReader.PROPERTY_FILE_NAME_PROPERTY,
				"ocl_generated/user_config.properties");
		System.setProperty(MofscriptParser.TRANSFORMATION_DIR_PROPERTY,
				"../VERDE_SimulatorGenerator");
          }
		try {
			MofScriptDriver.main(null);
			System.out.println("\n-----------MOF Transformation Completed---------\n");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
