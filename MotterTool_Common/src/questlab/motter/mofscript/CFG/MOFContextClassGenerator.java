package questlab.motter.mofscript.CFG;

import questlab.motter.CFG.AtomicNode;
import questlab.motter.CFG.CFG;
import questlab.motter.CFG.CompositeNode;
import questlab.motter.CFG.Condition;
import questlab.motter.CFG.DecisionNode;
import questlab.motter.CFG.Node;
//conditions 8 branches 11
public class MOFContextClassGenerator {
	//conditions 4 branches 9          9-4=  5
	public static Node generateStatefulClassCode(CFG R, Node parent){
		AtomicNode atNode,atNode1,atNode2,atNode3,atNode4,atNode5;
		CompositeNode compNode;
		DecisionNode decNode,decNode1;
		Condition cond;
//		CFG R =new CFG();
		atNode1=new AtomicNode("8#GSFCC1","Line:13-Line30");
		R.insert(atNode1, parent);
		compNode=new CompositeNode("31#GSFCC2:Comp","stateMach.generateStateClasses(htTimeEventIds, maxNDChoiceCount, htTimeProbabilities, htNDChoices," 
		+"htNDPsuedoChoices, actionClass, stateFilePath, pkgName, oclToJavaClassPath, htClassSpecificErrorIds, maxErrorStateCount,"
		+"htErrorStateCountPerClass)");
		R.insert(compNode, atNode1);
		Node returnNode=MOFStateClassesGenerator.generateStateClasses(R,compNode);
//		R.insert(MOFStateClassesGenerator.generateStateClasses().getRootNode(),compNode);//--------------f call
		atNode2=new AtomicNode("35#GSFCC3","Line:35-Line60");
		R.insert(atNode2, returnNode);
		String query="StateMachine.allInstances()->exists(sm|sm.region->size()>1)";
		String query1="StateMachine.allInstances()->exists(sm|sm.region->size()=1)";
		decNode=new DecisionNode("61#GSFCC3:DecisionNode:C1","stateMach.region.size() > 1");
		cond=new Condition(query);
		decNode.setcondition(cond);
		decNode.setFalseCondition(new Condition(query1));
		decNode.setParentBlock(0);
		R.insert(decNode, atNode2);
		atNode1=new AtomicNode("626262#GSFCC4:then:GSFCC3","Line:62-Line88");
		R.insert(atNode1, decNode);
		compNode=new CompositeNode("89#GSFCC5:Comp","self.generateContextRegionClass(regionStateList, tempInitStates, tempStateObj, signalsByRegion," 
		+"rg.name+StateContext, self.name, classFilePath, rg.name, pkgName, actionClass)");
		R.insert(compNode, atNode1);
		returnNode=MOFContextClassHelper.generateContextRegionClass(R,compNode);
//		R.insert(MOFContextClassHelper.generateContextRegionClass().getRootNode(),compNode);//--------------f call
		atNode5=new AtomicNode("91#GSFCC6","Line:91-Line101");
		R.insert(atNode5, returnNode);
		atNode1=new AtomicNode("102#GSFCC7:else:GSFCC3","Line:102-Line118");
		R.insert(atNode1, decNode);
		query="Transition.allInstances()->exists(t|t.target.oclIsTypeOf(Pseudostate))";
		query1="Transition.allInstances()->exists(t|t.target.oclIsTypeOf(FinalState) or t.target.oclIsTypeOf(State))";

		decNode1=new DecisionNode("119#GSFCC8:DecisionNode:C2","targetState.oclIsTypeOf(uml.Pseudostate)");
		cond=new Condition(query);
		decNode1.setcondition(cond);
		decNode1.setFalseCondition(new Condition(query1));
		decNode1.setParentBlock(2);
		R.insert(decNode1, atNode1);
		atNode4=new AtomicNode("121#GSFCC9:then:GSFCC8","Line:120-Line125");
		R.insert(atNode4, decNode1);
		
		atNode=new AtomicNode("127127#GSFCC10:else:GSFCC8","Line:127-Line127");
		R.insert(atNode, decNode1);
		query="Transition.allInstances()->exists(t|t.target.oclIsTypeOf(State)) and State.allInstances()->exists(st|st.isComposite=true)";
		query1="Transition.allInstances()->exists(t|t.target.oclIsTypeOf(State)) and State.allInstances()->exists(st|st.isComposite=false)";

		decNode=new DecisionNode("128#GSFCC11:DecisionNode:C3","targetState.isComposite");
		cond=new Condition(query);
		decNode.setcondition(cond);
		decNode.setFalseCondition(new Condition(query1));
		decNode.setParentBlock(2);
		R.insert(decNode, atNode);
		atNode1=new AtomicNode("129#GSFCC12:then:GSFCC11","Line:129-Line135");
		R.insert(atNode1, decNode);
		
		atNode=new AtomicNode("136#GSFCC13:else:GSFCC11","Line:136-Line136");
		R.insert(atNode, decNode);
		query="Transition.allInstances()->exists(t|t.target.oclIsTypeOf(State)) and State.allInstances()->exists(st|st.isOrthogonal=true)";
		query1="Transition.allInstances()->exists(t|t.target.oclIsTypeOf(State)) and State.allInstances()->exists(st|st.isOrthogonal=false)";
		
		decNode=new DecisionNode("137137#GSFCC14:DecisionNode:C4","targetState.isOrthogonal");
		cond=new Condition(query);
		decNode.setcondition(cond);
		decNode.setFalseCondition(new Condition(query1));
		decNode.setParentBlock(2);
		R.insert(decNode, atNode);
		atNode3=new AtomicNode("138#GSFCC15:then:GSFCC14","Line:138-Line139");
		R.insert(atNode3, decNode);
		
		atNode=new AtomicNode("140#GSFCC16:else:GSFCC14","Line:140-Line142");
		R.insert(atNode, decNode);
				
		atNode2=new AtomicNode("142#GSFCC17","Line:142-Line142");
		R.insert(atNode2, atNode3);
		atNode2.setParentNode(atNode);
				
		atNode=new AtomicNode("143#GSFCC18","Line:143-Line143");
		R.insert(atNode, atNode1);
		atNode2.setParentNode(atNode2);
				
		atNode2=new AtomicNode("144#GSFCC19","Line:144-Line144");
		R.insert(atNode2, atNode4);
		atNode2.setParentNode(atNode);
		
		
		
		compNode=new CompositeNode("148#GSFCC20:Comp","self.generateContextClass(immediateStateList, stateList, initStates, initPsuedoStates, stateObjectNames, signalsByRegion, compositeStateCode,"
	 +"sParallelSM, classFilePath, errorStatesCount, actionClass, callEventActions, htNDChoices, pkgName, htNDClassScope,"
	 +"maxNDChoiceCount, oclToJavaClassPath)");
				R.insert(compNode, atNode2);
				compNode.setParentNode(atNode5);
				 returnNode=generateContextClass(R, compNode);
				//R.insert(generateContextClass().getRootNode(),compNode);//--------------f call
				
		
		return returnNode;
	}
	//conditions 4 branches 9         9-4= 5
	public static Node generateContextClass(CFG R, Node parent){
		AtomicNode atNode,atNode1,atNode2;
		CompositeNode compNode,compNode1,compNode2;
		DecisionNode decNode;
		Condition cond;
//		CFG R =new CFG();
		
		atNode1=new AtomicNode("162#GCC1","Line:162-Line168");
		R.insert(atNode1, parent);
		compNode=new CompositeNode("169#GCC2:Comp","self.generateHeaderForContextClass(self.name.replace(\" \", \"\"), pkgName)");
		R.insert(compNode, atNode1);
		Node returnNode=MOFContextClassHelper.generateHeaderForContextClass(R,compNode);
//		R.insert(MOFContextClassHelper.generateHeaderForContextClass().getRootNode(),compNode);//--------------f call
		atNode2=new AtomicNode("170#GCC3","Line:170-Line176");
		R.insert(atNode2, returnNode);
		String query="Class.allInstances()->exists(c|c.ownedOperation->notEmpty())";
		String query1="Class.allInstances()->exists(c|c.ownedOperation->isEmpty())";
		decNode=new DecisionNode("17717#GCC4:DecisionNode:C1","constructor != null != null");
		cond=new Condition(query);
		decNode.setcondition(cond);
		decNode.setFalseCondition(new Condition(query1));
		decNode.setParentBlock(0);
		R.insert(decNode, atNode2);
		atNode1=new AtomicNode("17817#GCC5:then:GCC4","Line:178-Line181");
		R.insert(atNode1, decNode);
		atNode2=new AtomicNode("18318#GCC6:else:GCC4","Line:183-Line189");
		R.insert(atNode2, decNode);
		atNode=new AtomicNode("190#GCC7","Line:190-Line196");
		R.insert(atNode, atNode1);
		atNode.setParentNode(atNode2);
		 query="Transition.allInstances()->exists(t|t.effect.oclIsTypeOf(OpaqueBehavior))";
		 query1="Transition.allInstances()->exists(t|t.effect.oclIsUndefined())";
		
		decNode=new DecisionNode("197#GCC8:DecisionNode:C2","transition.effect.behavior:uml.OpaqueBehavior != null");
		cond=new Condition(query);
		decNode.setcondition(cond);
		decNode.setFalseCondition(new Condition(query1));
		decNode.setParentBlock(0);
		R.insert(decNode, atNode);
		compNode=new CompositeNode("199#GCC9:then:GCC8","self.generateCCConstructor(immediateStateList, stateList, stateObjectsName, initStates, self.name.replace(\" \", \"\"), isParallelSM, actionCode," 
	    +"errorStatesCount, actionClass, htNDClassScope, maxNDChoiceCount, oclToJavaClassPath)");
		R.insert(compNode, decNode);
		returnNode=MOFContextClassHelper.generateCCConstructor(R,compNode);
//		R.insert(MOFContextClassHelper.generateCCConstructor().getRootNode(),compNode);//--------------f call
		atNode=new AtomicNode("202#GCC10","Line:202-Line208");
		R.insert(atNode, returnNode);
		atNode.setParentNode(decNode);
		
		compNode=new CompositeNode("209#GCC11:Comp","self.generateStateObjectDefinitionCode(stateObjectsName, isParallelSM)");
		R.insert(compNode, atNode);
		returnNode=MOFContextClassHelper.generateStateObjectDefinitionCode(R,compNode);
//		R.insert(MOFContextClassHelper.generateStateObjectDefinitionCode().getRootNode(),compNode);//--------------f call
		 query="Region.allInstances()->size()>1 and StateMachine.allInstances()->exists(st|st.region->size()>1)";
		 query1="Region.allInstances()->size()>0 and StateMachine.allInstances()->exists(st|st.region->size()=1)";
		
		decNode=new DecisionNode("215512#GCC12:DecisionNode:C3","isParallelSM == true");
		cond=new Condition(query);
		decNode.setcondition(cond);
		decNode.setParentBlock(1);
		decNode.setFalseCondition(new Condition(query1));
		R.insert(decNode, returnNode);
		compNode=new CompositeNode("217712#GCC13:then:GCC12","self.generateStateClassesRelatedCodeForParallelSM(stateList,stateObjectsName)");
		R.insert(compNode, decNode);
		Node returnNode1=MOFContextClassHelper.generateStateClassesRelatedCodeForParallelSM(R,compNode);
//		R.insert(MOFContextClassHelper.generateStateClassesRelatedCodeForParallelSM().getRootNode(),compNode);//--------------f call
		compNode1=new CompositeNode("221122#GCC14:else:GCC12","self.generateStateClassesRelatedCode(immediateStateList, stateList, stateObjectsName)");
		R.insert(compNode1, decNode);
		Node returnNode2=MOFContextClassHelper.generateStateClassesRelatedCode(R,compNode1);
//		R.insert(MOFContextClassHelper.generateStateClassesRelatedCode().getRootNode(),compNode1);//--------------f call
		compNode2=new CompositeNode("225#GCC15:Comp","self.generateCCSignalCode(signalsByRegion, stateObjectsName, false, self.name, \"\")");
		R.insert(compNode2, returnNode1);
		compNode2.setParentNode(returnNode2);
		returnNode=MOFContextClassHelper.generateCCSignalCode(R,compNode2);
//		R.insert(MOFContextClassHelper.generateCCSignalCode().getRootNode(),compNode2);//--------------f call
		
		compNode=new CompositeNode("228#GCC16:Comp","self.generateRunMethod(false)");
		R.insert(compNode, returnNode);
		returnNode1=MOFContextClassHelper.generateRunMethod(R,compNode);
//		R.insert(MOFContextClassHelper.generateRunMethod().getRootNode(),compNode);//--------------f call
		
		compNode1=new CompositeNode("230#GCC17:Comp","self.generateTimeoutCodeForContextClass(stateObjectsName, isParallelSM)");
		R.insert(compNode1, returnNode1);
		returnNode=MOFContextClassHelper.generateTimeoutCodeForContextClass(R,compNode1);
//		R.insert(MOFContextClassHelper.generateTimeoutCodeForContextClass().getRootNode(),compNode1);//--------------f call
		
		compNode2=new CompositeNode("234#GCC18:Comp","self.generateChangeEventCodeForContextClass(stateObjectsName, isParallelSM)");
		R.insert(compNode2, returnNode);
		returnNode=MOFContextClassHelper.generateChangeEventCodeForContextClass(R,compNode2);
//		R.insert(MOFContextClassHelper.generateChangeEventCodeForContextClass().getRootNode(),compNode2);//--------------f call
		
		compNode1=new CompositeNode("237#GCC19:Comp","self.generateCompletionEventCodeForContextClass(stateObjectsName, isParallelSM)");
		R.insert(compNode1, returnNode);
		returnNode=MOFContextClassHelper.generateCompletionEventCodeForContextClass(R,compNode1);
//		R.insert(MOFContextClassHelper.generateCompletionEventCodeForContextClass().getRootNode(),compNode1);//--------------f call
		
		compNode2=new CompositeNode("240#GCC20:Comp","self.generateCompositeCode(compositeStateCode)");
		R.insert(compNode2, returnNode);
		returnNode=MOFContextClassHelper.generateCompositeCode(R,compNode2);
//		R.insert(MOFContextClassHelper.generateCompositeCode().getRootNode(),compNode2);//--------------f call
		
		compNode1=new CompositeNode("243#GCC21:Comp","self.generateAttributeRelatedCode(stateObjectsName, isParallelSM)");
		R.insert(compNode1, returnNode);
		returnNode=MOFContextClassHelper.generateAttributeRelatedCode(R,compNode1);
//		R.insert(MOFContextClassHelper.generateAttributeRelatedCode().getRootNode(),compNode1);//--------------f call
		 query="Region.allInstances()->size()>1 and StateMachine.allInstances()->exists(st|st.region->size()>1)";
		 query1="Region.allInstances()->size()>0 and StateMachine.allInstances()->exists(st|st.region->size()=1)";

		decNode=new DecisionNode("246246#GCC22:DecisionNode:C4","isParallelSM == true");
		cond=new Condition(query);
		decNode.setcondition(cond);
		decNode.setParentBlock(0);
		decNode.setFalseCondition(new Condition(query1));
		R.insert(decNode, returnNode);
		compNode=new CompositeNode("247742#GCC23:then:GCC22","self.generateIsInStateMethodForParallelSM(stateList, stateObjectsName)");
		R.insert(compNode, decNode);
		returnNode=MOFContextClassHelper.generateIsInStateMethodForParallelSM(R,compNode);
//		R.insert(MOFContextClassHelper.generateIsInStateMethodForParallelSM().getRootNode(),compNode);//--------------f call
		compNode2=new CompositeNode("248#GCC24:Comp","self.generateAddExceptionMethodsForParallelSM(stateList, stateObjectsName)");
		R.insert(compNode2, returnNode);
		returnNode1=MOFContextClassHelper.generateAddExceptionMethodsForParallelSM(R,compNode2);
//		R.insert(MOFContextClassHelper.generateAddExceptionMethodsForParallelSM().getRootNode(),compNode2);//--------------f call
		compNode1=new CompositeNode("251152#GCC25:else:GCC22","self.generateIsInStateMethod(stateList,stateObjectsName)");
		R.insert(compNode1, decNode);
		returnNode2=MOFContextClassHelper.generateIsInStateMethod(R,compNode1);
//		R.insert(MOFContextClassHelper.generateIsInStateMethod().getRootNode(),compNode1);//--------------f call
		
		atNode2=new AtomicNode("253#GCC26","Line:253-Line260");
		R.insert(atNode2, returnNode1);
		atNode2.setParentNode(returnNode2);
		
		return atNode2;
	}
	//conditions 0 branches 1
	public static Node generateStatelessClass(CFG R, Node parent){
		AtomicNode atNode1,atNode2;
		CompositeNode compNode, compNode1;
		
//		CFG R =new CFG();
		atNode1=new AtomicNode("270#GSC1","Line:271-Line278");
		R.insert(atNode1, parent);
		compNode=new CompositeNode("279#GSC2:Comp","self.generateHeaderForStatelessClass(self.name.replace(\" \", \"\"), pkgName)");
		R.insert(compNode, atNode1);
		Node returnNode=MOFContextClassHelper.generateHeaderForStatelessClass(R,compNode);
//		R.insert(MOFContextClassHelper.generateHeaderForStatelessClass().getRootNode(),compNode);//--------------f call
		
		compNode1=new CompositeNode("282#GSC3:Comp","self.generateConstructorStateless()");
		R.insert(compNode1, returnNode);
		returnNode=MOFContextClassHelper.generateConstructorStateless(R,compNode1);
//		R.insert(MOFContextClassHelper.generateConstructorStateless().getRootNode(),compNode1);//--------------f call
		
		compNode=new CompositeNode("283#GSC4:Comp","self.generateAttributeRelatedCodeStateless()");
		R.insert(compNode, returnNode);
		returnNode=MOFContextClassHelper.generateAttributeRelatedCodeStateless(R,compNode);
//		R.insert(MOFContextClassHelper.generateAttributeRelatedCodeStateless().getRootNode(),compNode);//--------------f call
		
		atNode2=new AtomicNode("286#GSC5","Line:286-Line290");
		R.insert(atNode2, returnNode);
		
		return atNode2;
	}
	
}
