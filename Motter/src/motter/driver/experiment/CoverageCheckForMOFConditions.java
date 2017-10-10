package motter.driver.experiment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.uml2.uml.EnumerationLiteral;
import org.questlab.motter.uml2instance.UMLInstanceGenerator;
import org.questlab.motter.uml2instance.driver.InstanceStateMachineGeneratorDriver;

import simula.embt.simulator.CaseSpecificInputsReader;
import simula.embt.simulator.MofscriptParser;
import simula.embt.simulator.driver.MofScriptDriver;

import snt.oclsolver.datatypes.EnumerationValueTuple;
import snt.oclsolver.datatypes.PrimitiveValueTuple;
import snt.oclsolver.distance.ClassDiagramTestData;
import snt.oclsolver.distance.FitnessCalculator;
import snt.oclsolver.distance.Problem;
import snt.oclsolver.distance.SimpleProblem;
import snt.oclsolver.driver.FinalDriver;
import snt.oclsolver.driver.SolverRunner;
import snt.oclsolver.reader.ObjScriptGenerator;
import snt.oclsolver.search.Individual;
import snt.oclsolver.search.Search;
import snt.oclsolver.tuples.ClassifierTuple;
import snt.oclsolver.tuples.ClassifierValueTuple;
import snt.oclsolver.tuples.IPropertyTuple;
import snt.oclsolver.tuples.ValueTuple;

/**
 * A Class which drives the execution of the experiment.
 * 
 * @author Atif Jilani
 * @version 1.0
 */
public class CoverageCheckForMOFConditions {

