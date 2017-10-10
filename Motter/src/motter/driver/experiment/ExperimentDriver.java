package motter.driver.experiment;

import java.io.*;
import java.math.BigDecimal;

import questlab.motter.CFG.CFG;
import questlab.motter.CFG.CFGATLDriverU;
import questlab.motter.CFG.DecisionNode;
import questlab.motter.CFG.Node;
import questlab.motter.common.driver.TryCFG;
import questlab.motter.mofscript.CFG.MOFMain;
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
import java.util.*;

/**
 * A Class which drives the execution of the Experiment.
 * 
 * @author Atif Jilani
 * @version 1.0
 */
@SuppressWarnings("unused")
public class ExperimentDriver {

	@SuppressWarnings("unchecked")
	public static void main(String s[]) throws Exception {
		
//		String filePath = "MOTTERExperiment/Blank Package_int.uml";
		String filePath = "MOTTERExperiment/StateMachineMetaModel-Inst-Integer.uml";
		ClassDiagramTestData.getInstance().reset(filePath);
		ObjScriptGenerator driver = new ObjScriptGenerator(
				filePath);
		driver.generateScript();
//	    CFGATLDriverU t=new CFGATLDriverU();
//		TryCFG t = new TryCFG();
		CFG TP=MOFMain.mainMethod();
//		CFG TP = t.funRulePersistentClass2Table();
		TP.dfs(TP.getRootNode());

		// TP.BFS();

		int size = TP.getDecisionNodesSize();
		ConditionTracker[] cts = new ConditionTracker[size];
		//System.out.println("Size: " + size);
		
		for (int ii = 0; ii < size; ii++) {
			DecisionNode nd = (DecisionNode) TP.getDecisionNodeAt(ii);
			cts[ii] = new ConditionTracker(nd.getcondition().getDecstatement());
			cts[ii].setFalseCondition(nd.getFalseCondition().getDecstatement());

			Node n1 = nd.getElseNode();
			if (n1 != null) {
				n1 = nd.getNextDecisionNode(n1);
				if (n1 != null) {
					DecisionNode falseC = (DecisionNode) n1;
					ConditionTracker ctF = new ConditionTracker(falseC
							.getcondition().getDecstatement());
					ctF.setParent(cts[ii]);
					cts[ii].setFalsePathCondition(ctF);
				}
			}
			n1 = nd.getThenNode();
			if (n1 != null) {
				n1 = nd.getNextDecisionNode(n1);
				if (n1 != null) {
					DecisionNode trueC = (DecisionNode) n1;
					ConditionTracker ctT = new ConditionTracker(trueC
							.getcondition().getDecstatement());
					ctT.setParent(cts[ii]);
					cts[ii].setTruePathCondition(ctT);
				}
			}

//			System.out.println("Condition: " + nd.getNodeId() + " - "
//					+ nd.getcondition().getDecstatement());
			ArrayList<Node> pnd = TP.getDecisionNodeParentsAt(ii);

//			System.out.println("ParentConditions: " + pnd.size());
			boolean parentSetted = false;
			for (Node n : pnd) {
//				System.out.println("ParentSt: " + n.getNodeId() + " - "
//						+ ((DecisionNode) n).getcondition().getDecstatement());
				if(!parentSetted){
					cts[ii].setParent(new ConditionTracker(((DecisionNode) n).getcondition().getDecstatement()));
					parentSetted = true;
				}
			}
//			System.out.println();

//			cts[i].print();
		}
		cts[0].createParentChildConnection(cts);
		
		int numberOfRuns = 0;
		int totalIterations = 500*(size*2);
		int maxiterationperConstraint=totalIterations/(size*2);
		int startR = -1, endR = -1;
		try {
			s = new String[9];
			s[0] = "" + 1;
			s[1] = "" + 1;

			startR = Integer.parseInt(s[0]);
			endR = Integer.parseInt(s[1]);
			numberOfRuns = Integer.parseInt(s[1]);
		
//		Search[] SA = new Search[] { new snt.oclsolver.search.AVM() };
		Search[] SA = new Search[] { new snt.oclsolver.search.OpOEA()};
//		Search[] SA = new Search[] { new snt.oclsolver.search.SSGA()};
//		Search[] SA = new Search[] { new snt.oclsolver.search.RandomSearch()};
//		Search[] SA = new Search[] { new snt.oclsolver.search.AVM(),new snt.oclsolver.search.OpOEA(), new snt.oclsolver.search.SSGA(), new snt.oclsolver.search.RandomSearch()  };
//		Search[] SA = new Search[] { new snt.oclsolver.search.AVM(), new snt.oclsolver.search.SSGA()  };
		for (Search sv : SA) {
			int k = 50;
			BufferedWriter file = new BufferedWriter(new FileWriter(
					sv.getShortName()+"-MOFConditions-" + startR + "-"
							+ numberOfRuns + ".txt"));
			for (int i = startR; i <= endR; i++) { // runs
				int currentIterationsConsumed = 0;
				boolean breakOnIterations = false;
				long Covstart = System.currentTimeMillis();
				
				for (int c = 0; c < size; c++) {
					String pCon = cts[c].getParentCondition();
					for (int d = 0; d <= 1; d++) {
						
						String constraint1 = null;
						if (d == 0) { // in true case
							if(pCon != null && !pCon.equals("")){
								constraint1 = cts[c].getCondition()+" and "+pCon;
							
								if ((cts[c].getParent().getTruePathCondition()!=null && cts[c].getParent().getTruePathCondition().equals(cts[c]) && (cts[c].getParent()).isExecutedSolutionNotFoundTruePath()) ||(cts[c].getParent().getFalsePathCondition()!=null && cts[c].getParent().getFalsePathCondition().equals(cts[c]) && (cts[c].getParent()).isExecutedSolutionNotFoundFalsePath()) )
							    	continue;
							}else
								constraint1 = cts[c].getCondition();
							cts[c].setCurrentExecution(true);
						}
						if (d == 1) { // in false case
							if(pCon != null && !pCon.equals("")){
								constraint1 = cts[c].getFalseCondition()+" and "+pCon;
								boolean kk=	(cts[c].getParent()).isExecutedSolutionNotFoundFalsePath();
								if ((cts[c].getParent().getFalsePathCondition()!=null && cts[c].getParent().getFalsePathCondition().equals(cts[c]) && (cts[c].getParent()).isExecutedSolutionNotFoundFalsePath())|| (cts[c].getParent().getTruePathCondition()!=null && cts[c].getParent().getTruePathCondition().equals(cts[c]) && (cts[c].getParent()).isExecutedSolutionNotFoundTruePath()))
							    	continue;
							}else
								constraint1 = cts[c].getFalseCondition();
							cts[c].setCurrentExecution(false);
						}
					    

							ArrayList<String> queries = new ArrayList<String>();
//							System.out.println(constraint1);
							queries.add(constraint1);

						ArrayList<ClassifierTuple> oldCTuples = new ArrayList<ClassifierTuple>();
//						String filePath = "MOTTERExperiment/Blank Package_int.uml";

						for (int k1 = 0; k1 < queries.size(); k1++) // Problem
						{
       						ClassDiagramTestData.getInstance().reset(filePath);
							FinalDriver obj = new FinalDriver();
							ClassDiagramTestData.getInstance().setFileNames(
									obj.getclassDiag(), obj.getObjDiag());
							
							ArrayList<ClassifierTuple> temp = cts[c].getValues();
							if (temp != null && temp.size() > 0)
								oldCTuples = temp; 
									Problem p=null;
							 p = new SimpleProblem(queries.get(k1),
									obj.getclassDiag(), obj.getObjDiag(),
									ClassDiagramTestData.umlFileURI, oldCTuples);
							 file.write(queries.get(k1)+"\r");// will write the query
//	will print the query	System.out.println(queries.get(k1));

							p.k = k;
							SolverRunner exp = new SolverRunner();
							exp.setIndividualType("simple");
							sv.iFactory.setType("simple");
//							sv.setMaxIterations(totalIterations - currentIterationsConsumed);
							sv.setMaxIterations(maxiterationperConstraint);
							sv.current_iteration = 0;
							long start = System.currentTimeMillis();
							sv.search(p);
							boolean found = p.getFitness() == 0d;
							int steps = sv.getIteration();
							currentIterationsConsumed += steps;
							long elapsedTimeMillis = System.currentTimeMillis()
									- start;
							if (found && cts[c].currentExecution) {
								cts[c].setCurrentStatus(true);
								cts[c].setTruePathevaluationTime(elapsedTimeMillis);
								cts[c].setTruePathExecuted(true);
								cts[c].setTruePathnoOfIterations(steps);
								cts[c].setTruePathvalues((ArrayList<ClassifierTuple>) p.getQueryVariables().clone());	
								
							}
							else if(!found && cts[c].currentExecution){
								cts[c].setCurrentStatus(true);
								cts[c].setTruePathevaluationTime(elapsedTimeMillis);
								cts[c].setTruePathExecuted(false);
								cts[c].setTruePathnoOfIterations(steps);
								cts[c].setTruePathvalues((ArrayList<ClassifierTuple>) p.getQueryVariables().clone());
								cts[c].setExecutedSolutionNotFoundTruePath(true);
								currentIterationsConsumed=0;
							}
							if (found && !cts[c].currentExecution) {
								cts[c].setCurrentStatus(false);
								cts[c].setFalsePathevaluationTime(elapsedTimeMillis);
								cts[c].setFalsePathExecuted(true);
								cts[c].setFalsePathnoOfIterations(steps);
								cts[c].setFalsePathvalues((ArrayList<ClassifierTuple>) p
										.getQueryVariables().clone());
								
							}
							else if(!found && !cts[c].currentExecution){
								cts[c].setCurrentStatus(false);
								cts[c].setFalsePathevaluationTime(elapsedTimeMillis);
								cts[c].setFalsePathExecuted(false);
								cts[c].setFalsePathnoOfIterations(steps);
								cts[c].setFalsePathvalues((ArrayList<ClassifierTuple>) p
										.getQueryVariables().clone());
								cts[c].setExecutedSolutionNotFoundFalsePath(true);
								currentIterationsConsumed=0;
							}
							resetStaticMembers();
							if(currentIterationsConsumed >= totalIterations){
								breakOnIterations = true;
								break;
							}
						}
						// k++;
						if(breakOnIterations)
							break;
					}
					if(breakOnIterations)
						break;
				}


				int steps = 0;
				long elapsedTimeMillis = 0;
				double coverage = 0;
				for (ConditionTracker ct : cts) {
					steps += ct.getTruePathnoOfIterations()
							+ ct.getFalsePathnoOfIterations();
					elapsedTimeMillis += ct.getTruePathevaluationTime()
							+ ct.getFalsePathevaluationTime();
					if(ct.isTruePathExecuted())
						coverage += 1;
					if(ct.isFalsePathExecuted())
						coverage += 1;
				}
				int PredCov=(int) coverage;
				coverage = (coverage / (cts.length * 2))*100;
				System.out.println("Size: " + size);
				System.out.println("Coverage: "+coverage+ "%");

				float elapsedTimeSec = elapsedTimeMillis / 1000F;
				long totalElapsedTimeMillis = System.currentTimeMillis()
						- Covstart;
				float totalElapsedTimeSec = totalElapsedTimeMillis / 1000F;
				BigDecimal bd = new BigDecimal(coverage); 
				bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
				//bd.doubleValue();
				file.write(sv.getShortName()); // Algo Name
				file.write("\tBreak"); // Approach Name
				file.write(" \t " + i); // No of Runs 
				file.write(" \t "+PredCov); // No Predicates solved
				file.write(" \t "+bd.doubleValue()); // Coverage Achieved
				file.write(" \t " + steps); // No of Iteration taken to achive coverage
				file.write(" \t " + currentIterationsConsumed); // No of Iteration taken to achive coverage				
				if(PredCov>0)
				file.write(" \t true"); // Solution Found or Not
				else
			    file.write(" \t false"); // Solution Found or Not
				file.write(" \t " + elapsedTimeSec); // Time
				file.write(" \t " + totalElapsedTimeSec + "\r\n"); // Time
				file.flush();
				
				k++;
//				coverage=0;
//				Steps=0;
			}
			
			file.close();
		}

		System.out
				.println("\n\n\t================================= Execution Completed========================");

		} catch (Exception e) {

			System.out.println("Exception1");
			e.printStackTrace();
			return;
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
}
