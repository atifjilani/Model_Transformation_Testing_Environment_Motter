package questlab.motter.mofscript.CFG;

import questlab.motter.CFG.AtomicNode;
import questlab.motter.CFG.CFG;
import questlab.motter.CFG.CompositeNode;
import questlab.motter.CFG.Condition;
import questlab.motter.CFG.DecisionNode;

public class MOFJavaSyntaxTrans {

	public static CFG getSignatureOfAllSignals(){
		AtomicNode atNode1,atNode2;
		CompositeNode compNode;
		DecisionNode decNode;
		Condition cond;
		CFG R =new CFG();
		atNode1=new AtomicNode("19#GSOAS1","Line:19-Line28");
		R.insert(atNode1, null);
		compNode=new CompositeNode("29#GSOAS2:Comp","tr.event.getSignalEventJavaSyntax())");
		R.insert(compNode, atNode1);
		R.insert(getSignalEventJavaSyntax().getRootNode(),compNode);//--------------f call
		atNode2=new AtomicNode("30#GSOAS3","Line:30-Line40");
		R.insert(atNode2, compNode);
		
		decNode=new DecisionNode("41#GSOAS4:DecisionNode:C1","s.isComposite");
		cond=new Condition("s.isComposite");
		decNode.setcondition(cond);
		R.insert(decNode, atNode2);
		atNode1=new AtomicNode("42#GCF5:then:GSOAS4","Line:42-Line45");
		R.insert(atNode1, decNode);
		compNode=new CompositeNode("46#GSOAS6:Comp","rg.getSignatureOfAllSignals()");
		R.insert(compNode, atNode1);
	//	R.insert(getSignatureOfAllSignals().getRootNode(),compNode);//--------------f call
		
		atNode2=new AtomicNode("47#GSOAS7","Line:47-Line56");
		R.insert(atNode2, compNode);
		atNode2.setParentNode(decNode);
		
		return R;
		
	}
	
	public static CFG getSignatureOfAllOperations(){
		AtomicNode atNode1,atNode2;
		CompositeNode compNode;

		CFG R =new CFG();
		atNode1=new AtomicNode("61#GSOAO1","Line:61-Line65");
		R.insert(atNode1, null);
		compNode=new CompositeNode("66#GSOAO2:Comp","tr.event.getCallEventJavaSyntax(operationList)");
		R.insert(compNode, atNode1);
		R.insert(getCallEventJavaSyntax().getRootNode(),compNode);//--------------f call
		atNode2=new AtomicNode("67#GSOAS3","Line:67-Line70");
		R.insert(atNode2, compNode);
		
		return R;
	}
	
	public static CFG getCallEventJavaSyntax(){
		AtomicNode atNode1,atNode2;
		CompositeNode compNode;

		CFG R =new CFG();
		atNode1=new AtomicNode("74#GCEJS1","Line:74-Line75");
		R.insert(atNode1, null);
		compNode=new CompositeNode("76#GCEJS2:Comp","self.operation.getOperationJavaSignature(operationList)");
		R.insert(compNode, atNode1);
		R.insert(getOperationJavaSignature().getRootNode(),compNode);//--------------f call
		atNode2=new AtomicNode("77#GCEJS3","Line:77-Line78");
		R.insert(atNode2, compNode);
		
		return R;
	}
	
	public static CFG getSignalEventJavaSyntax(){
		
		CompositeNode compNode;
		CFG R =new CFG();
		
		compNode=new CompositeNode("76#GSEJS1:Comp","self.signal.getSignalJavaSignature()");
		R.insert(compNode, null);
		R.insert(getSignalJavaSignature().getRootNode(),compNode);//--------------f call
				
		return R;
	}
	
	public static CFG getSignalJavaSignatureStringContextParam(){
		AtomicNode atNode1,atNode2;
		CompositeNode compNode;

		CFG R =new CFG();
		atNode1=new AtomicNode("88#GSJSSCP1","Line:88-Line102");
		R.insert(atNode1, null);
		compNode=new CompositeNode("103#GSJSSCP2:Comp","type.getJavaEquivalent()");
		R.insert(compNode, atNode1);
		R.insert(getJavaEquivalent().getRootNode(),compNode);//--------------f call
		atNode2=new AtomicNode("104#GSJSSCP3","Line:104-Line115");
		R.insert(atNode2, compNode);
		
		return R;
	}
	
	public static CFG getSignalJavaSignatureString(){
		AtomicNode atNode1,atNode2;
		CompositeNode compNode;

		CFG R =new CFG();
		atNode1=new AtomicNode("119#GSJSS1","Line:119-Line133");
		R.insert(atNode1, null);
		compNode=new CompositeNode("134#GSJSS2:Comp","type.getJavaEquivalent()");
		R.insert(compNode, atNode1);
		R.insert(getJavaEquivalent().getRootNode(),compNode);//--------------f call
		atNode2=new AtomicNode("135#GSJSS3","Line:135-Line148");
		R.insert(atNode2, compNode);
		
		return R;
	}
	
	public static CFG getSignalJavaSignature(){
		AtomicNode atNode1,atNode2;
		CompositeNode compNode;

		CFG R =new CFG();
		atNode1=new AtomicNode("154#GSJS1","Line:154-Line156");
		R.insert(atNode1, null);
		compNode=new CompositeNode("157#GSJS2:Comp","self.getSignalJavaSignatureString()");
		R.insert(compNode, atNode1);
		R.insert(getSignalJavaSignatureString().getRootNode(),compNode);//--------------f call
		atNode2=new AtomicNode("158#GSJS3","Line:158-Line163");
		R.insert(atNode2, compNode);
		
		return R;
	}
	