	public static void main(String s[]) throws Exception {

		// SolveConditionsAndGenerateResults(s);
//		String q="Class.allInstances()->exists(c|c.ownedBehavior->exists(o| o.oclAsType(StateMachine).region->exists(r| r.oclAsType(Region).transition->exists(t| t.oclAsType(Transition).source.oclIsTypeOf(Pseudostate) and t.oclAsType(Transition).target.oclIsTypeOf(State) and t.oclAsType(Transition).stereotypeTimeProbability > 0))))";
		String q="Class.allInstances()->forAll(c|c.ownedBehavior->forAll(o| o.oclIsTypeOf(StateMachine))) " 
		    		+ "and StateMachine.allInstances()->forAll(sm|sm.region->forAll(r| r.oclIsTypeOf(Region))) "  
					+ "and Region.allInstances()->forAll(r|r.transition->forAll(t| t.oclIsTypeOf(Transition))) "
					+ "and Transition.allInstances()->exists(t|t.source.oclIsTypeOf(Pseudostate) and t.target.oclIsTypeOf(State) and " +
					  "t.stereotypeTimeProbability>0 )";
//		solveConditionAndGenerateResult(q, 1);
		int qno=1;
	createMOFInputModel("QueryOutputNew/SolverResultQuery"+qno+".txt","OCL_Query"+qno+"SM");
//		executeMOFTransformation(1);
		
		
		
	}
	public static void executeMOFTransformation(int mode){
		System.out.println("\n-----------MOF Transformation Started----------\n");
        if(mode==0){
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
	public static void createMOFInputModel(String oclSolverResultFile,String objDiagramFileName ){
		CopyFiles("../MotterTool_Driver/ProfileOriginal/Default.profile.uml", "../Motter/MOFInput/Default.profile.uml");
		CopyFiles("../MotterTool_Driver/ProfileOriginal/Profile.profile.uml", "../Motter/MOFInput/Profile.profile.uml");
		CopyFiles("../Motter/MOFInput/ProfileBase.profile.uml", "../Motter/MOFInput/ProfileBase.profile.uml");
		// Path where UML file need to be created. 
		String pathforMOFRunner="MOFInput";

//		objDiagramFileName= "OCL_QuerySM";// Name of the File to be created
		UMLInstanceGenerator s = new UMLInstanceGenerator();
		s.retrieveTupple(readOCLGeneratedTupples(oclSolverResultFile));
		s.generateUMLStateMachine(objDiagramFileName, pathforMOFRunner);
		CopyFiles("../Motter/MOFInput/"+objDiagramFileName+".uml", "../Motter/resources/ocl_generated/OCL_GenSM.uml");
	}
	@SuppressWarnings("unused")
	public static void solveConditionsAndGenerateResults(String s[]) {
		ArrayList<String> queries = new ArrayList<String>();
		int numberOfRuns = 0;
		int startR = -1, endR = -1;
		try {
			s = new String[9];
			s[0] = "" + 00;
			s[1] = "" + 00;

			startR = Integer.parseInt(s[0]);
			endR = Integer.parseInt(s[1]);
			numberOfRuns = Integer.parseInt(s[1]);

			String fin = "AVM-MOFConditions-0-0.txt";
			ArrayList<String> Myarray = new ArrayList<String>();
			BufferedReader br = new BufferedReader(new FileReader(fin));

			String line = "";
			while ((line = br.readLine()) != null) {
				// System.out.println(line);
				Myarray.add(line);
			}

			br.close();

			for (String query : Myarray) {
				queries.add(query);
			}
			ArrayList<ClassifierTuple> oldCTuples = new ArrayList<ClassifierTuple>();
			// Search[] SA = new Search[] { new snt.oclsolver.search.AVM(),new
			// snt.oclsolver.search.OpOEA(),new snt.oclsolver.search.SSGA(),};
			// Search[] SA = new Search[] { new snt.oclsolver.search.SSGA()};
			// Search[] SA = new Search[] { new
			// snt.oclsolver.search.RandomSearch()};
			// Search[] SA = new Search[] { new snt.oclsolver.search.OpOEA() };
			Search[] SA = new Search[] { new snt.oclsolver.search.AVM() };
			// Search[] SA = new Search[] { new snt.oclsolver.search.AVM(),new
			// snt.oclsolver.search.SSGA()};

			// String
			// filePath="examples/MOTTERExperiment/StateMachineMetaModel.uml";
			String filePath = "MOTTERExperiment/StateMachineMetaModel-Inst-Integer.uml";

			int k = 1;

			for (Search sv : SA) {

				ClassDiagramTestData.getInstance().reset(filePath);

				ObjScriptGenerator driver = new ObjScriptGenerator(filePath);
				driver.generateScript();

				BufferedWriter file = new BufferedWriter(new FileWriter(
						sv.getShortName() + "-MOFExpTRy-" + startR + "-"
								+ numberOfRuns + ".txt"));

				for (int k1 = 0; k1 < queries.size(); k1++) // Problem
				{
					k = 1;
					for (int i = startR; i <= endR; i++) // runs
					{
						// ClassDiagramTestData.getInstance().reset(filePath);
						FinalDriver obj = new FinalDriver();
						ClassDiagramTestData.getInstance().setFileNames(
								obj.getclassDiag(), obj.getObjDiag());
						Problem p = new SimpleProblem(queries.get(k1),
								obj.getclassDiag(), obj.getObjDiag(),
								ClassDiagramTestData.umlFileURI, oldCTuples);
						Problem.k = k;

						file.write(queries.get(k1) + "\n");
						SolverRunner exp = new SolverRunner();
						exp.setIndividualType("simple");
						sv.iFactory.setType("simple");
						sv.setMaxIterations(1000);
						sv.current_iteration = 0;
						long start = System.currentTimeMillis();
						sv.search(p);
						boolean found = p.getFitness() == 0d;

						int steps = sv.getIteration();
						long elapsedTimeMillis = System.currentTimeMillis()
								- start;
						float elapsedTimeSec = elapsedTimeMillis / 1000F;
						file.write(sv.getShortName());
						file.write("\tBreak");
						file.write(" \t " + i);
						file.write(" \t " + (k1));
						file.write(" \t " + steps);
						file.write(" \t " + found);
						file.write(" \t " + elapsedTimeSec + "\r\n");
						file.flush();

						if (found) {
							String objDiagramPath = "QueryOutputNew";
							String objDiagramFileName = objDiagramPath
									+ "/SolverResultQuery" + k1 + ".txt";
							retrieveTuppleForExperiment(p.getQueryVariables(),
									objDiagramFileName);
						}
						resetStaticMembers();
						k++;
					}

				}
				file.close();
			}
		} catch (Exception e) {

			System.out.println("Exception1");
			e.printStackTrace();
			return;
		}

	}
	public static void solveConditionAndGenerateResult(String query,int k1) {

			ArrayList<ClassifierTuple> oldCTuples = new ArrayList<ClassifierTuple>();
			// Search[] SA = new Search[] { new snt.oclsolver.search.AVM(),new
			// snt.oclsolver.search.OpOEA(),new snt.oclsolver.search.SSGA(),};
			// Search[] SA = new Search[] { new snt.oclsolver.search.SSGA()};
			// Search[] SA = new Search[] { new
			// snt.oclsolver.search.RandomSearch()};
			// Search[] SA = new Search[] { new snt.oclsolver.search.OpOEA() };
			Search[] SA = new Search[] { new snt.oclsolver.search.AVM() };
			// Search[] SA = new Search[] { new snt.oclsolver.search.AVM(),new
			// snt.oclsolver.search.SSGA()};

			// String
			// filePath="examples/MOTTERExperiment/StateMachineMetaModel.uml";
			String filePath = "MOTTERExperiment/StateMachineMetaModel-Inst-Integer.uml";
			try {
			int k = 1;

			for (Search sv : SA) {

				ClassDiagramTestData.getInstance().reset(filePath);

				ObjScriptGenerator driver = new ObjScriptGenerator(filePath);
				driver.generateScript();	

						FinalDriver obj = new FinalDriver();
						ClassDiagramTestData.getInstance().setFileNames(
								obj.getclassDiag(), obj.getObjDiag());
					
						Problem p = new SimpleProblem(query,
								obj.getclassDiag(), obj.getObjDiag(),
								ClassDiagramTestData.umlFileURI, oldCTuples);
						Problem.k = k;
						SolverRunner exp = new SolverRunner();
						exp.setIndividualType("simple");
						sv.iFactory.setType("simple");
						sv.setMaxIterations(1000);
						sv.current_iteration = 0;
						long start = System.currentTimeMillis();
						sv.search(p);
						boolean found = p.getFitness() == 0d;
						int steps = sv.getIteration();
						long elapsedTimeMillis = System.currentTimeMillis()
								- start;
						float elapsedTimeSec = elapsedTimeMillis / 1000F;

						if (found) {
							String objDiagramPath = "QueryOutputNew";
							String objDiagramFileName = objDiagramPath
									+ "/SolverResultQuery" + k1 + ".txt";
							retrieveTuppleForExperiment(p.getQueryVariables(),
									objDiagramFileName);
						}
						resetStaticMembers();
						k++;
				
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	private static void resetStaticMembers() {
		// Problem.tempTuples=new ArrayList<TempTuple>();
		// Problem.ClassToPropMap=new HashMap<String, ArrayList<String>>();
		// Problem.criticalTuples=new ArrayList<ClassifierTuple>();
		// Problem.orderedQueryClasses=new ArrayList<QueryClass>();
		// Problem.queryWithConstants=new Hashtable<String,
		// ArrayList<Object>>();
		// Problem.queryWithOperators=new Hashtable<String,
		// ArrayList<QueryType>>();
		// Individual.refreshCount=0;
		// FitnessCalculator.queryVariables = new ArrayList<ClassifierTuple>();
		Problem.tempTuples.clear();
		Problem.ClassToPropMap.clear();
		Problem.criticalTuples.clear();
		Problem.orderedQueryClasses.clear();
		Problem.queryWithConstants.clear();
		Problem.queryWithOperators.clear();
		Problem.queryWithObjOpsList.clear();
		Individual.refreshCount = 0;
		FitnessCalculator.queryVariables.clear();
	}
	private static void retrieveTuppleForExperiment(
			ArrayList<ClassifierTuple> queryVariables, String filePath) {
		ArrayList<List<Object>> QueryTuppleMap;

		QueryTuppleMap = new ArrayList<List<Object>>();
		for (ClassifierTuple ct : queryVariables) {

			ArrayList<ClassifierValueTuple> cvts = ct.getObjectTuples();
			ArrayList<IPropertyTuple> propTup = ct.getValueTuples();
			for (ClassifierValueTuple cvt : cvts) {

				ArrayList<ValueTuple> vts = cvt.getAttributeValues();
				if (vts.size() == 0) {
					Object Class = ct.getClassName(); // size of objects
					// constraint
					Object obj = cvt.getObjectName();
					// System.out.print(Class + " ");
					// System.out.print(obj + " ");

					List<Object> objList = new ArrayList<Object>();
					objList.add(Class);
					objList.add(obj);
					/*
					 * if(obj.toString().contains("-")){ String
					 * org[]=obj.toString().split("-"); //
					 * System.out.println(org
					 * [0]+"------------"+org[1]+"------------"+org[2]);
					 * objList.add(org[0]); objList.add(org[2]); }
					 */
					QueryTuppleMap.add(objList);
					// System.out.println("\n");
				}
				for (ValueTuple vt : vts) {
					if (vt instanceof PrimitiveValueTuple) {
						Object val;
						if (propTup.contains(vt.getRelatedProperty())) {
							PrimitiveValueTuple ivt = (PrimitiveValueTuple) vt;
							if (ivt instanceof EnumerationValueTuple) {
								val = ((EnumerationLiteral) ivt.getValue())
										.getQualifiedName();
							} else {
								val = ivt.getValue();
							}
							Object attribute = ivt.getRelatedProperty()
									.getRoleeName();
							Object type = ivt.getRelatedProperty().getType();
							Object obj = cvt.getObjectName();
							Object Class = ct.getClassName();// primitive type
							// constraints

							// System.out.print(Class + " ");
							// System.out.print(obj + " ");
							// System.out.print(type + " ");
							// System.out.print(attribute + " ");
							// System.out.print(val + " ");
							// System.out.print("\n");

							List<Object> objList = new ArrayList<Object>();
							objList.add(Class);
							objList.add(obj);
							objList.add(type);
							objList.add(attribute);
							objList.add(val);
							// if (obj.toString().contains("-")) {
							// String org[] = obj.toString().split("-");
							// //
							// System.out.println(org[0]+"------------"+org[1]+"------------"+org[2]);
							// objList.add(org[0]);
							// objList.add(org[2]);
							// }

							QueryTuppleMap.add(objList);

						}
					} else {

						if (propTup.contains(vt.getRelatedProperty())) {
							ClassifierValueTuple ivt = (ClassifierValueTuple) vt;
							Object val = ivt.getObjectName();
							Object attribute = ivt.getRelatedProperty()
									.getRoleeName();
							Object type = ivt.getRelatedProperty().getType();
							Object obj = cvt.getObjectName();
							Object Class = ct.getClassName();

							// System.out.print(Class + " ");
							// System.out.print(obj + " "); // navigational
							// // constraints
							// System.out.print(type + " ");
							// System.out.print(attribute + " ");
							// System.out.print(val + " ");
							// System.out.print("\n");
							List<Object> objList = new ArrayList<Object>();
							objList.add(Class);
							objList.add(obj);
							objList.add(type);
							objList.add(attribute);
							objList.add(val);
							// if (obj.toString().contains("-")) {
							// String org[] = obj.toString().split("-");
							// //
							// System.out.println(org[0]+"------------"+org[1]+"------------"+org[2]);
							// objList.add(org[0]);
							// objList.add(org[2]);
							// }
							QueryTuppleMap.add(objList);
						}
					}
				}
			}
			// System.out.println();

		}
		try {
			PrintWriter writer = new PrintWriter(filePath, "UTF-8");
			for (List<Object> values : QueryTuppleMap) {
				for (Object ob : values) {
					writer.print(ob + ",");
				}
				writer.println();
			}
			writer.close();
			System.out.println("OCL Solver Output File is Generated at :"
					+ filePath);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		/*
		 * for (List<Object> values : QueryTuppleMap) {
		 * System.out.println(values+" "); } System.out.println(
		 * "\n----------------------OCL SOLVER EXECUTION COMPLETED-----------------------\n"
		 * ); return QueryTuppleMap;
		 */
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
