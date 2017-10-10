package questlab.motter.common.driver;

import java.util.ArrayList;
import java.util.HashMap;
import questlab.motter.CFG.CFG;
import questlab.motter.CFG.CFGATLDriverNew;
import questlab.motter.CFG.Condition;
import questlab.motter.CFG.DecisionNode;
import questlab.motter.CFG.Node;
import questlab.motter.CFG.conditionMap;

public class ATL_Fitness_Calculator {

	public ATL_Fitness_Calculator() {
		System.out.println("\n MOTTER Fitness Calculation Started");
	}
 
    public void printCFG(HashMap<String, conditionMap> nodeIds){
    	System.out.println("\nCGF Traversal of the Input Model  : \n");
    	int l=1;
    	for (String key : nodeIds.keySet()) {
    		System.out.print((l++)+": ");
    		conditionMap c=nodeIds.get(key);
    		c.print();
    	}
    }
    
    public ArrayList<String> printParentTree(String key,HashMap<String, conditionMap> conditionStatements){
        ArrayList<String> pList=new ArrayList<String>();
        CFGATLDriverNew d=new CFGATLDriverNew();
		CFG TP=d.funRulePersistentClass2Table();
			Node n = TP.BFS(key,1);
			
	//		TP.reInitializeIsVisisted(TP.getRootNode());
			conditionMap c=conditionStatements.get(key);
		if(c.getConditionStatus()!=true){
        if(getParentDecisionNode(n)==null){
        	pList.add("  ");
        }
        else{
        	pList.add(" -> "+getParentDecisionNode(n).getNodeId().split("#")[1].split(":")[0].toString());
            pList.addAll(printParentTree(getParentDecisionNode(n).getNodeId().split("#")[1].split(":")[0].toString(),conditionStatements));
        
        }
		}
		
			
    	return pList;
    }
    public  double calculateApproachLevel(String key,HashMap<String, conditionMap> conditionStatements ){
    	double appLevel=0.0;
    	conditionMap c=conditionStatements.get(key);
    	 if (!c.getParentConditionNode().equals("null")){
            // appLevel=1.0;
    		 if(conditionStatements.get(c.getParentConditionNode()).getConditionStatus()== false){
    			 appLevel=calculateApproachLevel(c.getParentConditionNode(), conditionStatements);
        		 appLevel++; 
    		 }
    	 }
    	return appLevel;
    }
    
    public  double CalculateFitness(HashMap<String, conditionMap> conditionStatements){
    	double approach=0.0;
    	for (String key : conditionStatements.keySet()) {
    		conditionMap c=conditionStatements.get(key);
    		if(!c.getConditionStatus()){ // find target nodes which are not yet covered
    			approach=calculateApproachLevel(key, conditionStatements);
    			System.out.print("App-Level of "+key+"  = "+approach+"  :  ");
    			for(String u : printParentTree(key,conditionStatements)){
    				System.out.print( u );	
    			}
    			System.out.print("\n");
    			}
    		}    	
    	return approach;
    }

    
    public  Node getParentDecisionNode(Node n){
    	Node PNode=new Node();
    	n=n.getParentNode();
    	if(n.getParentNode()!=null){
    	
    	if(n.getNodeId().contains(":")){///specific node DecisionNode or Comp
			//String nodeNumber=n.getNodeId().split(":")[0].split("#")[1];
			if(n.getNodeId().split(":")[1].endsWith("DecisionNode"))
				PNode =n;
			else
				PNode=getParentDecisionNode(n);
			}
    	else{
    		PNode=getParentDecisionNode(n);
    	}
		return PNode;
    	}
    	return null;
    }
    
    public  HashMap<String, conditionMap> cfgCompare(ArrayList<String> logs){
    	CFGATLDriverNew d=new CFGATLDriverNew();
		CFG TP=d.funRulePersistentClass2Table();
		ArrayList<String> nodeIds = new ArrayList<String>();
		for(int l=0; l<logs.size(); l++){
    		//System.out.println((l+1)+": "+logs.get(l));
    		Node n = TP.BFS(logs.get(l));
    		if(n != null){
    			//System.out.println(logs.get(l)+" GOESTO "+n.getAtlCode());
    			//if(nodeIds.contains(n.getNodeId()))
    			if(nodeIds.size() != 0){
    				//String s=nodeIds.get((nodeIds.size() - 1));
    				//String ss=n.getNodeId();
    				if(!nodeIds.get((nodeIds.size() - 1)).equals(n.getNodeId()))
    					nodeIds.add(n.getNodeId());
    			}else
    				nodeIds.add(n.getNodeId());
    			//System.out.println("Found-"+(l+1)+": "+logs.get(l));
    		}
    		//else 
  			//System.out.println("NotFound: "+(l+1)+": "+logs.get(l));//not found
    		//System.out.println("NotFound "+logs.get(l));//not found
    		TP.reInitializeIsVisisted(TP.getRootNode());
		}
		 
		HashMap<String, conditionMap> conList=new HashMap<String, conditionMap>();//=new ArrayList<>();
		for(int l=0; l<nodeIds.size(); l++){
			//System.out.println((l+1)+": "+nodeIds.get(l));
			Node n = TP.BFS(nodeIds.get(l), true);
			//System.out.println(n.getNodeId()+" ****** "+n.getAtlCode()+"\n");  //visitedNode
			conditionMap cm1=new conditionMap();
			//ArrayList<conditionMap> conList;
			
			if(n.getNodeId().contains(":")){///specific node DecisionNode or Comp
				String nodeNumber=n.getNodeId().split(":")[0].split("#")[1];
				  
				if(n.getNodeId().split(":")[1].endsWith("DecisionNode")){
					DecisionNode ddec=(DecisionNode)n;
					Condition cond=ddec.getcondition();
					String decStatement=cond.getDecstatement();
					Node pNode=getParentDecisionNode(n);
					cm1.setDecNode(n);
					cm1.setConditionNodeNumber(nodeNumber);
					cm1.setConditionValue(decStatement);
					cm1.setApproachLevel(0);
					if(pNode!=null){
					cm1.setParentConditionNode(pNode.getNodeId().split(":")[0].split("#")[1]);
					cm1.setParentCondition(pNode);
					}
					else{
						cm1.setParentConditionNode("null");
						cm1.setParentCondition(new Node("null", ""));
					}	
					conList.put(nodeNumber,cm1);
				}
				if(n.getNodeId().split(":")[1].endsWith("then")){
					conditionMap thcon=conList.get(n.getNodeId().split(":")[2]);
					if(thcon!=null){
						String thenNode=n.getNodeId().split(":")[0].split("#")[1];
						thcon.setConditionStatus(true);
						thcon.setThenNodeNumber(thenNode);
						thcon.setNodeThen(n);
						conList.put(n.getNodeId().split(":")[2], thcon);
					}
					
				}
				if(n.getNodeId().split(":")[1].endsWith("else")){
					conditionMap thcon=conList.get(n.getNodeId().split(":")[2]);
					if(thcon!=null){
						String elseNode=n.getNodeId().split(":")[0].split("#")[1];
						thcon.setConditionStatus(false);
						thcon.setElseNodeNumber(elseNode);
						thcon.setNodeElse(n);
						conList.put(n.getNodeId().split(":")[2], thcon);
					}
				}				
			}
			//conList.add(cm1);
			TP.reInitializeIsVisisted(TP.getRootNode());
    	}
		return conList;
    }
   
}
