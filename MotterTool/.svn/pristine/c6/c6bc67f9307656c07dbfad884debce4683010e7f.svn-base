package questlab.motter.mofscript.CFG;

import questlab.motter.CFG.AtomicNode;
import questlab.motter.CFG.CFG;
import questlab.motter.CFG.CompositeNode;
import questlab.motter.CFG.Condition;
import questlab.motter.CFG.DecisionNode;

public class MOFContextClassGenerator {

	public static CFG generateStatefulClassCode(){
		AtomicNode atNode,atNode1,atNode2;
		CompositeNode compNode;
		DecisionNode decNode,decNode1;
		Condition cond;
		CFG R =new CFG();
		atNode1=new AtomicNode("8#GSFCC1","Line:13-Line30");
		R.insert(atNode1, null);
		compNode=new CompositeNode("31#GSFCC2:Comp","stateMach.generateStateClasses(htTimeEventIds, maxNDChoiceCount, htTimeProbabilities, htNDChoices," 
		+"htNDPsuedoChoices, actionClass, stateFilePath, pkgName, oclToJavaClassPath, htClassSpecificErrorIds, maxErrorStateCount,"
		+"htErrorStateCountPerClass)");
		R.insert(compNode, atNode1);
		R.insert(MOFStateClassesGenerator.generateStateClasses().getRootNode(),compNode);//--------------f call
		atNode2=new AtomicNode("35#GSFCC3","Line:35-Line60");
		R.insert(atNode2, compNode);
		
		decNode=new DecisionNode("61#GSFCC3:DecisionNode:C1","stateMach.region.size() > 1");
		cond=new Condition("stateMach.region.size() > 1");
		decNode.setcondition(cond);
		R.insert(decNode, atNode2);
		atNode1=new AtomicNode("62#GSFCC4:then:GSFCC3","Line:62-Line88");
		R.insert(atNode1, decNode);
		compNode=new CompositeNode("89#GSFCC5:Comp","self.generateContextRegionClass(regionStateList, tempInitStates, tempStateObj, signalsByRegion," 
		+"rg.name+StateContext, self.name, classFilePath, rg.name, pkgName, actionClass)");
		R.insert(compNode, atNode1);
		R.insert(MOFContextClassHelper.generateContextRegionClass().getRootNode(),compNode);//--------------f call
		atNode2=new AtomicNode("91#GSFCC6","Line:91-Line101");
		R.insert(atNode2, compNode);
		atNode1=new AtomicNode("102#GSFCC7:else:GSFCC3","Line:102-Line118");
		R.insert(atNode1, decNode);
		
		decNode1=new DecisionNode("119#GSFCC8:DecisionNode:C2","targetState.oclIsTypeOf(uml.Pseudostate)");
		cond=new Condition("targetState.oclIsTypeOf(uml.Pseudostate)");
		decNode1.setcondition(cond);
		R.insert(decNode1, atNode1);
		atNode1=new AtomicNode("121#GSFCC9:then:GSFCC8","Line:120-Line125");
		R.insert(atNode1, decNode1);
		
		atNode=new AtomicNode("127127#GSFCC10:else:GSFCC8","Line:127-Line127");
		R.insert(atNode, decNode1);
				
		decNode=new DecisionNode("128#GSFCC11:DecisionNode:C3","targetState.isComposite");
		cond=new Condition("targetState.isComposite");
		decNode.setcondition(cond);
		R.insert(decNode, atNode);
		atNode1=new AtomicNode("129#GSFCC12:then:GSFCC11","Line:129-Line135");
		R.insert(atNode1, decNode);
		
		atNode=new AtomicNode("136#GSFCC13:else:GSFCC11","Line:136-Line136");
		R.insert(atNode, decNode);
				
		decNode=new DecisionNode("137137#GSFCC14:DecisionNode:C4","targetState.isOrthogonale");
		cond=new Condition("targetState.isOrthogonal");
		decNode.setcondition(cond);
		R.insert(decNode, atNode);
		atNode1=new AtomicNode("138#GSFCC15:then:GSFCC14","Line:138-Line139");
		R.insert(atNode1, decNode);
		
		atNode=new AtomicNode("140#GSFCC16:else:GSFCC14","Line:140-Line142");
		R.insert(atNode, decNode);
				
		atNode2=new AtomicNode("142#GSFCC17","Line:142-Line142");
		R.insert(atNode2, atNode1);
		atNode2.setParentNode(atNode);
				
		atNode=new AtomicNode("143#GSFCC18","Line:143-Line143");
		R.insert(atNode, atNode1);
		atNode2.setParentNode(atNode2);
				
		atNode2=new AtomicNode("144#GSFCC19","Line:144-Line144");
		R.insert(atNode2, atNode1);
		atNode2.setParentNode(atNode);
		
		
		
		compNode=new CompositeNode("148#GSFCC20:Comp","self.generateContextClass(immediateStateList, stateList, initStates, initPsuedoStates, stateObjectNames, signalsByRegion, compositeStateCode,"
	 +"sParallelSM, classFilePath, errorStatesCount, actionClass, callEventActions, htNDChoices, pkgName, htNDClassScope,"
	 +"maxNDChoiceCount, oclToJavaClassPath)");
				R.insert(compNode, atNode2);
				R.insert(generateContextClass().getRootNode(),compNode);//--------------f call
				//compNode.setParentNode(atNode2);
		
		return R;
	}
	
