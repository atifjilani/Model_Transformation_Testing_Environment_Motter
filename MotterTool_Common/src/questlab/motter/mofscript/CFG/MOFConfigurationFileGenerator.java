package questlab.motter.mofscript.CFG;

import questlab.motter.CFG.AtomicNode;
import questlab.motter.CFG.CFG;
import questlab.motter.CFG.CompositeNode;
import questlab.motter.CFG.Condition;
import questlab.motter.CFG.DecisionNode;
import questlab.motter.CFG.Node;
//conditions 6 branches 10
public class MOFConfigurationFileGenerator {
	//conditions 6 branches 12+1=13    6+1=7
	public static Node generateConfigurationFile(CFG R, Node parent){
		AtomicNode atNode,atNode1,atNode2;
		CompositeNode compNode;
		DecisionNode decNode;
		Condition cond;
	//	CFG R =new CFG();
		//atNode=new AtomicNode("0#GCF1","Line:13-Line16");
		atNode1=new AtomicNode("13#GCF1","Line:13-Line16");
		R.insert(atNode1, parent);
		compNode=new CompositeNode("17#GCF2:Comp","self.generateHeader(packageName)");
		R.insert(compNode, atNode1);
		Node returnNode=generateHeader(R,compNode);
//		R.insert(generateHeader().getRootNode(),compNode);//--------------f call
		atNode2=new AtomicNode("18#GCF3","Line:18-Line40");
		R.insert(atNode2, returnNode);
		decNode=new DecisionNode("4141#GCF4:DecisionNode:C1","att.getAppliedStereotype(ST_NON_DETERMINISTIC) != null");
		String query="Class.allInstances()->exists(c|c.ownedAttribute->exists(o|o.oclIsTypeOf(Property))) " 
				   + "and Property.allInstances()->exists (p|p.stereotypeNonDeterministic>0) ";
		String query1="Class.allInstances()->exists(c|c.ownedAttribute->exists(o|o.oclIsTypeOf(Property))) " 
				   + "and Property.allInstances()->exists (p|p.stereotypeNonDeterministic<=0) ";
		cond=new Condition(query);
		decNode.setFalseCondition(new Condition(query1));
		decNode.setcondition(cond);
		decNode.setParentBlock(0);
		R.insert(decNode, atNode2);
		atNode1=new AtomicNode("4343#GCF5:then:GCF4","Line:43-Line44");
		R.insert(atNode1, decNode);
		atNode2=new AtomicNode("45#GCF6","Line:45-Line49");
		R.insert(atNode2, atNode1);
		atNode2.setParentNode(decNode);
		 query="Class.allInstances()->exists(c|c.ownedBehavior->exists(o| o.oclIsTypeOf(StateMachine))) " 
		    		+ "and StateMachine.allInstances()->exists(sm|sm.region->exists(r| r.oclIsTypeOf(Region))) "  
					+ "and Region.allInstances()->exists(r|r.transition->exists(t| t.oclIsTypeOf(Transition))) "
					+ "and Transition.allInstances()->exists(t|t.source.oclIsTypeOf(Pseudostate) and t.target.oclIsTypeOf(State) and " +
					  "t.stereotypeTimeProbability>0 ) " 
					;
		 query1="Class.allInstances()->exists(c|c.ownedBehavior->exists(o| o.oclIsTypeOf(StateMachine))) " 
		    		+ "and StateMachine.allInstances()->exists(sm|sm.region->exists(r| r.oclIsTypeOf(Region))) "  
					+ "and Region.allInstances()->exists(r|r.transition->exists(t| t.oclIsTypeOf(Transition))) "
					+ "and Transition.allInstances()->exists(t|t.source.oclIsTypeOf(Pseudostate) and t.target.oclIsTypeOf(State) and " +
					  "t.stereotypeTimeProbability<=0) " 
					;
		decNode=new DecisionNode("50#GCF7:DecisionNode:C2","t.getAppliedStereotype(ST_TIME_PROBABILITY) != null");
		cond=new Condition(query);
		decNode.setcondition(cond);
		decNode.setFalseCondition(new Condition (query1));
		decNode.setParentBlock(1);
		R.insert(decNode, atNode2);
		atNode1=new AtomicNode("5225#GCF8:then:GCF7","Line:52-Line53");
		R.insert(atNode1, decNode);
		atNode2=new AtomicNode("54#GCF9","Line:54-Line59");
		R.insert(atNode2, atNode1);
		atNode2.setParentNode(decNode);
		 query="Pseudostate.allInstances()->exists(p|p.stereotypeNDChoice>0)"; 
					
		 query1="Pseudostate.allInstances()->exists(p|p.stereotypeNDChoice<=0)"; 
					
		decNode=new DecisionNode("60#GCF10:DecisionNode:C3","ps.getAppliedStereotype(ST_ND_CHOICE) != null");
		cond=new Condition(query);
		decNode.setcondition(cond);
		decNode.setFalseCondition(new Condition(query1));
		decNode.setParentBlock(1);
		R.insert(decNode, atNode2);
		atNode1=new AtomicNode("6262#GCF11:then:GCF10","Line:62-Line63");
		R.insert(atNode1, decNode);
		atNode2=new AtomicNode("64#GCF12","Line:64-Line102");
		R.insert(atNode2, atNode1);
		atNode2.setParentNode(decNode);
		
		compNode=new CompositeNode("103#GCF13:Comp","generateCodeForNDStereotype(prop, classSpecificChoiceId)");
		R.insert(compNode, atNode2);
		Node returnNode1=generateCodeForNDStereotype(R,compNode);
		//R.insert(,compNode);//--------------f call
		atNode2=new AtomicNode("104#GCF14","Line:104-Line107");
		
		R.insert(atNode2, returnNode1);
		 query="Property.allInstances()->exists(p|p.stereotypeNonDeterministic>0 )"; 
			
		 query1="Property.allInstances()->exists(p|p.stereotypeNonDeterministic<=0 )"; 
				
		decNode=new DecisionNode("108801#GCF15:DecisionNode:C4","scope.name == ST_PROP_VAL_CLASS or scope.name = ST_PROP_VAL_HYBRID");
		cond=new Condition(query);
		decNode.setcondition(cond);
		decNode.setFalseCondition(new Condition(query1));
		decNode.setParentBlock(1);
		R.insert(decNode, atNode2);
		atNode1=new AtomicNode("110011#GCF16:then:GCF15","Line:110-Line110");
		R.insert(atNode1, decNode);
		atNode2=new AtomicNode("111#GCF17","Line:111-Line111");
		R.insert(atNode2, atNode1);
		atNode2.setParentNode(decNode);
		 query="Property.allInstances()->exists(p|p.stereotypeNonDeterministic>0 )"; 
			
		 query1="Property.allInstances()->exists(p|p.stereotypeNonDeterministic<=0)"; 
			
		decNode=new DecisionNode("112#GCF18:DecisionNode:C5","scope.name == ST_PROP_VAL_STATE or scope.name = ST_PROP_VAL_HYBRID");
		cond=new Condition(query);
		decNode.setcondition(cond);
		decNode.setFalseCondition(new Condition(query1));
		decNode.setParentBlock(1);
		R.insert(decNode, atNode2);
		atNode1=new AtomicNode("114#GCF19:then:GCF18","Line:114-Line114");
		R.insert(atNode1, decNode);
		atNode2=new AtomicNode("116#GCF20","Line:116-Line126");
		R.insert(atNode2, atNode1);
		atNode2.setParentNode(decNode);
		 query="Transition.allInstances()->exists(t|t.target.oclIsTypeOf(State) and t.stereotypeTimeProbability>0 ) and State.allInstances()->exists (s|s.stereotypeFailure>0)";  
			
		 query1="Transition.allInstances()->exists(t|t.target.oclIsTypeOf(State) and t.stereotypeTimeProbability>0) and State.allInstances()->exists (s|s.stereotypeFailure<=0)";
			
		decNode=new DecisionNode("127#GCF21:DecisionNode:C6","tr.target.getAppliedStereotype(ST_FAILURE) != null");
		cond=new Condition(query);
		decNode.setcondition(cond);
		decNode.setFalseCondition(new Condition(query1));
		decNode.setParentBlock(1);
		R.insert(decNode, atNode2);
		atNode1=new AtomicNode("130031#GCF22:then:GCF21","Line:130-Line132");
		R.insert(atNode1, decNode);
		
		atNode=new AtomicNode("133#GCF23:else:GCF21","Line:133-Line138");
		R.insert(atNode, decNode);
		atNode2=new AtomicNode("139#GCF24","Line:139-Line185");
		R.insert(atNode2, atNode1);
		atNode2.setParentNode(atNode);
		
		compNode=new CompositeNode("186#GCF25:Comp","self.generateClassEndCode()");
		R.insert(compNode, atNode2);
		Node returnNode2=generateClassEndCode(R,compNode);
		//R.insert(generateClassEndCode().getRootNode(),compNode);//--------------f call
		atNode2=new AtomicNode("18#GCF3","Line:187-Line195");
		R.insert(atNode2, returnNode2);
		
		return atNode2;		
	}
	//conditions 0 branches 1
	public static Node generateHeader(CFG R, Node parent){
	//	CFG R =new CFG();
		AtomicNode atNode1=new AtomicNode("200#GH1","Line:200-Line233");
		R.insert(atNode1, parent);
		return atNode1;
		}
	//conditions 0 branches 1
	public static Node generateCodeForNDStereotype(CFG R, Node parent){
//		CFG R =new CFG();
		AtomicNode atNode1=new AtomicNode("236#GCFNDS1","Line:236-Line246");
		R.insert(atNode1, parent);
		return atNode1;
		}
	//conditions 0 branches 1
	public static Node generateClassEndCode(CFG R, Node parent){
	//	CFG R =new CFG();
		AtomicNode atNode1=new AtomicNode("248#GCEC1","Line:248-Line260");
		R.insert(atNode1, parent);
		return atNode1;
		}
}
