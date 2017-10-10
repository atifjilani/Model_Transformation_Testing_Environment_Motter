package motter.driver.experiment;
import java.io.*;

import snt.oclsolver.distance.FitnessCalculator;
import snt.oclsolver.distance.Problem;
import snt.oclsolver.distance.ClassDiagramTestData;
import snt.oclsolver.distance.SimpleProblem;
import snt.oclsolver.driver.FinalDriver;
import snt.oclsolver.driver.SolverRunner;
import snt.oclsolver.reader.ObjScriptGenerator;
import snt.oclsolver.search.Individual;
import snt.oclsolver.search.Search;
import snt.oclsolver.tuples.ClassifierTuple;
import snt.oclsolver.tuples.TempTuple;
import snt.oclsolver.distance.QueryType;
import snt.oclsolver.QueryClass;
import java.util.*;
/**
 * A Class which drives the execution of the experiment.
 * 
 * @author Atif Jilani
 * @version 1.0
 */
public class ExperimentMOFwithInteger 
{


	public static void main(String s[]) throws Exception
	{
		ArrayList<String> queries = new ArrayList<String>();
		int numberOfRuns=0;
		int startR=-1, endR=-1;
		try
		{
			s = new String[9];
			s[0] = ""+00;
			s[1] = ""+00;

			startR= Integer.parseInt(s[0]);
			endR= Integer.parseInt(s[1]);
			numberOfRuns = Integer.parseInt(s[1]);


		


		//		 specifying the constraints 

		//[1]
		String fin="AVM-MOFConditions-0-0.txt";
		ArrayList<String> Myarray=new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(fin));
		 
		String line = "";
		while ((line = br.readLine()) != null) {
//			System.out.println(line);
			Myarray.add(line);
		}
	 
		br.close();
		
		for (String query : Myarray){
			queries.add(query);
		}
		String constraint1=		
				"Pseudostate.allInstances()->exists(p|p.stereotypeNDChoice>0)" +
				" and Class.allInstances()->exists(c|c.ownedAttribute->exists(o|o.oclIsTypeOf(Property)))" +
				" and Property.allInstances()->exists (p|p.stereotypeNonDeterministic>0)" +
				"  and Class.allInstances()->exists(c|c.ownedBehavior->exists(o| o.oclIsTypeOf(StateMachine)))" +
				" and StateMachine.allInstances()->exists(sm|sm.region->exists(r| r.oclIsTypeOf(Region)))" +
				" and Region.allInstances()->exists(r|r.transition->exists(t| t.oclIsTypeOf(Transition)))" 
			   +" and Transition.allInstances()->exists(t|t.source.oclIsTypeOf(Pseudostate) and t.target.oclIsTypeOf(State) and t.stereotypeTimeProbability>0 )"
		       +" and State.allInstances()->size()>0"        
		;
	String q2=	"State.allInstances()->exists(st|(st.region->size()>1))" +
			" and Region.allInstances()->exists(r|(r.subvertex->select(oclIsTypeOf(State))->collect(oclAsType(State))->select(isComposite=true)->size() > 1))";
//		queries.add(q2);
		

		
		ArrayList<ClassifierTuple> oldCTuples= new ArrayList<ClassifierTuple>();
//		Search[] SA = new Search[] { new snt.oclsolver.search.AVM(),new snt.oclsolver.search.OpOEA(),new snt.oclsolver.search.SSGA(),};
//    	Search[] SA = new Search[] { new snt.oclsolver.search.SSGA()};
//		Search[] SA = new Search[] { new snt.oclsolver.search.RandomSearch()};
    	Search[] SA = new Search[] { new snt.oclsolver.search.OpOEA()};
//  	    Search[] SA = new Search[] { new snt.oclsolver.search.AVM()};
//     	Search[] SA = new Search[] { new snt.oclsolver.search.AVM(),new snt.oclsolver.search.SSGA()};

//		String filePath="examples/MOTTERExperiment/StateMachineMetaModel.uml";
		String filePath = "MOTTERExperiment/StateMachineMetaModel-Inst-Integer.uml";

		int k=1;

		for(Search sv : SA)
		{

			ClassDiagramTestData.getInstance().reset(filePath);

			ObjScriptGenerator driver = new ObjScriptGenerator(filePath);
			driver.generateScript();
			
			BufferedWriter file = new BufferedWriter(new FileWriter(
					sv.getShortName() + "-MOFExpTRy-"+ startR + "-"
							+ numberOfRuns + ".txt"));
			
			for (int k1 = 0; k1 < queries.size(); k1++) // Problem
			{
				k=1;
				for (int i = startR; i <=endR; i++) // runs
				{  
//					ClassDiagramTestData.getInstance().reset(filePath);
					FinalDriver obj = new FinalDriver();
					ClassDiagramTestData.getInstance().setFileNames(obj.getclassDiag(), obj.getObjDiag());
					Problem  p=  new SimpleProblem(queries.get(k1),obj.getclassDiag(),obj.getObjDiag(),ClassDiagramTestData.umlFileURI,oldCTuples);
					p.k=k;
					file.write(queries.get(k1)+"\n");
					SolverRunner exp = new SolverRunner();	
					exp.setIndividualType("simple");
					sv.iFactory.setType("simple");
					sv.setMaxIterations(1000);
					sv.current_iteration=0;
					long start = System.currentTimeMillis();
					sv.search(p);
					boolean found = p.getFitness()==0d;


					int steps = sv.getIteration();
					long elapsedTimeMillis = System.currentTimeMillis()-start;
					float elapsedTimeSec = elapsedTimeMillis/1000F;
					file.write(sv.getShortName());
					file.write("\tBreak");
					file.write(" \t " + i);
					file.write(" \t " + (k1));
					file.write(" \t " + steps);
					file.write(" \t " + found);
					file.write(" \t " + elapsedTimeSec+"\r\n");					
					file.flush();
					resetStaticMembers();
					k++;
				}
				
			}
			file.close();
		}
		}
		catch(Exception e)
		{

			System.out.println("Exception1");
			e.printStackTrace();
			return;
		}

		System.out.println("\n\n\t================================= Execution Completed========================");
	}
	private static void resetStaticMembers() {
		//		Problem.tempTuples=new ArrayList<TempTuple>();
		//		Problem.ClassToPropMap=new HashMap<String, ArrayList<String>>();
		//		Problem.criticalTuples=new ArrayList<ClassifierTuple>();
		//		Problem.orderedQueryClasses=new ArrayList<QueryClass>();
		//		Problem.queryWithConstants=new Hashtable<String, ArrayList<Object>>();
		//		Problem.queryWithOperators=new Hashtable<String, ArrayList<QueryType>>();
		//		Individual.refreshCount=0;
		//		FitnessCalculator.queryVariables = new ArrayList<ClassifierTuple>();
		Problem.tempTuples.clear();
		Problem.ClassToPropMap.clear();
		Problem.criticalTuples.clear();
		Problem.orderedQueryClasses.clear();
		Problem.queryWithConstants.clear();
		Problem.queryWithOperators.clear();
		Problem.queryWithObjOpsList.clear();
		Individual.refreshCount=0;
		FitnessCalculator.queryVariables.clear();
	}	
}




