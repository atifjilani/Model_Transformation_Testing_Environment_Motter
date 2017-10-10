package org.questlab.motter.atl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;



import org.eclipse.m2m.atl.drivers.emf4atl.AtlEMFModelHandler; 
import org.eclipse.m2m.atl.engine.vm.ASMStackFrame;
import org.eclipse.m2m.atl.engine.vm.AtlLauncher;
import org.eclipse.m2m.atl.engine.vm.AtlModelHandler;
import org.eclipse.m2m.atl.engine.vm.Debugger;
import org.eclipse.m2m.atl.engine.vm.ModelLoader;
import org.eclipse.m2m.atl.engine.vm.StackFrame;
import org.eclipse.m2m.atl.engine.vm.nativelib.ASMModel;

import questlab.motter.CFG.CFG;
import questlab.motter.CFG.CFGATLDriverNew;
import questlab.motter.CFG.Condition;
import questlab.motter.CFG.DecisionNode;
import questlab.motter.CFG.Node;
import questlab.motter.CFG.conditionMap;
import questlab.motter.atl.parser.Generator;
import questlab.motter.mofscript.CFG.MOFMain;
import snt.oclsolver.distance.ClassDiagramTestData;
import snt.oclsolver.driver.SolverRunner;
import snt.oclsolver.reader.ObjScriptGenerator;
import snt.oclsolver.search.SearchAlgorithmEnum;
import snt.oclsolver.tuples.ClassifierTuple;
public class ATLTransformationLaunch {
    
	private ArrayList<String> logs = new ArrayList<String>();
	
