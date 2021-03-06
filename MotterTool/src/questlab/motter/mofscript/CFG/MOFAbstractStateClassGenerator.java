package questlab.motter.mofscript.CFG;

import questlab.motter.CFG.AtomicNode;
import questlab.motter.CFG.CFG;
import questlab.motter.CFG.CompositeNode;

public class MOFAbstractStateClassGenerator {

	
	public static CFG generateStateAbstractClass(){
		AtomicNode atNode1,atNode2;
		CompositeNode compNode;
		CFG R = new CFG();
		atNode1=new AtomicNode("11#GSAC1","Line:11-Line53");
		R.insert(atNode1, null);
		compNode=new CompositeNode("54#GSAC2:Comp","signal.getSignalJavaSignatureStringContextParam(contextClassName)");
		R.insert(compNode, atNode1);
		R.insert(MOFJavaSyntaxTrans.getSignalJavaSignatureStringContextParam().getRootNode(),compNode);//--------------f call
		atNode2=new AtomicNode("55#GSAC3","Line:55-Line166");
		R.insert(atNode2, compNode);
		
		return R;
		
	}
}
