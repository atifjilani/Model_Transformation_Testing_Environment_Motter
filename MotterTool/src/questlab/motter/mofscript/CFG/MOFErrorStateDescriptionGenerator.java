package questlab.motter.mofscript.CFG;

import questlab.motter.CFG.AtomicNode;
import questlab.motter.CFG.CFG;
import questlab.motter.CFG.CompositeNode;
import questlab.motter.CFG.Condition;
import questlab.motter.CFG.DecisionNode;

public class MOFErrorStateDescriptionGenerator {

	public static CFG generateErrorStateDescriptions(){
		AtomicNode atNode1,atNode2,atNode3;
		CompositeNode compNode;
		DecisionNode decNode;
		Condition cond;
		CFG R =new CFG();
		
		atNode1=new AtomicNode("20#GESD1","Line:20-Line22");
		R.insert(atNode1, null);
		compNode=new CompositeNode("23#GESD2:Comp","self.generateESHeader(packageName)");
		R.insert(compNode, atNode1);
		R.insert(generateESHeader().getRootNode(),compNode);//--------------f call
		
		atNode2=new AtomicNode("24#GESD3","Line:24-Line97");
		R.insert(atNode2, compNode);
		
		decNode=new DecisionNode("98#GESD4:DecisionNode:C1","priority.name == ST_PROP_VAL_PRIORITY_LOW OR priority.name == ST_PROP_VAL_PRIORITY_MEDIUM OR priority.name == ST_PROP_VAL_PRIORITY_HIGH");
		cond=new Condition("priority.name == ST_PROP_VAL_PRIORITY_LOW OR priority.name == ST_PROP_VAL_PRIORITY_MEDIUM OR priority.name == ST_PROP_VAL_PRIORITY_HIGH");
		decNode.setcondition(cond);
		R.insert(decNode, atNode2);
		atNode3=new AtomicNode("99#GESD5:then:GESD4","Line:99-Line109");
		R.insert(atNode3, decNode);
		
		atNode2=new AtomicNode("110#GESD6","Line:110-Line143");
		R.insert(atNode2, atNode3);
		atNode2.setParentNode(decNode);		
		compNode=new CompositeNode("144#GESD7:Comp","self.generateESClassEndCode()");
		R.insert(compNode, atNode2);
		R.insert(generateESClassEndCode().getRootNode(),compNode);//--------------f call
		atNode2=new AtomicNode("145541#GESD8","Line:145-Line153");
		R.insert(atNode2, compNode);
		return R;
	}
	
	public static CFG generateESHeader(){
		AtomicNode atNode1;
		CFG R =new CFG();
		atNode1=new AtomicNode("156#GESH1","Line:156-Line188");
		R.insert(atNode1, null);
				
		return R;
	}
	
	public static CFG generateESClassEndCode(){
		AtomicNode atNode1;
		CFG R =new CFG();
		atNode1=new AtomicNode("190#GESCEC1","Line:190-Line200");
		R.insert(atNode1, null);
				
		return R;
	}
	
}
