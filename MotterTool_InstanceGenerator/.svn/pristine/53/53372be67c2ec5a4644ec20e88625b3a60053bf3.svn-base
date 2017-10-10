package org.questlab.motter.uml2instance.driver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.questlab.motter.uml2instance.UMLInstanceGenerator;


 public class InstanceStateMachineGeneratorDriver {

	public static void main(String[] args) {
		try {
			ArrayList<List<Object>> result;
			String objDiagramPath = null;
			String oclSolverResultFile=null;
			String objDiagramFileName = null;
			if(args.length==0){
				objDiagramFileName = "OCL_GenSM";//Name of the UML File to be created
				objDiagramPath = "I:/workspace-xp/MotterTool_Driver/OCLSolverOutput";//path where the file is to be placed
				oclSolverResultFile=objDiagramPath+"/OCLSolverResult.txt";//path where OCL generated file is to be read
			}
			else{
				objDiagramFileName=args[0];
				objDiagramPath = args[1];
				oclSolverResultFile=args[2]+"/OCLSolverResult.txt";
			}
			

			result=readOCLGeneratedTupples(oclSolverResultFile);
			
			UMLInstanceGenerator s = new UMLInstanceGenerator();
			s.retrieveTupple(result);
			s.generateUMLStateMachine(objDiagramFileName, objDiagramPath);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static ArrayList<List<Object>> readOCLGeneratedTupples(String filepath){
		ArrayList<List<Object>> result = new ArrayList<List<Object>>();	
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filepath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    try {
		        StringBuilder sb = new StringBuilder();
		        String line = br.readLine();
		       // System.out.println(line);
		        while (line != null) {
		            sb.append(line);  
		            List<Object> myList=new ArrayList<Object>() ;
		           String array[]= line.split(",");
		          for(String s:array){
		        	  myList.add((Object)s);
		          }
//		          if(myList.size()<5)
//			        	 myList.add("");
		          result.add(myList);
		            line = br.readLine();
		        }
		     //   String everything = sb.toString();
		    } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    finally {
		        try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		
		return result;
	
	}
}

