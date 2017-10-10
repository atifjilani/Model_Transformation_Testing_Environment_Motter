package org.questlab.motter.atl.driver;

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
import org.questlab.motter.atl.ATLASMGenerator;

import questlab.motter.atl.parser.Generator;


public class ATLDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ATLDriver d=new ATLDriver();
		if(args.length!=0){
			d.executeTransformation(args[0]);
		}
	

	}
	private ArrayList<String> logs = new ArrayList<String>();

	public ArrayList<String> executeTransformation(String InputModel) {
		try {
			
		//ArrayList<String> logs = new ArrayList<String>();
		System.out.println("\n ATL Transformation Started \n");
		//AtlModelHandler and ModelLoader init
		AtlModelHandler.registerDefaultHandler("EMF", new AtlEMFModelHandler());   		
    	AtlModelHandler amh = AtlModelHandler.getDefault("EMF");
    	ModelLoader ml = amh.createModelLoader();
    	
    	//stores metamodels and models
    	Map<String, ASMModel> models = new HashMap<String, ASMModel>();
    	//used to locate metamodels,models and trasformation file
    	URL url;
    	File file;
    	InputStream in;
    	//Models-MetaModels-Transformation Files
    	//MotterTool/SampleTransformation/SimpleClass2SimpleRDBMS/Blank Package.ecore
    	String SourceMetaModel="../MotterTool_ATLRunner/SampleTransformation/SimpleClass2SimpleRDBMS/Blank Package.ecore";
    	//String SourceMetaModel="../ATLExecutor/SampleTransformation/SimpleClass2SimpleRDBMS/SimpleClass.ecore";
    	String TargetMetaModel="../MotterTool_ATLRunner/SampleTransformation/SimpleClass2SimpleRDBMS/SimpleRDBMS.ecore";
    	//String InputModel="../ATLExecutor/SampleTransformation/SimpleClass2SimpleRDBMS/Sample-SimpleClass.ecore";
    	String OutputModel="../MotterTool_ATLRunner/ATLTransformationOutput/TransformedMy2.xmi";
    	//String ATLFile="../TestSimpleClass/SimpleClass2SimpleRDBMS/SimpleClass2SimpleRDBMS.atl";
    	String ATLFile="../MotterTool_ATLRunner/SampleTransformation/SimpleClass2SimpleRDBMS/SimpleClass2SimpleRDBMS.atl";
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
//					String name = clearName(arg0.getOpName()).trim();
//					if(!ATLTransformationLaunch.logs.contains(name)){
//						ATLTransformationLaunch.logs.add(name);
//					}
					//String ATLFile="../TestSimpleClass/SimpleClass2SimpleRDBMS/SimpleClass2SimpleRDBMS.atl";
					String ATLFile="../MotterTool_ATLRunner/SampleTransformation/SimpleClass2SimpleRDBMS/SimpleClass2SimpleRDBMS.atl";
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
		System.out.println("\n ATL Transformation Completed \n");
		return logs;
	}
}
