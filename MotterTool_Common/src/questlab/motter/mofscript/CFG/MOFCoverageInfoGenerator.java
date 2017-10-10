package questlab.motter.mofscript.CFG;

import questlab.motter.CFG.AtomicNode;
import questlab.motter.CFG.CFG;
import questlab.motter.CFG.CompositeNode;
import questlab.motter.CFG.Condition;
import questlab.motter.CFG.DecisionNode;
import questlab.motter.CFG.Node;
//conditions 4 branches 9-4=5
public class MOFCoverageInfoGenerator {
	//conditions 4 branches 9-4=5
	public static Node generateCoverageInfo(CFG R, Node parent){
		AtomicNode atNode,atNode1,atNode2,atNode3;
		CompositeNode compNode, compNode1;
		DecisionNode decNode,decNode1,decNode3;
		Condition cond;
//		CFG R =new CFG();
		
		atNode1=new AtomicNode("17#GCI1","Line:17-Line73");
		R.insert(atNode1, parent);
		String query="Class.allInstances()->forAll(c|c.stereotypeCotext>0)";
		String query1="Class.allInstances()->forAll(c|c.stereotypeCotext<=0)";
		decNode3=new DecisionNode("74#GCI2:DecisionNode:C1","myClass.getAppliedStereotype(\"Context\") != null");
		cond=new Condition(query);
		decNode3.setcondition(cond);
		decNode3.setFalseCondition(new Condition(query1));
		decNode3.setParentBlock(0);
		R.insert(decNode3, atNode1);
		atNode3=new AtomicNode("75#GCI3:then:GCI2","Line:75-Line84");
		R.insert(atNode3, decNode3);
		query="StateMachine.allInstances()->forAll(sm|(sm.region->size()>1))";
		query1="StateMachine.allInstances()->forAll(sm|(sm.region->size()=1))";
		decNode=new DecisionNode("85#GCI4:DecisionNode:C2","stateMach.region.size() > 1");
		cond=new Condition(query);
		decNode.setcondition(cond);
		decNode.setFalseCondition(new Condition(query1));
		decNode.setParentBlock(1);
		R.insert(decNode, atNode3);
		atNode2=new AtomicNode("86#GCI5:then:GCI4","Line:86-Line88");
		R.insert(atNode2, decNode);
		atNode1=new AtomicNode("89#GCI6","Line:89-Line96");
		R.insert(atNode1, atNode2);
		atNode1.setParentNode(decNode);
		query="Region.allInstances()->exists(r|(r.subvertex->select(oclIsTypeOf(State))->collect(oclAsType(State))->select(isComposite=true)->size() > 1))";
		query1="Region.allInstances()->exists(r|(r.subvertex->select(oclIsTypeOf(State))->collect(oclAsType(State))->select(isComposite=true)->size() <= 1))";
		decNode=new DecisionNode("979797#GCI7:DecisionNode:C3","s.isComposite");
		cond=new Condition(query);
		decNode.setcondition(cond);
		decNode.setFalseCondition(new Condition(query1));
		decNode.setParentBlock(1);
		R.insert(decNode, atNode2);
		atNode2=new AtomicNode("989898#GCI8:then:GCI7","Line:98-Line103");
		R.insert(atNode2, decNode);
		query="State.allInstances()->exists(st|(st.region->size()>1))";
		query1="State.allInstances()->exists(st|(st.region->size()=1))";

		decNode1=new DecisionNode("104401#GCI8:DecisionNode:C4","s.region.size() > 1");
		cond=new Condition(query);
		decNode1.setcondition(cond);
		decNode1.setParentBlock(1);
		decNode1.setFalseCondition(new Condition(query1));
		R.insert(decNode1, atNode2);
		atNode2=new AtomicNode("105501#GCI9:then:GCI8","Line:105-Line117");
		R.insert(atNode2, decNode1);
		compNode=new CompositeNode("111#GCI10:Comp","formatStateName(myClass.name, plSubState.name)");
		R.insert(compNode, atNode2);
		//R.insert(generateHeader().getRootNode(),compNode);//--------------f call
		atNode1=new AtomicNode("118811#GCI11:else:GCI8","Line:118-Line131");
		R.insert(atNode1, decNode);
		compNode1=new CompositeNode("130#GCI12:Comp","formatStateName(myClass.name, plSubState.name)");
		R.insert(compNode1, atNode1);
		//R.insert(generateHeader().getRootNode(),compNode);//--------------f call
		atNode1=new AtomicNode("131#GCI13","Line:132-Line132");//else of dec node1
		R.insert(atNode1, compNode);
		atNode1.setParentNode(compNode1);
		
		atNode2=new AtomicNode("133331#GCI14:else:GCI7","Line:133-Line138"); //else of decnode
		R.insert(atNode2, decNode);
		atNode=new AtomicNode("139#GCI15","Line:139-Line139");
		R.insert(atNode, atNode1);
		atNode.setParentNode(atNode2);
		
		atNode2=new AtomicNode("140140#GCI16","Line:140-Line186");
		R.insert(atNode2, atNode);
		atNode2.setParentNode(decNode3);

		
		return atNode2;
	}

}