	public static CFG getOperationJavaSignature(){
		AtomicNode atNode1,atNode2;
		CompositeNode compNode;

		CFG R =new CFG();
		atNode1=new AtomicNode("170#GOJS1","Line:170-Line184");
		R.insert(atNode1, null);
		compNode=new CompositeNode("185#GOJS2:Comp","self.type.getJavaEquivalent()");
		R.insert(compNode, atNode1);
		R.insert(getJavaEquivalent().getRootNode(),compNode);//--------------f call
		atNode2=new AtomicNode("186#GOJS3","Line:186-Line205");
		R.insert(atNode2, compNode);
		
		return R;
	}
	
	public static CFG getOperationJavaCall(){
		AtomicNode atNode1;

		CFG R =new CFG();
		atNode1=new AtomicNode("213#GOJC1","Line:213-Line238");
		R.insert(atNode1, null);
				
		return R;
	}
	
	public static CFG getSignalJavaCallAsSignalWithThis(){
		AtomicNode atNode1;

		CFG R =new CFG();
		atNode1=new AtomicNode("243#GSJCASWT1","Line:243-Line268");
		R.insert(atNode1, null);
				
		return R;
	}
	
	public static CFG getSignalJavaCallAsSignalWithThisNotContext(){
		AtomicNode atNode1;

		CFG R =new CFG();
		atNode1=new AtomicNode("270#GSJCASWTNC1","Line:270-Line296");
		R.insert(atNode1, null);
				
		return R;
	}
	
	public static CFG getSignalJavaCallWithThis(){
		AtomicNode atNode1;

		CFG R =new CFG();
		atNode1=new AtomicNode("300#GSJCSWT1","Line:300-Line325");
		R.insert(atNode1, null);
				
		return R;
	}
	
	public static CFG processTimeEventExpression(){
		AtomicNode atNode1;

		CFG R =new CFG();
		atNode1=new AtomicNode("327#PTEE1","Line:327-Line332");
		R.insert(atNode1, null);
				
		return R;
	}
	
	public static CFG formatStateName(){
		AtomicNode atNode1;

		CFG R =new CFG();
		atNode1=new AtomicNode("335#FSM1","Line:335-Line338");
		R.insert(atNode1, null);
				
		return R;
	}
	
	public static CFG getJavaEquivalent(){
		AtomicNode atNode1;

		CFG R =new CFG();
		atNode1=new AtomicNode("341#GJE1","Line:341-Line359");
		R.insert(atNode1, null);
				
		return R;
	}
	
	public static CFG handleAssignmentAction(){
		AtomicNode atNode1;

		CFG R =new CFG();
		atNode1=new AtomicNode("373#HAA1","Line:373-Line435");
		R.insert(atNode1, null);
				
		return R;
	}
	
	public static CFG parseActionCodeHavingActionClassCall(){
		AtomicNode atNode1;

		CFG R =new CFG();
		atNode1=new AtomicNode("438#PACHACC1","Line:438-Line556");
		R.insert(atNode1, null);
				
		return R;
	}
	
	public static CFG convertActionToJavaCall(){
		AtomicNode atNode1;

		CFG R =new CFG();
		atNode1=new AtomicNode("580#CATJC1","Line:580-Line671");
		R.insert(atNode1, null);
				
		return R;
	}
	
	public static CFG convertActionToJavaCallAsString(){
		AtomicNode atNode1;

		CFG R =new CFG();
		atNode1=new AtomicNode("580#CATJCAS1","Line:673-Line687");
		R.insert(atNode1, null);
				
		return R;
	}
	
	public static CFG generateMethodNoParam(){
		AtomicNode atNode1;

		CFG R =new CFG();
		atNode1=new AtomicNode("701#GMNP1","Line:701-Line714");
		R.insert(atNode1, null);
				
		return R;
	}
	
	public static CFG generateMethodWithParam(){
		AtomicNode atNode1;

		CFG R =new CFG();
		atNode1=new AtomicNode("716#GMWP1","Line:716-Line739");
		R.insert(atNode1, null);
				
		return R;
	}
	
	public static CFG convertToSignalCall(){
		AtomicNode atNode1;

		CFG R =new CFG();
		atNode1=new AtomicNode("742#GMWP1","Line:742-Line812");
		R.insert(atNode1, null);
				
		return R;
	}
	
	public static CFG surroundWithTryCatch(){
		AtomicNode atNode1;

		CFG R =new CFG();
		atNode1=new AtomicNode("815#SWTC1","Line:815-Line825");
		R.insert(atNode1, null);
				
		return R;
	}
	
	public static CFG addHashtable(){
		AtomicNode atNode1;

		CFG R =new CFG();
		atNode1=new AtomicNode("827#AH1","Line:827-Line835");
		R.insert(atNode1, null);
				
		return R;
	}
	
	public static CFG cloneHashtable(){
		AtomicNode atNode1;

		CFG R =new CFG();
		atNode1=new AtomicNode("837#CH1","Line:827-Line844");
		R.insert(atNode1, null);
				
		return R;
	}
}
