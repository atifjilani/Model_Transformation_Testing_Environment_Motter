package questlab.motter.common.driver;

import questlab.motter.CFG.AtomicNode;
import questlab.motter.CFG.CFG;
import questlab.motter.CFG.CompositeNode;
import questlab.motter.CFG.Condition;
import questlab.motter.CFG.DecisionNode;
import questlab.motter.CFG.Node;

public class TryCFG1 {
	
	public  static CFG funRulePersistentClass2Table(){
		AtomicNode atNode1,atNode2,atNode3;
		DecisionNode decNode,decNode1;
		CompositeNode compNode,compNode1;
		Condition cond;
		
		CFG R =new CFG();
		//FROM BLOCK
		
		atNode1=new AtomicNode("8#R1","S1");
		R.insert(atNode1, null);
		
		decNode=new DecisionNode("30#R8:DecisionNode:C1","x1 > y1");//if
		cond=new Condition("True of C1");
		decNode.setcondition(cond);
		decNode.setFalseCondition(new Condition("False of C1"));
		R.insert(decNode, atNode1);
		
		atNode1=new AtomicNode("38#R11:then:R8","T1");
		R.insert(atNode1, decNode);
		
		compNode=new CompositeNode("40#R12:else:R8","E-Ct1");
		R.insert(compNode, decNode);
		Node returnNode = myNode(R, compNode);
		
		Node endStmt=new AtomicNode("38#R11","SE");
		R.insert(endStmt, returnNode);
		
		
		decNode1=new DecisionNode("30#R9:DecisionNode:C2","C2");//if
		decNode1.setcondition(new Condition("C2"));
		decNode1.setFalseCondition(new Condition("not of C2"));
//		decNode1.setFalseCondition(new Condition("Trigger.allInstances()->exists(tr|tr.event.oclIsTypeOf(AnyRecieveEvent)) and Transition.allInstances()->exists(t|t.trigger->exists(tt|tt.oclIsTypeOf(Trigger))) and Trigger.allInstances()->exists(tr|tr.event.oclIsTypeOf(TimeEvent)) and TimeEvent.allInstances()->exists(t|t.when.oclIsTypeOf(TimeExpression)) and TimeExpression.allInstances()->exists(t|t.expr.oclIsTypeOf(OpaqueExpression)) and Transition.allInstances()->exists(t|t.target.oclIsKindOf(State) and t.stereotypeTimeProbability>0)"));
		R.insert(decNode1, atNode1);
		
		compNode=new CompositeNode("40#R12:then:R9","T-Ct2");
		R.insert(compNode, decNode1);
		returnNode = myNode(R, compNode);
		endStmt.setParentNode(returnNode);
		
		atNode1=new AtomicNode("38#R11:else:R9","E2");
		R.insert(atNode1, decNode1);
		
		DecisionNode decNode2=new DecisionNode("30#R10:DecisionNode:C3","C3");//if
		cond=new Condition("C4");
		decNode2.setcondition(cond);
		decNode2.setFalseCondition(new Condition("not of C4"));
		R.insert(decNode2, atNode1);
		
		atNode2=new AtomicNode("40#R13:then:R10","E4");
		R.insert(atNode2, decNode2);
		
		atNode3=new AtomicNode("38#R14:else:R10","E3");
		R.insert(atNode3, decNode2);
		
		Node end2=new AtomicNode("38#R14","SE2");
		R.insert(end2, atNode2);
		end2.setParentNode(atNode3);
		
		Node end3=new AtomicNode("38#R15","SE2");
		R.insert(end2, endStmt);
		end3.setParentNode(atNode2);
		
		return R;
	}
	
	private static Node myNode(CFG TP, Node parent){
		AtomicNode atNode1,atNode3;
		DecisionNode decNode;
		CompositeNode compNode;
		Condition cond;
		
//		CFG TP =new CFG();
		//topParent : SimpleClass!Class =
		decNode=new DecisionNode("180#TP1:DecisionNode:C4","C4-F"+parent.getNodeId());//if
		cond=new Condition("Function C3");
		decNode.setcondition(cond);
		decNode.setFalseCondition(new Condition("Not of Function C3"));
		TP.insert(decNode, parent);
		//then
		atNode1=new AtomicNode("182#TP2:then:TP1","self");
		TP.insert(atNode1, decNode);
		//else
		compNode=new CompositeNode("184#TP3:else:TP1","self.parent.topParent ");
		TP.insert(compNode, decNode);
//		compNode.addChildNode(decNode);
		atNode3=new AtomicNode("185#TP4","endif;");
		TP.insert(atNode3, atNode1);
		atNode3.setParentNode(compNode);
		//TP.insert(atNode3, atNode2);
		//atNode=new AtomicNode("TP5","topParent : SimpleClass!Class =");
		//TP.insert(atNode, atNode3);
		//TP.BFS();
	/////////////////
		return atNode3;
	}
	
}
