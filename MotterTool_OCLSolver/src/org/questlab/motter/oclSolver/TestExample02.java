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

public class TestExample02 extends AbstractTestCase{
	
	String umlFile = "../MotterTool_OCLSolver/OCLSolverInput/Blank Package.uml";
	int noOfIterations=1000;
	int noOfRuns=1;
	SearchAlgorithmEnum SearchAlgo=SearchAlgorithmEnum.AVM;
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
		String objDiagramFileName=objDiagram+"/OCLSolverATLResult1.txt";
		ArrayList<ClassifierTuple> result1 = null;
	    ClassDiagramTestData.getInstance().reset(umlFile);
	    	    
	    String query = "Class.allInstances()-> size() = 4 "
			     + "and PrimitiveDataType.allInstances()->size() = 1 " 
			     + "and Attribute.allInstances()->size() = 2 " 
			     + "and Association.allInstances()->size() = 1 "
			     + "and PrimitiveDataType.allInstances()->forAll(c|c.name='String') "
				 + "and Class.allInstances()->forAll(c|c.is_persistent=true and c.attrs->forAll(attr |attr.oclIsTypeOf(Attribute))) "
			     + "and Attribute.allInstances()->forAll(at | at.is_primary=true and at.type.oclIsKindOf(Classifier)) "
				 + "and Association.allInstances()->forAll(as | as.src.oclIsTypeOf(Class) and as.dest.oclIsTypeOf(Class)) "
	//			 + "and Class.allInstances()->select(c | c.parent.oclIsTypeOf(Class))-> size() = 1"
	//			 + "and Class.allInstances()->exists(c | c.parent.oclIsTypeOf(Class))"
				;

	    result1 = test(noOfRuns,noOfIterations, query, SearchAlgo, umlFile,objDiagramPath);
        try{
        	CopyFiles("../MotterTool_OCLSolver/all.log", "../MotterTool_OCLSolver/testQueryATL1.log");
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
		
		String objDiagramFileName=objDiagram+"/OCLSolverATLResult2.txt";
		ArrayList<ClassifierTuple> result1 = null;
	    ClassDiagramTestData.getInstance().reset(umlFile);
	    	    
	    String query = "Class.allInstances()-> size() = 4 "
			     + "and PrimitiveDataType.allInstances()->size() = 1 " 
			     + "and Attribute.allInstances()->size() = 2 " 
			     + "and Association.allInstances()->size() = 2 "
			     + "and PrimitiveDataType.allInstances()->forAll(c|c.name='String') "
				 + "and Class.allInstances()->forAll(c|c.is_persistent=true and c.attrs->forAll(attr |attr.oclIsTypeOf(Attribute))) "
			     + "and Attribute.allInstances()->select(at | at.is_primary=true and at.type.oclIsKindOf(Classifier))->size()=1 "
				 + "and Association.allInstances()->forAll(as | as.src.oclIsTypeOf(Class) and as.dest.oclIsTypeOf(Class)) "
	//			 + "and Class.allInstances()->select(c | c.parent.oclIsTypeOf(Class))-> size() = 1"
	//			 + "and Class.allInstances()->exists(c | c.parent.oclIsTypeOf(Class))"
				;

	    result1 = test(noOfRuns,noOfIterations, query, SearchAlgo, umlFile,objDiagramPath);
        try{
        	CopyFiles("../MotterTool_OCLSolver/all.log", "../MotterTool_OCLSolver/testQuery2.log");
        	String str = this.verifyResult(query,result1);
        	Assert.assertEquals("true", str);
        	if(str.equals("true"))
			MOFExperiment.retrieveTuppleForExperiment(result1, objDiagramFileName);
       	   // CopyFiles("../MotterTool_OCLSolver/all.log", "../MotterTool_OCLSolver/testQueryATL2.log");
           
        }
        catch (Exception e) {
    		e.printStackTrace();
    	}
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testQuery03()
	{     //GCF10 : if(ps.getAppliedStereotype(ST_ND_CHOICE) != null)
		String objDiagramFileName=objDiagram+"/OCLSolverATLResult3.txt";
		ArrayList<ClassifierTuple> result1 = null;
	    ClassDiagramTestData.getInstance().reset(umlFile);
	    	    
	    String query = "Class.allInstances()-> size() >1 "
			     + "and PrimitiveDataType.allInstances()->size() = 1 " 
			     + "and Attribute.allInstances()->size() = 2 " 
			     + "and Association.allInstances()->size() = 1 "
			     + "and PrimitiveDataType.allInstances()->forAll(c|c.name='String') "
				 + "and Class.allInstances()->forAll(c|c.is_persistent=true and c.attrs->forAll(attr |attr.oclIsTypeOf(Attribute))) "
			     + "and Attribute.allInstances()->select(at | at.is_primary=true and at.type.oclIsKindOf(Class))->size()=1 "
				 + "and Association.allInstances()->forAll(as | as.src.oclIsTypeOf(Class) and as.dest.oclIsTypeOf(Class)) "
	//			 + "and Class.allInstances()->select(c | c.parent.oclIsTypeOf(Class))-> size() = 1"
	//			 + "and Class.allInstances()->exists(c | c.parent.oclIsTypeOf(Class))"
				;

	    result1 = test(noOfRuns,noOfIterations, query, SearchAlgo, umlFile,objDiagramPath);
        try{
        	CopyFiles("../MotterTool_OCLSolver/all.log", "../MotterTool_OCLSolver/testQueryATL3.log");
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
	{     //GCF10 : if(ps.getAppliedStereotype(ST_ND_CHOICE) != null)
		String objDiagramFileName=objDiagram+"/OCLSolverATLResult4.txt";
		ArrayList<ClassifierTuple> result1 = null;
	    ClassDiagramTestData.getInstance().reset(umlFile);
	    	    
	    String query = "Class.allInstances()-> size() =8 "
			     + "and PrimitiveDataType.allInstances()->size() = 1 " 
			     + "and Attribute.allInstances()->size() = 2 " 
			     + "and Association.allInstances()->size() = 1 "
			     + "and PrimitiveDataType.allInstances()->forAll(c|c.name='String') "
				 + "and Class.allInstances()->forAll(c|c.is_persistent=true and c.attrs->forAll(attr |attr.oclIsTypeOf(Attribute))) "
			     + "and Attribute.allInstances()->select(at | at.is_primary=true and at.type.oclIsKindOf(Class))->size()=1 "
				 + "and Association.allInstances()->forAll(as | as.src.oclIsTypeOf(Class) and as.dest.oclIsTypeOf(Class)) "
				 + "and Class.allInstances()->select(c | c.parent.oclIsTypeOf(Class))-> size() >0"
	//			 + "and Class.allInstances()->exists(c | c.parent.oclIsTypeOf(Class))"
				;

	    result1 = test(noOfRuns,noOfIterations, query, SearchAlgo, umlFile,objDiagramPath);
        try{
        	CopyFiles("../MotterTool_OCLSolver/all.log", "../MotterTool_OCLSolver/testQueryATL4.log");
        	String str = this.verifyResult(query,result1);
        	Assert.assertEquals("true", str);
        	if(str.equals("true"))
			MOFExperiment.retrieveTuppleForExperiment(result1, objDiagramFileName);
       	    
          
        }
        catch (Exception e) {
    		e.printStackTrace();
    	}
	}

}
