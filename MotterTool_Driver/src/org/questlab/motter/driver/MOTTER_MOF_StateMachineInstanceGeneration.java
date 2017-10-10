package org.questlab.motter.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.questlab.motter.mof.driver.MOFDriver;
import org.questlab.motter.oclSolver.driver.OCLDriverforInstanceGeneration;
import org.questlab.motter.oclSolver.driver.OCLDriverforMOF;
import org.questlab.motter.uml2instance.driver.InstanceStateMachineGeneratorDriver;
import questlab.motter.common.driver.MOF_Fitness_Calculator;

 @SuppressWarnings("unused")
public class MOTTER_MOF_StateMachineInstanceGeneration {

	public static void main(String[] args) {
		try {
		//	ArrayList<List<Object>> result;
			////////////////////////////OCL Solver     /////////////////////
			
			String umlFile = "E:/OneDrive_Data/OneDrive/workspace-xp/MotterTool_Driver/OCLSolverInput/StateMachineMetaModel-Integer.uml";
			String objDiagramPath = "E:/OneDrive_Data/OneDrive/workspace-xp/MotterTool_Driver/OCLSolverOutput";
			String oclSolverResultFile=objDiagramPath;
			// Create UML StateMachine from ClassifierTuple
			String arg1[] = new String[2];
			arg1[0]=umlFile; //Path and Name of the Meta-Model to be provided to OCL Solver
			arg1[1]=objDiagramPath; //Path where OCL Solver generated 3 file 2 object diagrams and 1 Value Tuple
	//		OCLDriverforInstanceGeneration.main(arg1);
			
			////////////////////////////   Instance Generator     /////////////////////
			CopyFiles("../MotterTool_Driver/ProfileOriginal/Default.profile.uml", "../MotterTool_Driver/OCLSolverOutput/Default.profile.uml");
			CopyFiles("../MotterTool_Driver/ProfileOriginal/Profile.profile.uml", "../MotterTool_Driver/OCLSolverOutput/Profile.profile.uml");
			CopyFiles("../MotterTool_Driver/ProfileOriginal/ProfileBase.profile.uml", "../MotterTool_Driver/OCLSolverOutput/ProfileBase.profile.uml");
			
			String pathforMOFRunner="E:/OneDrive_Data/OneDrive/workspace-xp/MotterTool_Driver/OCLSolverOutput";
//			String pathforMOFRunner="I:/workspace-xp/MotterTool_MOFRunner/OCLSolverOutput";
			
			String objDiagramFileName = "OCL_InstanceGeneratedSM";
			String arg2[] = new String[3];
			arg2[0]=objDiagramFileName;// Name of the File to be created
			arg2[1]=pathforMOFRunner;// Path where UML file need to be created. 
			arg2[2]=oclSolverResultFile; //Path where OCL file txt is to be read.
			InstanceStateMachineGeneratorDriver.main(arg2);
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void CopyFiles(String source, String target)   {	
    	
    	InputStream inStream = null;
	    OutputStream outStream = null;
		
    	try{
    		
    	    File afile =new File(source);
    	    File bfile =new File(target);
    		
    	    inStream = new FileInputStream(afile);
    	    outStream = new FileOutputStream(bfile);
        	
    	    byte[] buffer = new byte[1024];
    		
    	    int length;
    	    //copy the file content in bytes 
    	    while ((length = inStream.read(buffer)) > 0){
    	  
    	    	outStream.write(buffer, 0, length);
    	 
    	    }
    	 
    	    inStream.close();
    	    outStream.close();
    	      
    	 //   System.out.println("File is copied successful!");
    	    
    	}catch(IOException e){
    		e.printStackTrace();
    	}
    }
 }

