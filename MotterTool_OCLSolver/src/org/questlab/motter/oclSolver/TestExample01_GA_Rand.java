package org.questlab.motter.oclSolver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import junit.framework.Assert;

import org.junit.Test;

//import org.questlab.motter.atl.CreateSaveTester;

import snt.oclsolver.distance.ClassDiagramTestData;
import snt.oclsolver.search.SearchAlgorithmEnum;
import snt.oclsolver.tuples.ClassifierTuple;
import test.snt.oclsolver.AbstractTestCase;

public class TestExample01_GA_Rand extends AbstractTestCase{
	
	String umlFile = "../MotterTool_OCLSolver/OCLSolverInputForExp/StateMachineMetaModel.uml";
	int noOfIterations=1000;
	int noOfRuns=1;
	SearchAlgorithmEnum SearchAlgo=SearchAlgorithmEnum.SSGA;
	String objDiagramPath="OCLSolverOutput";
	String objDiagram="../MotterTool_OCLSolver/";
	
    public static void CopyFiles(String source, String target)   {	
    	
    	InputStream inStream = null;
	    OutputStream outStream = null;
		
    	try{
    		File afile =new File(source);
    	    File bfile =new File(target);
    		
    	    inStream = new FileInputStream(afile);
    	    outStream = new FileOutputStream(bfile);
        	byte[] buffer = new byte[1024];
    		
    	    int length;
    	    //copy the file content in bytes 
    	    while ((length = inStream.read(buffer)) > 0){
    	    	outStream.write(buffer, 0, length);
    	    }
    	 
    	    inStream.close();
    	    outStream.close();
    	    System.out.println("File is copied successfully!");
    	    
    	}catch(IOException e){
    		e.printStackTrace();
    	}
    }
	
	@SuppressWarnings("unused")
	@Test
	public void testQuery01()
	{     //GCF4 : if(att.getAppliedStereotype(ST_NON_DETERMINISTIC) != null)
		String objDiagramFileName=objDiagram+"/OCLSolverResult1.txt";
		ArrayList<ClassifierTuple> result1 = null;
	    ClassDiagramTestData.getInstance().reset(umlFile);
	    	    
	    String query ="Class.allInstances()->forAll(c|c.ownedAttribute->forAll(o|o.oclIsTypeOf(Property))) " 
				    + "and Property.allInstances()->size()=2 "
					+ "and Property.allInstances()->forAll(p|p.stereotype4=false) " 
				    ;

	    result1 = test(noOfRuns,noOfIterations, query, SearchAlgo, umlFile,objDiagramPath);
        try{
        	CopyFiles("../MotterTool_OCLSolver/all.log", "../MotterTool_OCLSolver/testQuery1.log");
        	String str = this.verifyResult(query,result1);
        	Assert.assertEquals("true", str);
        	
        	if(str.equals("true"))
			MOFExperiment.retrieveTuppleForExperiment(result1, objDiagramFileName);
        	
        }
        catch (Exception e) {
    		e.printStackTrace();
    	}
       
	}

