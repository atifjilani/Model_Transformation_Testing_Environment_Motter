package org.questlab.motter.oclSolver;

import java.util.ArrayList;
import junit.framework.Assert;

import org.junit.Test;

import org.questlab.motter.atl.CreateSaveTester;

import snt.oclsolver.distance.ClassDiagramTestData;
import snt.oclsolver.search.SearchAlgorithmEnum;
import snt.oclsolver.tuples.ClassifierTuple;
import test.snt.oclsolver.AbstractTestCase;

public class TestExample01 extends AbstractTestCase{
	String umlFile = "../MotterTool/OCLSolverInput/StateMachineMetaModel.uml";
	//TestOCL/example/Blank Package.ecore
	String path = "OutputFile";
	@Test
	public void testQuery01()
	{
		ArrayList<ClassifierTuple> result1 = null;
	    ClassDiagramTestData.getInstance().reset(umlFile);
	    
	    
	    String query =
				"Package.allInstances()->select(p|p.name='pkg')->size() = 1"
			    + " and Class.allInstances()->forAll(c|c.isActive=true and c.ownedBehavior->forAll(b|b.oclIsTypeOf(Behavior) and c.ownedAttribute->forAll(a|a.name='aName' and a.visibility=VisibilityKind::public and a.oclIsTypeOf(Type))))->size() > 0" 
				+ " and StateMachine.allInstances()->forAll(s|s.region->select(r|r.name='sm')->size()>0)"
//				+ " and Transition.allInstances()->forAll(t| t.kind=TransitionKind::external and t.source.oclIsKindOf(Vertex) and t.target.oclIsKindOf(Vertex))"
//				+ " and Transition.allInstances()->forAll(t| t.effect.name='eff' and t.effect.Body='bd' and t.effect.language='lan')"
//				+ " and SignalEvent.allInstances()->select(s| s.name='sigEvt')->size()=1"
//				+ " and Signal.allInstances()->select(s| s.name='sig')->size()=1"
//				+ " and TimeEvent.allInstances()->select(t| t.name='tEvt' and t.isRelative=true and t.when.expr.oclIsKindOf(ValueSpecification))->size()<2"
//				+ " and Class.allInstances()->forAll(c|c.ownedOperation->forAll(o|o.name='op'))"
				+ " and Class.allInstances()->forAll(c|c.ownedReception->forAll(r|r.signal.name='rep'))"
				;
		
	    String objDiagramPath="OCLSolverOutput";
		String objDiagramFileName=objDiagramPath+"/OCLOutputStateMachine.xmi";
		int noOfIterations=100;
		int noOfRuns=1;
	    
	    result1 = test(noOfRuns,noOfIterations, query, SearchAlgorithmEnum.AVM, umlFile,objDiagramPath);
        try{

    	/*Logger logger = LogManager.getLogger();	
    		
		ObjScriptGenerator driver =  new ObjScriptGenerator(umlFile);
		driver.generateScript();
		
		SolverRunner exp = new SolverRunner();	
		exp.setNoOfRuns(noOfRuns);
		exp.setObjDiagramPath(objDiagramPath);
		//ArrayList<ClassifierTuple> result = exp.getValues(query.trim(), noOfIterations,SearchAlgorithmEnum.AVM);
*/
//        CreateSaveTester s=new CreateSaveTester();
//		s.retrieveTupple(result1);
//		s.generateXmiObjectDiagram(objDiagramFileName);
        }
        catch (Exception e) {
    		e.printStackTrace();
    	}
		String str = this.verifyResult(query,result1);
		Assert.assertEquals("true", str);

	}

}
