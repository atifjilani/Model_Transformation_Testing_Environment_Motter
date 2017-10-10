package questlab.motter.mofscript.CFG;

import questlab.motter.CFG.AtomicNode;
import questlab.motter.CFG.CFG;
import questlab.motter.CFG.CompositeNode;
import questlab.motter.CFG.Condition;
import questlab.motter.CFG.DecisionNode;
import questlab.motter.CFG.Node;
//conditions 1 branches 4
public class MOFMain {
	//conditions 0 branches 1
	public static CFG mainMethod(){
		AtomicNode atNode1,atNode2;
		CompositeNode compNode,compNode1;
		
		
		CFG R =new CFG();
		atNode1=new AtomicNode("1#M0","Line:1-Line19");
		R.insert(atNode1, null);
		compNode=new CompositeNode("20#M1:Comp","getInputs()");
		R.insert(compNode, atNode1);
		Node returnNode=getInputs(R,compNode);
//		R.insert(getInputs().getRootNode(),compNode);//--------------f call
		atNode1=new AtomicNode("22#M2","Line:21-Line50");
		R.insert(atNode1, returnNode);
		compNode1=new CompositeNode("51#M3","self.readSimulatorConfigFile(simConfigFilePath, configReaderClassPath)");
		R.insert(compNode1,atNode1);
		returnNode=MOFSimulatorConfigFileReader.readSimulatorConfigFile(R,compNode1);
//		R.insert(MOFSimulatorConfigFileReader.readSimulatorConfigFile().getRootNode(),compNode1);//--------------f call
		atNode2=new AtomicNode("52#M4","Line:52-Line66");
		R.insert(atNode2, returnNode);
		compNode=new CompositeNode("67#M5:Comp","self.generateConfigurationFile(configurationfilePath, pkgName, classList))");
		R.insert(compNode, atNode2);
		returnNode=MOFConfigurationFileGenerator.generateConfigurationFile(R,compNode);
//		R.insert(MOFConfigurationFileGenerator.generateConfigurationFile().getRootNode(),compNode);//--------------f call
		atNode1=new AtomicNode("68#M6","Line:68-Line102");
		R.insert(atNode1, returnNode);
		compNode1=new CompositeNode("103#M7","self.generateCoverageInfo(classFilePath, pkgName)");
		R.insert(compNode1,atNode1);
		returnNode=MOFCoverageInfoGenerator.generateCoverageInfo(R,compNode1);
//		R.insert(MOFCoverageInfoGenerator.generateCoverageInfo().getRootNode(),compNode1);//--------------f call
		
		atNode2=new AtomicNode("104#M8","Line:104-Line108");
		R.insert(atNode2, returnNode);
		compNode=new CompositeNode("109#M9:Comp","self.generateErrorStateDescriptions(errorStateFilePath, pkgName, classList)");
		R.insert(compNode, atNode2);
		returnNode=MOFErrorStateDescriptionGenerator.generateErrorStateDescriptions(R,compNode);
//		R.insert(MOFErrorStateDescriptionGenerator.generateErrorStateDescriptions().getRootNode(),compNode);//--------------f call
		atNode1=new AtomicNode("110#M10","Line:110-Line130");
		R.insert(atNode1, returnNode);
		compNode1=new CompositeNode("131#M11","myClass.processEachClass( htTimeEventIds, maxNDChoiceCount, htNDClassScope,htTimeProbabilities, htNDChoices," 
		  		+"htNDPsuedoChoices, actionClass, stateFilePath, classFilePath, pkgName, oclToJavaClassPath, htClassSpecificErrorIds,"
		  		+"maxErrorStateCount, htErrorStateCountPerClass)");
		R.insert(compNode1,atNode1);
		returnNode=processEachClass(R,compNode1);
//		R.insert(processEachClass().getRootNode(),compNode1);//--------------f call
		atNode2=new AtomicNode("134#M12","Line:134-Line138");
		R.insert(atNode2, returnNode);
		
		return R;
	}
	//conditions 1 branches 2
	public static Node processEachClass(CFG R, Node parent){
		AtomicNode atNode1,atNode2;
		CompositeNode compNode,compNode1;
		DecisionNode decNode;
		Condition cond;	
//		CFG R =new CFG();
		String query="Class.allInstances()->exists(c|c.ownedBehavior->exists(oclIsTypeOf(StateMachine)))";
		String query1="Class.allInstances()->exists(c|c.ownedBehavior->exists(oclIsTypeOf(StateMachine)))";
		atNode1=new AtomicNode("142#PEC1","Line:142-Line144");
		R.insert(atNode1, parent);
		decNode=new DecisionNode("145145#PEC2:DecisionNode:C1","sm.size() == 0");
		cond=new Condition(query);
		decNode.setcondition(cond);
		decNode.setFalseCondition(new Condition(query1));
		decNode.setParentBlock(0);
		R.insert(decNode, atNode1);
		compNode=new CompositeNode("148148#PEC3:then:PEC2","self.generateStatelessClass(classFilePath, pkgName)");
		R.insert(compNode, decNode);
		Node returnNode1=MOFContextClassGenerator.generateStatelessClass(R,compNode);
//		R.insert(MOFContextClassGenerator.generateStatelessClass().getRootNode(),compNode);//--------------f call
		compNode1=new CompositeNode("152152#PEC4:else:PEC2","self.generateStatefulClassCode(htTimeEventIds, maxNDChoiceCount, htNDClassScope,htTimeProbabilities," 
			+"htNDChoices, htNDPsuedoChoices, actionClass, stateFilePath, classFilePath, pkgName, oclToJavaClassPath,"
			+"htClassSpecificErrorIds, maxErrorStateCount, htErrorStateCountPerClass)");
		R.insert(compNode1, decNode);
		Node returnNode2=MOFContextClassGenerator.generateStatefulClassCode(R,compNode1);
//		R.insert(MOFContextClassGenerator.generateStatefulClassCode().getRootNode(),compNode1);//--------------f call
		atNode2=new AtomicNode("156#PEC5","Line:156-Line157");
		R.insert(atNode2, returnNode1);
		atNode2.setParentNode(returnNode2);	
		return atNode2;
	}
	//conditions 0 branches 1
	public static Node getInputs(CFG R, Node parent){
//		CFG R =new CFG();
		AtomicNode atNode1=new AtomicNode("142#GI1","Line:160-Line163");
		R.insert(atNode1, parent);
		return atNode1;
		}
}
