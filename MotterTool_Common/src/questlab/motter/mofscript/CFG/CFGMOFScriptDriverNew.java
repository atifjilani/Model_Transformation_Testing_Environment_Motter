package questlab.motter.mofscript.CFG;

import questlab.motter.CFG.CFG;
import questlab.motter.CFG.CFGATLDriverU;
import questlab.motter.common.driver.TryCFG;




public class CFGMOFScriptDriverNew {

	public CFGMOFScriptDriverNew() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		CFG TP=MOFMain.mainMethod();
//		CFG TP=TryCFG.funRulePersistentClass2Table();
//		CFGATLDriverU U=new CFGATLDriverU();
//		CFG TP=U.funRulePersistentClass2Table();
		//CFG TP=MOFStateClassesGenerator.generateCompositeStateClassWithParallelRegions();
		//CFG TP=MOFContextClassHelper.generateAttributeRelatedCode();
	
				
        TP.BFS();
        TP.reInitializeIsVisisted(TP.getRootNode());
        
        
//        TP.PreOrder();
//        TP.reInitializeIsVisisted(TP.getRootNode());
//       
        System.out.println();
//        
//		TP.PostOrder();
//		TP.reInitializeIsVisisted(TP.getRootNode());
	}
	
	
}

	