	@SuppressWarnings("unused")
	@Test
	public void testQuery02()
	{     //GCF7 : if(t.getAppliedStereotype(ST_TIME_PROBABILITY) != null)
		
		String objDiagramFileName=objDiagram+"/OCLSolverResult2.txt";
		ArrayList<ClassifierTuple> result1 = null;
	    ClassDiagramTestData.getInstance().reset(umlFile);
	    	    
	    String query =
				
				"Class.allInstances()->forAll(c|c.ownedAttribute->forAll(o|o.oclIsTypeOf(Property)) and c.ownedBehavior->forAll(o| o.oclIsTypeOf(StateMachine))) " 
	    		+ "and Property.allInstances()->forAll (p|p.stereotype4=false) " 
				+ "and StateMachine.allInstances()->forAll(sm|sm.region->forAll(r| r.oclIsTypeOf(Region))) "  
				+ "and Region.allInstances()->forAll(r|r.transition->forAll(t| t.oclIsTypeOf(Transition))) "
				+ "and Transition.allInstances()->forAll(t|t.source.oclIsTypeOf(Pseudostate) and t.target.oclIsTypeOf(State) and " +
				  "t.stereotype2=false) " 
				+ "and Class.allInstances()->size()=1 " 
				+ "and Property.allInstances()->size()=1 " 
				+ "and StateMachine.allInstances()->size()=1 " 
				+ "and Region.allInstances()->size()=1 " 
				+ "and Transition.allInstances()->size()=1 " 
				;
	        
	    	result1 = test(noOfRuns,noOfIterations, query, SearchAlgo, umlFile,objDiagramPath);  
	    try{
        	CopyFiles("../MotterTool_OCLSolver/all.log", "../MotterTool_OCLSolver/testQuery2.log");
        	String str = this.verifyResult(query,result1);
        	Assert.assertEquals("true", str);
        	if(str.equals("true"))
			MOFExperiment.retrieveTuppleForExperiment(result1, objDiagramFileName);
       	    //CopyFiles("../MotterTool_OCLSolver/all.log", "../MotterTool_OCLSolver/testQuery2.log");
           
        }
        catch (Exception e) {
    		e.printStackTrace();
    	}
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testQuery03()
	{     //GCF10 : if(ps.getAppliedStereotype(ST_ND_CHOICE) != null)
		String objDiagramFileName=objDiagram+"/OCLSolverResult3.txt";
		ArrayList<ClassifierTuple> result1 = null;
	    ClassDiagramTestData.getInstance().reset(umlFile);
	    	    
	    String query =
				
				"Class.allInstances()->forAll(c|c.ownedAttribute->forAll(o|o.oclIsTypeOf(Property)) and c.ownedBehavior->forAll(o| o.oclIsTypeOf(StateMachine))) " 
	    		+ "and Property.allInstances()->forAll(p|p.stereotype4=false) " 
				+ "and StateMachine.allInstances()->forAll(sm|sm.region->forAll(r| r.oclIsTypeOf(Region))) "  
				+ "and Region.allInstances()->forAll(r|r.transition->forAll(t| t.oclIsTypeOf(Transition))) "
				+ "and Transition.allInstances()->forAll(t|t.source.oclIsTypeOf(Pseudostate) and t.target.oclIsTypeOf(State) and " +
				  "t.stereotype2=false) " 
				+ "and Class.allInstances()->size()=1 " 
				+ "and Property.allInstances()->size()=1 " 
				+ "and StateMachine.allInstances()->size()=1 " 
				+ "and Region.allInstances()->size()=1 " 
				+ "and Transition.allInstances()->size()=1 " 
				+ "and Pseudostate.allInstances()->select(p|p.stereotype1=false)->size()>1 "
				;

	    result1 = test(noOfRuns,noOfIterations, query, SearchAlgo, umlFile,objDiagramPath);
        try{
        	CopyFiles("../MotterTool_OCLSolver/all.log", "../MotterTool_OCLSolver/testQuery3.log");
        	String str = this.verifyResult(query,result1);
        	Assert.assertEquals("true", str);
        	if(str.equals("true"))
			MOFExperiment.retrieveTuppleForExperiment(result1, objDiagramFileName);
       	    
          
        }
        catch (Exception e) {
    		e.printStackTrace();
    	}
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testQuery04()
	{     //GCF15 & 18 : if(scope.name == ST_PROP_VAL_CLASS or scope.name = ST_PROP_VAL_HYBRID) & 
		// if(scope.name == ST_PROP_VAL_STATE or scope.name = ST_PROP_VAL_HYBRID)
		String objDiagramFileName=objDiagram+"/OCLSolverResult4.txt";
		ArrayList<ClassifierTuple> result1 = null;
	    ClassDiagramTestData.getInstance().reset(umlFile);
	    	    
	    String query =
				
				"Class.allInstances()->forAll(c|c.ownedAttribute->forAll(o|o.oclIsTypeOf(Property)) and c.ownedBehavior->forAll(o| o.oclIsTypeOf(StateMachine))) " 
			    		+ "and Property.allInstances()->forAll(p|p.stereotype4=false) " 
						+ "and StateMachine.allInstances()->forAll(sm|sm.region->forAll(r| r.oclIsTypeOf(Region))) "  
						+ "and Region.allInstances()->forAll(r|r.transition->forAll(t| t.oclIsTypeOf(Transition))) "
						+ "and Transition.allInstances()->forAll(t|t.source.oclIsTypeOf(Pseudostate) and t.target.oclIsTypeOf(State) and " +
						  "t.stereotype2=false) " 
						+ "and Class.allInstances()->size()=1 " 
						+ "and Property.allInstances()->size()=1 " 
						+ "and StateMachine.allInstances()->size()=1 " 
						+ "and Region.allInstances()->size()=1 " 
						+ "and Transition.allInstances()->size()=1 " 
						+ "and Pseudostate.allInstances()->select(p|p.stereotype1=false)->size()>1 "
						;

	    result1 = test(noOfRuns,noOfIterations, query, SearchAlgo, umlFile,objDiagramPath);
        try{
        	CopyFiles("../MotterTool_OCLSolver/all.log", "../MotterTool_OCLSolver/testQuery4.log");
        	String str = this.verifyResult(query,result1);
        	Assert.assertEquals("true", str);
        	if(str.equals("true"))
			MOFExperiment.retrieveTuppleForExperiment(result1, objDiagramFileName);
        	
        	           
        }
        catch (Exception e) {
    		e.printStackTrace();
    	}
	}
	@SuppressWarnings("unused")
	@Test
	public void testQuery05()
	{     //GCF21 : if(tr.target.getAppliedStereotype(ST_FAILURE) != null)
		String objDiagramFileName=objDiagram+"/OCLSolverResult5.txt";
		ArrayList<ClassifierTuple> result1 = null;
	    ClassDiagramTestData.getInstance().reset(umlFile);
	    	    
	    String query =
				
				"Class.allInstances()->forAll(c|c.ownedAttribute->forAll(o|o.oclIsTypeOf(Property)) and c.ownedBehavior->forAll(o| o.oclIsTypeOf(StateMachine))) " 
			    		+ "and Property.allInstances()->forAll(p|p.stereotype4=false) " 
						+ "and StateMachine.allInstances()->forAll(sm|sm.region->forAll(r| r.oclIsTypeOf(Region))) "  
						+ "and Region.allInstances()->forAll(r|r.transition->forAll(t| t.oclIsTypeOf(Transition))) "
						+ "and Transition.allInstances()->forAll(t|t.source.oclIsTypeOf(Pseudostate) and t.target.oclIsTypeOf(State) and " +
						  "t.stereotype2=false) " 
						+ "and Class.allInstances()->size()=1 " 
						+ "and Property.allInstances()->size()=1 " 
						+ "and StateMachine.allInstances()->size()=1 " 
						+ "and Region.allInstances()->size()=1 " 
						+ "and Transition.allInstances()->size()=1 " 
						+ "and Pseudostate.allInstances()->select(p|p.stereotype1=false)->size()>1 "
				        + "and State.allInstances()->select(p|p.stereotype3=false)->size()>1"
				        ;

	    result1 = test(noOfRuns,noOfIterations, query, SearchAlgo, umlFile,objDiagramPath);
        try{
        	CopyFiles("../MotterTool_OCLSolver/all.log", "../MotterTool_OCLSolver/testQuery5.log");
        	String str = this.verifyResult(query,result1);
        	Assert.assertEquals("true", str);
       
        	if(str.equals("true"))
			MOFExperiment.retrieveTuppleForExperiment(result1, objDiagramFileName);
        	
        	           
        }
        catch (Exception e) {
    		e.printStackTrace();
    	}
	}
	@SuppressWarnings("unused")
	@Test
	public void testQuery06()
	{     //GCI2 : if(myClass.getAppliedStereotype("Context") != null )
		String objDiagramFileName=objDiagram+"/OCLSolverResult6.txt";
		ArrayList<ClassifierTuple> result1 = null;
	    ClassDiagramTestData.getInstance().reset(umlFile);
        
	    String query = "Class.allInstances()->forAll(c|c.ownedAttribute->forAll(o|o.oclIsTypeOf(Property)) and c.ownedBehavior->forAll(o| o.oclIsTypeOf(StateMachine)) and c.stereotype5=false and c.isActive=false) " 
			    		+ "and Property.allInstances()->forAll(p|p.stereotype4=false) " 
						+ "and StateMachine.allInstances()->forAll(sm|sm.region->forAll(r| r.oclIsTypeOf(Region))) "  
						+ "and Region.allInstances()->forAll(r|r.transition->forAll(t| t.oclIsTypeOf(Transition))) "
						+ "and Transition.allInstances()->forAll(t|t.source.oclIsTypeOf(Pseudostate) and t.target.oclIsTypeOf(State) and " +
						  "t.stereotype2=false) " 
						+ "and Class.allInstances()->size()=1 " 
						+ "and Property.allInstances()->size()=1 " 
						+ "and StateMachine.allInstances()->size()=1 " 
						+ "and Region.allInstances()->size()=1 " 
						+ "and Transition.allInstances()->size()=1 " 
						+ "and Pseudostate.allInstances()->select(p|p.stereotype1=false)->size()>1 "
				        + "and State.allInstances()->select(p|p.stereotype3=false)->size()>1"
				        ;	    

	    result1 = test(noOfRuns,noOfIterations, query, SearchAlgo, umlFile,objDiagramPath);
        try{
        	CopyFiles("../MotterTool_OCLSolver/all.log", "../MotterTool_OCLSolver/testQuery6.log");    
        	String str = this.verifyResult(query,result1);
        	Assert.assertEquals("true", str);
        	if(str.equals("true"))
			MOFExperiment.retrieveTuppleForExperiment(result1, objDiagramFileName);
        	       
        }
        catch (Exception e) {
    		e.printStackTrace();
    	}
	}
	@SuppressWarnings("unused")
	@Test
	public void testQuery10()
	{     //PEC2 : if(sm.size() == 0)
		String objDiagramFileName=objDiagram+"/OCLSolverResult10.txt";
		ArrayList<ClassifierTuple> result1 = null;
	    ClassDiagramTestData.getInstance().reset(umlFile);
	    	    
 String query ="Class.allInstances()->forAll(c|c.ownedAttribute->forAll(o|o.oclIsTypeOf(Property))and c.stereotype5=false and c.isActive=false) " 
//	    		+ "and Property.allInstances()->select (p|p.stereotype4='NonDet')->size()=1 " 
//				+ "and Class.allInstances()->size()=1 " 
//				+ "and Property.allInstances()->size()=1 " 
				;

	    result1 = test(noOfRuns,noOfIterations, query, SearchAlgo, umlFile,objDiagramPath);
        try{
        	CopyFiles("../MotterTool_OCLSolver/all.log", "../MotterTool_OCLSolver/testQuery10.log");
        	String str = this.verifyResult(query,result1);
        	Assert.assertEquals("true", str);
        	if(str.equals("true"))
			MOFExperiment.retrieveTuppleForExperiment(result1, objDiagramFileName);
        	 
        }
        catch (Exception e) {
    		e.printStackTrace();
    	}
       
	}
	@SuppressWarnings("unused")
	@Test
	public void testQuery11()
	{     //
		String objDiagramFileName=objDiagram+"/OCLSolverResult11.txt";
		ArrayList<ClassifierTuple> result1 = null;
	    ClassDiagramTestData.getInstance().reset(umlFile);
	    	    
	    String query = "Class.allInstances()->forAll(c|c.ownedAttribute->forAll(o|o.oclIsTypeOf(Property)) and c.ownedBehavior->forAll(o| o.oclIsTypeOf(StateMachine)) and c.stereotype5=false and c.isActive=false) " 
	    		+ "and Property.allInstances()->forAll(p|p.stereotype4=false) " 
				+ "and StateMachine.allInstances()->forAll(sm|sm.region->forAll(r| r.oclIsTypeOf(Region))) "  
				+ "and Region.allInstances()->forAll(r|r.transition->forAll(t| t.oclIsTypeOf(Transition))) "
				+ "and Transition.allInstances()->forAll(t|t.source.oclIsTypeOf(Pseudostate) and t.target.oclIsTypeOf(State) and " +
				  "t.stereotype2=false and t.trigger.oclIsTypeOf(Trigger) and t.effect.oclIsTypeOf(OpaqueBehavior)) " 
				+ "and Class.allInstances()->size()=1 " 
				+ "and Trigger.allInstances()->forAll( t|t.event.oclIsTypeOf(TimeEvent)) " 
				+ "and Property.allInstances()->size()=1 " 
				+ "and StateMachine.allInstances()->size()=1 " 
				+ "and Region.allInstances()->size()=1 " 
				+ "and Transition.allInstances()->size()=1 " 
				+ "and Trigger.allInstances()->size()=1 " 
			//	+ "and OpaqueBehavior.allInstances()->size()=1 " 
				+ "and Pseudostate.allInstances()->select(p|p.stereotype1=false)->size()>1 "
		        + "and State.allInstances()->select(p|p.stereotype3=false)->size()>1"
		        ;

	    result1 = test(noOfRuns,noOfIterations, query, SearchAlgo, umlFile,objDiagramPath);
        try{
        	CopyFiles("../MotterTool_OCLSolver/all.log", "../MotterTool_OCLSolver/testQuery11.log");
        	String str = this.verifyResult(query,result1);
        	Assert.assertEquals("true", str);
        	if(str.equals("true"))
			MOFExperiment.retrieveTuppleForExperiment(result1, objDiagramFileName);
        	 
        }
        catch (Exception e) {
    		e.printStackTrace();
    	}
       
	}
	@SuppressWarnings("unused")
	@Test
	public void testQuery12()
	{     //
		String objDiagramFileName=objDiagram+"/OCLSolverResult12.txt";
		ArrayList<ClassifierTuple> result1 = null;
	    ClassDiagramTestData.getInstance().reset(umlFile);
	    	    
	    String query = "Class.allInstances()->forAll(c|c.ownedAttribute->forAll(o|o.oclIsTypeOf(Property)) and c.ownedBehavior->forAll(o| o.oclIsTypeOf(StateMachine)) and c.stereotype5=false and c.isActive=false) " 
	    		+ "and Property.allInstances()->forAll(p|p.stereotype4=false) " 
				+ "and StateMachine.allInstances()->forAll(sm|sm.region->forAll(r| r.oclIsTypeOf(Region))) "  
				+ "and Region.allInstances()->forAll(r|r.transition->forAll(t| t.oclIsTypeOf(Transition))) "
				+ "and Transition.allInstances()->forAll(t|t.source.oclIsTypeOf(Pseudostate) and t.target.oclIsTypeOf(State) and " +
				  "t.stereotype2=false and t.trigger.oclIsTypeOf(Trigger) and t.effect.oclIsTypeOf(OpaqueBehavior)) " 
				+ "and Class.allInstances()->size()=1 " 
				+ "and Trigger.allInstances()->forAll( t|t.event.oclIsTypeOf(TimeEvent)) " 
				+ "and Property.allInstances()->size()=1 " 
				+ "and StateMachine.allInstances()->size()=1 " 
				+ "and Region.allInstances()->size()=2 " 
				+ "and Transition.allInstances()->size()=1 " 
				+ "and Trigger.allInstances()->size()=1 " 
				+ "and StateMachine.allInstances()->forAll(sm|sm.region->size()>0) "  
				+ "and Pseudostate.allInstances()->select(p|p.stereotype1=false)->size()>1 "
		        + "and State.allInstances()->select(p|p.stereotype3=false)->size()>1"
		        ;

	    result1 = test(noOfRuns,noOfIterations, query, SearchAlgo, umlFile,objDiagramPath);
        try{
        	CopyFiles("../MotterTool_OCLSolver/all.log", "../MotterTool_OCLSolver/testQuery12.log");
        	String str = this.verifyResult(query,result1);
        	Assert.assertEquals("true", str);
        	if(str.equals("true"))
			MOFExperiment.retrieveTuppleForExperiment(result1, objDiagramFileName);
        	 
        }
        catch (Exception e) {
    		e.printStackTrace();
    	}
	}
/*	@SuppressWarnings("unused")
	@Test
	public void testQuery07()
	{     //GCI4 : if(stateMach.region.size() > 1)
		String objDiagramFileName=objDiagram+"/OCLSolverResult7.txt";
		ArrayList<ClassifierTuple> result1 = null;
	    ClassDiagramTestData.getInstance().reset(umlFile);
	    	    
	    String query = "Class.allInstances()->forAll(c|c.ownedAttribute->forAll(o|o.oclIsTypeOf(Property)) and c.ownedBehavior->forAll(o| o.oclIsTypeOf(StateMachine)) and c.stereotype5='Con') " 
	    		+ "and Property.allInstances()->forAll(p|p.stereotype4='NonDet') " 
				+ "and StateMachine.allInstances()->forAll(sm|sm.region->forAll(r| r.oclIsTypeOf(Region))) " 
			//	+ "and StateMachine.allInstances()->forAll(sm|sm.region->size()>1) " 
				+ "and Region.allInstances()->forAll(r|r.transition->forAll(t| t.oclIsTypeOf(Transition))) "
				+ "and Transition.allInstances()->forAll(t|t.source.oclIsTypeOf(Pseudostate) and t.target.oclIsTypeOf(State) and " +
				  "t.stereotype2='TimeProb') " 
				+ "and Class.allInstances()->size()=1 " 
				+ "and Property.allInstances()->size()=1 " 
				+ "and StateMachine.allInstances()->size()=1 " 
				+ "and Region.allInstances()->size()=1 " 
				+ "and Transition.allInstances()->size()=1 " 
				+ "and Pseudostate.allInstances()->select(p|p.stereotype1='NDChoice')->size()>1 "
		        + "and State.allInstances()->select(p|p.stereotype3='Failure')->size()>1"
		        ;

	    result1 = test(noOfRuns,noOfIterations, query, SearchAlgo, umlFile,objDiagramPath);
        try{

        	String str = this.verifyResult(query,result1);
        	Assert.assertEquals("true", str);
        	if(str.equals("true"))
			MOFExperiment.retrieveTuppleForExperiment(result1, objDiagramFileName);
        	CopyFiles("../MotterTool_OCLSolver/all.log", "../MotterTool_OCLSolver/testQuery7.log");
        	           
        }
        catch (Exception e) {
    		e.printStackTrace();
    	}
	}*/
	

}