	public static CFG generateContextClass(){
		AtomicNode atNode,atNode1,atNode2;
		CompositeNode compNode,compNode1,compNode2;
		DecisionNode decNode;
		Condition cond;
		CFG R =new CFG();
		
		atNode1=new AtomicNode("162#GCC1","Line:162-Line168");
		R.insert(atNode1, null);
		compNode=new CompositeNode("169#GCC2:Comp","self.generateHeaderForContextClass(self.name.replace(\" \", \"\"), pkgName)");
		R.insert(compNode, atNode1);
		R.insert(MOFContextClassHelper.generateHeaderForContextClass().getRootNode(),compNode);//--------------f call
		atNode2=new AtomicNode("170#GCC3","Line:170-Line176");
		R.insert(atNode2, compNode);
		
		decNode=new DecisionNode("177#GCC4:DecisionNode:C1","uml.Operation != null");
		cond=new Condition("uml.Operation != null");
		decNode.setcondition(cond);
		R.insert(decNode, atNode2);
		atNode1=new AtomicNode("178#GCC5:then:GCC4","Line:178-Line181");
		R.insert(atNode1, decNode);
		atNode2=new AtomicNode("183#GCC6:else:GCC4","Line:183-Line189");
		R.insert(atNode2, decNode);
		atNode=new AtomicNode("190#GCC7","Line:190-Line196");
		R.insert(atNode, atNode1);
		atNode.setParentNode(atNode2);
		
		decNode=new DecisionNode("197#GCC8:DecisionNode:C2","trans.effect != null");
		cond=new Condition("trans.effect != null");
		decNode.setcondition(cond);
		R.insert(decNode, atNode);
		compNode=new CompositeNode("199#GCC9:then:GCC8","self.generateCCConstructor(immediateStateList, stateList, stateObjectsName, initStates, self.name.replace(\" \", \"\"), isParallelSM, actionCode," 
	    +"errorStatesCount, actionClass, htNDClassScope, maxNDChoiceCount, oclToJavaClassPath)");
		R.insert(compNode, decNode);
		R.insert(MOFContextClassHelper.generateCCConstructor().getRootNode(),compNode);//--------------f call
		atNode=new AtomicNode("202#GCC10","Line:202-Line208");
		R.insert(atNode, compNode);
		atNode.setParentNode(decNode);
		
		compNode=new CompositeNode("209#GCC11:Comp","self.generateStateObjectDefinitionCode(stateObjectsName, isParallelSM)");
		R.insert(compNode, atNode);
		R.insert(MOFContextClassHelper.generateStateObjectDefinitionCode().getRootNode(),compNode);//--------------f call
	
		decNode=new DecisionNode("215512#GCC12:DecisionNode:C3","isParallelSM == true");
		cond=new Condition("isParallelSM == true");
		decNode.setcondition(cond);
		R.insert(decNode, compNode);
		compNode=new CompositeNode("217#GCC13:then:GCC12","self.generateStateClassesRelatedCodeForParallelSM(stateList,stateObjectsName)");
		R.insert(compNode, decNode);
		R.insert(MOFContextClassHelper.generateStateClassesRelatedCodeForParallelSM().getRootNode(),compNode);//--------------f call
		compNode1=new CompositeNode("221#GCC14:else:GCC12","self.generateStateClassesRelatedCode(immediateStateList, stateList, stateObjectsName)");
		R.insert(compNode1, decNode);
		R.insert(MOFContextClassHelper.generateStateClassesRelatedCode().getRootNode(),compNode1);//--------------f call
		compNode2=new CompositeNode("225#GCC15:Comp","self.generateCCSignalCode(signalsByRegion, stateObjectsName, false, self.name, \"\")");
		R.insert(compNode2, compNode);
		compNode2.setParentNode(compNode1);
		R.insert(MOFContextClassHelper.generateCCSignalCode().getRootNode(),compNode2);//--------------f call
		
		compNode=new CompositeNode("228#GCC16:Comp","self.generateRunMethod(false)");
		R.insert(compNode, compNode2);
		R.insert(MOFContextClassHelper.generateRunMethod().getRootNode(),compNode);//--------------f call
		
		compNode1=new CompositeNode("230#GCC17:Comp","self.generateTimeoutCodeForContextClass(stateObjectsName, isParallelSM)");
		R.insert(compNode1, compNode);
		R.insert(MOFContextClassHelper.generateTimeoutCodeForContextClass().getRootNode(),compNode1);//--------------f call
		
		compNode2=new CompositeNode("234#GCC18:Comp","self.generateChangeEventCodeForContextClass(stateObjectsName, isParallelSM)");
		R.insert(compNode2, compNode1);
		R.insert(MOFContextClassHelper.generateChangeEventCodeForContextClass().getRootNode(),compNode2);//--------------f call
		
		compNode1=new CompositeNode("237#GCC19:Comp","self.generateCompletionEventCodeForContextClass(stateObjectsName, isParallelSM)");
		R.insert(compNode1, compNode2);
		R.insert(MOFContextClassHelper.generateCompletionEventCodeForContextClass().getRootNode(),compNode1);//--------------f call
		
		compNode2=new CompositeNode("240#GCC20:Comp","self.generateCompositeCode(compositeStateCode)");
		R.insert(compNode2, compNode1);
		R.insert(MOFContextClassHelper.generateCompositeCode().getRootNode(),compNode2);//--------------f call
		
		compNode1=new CompositeNode("243#GCC21:Comp","self.generateAttributeRelatedCode(stateObjectsName, isParallelSM)");
		R.insert(compNode1, compNode2);
		R.insert(MOFContextClassHelper.generateAttributeRelatedCodeStateless().getRootNode(),compNode1);//--------------f call
		
		decNode=new DecisionNode("246246#GCC22:DecisionNode:C4","isParallelSM == true");
		cond=new Condition("isParallelSM == true");
		decNode.setcondition(cond);
		R.insert(decNode, compNode1);
		compNode=new CompositeNode("247#GCC23:then:GCC22","self.generateIsInStateMethodForParallelSM(stateList, stateObjectsName)");
		R.insert(compNode, decNode);
		R.insert(MOFContextClassHelper.generateIsInStateMethodForParallelSM().getRootNode(),compNode);//--------------f call
		compNode2=new CompositeNode("248#GCC24:Comp","self.generateAddExceptionMethodsForParallelSM(stateList, stateObjectsName)");
		R.insert(compNode2, compNode);
		R.insert(MOFContextClassHelper.generateAddExceptionMethodsForParallelSM().getRootNode(),compNode2);//--------------f call
		compNode1=new CompositeNode("251#GCC25:else:GCC22","self.generateIsInStateMethod(stateList,stateObjectsName)");
		R.insert(compNode1, decNode);
		R.insert(MOFContextClassHelper.generateIsInStateMethod().getRootNode(),compNode1);//--------------f call
		
		atNode2=new AtomicNode("253#GCC26","Line:253-Line260");
		R.insert(atNode2, compNode2);
		atNode2.setParentNode(compNode1);
		
		return R;
	}
	
