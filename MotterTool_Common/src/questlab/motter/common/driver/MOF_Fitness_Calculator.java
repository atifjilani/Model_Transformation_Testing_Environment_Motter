package questlab.motter.common.driver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import questlab.motter.CFG.CFG;
import questlab.motter.CFG.Condition;
import questlab.motter.CFG.DecisionNode;
import questlab.motter.CFG.Node;
import questlab.motter.CFG.conditionMap;
import questlab.motter.mofscript.CFG.MOFMain;
//import simula.embt.simulator.CaseSpecificInputsReader;
//import simula.embt.simulator.MofscriptParser;
//import simula.embt.simulator.driver.MofScriptDriver;

public class MOF_Fitness_Calculator {

	public MOF_Fitness_Calculator() {
		System.out.println("\n MOTTER Fitness Calculation Started");
	}

	private ArrayList<String> logs = new ArrayList<String>();
	private ArrayList<String> nodeIds = new ArrayList<String>();
	private String removeSpaces(String line) {
		String l = "";
		for (int i = 1; i < line.length(); i += 2) {
			l += line.charAt(i);
		}
		return l;
	}

	public HashMap<String, conditionMap> executeTransformation(String InputModel) {

		
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
		System.out.println("\n CGF Traversal of the OCL Generated  Model  : \n");
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
		//if(c!=null)
		if (c.getConditionStatus() != true && c.getIfBranch()==1) {
			if (getParentDecisionNode(n,nodeIds) == null) {
				pList.add("  ");
			} else {
				pList.add(" -> "
						+ getParentDecisionNode(n,nodeIds).getNodeId().split("#")[1].split(":")[0].toString());
				pList.addAll(printParentTree(getParentDecisionNode(n,nodeIds)
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
					.getConditionStatus() == false && c.getIfBranch()==1 ) {
				appLevel = calculateApproachLevel(c.getParentConditionNode(),
						conditionStatements);
				appLevel++;
			}
		}
		return appLevel;
	}

	public double CalculateFitness(
			HashMap<String, conditionMap> conditionStatements) {
		System.out.println("\n Approach Level Of the Generated Model \n");
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
		System.out.println("\n MOTTER Execution Completed \n");
		return approach;
	}

	private Node getParentDecisionNode(Node n,ArrayList<String> nodeIds) {
		Node PNode = new Node();
		n = n.getParentNode();
		if (n.getParentNode() != null) {

			if (n.getNodeId().contains(":")) {// /specific node DecisionNode or
				// Comp
				// String nodeNumber=n.getNodeId().split(":")[0].split("#")[1];
				boolean b=searchNode(n,nodeIds);
				if (n.getNodeId().split(":")[1].endsWith("DecisionNode")){
					if(nodeIds.contains(n.getNodeId()))
						PNode = n;         
					else
						PNode = getParentDecisionNode(n,nodeIds);
				}					
				else
					PNode = getParentDecisionNode(n,nodeIds);
			} else {
				PNode = getParentDecisionNode(n,nodeIds);
			}
			return PNode;
		}
		return null;
	}

	private boolean searchNode(Node n, ArrayList<String> nodeIds) {
		// TODO Auto-generated method stub
		for (String str : nodeIds) {
			//str.4141#GCF4:DecisionNode:C1
			if (str.contains(":")) {// /specific node DecisionNode or
				// Comp
				String nodeNumber = str.split(":")[0].split("#")[1];

				if (str.split(":")[1].endsWith("DecisionNode")) {
					
				}
			}
			
		}
		return false;
	}

	/*private Node getParentDecisionNode(Node n) {
		Node PNode = new Node();
		n = n.getParentNode();
		if (n.getParentNode() != null) {

			if (n.getNodeId().contains(":")) {// /specific node DecisionNode or
				// Comp
				// String nodeNumber=n.getNodeId().split(":")[0].split("#")[1];
				if (n.getNodeId().split(":")[1].endsWith("DecisionNode")){
					PNode = n;
					//return PNode;
				}					
				else
					PNode = getParentDecisionNode(n);
			} else {
				PNode = getParentDecisionNode(n);
			}
			return PNode;
		}
		return null;
	}*/
	private HashMap<String, conditionMap> cfgCompare() {
		// CFGATLDriverNew d=new CFGATLDriverNew();
		CFG TP = MOFMain.mainMethod();
		//ArrayList<String> nodeIds = new ArrayList<String>();
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
					Node pNode = getParentDecisionNode(n,nodeIds);
					cm1.setDecNode(n);
					cm1.setConditionNodeNumber(nodeNumber);
					cm1.setConditionValue(decStatement);
					cm1.setApproachLevel(0);
					cm1.setIfBranch(ddec.getParentBlock());
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
