package questlab.motter.mofscript.CFG;

import questlab.motter.CFG.AtomicNode;
import questlab.motter.CFG.CFG;
import questlab.motter.CFG.CompositeNode;
import questlab.motter.CFG.Condition;
import questlab.motter.CFG.DecisionNode;
import questlab.motter.CFG.Node;
//conditions 1 branches 4
public class MOFErrorStateDescriptionGenerator {
	//conditions 1 branches 2
	public static Node generateErrorStateDescriptions(CFG R, Node parent){
		AtomicNode atNode1,atNode2,atNode3;
		CompositeNode compNode;
		DecisionNode decNode;
		Condition cond;
//		CFG R =new CFG();
		
		atNode1=new AtomicNode("20#GESD1","Line:20-Line22");
		R.insert(atNode1, parent);
		compNode=new CompositeNode("23#GESD2:Comp","self.generateESHeader(packageName)");
		R.insert(compNode, atNode1);
		Node returnNode=generateESHeader(R,compNode);
//		R.insert(generateESHeader().getRootNode(),compNode);//--------------f call
		
		atNode2=new AtomicNode("24#GESD3","Line:24-Line97");
		R.insert(atNode2, returnNode);
		String query="State.allInstances()->exists(s|s.stereotypeError>0)";
		String query1="State.allInstances()->exists(s|s.stereotypeError<=0)";
		
		decNode=new DecisionNode("98098#GESD4:DecisionNode:C1","priority.name == ST_PROP_VAL_PRIORITY_LOW OR priority.name == ST_PROP_VAL_PRIORITY_MEDIUM OR priority.name == ST_PROP_VAL_PRIORITY_HIGH");
		cond=new Condition(query);
		decNode.setcondition(cond);
		decNode.setFalseCondition(new Condition(query1));
		decNode.setParentBlock(0);
		R.insert(decNode, atNode2);
		atNode3=new AtomicNode("99099#GESD5:then:GESD4","Line:99-Line109");
		R.insert(atNode3, decNode);
		
		atNode2=new AtomicNode("110#GESD6","Line:110-Line143");
		R.insert(atNode2, atNode3);
		atNode2.setParentNode(decNode);		
		compNode=new CompositeNode("144#GESD7:Comp","self.generateESClassEndCode()");
		R.insert(compNode, atNode2);
		returnNode=generateESClassEndCode(R,compNode);
//		R.insert(generateESClassEndCode().getRootNode(),compNode);//--------------f call
		atNode2=new AtomicNode("145541#GESD8","Line:145-Line153");
		R.insert(atNode2, returnNode);
		return atNode2;
	}
	//conditions 0 branches 1
	public static Node generateESHeader(CFG R, Node parent){
		AtomicNode atNode1;
//		CFG R =new CFG();
		atNode1=new AtomicNode("156#GESH1","Line:156-Line188");
		R.insert(atNode1, parent);
				
		return atNode1;
	}
	//conditions 0 branches 1
	public static Node generateESClassEndCode(CFG R, Node parent){
		AtomicNode atNode1;
//		CFG R =new CFG();
		atNode1=new AtomicNode("190#GESCEC1","Line:190-Line200");
		R.insert(atNode1, parent);
				
		return atNode1;
	}
	
}
