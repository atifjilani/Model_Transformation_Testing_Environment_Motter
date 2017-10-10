package org.questlab.motter.mof;
import simula.embt.simulator.CaseSpecificInputsReader;
import simula.embt.simulator.MofscriptParser;
import simula.embt.simulator.driver.MofScriptDriver;


public class StandaloneMOF_Execution {
	public static void main(String[] args)
	{
		System.setProperty(CaseSpecificInputsReader.PROPERTY_FILE_NAME_PROPERTY, "booting_new/booting_new-user-config.properties");
		System.setProperty(MofscriptParser.TRANSFORMATION_DIR_PROPERTY, "../VERDE_SimulatorGenerator");
		
		try {
			MofScriptDriver.main(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
