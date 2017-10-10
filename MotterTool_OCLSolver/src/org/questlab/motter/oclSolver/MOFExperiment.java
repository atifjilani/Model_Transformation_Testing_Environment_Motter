package org.questlab.motter.oclSolver;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.uml2.uml.EnumerationLiteral;

import snt.oclsolver.datatypes.EnumerationValueTuple;
import snt.oclsolver.datatypes.PrimitiveValueTuple;
import snt.oclsolver.driver.ExperimentMOFStateMachineInstanceGenerationWInt;
import snt.oclsolver.search.SearchAlgorithmEnum;
import snt.oclsolver.tuples.ClassifierTuple;
import snt.oclsolver.tuples.ClassifierValueTuple;
import snt.oclsolver.tuples.IPropertyTuple;
import snt.oclsolver.tuples.ValueTuple;

public class MOFExperiment {
	private static int noOfIterations=100;
	private static int noOfRuns=1; 
	private static SearchAlgorithmEnum SearchAlgo;
	private static String ObjectDiagramPath;
	private static String UMLMetaModel;
	private static OCLSolverTest solver;
	private static String query=null;
	private static ArrayList<List<Object>> QueryTuppleMap;
	public MOFExperiment(){
		noOfIterations=100;
		noOfRuns=1;
		SearchAlgo=SearchAlgorithmEnum.RANDOM;
		query="";
		solver=new OCLSolverTest();
		
	}
	
	public static void setUpExperiment(int NoOFIteration,int NoOFRun,int searchAlgo,String umlMetaModel,String objectDiagramPath ){
		noOfIterations=NoOFIteration;
		noOfRuns=NoOFRun;
		switch (searchAlgo){
        case 0:  
        	SearchAlgo=SearchAlgorithmEnum.RANDOM;
        	break;
        case 1:  
           	SearchAlgo=SearchAlgorithmEnum.AVM;
           	break;
		}
		System.out.println(" Search Algorithm is : "+SearchAlgo.toString());
		//SearchAlgo=searchAlgo;
		ObjectDiagramPath=objectDiagramPath;
		UMLMetaModel=umlMetaModel;
	}
	
	public static void SolveQuery(String Query){
		ArrayList<ClassifierTuple> result=null;
		query=Query;
		solver=new OCLSolverTest();
        result =  solver.test(noOfRuns, noOfIterations, query, SearchAlgo,UMLMetaModel, ObjectDiagramPath);
        retrieveTuppleForState(result);
	}

	public static void SolveQueryWithClassifierTupple(String Query,ArrayList<ClassifierTuple> ct){
		ArrayList<ClassifierTuple> result=null;
		query=Query;
        result =  solver.test(noOfRuns, noOfIterations, query, SearchAlgo,UMLMetaModel, ObjectDiagramPath,ct);
		//return result;
		retrieveTuppleForState(result);
	}
	
	private static ArrayList<List<Object>> retrieveTuppleForState(
			ArrayList<ClassifierTuple> queryVariables) {
		

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
		try{
			PrintWriter writer = new PrintWriter(ObjectDiagramPath+"/OCLSolverResult.txt", "UTF-8");
			for (List<Object> values : QueryTuppleMap) {
				for(Object ob:values){
					writer.print(ob+",");
				}
				writer.println();
			}	
			writer.close();	
			System.out.println("OCL Solver Output File is Generated at :"+ObjectDiagramPath+"/OCLSolverResult.txt");
		}
		catch (IOException e){
			System.out.println(e.getMessage());
		}
		
		for (List<Object> values : QueryTuppleMap) {
                 System.out.println(values+" ");
		}
		System.out.println("\n----------------------OCL SOLVER EXECUTION COMPLETED-----------------------\n");
		return QueryTuppleMap;
	}

	public static void retrieveTuppleForExperiment(
			ArrayList<ClassifierTuple> queryVariables, String filePath ) {
		

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
		try{
			PrintWriter writer = new PrintWriter(filePath, "UTF-8");
			for (List<Object> values : QueryTuppleMap) {
				for(Object ob:values){
					writer.print(ob+",");
				}
				writer.println();
			}	
			writer.close();	
			System.out.println("OCL Solver Output File is Generated at :"+filePath);
		}
		catch (IOException e){
			System.out.println(e.getMessage());
		}
		
/*		for (List<Object> values : QueryTuppleMap) {
                 System.out.println(values+" ");
		}
		System.out.println("\n----------------------OCL SOLVER EXECUTION COMPLETED-----------------------\n");
		return QueryTuppleMap;*/
	}

	public static void generateInstance(String query1) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<ClassifierTuple> result=null;
		query=query1;
		ExperimentMOFStateMachineInstanceGenerationWInt instanceGen=new ExperimentMOFStateMachineInstanceGenerationWInt();
		
	//	solver=new OCLSolverTest();
        result =  instanceGen.instanceGeneration(noOfRuns, noOfIterations, query, SearchAlgo,UMLMetaModel, ObjectDiagramPath);
        retrieveTuppleForState(result);
	}
	
}
