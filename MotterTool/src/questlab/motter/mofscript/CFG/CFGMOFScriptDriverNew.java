package questlab.motter.mofscript.CFG;

import questlab.motter.CFG.CFG;




public class CFGMOFScriptDriverNew {

	public CFGMOFScriptDriverNew() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		CFG TP=MOFMain.mainMethod();
		//CFG TP=MOFStateClassesGenerator.generateCompositeStateClassWithParallelRegions();
		
	
				
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

	