    public static void main(String[] args) {
    	try {

    		String umlFile="OCLSolverInput/Blank Package.uml";		        	
    		ClassDiagramTestData.getInstance().reset(umlFile);
    		
			String query = "Class.allInstances()->select(c |c.is_persistent=true )-> size() > 0 "
//					     + "and Class.allInstances()->select(ca |ca.attrs->forAll(attr|attr.is_primary = true))->size() =1  "
					     + "and Association.allInstances()->size() > 1 "
					     + "and Class.allInstances()->size() > 0 "
//					     + "and PrimitiveDataType.allInstances()->size() > 0 "
					     + "and Classifier.allInstances()->size() > 0 "
//					     + "and Attribute.allInstances()->select(at : Attribute | at.type.oclIsTypeOf(SimpleClass::Class)) ";
//         		         + "and Attribute.allInstances()->select(at |at.is_primary=true ) -> size() = 5 ";
//					     +" and Attribute.allInstances()->forAll(at |at.type.oclIsTypeOf(Class)) "
//					     + "and Attribute.allInstances()->forAll(at |at.type.oclIsKindOf(PrimitiveDataType))->size() > 0 "
					     + "";

			
    		String objDiagramPath="OCLSolverOutput";
    		String objDiagramFileName="GenModels"+"/FromOCL_ATL_Input.xmi";
    		int noOfIterations=100;
    		int noOfRuns=1;
    		
    			ObjScriptGenerator driver =  new ObjScriptGenerator(umlFile);
    			driver.generateScript();
    			
    			SolverRunner exp = new SolverRunner();	
    			exp.setNoOfRuns(noOfRuns);
    			exp.setObjDiagramPath(objDiagramPath);
    			exp.setIndividualType("simple");
    			ArrayList<ClassifierTuple> result = exp.getValues(query.trim(), noOfIterations,SearchAlgorithmEnum.AVM);
    			
    			CreateSaveTester s=new CreateSaveTester();
    			s.retrieveTupple(result);
    			s.generateXmiObjectDiagram(objDiagramFileName);
    			
    			ATLTransformationLaunch launch= new ATLTransformationLaunch();
        		//String InputModel="../TestSimpleClass/SimpleClass2SimpleRDBMS/Sample-SimpleClass.ecore";
        		String InputModel=objDiagramFileName;
        		
        		
        		launch.printCFG(launch.executeTransformation(InputModel));
            	launch.CalculateFitness(launch.executeTransformation(InputModel));
  	
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    }
	
	public HashMap<String, conditionMap> executeTransformation(String InputModel) {
		try {
		System.out.println("\n MOTTER Execution Started");
		//AtlModelHandler and ModelLoader init
		AtlModelHandler.registerDefaultHandler("EMF", new AtlEMFModelHandler());   		
    	AtlModelHandler amh = AtlModelHandler.getDefault("EMF");
    	ModelLoader ml = amh.createModelLoader();
    	
    	//stores metamodels and models
    	Map models = new HashMap();
    	//used to locate metamodels,models and trasformation file
    	URL url;
    	File file;
    	InputStream in;
    	//Models-MetaModels-Transformation Files
    	//MotterTool/SampleTransformation/SimpleClass2SimpleRDBMS/Blank Package.ecore
    	String SourceMetaModel="../MotterTool/SampleTransformation/SimpleClass2SimpleRDBMS/Blank Package.ecore";
    	//String SourceMetaModel="../ATLExecutor/SampleTransformation/SimpleClass2SimpleRDBMS/SimpleClass.ecore";
    	String TargetMetaModel="../MotterTool/SampleTransformation/SimpleClass2SimpleRDBMS/SimpleRDBMS.ecore";
    	//String InputModel="../ATLExecutor/SampleTransformation/SimpleClass2SimpleRDBMS/Sample-SimpleClass.ecore";
    	String OutputModel="../MotterTool/ATLTransformationOutput/TransformedMy2.xmi";
    	//String ATLFile="../TestSimpleClass/SimpleClass2SimpleRDBMS/SimpleClass2SimpleRDBMS.atl";
    	String ATLFile="../MotterTool/SampleTransformation/SimpleClass2SimpleRDBMS/SimpleClass2SimpleRDBMS.atl";
    	//Compile ATL File and Return ASM File Location.
    	ATLASMGenerator g=new ATLASMGenerator();
    	String ATLASMFile=g.executeCompiler(ATLFile);
    	
    	
    	
    	file = new File(SourceMetaModel);
		in = new FileInputStream(file);
    	
		//load Families metamodel (the name is the same in the ATL file  [ex.: ATL CODE-> create OUT : Persons from IN : *Families*;])
    	//ASMModel metamodel_families = ml.loadModel("Families", ml.getMOF(), url.openStream());
		ASMModel metamodel_families = ml.loadModel("SimpleClass", ml.getMOF(), in);
		//ASMModel metamodel_families = ml.loadModel("SimpleClass", ml.getMOF(), in,);
		//store   
    	models.put("SimpleClass", metamodel_families);
    	//System.out.println("...\r...\r...\r...\r");
    	
    	//URL where Persons.ecore (output metamodel) is located
    	//url = new URL("file://../ATLExecutor/SampleTransformation/Families2Persons/Persons.ecore");
    	//load Persons metamodel (the name is the same in the ATL file  [ex.: ATL CODE-> create OUT : *Persons* from IN : Families;])
    	
    	file = new File(TargetMetaModel);
		in = new FileInputStream(file);

    	ASMModel metamodel_persons = ml.loadModel("SimpleRDBMS", ml.getMOF(), in);
    	//store 
    	models.put("SimpleRDBMS", metamodel_persons);
    	
    	//URL where sample-Families.xmi (input model) is located
    	//url = new URL("file://../ATLExecutor/SampleTransformation/Families2Persons/sample-Families.xmi");
    	//load Families model (the name is the same in the ATL file  [ex.: ATL CODE-> create OUT : Persons from *IN* : Families;])
    	file = new File(InputModel);
		in = new FileInputStream(file);

    	ASMModel model_families = ml.loadModel("IN", metamodel_families, in);
    	//store 
    	models.put("IN", model_families);
    	
    	//URL where sample-Persons.xmi (output model) is located
    	//url = new URL("file://../ATLExecutor/SampleTransformation/Families2Persons/Generated-Persons.xmi");
    	//create a new output model (the name is the same in the ATL file  [ex.: ATL CODE-> create *OUT* : Persons from IN : Families;])
    	
    	
    	ASMModel outputModel = ml.newModel("OUT", OutputModel, metamodel_persons);
    	//store 
    	models.put("OUT", outputModel);
    	
    	//URL where Families2Persons.asm (transformation .asm) is located
    	url = new URL("file","",ATLASMFile);
    	
    	//get an Atllauncher
    	//System.out.println(url.getPath()+" pro  "+url.getProtocol()+" host "+url.getHost());
    	
	
    	AtlLauncher mylauncher = AtlLauncher.getDefault();
    	//AtlLauncher.
    		    	
    	//execute the transformation (in this case we pass just the map "models")
    	//mylauncher.launch(url, Collections.EMPTY_MAP, models, Collections.EMPTY_MAP, Collections.EMPTY_LIST, Collections.EMPTY_MAP);
    	
    	////TEMP
    	Debugger debugger = new Debugger() {
			
			@Override
		public void step(ASMStackFrame arg0) {
				// TODO Auto-generated method stub 
				
				if(arg0.getSourceLocation() != null){
					String name = clearName(arg0.getOpName()).trim();
//					if(!ATLTransformationLaunch.logs.contains(name)){
//						ATLTransformationLaunch.logs.add(name);
//					}
					//String ATLFile="../TestSimpleClass/SimpleClass2SimpleRDBMS/SimpleClass2SimpleRDBMS.atl";
					String ATLFile="../MotterTool/SampleTransformation/SimpleClass2SimpleRDBMS/SimpleClass2SimpleRDBMS.atl";
					Generator gen = new Generator(ATLFile);
					try {
					//System.out.println(gen.getaline(""+arg0.geSourcetLocation(), ATLFile).toString());
						String temp = gen.getaline(arg0.getSourceLocation(), ATLFile).toString();
						//String temp = gen.getasingleline(arg0.getSourceLocation(), ATLFile).toString();
						if (arg0.getSourceLocation() != null) {
							String number = (arg0.getSourceLocation().split(":"))[0];
							temp = number + "#" + temp;
	//   		show code	            System.out.println(temp);
							if (!logs.contains(temp)) {
								logs.add(temp);
							}
						}
					} catch (Exception e) {
						System.out.print(e.getMessage());
					}
//					ASMOperation op = (ASMOperation) arg0.getOperation();
//					List<ASMInstruction> list = (List<ASMInstruction>) op.getInstructions();
//					for(int i=0; i<list.size(); i++){
//						//System.out.println(list.get(i).getClass().getSimpleName());
//						if(list.get(i).getClass().getSimpleName().equals("ASMInstructionWithOperand"))
//							System.out.println(list.get(i).getMnemonic()+" - "+((ASMInstructionWithOperand) list.get(i)).getOperand());
//					}
					//	System.out.println(arg0.nextInstruction().toString());
				}
				//System.out.println(arg0.toString()+"\n");
			}

			@Override
			public void enter(StackFrame arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void error(StackFrame arg0, String arg1, Exception arg2) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void leave(StackFrame arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void terminated() {
				// TODO Auto-generated method stub
				
			}
			
		};
    	///TEMP
    	mylauncher.launch(url, Collections.EMPTY_MAP, models, Collections.EMPTY_MAP, Collections.EMPTY_LIST, Collections.EMPTY_MAP, debugger);
    	
    	//save the outputModel in "file://C:/Documents and Settings/Administrator/workspace2/Families2Persons/sample-Persons.xmi"
    	outputModel.getModelLoader().save(outputModel, OutputModel);
    	
    	//once executed this file make a refresh on the destination folder
    	//System.out.println("Transformation Completed... Please refresh project to see output file");
    	for(int l=0; l<logs.size(); l++){
    		//System.out.println((l+1)+": "+logs.get(l));
    		//System.out.println(logs.+": "+logs.get(l));
    	}
		}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
		HashMap<String, conditionMap> nodeIds=cfgCompare();
		return nodeIds;
	}
	   
    public void printCFG(HashMap<String, conditionMap> nodeIds){
    	System.out.println("\nCGF Traversal of the Input Model  : \n");
    	int l=1;
    	for (String key : nodeIds.keySet()) {
    		System.out.print((l++)+": ");
    		conditionMap c=nodeIds.get(key);
    		c.print();
    	}
    }
    
    public ArrayList<String> printParentTree(String key,HashMap<String, conditionMap> conditionStatements){
        ArrayList<String> pList=new ArrayList<String>();
        CFGATLDriverNew d=new CFGATLDriverNew();
		CFG TP=d.funRulePersistentClass2Table();
			Node n = TP.BFS(key,1);
			
	//		TP.reInitializeIsVisisted(TP.getRootNode());
			conditionMap c=conditionStatements.get(key);
		if(c.getConditionStatus()!=true){
        if(getParentDecisionNode(n)==null){
        	pList.add("  ");
        }
        else{
        	pList.add(" -> "+getParentDecisionNode(n).getNodeId().split("#")[1].split(":")[0].toString());
            pList.addAll(printParentTree(getParentDecisionNode(n).getNodeId().split("#")[1].split(":")[0].toString(),conditionStatements));
        
        }
		}
		
			
    	return pList;
    }
    public  double calculateApproachLevel(String key,HashMap<String, conditionMap> conditionStatements ){
    	double appLevel=0.0;
    	conditionMap c=conditionStatements.get(key);
    	 if (!c.getParentConditionNode().equals("null")){
            // appLevel=1.0;
    		 if(conditionStatements.get(c.getParentConditionNode()).getConditionStatus()== false){
    			 appLevel=calculateApproachLevel(c.getParentConditionNode(), conditionStatements);
        		 appLevel++; 
    		 }
    	 }
    	return appLevel;
    }
    
    public  double CalculateFitness(HashMap<String, conditionMap> conditionStatements){
    	double approach=0.0;
    	for (String key : conditionStatements.keySet()) {
    		conditionMap c=conditionStatements.get(key);
    		if(!c.getConditionStatus()){ // find target nodes which are not yet covered
    			approach=calculateApproachLevel(key, conditionStatements);
    			System.out.print("App-Level of "+key+"  = "+approach+"  :  ");
    			for(String u : printParentTree(key,conditionStatements)){
    				System.out.print( u );	
    			}
    			System.out.print("\n");
    			}
    		}    	
    	return approach;
    }
    public  String clearName(String name){
    	if(name.contains("__match")){
    		return name.replace("__match", "");
    	}else if(name.contains("__init")){
    		return name.replace("__init", "");
    	}else if(name.contains("__apply")){
    		return name.replace("__apply", "");
    	}
    	return name;
    }
    
    public  Node getParentDecisionNode(Node n){
    	Node PNode=new Node();
    	n=n.getParentNode();
    	if(n.getParentNode()!=null){
    	
    	if(n.getNodeId().contains(":")){///specific node DecisionNode or Comp
			//String nodeNumber=n.getNodeId().split(":")[0].split("#")[1];
			if(n.getNodeId().split(":")[1].endsWith("DecisionNode"))
				PNode =n;
			else
				PNode=getParentDecisionNode(n);
			}
    	else{
    		PNode=getParentDecisionNode(n);
    	}
		return PNode;
    	}
    	return null;
    }
    
    public  HashMap<String, conditionMap> cfgCompare(){
    	CFGATLDriverNew d=new CFGATLDriverNew();
		CFG TP=d.funRulePersistentClass2Table();
		ArrayList<String> nodeIds = new ArrayList<String>();
		for(int l=0; l<logs.size(); l++){
    		//System.out.println((l+1)+": "+logs.get(l));
    		Node n = TP.BFS(logs.get(l));
    		if(n != null){
    			//System.out.println(logs.get(l)+" GOESTO "+n.getAtlCode());
    			//if(nodeIds.contains(n.getNodeId()))
    			if(nodeIds.size() != 0){
    				//String s=nodeIds.get((nodeIds.size() - 1));
    				//String ss=n.getNodeId();
    				if(!nodeIds.get((nodeIds.size() - 1)).equals(n.getNodeId()))
    					nodeIds.add(n.getNodeId());
    			}else
    				nodeIds.add(n.getNodeId());
    			//System.out.println("Found-"+(l+1)+": "+logs.get(l));
    		}
    		//else 
  			//System.out.println("NotFound: "+(l+1)+": "+logs.get(l));//not found
    		//System.out.println("NotFound "+logs.get(l));//not found
    		TP.reInitializeIsVisisted(TP.getRootNode());
		}
		 
		HashMap<String, conditionMap> conList=new HashMap<String, conditionMap>();//=new ArrayList<>();
		for(int l=0; l<nodeIds.size(); l++){
			//System.out.println((l+1)+": "+nodeIds.get(l));
			Node n = TP.BFS(nodeIds.get(l), true);
			//System.out.println(n.getNodeId()+" ****** "+n.getAtlCode()+"\n");  //visitedNode
			conditionMap cm1=new conditionMap();
			//ArrayList<conditionMap> conList;
			
			if(n.getNodeId().contains(":")){///specific node DecisionNode or Comp
				String nodeNumber=n.getNodeId().split(":")[0].split("#")[1];
				  
				if(n.getNodeId().split(":")[1].endsWith("DecisionNode")){
					DecisionNode ddec=(DecisionNode)n;
					Condition cond=ddec.getcondition();
					String decStatement=cond.getDecstatement();
					Node pNode=getParentDecisionNode(n);
					cm1.setDecNode(n);
					cm1.setConditionNodeNumber(nodeNumber);
					cm1.setConditionValue(decStatement);
					cm1.setApproachLevel(0);
					if(pNode!=null){
					cm1.setParentConditionNode(pNode.getNodeId().split(":")[0].split("#")[1]);
					cm1.setParentCondition(pNode);
					}
					else{
						cm1.setParentConditionNode("null");
						cm1.setParentCondition(new Node("null", ""));
					}	
					conList.put(nodeNumber,cm1);
				}
				if(n.getNodeId().split(":")[1].endsWith("then")){
					conditionMap thcon=conList.get(n.getNodeId().split(":")[2]);
					if(thcon!=null){
						String thenNode=n.getNodeId().split(":")[0].split("#")[1];
						thcon.setConditionStatus(true);
						thcon.setThenNodeNumber(thenNode);
						thcon.setNodeThen(n);
						conList.put(n.getNodeId().split(":")[2], thcon);
					}
					
				}
				if(n.getNodeId().split(":")[1].endsWith("else")){
					conditionMap thcon=conList.get(n.getNodeId().split(":")[2]);
					if(thcon!=null){
						String elseNode=n.getNodeId().split(":")[0].split("#")[1];
						thcon.setConditionStatus(false);
						thcon.setElseNodeNumber(elseNode);
						thcon.setNodeElse(n);
						conList.put(n.getNodeId().split(":")[2], thcon);
					}
				}				
			}
			//conList.add(cm1);
			TP.reInitializeIsVisisted(TP.getRootNode());
    	}
		return conList;
    }
   
}
