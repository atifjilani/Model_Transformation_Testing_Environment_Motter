package questlab.motter.mofscript.CFG;

import questlab.motter.CFG.AtomicNode;
import questlab.motter.CFG.CFG;
import questlab.motter.CFG.CompositeNode;
import questlab.motter.CFG.Condition;
import questlab.motter.CFG.DecisionNode;
import questlab.motter.CFG.Node;
//conditions 24 branches 79-20 =59
public class MOFContextClassHelper {
	//conditions 0 branches 1
	public static Node generateContextRegionClass(CFG R, Node parent){
		AtomicNode atNode1,atNode2;
		CompositeNode compNode, compNode1;
		
//		CFG R =new CFG();
		atNode1=new AtomicNode("12#GCRC1","Line:13-Line21");
		R.insert(atNode1, parent);
		compNode=new CompositeNode("22#GCRC2:Comp","self.generatePackage(pkgName)");
		R.insert(compNode, atNode1);
		Node returnNode=generatePackage(R,compNode);
//		R.insert(generatePackage().getRootNode(),compNode);//--------------f call
		compNode1=new CompositeNode("24#GCRC3:Comp","self.generateImports()");
		R.insert(compNode1, returnNode);
		returnNode=generateImports(R,compNode);
//		R.insert(generateImports().getRootNode(),compNode1);//--------------f call
		atNode2=new AtomicNode("25#GCRC4","Line:25-Line30");
		R.insert(atNode2, returnNode);
		compNode=new CompositeNode("31#GCRC5:Comp","self.generateCCConstructorForRegionContext(stateList, stateObjectsName, initStates, className, contextClass)");
		R.insert(compNode, atNode2);
		returnNode=generateCCConstructorForRegionContext(R,compNode);
//		R.insert(generateCCConstructorForRegionContext().getRootNode(),compNode);//--------------f call
		compNode1=new CompositeNode("34#GCRC6:Comp","self.generateStateObjectDefinitionCode(stateObjectsName, false)");
		R.insert(compNode1, returnNode);
		returnNode=generateStateObjectDefinitionCode(R,compNode1);
//		R.insert(generateStateObjectDefinitionCode().getRootNode(),compNode1);//--------------f call
		compNode=new CompositeNode("37#GCRC7:Comp","self.generateStateClassesRelatedCodeForRegionContext(contextClass, stateList, stateObjectsName, initStates, actionClass)");
		R.insert(compNode, returnNode);
		returnNode=generateStateClassesRelatedCodeForRegionContext(R,compNode);
//		R.insert(generateStateClassesRelatedCodeForRegionContext().getRootNode(),compNode);//--------------f call
		compNode1=new CompositeNode("40#GCRC8:Comp","self.generateCCSignalCode(signalsByRegion, stateObjectsName, true, contextClass, regionName)");
		R.insert(compNode1, returnNode);
		returnNode=generateCCSignalCode(R,compNode1);
//		R.insert(generateCCSignalCode().getRootNode(),compNode1);//--------------f call
		compNode=new CompositeNode("43#GCRC9:Comp","self.generateRunMethod(true)");
		R.insert(compNode, returnNode);///////////////////////////////////////////////////////////////////////////////////
		returnNode=generateRunMethod(R,compNode);
//		R.insert(generateRunMethod().getRootNode(),compNode);//--------------f call
		compNode1=new CompositeNode("46#GCRC10:Comp","self.generateTimeoutCodeForRegionContextClass(stateObjectsName, contextClass, stateList)");
		R.insert(compNode1, returnNode);
		returnNode=generateTimeoutCodeForRegionContextClass(R,compNode1);
//		R.insert(generateTimeoutCodeForRegionContextClass().getRootNode(),compNode1);//--------------f call
		compNode=new CompositeNode("49#GCRC11:Comp","self.generateChangeEventCodeForRegionContextClass(stateObjectsName, contextClass)");
		R.insert(compNode, returnNode);
		returnNode=generateChangeEventCodeForRegionContextClass(R,compNode);
//		R.insert(generateChangeEventCodeForRegionContextClass().getRootNode(),compNode);//--------------f call
		compNode1=new CompositeNode("52#GCRC12:Comp","self.generateCompletionEventCodeForRegionContextClass(stateObjectsName, contextClass)");
		R.insert(compNode1, returnNode);
		returnNode=generateCompletionEventCodeForRegionContextClass(R,compNode1);
//		R.insert(generateCompletionEventCodeForRegionContextClass().getRootNode(),compNode1);//--------------f call
		compNode=new CompositeNode("54#GCRC11:Comp","self.generateIsInStateMethod(stateList, stateObjectsName)");
		R.insert(compNode, returnNode);
		returnNode=generateIsInStateMethod(R,compNode);
//		R.insert(generateIsInStateMethod().getRootNode(),compNode);//--------------f call
		compNode1=new CompositeNode("56#GCRC12:Comp","self.generateExitStateMethodForRegionContext(contextClass,stateList, stateObjectsName)");
		R.insert(compNode1, returnNode);
		returnNode=generateExitStateMethodForRegionContext(R,compNode1);
//		R.insert(generateExitStateMethodForRegionContext().getRootNode(),compNode1);//--------------f call
		return returnNode;
	}
	//conditions 0 branches 1
	public static Node generatePackage(CFG R, Node parent){
		AtomicNode atNode1;
//		CFG R =new CFG();
		atNode1=new AtomicNode("64#GP1","Line:65-Line78");
		R.insert(atNode1, parent);
		return atNode1;
	}
	//conditions 0 branches 1
	public static Node generateImports(CFG R, Node parent){
		AtomicNode atNode2;
//		CFG R =new CFG();
		atNode2=new AtomicNode("80#GI1","Line:80-Line88");
		R.insert(atNode2, parent);
		return atNode2;
	}
	//conditions 1 branches 2
	public static Node generateHeaderForContextClass(CFG R, Node parent){
		AtomicNode atNode1,atNode2;
		CompositeNode compNode, compNode1;
		DecisionNode decNode;
		Condition cond;
//		CFG R =new CFG();
		
		compNode=new CompositeNode("92#GHFCC1:Comp","self.generatePackage(packageName)");
		R.insert(compNode, parent);
		Node returnNode=generatePackage(R,compNode);
//		R.insert(generatePackage().getRootNode(),compNode);//--------------f call
		compNode1=new CompositeNode("93#GHFCC2:Comp","self.generateImports()");
		R.insert(compNode1, returnNode);
		returnNode=generateImports(R,compNode1);
//		R.insert(generateImports().getRootNode(),compNode1);//--------------f call
		String query="Class.allInstances()->exists(c|c.isActive=true)";
		String query1="Class.allInstances()->exists(c|c.isActive=false)";
		decNode=new DecisionNode("9797#GHFCC3:DecisionNode:C1","self.isActive = true && self.superClass.isEmpty()");
		cond=new Condition(query);
		decNode.setcondition(cond);
		decNode.setFalseCondition(new Condition(query1));
		decNode.setParentBlock(0);
		R.insert(decNode, returnNode);
		atNode1=new AtomicNode("9898#GHFCC4:then:GHFCC3","Line:98-Line101");
		R.insert(atNode1, decNode);
		atNode2=new AtomicNode("102102#GHFCC5","Line:102-Line106");
		R.insert(atNode2, atNode1);
		atNode2.setParentNode(decNode);
		return atNode2;
	}
	//conditions 1 branches 2
	public static Node generateHeaderForStatelessClass(CFG R, Node parent){
		AtomicNode atNode1,atNode2;
		CompositeNode compNode, compNode1;
		DecisionNode decNode;
		Condition cond;
//		CFG R =new CFG();
				
		compNode=new CompositeNode("111#GHFSC1:Comp","self.generatePackage(packageName)");
		R.insert(compNode, parent);
		Node returnNode=generatePackage(R,compNode);
//		R.insert(generatePackage().getRootNode(),compNode);//--------------f call
		compNode1=new CompositeNode("113#GHFSC2:Comp","self.generateImports()");
		R.insert(compNode1, returnNode);
		returnNode=generateImports(R,compNode);
//		R.insert(generateImports().getRootNode(),compNode);//--------------f call
		String query="Class.allInstances()->exists(c|c.isActive=true)";
		String query1="Class.allInstances()->exists(c|c.isActive=false)";
		decNode=new DecisionNode("117711#GHFSC3:DecisionNode:C1","self.isActive = true && self.superClass.isEmpty()");
		cond=new Condition(query);
		decNode.setcondition(cond);
		decNode.setFalseCondition(new Condition(query1));
		decNode.setParentBlock(0);
		R.insert(decNode, returnNode);
		atNode1=new AtomicNode("118118#GHFSC4:then:GHFSC3","Line:118-Line121");
		R.insert(atNode1, decNode);
		atNode2=new AtomicNode("122#GHFSC5","Line:122-Line127");
		R.insert(atNode2, atNode1);
		atNode2.setParentNode(decNode);
		return atNode2;
	}
	//conditions 0 branches 1
	public static Node generateStateObjectDefinitionCode(CFG R, Node parent){
		AtomicNode atNode,atNode1,atNode2;
		
		DecisionNode decNode;
		Condition cond;
//		CFG R =new CFG();
		
		atNode1=new AtomicNode("131#GSODC1","Line:131-Line136");
		R.insert(atNode1, parent);	
		 String query="Region.allInstances()->size()>1 and StateMachine.allInstances()->exists(st|st.region->size()>1)";
		 String query1="Region.allInstances()->size()>0 and StateMachine.allInstances()->exists(st|st.region->size()=1)";
		
		decNode=new DecisionNode("137731#GSODC2:DecisionNode:C1","isParallelSM");
		cond=new Condition(query);
		decNode.setcondition(cond);
		decNode.setFalseCondition(new Condition(query1));
		decNode.setParentBlock(0);
		R.insert(decNode, atNode1);
		atNode1=new AtomicNode("138831#GSODC3:then:GSODC2","Line:138-Line143");
		R.insert(atNode1, decNode);
		atNode2=new AtomicNode("145541#GSODC4:else:GSODC2","Line:145-Line150");
		R.insert(atNode2, decNode);
		
		atNode=new AtomicNode("152#GSODC5","Line:151-Line154");
		R.insert(atNode, atNode1);
		atNode.setParentNode(atNode2);
		return atNode;
	}
	//conditions 8 branches 17              8+1=9 17-9=8
	public static Node generateAttributeRelatedCode(CFG R, Node parent){
		AtomicNode atNode,atNode1,atNode2;
		CompositeNode  compNode1;
		DecisionNode decNode,decNode1,decNode2;
		Condition cond;
//		CFG R =new CFG();
		
		atNode=new AtomicNode("156#GARC1","Line:156-Line164");
		R.insert(atNode, parent);
		compNode1=new CompositeNode("165#GARC2:Comp","type = p.type.getJavaEquivalent()");
		R.insert(compNode1, atNode);
		Node returnNode=MOFJavaSyntaxTrans.getJavaEquivalent(R,compNode1);
//		R.insert(MOFJavaSyntaxTrans.getJavaEquivalent().getRootNode(),compNode1);//--------------f call
		 String query="Property.allInstances()->exists(p|p.association.oclIsTypeOf(Association))";
		 String query1="Property.allInstances()->exists(p|p.association.oclIsUndefined())";
		
		decNode=new DecisionNode("166661#GARC3:DecisionNode:C1","p.association != null");
		cond=new Condition(query);
		decNode.setcondition(cond);
		decNode.setFalseCondition(new Condition(query1));
		decNode.setParentBlock(0);
		R.insert(decNode, returnNode);
		atNode1=new AtomicNode("167761#GARC4:then:GARC3","Line:167-Line167");
		R.insert(atNode1, decNode);
		  query="Property.allInstances()->exists(p|p.upperValue>1 or p.upperValue=-1)";
		  query1="Property.allInstances()->exists(p|p.upperValue.oclIsUndefined())";
		
		decNode1=new DecisionNode("168#GARC5:DecisionNode:C2","p.upperValue != null and p.upperValue.oclIsTypeOf(uml.LiteralUnlimitedNatural) and (p.upper = null) or p.upper > 1 or p.upper = -1");
		cond=new Condition(query);
		decNode1.setcondition(cond);
		decNode1.setFalseCondition(new Condition(query1));
		decNode1.setParentBlock(1);
		R.insert(decNode1, atNode1);
		atNode=new AtomicNode("169#GARC6:then:GARC5","Line:169-Line173");
		R.insert(atNode, decNode1);
		atNode1=new AtomicNode("174#GARC7","Line:174-Line174");
		R.insert(atNode1, atNode);
		atNode1.setParentNode(decNode1);
		  query="Property.allInstances()->exists(p|p.type.oclAsType(Class).stereotypeSystem>0)";
		  query1="Property.allInstances()->exists(p|p.type.oclAsType(Class).stereotypeSystem<=0)";
		
		decNode2=new DecisionNode("175#GARC8:DecisionNode:C3","p.type.getAppliedStereotype(ST_SYSTEM) != null");
		cond=new Condition(query);
		decNode2.setcondition(cond);
		decNode2.setFalseCondition(new Condition(query1));
		decNode2.setParentBlock(1);
		R.insert(decNode2, atNode1);
		atNode=new AtomicNode("176#GARC9:then:GARC8","Line:176-Line178");
		R.insert(atNode, decNode2);
		atNode1=new AtomicNode("178#GARC10","Line:178-Line178");
		R.insert(atNode1, atNode);
		atNode1.setParentNode(decNode2);
		atNode=new AtomicNode("178#GARC11","Line:179-Line179");
		R.insert(atNode, atNode1);
		atNode.setParentNode(decNode);
		
		
		atNode2=new AtomicNode("180#GARC12","Line:180-Line198");
		R.insert(atNode2, atNode);
		  query="Property.allInstances()->exists(p|p.association.oclIsTypeOf(Association))";
		  query1="Property.allInstances()->exists(p|p.association.oclIsUndefined())";
		
		decNode=new DecisionNode("199991#GARC13:DecisionNode:C4","p.association != null");
		cond=new Condition(query);
		decNode.setcondition(cond);
		decNode.setFalseCondition(new Condition(query1));
		decNode.setParentBlock(0);
		R.insert(decNode, atNode2);
		atNode1=new AtomicNode("200002#GARC14:then:GARC13","Line:200-Line201");
		R.insert(atNode1, decNode);
		 query="Property.allInstances()->exists(p|p.upperValue>1 or p.upperValue=-1)";
		  query1="Property.allInstances()->exists(p|p.upperValue.oclIsUndefined())";
		
		decNode1=new DecisionNode("202#GARC15:DecisionNode:C5","p.upperValue != null and p.upperValue.oclIsTypeOf(uml.LiteralUnlimitedNatural) and (p.upper = null) or p.upper > 1 or p.upper == -1");
		cond=new Condition(query);
		decNode1.setcondition(cond);
		decNode1.setFalseCondition(new Condition(query1));
		decNode1.setParentBlock(1);
		R.insert(decNode1, atNode1);
		atNode=new AtomicNode("203#GARC16:then:GARC15","Line:203-Line206");
		R.insert(atNode, decNode1);
		atNode1=new AtomicNode("206#GARC17","Line:206-Line206");
		R.insert(atNode1, atNode);
		atNode1.setParentNode(decNode1);
		atNode=new AtomicNode("207#GARC18","Line:207-Line207");
		R.insert(atNode, atNode1);
		atNode.setParentNode(decNode);
		  query="Property.allInstances()->exists(p|p.type.oclAsType(Class).stereotypeSystem>0)";
		  query1="Property.allInstances()->exists(p|p.type.oclAsType(Class).stereotypeSystem<=0)";
		
		decNode2=new DecisionNode("208802#GARC19:DecisionNode:C6","p.type.getAppliedStereotype(ST_SYSTEM) != null");
		cond=new Condition(query);
		decNode2.setcondition(cond);
		decNode2.setFalseCondition(new Condition(query1));
		decNode2.setParentBlock(0);
		R.insert(decNode2, atNode);
		atNode=new AtomicNode("209902#GARC20:then:GARC19","Line:209-Line211");
		R.insert(atNode, decNode2);
		atNode1=new AtomicNode("212#GARC21","Line:212-Line232");
		R.insert(atNode1, atNode);
		atNode1.setParentNode(decNode2);
		 query="Region.allInstances()->size()>1 and StateMachine.allInstances()->exists(st|st.region->size()>1)";
		 query1="Region.allInstances()->size()>0 and StateMachine.allInstances()->exists(st|st.region->size()=1)";
		
		decNode2=new DecisionNode("233332#GARC22:DecisionNode:C7","isParallel");
		cond=new Condition(query);
		decNode2.setcondition(cond);
		decNode2.setFalseCondition(new Condition(query1));
		decNode2.setParentBlock(0);
		R.insert(decNode2, atNode1);
		atNode=new AtomicNode("234432#GARC23:then:GARC22","Line:234-Line236");
		R.insert(atNode, decNode2);
		atNode1=new AtomicNode("237#GARC24","Line:237-Line245");
		R.insert(atNode1, atNode);
		atNode1.setParentNode(decNode2);
		 query="Region.allInstances()->size()>1 and StateMachine.allInstances()->exists(st|st.region->size()>1)";
		 query1="Region.allInstances()->size()>0 and StateMachine.allInstances()->exists(st|st.region->size()=1)";
		
		decNode1=new DecisionNode("246#GARC25:DecisionNode:C5","isParallel");
		cond=new Condition(query);
		decNode1.setcondition(cond);
		decNode1.setFalseCondition(new Condition(query1));
		decNode1.setParentBlock(0);
		R.insert(decNode1, atNode1);
		atNode=new AtomicNode("247#GARC26:then:GARC25","Line:247-Line249");
		R.insert(atNode, decNode1);
		atNode1=new AtomicNode("250#GARC27:else:GARC25","Line:251-Line255");
		R.insert(atNode1, decNode1);
		
		atNode=new AtomicNode("256#GARC28","Line:256-Line273");
		R.insert(atNode, atNode);
		atNode.setParentNode(atNode1);
	
		return atNode;
	}
	//conditions 0 branches 1
	public static Node generateAttributeRelatedCodeStateless(CFG R, Node parent){
		AtomicNode atNode1,atNode2;
		CompositeNode compNode;
		
//		CFG R =new CFG();
		
		atNode1=new AtomicNode("275#GARCS1","Line:276-Line284");
		R.insert(atNode1, parent);
		compNode=new CompositeNode("285#GARCS2:Comp","p.type.getJavaEquivalent()");
		R.insert(compNode, atNode1);
		Node returnNode=MOFJavaSyntaxTrans.getJavaEquivalent(R,compNode);
//		R.insert(MOFJavaSyntaxTrans.getJavaEquivalent().getRootNode(),compNode);//--------------f call
		atNode2=new AtomicNode("286#GP3","Line:286-Line313");
		R.insert(atNode2, returnNode);
		
		return atNode2;
	}
	//conditions 0 branches 1
	public static Node generateStateClassesRelatedCodeForParallelSM(CFG R, Node parent){
		AtomicNode atNode1,atNode2;
		CompositeNode compNode;
		
//		CFG R =new CFG();
		
		atNode1=new AtomicNode("316#GSCRCFPSM1","Line:317-Line346");
		R.insert(atNode1, parent);
		compNode=new CompositeNode("347#GSCRCFPSM2:Comp","formatStateName(self.name, state.name)");
		R.insert(compNode, atNode1);
		Node returnNode=MOFJavaSyntaxTrans.formatStateName(R,compNode);
//		R.insert(MOFJavaSyntaxTrans.formatStateName().getRootNode(),compNode);//--------------f call
		atNode2=new AtomicNode("348#GSCRCFPSM3","Line:348-Line356");
		R.insert(atNode2, returnNode);
		
		return atNode2;
	}
	//conditions 0 branches 1
	public static Node generateExitStateMethodForRegionContext(CFG R, Node parent){
		AtomicNode atNode1;
		
//		CFG R =new CFG();
		
		atNode1=new AtomicNode("360#GESMFRC1","Line:360-Line379");
		R.insert(atNode1, parent);
		
		return atNode1;
	}
	//conditions 3 branches 7               3+1=4   7-4=3
	public static Node generateStateClassesRelatedCodeForRegionContext(CFG R, Node parent){
	AtomicNode atNode,atNode1,atNode2;
	CompositeNode compNode;
	DecisionNode decNode,decNode1,decNode2;
	Condition cond;
//	CFG R =new CFG();
	
	atNode1=new AtomicNode("383#GSCRCFRC1","Line:383-Line388");
	R.insert(atNode1, parent);	
	compNode=new CompositeNode("389#GSCRCFRC2:Comp","formatStateName(self.name, state.name)");
	R.insert(compNode, atNode1);
	//R.insert(generateHeader().getRootNode(),compNode);//--------------f call
	atNode2=new AtomicNode("390#GSCRCFRC3","Line:390-Line417");
	R.insert(atNode2, compNode);
	String query="Transition.allInstances()->exists(t|t.source.oclIsTypeOf(Pseudostate))";
	String query1="Transition.allInstances()->exists(t|t.source.oclIsTypeOf(State))";
	
	decNode=new DecisionNode("418#GSCRCFRC4:DecisionNode:C1","t.source.oclIsTypeOf(uml.Pseudostate)");
	cond=new Condition(query);
	decNode.setcondition(cond);
	decNode.setFalseCondition(new Condition(query1));
	decNode.setParentBlock(0);
	R.insert(decNode, compNode);
	atNode1=new AtomicNode("419#GSCRCFRC5:then:GSCRCFRC4","Line:419-Line420");
	R.insert(atNode1, decNode);
	query="Pseudostate.allInstances()->exists(f|(f.kind = PseudostateKind::initial))";
	query1="Pseudostate.allInstances()->exists(f|((f.kind = PseudostateKind::deepHistory) or (f.kind = PseudostateKind::shallowHistory))) ";
	
	decNode1=new DecisionNode("421#GSCRCFRC6:DecisionNode:C2","pseudo.kind == uml.PseudostateKind.initial");
	cond=new Condition(query);
	decNode1.setcondition(cond);
	decNode1.setFalseCondition(new Condition(query1));
	decNode1.setParentBlock(1);
	R.insert(decNode1, atNode1);
	atNode1=new AtomicNode("422#GSCRCFRC7:then:GSCRCFRC6","Line:422-Line426");
	R.insert(atNode1, decNode1);
	query="Pseudostate.allInstances()->exists(f|f.outgoing->exists(t|t.effect.oclIsTypeOf(OpaqueBehavior)))";
	query1="Pseudostate.allInstances()->exists(f|f.outgoing->exists(t|t.effect.oclIsUndefined()))";
	
	decNode2=new DecisionNode("427724#GSCRCFRC8:DecisionNode:C3","behavior != null");
	cond=new Condition(query);
	decNode2.setcondition(cond);
	decNode2.setFalseCondition(new Condition(query1));
	decNode2.setParentBlock(1);
	R.insert(decNode2, atNode1);
	atNode1=new AtomicNode("428824#GSCRCFRC9:then:GSCRCFRC8","Line:428-Line436");
	R.insert(atNode1, decNode2);
	
	atNode=new AtomicNode("437#GSCRCFRC10","Line:437-Line437");
	R.insert(atNode, atNode1);
	atNode.setParentNode(decNode2);
	
	atNode1=new AtomicNode("438#GSCRCFRC11","Line:438-Line438");
	R.insert(atNode1, atNode);
	atNode1.setParentNode(decNode1);
	
	
	atNode2=new AtomicNode("439#GSCRCFRC12","Line:439-Line489");
	R.insert(atNode2,atNode1 );
	atNode2.setParentNode(decNode);
	
	return atNode2;
 }
	//conditions 0 branches 1
	public static Node getContainingState(CFG R, Node parent){
		AtomicNode atNode1,atNode2;
		CompositeNode compNode;
		
//		CFG R =new CFG();
		
		atNode1=new AtomicNode("491#GCS1","Line:491-Line499");
		R.insert(atNode1, parent);
		compNode=new CompositeNode("500#GCS2:Comp","isStateInRegion(self)");
		R.insert(compNode, atNode1);
		Node returnNode=isStateInRegion(R,compNode);
//		R.insert(isStateInRegion().getRootNode(),compNode);//--------------f call
		atNode2=new AtomicNode("501#GCS3","Line:501-Line508");
		R.insert(atNode2, returnNode);
		
		return atNode2;
	}
	//conditions 2 branches 5           2+1=3 5-3=2
	public static Node isStateInRegion(CFG R, Node parent){
		AtomicNode atNode, atNode1,atNode2;
		CompositeNode compNode;
		DecisionNode decNode, decNode1;
		Condition cond;
//		CFG R =new CFG();
		
		atNode1=new AtomicNode("509#ISIR1","Line:509-Line516");
		R.insert(atNode1, parent);
		String query="Transition.allInstances()->exists(t|t.target.oclIsTypeOf(State))";
		String query1="Transition.allInstances()->exists(t|t.target.oclIsTypeOf(State)) and State.allInstances()->exists(st|st.isComposite=false)";

		decNode1=new DecisionNode("517#ISIR2:DecisionNode:C1","s.uml.State==true");
		cond=new Condition(query);
		decNode1.setcondition(cond);
		decNode1.setFalseCondition(new Condition(query1));
		decNode1.setParentBlock(0);
		R.insert(decNode1, atNode1);
		atNode2=new AtomicNode("518#ISIR3:then:ISIR2","Line:517-Line520");
		R.insert(atNode2, decNode1);
		
		atNode1=new AtomicNode("522#ISIR4:else:ISIR2","Line:522-Line522");
		R.insert(atNode1, decNode1);
		query="State.allInstances()->exists(st|st.isComposite=true)";
		query1="State.allInstances()->exists(st|st.isComposite=false)";
		decNode=new DecisionNode("523#ISIR5:DecisionNode:C2","s.uml.State.iscomposite");
		cond=new Condition(query);
		decNode.setcondition(cond);
		decNode.setFalseCondition(new Condition(query1));
		decNode.setParentBlock(0);
		R.insert(decNode, atNode1);
		atNode=new AtomicNode("524#ISIR6:then:ISIR5","Line:524-Line527");
		R.insert(atNode, decNode);
		compNode=new CompositeNode("528#ISIR7:Comp","isStateInRegion(self)");
		R.insert(compNode, atNode);
//		R.insert(isStateInRegion().getRootNode(),compNode);//--------------f call
		atNode1=new AtomicNode("529#ISIR8","Line:529-Line533");
		R.insert(atNode1, compNode);
		atNode=new AtomicNode("534#ISIR9","Line:534-Line534");
		R.insert(atNode, atNode1);
		atNode.setParentNode(decNode);
				
		atNode1=new AtomicNode("536#ISIR10","Line:536-Line538");
		R.insert(atNode1, atNode2);
		atNode1.setParentNode(atNode);
		
		return atNode1;
	}
	//conditions 2 branches 5           2+1=3 5-3=2
	public static Node generateStateClassesRelatedCode(CFG R, Node parent){
		AtomicNode atNode, atNode1,atNode2;
		CompositeNode compNode;
		DecisionNode decNode;
		Condition cond;
//		CFG R =new CFG();
		atNode1=new AtomicNode("541#GSCRC1","Line:541-Line548");
		R.insert(atNode1, parent);
		compNode=new CompositeNode("549#GSCRC2:Comp","formatStateName(self.name, state.name)");
		R.insert(compNode, atNode1);
		//R.insert(generateHeader().getRootNode(),compNode);//--------------f call
		atNode2=new AtomicNode("550#GSCRC3","Line:550-Line571");
		R.insert(atNode2, compNode);
		compNode=new CompositeNode("572#GSCRC4:Comp","state.getContainingState(immediateStateList)");
		R.insert(compNode, atNode2);
		Node returnNode=getContainingState(R,compNode);
//		R.insert(getContainingState().getRootNode(),compNode);//--------------f call
		atNode2=new AtomicNode("573#GSCRC5","Line:573-Line615");
		R.insert(atNode2, returnNode);
		
		String query="State.allInstances()->exists(st|st.isComposite=true and st.region->size()=1)";
		String query1="State.allInstances()->exists(st|st.isComposite=false)";
		
		decNode=new DecisionNode("616#GSCRC6:DecisionNode:C1","s.uml.State.iscomposite and s.uml.State.region.size() == 1");
		cond=new Condition(query);
		decNode.setcondition(cond);
		decNode.setFalseCondition(new Condition(query1));
		decNode.setParentBlock(0);
		R.insert(decNode, atNode2);
		atNode=new AtomicNode("617#GSCRC7:then:GSCRC6","Line:617-Line619");
		R.insert(atNode, decNode);
		atNode1=new AtomicNode("620#GSCRC8","Line:620-Line622");
		R.insert(atNode1, atNode);
		atNode.setParentNode(decNode);
		
		query="State.allInstances()->exists(st|st.isComposite=true)";
		query1="State.allInstances()->exists(st|st.isComposite=false)";
		
		decNode=new DecisionNode("623326#GSCRC9:DecisionNode:C2","compositeStates.size() > 0");
		cond=new Condition(query);
		decNode.setcondition(cond);
		decNode.setFalseCondition(new Condition(query1));
		decNode.setParentBlock(0);
		R.insert(decNode, atNode2);
		atNode=new AtomicNode("624426#GSCRC10:then:GSCRC9","Line:624-Line805");
		R.insert(atNode, decNode);
		atNode1=new AtomicNode("806608#GSCRC11:else:GSCRC9","Line:807-Line830");
		R.insert(atNode1, decNode);
		
		atNode2=new AtomicNode("831#GSCRC12","Line:832-Line839");
		R.insert(atNode2, atNode);
		atNode2.setParentNode(atNode1);
		
		return atNode2;
	}
	//conditions 3 branches 7           3+1=4   7-4=3
	public static Node generateCCConstructor(CFG R, Node parent){
		AtomicNode atNode, atNode1,atNode2,atNode3;
		CompositeNode compNode;
		DecisionNode decNode, decNode1;
		Condition cond;
//		CFG R =new CFG();
		atNode1=new AtomicNode("843#GCCC0","Line:843-Line868");
		R.insert(atNode1, parent);
		String query="Property.allInstances()->exists(p|p.stereotypeNonDeterministic>0)";
		String query1="Property.allInstances()->exists(p|p.stereotypeNonDeterministic<=0)";
		
		decNode1=new DecisionNode("869968#GCCC1:DecisionNode:C1","scope.name == ST_PROP_VAL_CLASS");
		cond=new Condition(query);
		decNode1.setcondition(cond);
		decNode1.setFalseCondition(new Condition(query1));
		decNode1.setParentBlock(0);
		R.insert(decNode1, atNode1);
		atNode=new AtomicNode("870078#GCCC1-1:then:GCCC1","Line:951-Line1002");
		R.insert(atNode, decNode1);
		
		atNode2=new AtomicNode("874478#GCCC1-2:else:GCCC1","Line:874-875");
		R.insert(atNode2, decNode1);
		
		atNode1=new AtomicNode("876#GCCC1-3","Line:876-949");
		R.insert(atNode1, atNode);
		atNode1.setParentNode(atNode2);
		
		query="Region.allInstances()->size()>1 and StateMachine.allInstances()->exists(st|st.region->size()>1)";
		query1="Region.allInstances()->size()>0 and StateMachine.allInstances()->exists(st|st.region->size()=1)";
		
		decNode=new DecisionNode("950#GCCC2:DecisionNode:C1","isParallelSM");
		cond=new Condition(query);
		decNode.setcondition(cond);
		decNode.setFalseCondition(new Condition(query1));
		decNode.setParentBlock(0);
		R.insert(decNode,atNode1);
		atNode=new AtomicNode("951#GCCC3:then:GCCC2","Line:951-Line1002");
		R.insert(atNode, decNode);
		
		atNode1=new AtomicNode("1005#GCCC4:else:GCCC2","Line:1005-Line1021");
		R.insert(atNode1, decNode);
				
		query="Transition.allInstances()->exists(t|t.source.oclIsTypeOf(Pseudostate))";
		query1="Transition.allInstances()->exists(t|t.source.oclIsUndefined())";
		
		decNode1=new DecisionNode("1022#GCCC5:DecisionNode:C1","initSt.oclIsTypeOf(uml.Pseudostate)");
		cond=new Condition(query);
		decNode1.setcondition(cond);
		decNode1.setFalseCondition(new Condition(query1));
		decNode1.setParentBlock(0);
		R.insert(decNode1, atNode1);
		atNode1=new AtomicNode("1023#GCCC6:then:GCCC5","Line:1023-Line1024");
		R.insert(atNode1, decNode1);
		compNode=new CompositeNode("1025#GCCC7:Comp","self.generateSetInitStateCodeForChoiceNode(stateObjName, initSt, oclToJavaClassPath)");
		R.insert(compNode, atNode1);
		Node returnNode=generateSetInitStateCodeForChoiceNode(R,compNode);
//		R.insert(generateSetInitStateCodeForChoiceNode().getRootNode(),compNode);//--------------f call
		
		atNode2=new AtomicNode("1027#GCCC8:else:GCCC5","Line:1027-Line1031");
		R.insert(atNode2, decNode1);
		
		atNode3=new AtomicNode("1032#GCCC9","Line:1032-Line1035");
		R.insert(atNode3, returnNode);
		atNode3.setParentNode(atNode2);
				
		atNode2=new AtomicNode("1036#GCCC10","Line:1036-Line1036");
		R.insert(atNode2, atNode);
		atNode2.setParentNode(atNode3);

		atNode1=new AtomicNode("1037#GCCC11","Line:1037-Line1093");
		R.insert(atNode1, atNode2);
	    return atNode1;
	}
	//conditions 0 branches 1
	public static Node generateCCConstructorForRegionContext(CFG R, Node parent){
		AtomicNode atNode1,atNode2;
		CompositeNode compNode;
		
//		CFG R =new CFG();
		
		atNode1=new AtomicNode("1095#GCCCFGC1","Line:1095-Line1105");
		R.insert(atNode1, parent);
		compNode=new CompositeNode("1106#GCCCFGC2:Comp","formatStateName(self.name, state.name)");
		R.insert(compNode, atNode1);
		//R.insert(generateHeader().getRootNode(),compNode);//--------------f call
		atNode2=new AtomicNode("1107#GCCCFGC3","Line:1107-Line1130");
		R.insert(atNode2, compNode);
		
		return atNode2;
	}
	//conditions 0 branches 1
	public static Node generateConstructorStateless(CFG R, Node parent){
		AtomicNode atNode1;
//		CFG R =new CFG();
		
		atNode1=new AtomicNode("1132#GCS1","Line:1132-Line1141");
		R.insert(atNode1, parent);
		
		
		return atNode1;
	}
	//conditions 0 branches 1
	public static Node generateOperationRelatedCode(CFG R, Node parent){
		AtomicNode atNode1;
//		CFG R =new CFG();
		atNode1=new AtomicNode("1144#GORC1","Line:1144-Line1152");
		R.insert(atNode1, parent);
				
		return atNode1;
	}
	//conditions 2 branches 5           2+1=3 5-3=2
	public static Node generateCCSignalCode(CFG R, Node parent){
		AtomicNode atNode,atNode1,atNode2;
		CompositeNode compNode,compNode1;
		DecisionNode decNode,decNode1;
		Condition cond;
//		CFG R =new CFG();
		
		atNode1=new AtomicNode("1155#GCCSC1","Line:1155-Line1165");
		R.insert(atNode1, parent);
		String query="Region.allInstances()->size()>1 and StateMachine.allInstances()->exists(st|st.region->size()>1)";
		String  query1="Region.allInstances()->size()>0 and StateMachine.allInstances()->exists(st|st.region->size()=1)";
		
		decNode=new DecisionNode("116611#GCCSC2:DecisionNode:C1","signalsByRegion.size() > 1 and (not isRegionCode)");
		cond=new Condition(query);
		decNode.setcondition(cond);
		decNode.setParentBlock(0);
		decNode.setFalseCondition(new Condition(query1));
		R.insert(decNode, atNode1);
		atNode=new AtomicNode("116711#GCCSC3:then:GCCSC2","Line:1167-Line1200");
		R.insert(atNode, decNode);
		compNode=new CompositeNode("1201#GCCSC4:Comp","getSignalJavaCallAsSignalWithThis()");
		R.insert(compNode, atNode);
		Node returnNode=MOFJavaSyntaxTrans.getSignalJavaCallAsSignalWithThis(R,compNode);
//		R.insert(MOFJavaSyntaxTrans.getSignalJavaCallAsSignalWithThis().getRootNode(),compNode);//--------------f call
		atNode=new AtomicNode("1202#GCCSC5","Line:1202-Line1220");
		R.insert(atNode, returnNode);
		
		atNode1=new AtomicNode("122112#GCCSC6:else:GCCSC2","Line:1221-Line1237");
		R.insert(atNode1, decNode);

        query="Region.allInstances()->exists(r|r.transition->notEmpty())";
		query1="Region.allInstances()->exists(r|r.transition->isEmpty())";
		
		
		decNode1=new DecisionNode("1238#GCCSC7:DecisionNode:C1","isRegionCode");
		cond=new Condition(query);
		decNode1.setcondition(cond);
		decNode1.setFalseCondition(new Condition(query1));
		decNode1.setParentBlock(2);
		R.insert(decNode1, atNode1);
		atNode=new AtomicNode("1239#GCCSC8:then:GCCC7","Line:1239-Line1241");
		R.insert(atNode, decNode);
		compNode=new CompositeNode("1242#GCCSC9:Comp","addContextParamInMethod(methodSign, contextClassName)");
		R.insert(compNode, atNode);
		 returnNode=addContextParamInMethod(R,compNode);
//		R.insert(addContextParamInMethod().getRootNode(),compNode);//--------------f call
		compNode1=new CompositeNode("1242#GCCSC10:Comp","getSignalJavaCallWithThis()");
		R.insert(compNode1, atNode);
		returnNode=MOFJavaSyntaxTrans.getSignalJavaCallWithThis(R,compNode1);
//		R.insert(MOFJavaSyntaxTrans.getSignalJavaCallWithThis().getRootNode(),compNode1);//--------------f call
		atNode2=new AtomicNode("1243#GCCSC11","Line:1243-Line1264");
		R.insert(atNode2, returnNode);
		
		atNode=new AtomicNode("1265#GCCSC12:else:GCCSC7","Line:1265-Line1275");
		R.insert(atNode, decNode);
		compNode=new CompositeNode("1276#GCCSC13:Comp","getSignalJavaCallWithThisNotContext()");
		R.insert(compNode, atNode);
		returnNode=MOFJavaSyntaxTrans.getSignalJavaCallAsSignalWithThisNotContext(R,compNode);
//		R.insert(MOFJavaSyntaxTrans.getSignalJavaCallAsSignalWithThisNotContext().getRootNode(),compNode);//--------------f call
		atNode=new AtomicNode("1277#GCCSC13","Line:1277-Line1291");
		R.insert(atNode, returnNode);
		
		
		atNode1=new AtomicNode("11292#GCCSC14","Line:1292-Line1292");
		R.insert(atNode1, atNode2);
		atNode1.setParentNode(atNode);
		
		
		atNode2=new AtomicNode("1293#GCCSC15","Line:1293-Line1293");
		R.insert(atNode2, atNode);
		atNode2.setParentNode(atNode1);
		return atNode2;
	}
	//conditions 0 branches 1
	public static Node generateRunMethod(CFG R, Node parent){
		AtomicNode atNode1;
//		CFG R =new CFG();
		atNode1=new AtomicNode("1295#GRM1","Line:1296-Line1364");
		R.insert(atNode1,parent);
				
		return atNode1;
	}
	//conditions 0 branches 1
	public static Node generateTimeoutCodeForContextClass(CFG R, Node parent){
		AtomicNode atNode1;
//		CFG R =new CFG();
		atNode1=new AtomicNode("1367#GTCFCC1","Line:1367-Line1405");
		R.insert(atNode1, parent);
				
		return atNode1;
	}
	//conditions 0 branches 1
	public static Node generateTimeoutCodeForRegionContextClass(CFG R, Node parent){
		AtomicNode atNode1;
//		CFG R =new CFG();
		atNode1=new AtomicNode("1367#GTCFRCC1","Line:1406-Line1443");
		R.insert(atNode1, parent);
				
		return atNode1;
	}
	//conditions 1 branches 2
	public static Node generateChangeEventCodeForContextClass(CFG R, Node parent){
		AtomicNode atNode,atNode1,atNode2;
		DecisionNode decNode;
		Condition cond;
//		CFG R =new CFG();
		
		atNode1=new AtomicNode("1447#GCECFCCA1","Line:1447-Line1457");
		R.insert(atNode1, parent);
		String query="Region.allInstances()->size()>1 and StateMachine.allInstances()->exists(st|st.region->size()>1)";
		String  query1="Region.allInstances()->size()>0 and StateMachine.allInstances()->exists(st|st.region->size()=1)";
		
		decNode=new DecisionNode("1458#GCECFCCA2:DecisionNode:C1","isParallel");
		cond=new Condition(query);
		decNode.setcondition(cond);
		decNode.setFalseCondition(new Condition(query1));
		decNode.setParentBlock(0);
		R.insert(decNode, atNode1);
		atNode=new AtomicNode("1459#GCECFCCA3:then:GCECFCCA2","Line:1459-Line1467");
		R.insert(atNode, decNode);
		atNode1=new AtomicNode("1468#GCECFCCA4:else:GCECFCCA2","Line:1468-Line1471");
		R.insert(atNode1, decNode);
		atNode2=new AtomicNode("1472#GCECFCCA5","Line:1472-Line1476");
		R.insert(atNode2, atNode);
		atNode2.setParentNode(atNode1);
				
		return atNode2;
	}
	//conditions 0 branches 1
	public static Node generateChangeEventCodeForRegionContextClass(CFG R, Node parent){
		AtomicNode atNode1;
//		CFG R =new CFG();
		atNode1=new AtomicNode("1479#GCECFRCC1","Line:1478-Line1509");
		R.insert(atNode1, parent);
				
		return atNode1;
	}
	//conditions 1 branches 2
	public static Node generateCompletionEventCodeForContextClass(CFG R, Node parent){
		AtomicNode atNode,atNode1,atNode2;
		DecisionNode decNode;
		Condition cond;
//		CFG R =new CFG();
		
		atNode1=new AtomicNode("1511#GCECFCC1","Line:1511-Line1521");
		R.insert(atNode1, parent);
		
		String query="Region.allInstances()->size()>1 and StateMachine.allInstances()->exists(st|st.region->size()>1)";
		String  query1="Region.allInstances()->size()>0 and StateMachine.allInstances()->exists(st|st.region->size()=1)";
		
		decNode=new DecisionNode("152222#GCECFCC2:DecisionNode:C1","isParallel");
		cond=new Condition(query);
		decNode.setParentBlock(0);
		decNode.setcondition(cond);
		decNode.setFalseCondition(new Condition(query1));
		R.insert(decNode, atNode1);
		atNode=new AtomicNode("152323#GCECFCC3:then:GCECFCC2","Line:1523-Line1525");
		R.insert(atNode, decNode);
		atNode1=new AtomicNode("152626#GCECFCC4:else:GCECFCC2","Line:1526-Line1529");
		R.insert(atNode1, decNode);
		atNode2=new AtomicNode("1530#GCECFCC5","Line:1530-Line1534");
		R.insert(atNode2, atNode);
		atNode2.setParentNode(atNode1);
				
		return atNode2;
	}
	//conditions 0 branches 1
	public static Node generateCompletionEventCodeForRegionContextClass(CFG R, Node parent){
		AtomicNode atNode1;
//		CFG R =new CFG();
		atNode1=new AtomicNode("1536#GCECFRCC1","Line:1536-Line1551");
		R.insert(atNode1, parent);
				
		return atNode1;
	}
	//conditions 0 branches 1
	public static Node generateCompositeCode(CFG R, Node parent){
		AtomicNode atNode1;
//		CFG R =new CFG();
		atNode1=new AtomicNode("1557#GCC1","Line:1557-Line1572");
		R.insert(atNode1, parent);
				
		return atNode1;
	}
	//conditions 0 branches 1
	public static Node generateAddExceptionMethodsForParallelSM(CFG R, Node parent){
		AtomicNode atNode1;
//		CFG R =new CFG();
		atNode1=new AtomicNode("1575#GAEMFPSM1","Line:1575-Line1593");
		R.insert(atNode1, parent);
				
		return atNode1;
	}
	//conditions 0 branches 1
	public static Node generateIsInStateMethodForParallelSM(CFG R, Node parent){
		AtomicNode atNode1;
//		CFG R =new CFG();
		atNode1=new AtomicNode("1596#GIISMFPSM1","Line:1596-Line1630");
		R.insert(atNode1, parent);
				
		return atNode1;
	}
	//conditions 0 branches 1
	public static Node generateIsInStateMethod(CFG R, Node parent){
		AtomicNode atNode1;
//		CFG R =new CFG();
		atNode1=new AtomicNode("1632#GIISM1","Line:1632-Line1727");
		R.insert(atNode1, parent);
				
		return atNode1;
	}
	//conditions 0 branches 1
	public static Node removeRedundantMethod(CFG R, Node parent){
		AtomicNode atNode1;
//		CFG R =new CFG();
		atNode1=new AtomicNode("1729#RDM1","Line:1729-Line1758");
		R.insert(atNode1, parent);
				
		return atNode1;
	}
	//conditions 0 branches 1
	public static Node removeMethodsAlreadyGenerated(CFG R, Node parent){
		AtomicNode atNode1;
//		CFG R =new CFG();
		atNode1=new AtomicNode("1762#RMAG1","Line:1762-Line1778");
		R.insert(atNode1, parent);
				
		return atNode1;
	}
	//conditions 0 branches 1
	public static Node addContextParamInMethod(CFG R, Node parent){
		AtomicNode atNode1;
//		CFG R =new CFG();
		atNode1=new AtomicNode("1782#ACPIM1","Line:1782-Line1806");
		R.insert(atNode1, parent);
				
		return atNode1;
	}
	//conditions 0 branches 1
	public static Node translateOCLExpression(CFG R, Node parent){
		AtomicNode atNode1;
//		CFG R =new CFG();
		atNode1=new AtomicNode("1810#TOCLE1","Line:1810-Line1823");
		R.insert(atNode1, parent);
				
		return atNode1;
	}
	//conditions 0 branches 1
	public static Node generateSetInitStateCodeForChoiceNode(CFG R, Node parent){
		AtomicNode atNode1,atNode2;
		CompositeNode compNode,compNode1;
		
//		CFG R =new CFG();
		
		atNode1=new AtomicNode("1826#GSISCFCN1","Line:1826-Line1839");
		R.insert(atNode1, parent);
		compNode=new CompositeNode("1840#GSISCFCN2:Comp","convertGuardOCLToJavaWithClass(contextClass)");
		R.insert(compNode, atNode1);
		Node returnNode=MOFStateClassHelper.convertGuardOCLToJavaWithClass(R,compNode);
//		R.insert(MOFStateClassHelper.convertGuardOCLToJavaWithClass().getRootNode(),compNode);//--------------f call
		compNode1=new CompositeNode("1841#GSISCFCN3:Comp","translateOCLExpression(ogGuard, varName, oclCP)");
		R.insert(compNode1, returnNode);
		returnNode=translateOCLExpression(R,compNode1);
//		R.insert(translateOCLExpression().getRootNode(),compNode1);//--------------f call
		atNode2=new AtomicNode("1842#GSISCFCN4","Line:1842-Line1899");
		
		R.insert(atNode2, returnNode);
		
		return atNode2;
	}
	
}
