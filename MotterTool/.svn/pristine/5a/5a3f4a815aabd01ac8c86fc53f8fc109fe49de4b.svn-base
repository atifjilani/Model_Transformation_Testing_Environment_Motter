package questlab.motter.mofscript.CFG;

import questlab.motter.CFG.AtomicNode;
import questlab.motter.CFG.CFG;
import questlab.motter.CFG.CompositeNode;
import questlab.motter.CFG.Condition;
import questlab.motter.CFG.DecisionNode;

public class MOFStateClassHelper {

	public static CFG generateConstructor(){
		AtomicNode atNode1;
		
		
		CFG R =new CFG();
		atNode1=new AtomicNode("16#GC1","Line:16-Line60");
		R.insert(atNode1, null);
		
		
		return R;
	}
	
	public static CFG generateChangeEvents(){
		AtomicNode atNode1;
		CompositeNode compNode;
		
		CFG R =new CFG();
		atNode1=new AtomicNode("66#GCE1","Line:66-Line114");
		R.insert(atNode1, null);
		compNode=new CompositeNode("115#GCE2:Comp"," self.generateCodeForBranchDistanceForChangeEvent(changeTransList, contextClassName, cVar)");
		R.insert(compNode, atNode1);
		R.insert(generateCodeForBranchDistanceForChangeEvent().getRootNode(),compNode);//--------------f call
		atNode1=new AtomicNode("116#GCE3","Line:116-Line189");
		R.insert(atNode1, compNode);
		compNode=new CompositeNode("190#GCE4:Comp"," transition.generateCodeForTimersAndQueue()");
		R.insert(compNode, atNode1);
		R.insert(generateCodeForTimersAndQue().getRootNode(),compNode);//--------------f call
		atNode1=new AtomicNode("191#GCE5","Line:191-Line236");
		R.insert(atNode1, compNode);
		compNode=new CompositeNode("237#GCE6:Comp"," transition.generateCodeForChoiceNode(contextClass, isParallel, contextClassName, transition.source.name, stateObjName, actionClass)");
		R.insert(compNode, atNode1);
		R.insert(generateCodeForChoiceNode().getRootNode(),compNode);//--------------f call
		atNode1=new AtomicNode("238#GCE7","Line:191-Line291");
		R.insert(atNode1, compNode);
		
		return R;
	}
	
	public static CFG generateCompletionEventMethod(){
		AtomicNode atNode1;
		CompositeNode compNode;
		
		CFG R =new CFG();
		atNode1=new AtomicNode("295#GCEM1","Line:66-Line3544");
		R.insert(atNode1, null);
		compNode=new CompositeNode("355#GCEM2:Comp"," transition.generateCodeForChoiceNode(contextClass, isParallel, contextClassName, transition.source.name, stateObjName, actionClass)");
		R.insert(compNode, atNode1);
		R.insert(generateCodeForChoiceNode().getRootNode(),compNode);//--------------f call
		atNode1=new AtomicNode("356#GCEM3","Line:356-Line390");
		R.insert(atNode1, compNode);
			
		return R;
	}
	
	public static CFG generateTimeoutMethods(){
		AtomicNode atNode1;
		CompositeNode compNode;
		
		CFG R =new CFG();
		atNode1=new AtomicNode("395#GTM1","Line:395-Line588");
		R.insert(atNode1, null);
		compNode=new CompositeNode("589#GTM2:Comp"," transition.generateCodeForChoiceNode(contextClass, isParallel, contextClassName, transition.source.name, stateObjName, actionClass)");
		R.insert(compNode, atNode1);
		R.insert(generateCodeForChoiceNode().getRootNode(),compNode);//--------------f call
		atNode1=new AtomicNode("590#GTM3","Line:590-Line627");
		R.insert(atNode1, compNode);
		compNode=new CompositeNode("628#GTM4:Comp","self.generateCodeForBranchDistance(timeTransList, contextClassName)");
		R.insert(compNode, atNode1);
		R.insert(generateCodeForBranchDistance().getRootNode(),compNode);//--------------f call
		atNode1=new AtomicNode("629#GTM5","Line:629-Line746");
		R.insert(atNode1, compNode);
			
		return R;
	}
	