	public static CFG generateStatelessClass(){
		AtomicNode atNode1,atNode2;
		CompositeNode compNode, compNode1;
		
		CFG R =new CFG();
		atNode1=new AtomicNode("270#GSC1","Line:271-Line278");
		R.insert(atNode1, null);
		compNode=new CompositeNode("279#GSC2:Comp","self.generateHeaderForStatelessClass(self.name.replace(\" \", \"\"), pkgName)");
		R.insert(compNode, atNode1);
		R.insert(MOFContextClassHelper.generateHeaderForStatelessClass().getRootNode(),compNode);//--------------f call
		
		compNode1=new CompositeNode("282#GSC3:Comp","self.generateConstructorStateless()");
		R.insert(compNode1, compNode);
		R.insert(MOFContextClassHelper.generateConstructorStateless().getRootNode(),compNode1);//--------------f call
		
		compNode=new CompositeNode("283#GSC4:Comp","self.generateAttributeRelatedCodeStateless()");
		R.insert(compNode, compNode1);
		R.insert(MOFContextClassHelper.generateAttributeRelatedCodeStateless().getRootNode(),compNode);//--------------f call
		
		atNode2=new AtomicNode("286#GSC5","Line:286-Line290");
		R.insert(atNode2, compNode);
		
		return R;
	}
	
}
