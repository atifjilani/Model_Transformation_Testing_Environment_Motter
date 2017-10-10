package questlab.motter.mofscript.CFG;

import questlab.motter.CFG.AtomicNode;
import questlab.motter.CFG.CFG;
import questlab.motter.CFG.CompositeNode;
import questlab.motter.CFG.Condition;
import questlab.motter.CFG.DecisionNode;
import questlab.motter.CFG.Node;
//conditions 7 branches 26-6=20
public class MOFStateClassesGenerator {
	//conditions 1 branches 2
	public static Node generateStateClasses(CFG R, Node parent){
		AtomicNode atNode,atNode1,atNode2;
		CompositeNode compNode,compNode1;
		DecisionNode decNode;
		Condition cond;
		
		
//		CFG R =new CFG();
		atNode1=new AtomicNode("45#GSC1","Line:45-Line61");
		R.insert(atNode1, parent);
		 String query="Region.allInstances()->size()>1 and StateMachine.allInstances()->exists(st|st.region->size()>1)";
		 String query1="Region.allInstances()->size()>0 and StateMachine.allInstances()->exists(st|st.region->size()=1)";
		
		decNode=new DecisionNode("62#GSC2:DecisionNode:C1","self.region.size() > 1");
		cond=new Condition(query);
		decNode.setcondition(cond);
		decNode.setParentBlock(0);
		decNode.setFalseCondition(new Condition(query1));
		R.insert(decNode, atNode1);
		atNode1=new AtomicNode("63#GSC3:then:GSC2","Line:63-Line67");
		R.insert(atNode1, decNode);
		compNode=new CompositeNode("68#GSC4:Comp"," rg.generateStateCodeForRegion(.....)");
		R.insert(compNode, atNode1);
		Node returnNode=generateStateCodeForRegion(R,compNode);
//		R.insert(generateStateCodeForRegion().getRootNode(),compNode);//--------------f call
		atNode1=new AtomicNode("69#GSC5","Line:69-Line116");
		R.insert(atNode1, returnNode);
		
		atNode2=new AtomicNode("117117#GSC6:else:GSC2","Line:118-Line122");
		R.insert(atNode1, decNode);
		compNode=new CompositeNode("123#GSC7:Comp"," getSignatureOfAllSignals()");
		R.insert(compNode, atNode2);
		returnNode=MOFJavaSyntaxTrans.getSignatureOfAllSignals(R,compNode);
//		R.insert(MOFJavaSyntaxTrans.getSignatureOfAllSignals().getRootNode(),compNode);//--------------f call
		atNode2=new AtomicNode("124#GSC8","Line:124-Line158");
		R.insert(atNode1, returnNode);
		
		atNode=new AtomicNode("159#GSC9","Line:159-Line160");
		R.insert(atNode, atNode1);
		atNode.setParentNode(atNode2);
		
		compNode=new CompositeNode("162#GSC10:Comp"," generateStateAbstractClass");
		R.insert(compNode, atNode);
		returnNode=MOFAbstractStateClassGenerator.generateStateAbstractClass(R,compNode);
//		R.insert(MOFAbstractStateClassGenerator.generateStateAbstractClass().getRootNode(),compNode);//--------------f call
		compNode1=new CompositeNode("165#GSC10:Comp"," r.generateClassesForStates");
		R.insert(compNode1, returnNode);
		returnNode=generateClassesForStates(R,compNode);
//		R.insert(generateClassesForStates().getRootNode(),compNode);//--------------f call
		
		atNode=new AtomicNode("159#GSC11","Line:168-Line194");
		R.insert(atNode, returnNode);
		
		return atNode;
	}
	//conditions 1 branches 2
	public static Node generateClassesForStates(CFG R, Node parent){
		AtomicNode atNode,atNode1,atNode2;
		CompositeNode compNode,compNode1;
		DecisionNode decNode;
		Condition cond;
		
		
//		CFG R =new CFG();
		atNode1=new AtomicNode("198#GCFS1","Line:197-Line208");
		R.insert(atNode1, parent);
		compNode=new CompositeNode("209#GCFS2:Comp"," s.populateTimeEvents()");
		R.insert(compNode, atNode1);
		Node returnNode=populateTimeEvents(R,compNode);
//		R.insert(populateTimeEvents().getRootNode(),compNode);//--------------f call
		compNode1=new CompositeNode("210#GCFS3:Comp","s.populateSignalEvents()");
		R.insert(compNode1, returnNode);
		returnNode=populateSignalEvents(R,compNode1);
//		R.insert(populateSignalEvents().getRootNode(),compNode1);//--------------f call
		compNode=new CompositeNode("211#GCFS4:Comp"," s.populateChangeEvents()");
		R.insert(compNode, returnNode);
		returnNode=populateChangeEvents(R,compNode);
//		R.insert(populateChangeEvents().getRootNode(),compNode);//--------------f call
		
		String query="State.allInstances()->exists(st|st.isComposite=true)";
		String query1="State.allInstances()->exists(st|st.isComposite=false)";

		decNode=new DecisionNode("214214#GCFS5:DecisionNode:C1","s.isComposite == true");
		cond=new Condition(query);
		decNode.setcondition(cond);
		decNode.setParentBlock(0);
		decNode.setFalseCondition(new Condition(query1));
		R.insert(decNode, returnNode);
		atNode1=new AtomicNode("215215#GCFS6:then:GCFS5","Line:215-Line216");
		R.insert(atNode1, decNode);
		compNode=new CompositeNode("217#GCFS7:Comp"," s.processCompositeState");
		R.insert(compNode, atNode1);
		Node returnNode1=processCompositeState(R,compNode);
//		R.insert(processCompositeState().getRootNode(),compNode);//--------------f call
				
		atNode2=new AtomicNode("231231#GCFS8:else:GCFS5","Line:231-Line232");
		R.insert(atNode1, decNode);
		compNode1=new CompositeNode("233#GCFS9:Comp"," s.createClassForState");
		R.insert(compNode1, atNode2);
		Node returnNode2=createClassForState(R,compNode);
//		R.insert(createClassForState().getRootNode(),compNode);//--------------f call
		
		
		atNode=new AtomicNode("237#GSC9","Line:237-Line252");
		R.insert(atNode, returnNode1);
		atNode.setParentNode(returnNode2);
		
		
		
		return atNode;
	}
	//conditions 1 branches 2
	public static Node processCompositeState(CFG R, Node parent){
		AtomicNode atNode,atNode1,atNode2;
		CompositeNode compNode,compNode1;
		DecisionNode decNode;
		Condition cond;
		
		
//		CFG R =new CFG();
		atNode1=new AtomicNode("254#PCS1","Line:258-Line261");
		R.insert(atNode1, parent);
		String query="Region.allInstances()->size()>1 and StateMachine.allInstances()->exists(st|st.region->size()>1)";
		String query1="Region.allInstances()->size()>0 and StateMachine.allInstances()->exists(st|st.region->size()=1)";
		
		decNode=new DecisionNode("262262#PCS2:DecisionNode:C1","self.region.size() > 1");
		cond=new Condition(query);
		decNode.setcondition(cond);
		decNode.setParentBlock(0);
		decNode.setFalseCondition(new Condition(query1));
		R.insert(decNode, atNode1);
		atNode1=new AtomicNode("263362#PCS3:then:PCS2","Line:263-Line266");
		R.insert(atNode1, decNode);
		compNode=new CompositeNode("267#PCS4:Comp"," self.generateCompositeStateClassWithParallelRegions");
		R.insert(compNode, atNode1);
//		Node returnNode=generateCompositeStateClassWithParallelRegions(R,compNode);---------break recursion
		//R.insert(generateCompositeStateClassWithParallelRegions().getRootNode(),compNode);//--------------f call
		atNode1=new AtomicNode("272#PCS5","Line:272-Line288");
		R.insert(atNode1, compNode);
		
		atNode2=new AtomicNode("289982#PCS6:else:PCS2","Line:289-Line308");
		R.insert(atNode1, decNode);
		compNode=new CompositeNode("309#PCS7:Comp"," self.createCompositeStateClass");
		R.insert(compNode, atNode2);
		Node returnNode=createCompositeStateClass(R,compNode);
//		R.insert(createCompositeStateClass().getRootNode(),compNode);//--------------f call
		atNode2=new AtomicNode("310#PCS8","Line:124-Line318");
		R.insert(atNode1, returnNode);
		compNode=new CompositeNode("319#PCS9:Comp"," rg.getSignatureOfAllSignals()");
		R.insert(compNode, atNode1);
		returnNode=MOFJavaSyntaxTrans.getSignatureOfAllSignals(R,compNode);
//		R.insert(MOFJavaSyntaxTrans.getSignatureOfAllSignals().getRootNode(),compNode);//--------------f call
		compNode1=new CompositeNode("328#PCS10:Comp"," rg.generateClassesForStates()");
		R.insert(compNode1, returnNode);
//		returnNode=generateClassesForStates(R,compNode1); comment to avoid non breakable recursion
//		R.insert(generateClassesForStates().getRootNode(),compNode1);//--------------f call
				
		atNode=new AtomicNode("351#PCS11","Line:351-Line355");
		R.insert(atNode, atNode1);
		atNode.setParentNode(compNode1);
		
		return atNode;
	}
	//conditions 2 branches 5           2+1=3  5-2=3
	public static Node createClassForState(CFG R, Node parent){
		AtomicNode atNode,atNode1;
		CompositeNode compNode,compNode1;
		DecisionNode decNode;
		Condition cond;
		
		
//		CFG R =new CFG();
		atNode1=new AtomicNode("358#CCS1","Line:358-Line387");
		R.insert(atNode1, parent);
		compNode=new CompositeNode("388#CCS2:Comp"," self.generateTimeServiceCode(htNDChoices)");
		R.insert(compNode, atNode1);
		Node returnNode=generateTimeServiceCode(R,compNode);
//		R.insert(generateTimeServiceCode().getRootNode(),compNode);//--------------f call
		
		compNode1=new CompositeNode("393#CCS3:Comp"," self.generateConstructor()");
		R.insert(compNode1, returnNode);
		returnNode=MOFStateClassHelper.generateConstructor(R,compNode1);
//		R.insert(MOFStateClassHelper.generateConstructor().getRootNode(),compNode1);//--------------f call
		String query="Transition.allInstances()->exists(t|t.target.oclIsKindOf(State) and t.stereotypeTimeProbability>0)";  
		String query1="Transition.allInstances()->exists(t|t.target.oclIsKindOf(State) and t.stereotypeTimeProbability<=0)";
				
		decNode=new DecisionNode("395395#CCS4:DecisionNode:C1","timeEventsExist");
		cond=new Condition(query);
		decNode.setcondition(cond);
		decNode.setParentBlock(0);
		decNode.setFalseCondition(new Condition(query1));
		R.insert(decNode, returnNode);
		compNode=new CompositeNode("396396#CCS5:then:CCS4"," self.generateTimeoutMethods()");
		R.insert(compNode, decNode);
		returnNode=MOFStateClassHelper.generateTimeoutMethods(R,compNode);
//		R.insert(MOFStateClassHelper.generateTimeoutMethods().getRootNode(),compNode);//--------------f call

				
		atNode=new AtomicNode("398#CCS6","Line:398-Line398");
		R.insert(atNode, returnNode);
		atNode.setParentNode(decNode);
		
		compNode=new CompositeNode("399#CCS7:Comp"," self.generateSignalEvents");
		R.insert(compNode, atNode);
		returnNode=generateSignalEvents(R,compNode);
//		R.insert(generateSignalEvents().getRootNode(),compNode);//--------------f call
		
		compNode1=new CompositeNode("401#CCS8:Comp"," self.generateChangeEvents");
		R.insert(compNode1, returnNode);
		returnNode=MOFStateClassHelper.generateChangeEvents(R,compNode1);
//		R.insert(MOFStateClassHelper.generateChangeEvents().getRootNode(),compNode1);//--------------f call
		
		compNode=new CompositeNode("402#CCS9:Comp"," self.generateCompletionEventMethod");
		R.insert(compNode, returnNode);
		returnNode=MOFStateClassHelper.generateCompletionEventMethod(R,compNode);
//		R.insert(MOFStateClassHelper.generateCompletionEventMethod().getRootNode(),compNode);//--------------f call
		
		compNode1=new CompositeNode("403#CCS10:Comp"," self.generateStateEntryExitMethods");
		R.insert(compNode1, returnNode);
		returnNode=MOFStateClassHelper.generateStateEntryExitMethods(R,compNode1);
//		R.insert(MOFStateClassHelper.generateStateEntryExitMethods().getRootNode(),compNode1);//--------------f call
		
		compNode=new CompositeNode("406#CCS11:Comp"," self.generateStateNameGetter");
		R.insert(compNode, returnNode);
		returnNode=MOFStateClassHelper.generateStateNameGetter(R,compNode);
//		R.insert(MOFStateClassHelper.generateStateNameGetter().getRootNode(),compNode);//--------------f call
		
		compNode1=new CompositeNode("407#CCS12:Comp"," self.generateStopExecutionMethod(isParallel, timeEventsExist)");
		R.insert(compNode1, returnNode);
		returnNode=MOFStateClassHelper.generateStopExecutionMethod(R,compNode);
//		R.insert(MOFStateClassHelper.generateStopExecutionMethod().getRootNode(),compNode);//--------------f call
		 query="State.allInstances()->exists (s| s.stereotypeError>0)";  
		 query1="State.allInstances()->exists (s|s.stereotypeError<=0)";
		
		decNode=new DecisionNode("408804#CCS13:DecisionNode:C2","isErrorState1");
		cond=new Condition(query);
		decNode.setcondition(cond);
		decNode.setParentBlock(0);
		decNode.setFalseCondition(new Condition(query1));
		R.insert(decNode, returnNode);
		compNode=new CompositeNode("409904#CCS14:then:CCS13"," self.generateGetErrorIdMethod(");
		R.insert(compNode, decNode);
		returnNode=MOFStateClassHelper.generateGetErrorIdMethod(R,compNode);
//		R.insert(MOFStateClassHelper.generateGetErrorIdMethod().getRootNode(),compNode);//--------------f call

				
		atNode=new AtomicNode("412#CCS15","Line:412-Line415");
		R.insert(atNode, returnNode);
		atNode.setParentNode(decNode);
		
		return atNode;
	}
	//conditions 0 branches 1
	public static Node createCompositeStateClass(CFG R, Node parent){
		AtomicNode atNode1;
		CompositeNode compNode,compNode1;
	
		
		
//		CFG R =new CFG();
		atNode1=new AtomicNode("419#CCSC1","Line:419-Line427");
		R.insert(atNode1, parent);
		compNode=new CompositeNode("428#CCSC2:Comp"," self.generateClassHeader(packageName)");
		R.insert(compNode, atNode1);
		Node returnNode=MOFStateClassHelper.generateClassHeader(R, compNode);
//		R.insert(MOFStateClassHelper.generateClassHeader().getRootNode(),compNode);//--------------f call
		atNode1=new AtomicNode("429#CCSC3","Line:429-Line629");
		R.insert(atNode1, returnNode);
		
		compNode=new CompositeNode("630#CCSC4:Comp"," tr.event.getSignalEventJavaSyntax()");
		R.insert(compNode, atNode1);
		returnNode=MOFJavaSyntaxTrans.getSignalEventJavaSyntax(R,compNode);
//		R.insert(MOFJavaSyntaxTrans.getSignalEventJavaSyntax().getRootNode(),compNode);//--------------f call
		
		compNode1=new CompositeNode("640#CCSC5:Comp"," s.getSignalJavaSignatureStringContextParam(contextClassName)");
		R.insert(compNode1, returnNode);
		returnNode=MOFJavaSyntaxTrans.getSignalJavaSignatureStringContextParam(R,compNode1);
//		R.insert(MOFJavaSyntaxTrans.getSignalJavaSignatureStringContextParam().getRootNode(),compNode1);//--------------f call
		
		compNode=new CompositeNode("673#CCSC6:Comp"," self.generateSignalEvents()");
		R.insert(compNode, returnNode);
		returnNode=generateSignalEvents(R,compNode);
//		R.insert(generateSignalEvents().getRootNode(),compNode);//--------------f call
		
		compNode1=new CompositeNode("676#CCSC7:Comp"," self.generateCompletionEventMethod()");
		R.insert(compNode1, returnNode);
		returnNode=MOFStateClassHelper.generateCompletionEventMethod(R,compNode1);
//		R.insert(MOFStateClassHelper.generateCompletionEventMethod().getRootNode(),compNode1);//--------------f call
		
		compNode=new CompositeNode("755#CCSC8:Comp"," entry.convertActionToJavaCallAsString(actionClass, contextClass)");
		R.insert(compNode, returnNode);
		returnNode=MOFJavaSyntaxTrans.convertActionToJavaCallAsString(R,compNode);
//		R.insert(MOFJavaSyntaxTrans.convertActionToJavaCallAsString().getRootNode(),compNode);//--------------f call
		
		compNode1=new CompositeNode("821#CCSC9:Comp"," exit.convertActionToJavaCallAsString(actionClass)");
		R.insert(compNode1, returnNode);
		returnNode=MOFJavaSyntaxTrans.convertActionToJavaCallAsString(R,compNode1);
//		R.insert(MOFJavaSyntaxTrans.convertActionToJavaCallAsString().getRootNode(),compNode1);//--------------f call
		return returnNode;
	}
	//conditions 2 branches 5           2+1=3  5-2=3
	public static Node createCompositeClassHavingParallelRegion(CFG R, Node parent){
		AtomicNode atNode,atNode1;
		CompositeNode compNode;
		DecisionNode decNode;
		Condition cond;
		
		
//		CFG R =new CFG();
		atNode1=new AtomicNode("937#CCCHPR1","Line:937-Line947");
		R.insert(atNode1, parent);
		compNode=new CompositeNode("949#CCCHPR2:Comp"," self.generateClassHeader(packageName)");
		R.insert(compNode, atNode1);
		Node returnNode=MOFStateClassHelper.generateClassHeader(R,compNode);
//		R.insert(MOFStateClassHelper.generateClassHeader().getRootNode(),compNode);//--------------f call
		atNode1=new AtomicNode("950#CCCHPR3","Line:950-Line964");
		R.insert(atNode1, returnNode);
		
		compNode=new CompositeNode("965#CCCHPR4:Comp"," self.generateTimeServiceCode(htNDChoices)");
		R.insert(compNode, atNode1);
		returnNode=generateTimeServiceCode(R,compNode);
//		R.insert(generateTimeServiceCode().getRootNode(),compNode);//--------------f call
		
		atNode1=new AtomicNode("966#CCCHPR5","Line:966-Line1010");
		R.insert(atNode1, returnNode);
		String query="Transition.allInstances()->exists(t|t.target.oclIsKindOf(State) and t.stereotypeTimeProbability>0 )";  
		String query1="Transition.allInstances()->exists(t|t.target.oclIsKindOf(State) and t.stereotypeTimeProbability<=0)";
			
		decNode=new DecisionNode("10111101#CCCHPR6:DecisionNode:C2","timeEventsExist");
		cond=new Condition(query);
		decNode.setcondition(cond);
		decNode.setParentBlock(0);
		decNode.setFalseCondition(new Condition(query1));
		R.insert(decNode, atNode1);
		compNode=new CompositeNode("10122101#CCCHPR7:then:CCCHPR6","  self.generateTimeoutMethods()");
		R.insert(compNode, decNode);
		returnNode=MOFStateClassHelper.generateTimeoutMethods(R,compNode);
//		R.insert(MOFStateClassHelper.generateTimeoutMethods().getRootNode(),compNode);//--------------f call

				
		atNode=new AtomicNode("1014#CCCHPR8","Line:1014-Line1045");
		R.insert(atNode, returnNode);
		atNode.setParentNode(decNode);
		
		compNode=new CompositeNode("1046#CCCHPR9","  self.generateSignalEventsLocal()");
		R.insert(compNode, atNode);
		returnNode=MOFStateClassHelper.generateSignalEventLocal(R,compNode);
//		R.insert(MOFStateClassHelper.generateSignalEventLocal().getRootNode(),compNode);//--------------f call
		
		atNode=new AtomicNode("1048#CCCHPR10","Line:1048-Line1078");
		R.insert(atNode, returnNode);
		compNode=new CompositeNode("1079#CCCHPR11","val1.getSignalJavaCallAsSignalWithThis()");
		R.insert(compNode, atNode);
		returnNode=MOFJavaSyntaxTrans.getSignalJavaCallAsSignalWithThis(R,compNode);
//		R.insert(MOFJavaSyntaxTrans.getSignalJavaCallAsSignalWithThis().getRootNode(),compNode);//--------------f call
		atNode=new AtomicNode("1080#CCCHPR12","Line:1080-Line1221");
		R.insert(atNode, returnNode);
		query="Transition.allInstances()->exists(t|t.target.oclIsKindOf(State) and t.stereotypeTimeProbability>0)";  
		query1="Transition.allInstances()->exists(t|t.target.oclIsKindOf(State) and t.stereotypeTimeProbability<=0)";
			
		decNode=new DecisionNode("1222#CCCHPR13:DecisionNode:C2","timeEventsExist");
		cond=new Condition(query);
		decNode.setcondition(cond);
		decNode.setParentBlock(0);
		decNode.setFalseCondition(new Condition(query1));
		R.insert(decNode, compNode);
		compNode=new CompositeNode("1223#CCCHPR14:then:CCCHPR13"," self.generateTimeRelatedStateEntryCode()");
		R.insert(compNode, decNode);
		returnNode=MOFStateClassHelper.generateTimeRelatedStateEntryCode(R,compNode);
//		R.insert(MOFStateClassHelper.generateTimeRelatedStateEntryCode().getRootNode(),compNode);//--------------f call

				
		atNode=new AtomicNode("1226#CCCHPR15","Line:1226-Line1385");
		R.insert(atNode, returnNode);
		atNode.setParentNode(decNode);
		return atNode;
	}
	//conditions 0 branches 1
	public static Node generateCompositeStateClassWithParallelRegions(CFG R, Node parent){
		AtomicNode atNode1;
		CompositeNode compNode;

		
		
//		CFG R =new CFG();
		atNode1=new AtomicNode("1388#GCCWPR1","Line:1388-Line1420");
		R.insert(atNode1, parent);
		compNode=new CompositeNode("1421#GCCWPR2:Comp"," rg.generateStateCodeForRegion()");
		R.insert(compNode, atNode1);
		Node returnNode=generateStateCodeForRegion(R,compNode);
//		R.insert(generateStateCodeForRegion().getRootNode(),compNode);//--------------f call
		atNode1=new AtomicNode("1426#GCCWPR3","Line:1426-Line1477");
		R.insert(atNode1, returnNode);
		
		compNode=new CompositeNode("1478#GCCWPR4:Comp"," contextClass.generateContextRegionClass()");
		R.insert(compNode, atNode1);
		returnNode=MOFContextClassHelper.generateContextRegionClass(R,compNode);
//		R.insert(MOFContextClassHelper.generateContextRegionClass().getRootNode(),compNode);//--------------f call
		
		atNode1=new AtomicNode("1480#GCCWPR5","Line:1480-Line1499");
		R.insert(atNode1, returnNode);
		
		compNode=new CompositeNode("1500#GCCWPR6:Comp"," self.createCompositeClassHavingParallelRegion()");
		R.insert(compNode, atNode1);
		returnNode=createCompositeClassHavingParallelRegion(R,compNode);
//		R.insert(createCompositeClassHavingParallelRegion().getRootNode(),compNode);//--------------f call
		
		atNode1=new AtomicNode("1503#GCCWPR7","Line:1503-Line1508");
		R.insert(atNode1, returnNode);
		
				
		return atNode1;
	}
	//conditions 0 branches 1
	public static Node populateSignalEvents(CFG R, Node parent){
		AtomicNode atNode1;
	
		
		
//		CFG R =new CFG();
		atNode1=new AtomicNode("1511#PSE1","Line:1511-Line1542");
		R.insert(atNode1, parent);
		
		return atNode1;
	}
	//conditions 0 branches 1
	public static  Node populateCallEvents(CFG R, Node parent){
		AtomicNode atNode1;
	
		
//		CFG R =new CFG();
		atNode1=new AtomicNode("1511#PCE1","Line:1545-Line1566");
		R.insert(atNode1, parent);
		
		return atNode1;
	}
	//conditions 0 branches 1
	public static Node populateChangeEvents(CFG R, Node parent){
		AtomicNode atNode1;
		
		
//		CFG R =new CFG();
		atNode1=new AtomicNode("1511#PCE1","Line:1573-Line1594");
		R.insert(atNode1, parent);
		
		return atNode1;
	}
	//conditions 0 branches 1
	public static Node populateTimeEvents(CFG R, Node parent){
		AtomicNode atNode1;
		
		
//		CFG R =new CFG();
		atNode1=new AtomicNode("1603#PTE1","Line:1603-Line1625");
		R.insert(atNode1, parent);
		
		return atNode1;
	}
	//conditions 0 branches 1
	public static Node ClearLists(CFG R, Node parent){
		AtomicNode atNode1;
		
		
		
//		CFG R =new CFG();
		atNode1=new AtomicNode("1627#CL1","Line:1627-Line1639");
		R.insert(atNode1, parent);
		
		return atNode1;
	}
	//conditions 0 branches 1
	public static Node generateTimeServiceCode(CFG R, Node parent){
		AtomicNode atNode1;
		
//		CFG R =new CFG();
		atNode1=new AtomicNode("1627#GTSC1","Line:1643-Line1701");
		R.insert(atNode1, parent);
		
		return atNode1;
	}
	//conditions 0 branches 1
	public static Node generateStateCodeForRegion(CFG R, Node parent){
		AtomicNode atNode1,atNode2;
		CompositeNode compNode;
		
		
//		CFG R =new CFG();
		atNode1=new AtomicNode("1703#GTSCFR1","Line:1703-Line1728");
		R.insert(atNode1, parent);
		compNode=new CompositeNode("1729#GTSCFR2:Comp"," self.generateStateAbstractClass()");
		R.insert(compNode, atNode1);
		Node returnNode=MOFAbstractStateClassGenerator.generateStateAbstractClass(R,compNode);
//		R.insert(MOFAbstractStateClassGenerator.generateStateAbstractClass().getRootNode(),compNode);//--------------f call
		
		atNode1=new AtomicNode("1730#GTSCFR3","Line:1730-Line1732");
		R.insert(atNode1, returnNode);
		
		compNode=new CompositeNode("1733#GCCWPR6:Comp"," self.generateClassesForStates()");
		R.insert(compNode, atNode1);
		returnNode=generateClassesForStates(R,compNode);
//		R.insert(generateClassesForStates().getRootNode(),compNode);//--------------f call
		
		atNode2=new AtomicNode("1736#GCCWPR7","Line:1736-Line1782");
		R.insert(atNode2, returnNode);
		
		
		return atNode2;
	}
	//conditions 0 branches 1
	public static Node generateSignalEvents(CFG R, Node parent){
		AtomicNode atNode1;
		CompositeNode compNode;
		
//		CFG R =new CFG();
		atNode1=new AtomicNode("1788#GSE1","Line:1788-Line1788");
		R.insert(atNode1, parent);
		compNode=new CompositeNode("1791#GSE2:Comp"," self.generateSignalEventsLocal()");
		R.insert(compNode, atNode1);
		Node returnNode=MOFStateClassHelper.generateSignalEventLocal(R,compNode);
//		R.insert(MOFStateClassHelper.generateSignalEventLocal().getRootNode(),compNode);//--------------f call
		
		return returnNode;
	}
}
