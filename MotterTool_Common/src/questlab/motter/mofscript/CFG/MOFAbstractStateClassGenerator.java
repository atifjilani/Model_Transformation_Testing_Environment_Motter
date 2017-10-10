package questlab.motter.mofscript.CFG;

import questlab.motter.CFG.AtomicNode;
import questlab.motter.CFG.CFG;
import questlab.motter.CFG.CompositeNode;
import questlab.motter.CFG.Node;
//conditions 0 branches 1
public class MOFAbstractStateClassGenerator {

	//conditions 0 branches 1
	public static Node generateStateAbstractClass(CFG R, Node parent){
		AtomicNode atNode1,atNode2;
		CompositeNode compNode;
	//	CFG R = new CFG();
		atNode1=new AtomicNode("11#GSAC1","Line:11-Line53");
		R.insert(atNode1, parent);
		compNode=new CompositeNode("54#GSAC2:Comp","signal.getSignalJavaSignatureStringContextParam(contextClassName)");
		R.insert(compNode, atNode1);
		Node returnNode=MOFJavaSyntaxTrans.getSignalJavaSignatureStringContextParam(R,compNode);
//		R.insert(MOFJavaSyntaxTrans.getSignalJavaSignatureStringContextParam().getRootNode(),compNode);//--------------f call
		atNode2=new AtomicNode("55#GSAC3","Line:55-Line166");
		R.insert(atNode2, returnNode);
		
		return atNode2;
		
	}
}
