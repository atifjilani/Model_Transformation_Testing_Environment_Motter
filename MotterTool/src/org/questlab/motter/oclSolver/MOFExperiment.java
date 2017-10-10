package org.questlab.motter.oclSolver;

import java.util.ArrayList;

import snt.oclsolver.search.SearchAlgorithmEnum;
import snt.oclsolver.tuples.ClassifierTuple;

public class MOFExperiment {
	private int noOfIterations=100;
	private int noOfRuns=1; 
	private SearchAlgorithmEnum SearchAlgo;
	private String ObjectDiagramPath;
	private String UMLMetaModel;
	private OCLSolverTest solver;
	private String query=null;
	public MOFExperiment(){
		noOfIterations=100;
		noOfRuns=1;
		SearchAlgo=SearchAlgorithmEnum.RANDOM;
		query="";
		solver=new OCLSolverTest();
		
	}
	
	public void setUpExperiment(int NoOFIteration,int NoOFRun,SearchAlgorithmEnum searchAlgo,String umlMetaModel,String objectDiagramPath ){
		noOfIterations=NoOFIteration;
		noOfRuns=NoOFRun;
		SearchAlgo=searchAlgo;
		ObjectDiagramPath=objectDiagramPath;
		UMLMetaModel=umlMetaModel;
	}
	
	public ArrayList<ClassifierTuple> SolveQuery(String Query){
		ArrayList<ClassifierTuple> result=null;
		query=Query;
        result =  solver.test(noOfRuns, noOfIterations, query, SearchAlgo,UMLMetaModel, ObjectDiagramPath);
		return result;
	}
	public ArrayList<ClassifierTuple> SolveQueryWithClassifierTupple(String Query,ArrayList<ClassifierTuple> ct){
		ArrayList<ClassifierTuple> result=null;
		query=Query;
        result =  solver.test(noOfRuns, noOfIterations, query, SearchAlgo,UMLMetaModel, ObjectDiagramPath,ct);
		return result;
	}
}
