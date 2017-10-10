package questlab.motter.mofscript.CFG;

import questlab.motter.CFG.AtomicNode;
import questlab.motter.CFG.CFG;
import questlab.motter.CFG.Node;
//conditions 0 branches 1
public class MOFSimulatorConfigFileReader {
	//conditions 0 branches 1
	public static Node readSimulatorConfigFile(CFG R, Node parent){
		AtomicNode atNode1;
//		CFG R =new CFG();
		atNode1=new AtomicNode("3#RSCF1","Line:3-Line42");
		R.insert(atNode1, parent);
				
		return atNode1;
	}
}
