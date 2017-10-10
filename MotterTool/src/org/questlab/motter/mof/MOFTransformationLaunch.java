package org.questlab.motter.mof;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import org.questlab.motter.atl.CreateSaveTester;
import org.questlab.motter.oclSolver.MOFExperiment;
import questlab.motter.CFG.CFG;
import questlab.motter.CFG.Condition;
import questlab.motter.CFG.DecisionNode;
import questlab.motter.CFG.Node;
import questlab.motter.CFG.conditionMap;
import questlab.motter.mofscript.CFG.MOFMain;
import simula.embt.simulator.CaseSpecificInputsReader;
import simula.embt.simulator.MofscriptParser;
import simula.embt.simulator.driver.MofScriptDriver;
import snt.oclsolver.distance.ClassDiagramTestData;
import snt.oclsolver.search.SearchAlgorithmEnum;
import snt.oclsolver.tuples.ClassifierTuple;

public class MOFTransformationLaunch {

	private ArrayList<String> logs = new ArrayList<String>();

	public static void main(String[] args) {
		try {

			String umlFile = "OCLSolverInput/StateMachineMetaModel.uml";
			String objDiagramPath = "OCLSolverOutput";
			ClassDiagramTestData.getInstance().reset(umlFile);
			int noOfIterations = 1000;
			int noOfRuns = 1;
			ArrayList<ClassifierTuple> result = null;
			MOFExperiment mofe1 = new MOFExperiment();
			mofe1.setUpExperiment(noOfIterations, noOfRuns,
					SearchAlgorithmEnum.AVM, umlFile, objDiagramPath);
			result=SetUpDiagram(mofe1);
			
			// Create UML StateMachine from ClassifierTuple
			String objDiagramFileName = "OCL_GenSM";
			CreateSaveTester s = new CreateSaveTester();
			s.retrieveTuppleForState(result);
			s.generateUMLStateMachine(objDiagramFileName, objDiagramPath);

			// Call MOF Transformation
//			MOFTransformationLaunch launch = new MOFTransformationLaunch();
//			String InputModel = "OCLSolverOutput/MOFLogFile.txt";
//			launch.executeMOF();
//			launch.printCFG(launch.executeTransformation(InputModel));
//			launch.CalculateFitness(launch.executeTransformation(InputModel));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static ArrayList<ClassifierTuple> SetUpDiagram(MOFExperiment mofe1){
		ArrayList<ClassifierTuple> result = null;
		String query1 = "" 
				+ "Class.allInstances()->size()=2" 
				+ " and Property.allInstances()->size()>0" 
				+ " and Association.allInstances()->size()>0" 
			//	+ " and Operation.allInstances()->size()>2" 
//				+ " and StateMachine.allInstances()->size()=1"
//			//	+ " and Region.allInstances()->size()=1" 
//				+ " and State.allInstances()->size()>0"
//				+ " and Transition.allInstances()->size()>0" 
//				+ " and Trigger.allInstances()->size()>0" 
//				+ " and CallEvent.allInstances()->size()=1"
//				+ " and ChangeEvent.allInstances()->size()=1"
//				+ " and SignalEvent.allInstances()->size()=1" 
//				+ " and TimeEvent.allInstances()->size()=1"
//				+ " and TimeExpression.allInstances()->size()=1" 
//				+ " and Signal.allInstances()->size()=1"
//				+ " and Reception.allInstances()->size()=1"
//				+ " and Package.allInstances()->size()=1"
//				+ " and OpaqueExpression.allInstances()->size()>0"
//				+ " and CallEvent.allInstances()->exists(ca|ca.operation.oclIsKindOf(Operation))" 
//				+ " and SignalEvent.allInstances()->exists(ca|ca.signal.oclIsKindOf(Signal))" 
//				+ " and TimeEvent.allInstances()->exists(ca|ca.when.oclIsKindOf(TimeExpression))"
//				+ " and ChangeEvent.allInstances()->exists(ca|ca.changeExpression.oclIsKindOf(OpaqueExpression))"
//				+ " and TimeExpression.allInstances()->exists(ca|ca.expr.oclIsTypeOf(OpaqueExpression))"
				;
	              
	//	result = mofe1.SolveQuery(query1);	
		String query2 = "" 
				+ "Class.allInstances()->size() >0" 
				+ " and Property.allInstances()->size()>0" 
				+ " and Association.allInstances()->size()>0" 
				+ " and Operation.allInstances()->size()>2" 
				+ " and StateMachine.allInstances()->size()=1"
			//	+ " and Region.allInstances()->size()=1" 
				+ " and State.allInstances()->size()>0"
				+ " and Transition.allInstances()->size()>0" 
				+ " and Trigger.allInstances()->size()>0" 
				+ " and CallEvent.allInstances()->size()=1"
				+ " and ChangeEvent.allInstances()->size()=1"
				+ " and SignalEvent.allInstances()->size()=1" 
				+ " and TimeEvent.allInstances()->size()=1"
				+ " and TimeExpression.allInstances()->size()=1" 
				+ " and Signal.allInstances()->size()=1"
				+ " and Reception.allInstances()->size()=1"
				+ " and Package.allInstances()->size()=1"
			//	+ " and OpaqueExpression.allInstances()->size()=2"
				+ " and CallEvent.allInstances()->exists(ca|ca.operation.oclIsTypeOf(Operation))" 
				+ " and SignalEvent.allInstances()->exists(ca|ca.signal.oclIsTypeOf(Signal))" 
				+ " and TimeEvent.allInstances()->exists(ca|ca.when.oclIsTypeOf(TimeExpression))"
				+ " and ChangeEvent.allInstances()->exists(ca|ca.changeExpression.oclIsTypeOf(OpaqueExpression))"
				+ " and TimeExpression.allInstances()->exists(ca|ca.expr.oclIsTypeOf(OpaqueExpression))"
				+ " and Operation.allInstances()->forAll(s|s.class.oclIsTypeOf(Class))" 
				+ " and Class.allInstances()->select(c|c.isActive=true and c.ownedOperation->forAll(o|o.oclIsKindOf(Operation))"
				+ " and c.ownedAttribute->exists(a|a.oclIsKindOf(Property)) and c.ownedBehavior->exists(ob| ob.oclIsKindOf(StateMachine))"
				+ ")->size()=1"
				+ " and StateMachine.allInstances()->exists(s |s.region->exists(r|r.name='r1'))" 
                ;
		
		
	//	result=mofe1.SolveQueryWithClassifierTupple(query2, result);
		String query3 = "" 
				+ "Class.allInstances()->size()>0" 
				+ " and Property.allInstances()->size()>0" 
				+ " and Association.allInstances()->size()>0" 
				+ " and Operation.allInstances()->size()>0" 
				+ " and StateMachine.allInstances()->size()=1"
		//		+ " and Region.allInstances()->size()>0" 
				+ " and State.allInstances()->size()>0"
				+ " and Transition.allInstances()->size()>0" 
				+ " and Trigger.allInstances()->size()>0" 
				+ " and CallEvent.allInstances()->size()>=1"
				+ " and ChangeEvent.allInstances()->size()>=1"
				+ " and SignalEvent.allInstances()->size()>=1" 
				+ " and TimeEvent.allInstances()->size()>=1"
				+ " and TimeExpression.allInstances()->size()>0" 
				+ " and Signal.allInstances()->size()>0"
				+ " and Reception.allInstances()->size()>0"
				+ " and Package.allInstances()->size()=1"
				+ " and OpaqueBehavior.allInstances()->size()>0"
				+ " and CallEvent.allInstances()->exists(ca|ca.operation.oclIsTypeOf(Operation))" 
				+ " and SignalEvent.allInstances()->exists(ca|ca.signal.oclIsTypeOf(Signal))" 
				+ " and TimeEvent.allInstances()->exists(ca|ca.when.oclIsTypeOf(TimeExpression))"
				+ " and ChangeEvent.allInstances()->exists(ca|ca.changeExpression.oclIsTypeOf(OpaqueExpression))"
				+ " and TimeExpression.allInstances()->exists(ca|ca.expr.oclIsTypeOf(OpaqueExpression))"
				+ " and Reception.allInstances()->exists(ca|ca.signal.oclIsTypeOf(Signal))"
				+ " and Operation.allInstances()->forAll(s|s.class.oclIsTypeOf(Class))"
				+ " and Class.allInstances()->select(c|c.isActive=false)->size()=1" 
				+ " and Class.allInstances()->select(c|c.isActive=true and c.ownedOperation->exists(o|o.oclIsTypeOf(Operation))"
				+ " and c.ownedAttribute->exists(a|a.oclIsTypeOf(Property)) and c.ownedBehavior->exists(ob| ob.oclIsTypeOf(StateMachine)) and c.ownedReception->exists(ob| ob.oclIsTypeOf(Reception))"
				+ ")->size()=1"
				+ " and StateMachine.allInstances()->exists(s |s.region->exists(r|r.name='r1'))" 
				+ " and Region.allInstances()->select(m|m.name='r1')->exists(r|r.transition->exists(t|t.source.oclIsTypeOf(State) and t.target.oclIsTypeOf(State)))"
		//		+ " and Region.allInstances()->select(m|m.name='r1')->exists(r|r.transition->exists(t|t.source.oclIsTypeOf(State) and t.target.oclIsTypeOf(FinalState)))"
		//		+ " and Region.allInstances()->select(m|m.name='r1')->select(r|r.transition->select(t|t.source.oclIsTypeOf(Vertex) and t.target.oclIsTypeOf(Vertex))->size()=5)->size()=5"
				+ " and Transition.allInstances()->forAll(t|t.effect.oclIsTypeOf(OpaqueBehavior) and t.trigger.oclIsTypeOf(Trigger))"
				+ " and Trigger.allInstances()->forAll(t|t.event.oclIsKindOf(Event))"
				//+ " and Region.allInstances()->forAll(r|r.subvertex->forAll(v|v.oclIsKindOf(Vertex)))"
	             ;
		//result=mofe1.SolveQueryWithClassifierTupple(query3, result);
		result=mofe1.SolveQuery(query3);
		
		return result;
	
	}
	
	public void executeMOF() {

//		 System.setProperty(CaseSpecificInputsReader.PROPERTY_FILE_NAME_PROPERTY,
//		 "booting_new/booting_new-user-config.properties");

		System.setProperty(
				CaseSpecificInputsReader.PROPERTY_FILE_NAME_PROPERTY,
				"ocl_generated/user_config.properties");
		System.setProperty(MofscriptParser.TRANSFORMATION_DIR_PROPERTY,
				"../VERDE_SimulatorGenerator");

		try {
			MofScriptDriver.main(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String removeSpaces(String line) {
		String l = "";
		for (int i = 1; i < line.length(); i += 2) {
			l += line.charAt(i);
		}
		return l;
	}

	public HashMap<String, conditionMap> executeTransformation(String InputModel) {

		System.out.println("\n MOTTER Execution Started");
		try {

			BufferedReader br = new BufferedReader(new FileReader(InputModel));
			try {

				String line = null;

				while ((line = br.readLine()) != null) {
					// System.out.println(line);
					line = removeSpaces(line);
					// System.out.println(line);
					String part[] = line.split("#");
					if (part.length > 2) {
						String number = part[0];
						String temp = part[1];
						temp = number + "#" + temp;
						// System.out.println(temp);
						if (!logs.contains(temp)) {
							logs.add(temp);
						}
					}
					// line = br.readLine();

				}

			} finally {
				br.close();
			}

			for (int l = 0; l < logs.size(); l++) {
				// System.out.println((l+1)+": "+logs.get(l));
				// System.out.println(logs.+": "+logs.get(l));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		HashMap<String, conditionMap> nodeIds = cfgCompare();
		return nodeIds;
	}

	public void printCFG(HashMap<String, conditionMap> nodeIds) {
		System.out.println("\nCGF Traversal of the Input Model  : \n");
		int l = 1;
		for (String key : nodeIds.keySet()) {
			System.out.print((l++) + ": ");
			conditionMap c = nodeIds.get(key);
			c.print();
		}
	}

	private ArrayList<String> printParentTree(String key,
			HashMap<String, conditionMap> conditionStatements) {
		ArrayList<String> pList = new ArrayList<String>();
		CFG TP = MOFMain.mainMethod();
		Node n = TP.BFS(key, 1);

		// TP.reInitializeIsVisisted(TP.getRootNode());
		conditionMap c = conditionStatements.get(key);
		if (c.getConditionStatus() != true) {
			if (getParentDecisionNode(n) == null) {
				pList.add("  ");
			} else {
				pList.add(" -> "
						+ getParentDecisionNode(n).getNodeId().split("#")[1]
								.split(":")[0].toString());
				pList.addAll(printParentTree(getParentDecisionNode(n)
						.getNodeId().split("#")[1].split(":")[0].toString(),
						conditionStatements));

			}
		}

		return pList;
	}

	private double calculateApproachLevel(String key,
			HashMap<String, conditionMap> conditionStatements) {
		double appLevel = 0.0;
		conditionMap c = conditionStatements.get(key);
		if (!c.getParentConditionNode().equals("null")) {
			// appLevel=1.0;
			if (conditionStatements.get(c.getParentConditionNode())
					.getConditionStatus() == false) {
				appLevel = calculateApproachLevel(c.getParentConditionNode(),
						conditionStatements);
				appLevel++;
			}
		}
		return appLevel;
	}

	public double CalculateFitness(
			HashMap<String, conditionMap> conditionStatements) {
		double approach = 0.0;
		for (String key : conditionStatements.keySet()) {
			conditionMap c = conditionStatements.get(key);
			if (!c.getConditionStatus()) { // find target nodes which are not
				// yet covered
				approach = calculateApproachLevel(key, conditionStatements);
				System.out.print("App-Level of " + key + "  = " + approach
						+ "  :  ");
				for (String u : printParentTree(key, conditionStatements)) {
					System.out.print(u);
				}
				System.out.print("\n");
			}
		}
		return approach;
	}

	private String clearName(String name) {
		if (name.contains("__match")) {
			return name.replace("__match", "");
		} else if (name.contains("__init")) {
			return name.replace("__init", "");
		} else if (name.contains("__apply")) {
			return name.replace("__apply", "");
		}
		return name;
	}

	private Node getParentDecisionNode(Node n) {
		Node PNode = new Node();
		n = n.getParentNode();
		if (n.getParentNode() != null) {

			if (n.getNodeId().contains(":")) {// /specific node DecisionNode or
				// Comp
				// String nodeNumber=n.getNodeId().split(":")[0].split("#")[1];
				if (n.getNodeId().split(":")[1].endsWith("DecisionNode"))
					PNode = n;
				else
					PNode = getParentDecisionNode(n);
			} else {
				PNode = getParentDecisionNode(n);
			}
			return PNode;
		}
		return null;
	}

	private HashMap<String, conditionMap> cfgCompare() {
		// CFGATLDriverNew d=new CFGATLDriverNew();
		CFG TP = MOFMain.mainMethod();
		ArrayList<String> nodeIds = new ArrayList<String>();
		for (int l = 0; l < logs.size(); l++) {
			// System.out.println((l+1)+": "+logs.get(l));
			Node n = TP.BFS(logs.get(l));
			if (n != null) {
				// System.out.println(logs.get(l)+" GOESTO "+n.getAtlCode());
				// if(nodeIds.contains(n.getNodeId()))
				if (nodeIds.size() != 0) {
					// String s=nodeIds.get((nodeIds.size() - 1));
					// String ss=n.getNodeId();
					if (!nodeIds.get((nodeIds.size() - 1))
							.equals(n.getNodeId()))
						nodeIds.add(n.getNodeId());
				} else
					nodeIds.add(n.getNodeId());
				// System.out.println("Found-"+(l+1)+": "+logs.get(l));
			}
			// else {
			// System.out.println("NotFound: "+(l+1)+": "+logs.get(l));//not
			// found
			// System.out.println("NotFound "+logs.get(l));//not found
			TP.reInitializeIsVisisted(TP.getRootNode());// }
		}

		HashMap<String, conditionMap> conList = new HashMap<String, conditionMap>();// =new
		// ArrayList<>();
		for (int l = 0; l < nodeIds.size(); l++) {
			// System.out.println((l+1)+": "+nodeIds.get(l));
			Node n = TP.BFS(nodeIds.get(l), true);
			// System.out.println(n.getNodeId()+" ****** "+n.getAtlCode()+"\n");
			// //visitedNode
			conditionMap cm1 = new conditionMap();
			// ArrayList<conditionMap> conList;
			// if(n!=null)
			if (n.getNodeId().contains(":")) {// /specific node DecisionNode or
				// Comp
				String nodeNumber = n.getNodeId().split(":")[0].split("#")[1];

				if (n.getNodeId().split(":")[1].endsWith("DecisionNode")) {
					DecisionNode ddec = (DecisionNode) n;
					Condition cond = ddec.getcondition();
					String decStatement = cond.getDecstatement();
					Node pNode = getParentDecisionNode(n);
					cm1.setDecNode(n);
					cm1.setConditionNodeNumber(nodeNumber);
					cm1.setConditionValue(decStatement);
					cm1.setApproachLevel(0);
					if (pNode != null) {
						cm1.setParentConditionNode(pNode.getNodeId().split(":")[0]
								.split("#")[1]);
						cm1.setParentCondition(pNode);
					} else {
						cm1.setParentConditionNode("null");
						cm1.setParentCondition(new Node("null", ""));
					}
					conList.put(nodeNumber, cm1);
				}
				if (n.getNodeId().split(":")[1].endsWith("then")) {
					conditionMap thcon = conList
							.get(n.getNodeId().split(":")[2]);
					if (thcon != null) {
						String thenNode = n.getNodeId().split(":")[0]
								.split("#")[1];
						thcon.setConditionStatus(true);
						thcon.setThenNodeNumber(thenNode);
						thcon.setNodeThen(n);
						conList.put(n.getNodeId().split(":")[2], thcon);
					}

				}
				if (n.getNodeId().split(":")[1].endsWith("else")) {
					conditionMap thcon = conList
							.get(n.getNodeId().split(":")[2]);
					if (thcon != null) {
						String elseNode = n.getNodeId().split(":")[0]
								.split("#")[1];
						thcon.setConditionStatus(false);
						thcon.setElseNodeNumber(elseNode);
						thcon.setNodeElse(n);
						conList.put(n.getNodeId().split(":")[2], thcon);
					}
				}
			}
			// conList.add(cm1);
			TP.reInitializeIsVisisted(TP.getRootNode());
		}
		return conList;
	}

}
