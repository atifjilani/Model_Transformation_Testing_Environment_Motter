package org.questlab.motter.atl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;



import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.m2m.atl.core.ATLCoreException;
import org.eclipse.m2m.atl.core.IExtractor;
import org.eclipse.m2m.atl.core.IInjector;
import org.eclipse.m2m.atl.core.IModel;
import org.eclipse.m2m.atl.core.IReferenceModel;
import org.eclipse.m2m.atl.core.ModelFactory;
import org.eclipse.m2m.atl.core.emf.EMFExtractor;
import org.eclipse.m2m.atl.core.emf.EMFInjector;
import org.eclipse.m2m.atl.core.emf.EMFModel;
import org.eclipse.m2m.atl.core.emf.EMFModelFactory;
import org.eclipse.m2m.atl.core.emf.EMFReferenceModel;
import org.eclipse.m2m.atl.core.launch.ILauncher;
import org.eclipse.m2m.atl.engine.emfvm.launch.EMFVMLauncher;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.uml2.uml.UMLPackage;

public class RunTransfoJava {

	public static void main(String[] args) {
		try {
			System.out.println("Running...");

			/*
			 * Paths 
			 */
			//String inModelPath = "../TestOCL/OutputFile/Object_Diagram_00.uml";
			String inModelPath = "../ATLExecutor/OCLSolverOutput/OCLOutput.xmi";
			String outModelPath = "../ATLExecutor/OCLSolverOutput/TransformedMy2.xmi";
			String InMetamodelPath = "../TestOCL/example/Blank Package.ecore";
			String OutMetamodelPath = "../ATLExecutor/SampleTransformation/SimpleClass2SimpleRDBMS/SimpleRDBMS.ecore";                	

			System.out.println("inModelPath: " + inModelPath);
			System.out.println("outModelPath: " + outModelPath);
			System.out.println("InputMetamodelPath: " + InMetamodelPath);
			System.out.println("outPutMetamodelPath: " + OutMetamodelPath);

			/*
			 * Initializations
			 */
			ILauncher transformationLauncher = new EMFVMLauncher();
			ModelFactory modelFactory = new EMFModelFactory();
			IInjector injector = new EMFInjector();
			IExtractor extractor = new EMFExtractor();

			/*
			 * Register Metamodel
			 */
			//Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
			EPackage.Registry.INSTANCE.put("http://www.eclipse.org/uml2/3.0.0/UML",UMLPackage.eINSTANCE);
			
			
			/*
			 * Load metamodels
			 */
			
			
			IReferenceModel drdMetamodel = modelFactory.newReferenceModel();
			//injector.inject(drdMetamodel, "http://www.eclipse.org/uml2/4.0.0/UML");
			injector.inject(drdMetamodel,InMetamodelPath);

			IReferenceModel aiMetamodel = modelFactory.newReferenceModel();
			injector.inject(aiMetamodel, OutMetamodelPath);;
			System.out.println("Metamodels loaded.");

			/*
			 * Load models and run transformation
			 */
			IModel inModel = modelFactory.newModel(drdMetamodel);
			injector.inject(inModel,inModelPath);
			IModel outModel = modelFactory.newModel(aiMetamodel);
			System.out.println("IN, OUT models loaded.");

			System.out.print("Running ATL trasformation...");
			transformationLauncher.initialize(new HashMap<String,Object>());
			transformationLauncher.addInModel(inModel, "IN", "SimpleClass");
			transformationLauncher.addOutModel(outModel, "OUT", "SimpleRDBMS");

			transformationLauncher.launch(ILauncher.RUN_MODE, new NullProgressMonitor(), new HashMap<String,Object>(),
					new FileInputStream("../ATLExecutor/SampleTransformation/SimpleClass2SimpleRDBMS/SimpleClass2SimpleRDBMS.asm"));
			System.out.println("Done.");

			System.out.print("Extracting OUT model...");
			extractor.extract(outModel, outModelPath);
			System.out.println("Done.");

			/*
			 * Unload all models and metamodels (EMF-specific)
			 */
			EMFModelFactory emfModelFactory = (EMFModelFactory) modelFactory;
			emfModelFactory.unload((EMFModel) inModel);
			emfModelFactory.unload((EMFModel) outModel);
			emfModelFactory.unload((EMFReferenceModel) drdMetamodel);
			emfModelFactory.unload((EMFReferenceModel) aiMetamodel);
			System.out.println("Done.");
		} catch (ATLCoreException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}