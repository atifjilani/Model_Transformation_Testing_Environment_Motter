package questlab.motter.mofscript.CFG;

import questlab.motter.CFG.AtomicNode;
import questlab.motter.CFG.CFG;

public class MOFOCLTranslator {

	
	public static CFG translateOCL(){
		AtomicNode atNode1;
		CFG R =new CFG();
		atNode1=new AtomicNode("10#TOCL1","Line:10-Line20");
		R.insert(atNode1, null);
				
		return R;
	}
}
