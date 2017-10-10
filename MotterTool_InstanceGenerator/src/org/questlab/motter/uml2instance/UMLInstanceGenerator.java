package org.questlab.motter.uml2instance;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.AnyReceiveEvent;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.ChangeEvent;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.Feature;
import org.eclipse.uml2.uml.FinalState;
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Pseudostate;
import org.eclipse.uml2.uml.PseudostateKind;
import org.eclipse.uml2.uml.Reception;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.SignalEvent;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.TimeExpression;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.TransitionKind;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.uml2.uml.Vertex;
import org.eclipse.uml2.uml.VisibilityKind;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.eclipse.uml2.uml.util.UMLUtil;
import org.questlab.motter.uml2instance.ProfiledStateMachine.MessageEventkind;


import SimpleClass.*;
import SimpleClass.Class;

public class UMLInstanceGenerator {
	
	public ArrayList<List<Object>> QueryTuppleMap;
	public Profile profile;

	/**
	 * @param args
	 */
    
	public static void main(String[] args) {
		// Initialize the model

		SimpleClassPackage.eINSTANCE.eClass();
		// Retrieve the default factory singleton
		SimpleClassFactory factory = SimpleClassFactory.eINSTANCE;

		// create the content of the model by creating classes

		Class c1 = factory.createClass();
		c1.setName("Student");
		c1.setIs_persistent(true);

		// Adding primitive Types
		PrimitiveDataType integerPrimitive = factory.createPrimitiveDataType();
		integerPrimitive.setName("Integer");
		PrimitiveDataType StringPrimitive = factory.createPrimitiveDataType();
		StringPrimitive.setName("String");

		// Adding Attributes
		Attribute a1 = factory.createAttribute();
		a1.setName("Name");
		a1.setIs_primary(true);
		a1.setType(StringPrimitive);

		Attribute a2 = factory.createAttribute();
		a2.setName("Address");
		a2.setIs_primary(true);
		a2.setType(integerPrimitive);
		c1.getAttrs().add(a1);
		c1.getAttrs().add(a2);

		Class c2 = factory.createClass();
		c2.setName("Human");
		c2.setIs_persistent(true);
		c2.getAttrs().add(a1);

		c1.setParent(c2);
		// As of here we preparing to save the model content

		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("xmi", new XMIResourceFactoryImpl());

		// Obtain a new resource set
		ResourceSet resSet = new ResourceSetImpl();

		// create a resource
		Resource resource = resSet.createResource(URI
				.createURI("../ATLExecutor/OCLSolverOutput/My2.xmi"));
		// Get the first model element and cast it to the right type, in my
		// example everything is hierarchical included in this first node
		resource.getContents().add(c1);
		resource.getContents().add(c2);
		resource.getContents().add(StringPrimitive);
		resource.getContents().add(integerPrimitive);

		// now save the content.
		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void generateXmiObjectDiagram(String fileName) {

		// fileName="../ATLExecutor/OCLSolverOutput/My2.xmi";
		// Initialize the model
		SimpleClassPackage.eINSTANCE.eClass();
		// Retrieve the default factory singleton
		SimpleClassFactory factory = SimpleClassFactory.eINSTANCE;
		// As of here we preparing to save the model content
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("xmi", new XMIResourceFactoryImpl());

		// Obtain a new resource set
		ResourceSet resSet = new ResourceSetImpl();

		// create a resource
		Resource resource = resSet.createResource(URI.createURI(fileName));
		
		HashMap<String, Object> resourcesToAdd = new HashMap<String, Object>();
		HashMap<String, List<Object>> resourcesListToAdd = new HashMap<String, List<Object>>();
		HashMap<String, Boolean> shouldResourcesAdd = new HashMap<String, Boolean>();

		for (List<Object> values : QueryTuppleMap) {
			if (values.get(0).toString().equals("Class")) {
				if (!resourcesToAdd.containsKey(values.get(1).toString())) {
				Class c1 = factory.createClass();
				c1.setName(values.get(1).toString());
				if(values.size()>2){
					if (values.get(3).toString().equals("name")){
						c1.setName(values.get(4).toString());
						}
					if (values.get(3).toString().equals("is_persistent")){
							c1.setIs_persistent( Boolean.parseBoolean((String) values.get(4)));
						}
					}
				resourcesToAdd.put(values.get(1).toString(), c1);
				resourcesListToAdd.put(values.get(1).toString(), values);
				shouldResourcesAdd.put(values.get(1).toString(), false);
			}}
			if (values.get(0).toString().equals("Association")) {
				if (!resourcesToAdd.containsKey(values.get(1).toString())) {
				Association a1 = factory.createAssociation();
				a1.setName(values.get(1).toString());
				if(values.size()>2)
				if (values.get(3).toString().equals("name")){
					a1.setName(values.get(4).toString());
				}
			
				resourcesToAdd.put(values.get(1).toString(), a1);
				resourcesListToAdd.put(values.get(1).toString(), values);
				shouldResourcesAdd.put(values.get(1).toString(), false);
			}}
			if (values.get(0).toString().equals("Attribute")) {
				if (!resourcesToAdd.containsKey(values.get(1).toString())) {
				Attribute at1 = factory.createAttribute();
				at1.setName(values.get(1).toString());
				if(values.size()>2){
				if (values.get(3).toString().equals("is_primary")){
					at1.setIs_primary(Boolean.parseBoolean((String) values.get(4)));
					}
				if (values.get(3).toString().equals("name")){
						at1.setName(values.get(4).toString());
					}
				}
				resourcesToAdd.put(values.get(1).toString(), at1);
				resourcesListToAdd.put(values.get(1).toString(), values);
				shouldResourcesAdd.put(values.get(1).toString(), false);// ///////////////////
			}}
			if (values.get(0).toString().equals("PrimitiveDataType")) {
				if (!resourcesToAdd.containsKey(values.get(1).toString())) {
				PrimitiveDataType pt1 = factory.createPrimitiveDataType();
				pt1.setName(values.get(1).toString());
				if(values.size()>2)
					if (values.get(3).toString().equals("name")){
						pt1.setName(values.get(4).toString());
					}
				resourcesToAdd.put(values.get(1).toString(), pt1);
				resourcesListToAdd.put(values.get(1).toString(), values);
				shouldResourcesAdd.put(values.get(1).toString(), true);

			}}

		}
		createAllXMIClassRelationships(resourcesToAdd, shouldResourcesAdd);
		String[] tempTags = new String[] { "Class","Association" };
		for(String tt : tempTags){
			createAllXMIClassRelationships(resourcesToAdd,shouldResourcesAdd, tt);
		} 
		
		for (Map.Entry<String, Boolean> entry : shouldResourcesAdd.entrySet()) {
			//System.out.println("Key : " + entry.getKey() + " Value : "+ entry.getValue());
			if ((Boolean) shouldResourcesAdd.get(entry.getKey())) {
				EObject e = (EObject) resourcesToAdd.get(entry.getKey());
				resource.getContents().add(e);
			}

		}
		System.out.println("OCL Generated XMI file at location :" + fileName
				+ " is sucessfully created.");
		// now save the content.
		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	public void generateUMLStateMachine(String fileName, String path) {
		System.out.println("\n-----------UML Instance Generation Started---------\n");                                                 
				
		ProfiledStateMachine SM = new ProfiledStateMachine();
		//Create Model
		Model epo2Model = SM.createModel("GeneratedSM");
		
		//Load Profiles
		Profile profile1=SM.loadApplyStandardProfile(UMLResource.STANDARD_PROFILE_URI,epo2Model);
		Profile profile2=SM.loadApplyProfile(path+"/Default.profile.uml",epo2Model);
		//Profile profile3=SM.loadApplyProfile(path+"/Deployment.profile.uml",epo2Model);
	    profile=SM.loadApplyProfile(path+"/Profile.profile.uml",epo2Model);
	   
		SM.importPrimitivePackage(epo2Model,UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI);
		
		//Generate UML Primitive Type
		PrimitiveType stringPrimitiveType = SM.createPrimitiveType("String");
		PrimitiveType intPrimitiveType = SM.createPrimitiveType("Integer");
		PrimitiveType boolPrimitiveType = SM.createPrimitiveType("Boolean");

		HashMap<String, Object> resourcesToAdd = new HashMap<String, Object>();
		//iterate each of the tupple
//		for (List<Object> values : QueryTuppleMap) {
//			System.out.println("Values = " + values + ".....n");
//		}

		for (List<Object> values : QueryTuppleMap) {
			// 1
			if (values.get(0).toString().equals("Class")) {
				if (!resourcesToAdd.containsKey(values.get(1).toString())) {
					org.eclipse.uml2.uml.Class c1 = SM.createClass(epo2Model,
							values.get(1).toString(), false, false,
							org.eclipse.uml2.uml.VisibilityKind.PUBLIC_LITERAL,
							null);
					//SM.createOperation(c1, c1.getName(),VisibilityKind.PUBLIC_LITERAL);
					if (values.size() > 2) {
						if (values.get(3).toString().equals("name")) {
							if (!values.get(4).toString().equals(""))
								c1.setName(values.get(4).toString());
						}
						if (values.get(3).toString().equals("stereotype5")) {
							if (values.size() > 4)
							if (!values.get(4).toString().equals("")) {
								String StereoName="";
								if(values.get(4).toString().equals("Sys"))
									StereoName="System";
								if(values.get(4).toString().equals("Con"))
									StereoName="Context";
								Stereotype stereo= SM.retrieveStereoType(profile, StereoName);
							    SM.applyStereotype(c1, stereo);
							}
						}
						if (values.get(3).toString().equals("isActive"))
							c1.setIsActive(Boolean.parseBoolean((String) values.get(4)));
						if (values.get(3).toString().equals("isAbstract"))
							c1.setIsAbstract(Boolean.parseBoolean((String) values.get(4)));
						// if (values.get(3).toString().equals("visibility"))
						// c1.setVisibility((VisibilityKind) values.get(4));
					}
					resourcesToAdd.put(values.get(1).toString(), c1);
				}
			}// 2
			if (values.get(0).toString().equals("Package")) {
				if (!resourcesToAdd.containsKey(values.get(1).toString())) {
					org.eclipse.uml2.uml.Package c1 = SM.createPackage(
							epo2Model, values.get(1).toString());
					if (values.size() > 2) {
						if (values.get(3).toString().equals("name")) {
							if (!values.get(4).toString().equals(""))
								c1.setName(values.get(4).toString());
						}
						// if (values.get(3).toString().equals("visibility"))
						// c1.setVisibility((VisibilityKind) values.get(4));
					}
					resourcesToAdd.put(values.get(1).toString(), c1);
				}
			}// 3
				// if (values.get(0).toString().equals("PrimitiveDataType")) {
			// if (!resourcesToAdd.containsKey(values.get(1).toString())) {
			// org.eclipse.uml2.uml.PrimitiveType c1 = SM
			// .createPrimitiveType(values.get(1).toString());
			// if (values.get(3).toString().equals("name"))
			// c1.setName(values.get(4).toString());
			// resourcesToAdd.put(values.get(1).toString(), c1);
			// resourcesListToAdd.put(values.get(1).toString(), values);
			// shouldResourcesAdd.put(values.get(1).toString(), false);
			// }}// 4
			if (values.get(0).toString().equals("Signal")) {
				if (!resourcesToAdd.containsKey(values.get(1).toString())) {
					org.eclipse.uml2.uml.Signal c1 = SM.createSignal(values
							.get(1).toString(), epo2Model);
					if (values.size() > 2) {
						if (values.get(3).toString().equals("name")) {
							if (!values.get(4).toString().equals(""))
								c1.setName(values.get(4).toString());
						}
						// if (values.get(3).toString().equals("visibility"))
						// c1.setVisibility((VisibilityKind) values.get(4));
					}
					resourcesToAdd.put(values.get(1).toString(), c1);
					// resourcesListToAdd.put(values.get(1).toString(), values);
					// shouldResourcesAdd.put(values.get(1).toString(), false);
				}
			}// 5
			if (values.get(0).toString().equals("Reception")) {
				if (!resourcesToAdd.containsKey(values.get(1).toString())) {
					org.eclipse.uml2.uml.Reception c1 = SM.createReception(
							values.get(1).toString(), null, null);
					resourcesToAdd.put(values.get(1).toString(), c1);
					// resourcesListToAdd.put(values.get(1).toString(), values);
					// shouldResourcesAdd.put(values.get(1).toString(), false);
				}
			}// 6
			if (values.get(0).toString().equals("OpaqueExpression")) {
				if (!resourcesToAdd.containsKey(values.get(1).toString())) {
					org.eclipse.uml2.uml.OpaqueExpression c1 = SM
							.createOpaqueExpression(values.get(1).toString(),
									"OCL", "self.isDefault");
					if (values.size() > 2) {
						if (values.get(3).toString().equals("language"))
							c1.getLanguages().add(values.get(4).toString());
						if (values.get(3).toString().equals("body"))
							c1.getLanguages().add(values.get(4).toString());
						if (values.get(3).toString().equals("name")) {
							if (!values.get(4).toString().equals(""))
								c1.setName(values.get(4).toString());
						}
					}
					resourcesToAdd.put(values.get(1).toString(), c1);
					// resourcesListToAdd.put(values.get(1).toString(), values);
					// shouldResourcesAdd.put(values.get(1).toString(), false);
				}
			}//6A
			if (values.get(0).toString().equals("LiteralString")) {
				if (!resourcesToAdd.containsKey(values.get(1).toString())) {
					org.eclipse.uml2.uml.LiteralString c1 = SM.createLiteralString(values.get(1).toString(),"after, 5s");
					if (values.size() > 2) {
						if (values.get(3).toString().equals("value"))
							c1.setValue(values.get(4).toString());
						
					}
					resourcesToAdd.put(values.get(1).toString(), c1);
					// resourcesListToAdd.put(values.get(1).toString(), values);
					// shouldResourcesAdd.put(values.get(1).toString(), false);
				}
			}// 7
			if (values.get(0).toString().equals("AnyRecieveEvent")) {
				if (!resourcesToAdd.containsKey(values.get(1).toString())) {
					org.eclipse.uml2.uml.AnyReceiveEvent c1 = (AnyReceiveEvent) SM
							.createMessageEvent(values.get(1).toString(),
									epo2Model, MessageEventkind.AnyReciveEvent,
									null, null);
					if (values.size() > 2) {
						if (values.get(3).toString().equals("name")) {
							if (!values.get(4).toString().equals(""))
								c1.setName(values.get(4).toString());
						}
					}
					// if (values.get(3).toString().equals("visibility"))
					// c1.setVisibility((VisibilityKind) values.get(4));
					resourcesToAdd.put(values.get(1).toString(), c1);
					// resourcesListToAdd.put(values.get(1).toString(), values);
					// shouldResourcesAdd.put(values.get(1).toString(), false);
				}
			}// 8
			if (values.get(0).toString().equals("SignalEvent")) {
				if (!resourcesToAdd.containsKey(values.get(1).toString())) {
					org.eclipse.uml2.uml.SignalEvent c1 = (SignalEvent) SM
							.createMessageEvent(values.get(1).toString(),
									epo2Model, MessageEventkind.SignalEvent,
									null, null);
					if (values.size() > 2) {
						if (values.get(3).toString().equals("name")) {
							if (!values.get(4).toString().equals(""))
								c1.setName(values.get(4).toString());
						}
					}
					// if (values.get(3).toString().equals("visibility"))
					// c1.setVisibility((VisibilityKind) values.get(4));
					resourcesToAdd.put(values.get(1).toString(), c1);
					// resourcesListToAdd.put(values.get(1).toString(), values);
					// shouldResourcesAdd.put(values.get(1).toString(), false);
				}
			}// 9
			if (values.get(0).toString().equals("CallEvent")) {
				if (!resourcesToAdd.containsKey(values.get(1).toString())) {
					org.eclipse.uml2.uml.CallEvent c1 = (CallEvent) SM
							.createMessageEvent(values.get(1).toString(),
									epo2Model, MessageEventkind.CallEvent,
									null, null);
					if (values.size() > 2) {
						if (values.get(3).toString().equals("name")) {
							if (!values.get(4).toString().equals(""))
								c1.setName(values.get(4).toString());
						}
						// if (values.get(3).toString().equals("visibility"))
						// c1.setVisibility((VisibilityKind) values.get(4));
					}
					resourcesToAdd.put(values.get(1).toString(), c1);
					// resourcesListToAdd.put(values.get(1).toString(), values);
					// shouldResourcesAdd.put(values.get(1).toString(), false);
				}
			}// 10
			if (values.get(0).toString().equals("Operation")) {
				if (!resourcesToAdd.containsKey(values.get(1).toString())) {
					org.eclipse.uml2.uml.Operation c1 = SM.createOperation(
							null, values.get(1).toString(),
							VisibilityKind.PUBLIC_LITERAL);
					if (values.size() > 2) {
						if (values.get(3).toString().equals("name")) {
							if (!values.get(4).toString().equals(""))
								c1.setName(values.get(4).toString());
						}
						// if (values.get(3).toString().equals("visibility"))
						// c1.setVisibility((VisibilityKind) values.get(4));
					}
					resourcesToAdd.put(values.get(1).toString(), c1);
					// resourcesListToAdd.put(values.get(1).toString(), values);
					// shouldResourcesAdd.put(values.get(1).toString(), false);
				}
			}// 11
			if (values.get(0).toString().equals("TimeExpression")) {
				if (!resourcesToAdd.containsKey(values.get(1).toString())) {
					org.eclipse.uml2.uml.TimeExpression c1 = SM
							.createTimeExpression(values.get(1).toString(),
									null);
					resourcesToAdd.put(values.get(1).toString(), c1);
					// resourcesListToAdd.put(values.get(1).toString(), values);
					// shouldResourcesAdd.put(values.get(1).toString(), false);
				}
			}// 12
			if (values.get(0).toString().equals("ChangeEvent")) {
				if (!resourcesToAdd.containsKey(values.get(1).toString())) {
					org.eclipse.uml2.uml.ChangeEvent c1 = SM.createChangeEvent(
							values.get(1).toString(), null, epo2Model,
							VisibilityKind.PUBLIC_LITERAL);
					if (values.size() > 2) {
						if (values.get(3).toString().equals("name")) {
							if (!values.get(4).toString().equals(""))
								c1.setName(values.get(4).toString());
						}
						// if (values.get(3).toString().equals("visibility"))
						// c1.setVisibility((VisibilityKind) values.get(4));
					}
					resourcesToAdd.put(values.get(1).toString(), c1);
					// resourcesListToAdd.put(values.get(1).toString(), values);
					// shouldResourcesAdd.put(values.get(1).toString(), false);
				}
			}// 13
			if (values.get(0).toString().equals("TimeEvent")) {
				if (!resourcesToAdd.containsKey(values.get(1).toString())) {
					org.eclipse.uml2.uml.TimeEvent c1 = SM.createTimeEvent(
							values.get(1).toString(), epo2Model, null, false);
					if (values.size() > 2) {
						if (values.get(3).toString().equals("isRelative"))
							c1.setIsRelative((Boolean) values.get(4));
						if (values.get(3).toString().equals("name")) {
							if (!values.get(4).toString().equals(""))
								c1.setName(values.get(4).toString());
						}
						// if (values.get(3).toString().equals("visibility"))
						// c1.setVisibility((VisibilityKind) values.get(4));
					}
					resourcesToAdd.put(values.get(1).toString(), c1);
					// resourcesListToAdd.put(values.get(1).toString(), values);
					// shouldResourcesAdd.put(values.get(1).toString(), false);
				}
			}// 14
			if (values.get(0).toString().equals("FinalState")) {
				if (!resourcesToAdd.containsKey(values.get(1).toString())) {
					org.eclipse.uml2.uml.FinalState c1 = SM.createFinalState(
							values.get(1).toString(), null, null, null, null,
							null, null, null);
					if (values.size() > 2){
						if (values.get(3).toString().equals("name")) {
							if (!values.get(4).toString().equals(""))
								c1.setName(values.get(4).toString());
						}
					}	
					resourcesToAdd.put(values.get(1).toString(), c1);
					// resourcesListToAdd.put(values.get(1).toString(), values);
					// shouldResourcesAdd.put(values.get(1).toString(), false);
				}
			}// 15
			if (values.get(0).toString().equals("Property")) {
				if (!resourcesToAdd.containsKey(values.get(1).toString())) {
					org.eclipse.uml2.uml.Property c1 = SM
							.createAttributeSimple(values.get(1).toString(),
									stringPrimitiveType,0, 1);
					if (values.size() > 2) {
						if (values.get(3).toString().equals("name")) {
							if (!values.get(4).toString().equals(""))
								c1.setName(values.get(4).toString());
						}
					
						if (values.get(3).toString().equals("upperValue")) {

							c1.setUpperValue((ValueSpecification) intPrimitiveType);
						}
						if (values.get(3).toString().equals("lowerValue")) {

							c1.setLowerValue((ValueSpecification) intPrimitiveType);
						}
						if (values.get(3).toString().equals("isComposite")) {

							c1.setIsComposite((Boolean) values.get(4));
						}
						if (values.get(3).toString().equals("aggregation")) {

							c1.setAggregation((AggregationKind) values.get(4));
						}
						if (values.get(3).toString().equals("default")) {

							c1.setDefault((String) values.get(4));
						}

						// if (values.get(3).toString().equals("visibility"))
						// c1.setVisibility((VisibilityKind) values.get(4));
					}
					resourcesToAdd.put(values.get(1).toString(), c1);
					// resourcesListToAdd.put(values.get(1).toString(), values);
					// shouldResourcesAdd.put(values.get(1).toString(), false);
				}
			}// 16
			if (values.get(0).toString().equals("Association")) {
				if (!resourcesToAdd.containsKey(values.get(1).toString())) {
					org.eclipse.uml2.uml.Association c1 = SM
							.createAssociationSimple(values.get(1).toString(),epo2Model);
					if (values.size() > 2) {
						if (values.get(3).toString().equals("name")) {
							if (!values.get(4).toString().equals(""))
								c1.setName(values.get(4).toString());
						}
						// if (values.get(3).toString().equals("visibility"))
						// c1.setVisibility((VisibilityKind) values.get(4));
					}
					resourcesToAdd.put(values.get(1).toString(), c1);
					// resourcesListToAdd.put(values.get(1).toString(), values);
					// shouldResourcesAdd.put(values.get(1).toString(), false);
				}
			}// 17
			if (values.get(0).toString().equals("OpaqueBehavior")) {
				if (!resourcesToAdd.containsKey(values.get(1).toString())) {
					org.eclipse.uml2.uml.OpaqueBehavior c1 = SM
							.createOpaqueBehavior(values.get(1).toString(),
									"OCL", "default.body");
					if (values.size() > 2) {
						if (values.get(3).toString().equals("Body"))
							c1.getBodies().add(values.get(4).toString());
						if (values.get(3).toString().equals("language"))
							c1.getLanguages().add(values.get(4).toString());
						if (values.get(3).toString().equals("name")) {
							if (!values.get(4).toString().equals(""))
								c1.setName(values.get(4).toString());
						}
					}
					resourcesToAdd.put(values.get(1).toString(), c1);
					// resourcesListToAdd.put(values.get(1).toString(), values);
					// shouldResourcesAdd.put(values.get(1).toString(), false);
				}
			}// 18
			if (values.get(0).toString().equals("Trigger")) {
				if (!resourcesToAdd.containsKey(values.get(1).toString())) {
					org.eclipse.uml2.uml.Trigger c1 = SM.createTrigger(values
							.get(1).toString(), null);
					if (values.size() > 2) {
						if (values.get(3).toString().equals("name")) {
							if (!values.get(4).toString().equals(""))
								c1.setName(values.get(4).toString());
						}
					}
					resourcesToAdd.put(values.get(1).toString(), c1);
					// resourcesListToAdd.put(values.get(1).toString(), values);
					// shouldResourcesAdd.put(values.get(1).toString(), false);
				}
			}// 19
			if (values.get(0).toString().equals("State")) {
				if (!resourcesToAdd.containsKey(values.get(1).toString())) {
					org.eclipse.uml2.uml.State c1 = SM.createState(values
							.get(1).toString(), null, null, null, null, null,
							null, null, null);
					if (values.size() > 2){
						if (values.get(3).toString().equals("name")) {
							if (!values.get(4).toString().equals(""))
								c1.setName(values.get(4).toString());
						}
						
/*						if (values.get(3).toString().equals("stereotype3")) {
							String StereoName="";
							if (values.size() > 4)
							if (!values.get(4).toString().equals("Error")) {
								    StereoName="Error";
								if(values.get(4).toString().equals("Failure"))
									StereoName="Failure";
								Stereotype stereo= SM.retrieveStereoType(profile, StereoName);
								
							//    SM.applyStereotype(c1, stereo);
							}
						}*/
					}
					resourcesToAdd.put(values.get(1).toString(), c1);
					// resourcesListToAdd.put(values.get(1).toString(), values);
					// shouldResourcesAdd.put(values.get(1).toString(), false);
				}
			}// 20
			if (values.get(0).toString().equals("Transition")) {
				if (!resourcesToAdd.containsKey(values.get(1).toString())) {
					org.eclipse.uml2.uml.Transition c1 = SM.createTransition(
							values.get(1).toString(), null, null,
							TransitionKind.EXTERNAL_LITERAL, null, null, null);
					if (values.size() > 2) {
						if (values.get(3).toString().equals("kind"))
							c1.setKind((TransitionKind) values.get(4));
						if (values.get(3).toString().equals("name")) {
							if (!values.get(4).toString().equals(""))
								c1.setName(values.get(4).toString());
						}
					}
					resourcesToAdd.put(values.get(1).toString(), c1);
					// resourcesListToAdd.put(values.get(1).toString(), values);
					// shouldResourcesAdd.put(values.get(1).toString(), false);
				}
			}// 21
			if (values.get(0).toString().equals("Region")) {
				if (!resourcesToAdd.containsKey(values.get(1).toString())) {
					org.eclipse.uml2.uml.Region c1 = SM.createRegionSimple(
							null, values.get(1).toString());
					if (values.size() > 2) {
						if (values.get(3).toString().equals("name")) {
							if (values.size()==5 && !values.get(4).toString().equals(""))
								c1.setName(values.get(4).toString());
						}
					}
					resourcesToAdd.put(values.get(1).toString(), c1);
					// resourcesListToAdd.put(values.get(1).toString(), values);
					// shouldResourcesAdd.put(values.get(1).toString(), false);
				}
			}// 22
			if (values.get(0).toString().equals("StateMachine")) {
				if (!resourcesToAdd.containsKey(values.get(1).toString())) {
					org.eclipse.uml2.uml.StateMachine c1 = SM
							.createStateMachine(null, values.get(1).toString());
					if (values.size() > 2) {
						if (values.get(3).toString().equals("name")) {
							if (!values.get(4).toString().equals(""))
								c1.setName(values.get(4).toString());
						}
					}
					resourcesToAdd.put(values.get(1).toString(), c1);
					// resourcesListToAdd.put(values.get(1).toString(), values);
					// shouldResourcesAdd.put(values.get(1).toString(), false);
				}
			}// 23
			if (values.get(0).toString().equals("Pseudostate")) {
				if (!resourcesToAdd.containsKey(values.get(1).toString())) {
					org.eclipse.uml2.uml.Pseudostate c1 = SM.createPseudoState(
							values.get(1).toString(), PseudostateKind.INITIAL_LITERAL, null, null);
					if (values.size() > 2) {
						if (values.get(3).toString().equals("name")) {
							if (!values.get(4).toString().equals(""))
								c1.setName(values.get(4).toString());
						}
					}
					resourcesToAdd.put(values.get(1).toString(), c1);
					// resourcesListToAdd.put(values.get(1).toString(), values);
					// shouldResourcesAdd.put(values.get(1).toString(), false);
				}
			}// 24
			if (values.get(0).toString().equals("Constraint")) {
				if (!resourcesToAdd.containsKey(values.get(1).toString())) {
					org.eclipse.uml2.uml.Constraint c1 = SM.createConstraint(
							values.get(1).toString(), null);
					if (values.size() > 2) {
						if (values.get(3).toString().equals("name")) {
							if (!values.get(4).toString().equals(""))
								c1.setName(values.get(4).toString());
						}
						// if (values.get(3).toString().equals("visibility"))
						// c1.setVisibility((VisibilityKind) values.get(4));
					}
					resourcesToAdd.put(values.get(1).toString(), c1);
					// resourcesListToAdd.put(values.get(1).toString(), values);
					// shouldResourcesAdd.put(values.get(1).toString(), false);
				}
			}

			// System.out.println("Values = " + values + ".....n");
		}

		createAllDiagramRelationships(resourcesToAdd);
		String[] tempTags = new String[] { "Constraint","Reception", "TimeExpression",
				"TimeEvent", "CallEvent", "ChangeEvent", "SignalEvent",
				"Trigger", "Transition","Region","State", "StateMachine" };
		for(String tt : tempTags){
			createAllDiagramRelationships(resourcesToAdd, tt);
		}
		 tempTags = new String[] { "Property","State","Pseudostate","Transition" };
		for(String tt : tempTags){
			createAllDiagramStereotype(resourcesToAdd,  tt);
		}
		// now save the content.

		SM.saveModel(path, epo2Model, fileName);

		System.out.println("\n-----------UML Instance Generation Completed---------\n");

	}

	private void createAllDiagramRelationships(
			HashMap<String, Object> resourcesToAdd) {
		for (List<Object> values : QueryTuppleMap) {
			// 1
			if (values.get(0).toString().equals("Class")) {
				org.eclipse.uml2.uml.Class c1 = (org.eclipse.uml2.uml.Class) resourcesToAdd
						.get(values.get(1));
				if (values.size() > 2) {
					if (values.get(3).toString().equals("stereotypeSystem")) {
					if(c1.getAppliedStereotypes().size()==0){
						ProfiledStateMachine SM = new ProfiledStateMachine();
						if(values.size()>4)
						if (!values.get(4).toString().equals("")) {
							String StereoName="";
							if(Integer.parseInt(values.get(4).toString())>0){
								StereoName="System";
							Stereotype stereo= SM.retrieveStereoType(profile, StereoName);
						    SM.applyStereotype(c1, stereo);
						}
						}
					}
					}
					if (values.get(3).toString().equals("stereotypeCotext")) {
					if(c1.getAppliedStereotypes().size()==0){
						ProfiledStateMachine SM = new ProfiledStateMachine();
						if(values.size()>4)
						if (!values.get(4).toString().equals("")) {
							String StereoName="";
							if(Integer.parseInt(values.get(4).toString())>0){
								StereoName="Context";
							Stereotype stereo= SM.retrieveStereoType(profile, StereoName);
						    SM.applyStereotype(c1, stereo);
						}
						}
					}
					}
					if(values.size()>4){
					Object rObj = resourcesToAdd.get(values.get(4));
					if (rObj != null && c1 != null)
						createClassRelationship(c1, rObj, values);
					}
				}
			}// 2
			if (values.get(0).toString().equals("Package")) {
				org.eclipse.uml2.uml.Package c1 = (org.eclipse.uml2.uml.Package) resourcesToAdd
						.get(values.get(1));
				if (values.size() > 2) {
					Object rObj = resourcesToAdd.get(values.get(4));
					if (rObj != null && c1 != null)
						createPackageRelationship(c1, rObj, values);
				}
			}// 3
			if (values.get(0).toString().equals("PrimitiveDataType")) {
				org.eclipse.uml2.uml.PrimitiveType c1 = (org.eclipse.uml2.uml.PrimitiveType) resourcesToAdd
						.get(values.get(1));
				if (values.size() > 2) {
					Object rObj = resourcesToAdd.get(values.get(4));
					if (rObj != null && c1 != null)
						createPrimitiveTypeRelationship(c1, rObj, values);
				}
			}// 4
			if (values.get(0).toString().equals("Signal")) {
				org.eclipse.uml2.uml.Signal c1 = (org.eclipse.uml2.uml.Signal) resourcesToAdd
						.get(values.get(1));
				if (values.size() > 2) {
					Object rObj = resourcesToAdd.get(values.get(4));
					if (rObj != null && c1 != null)
						createSignalRelationship(c1, rObj, values);
				}
			}// 5
//			if (values.get(0).toString().equals("Reception")) {
//				org.eclipse.uml2.uml.Reception c1 = (org.eclipse.uml2.uml.Reception) resourcesToAdd
//						.get(values.get(1));
//				if (values.size() > 2) {
//					Object rObj = resourcesToAdd.get(values.get(4));
//					if (rObj != null && c1 != null)
//						createReceptionRelationship(c1, rObj, values);
//				}
//			}// 6
			if (values.get(0).toString().equals("OpaqueExpression")) {
				org.eclipse.uml2.uml.OpaqueExpression c1 = (org.eclipse.uml2.uml.OpaqueExpression) resourcesToAdd
						.get(values.get(1));
				if (values.size() > 2) {
					Object rObj = resourcesToAdd.get(values.get(4));
					if (rObj != null && c1 != null)
						createOpaqueExpressionRelationship(c1, rObj, values);
				}
			}//6A
			if (values.get(0).toString().equals("LiteralString")) {
				org.eclipse.uml2.uml.LiteralString c1 = (org.eclipse.uml2.uml.LiteralString) resourcesToAdd
						.get(values.get(1));
				if (values.size() > 2) {
					Object rObj = resourcesToAdd.get(values.get(4));
					if (rObj != null && c1 != null)
						createLiteralStringRelationship(c1, rObj, values);
				}
			}// 7
			if (values.get(0).toString().equals("AnyRecieveEvent")) {
				org.eclipse.uml2.uml.AnyReceiveEvent c1 = (org.eclipse.uml2.uml.AnyReceiveEvent) resourcesToAdd
						.get(values.get(1));
				if (values.size() > 2) {
					Object rObj = resourcesToAdd.get(values.get(4));
					if (rObj != null && c1 != null)
						createAnyRecieveEventRelationship(c1, rObj, values);
				}
			}// 8
//			if (values.get(0).toString().equals("SignalEvent")) {
//				org.eclipse.uml2.uml.SignalEvent c1 = (SignalEvent) resourcesToAdd
//						.get(values.get(1));
//				if (values.size() > 2) {
//					Object rObj = resourcesToAdd.get(values.get(4));
//					if (rObj != null && c1 != null)
//						createSignalEventRelationship(c1, rObj, values);
//				}
//			}// 9
//			if (values.get(0).toString().equals("CallEvent")) {
//				org.eclipse.uml2.uml.CallEvent c1 = (CallEvent) resourcesToAdd
//						.get(values.get(1));
//				if (values.size() > 2) {
//					Object rObj = resourcesToAdd.get(values.get(4));
//					if (rObj != null && c1 != null)
//						createCallEventRelationship(c1, rObj, values);
//				}
//			}// 10
			if (values.get(0).toString().equals("Operation")) {
				org.eclipse.uml2.uml.Operation c1 = (Operation) resourcesToAdd
						.get(values.get(1));
				if (values.size() > 2) {
					Object rObj = resourcesToAdd.get(values.get(4));
					if (rObj != null && c1 != null)
						createOperationRelationship(c1, rObj, values);
				}
			}// 11
//			if (values.get(0).toString().equals("TimeExpression")) {
//				org.eclipse.uml2.uml.TimeExpression c1 = (TimeExpression) resourcesToAdd
//						.get(values.get(1));
//				if (values.size() > 2) {
//					Object rObj = resourcesToAdd.get(values.get(4));
//					if (rObj != null && c1 != null)
//						createTimeExpressionRelationship(c1, rObj, values);
//				}
//			}// 12
//			if (values.get(0).toString().equals("ChangeEvent")) {
//				org.eclipse.uml2.uml.ChangeEvent c1 = (ChangeEvent) resourcesToAdd
//						.get(values.get(1));
//				if (values.size() > 2) {
//					Object rObj = resourcesToAdd.get(values.get(4));
//					if (rObj != null && c1 != null)
//						createChangeEventRelationship(c1, rObj, values);
//				}
//			}// 13
//			if (values.get(0).toString().equals("TimeEvent")) {
//				org.eclipse.uml2.uml.TimeEvent c1 = (TimeEvent) resourcesToAdd
//						.get(values.get(1));
//				if (values.size() > 2) {
//					Object rObj = resourcesToAdd.get(values.get(4));
//					if (rObj != null && c1 != null)
//						createTimeEventRelationship(c1, rObj, values);
//				}
//			}// 14
			if (values.get(0).toString().equals("FinalState")) {
				org.eclipse.uml2.uml.FinalState c1 = (FinalState) resourcesToAdd
						.get(values.get(1));
				if (values.size() > 2) {
					Object rObj = resourcesToAdd.get(values.get(4));
					if (rObj != null && c1 != null)
						createFinalStateRelationship(c1, rObj, values);
				}
			}// 15
			if (values.get(0).toString().equals("Property")) {
				org.eclipse.uml2.uml.Property c1 = (Property) resourcesToAdd
						.get(values.get(1));
				if (values.size() > 2) {
					if(values.size()>4)	{
					Object rObj = resourcesToAdd.get(values.get(4));
					if (rObj != null && c1 != null)
						createPropertyRelationship(c1, rObj, values);
/*					if (values.get(3).toString().equals("stereotype4")) {
						if(c1.getAppliedStereotypes().size()==0){
							ProfiledStateMachine SM = new ProfiledStateMachine();
							if(values.size()>4)
							if (!values.get(4).toString().equals("")) {
								String StereoName="";
								if(values.get(4).toString().equals("NonDet"))
									StereoName="NonDeterministic";
								Stereotype stereo= SM.retrieveStereoType(profile, StereoName);
								//SM.setStereotypePropertyValue(c1, stereo, stereo.getAllAttributes().get(0), "300");
							    SM.applyStereotype(c1, stereo);
							}
						}
					}*/
					}
				}
			}// 16
			if (values.get(0).toString().equals("Association")) {
				org.eclipse.uml2.uml.Association c1 = (org.eclipse.uml2.uml.Association) resourcesToAdd
						.get(values.get(1));
				if (values.size() > 2) {
					Object rObj = resourcesToAdd.get(values.get(4));
					if (rObj != null && c1 != null)
						createAssociationRelationship(c1, rObj, values);
				}
			}// 17
			if (values.get(0).toString().equals("OpaqueBehavior")) {
				org.eclipse.uml2.uml.OpaqueBehavior c1 = (OpaqueBehavior) resourcesToAdd
						.get(values.get(1));
				if (values.size() > 2) {
					Object rObj = resourcesToAdd.get(values.get(4));
					if (rObj != null && c1 != null)
						createOpaqueBehaviorRelationship(c1, rObj, values);
				}
			}// 18
//			if (values.get(0).toString().equals("Trigger")) {
//				org.eclipse.uml2.uml.Trigger c1 = (Trigger) resourcesToAdd
//						.get(values.get(1));
//				if (values.size() > 2) {
//					Object rObj = resourcesToAdd.get(values.get(4));
//					if (rObj != null && c1 != null)
//						createTriggerRelationship(c1, rObj, values);
//					System.out.print("");
//				}
//			}// 19
//			if (values.get(0).toString().equals("State")) {
//				org.eclipse.uml2.uml.State c1 = (State) resourcesToAdd
//						.get(values.get(1));
//				if (values.size() > 2) {
//					Object rObj = resourcesToAdd.get(values.get(4));
//					if (rObj != null && c1 != null)
//						createStateRelationship(c1, rObj, values);
//				}
//			}// 20
//			if (values.get(0).toString().equals("Transition")) {
//				org.eclipse.uml2.uml.Transition c1 = (Transition) resourcesToAdd
//						.get(values.get(1));
//				if (values.size() > 2) {
//					Object rObj = resourcesToAdd.get(values.get(4));
//					if (rObj != null && c1 != null)
//						createTransitionRelationship(c1, rObj, values);
//				}
//			}// 21
//			if (values.get(0).toString().equals("Region")) {
//				org.eclipse.uml2.uml.Region c1 = (Region) resourcesToAdd
//						.get(values.get(1));
//				if (values.size() > 4) {
//					Object rObj = resourcesToAdd.get(values.get(4));
//					if (rObj != null && c1 != null)
//						createRegionRelationship(c1, rObj, values);
//				}
//			}// 22
//			if (values.get(0).toString().equals("StateMachine")) {
//				org.eclipse.uml2.uml.StateMachine c1 = (StateMachine) resourcesToAdd
//						.get(values.get(1));
//				if (values.size() > 2) {
//					Object rObj = resourcesToAdd.get(values.get(4));
//					if (rObj != null && c1 != null)
//						createStateMachineRelationship(c1, rObj, values);
//				}
//			}// 23
			if (values.get(0).toString().equals("Pseudostate")) {
				org.eclipse.uml2.uml.Pseudostate c1 = (Pseudostate) resourcesToAdd
						.get(values.get(1));
				if (values.size() > 4) {			
					Object rObj = resourcesToAdd.get(values.get(4));
					if (rObj != null && c1 != null)
						createPseudoStateRelationship(c1, rObj, values);
				}
			}// 24
//			if (values.get(0).toString().equals("Constraint")) {
//				org.eclipse.uml2.uml.Constraint c1 = (Constraint) resourcesToAdd
//						.get(values.get(1));
//				if (values.size() > 2) {
//					Object rObj = resourcesToAdd.get(values.get(4));
//					if (rObj != null && c1 != null)
//						createConstraintRelationship(c1, rObj, values);
//				}
//			}
		}
	}
	
	private void createAllDiagramRelationships(
			HashMap<String, Object> resourcesToAdd, String value) {
		for (List<Object> values : QueryTuppleMap) {
			if (values.get(0).toString().equals(value)) {
				// 24
				if (values.get(0).toString().equals("Constraint")) {
					org.eclipse.uml2.uml.Constraint c1 = (Constraint) resourcesToAdd
							.get(values.get(1));
					if (values.size() > 2) {
						Object rObj = resourcesToAdd.get(values.get(4));
						if (rObj != null && c1 != null){
							createConstraintRelationship(c1, rObj, values);
							c1.getSpecification();
						}
					}
				}
				//19
				if (values.get(0).toString().equals("State")) {
					org.eclipse.uml2.uml.State c1 = (State) resourcesToAdd
							.get(values.get(1));
					if (values.size() > 4) {			
						Object rObj = resourcesToAdd.get(values.get(4));
						if (rObj != null && c1 != null)
							createStateRelationship(c1, rObj, values);
					}
				}
				// 5
				if (values.get(0).toString().equals("Reception")) {
					org.eclipse.uml2.uml.Reception c1 = (org.eclipse.uml2.uml.Reception) resourcesToAdd
							.get(values.get(1));
					if (values.size() > 2) {
						Object rObj = resourcesToAdd.get(values.get(4));
						if (rObj != null && c1 != null)
							createReceptionRelationship(c1, rObj, values);
					}
				}
				// 8
				if (values.get(0).toString().equals("SignalEvent")) {
					org.eclipse.uml2.uml.SignalEvent c1 = (SignalEvent) resourcesToAdd
							.get(values.get(1));
					if (values.size() > 2) {
						Object rObj = resourcesToAdd.get(values.get(4));
						if (rObj != null && c1 != null)
							createSignalEventRelationship(c1, rObj, values);
					}
				}// 9
				if (values.get(0).toString().equals("CallEvent")) {
					org.eclipse.uml2.uml.CallEvent c1 = (CallEvent) resourcesToAdd
							.get(values.get(1));
					if (values.size() > 2) {
						Object rObj = resourcesToAdd.get(values.get(4));
						if (rObj != null && c1 != null)
							createCallEventRelationship(c1, rObj, values);
					}
				}
				// 11
				if (values.get(0).toString().equals("TimeExpression")) {
					org.eclipse.uml2.uml.TimeExpression c1 = (TimeExpression) resourcesToAdd
							.get(values.get(1));
					if (values.size() > 2) {
						Object rObj = resourcesToAdd.get(values.get(4));
						if (rObj != null && c1 != null)
							createTimeExpressionRelationship(c1, rObj, values);
					}
				}// 12
				if (values.get(0).toString().equals("ChangeEvent")) {
					org.eclipse.uml2.uml.ChangeEvent c1 = (ChangeEvent) resourcesToAdd
							.get(values.get(1));
					if (values.size() > 2) {
						Object rObj = resourcesToAdd.get(values.get(4));
						if (rObj != null && c1 != null)
							createChangeEventRelationship(c1, rObj, values);
					}
				}// 13
				if (values.get(0).toString().equals("TimeEvent")) {
					org.eclipse.uml2.uml.TimeEvent c1 = (TimeEvent) resourcesToAdd
							.get(values.get(1));
					if (values.size() > 2) {
						Object rObj = resourcesToAdd.get(values.get(4));
						if (rObj != null && c1 != null)
							createTimeEventRelationship(c1, rObj, values);
					}
				}// 14
				if (values.get(0).toString().equals("Trigger")) {
					org.eclipse.uml2.uml.Trigger c1 = (Trigger) resourcesToAdd
							.get(values.get(1));
					if (values.size() > 2) {
						Object rObj = resourcesToAdd.get(values.get(4));
						if (rObj != null && c1 != null)
							createTriggerRelationship(c1, rObj, values);
						System.out.print("");
					}
				}// 19
				if (values.get(0).toString().equals("Transition")) {
					org.eclipse.uml2.uml.Transition c1 = (Transition) resourcesToAdd
							.get(values.get(1));
					if (values.size() > 4) {
						Object rObj = resourcesToAdd.get(values.get(4));
						if (rObj != null && c1 != null)
							createTransitionRelationship(c1, rObj, values);
					}
				}// 21
				if (values.get(0).toString().equals("Region")) {
					org.eclipse.uml2.uml.Region c1 = (Region) resourcesToAdd
							.get(values.get(1));
					if (values.size() > 4) {
						Object rObj = resourcesToAdd.get(values.get(4));
						if (rObj != null && c1 != null)
							createRegionRelationship(c1, rObj, values);
					}
				}// 22
				if (values.get(0).toString().equals("StateMachine")) {
					org.eclipse.uml2.uml.StateMachine c1 = (StateMachine) resourcesToAdd
							.get(values.get(1));
					if (values.size() > 2) {
						Object rObj = resourcesToAdd.get(values.get(4));
						if (rObj != null && c1 != null)
							createStateMachineRelationship(c1, rObj, values);
					}
				}// 23
			}
		}
	}

	private void createAllDiagramStereotype(
			HashMap<String, Object> resourcesToAdd, String value) {
		for (List<Object> values : QueryTuppleMap) {	
			if (values.get(0).toString().equals(value)) {
				if (values.get(0).toString().equals("Transition")) {
					org.eclipse.uml2.uml.Transition c1 = (Transition) resourcesToAdd
							.get(values.get(1));
					if (values.size() > 4) {
						if (values.get(3).toString().equals("stereotypeTimeProbability")) {
							if(c1.getAppliedStereotypes().size()==0){
								ProfiledStateMachine SM = new ProfiledStateMachine();
								if(values.size()>4)
								if (!values.get(4).toString().equals("")) {
									String StereoName="";
									if(Integer.parseInt(values.get(4).toString())>0){
										StereoName="TimeProbability";
									Stereotype stereo= SM.retrieveStereoType(profile, StereoName);
								    if(stereo!=null)
									SM.applyStereotype(c1, stereo);
								}
								}
							}
							}
						if (values.get(3).toString().equals("stereotypeNonLeaving")) {
							if(c1.getAppliedStereotypes().size()==0){
								ProfiledStateMachine SM = new ProfiledStateMachine();
								if(values.size()>4)
								if (!values.get(4).toString().equals("")) {
									String StereoName="";
									if(Integer.parseInt(values.get(4).toString())>0){
										StereoName="NonLeaving";
									Stereotype stereo= SM.retrieveStereoType(profile, StereoName);
								    if(stereo!=null)
									SM.applyStereotype(c1, stereo);
									}
								}
							}
							}
					}
				}//
				if (values.get(0).toString().equals("Property")) {
					org.eclipse.uml2.uml.Property c1 = (Property) resourcesToAdd
							.get(values.get(1));
					if (values.size() > 2) {
						if(values.size()>4)	{
						if (values.get(3).toString().equals("stereotypeNonDeterministic")) {
							if(c1.getAppliedStereotypes().size()==0){
								ProfiledStateMachine SM = new ProfiledStateMachine();
								if(values.size()>4)
								if (!values.get(4).toString().equals("")) {
									String StereoName="";
									if(Integer.parseInt(values.get(4).toString())>0){
										StereoName="NonDeterministic";
									Stereotype stereo= SM.retrieveStereoType(profile, StereoName);
									//SM.setStereotypePropertyValue(c1, stereo, stereo.getAllAttributes().get(0), "300");
								    SM.applyStereotype(c1, stereo);
									}
								}
							}
						}
						}
					}
				}
				if (values.get(0).toString().equals("Pseudostate")) {
					org.eclipse.uml2.uml.Pseudostate c1 = (Pseudostate) resourcesToAdd
							.get(values.get(1));
					if (values.size() > 2) {
						if (values.get(3).toString().equals("stereotypeNDChoice")) {
							ProfiledStateMachine SM = new ProfiledStateMachine();
							if(values.size()>4)
							if (!values.get(4).toString().equals("")) {
								String StereoName="";
								if(Integer.parseInt(values.get(4).toString())>0){
									StereoName="NDChoice";
								Stereotype stereo= SM.retrieveStereoType(profile, StereoName);
							    if(stereo!=null)
								SM.applyStereotype(c1, stereo);
								}
							}
						}
					}
				}
				if (values.get(0).toString().equals("State")) {
					org.eclipse.uml2.uml.State c1 = (State) resourcesToAdd
							.get(values.get(1));
					if (values.size() > 2) {
						if (values.get(3).toString().equals("stereotypeFailure")) {
							ProfiledStateMachine SM = new ProfiledStateMachine();
							if(values.size()>4)
							if (!values.get(4).toString().equals("")) {
								String StereoName="";
								if(Integer.parseInt(values.get(4).toString())>0){
									StereoName="Failure";
								if(Integer.parseInt(values.get(4).toString())>0)
									StereoName="Error";
								Stereotype stereo= SM.retrieveStereoType(profile, StereoName);
							    SM.applyStereotype(c1, stereo);
							}
							}
						}
						if (values.get(3).toString().equals("stereotypeError")) {
							ProfiledStateMachine SM = new ProfiledStateMachine();
							if(values.size()>4)
							if (!values.get(4).toString().equals("")) {
								String StereoName="";
								if(Integer.parseInt(values.get(4).toString())>0){
									StereoName="Error";
								Stereotype stereo= SM.retrieveStereoType(profile, StereoName);
							    SM.applyStereotype(c1, stereo);
							}
							}
						}

					}
				}
	}}}
	private void createConstraintRelationship(Constraint c1, Object rObj,
			List<Object> values) {
		if (values.get(3).toString().trim().equals("specification")) {
			if (rObj instanceof OpaqueExpression){
				ValueSpecification v=c1.createSpecification(((ValueSpecification) rObj).getName(), ((ValueSpecification) rObj).getType(),((ValueSpecification) rObj).eClass());
			//	c1.setSpecification((OpaqueExpression) rObj);
				if (v instanceof OpaqueExpression){
					((OpaqueExpression)v).getBodies().addAll(((OpaqueExpression) rObj).getBodies());
					((OpaqueExpression)v).getLanguages().addAll(((OpaqueExpression) rObj).getLanguages());
				}
				else{if (v instanceof LiteralString){
					((LiteralString)v).setValue(((LiteralString) rObj).getValue());
				}
				}
			}
		}
	}

	private void createPseudoStateRelationship(Pseudostate c1, Object rObj,
			List<Object> values) {
		
		if (values.get(3).toString().trim().equals("state")) {
			if (rObj instanceof State)
				c1.setState((State) rObj);
		}
		if (values.get(3).toString().trim().equals("stateMachine")) {
			if (rObj instanceof StateMachine)
				c1.setStateMachine((StateMachine) rObj);
		}
	}

	private void createStateMachineRelationship(StateMachine c1, Object rObj,
			List<Object> values) {

		if (values.get(3).toString().trim().equals("region")) {
			if (rObj instanceof Region) {
			//1.createRegion(((Region) rObj).getName());
				c1.getRegions().add((Region) rObj);
			}
		}
		if (values.get(3).toString().trim().equals("connectionPoint")) {
			if (rObj instanceof Pseudostate) {
			//1.createConnectionPoint(((Pseudostate) rObj).getName());
				c1.getConnectionPoints().add((Pseudostate) rObj);
			}
		}
	}

	private void createRegionRelationship(Region c1, Object rObj,
			List<Object> values) {
		if (values.get(3).toString().trim().equals("transition")) {
			if (rObj instanceof Transition && rObj!=null) {
//			Vertex v=c1.createSubvertex(((Transition) rObj).getTarget().getName(),
//						((Transition) rObj).getTarget().eClass());
//
//			Vertex v1=c1.createSubvertex(((Transition) rObj).getSource().getName(),
//						((Transition) rObj).getSource().eClass());
//
//				Transition t = c1.createTransition(
//						((Transition) rObj).getName(),
//						((Transition) rObj).eClass());
//				t.setSource(c1.getSubvertex(((Transition) rObj).getSource()
//						.getName()));
//				t.setTarget(c1.getSubvertex(((Transition) rObj).getTarget()
//						.getName()));
//				OpaqueBehavior b = (OpaqueBehavior) t.createEffect(
//						((Transition) rObj).getEffect().getName(),
//						((Transition) rObj).getEffect().eClass());
//
//				b.getLanguages().addAll(
//						((OpaqueBehavior) ((Transition) rObj).getEffect())
//								.getLanguages());
//				b.getBodies().addAll(
//						((OpaqueBehavior) ((Transition) rObj).getEffect())
//								.getBodies());
//				
//				if (((Transition) rObj).getTriggers() != null) {
//					for (Trigger tt : ((Transition) rObj).getTriggers()) {
//						Trigger k = t.createTrigger(tt.getName());						
//						if(tt.getEvent()!=null){
//						k.setEvent(tt.getEvent());
//						}
//						
//					}
//				}
				//c1.createTransition(((Transition) rObj).getName(), ((Transition) rObj).eClass());
				c1.getTransitions().add((Transition) rObj);
				c1.getSubvertices().add(((Transition) rObj).getTarget());
				c1.getSubvertices().add(((Transition) rObj).getSource());

			}
		}
		if (values.get(3).toString().trim().equals("state")) {
			if (rObj instanceof State)
				c1.setState((State) rObj);
		}
		if (values.get(3).toString().trim().equals("stateMachine")) {
			if (rObj instanceof StateMachine)
				c1.setStateMachine((StateMachine) rObj);
		}
		if (values.get(3).toString().trim().equals("subvertex")) {
			if (rObj instanceof Vertex) {
				c1.getSubvertices().add((Vertex) rObj);
				c1.createSubvertex(((Vertex) rObj).getName(),
						((Vertex) rObj).eClass());
			}
		}
	}

	private void createTransitionRelationship(Transition c1, Object rObj,
			List<Object> values) {
		if (values.get(3).toString().trim().equals("source")) {
			if (rObj instanceof Vertex) {
				c1.setSource((Vertex) rObj);
				
			}
		}
		if (values.get(3).toString().trim().equals("target")) {
			if (rObj instanceof Vertex) {
				c1.setTarget((Vertex) rObj);

			}
		}
		if (values.get(3).toString().trim().equals("effect")) {
			if (rObj instanceof OpaqueBehavior) {
				OpaqueBehavior b=(OpaqueBehavior) c1.createEffect(((OpaqueBehavior) rObj).getName(),
						((OpaqueBehavior) rObj).eClass());
			b.setName(((OpaqueBehavior) rObj).getName());
			b.getLanguages().addAll(((OpaqueBehavior) rObj).getLanguages());
			b.getBodies().addAll(((OpaqueBehavior) rObj).getBodies());
				c1.setEffect((OpaqueBehavior) rObj);
			}
		}
		if (values.get(3).toString().trim().equals("container")) {
			if (rObj instanceof Region)
				c1.setContainer((Region) rObj);
		}
		if (values.get(3).toString().trim().equals("trigger")) {
			if (rObj instanceof Trigger) {
				Trigger t=c1.createTrigger(((Trigger) rObj).getName());
				t.setEvent(((Trigger) rObj).getEvent());
//				c1.getTriggers().add((Trigger) rObj);
			}
		}
		if (values.get(3).toString().trim().equals("ownedRule")) {
			if (rObj instanceof Constraint) {
//				 c1.getOwnedRules().add((Constraint) rObj);
				Constraint ct=c1.createOwnedRule(((Constraint) rObj).getName(),
						((Constraint) rObj).eClass());
				ct.setSpecification(((Constraint) rObj).getSpecification());
				
			}
		}
	}

	private void createStateRelationship(State c1, Object rObj,
			List<Object> values) {
		if (values.get(3).toString().trim().equals("defferableTrigger")) {
			if (rObj instanceof Trigger)
				c1.getDeferrableTriggers().add((Trigger) rObj);
		}
		if (values.get(3).toString().trim().equals("region")) {
			if (rObj instanceof Region)
				c1.getRegions().add((Region) rObj);
			
		}
		if (values.get(3).toString().trim().equals("connectionPoint")) {
			if (rObj instanceof Pseudostate)
				c1.getConnectionPoints().add((Pseudostate) rObj);
		}
		if (values.get(3).toString().trim().equals("submachine")) {
			if (rObj instanceof StateMachine)
				c1.setSubmachine((StateMachine) rObj);
		}
		if (values.get(3).toString().trim().equals("stateInvariant")) {
			if (rObj instanceof Constraint){
				c1.setStateInvariant((Constraint) rObj);
				Constraint ct=c1.createStateInvariant(((Constraint) rObj).getName(), ((Constraint) rObj).eClass());
			    ct.setSpecification(((Constraint) rObj).getSpecification());
				//	c1.createOwnedRule(((Constraint) rObj).getName(), ((Constraint) rObj).eClass());
			//	Constraint v = c1.createStateInvariant(((Constraint) rObj).getName(), ((Constraint) rObj).eClass());
			//	v.createSpecification(((Constraint) rObj).getSpecification().getName(), ((Constraint) rObj).getSpecification().getType(),((Constraint) rObj).getSpecification().eClass());
			}
			
		       
		}
		if (values.get(3).toString().trim().equals("exit")) {
			if (rObj instanceof Behavior)
				c1.setExit((Behavior) rObj);
		}
		if (values.get(3).toString().trim().equals("doActivity")) {
			if (rObj instanceof Behavior)
				c1.setDoActivity((Behavior) rObj);
		}
		if (values.get(3).toString().trim().equals("entry")) {
			if (rObj instanceof Behavior)
				c1.setEntry((Behavior) rObj);
		}
		if (values.get(3).toString().trim().equals("container")) {
			if (rObj instanceof Region)
				c1.setContainer((Region) rObj);
		}

	}

	private void createTriggerRelationship(Trigger c1, Object rObj,
			List<Object> values) {
		if (values.get(3).toString().trim().equals("event")) {
			if (rObj instanceof Event) {
				c1.setEvent((Event) rObj);
//
//				System.out.println("Event association completed between :  "
//						+ c1.getName() + " ---- " + ((Event) rObj).getName());
			}
		}

	}

	private void createOpaqueBehaviorRelationship(OpaqueBehavior c1,
			Object rObj, List<Object> values) {
		// TODO Auto-generated method stub
	}

	private void createAssociationRelationship(
			org.eclipse.uml2.uml.Association c1, Object rObj,
			List<Object> values) {
		if (values.get(3).toString().trim().equals("memberEnd")) {
			if (rObj instanceof Property){
				c1.getMemberEnds().add((Property) rObj);
				  ((Property) rObj).setAssociation(c1);
			}
		}
		if (values.get(3).toString().trim().equals("navigableOwnedEnd")) {
			if (rObj instanceof Property) {
				c1.getNavigableOwnedEnds().add((Property) rObj);
//				c1.createNavigableOwnedEnd(((Property) rObj).getName(),
//						(Type) ((Property) rObj).getType(),
//						((Property) rObj).eClass());
				  ((Property) rObj).setAssociation(c1);
			}
		}
		if (values.get(3).toString().trim().equals("ownedEnd")) {
			if (rObj instanceof Property){
				c1.getOwnedEnds().add((Property) rObj);
			//Property p=	c1.createOwnedEnd(((Property) rObj).getName(), ((Property) rObj).getType(),((Property) rObj).eClass());
			    c1.getMemberEnds().add((Property) rObj);
			     ((Property) rObj).setAssociation(c1);
			     //((Property) rObj).getClass_().getPackage()
			   //  c1.setPackage(((Property) rObj).getClass_().geto);
			     //p.setAssociation(c1);
				//	((Property) rObj).getType().createAssociation(true, AggregationKind.NONE_LITERAL, "", 0, 1, arg5, true,  AggregationKind.NONE_LITERAL, "", 0, 1);
			//	c1.getOwnedEnds().add((Property) rObj);
		}
		}
	}

	private void createPropertyRelationship(Property c1, Object rObj,
			List<Object> values) {
		if (values.get(3).toString().trim().equals("type")) {
			if (rObj instanceof Feature)
				c1.setType((Type) rObj);
		}
		if (values.get(3).toString().trim().equals("class")) {
			if (rObj instanceof org.eclipse.uml2.uml.Class)
				((org.eclipse.uml2.uml.Class) rObj).getOwnedAttributes()
						.add(c1);
		}
		if (values.get(3).toString().trim().equals("association")) {
			if (rObj instanceof Association){
				c1.setAssociation((org.eclipse.uml2.uml.Association) rObj);
			}
		}
		if (values.get(3).toString().trim().equals("owningAssociation")) {
			if (rObj instanceof Association)
				c1.setOwningAssociation((org.eclipse.uml2.uml.Association) rObj);
		}
	}

	private void createFinalStateRelationship(FinalState c1, Object rObj,
			List<Object> values) {
		if (values.get(3).toString().trim().equals("defferableTrigger")) {
			if (rObj instanceof Trigger)
				c1.getDeferrableTriggers().add((Trigger) rObj);
		}
		if (values.get(3).toString().trim().equals("region")) {
			if (rObj instanceof Region)
				c1.getRegions().add((Region) rObj);
		}
		if (values.get(3).toString().trim().equals("connectionPoint")) {
			if (rObj instanceof Pseudostate)
				c1.getConnectionPoints().add((Pseudostate) rObj);
		}
		if (values.get(3).toString().trim().equals("submachine")) {
			if (rObj instanceof StateMachine)
				c1.setSubmachine((StateMachine) rObj);
		}
		if (values.get(3).toString().trim().equals("stateInvariants")) {
			if (rObj instanceof Constraint)
				c1.setStateInvariant((Constraint) rObj);
		}
		if (values.get(3).toString().trim().equals("exit")) {
			if (rObj instanceof Behavior)
				c1.setExit((Behavior) rObj);
		}
		if (values.get(3).toString().trim().equals("doActivity")) {
			if (rObj instanceof Behavior)
				c1.setDoActivity((Behavior) rObj);
		}
		if (values.get(3).toString().trim().equals("entry")) {
			if (rObj instanceof Behavior)
				c1.setEntry((Behavior) rObj);
		}
		if (values.get(3).toString().trim().equals("container")) {
			if (rObj instanceof Region)
				c1.setContainer((Region) rObj);
		}
	}

	private void createTimeEventRelationship(TimeEvent c1, Object rObj,
			List<Object> values) {
		if (values.get(3).toString().trim().equals("when")) {
			if (rObj instanceof TimeExpression){
			//	c1.setWhen((TimeExpression) rObj);
				
			TimeExpression te=c1.createWhen(((TimeExpression) rObj).getName(), ((TimeExpression) rObj).getType());
		//	te.setExpr(((TimeExpression) rObj).getExpr());
			
			 LiteralString lt =(LiteralString) te.createExpr(((TimeExpression) rObj).getExpr().getName(), ((TimeExpression) rObj).getExpr().getType(), ((TimeExpression) rObj).getExpr().eClass());
		              lt.setValue((((TimeExpression) rObj).getExpr()).stringValue());
			}}

	}

	private void createChangeEventRelationship(ChangeEvent c1, Object rObj,
			List<Object> values) {
		if (values.get(3).toString().trim().equals("changeExpression")) {
			if (rObj instanceof OpaqueExpression)
				c1.setChangeExpression((OpaqueExpression) rObj);
		}

	}

	private void createTimeExpressionRelationship(TimeExpression c1,
			Object rObj, List<Object> values) {
		if (values.get(3).toString().trim().equals("expr")) {
			if (rObj instanceof LiteralString){
			//	c1.setExpr((LiteralString) rObj);
			LiteralString v=(LiteralString)c1.createExpr(((LiteralString)rObj).getName(), ((LiteralString)rObj).getType(), ((LiteralString)rObj).eClass());
				v.setValue(((LiteralString)rObj).getValue());//c1.createExpr(((TimeExpression) rObj).getExpr().getName(), ((TimeExpression) rObj).getExpr().getType(), ((TimeExpression) rObj).getExpr().eClass());
		}}

	}

	private void createOperationRelationship(Operation c1, Object rObj,
			List<Object> values) {
		if (values.get(3).toString().trim().equals("type")) {
			if (rObj instanceof Type)
				c1.setType((Type) rObj);
		}
		if (values.get(3).toString().trim().equals("class")) {
			if (rObj instanceof Type)
				c1.setClass_((org.eclipse.uml2.uml.Class) rObj);
			// c1.getClass_().getOperations().add(c1);
		}

	}

	private void createCallEventRelationship(CallEvent c1, Object rObj,
			List<Object> values) {
		if (values.get(3).toString().trim().equals("operation")) {
			if (rObj instanceof Operation)
				c1.setOperation((Operation) rObj);
		}

	}

	private void createSignalEventRelationship(SignalEvent c1, Object rObj,
			List<Object> values) {
		if (values.get(3).toString().trim().equals("signal")) {
			if (rObj instanceof Signal)
				c1.setSignal((Signal) rObj);
		}
	}

	private void createAnyRecieveEventRelationship(AnyReceiveEvent c1,
			Object rObj, List<Object> values) {
		// TODO Auto-generated method stub

	}

	private void createOpaqueExpressionRelationship(OpaqueExpression c1,
			Object rObj, List<Object> values) {
		// TODO Auto-generated method stub

	}
	private void createLiteralStringRelationship(LiteralString c1,
			Object rObj, List<Object> values) {
		// TODO Auto-generated method stub

	}

	private void createReceptionRelationship(Reception c1, Object rObj,
			List<Object> values) {
		if (values.get(3).toString().trim().equals("signal")) {
			if (rObj instanceof Signal)
				c1.setSignal((Signal) rObj);
		}
	}

	private void createSignalRelationship(Signal c1, Object rObj,
			List<Object> values) {
		// TODO Auto-generated method stub

	}

	private void createPrimitiveTypeRelationship(PrimitiveType c1, Object rObj,
			List<Object> values) {
		// TODO Auto-generated method stub

	}

	private void createClassRelationship(org.eclipse.uml2.uml.Class c1,
			Object rObj, List<Object> values) {
		if (values.get(3).toString().trim().equals("ownedAttribute")) {
			if (rObj instanceof Property){
				//c1.createOwnedAttribute(((Property) rObj).getName(), ((Property) rObj).getType(),((Property) rObj).eClass());
				c1.getOwnedAttributes().add((Property) rObj);
				//((Property) rObj).get
				//((Property) rObj).getClass_().setPackage(c1.getPackage());
				}
		} else if (values.get(3).toString().trim().equals("ownedBehavior")) {
			if (rObj instanceof Behavior)
				c1.getOwnedBehaviors().add((Behavior) rObj);
		} else if (values.get(3).toString().trim().equals("ownedOperation")) {
			if (rObj instanceof Operation){
				c1.createOwnedOperation(((Operation) rObj).getName(),((Operation) rObj).getKeywords(),((Operation) rObj).getRaisedExceptions());
	//			c1.getOwnedOperations().add((Operation) rObj);
	
			}
		} else if (values.get(3).toString().trim().equals("ownedReception")) {
			if (rObj instanceof Reception)
				c1.getOwnedReceptions().add((Reception) rObj);
		} else if (values.get(3).toString().equals("stereotype5")) {
			ProfiledStateMachine SM = new ProfiledStateMachine();
			if (!values.get(4).toString().equals("")) {
				String StereoName="";
				if(values.get(4).toString().equals("Sys"))
					StereoName="System";
				if(values.get(4).toString().equals("Con"))
					StereoName="Context";
				Stereotype stereo= SM.retrieveStereoType(profile, StereoName);
			    SM.applyStereotype(c1, stereo);
			}
		}
	}////

	private void createPackageRelationship(Package c1, Object rObj,
			List<Object> values) {
		if (values.get(3).toString().trim().equals("packagedElement")) {
			if (rObj instanceof PackageableElement)
				c1.getPackagedElements().add((PackageableElement) rObj);
		}
	}

	public void retrieveTupple(ArrayList<List<Object>> result) {
		// TODO Auto-generated method stub
		QueryTuppleMap=result;
	}

	private void createAllXMIClassRelationships(HashMap<String, Object> resourcesToAdd, HashMap<String, Boolean> shouldResourcesAdd) {
		for (List<Object> values : QueryTuppleMap) {
//			if (values.get(0).toString().equals("Class")) {
//				Class c1 = (Class) resourcesToAdd
//						.get(values.get(1));
//				if (values.size() > 2) {				
//					Object rObj = resourcesToAdd.get(values.get(4));
//					if (rObj != null && c1 != null)
//						createXMIClassRelationship(c1, rObj, values);
//				}
//				resourcesToAdd.put(values.get(1).toString(), c1);
//				shouldResourcesAdd.put(values.get(1).toString(), true);
//			}
//			if (values.get(0).toString().equals("Association")) {
//				Association c1 = (Association) resourcesToAdd
//						.get(values.get(1));
//				if (values.size() > 2) {				
//					Object rObj = resourcesToAdd.get(values.get(4));
//					if (rObj != null && c1 != null)
//						createXMIAssociationRelationship(c1, rObj, values);
//				}
//				resourcesToAdd.put(values.get(1).toString(), c1);
//				shouldResourcesAdd.put(values.get(1).toString(), true);
//			}
			if (values.get(0).toString().equals("Attribute")) {
				Attribute c1 = (Attribute) resourcesToAdd
						.get(values.get(1));
				if (values.size() > 2) {				
					Object rObj = resourcesToAdd.get(values.get(4));
					if (rObj != null && c1 != null)
						createXMIAttributeRelationship(c1, rObj, values);
				}
				resourcesToAdd.put(values.get(1).toString(), c1);
				shouldResourcesAdd.put(values.get(1).toString(), false);
			}
	}
  }
    
	private void createAllXMIClassRelationships(HashMap<String, Object> resourcesToAdd, HashMap<String, Boolean> shouldResourcesAdd, String value) {
		for (List<Object> values : QueryTuppleMap) {
			if (values.get(0).toString().equals(value)) {
				if (values.get(0).toString().equals("Class")) {
					Class c1 = (Class) resourcesToAdd
							.get(values.get(1));
					if (values.size() > 2) {				
						Object rObj = resourcesToAdd.get(values.get(4));
						if (rObj != null && c1 != null)
							createXMIClassRelationship(c1, rObj, values);
					}
					resourcesToAdd.put(values.get(1).toString(), c1);
					shouldResourcesAdd.put(values.get(1).toString(), true);
				}
				if (values.get(0).toString().equals("Association")) {
					Association c1 = (Association) resourcesToAdd
							.get(values.get(1));
					if (values.size() > 2) {				
						Object rObj = resourcesToAdd.get(values.get(4));
						if (rObj != null && c1 != null)
							createXMIAssociationRelationship(c1, rObj, values);
					}
					resourcesToAdd.put(values.get(1).toString(), c1);
					shouldResourcesAdd.put(values.get(1).toString(), true);
				}
			}
		}
	}

	private void createXMIAttributeRelationship(Attribute c1, Object rObj,
			List<Object> values) {
		if (values.get(3).toString().trim().equals("type")) {
			if (rObj instanceof Classifier)
				c1.setType((Classifier)rObj);
		} 

		
	}

	private void createXMIAssociationRelationship(Association c1, Object rObj,
			List<Object> values) {
		if (values.get(3).toString().trim().equals("src")) {
			if (rObj instanceof Class)
				c1.setSrc((Class)rObj);
		} 
		else 
		 if (values.get(3).toString().trim().equals("dest")) {
			 if (rObj instanceof Class)
					c1.setDest((Class)rObj);
		}
		
	}

	private void createXMIClassRelationship(Class c1, Object rObj,
			List<Object> values) {
		if (values.get(3).toString().trim().equals("parent")) {
			if (rObj instanceof Class)
				c1.setParent((Class)rObj);
		} 
		else 
		 if (values.get(3).toString().trim().equals("attrs")) {
			if (rObj instanceof Attribute){
				     //c1.getAttrs().
				SimpleClassPackage.eINSTANCE.eClass();
				// Retrieve the default factory singleton
				SimpleClassFactory factory = SimpleClassFactory.eINSTANCE;
				Attribute at1 = factory.createAttribute();
				at1.setIs_primary(((Attribute) rObj).isIs_primary());
				at1.setName(((Attribute) rObj).getName());
				at1.setType(((Attribute) rObj).getType());
				c1.getAttrs().add((Attribute) at1);
			}
				
		}
		
		
	}


	
}