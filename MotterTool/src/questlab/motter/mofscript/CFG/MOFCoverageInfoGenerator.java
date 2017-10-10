package questlab.motter.mofscript.CFG;

import questlab.motter.CFG.AtomicNode;
import questlab.motter.CFG.CFG;
import questlab.motter.CFG.CompositeNode;
import questlab.motter.CFG.Condition;
import questlab.motter.CFG.DecisionNode;

public class MOFCoverageInfoGenerator {
	
	public static CFG generateCoverageInfo(){
		AtomicNode atNode,atNode1,atNode2,atNode3;
		CompositeNode compNode, compNode1;
		DecisionNode decNode,decNode1;
		Condition cond;
		CFG R =new CFG();
		
		atNode1=new AtomicNode("17#GCI1","Line:17-Line73");
		R.insert(atNode1, null);
		decNode=new DecisionNode("74#GCI2:DecisionNode:C1","myClass.getAppliedStereotype(\"Context\") != null");
		cond=new Condition("myClass.getAppliedStereotype(\"Context\") != null");
		decNode.setcondition(cond);
		R.insert(decNode, atNode1);
		atNode3=new AtomicNode("75#GCI3:then:GCI2","Line:75-Line84");
		R.insert(atNode3, decNode);
		
		decNode=new DecisionNode("85#GCI4:DecisionNode:C2","stateMach.region.size() > 1");
		cond=new Condition("stateMach.region.size() > 1");
		decNode.setcondition(cond);
		R.insert(decNode, atNode3);
		atNode2=new AtomicNode("86#GCI5:then:GCI4","Line:86-Line88");
		R.insert(atNode2, decNode);
		atNode1=new AtomicNode("89#GCI6","Line:89-Line96");
		R.insert(atNode1, atNode2);
		atNode1.setParentNode(decNode);
		
		decNode=new DecisionNode("97#GCI7:DecisionNode:C3","s.isComposite");
		cond=new Condition("s.isComposite");
		decNode.setcondition(cond);
		R.insert(decNode, atNode2);
		atNode2=new AtomicNode("98#GCI8:then:GCI7","Line:98-Line103");
		R.insert(atNode2, decNode);
		
		decNode1=new DecisionNode("104#GCI8:DecisionNode:C4","s.region.size() > 1");
		cond=new Condition("s.region.size() > 1");
		decNode1.setcondition(cond);
		R.insert(decNode1, atNode2);
		atNode2=new AtomicNode("105#GCI9:then:GCI8","Line:105-Line117");
		R.insert(atNode2, decNode1);
		compNode=new CompositeNode("111#GCI10:Comp","formatStateName(myClass.name, plSubState.name)");
		R.insert(compNode, atNode2);
		//R.insert(generateHeader().getRootNode(),compNode);//--------------f call
		atNode1=new AtomicNode("118#GCI11:else:GCI8","Line:118-Line131");
		R.insert(atNode1, decNode);
		compNode1=new CompositeNode("130#GCI12:Comp","formatStateName(myClass.name, plSubState.name)");
		R.insert(compNode1, atNode1);
		//R.insert(generateHeader().getRootNode(),compNode);//--------------f call
		atNode1=new AtomicNode("131#GCI13","Line:132-Line132");//else of dec node1
		R.insert(atNode1, compNode);
		atNode1.setParentNode(compNode1);
		
		atNode2=new AtomicNode("133#GCI14:else:GCI7","Line:133-Line138"); //else of decnode
		R.insert(atNode2, decNode);
		atNode=new AtomicNode("139#GCI15","Line:139-Line139");
		R.insert(atNode, atNode1);
		atNode.setParentNode(atNode2);
		
		

		atNode2=new AtomicNode("140#GCI16","Line:140-Line186");
		R.insert(atNode2, atNode);
		atNode2.setParentNode(decNode);

		
		return R;
	}

}
