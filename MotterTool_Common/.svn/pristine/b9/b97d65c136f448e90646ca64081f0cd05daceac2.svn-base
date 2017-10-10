package questlab.motter.mofscript.CFG;

import questlab.motter.CFG.AtomicNode;
import questlab.motter.CFG.CFG;
import questlab.motter.CFG.CompositeNode;
import questlab.motter.CFG.Condition;
import questlab.motter.CFG.DecisionNode;

public class MOFConfigurationFileGenerator {
	
	public static CFG generateConfigurationFile(){
		AtomicNode atNode,atNode1,atNode2;
		CompositeNode compNode;
		DecisionNode decNode;
		Condition cond;
		CFG R =new CFG();
		atNode1=new AtomicNode("13#GCF1","Line:13-Line16");
		R.insert(atNode1, null);
		compNode=new CompositeNode("17#GCF2:Comp","self.generateHeader(packageName)");
		R.insert(compNode, atNode1);
		R.insert(generateHeader().getRootNode(),compNode);//--------------f call
		atNode2=new AtomicNode("18#GCF3","Line:18-Line40");
		R.insert(atNode2, compNode);
		decNode=new DecisionNode("4141#GCF4:DecisionNode:C1","att.getAppliedStereotype(ST_NON_DETERMINISTIC) != null");
		cond=new Condition("att.getAppliedStereotype(ST_NON_DETERMINISTIC) != null");
		decNode.setcondition(cond);
		R.insert(decNode, atNode2);
		atNode1=new AtomicNode("43#GCF5:then:GCF4","Line:43-Line44");
		R.insert(atNode1, decNode);
		atNode2=new AtomicNode("45#GCF6","Line:45-Line49");
		R.insert(atNode2, atNode1);
		atNode2.setParentNode(decNode);
		
		decNode=new DecisionNode("50#GCF7:DecisionNode:C2","t.getAppliedStereotype(ST_TIME_PROBABILITY) != null");
		cond=new Condition("t.getAppliedStereotype(ST_TIME_PROBABILITY) != null");
		decNode.setcondition(cond);
		R.insert(decNode, atNode2);
		atNode1=new AtomicNode("52#GCF8:then:GCF7","Line:52-Line53");
		R.insert(atNode1, decNode);
		atNode2=new AtomicNode("54#GCF9","Line:54-Line59");
		R.insert(atNode2, atNode1);
		atNode2.setParentNode(decNode);
		
		decNode=new DecisionNode("60#GCF10:DecisionNode:C3","ps.getAppliedStereotype(ST_ND_CHOICE) != null");
		cond=new Condition("ps.getAppliedStereotype(ST_ND_CHOICE) != null");
		decNode.setcondition(cond);
		R.insert(decNode, atNode2);
		atNode1=new AtomicNode("62#GCF11:then:GCF10","Line:62-Line63");
		R.insert(atNode1, decNode);
		atNode2=new AtomicNode("64#GCF12","Line:64-Line102");
		R.insert(atNode2, atNode1);
		atNode2.setParentNode(decNode);
		
		compNode=new CompositeNode("103#GCF13:Comp","generateCodeForNDStereotype(prop, classSpecificChoiceId)");
		R.insert(compNode, atNode2);
		R.insert(generateCodeForNDStereotype().getRootNode(),compNode);//--------------f call
		atNode2=new AtomicNode("104#GCF14","Line:104-Line107");
		R.insert(atNode2, compNode);
		
		decNode=new DecisionNode("108#GCF15:DecisionNode:C4","scope.name == ST_PROP_VAL_CLASS or scope.name = ST_PROP_VAL_HYBRID");
		cond=new Condition("scope.name == ST_PROP_VAL_CLASS or scope.name = ST_PROP_VAL_HYBRID");
		decNode.setcondition(cond);
		R.insert(decNode, atNode2);
		atNode1=new AtomicNode("110#GCF16:then:GCF15","Line:110-Line110");
		R.insert(atNode1, decNode);
		atNode2=new AtomicNode("111#GCF17","Line:111-Line111");
		R.insert(atNode2, atNode1);
		atNode2.setParentNode(decNode);
		
		decNode=new DecisionNode("112#GCF18:DecisionNode:C5","scope.name == ST_PROP_VAL_STATE or scope.name = ST_PROP_VAL_HYBRID");
		cond=new Condition("scope.name == ST_PROP_VAL_STATE or scope.name = ST_PROP_VAL_HYBRID");
		decNode.setcondition(cond);
		R.insert(decNode, atNode2);
		atNode1=new AtomicNode("114#GCF19:then:GCF18","Line:114-Line114");
		R.insert(atNode1, decNode);
		atNode2=new AtomicNode("116#GCF20","Line:116-Line126");
		R.insert(atNode2, atNode1);
		atNode2.setParentNode(decNode);
		
		decNode=new DecisionNode("127#GCF21:DecisionNode:C6","tr.target.getAppliedStereotype(ST_FAILURE) != null");
		cond=new Condition("tr.target.getAppliedStereotype(ST_FAILURE) != null");
		decNode.setcondition(cond);
		R.insert(decNode, atNode2);
		atNode1=new AtomicNode("130#GCF22:then:GCF21","Line:130-Line132");
		R.insert(atNode1, decNode);
		
		atNode=new AtomicNode("133#GCF23:else:GCF21","Line:133-Line138");
		R.insert(atNode, decNode);
		atNode2=new AtomicNode("139#GCF24","Line:139-Line185");
		R.insert(atNode2, atNode1);
		atNode2.setParentNode(atNode);
		
		compNode=new CompositeNode("186#GCF25:Comp","self.generateClassEndCode()");
		R.insert(compNode, atNode2);
		R.insert(generateClassEndCode().getRootNode(),compNode);//--------------f call
		atNode2=new AtomicNode("18#GCF3","Line:187-Line195");
		R.insert(atNode2, compNode);
		
		return R;		
	}

	public static CFG generateHeader(){
		CFG R =new CFG();
		AtomicNode atNode1=new AtomicNode("200#GH1","Line:200-Line233");
		R.insert(atNode1, null);
		return R;
		}
	
	public static CFG generateCodeForNDStereotype(){
		CFG R =new CFG();
		AtomicNode atNode1=new AtomicNode("236#GCFNDS1","Line:236-Line246");
		R.insert(atNode1, null);
		return R;
		}
	
	public static CFG generateClassEndCode(){
		CFG R =new CFG();
		AtomicNode atNode1=new AtomicNode("248#GCEC1","Line:248-Line260");
		R.insert(atNode1, null);
		return R;
		}
}
