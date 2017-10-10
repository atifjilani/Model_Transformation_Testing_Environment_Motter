package questlab.motter.mofscript.CFG;

import questlab.motter.CFG.AtomicNode;
import questlab.motter.CFG.CFG;
import questlab.motter.CFG.Node;
//conditions 0 branches 1
public class MOFOCLTranslator {

	//conditions 0 branches 1
	public static Node translateOCL(CFG R, Node parent){
		AtomicNode atNode1;
//		CFG R =new CFG();
		atNode1=new AtomicNode("10#TOCL1","Line:10-Line20");
		R.insert(atNode1, parent);
				
		return atNode1;
	}
}