	public static CFG generateSignalEventLocal(){
		AtomicNode atNode,atNode1,atNode2;
		CompositeNode compNode;
		DecisionNode decNode;
		Condition cond;
		
		CFG R =new CFG();
		atNode1=new AtomicNode("751#GSEL1","Line:751-Line785");
		R.insert(atNode1, null);
		compNode=new CompositeNode("786#GSEL2:Comp"," transition.generateCodeForTimersAndQueue()");
		R.insert(compNode, atNode1);
		R.insert(generateCodeForTimersAndQue().getRootNode(),compNode);//--------------f call
		atNode1=new AtomicNode("787#GSEL3","Line:787-Line879");
		R.insert(atNode1, compNode);
		compNode=new CompositeNode("880#GSEL4:Comp","transition.generateCodeForChoiceNode(contextClass, isParallel, contextClassName, transition.source.name, stateObjName, actionClass)");
		R.insert(compNode, atNode1);
		R.insert(generateCodeForChoiceNode().getRootNode(),compNode);//--------------f call
	
		decNode=new DecisionNode("883#GSEL5:DecisionNode:C1","!choiceNode");
		cond=new Condition("!choiceNode");
		decNode.setcondition(cond);
		R.insert(decNode, compNode);
		atNode1=new AtomicNode("884#GSEL6:then:GSEL5","Line:884-Line938");
		R.insert(atNode1, decNode);
		compNode=new CompositeNode("939#GSEL7:Comp"," self.generateCodeForBranchDistance(transitionList, contextClassName)");
		R.insert(compNode, atNode1);
		R.insert(generateCodeForBranchDistance().getRootNode(),compNode);//--------------f call
		
		
		compNode=new CompositeNode("947#GSEL8:else:GSEL5"," signalEvent.signal.getSignalJavaSignatureStringContextParam(contextClassName)");
		R.insert(compNode, decNode);
		R.insert(MOFJavaSyntaxTrans.getSignalJavaSignatureStringContextParam().getRootNode(),compNode);//--------------f call
		atNode2=new AtomicNode("949#GSEL9","Line:949-Line1032");
		R.insert(atNode1, compNode);
		
		atNode=new AtomicNode("1033#GSEL10","Line:1033-Line1056");
		R.insert(atNode, compNode);
		atNode.setParentNode(atNode2);
		
		
		compNode=new CompositeNode("1057#GSEL11:Comp"," transition.generateCodeForTimersAndQueue()");
		R.insert(compNode, atNode);
		R.insert(generateCodeForTimersAndQue().getRootNode(),compNode);//--------------f call
		atNode1=new AtomicNode("1058#GSEL12","Line:1058-Line1074");
		R.insert(atNode1, compNode);
		compNode=new CompositeNode("1075#GSEL13:Comp"," choiceNode = transition.generateCodeForChoiceNode(contextClass, isParallel, contextClassName, transition.source.name, stateObjName, actionClass)");
		R.insert(compNode, atNode1);
		R.insert(generateCodeForChoiceNode().getRootNode(),compNode);//--------------f call
		atNode1=new AtomicNode("1076#GSEL14","Line:1076-Line1109");
		R.insert(atNode1, compNode);
		compNode=new CompositeNode("1110#GSEL15:Comp"," self.generateCodeForBranchDistance(transitionList, contextClassName)");
		R.insert(compNode, atNode1);
		R.insert(generateCodeForBranchDistance().getRootNode(),compNode);//--------------f call
		atNode1=new AtomicNode("1111#GSEL16","Line:1111-Line1124");
		R.insert(atNode1, compNode);
		return R;
	}
	
	public static CFG generateStateEntryExitMethods(){
		AtomicNode atNode1;
		
		CFG R =new CFG();
		atNode1=new AtomicNode("1132#GSEEM1","Line:1132-Line1579");
		R.insert(atNode1, null);
		
		return R;
	}
	
	public static CFG negateOCLExpression(){
		AtomicNode atNode1;
		
		CFG R =new CFG();
		atNode1=new AtomicNode("1582#NOCLE1","Line:1582-Line1588");
		R.insert(atNode1, null);
		
		return R;
	}
	
	public static CFG convertGuardOCLToJavaWithClass(){
		AtomicNode atNode1;
		
		CFG R =new CFG();
		atNode1=new AtomicNode("1589#CGOCLTJWC1","Line:1589-Line1603");
		R.insert(atNode1, null);
		
		return R;
	}
	
	public static CFG convertGuardOCLToJava(){
		AtomicNode atNode1;
		
		CFG R =new CFG();
		atNode1=new AtomicNode("1606#CGOCLTJ1","Line:1606-Line1618");
		R.insert(atNode1, null);
		
		return R;
	}
	
