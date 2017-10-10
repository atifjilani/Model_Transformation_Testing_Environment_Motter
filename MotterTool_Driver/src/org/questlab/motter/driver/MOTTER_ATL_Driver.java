package org.questlab.motter.driver;

import org.questlab.motter.atl.driver.ATLDriver;
import org.questlab.motter.oclSolver.driver.OCLDriverforATL;
import org.questlab.motter.uml2instance.driver.InstanceClassDiagramGeneratorDriver;
import questlab.motter.common.driver.ATL_Fitness_Calculator;

 public class MOTTER_ATL_Driver {

	public static void main(String[] args) {
		try {
		//	ArrayList<List<Object>> result;
			////////////////////////////OCL Solver     /////////////////////
			
			String umlFile = "I:/workspace-xp/MotterTool_Driver/OCLSolverInput/Blank Package.uml";
			String objDiagramPath = "I:/workspace-xp/MotterTool_Driver/OCLSolverOutput";
			String oclSolverResultFile=objDiagramPath;
			// Create UML StateMachine from ClassifierTuple
			String arg1[] = new String[2];
			arg1[0]=umlFile; //Path and Name of the Meta-Model to be provided to OCL Solver
			arg1[1]=objDiagramPath; //Path where OCL Solver generated 3 file 2 object diagrams and 1 Value Tuple
	//		OCLDriverforATL.main(arg1);
			
			////////////////////////////   Instance Generator     /////////////////////
			
			String pathforATLTransformation="../MotterTool_Driver/OCLSolverOutput";
			
			String objDiagramFileName = "OCL_GenClassDiagram";
			String arg2[] = new String[3];
			arg2[0]=objDiagramFileName;// Name of the File to be created
			arg2[1]=pathforATLTransformation;// Path where UML file need to be created. 
			arg2[2]=oclSolverResultFile; //Path where OCL file txt is to be read.
			InstanceClassDiagramGeneratorDriver.main(arg2);
			
            ////////////////////////////ATL Transformation     /////////////////////
			       		
		    ATLDriver ATL=new ATLDriver();
			ATL_Fitness_Calculator launch=new ATL_Fitness_Calculator();
			String InputModel=pathforATLTransformation+"/"+objDiagramFileName+".xmi";			
			launch.printCFG(launch.cfgCompare(ATL.executeTransformation(InputModel)));
			launch.CalculateFitness(launch.cfgCompare(ATL.executeTransformation(InputModel)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