	public static CFG generateTimeRelatedStateEntryCode(){
		AtomicNode atNode1;
		
		CFG R =new CFG();
		atNode1=new AtomicNode("1620#GTRSEC1","Line:1620-Line1805");
		R.insert(atNode1, null);
		
		return R;
	}
	
	public static CFG getTimeExpression(){
		AtomicNode atNode1;
		
		CFG R =new CFG();
		atNode1=new AtomicNode("1809#GTE1","Line:1809-Line1853");
		R.insert(atNode1, null);
		
		return R;
	}
	
	public static CFG generateStateNameGetter(){
		AtomicNode atNode1;
		
		CFG R =new CFG();
		atNode1=new AtomicNode("1856#GSNG1","Line:1856-Line1934");
		R.insert(atNode1, null);
		
		return R;
	}
	
	public static CFG generateErrorStateApproachLevel(){
		AtomicNode atNode1;
		
		CFG R =new CFG();
		atNode1=new AtomicNode("1856#GESAL1","Line:1937-Line1940");
		R.insert(atNode1, null);
		
		return R;
	}
	
	public static CFG generateStopExecutionMethod(){
		AtomicNode atNode1;
		
		CFG R =new CFG();
		atNode1=new AtomicNode("1942#GSEM1","Line:1942-Line1959");
		R.insert(atNode1, null);
		
		return R;
	}
	
	public static CFG generateClassHeader(){
		AtomicNode atNode1;
		
		CFG R =new CFG();
		atNode1=new AtomicNode("1963#GCH1","Line:1963-Line2023");
		R.insert(atNode1, null);
		
		return R;
	}
	
	public static CFG generateCodeForChoiceNode(){
		AtomicNode atNode1;
		
		CFG R =new CFG();
		atNode1=new AtomicNode("2026#GCFCN1","Line:2026-Line2162");
		R.insert(atNode1, null);
		
		return R;
	}
	
	public static CFG generateCodeForTimersAndQue(){
		AtomicNode atNode1;
		
		CFG R =new CFG();
		atNode1=new AtomicNode("2165#GCFTAQ1","Line:2165-Line2192");
		R.insert(atNode1, null);
		
		return R;
	}
	
	public static CFG sortByGuardType(){
		AtomicNode atNode1;
		
		CFG R =new CFG();
		atNode1=new AtomicNode("2196#SBGT1","Line:2196-Line2221");
		R.insert(atNode1, null);
		
		return R;
	}
	
	public static CFG getStateName(){
		AtomicNode atNode1;
		
		CFG R =new CFG();
		atNode1=new AtomicNode("2236#GSN1","Line:2236-Line2273");
		R.insert(atNode1, null);
		
		return R;
	}
	
	public static CFG getSignalEvents(){
		AtomicNode atNode1;
		
		CFG R =new CFG();
		atNode1=new AtomicNode("2275#GSE1","Line:2275-Line2304");
		R.insert(atNode1, null);
		
		return R;
	}
	
	public static CFG translateOCL(){
		AtomicNode atNode1;
		
		CFG R =new CFG();
		atNode1=new AtomicNode("2307#TOCL1","Line:2307-Line2323");
		R.insert(atNode1, null);
		
		return R;
	}
	
	public static CFG generateGetErrorIdMethod(){
		AtomicNode atNode1;
		
		CFG R =new CFG();
		atNode1=new AtomicNode("2325#GGEIM1","Line:2325-Line2341");
		R.insert(atNode1, null);
		
		return R;
	}
	
	public static CFG getAdjascentErrorStates(){
		AtomicNode atNode1;
		
		CFG R =new CFG();
		atNode1=new AtomicNode("2343#GAES1","Line:2343-Line2354");
		R.insert(atNode1, null);
		
		return R;
	}
	
	public static CFG generateCodeForBranchDistance(){
		AtomicNode atNode1;
		
		CFG R =new CFG();
		atNode1=new AtomicNode("2343#GCFBD1","Line:2357-Line2398");
		R.insert(atNode1, null);
		
		return R;
	}
	
	public static CFG generateCodeForBranchDistanceForChangeEvent(){
		AtomicNode atNode1;
		
		CFG R =new CFG();
		atNode1=new AtomicNode("2400#GCFBDFCE1","Line:2400-Line2447");
		R.insert(atNode1, null);
		
		return R;
	}
	public static CFG getTargetStateName(){
		AtomicNode atNode1;
		
		CFG R =new CFG();
		atNode1=new AtomicNode("2482#GTSN1","Line:2482-Line2506");
		R.insert(atNode1, null);
		
		return R;
	}
}
