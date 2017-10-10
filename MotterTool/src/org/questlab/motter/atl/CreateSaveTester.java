package org.questlab.motter.atl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.AnyReceiveEvent;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.ChangeEvent;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.Feature;
import org.eclipse.uml2.uml.FinalState;
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
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.uml2.uml.Vertex;
import org.eclipse.uml2.uml.VisibilityKind;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.questlab.motter.uml2instance.ProfiledStateMachine;
import org.questlab.motter.uml2instance.ProfiledStateMachine.MessageEventkind;

import snt.oclsolver.datatypes.EnumerationValueTuple;
import snt.oclsolver.datatypes.PrimitiveValueTuple;
import snt.oclsolver.tuples.ClassifierTuple;
import snt.oclsolver.tuples.ClassifierValueTuple;
import snt.oclsolver.tuples.IPropertyTuple;
import snt.oclsolver.tuples.ValueTuple;
import SimpleClass.*;
import SimpleClass.Class;

public class CreateSaveTester {

	public ArrayList<List<Object>> QueryTuppleMap;

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

		// Adding primitive Types
		PrimitiveDataType integerPrimitive = factory.createPrimitiveDataType();
		integerPrimitive.setName("Integer");
		PrimitiveDataType stringPrimitive = factory.createPrimitiveDataType();
		stringPrimitive.setName("String");
		PrimitiveDataType booleanPrimitive = factory.createPrimitiveDataType();
		booleanPrimitive.setName("Boolean");
		HashMap<String, Object> resourcesToAdd = new HashMap<String, Object>();
		HashMap<String, List<Object>> resourcesListToAdd = new HashMap<String, List<Object>>();
		HashMap<String, Boolean> shouldResourcesAdd = new HashMap<String, Boolean>();

		for (List<Object> values : QueryTuppleMap) {
			if (values.get(0).toString().equals("Class")) {
				Class c1 = factory.createClass();
				c1.setName(values.get(1).toString());
				if (values.get(3).toString().equals("is_persistent"))
					c1.setIs_persistent((Boolean) values.get(4));
				resourcesToAdd.put(values.get(1).toString(), c1);
				resourcesListToAdd.put(values.get(1).toString(), values);
				shouldResourcesAdd.put(values.get(1).toString(), false);
			}
			if (values.get(0).toString().equals("Association")) {
				Association a1 = factory.createAssociation();
				a1.setName(values.get(1).toString());
				resourcesToAdd.put(values.get(1).toString(), a1);
				resourcesListToAdd.put(values.get(1).toString(), values);
				shouldResourcesAdd.put(values.get(1).toString(), false);
				// a1.setDest(value);
				// a1.setSrc(value);
			}
			if (values.get(0).toString().equals("Attribute")) {
				Attribute at1 = factory.createAttribute();
				at1.setName(values.get(1).toString());
				if (values.get(3).toString().equals("is_primary"))
					at1.setIs_primary((Boolean) values.get(4));
				// if (values.get(3).toString().equals("type"))
				// at1.setType((Classifier)values.get(4));

				resourcesToAdd.put(values.get(1).toString(), at1);
				resourcesListToAdd.put(values.get(1).toString(), values);
				shouldResourcesAdd.put(values.get(1).toString(), false);// ///////////////////
				// at1.setIs_primary(true);
				// at1.setType(StringPrimitive);
			}
			if (values.get(0).toString().equals("PrimitiveDataType")) {
				PrimitiveDataType pt1 = factory.createPrimitiveDataType();
				pt1.setName(values.get(1).toString());

				resourcesToAdd.put(values.get(1).toString(), pt1);
				resourcesListToAdd.put(values.get(1).toString(), values);
				shouldResourcesAdd.put(values.get(1).toString(), false);
				// at1.setIs_primary(true);
				// at1.setType(StringPrimitive);
			}
			if (values.get(0).toString().equals("Classifier")) {
				Classifier cl1 = factory.createClassifier();
				cl1.setName(values.get(1).toString());

				resourcesToAdd.put(values.get(1).toString(), cl1);
				resourcesListToAdd.put(values.get(1).toString(), values);
				shouldResourcesAdd.put(values.get(1).toString(), false);
				// at1.setIs_primary(true);
				// at1.setType(StringPrimitive);
			}

			System.out.println("Values = " + values + "n");
		}
		for (Map.Entry<String, Object> entry : resourcesToAdd.entrySet()) {
			// System.out.println("Key : " + entry.getKey() + " Value : "
			// + entry.getValue());
			String topKey = entry.getKey();

			Object obj = resourcesToAdd.get(topKey);
			List<Object> values = resourcesListToAdd.get(topKey);

			if (obj instanceof Class) {
				Class c1 = (Class) obj;
				if (values.size() > 5) {
					if (values.get(5).toString().equals("")) {
						shouldResourcesAdd.put(topKey, true);
					} else {
						String fKey = values.get(5).toString();
						Object objtoEdit = resourcesToAdd.get(fKey);
						if (objtoEdit instanceof Class) {
							Class cEdit = (Class) objtoEdit;
							if (values.get(6).toString().equals("parent")) {
								cEdit.setParent(c1);
								resourcesToAdd.put(fKey, cEdit);
								shouldResourcesAdd.put(topKey, true);
							}
						}
						if (objtoEdit instanceof Association) {
							Association aEdit = (Association) objtoEdit;
							if (values.get(6).toString().equals("src"))
								aEdit.setSrc(c1);
							if (values.get(6).toString().equals("dest"))
								aEdit.setDest(c1);

							resourcesToAdd.put(fKey, aEdit);
							shouldResourcesAdd.put(topKey, true);

						}
					}
				} else {
					shouldResourcesAdd.put(topKey, true);
				}

			}
			if (obj instanceof Association) {
				Association a1 = (Association) obj;
				shouldResourcesAdd.put(topKey, true);
			}
			if (obj instanceof Attribute) {
				Attribute at1 = (Attribute) obj;
				if (values.size() > 5) {
					if (values.get(5).toString().equals("")) {
						shouldResourcesAdd.put(topKey, true);
					} else {
						String fKey = values.get(5).toString();
						Object objtoEdit = resourcesToAdd.get(fKey);
						if (objtoEdit instanceof Class) {
							Class cEdit = (Class) objtoEdit;
							if (values.get(6).toString().equals("attrs")) {
								cEdit.getAttrs().add(at1);
								resourcesToAdd.put(fKey, cEdit);
								shouldResourcesAdd.put(fKey, true);
								shouldResourcesAdd.put(topKey, false);
							}
						}
						if (objtoEdit instanceof Attribute) {
							Attribute attEdit = (Attribute) objtoEdit;
							if (values.get(6).toString().equals("type")) {
								Object j = resourcesToAdd.get(values.get(5)
										.toString());
								if (j instanceof Class)
									attEdit.setType((Class) j);
								if (j instanceof PrimitiveDataType)
									attEdit.setType((PrimitiveDataType) j);
								if (j instanceof Classifier)
									attEdit.setType((Classifier) j);

								resourcesToAdd.put(fKey, attEdit);
								shouldResourcesAdd.put(fKey, true);
								shouldResourcesAdd.put(topKey, true);
							}
						}
					}
				} else {
					/*
					 * if(values.size()>2){
					 * if(values.get(2).toString().equals("")){
					 * shouldResourcesAdd.put(topKey, true); } else{ String
					 * fKey=values.get(2).toString(); Object
					 * objtoEdit=resourcesToAdd.get(fKey); if(objtoEdit
					 * instanceof Attribute){ Attribute
					 * attEdit=(Attribute)objtoEdit;
					 * if(values.get(3).toString().equals("type")){ Object
					 * j=resourcesToAdd.get(values.get(2)); if(j instanceof
					 * Classifier) attEdit.setType((Classifier)j);
					 * resourcesToAdd.put(fKey, attEdit);
					 * shouldResourcesAdd.put(fKey, true);
					 * shouldResourcesAdd.put(topKey, true); } } } }
					 */

					shouldResourcesAdd.put(topKey, false);

				}
			}
			if (obj instanceof PrimitiveDataType) {
				PrimitiveDataType pt1 = (PrimitiveDataType) obj;
				shouldResourcesAdd.put(topKey, true);
			}
			if (obj instanceof Classifier) {
				Classifier cl1 = (Classifier) obj;
				shouldResourcesAdd.put(topKey, true);
			}
		}

		for (List<Object> values : QueryTuppleMap) {
			if (values.get(0).toString().equals("Attribute")) {
				Attribute at1 = (Attribute) resourcesToAdd.get(values.get(1)
						.toString());
				if (values.get(3).toString().equals("type")) {
					Object obj = resourcesToAdd.get(values.get(4).toString());
					at1.setType((Classifier) obj);
				}

				resourcesToAdd.put(values.get(1).toString(), at1);
				// resourcesListToAdd.put(values.get(1).toString(), values);
				shouldResourcesAdd.put(values.get(1).toString(), true);
			}
		}

		for (Map.Entry<String, Boolean> entry : shouldResourcesAdd.entrySet()) {
			System.out.println("Key : " + entry.getKey() + " Value : "
					+ entry.getValue());
			if ((Boolean) shouldResourcesAdd.get(entry.getKey())) {
				EObject e = (EObject) resourcesToAdd.get(entry.getKey());
				resource.getContents().add(e);
			}

		}
		// System.out.println("Key : " + entry.getKey() + " Value : "
		// + entry.getValue());
		// c1.getAttrs().add(a1);
		// c1.getAttrs().add(a2);

		// resource.getContents().add(stringPrimitive);
		// resource.getContents().add(integerPrimitive);
		// resource.getContents().add(booleanPrimitive);

		System.out.println("XMI file at location :" + fileName
				+ " is sucessfully created");
		// now save the content.
		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void generateUMLStateMachine(String fileName, String path) {
		ProfiledStateMachine SM = new ProfiledStateMachine();
		Model epo2Model = SM.createModel("GeneratedSM");
		// Profile
		// profile=SM.loadApplyProfile("GenStateMachine/Profile.profile.uml",epo2Model);
		SM.importPrimitivePackage(epo2Model,
				UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI);
		PrimitiveType stringPrimitiveType = SM.createPrimitiveType("String");
		PrimitiveType intPrimitiveType = SM.createPrimitiveType("Integer");
		PrimitiveType boolPrimitiveType = SM.createPrimitiveType("Boolean");

		HashMap<String, Object> resourcesToAdd = new HashMap<String, Object>();
		// HashMap<String, List<Object>> resourcesListToAdd = new
		// HashMap<String, List<Object>>();
		// HashMap<String, Boolean> shouldResourcesAdd = new HashMap<String,
		// Boolean>();
		for (List<Object> values : QueryTuppleMap) {
			System.out.println("Values = " + values + ".....n");
		}

		for (List<Object> values : QueryTuppleMap) {
			// 1
			if (values.get(0).toString().equals("Class")) {
				if (!resourcesToAdd.containsKey(values.get(1).toString())) {
					org.eclipse.uml2.uml.Class c1 = SM.createClass(epo2Model,
							values.get(1).toString(), false, false,
							org.eclipse.uml2.uml.VisibilityKind.PUBLIC_LITERAL,
							null);
					if (values.size() > 2) {
						if (values.get(3).toString().equals("name")) {
							if (!values.get(4).toString().equals(""))
								c1.setName(values.get(4).toString());
						}
						if (values.get(3).toString().equals("stereotype")) {
							if (!values.get(4).toString().equals("")) {
								// Stereotype stereo=
								// SM.retrieveStereoType(profile,
								// values.get(4).toString());
								// SM.applyStereotype(c1, stereo);
							}
						}
						if (values.get(3).toString().equals("isActive"))
							c1.setIsActive((Boolean) values.get(4));
						if (values.get(3).toString().equals("isAbstract"))
							c1.setIsAbstract((Boolean) values.get(4));
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
									null, null);
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
					if (values.size() > 2)
						if (values.get(3).toString().equals("name")) {
							if (!values.get(4).toString().equals(""))
								c1.setName(values.get(4).toString());
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
									null, 0, 1);
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
							.createAssociationSimple(values.get(1).toString());
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
					if (values.size() > 2)
						if (values.get(3).toString().equals("name")) {
							if (!values.get(4).toString().equals(""))
								c1.setName(values.get(4).toString());
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
							if (!values.get(4).toString().equals(""))
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
							values.get(1).toString(), null, null, null);
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

		// now save the content.

		SM.saveModel(path, epo2Model, fileName);

		System.out.println("UML file at location :" + fileName
				+ " is sucessfully created");

	}

	private void createAllDiagramRelationships(
			HashMap<String, Object> resourcesToAdd) {
		for (List<Object> values : QueryTuppleMap) {
			// 1
			if (values.get(0).toString().equals("Class")) {
				org.eclipse.uml2.uml.Class c1 = (org.eclipse.uml2.uml.Class) resourcesToAdd
						.get(values.get(1));
				if (values.size() > 2) {
					Object rObj = resourcesToAdd.get(values.get(4));
					if (rObj != null && c1 != null)
						createClassRelationship(c1, rObj, values);
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
			if (values.get(0).toString().equals("Reception")) {
				org.eclipse.uml2.uml.Reception c1 = (org.eclipse.uml2.uml.Reception) resourcesToAdd
						.get(values.get(1));
				if (values.size() > 2) {
					Object rObj = resourcesToAdd.get(values.get(4));
					if (rObj != null && c1 != null)
						createReceptionRelationship(c1, rObj, values);
				}
			}// 6
			if (values.get(0).toString().equals("OpaqueExpression")) {
				org.eclipse.uml2.uml.OpaqueExpression c1 = (org.eclipse.uml2.uml.OpaqueExpression) resourcesToAdd
						.get(values.get(1));
				if (values.size() > 2) {
					Object rObj = resourcesToAdd.get(values.get(4));
					if (rObj != null && c1 != null)
						createOpaqueExpressionRelationship(c1, rObj, values);
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
			}// 10
			if (values.get(0).toString().equals("Operation")) {
				org.eclipse.uml2.uml.Operation c1 = (Operation) resourcesToAdd
						.get(values.get(1));
				if (values.size() > 2) {
					Object rObj = resourcesToAdd.get(values.get(4));
					if (rObj != null && c1 != null)
						createOperationRelationship(c1, rObj, values);
				}
			}// 11
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
					Object rObj = resourcesToAdd.get(values.get(4));
					if (rObj != null && c1 != null)
						createPropertyRelationship(c1, rObj, values);
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
			if (values.get(0).toString().equals("State")) {
				org.eclipse.uml2.uml.State c1 = (State) resourcesToAdd
						.get(values.get(1));
				if (values.size() > 2) {
					Object rObj = resourcesToAdd.get(values.get(4));
					if (rObj != null && c1 != null)
						createStateRelationship(c1, rObj, values);
				}
			}// 20
			if (values.get(0).toString().equals("Transition")) {
				org.eclipse.uml2.uml.Transition c1 = (Transition) resourcesToAdd
						.get(values.get(1));
				if (values.size() > 2) {
					Object rObj = resourcesToAdd.get(values.get(4));
					if (rObj != null && c1 != null)
						createTransitionRelationship(c1, rObj, values);
				}
			}// 21
			if (values.get(0).toString().equals("Region")) {
				org.eclipse.uml2.uml.Region c1 = (Region) resourcesToAdd
						.get(values.get(1));
				if (values.size() > 2) {
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
			if (values.get(0).toString().equals("Pseudostate")) {
				org.eclipse.uml2.uml.Pseudostate c1 = (Pseudostate) resourcesToAdd
						.get(values.get(1));
				if (values.size() > 2) {
					Object rObj = resourcesToAdd.get(values.get(4));
					if (rObj != null && c1 != null)
						createPseudoStateRelationship(c1, rObj, values);
				}
			}// 24
			if (values.get(0).toString().equals("Constraint")) {
				org.eclipse.uml2.uml.Constraint c1 = (Constraint) resourcesToAdd
						.get(values.get(1));
				if (values.size() > 2) {
					Object rObj = resourcesToAdd.get(values.get(4));
					if (rObj != null && c1 != null)
						createConstraintRelationship(c1, rObj, values);
				}
			}
		}
	}

	private void createConstraintRelationship(Constraint c1, Object rObj,
			List<Object> values) {
		if (values.get(3).toString().trim().equals("specification")) {
			if (rObj instanceof ValueSpecification)
				c1.setSpecification((ValueSpecification) rObj);
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
				c1.createRegion(((Region) rObj).getName());
				c1.getRegions().add((Region) rObj);
			}
		}
		if (values.get(3).toString().trim().equals("connectionPoint")) {
			if (rObj instanceof Pseudostate) {
				c1.createConnectionPoint(((Pseudostate) rObj).getName());
				c1.getConnectionPoints().add((Pseudostate) rObj);
			}
		}
	}

	private void createRegionRelationship(Region c1, Object rObj,
			List<Object> values) {
		if (values.get(3).toString().trim().equals("transition")) {
			if (rObj instanceof Transition) {

				c1.createSubvertex(((Transition) rObj).getTarget().getName(),
						((Transition) rObj).getTarget().eClass());
				c1.createSubvertex(((Transition) rObj).getSource().getName(),
						((Transition) rObj).getSource().eClass());

				Transition t = c1.createTransition(
						((Transition) rObj).getName(),
						((Transition) rObj).eClass());
				t.setSource(c1.getSubvertex(((Transition) rObj).getSource()
						.getName()));
				t.setTarget(c1.getSubvertex(((Transition) rObj).getTarget()
						.getName()));
				OpaqueBehavior b = (OpaqueBehavior) t.createEffect(
						((Transition) rObj).getEffect().getName(),
						((Transition) rObj).getEffect().eClass());

				b.getLanguages().addAll(
						((OpaqueBehavior) ((Transition) rObj).getEffect())
								.getLanguages());
				b.getBodies().addAll(
						((OpaqueBehavior) ((Transition) rObj).getEffect())
								.getBodies());
				
				if (((Transition) rObj).getTriggers() != null) {
					for (Trigger tt : ((Transition) rObj).getTriggers()) {
						Trigger k = t.createTrigger(tt.getName());						
						if(tt.getEvent()!=null){
						k.setEvent(tt.getEvent());
						}
						
					}
				}

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
				c1.createEffect(((OpaqueBehavior) rObj).getName(),
						((OpaqueBehavior) rObj).eClass());
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
				c1.getTriggers().add(t);
			}
		}
		if (values.get(3).toString().trim().equals("ownedRule")) {
			if (rObj instanceof Constraint) {
				// c1.getOwnedRules().add((Constraint) rObj);
				c1.createOwnedRule(((Constraint) rObj).getName(),
						((Constraint) rObj).eClass());
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

	private void createTriggerRelationship(Trigger c1, Object rObj,
			List<Object> values) {
		if (values.get(3).toString().trim().equals("event")) {
			if (rObj instanceof Event) {
				c1.setEvent((Event) rObj);

				System.out.println("Event association completed between :  "
						+ c1.getName() + " ---- " + ((Event) rObj).getName());
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
			if (rObj instanceof Property)
				c1.getMemberEnds().add((Property) rObj);

		}
		if (values.get(3).toString().trim().equals("navigableOwnedEnd")) {
			if (rObj instanceof Property) {
				c1.getNavigableOwnedEnds().add((Property) rObj);
				c1.createNavigableOwnedEnd(((Property) rObj).getName(),
						(Type) ((Property) rObj).getType(),
						((Property) rObj).eClass());
			}
		}
		if (values.get(3).toString().trim().equals("OwnedEnd")) {
			if (rObj instanceof Property)
				c1.getOwnedEnds().add((Property) rObj);
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
			if (rObj instanceof Association)
				c1.setAssociation((org.eclipse.uml2.uml.Association) rObj);
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
			if (rObj instanceof TimeExpression)
				c1.setWhen((TimeExpression) rObj);
		}

	}

	private void createChangeEventRelationship(ChangeEvent c1, Object rObj,
			List<Object> values) {
		if (values.get(3).toString().trim().equals("changeExpression")) {
			if (rObj instanceof ValueSpecification)
				c1.setChangeExpression((ValueSpecification) rObj);
		}

	}

	private void createTimeExpressionRelationship(TimeExpression c1,
			Object rObj, List<Object> values) {
		if (values.get(3).toString().trim().equals("expr")) {
			if (rObj instanceof ValueSpecification)
				c1.setExpr((ValueSpecification) rObj);
		}

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
			if (rObj instanceof Property)
				c1.getOwnedAttributes().add((Property) rObj);
		} else if (values.get(3).toString().trim().equals("ownedBehavior")) {
			if (rObj instanceof StateMachine)
				c1.getOwnedBehaviors().add((Behavior) rObj);
		} else if (values.get(3).toString().trim().equals("ownedOperation")) {
			if (rObj instanceof Operation)
				c1.getOwnedOperations().add((Operation) rObj);
		} else if (values.get(3).toString().trim().equals("ownedReception")) {
			if (rObj instanceof Reception)
				c1.getOwnedReceptions().add((Reception) rObj);
		}
	}

	private void createPackageRelationship(Package c1, Object rObj,
			List<Object> values) {
		if (values.get(3).toString().trim().equals("packagedElement")) {
			if (rObj instanceof PackageableElement)
				c1.getPackagedElements().add((PackageableElement) rObj);
		}
	}

	public ArrayList<List<Object>> retrieveTuppleForState(
			ArrayList<ClassifierTuple> queryVariables) {
		System.out
				.println("----------------------RESULT-----------------------");

		QueryTuppleMap = new ArrayList<List<Object>>();
		for (ClassifierTuple ct : queryVariables) {

			ArrayList<ClassifierValueTuple> cvts = ct.getObjectTuples();
			ArrayList<IPropertyTuple> propTup = ct.getValueTuples();
			for (ClassifierValueTuple cvt : cvts) {

				ArrayList<ValueTuple> vts = cvt.getAttributeValues();
				if (vts.size() == 0) {
					Object Class = ct.getClassName(); // size of objects
														// constraint
					Object obj = cvt.getObjectName();
					// System.out.print(Class + " ");
					// System.out.print(obj + " ");

					List<Object> objList = new ArrayList<Object>();
					objList.add(Class);
					objList.add(obj);
					/*
					 * if(obj.toString().contains("-")){ String
					 * org[]=obj.toString().split("-"); //
					 * System.out.println(org
					 * [0]+"------------"+org[1]+"------------"+org[2]);
					 * objList.add(org[0]); objList.add(org[2]); }
					 */
					QueryTuppleMap.add(objList);
					// System.out.println("\n");
				}
				for (ValueTuple vt : vts) {
					if (vt instanceof PrimitiveValueTuple) {
						Object val;
						if (propTup.contains(vt.getRelatedProperty())) {
							PrimitiveValueTuple ivt = (PrimitiveValueTuple) vt;
							if (ivt instanceof EnumerationValueTuple) {
								val = ((EnumerationLiteral) ivt.getValue())
										.getQualifiedName();
							} else {
								val = ivt.getValue();
							}
							Object attribute = ivt.getRelatedProperty()
									.getRoleeName();
							Object type = ivt.getRelatedProperty().getType();
							Object obj = cvt.getObjectName();
							Object Class = ct.getClassName();// primitive type
																// constraints

							// System.out.print(Class + " ");
							// System.out.print(obj + " ");
							// System.out.print(type + " ");
							// System.out.print(attribute + " ");
							// System.out.print(val + " ");
							// System.out.print("\n");

							List<Object> objList = new ArrayList<Object>();
							objList.add(Class);
							objList.add(obj);
							objList.add(type);
							objList.add(attribute);
							objList.add(val);
							// if (obj.toString().contains("-")) {
							// String org[] = obj.toString().split("-");
							// //
							// System.out.println(org[0]+"------------"+org[1]+"------------"+org[2]);
							// objList.add(org[0]);
							// objList.add(org[2]);
							// }

							QueryTuppleMap.add(objList);

						}
					} else {

						if (propTup.contains(vt.getRelatedProperty())) {
							ClassifierValueTuple ivt = (ClassifierValueTuple) vt;
							Object val = ivt.getObjectName();
							Object attribute = ivt.getRelatedProperty()
									.getRoleeName();
							Object type = ivt.getRelatedProperty().getType();
							Object obj = cvt.getObjectName();
							Object Class = ct.getClassName();

							// System.out.print(Class + " ");
							// System.out.print(obj + " "); // navigational
							// // constraints
							// System.out.print(type + " ");
							// System.out.print(attribute + " ");
							// System.out.print(val + " ");
							// System.out.print("\n");
							List<Object> objList = new ArrayList<Object>();
							objList.add(Class);
							objList.add(obj);
							objList.add(type);
							objList.add(attribute);
							objList.add(val);
							// if (obj.toString().contains("-")) {
							// String org[] = obj.toString().split("-");
							// //
							// System.out.println(org[0]+"------------"+org[1]+"------------"+org[2]);
							// objList.add(org[0]);
							// objList.add(org[2]);
							// }
							QueryTuppleMap.add(objList);
						}
					}
				}
			}
			// System.out.println();

		}
		return QueryTuppleMap;
	}

	public ArrayList<List<Object>> retrieveTupple(
			ArrayList<ClassifierTuple> queryVariables) {
		System.out
				.println("----------------------RESULT-----------------------");

		QueryTuppleMap = new ArrayList<List<Object>>();
		for (ClassifierTuple ct : queryVariables) {

			ArrayList<ClassifierValueTuple> cvts = ct.getObjectTuples();
			ArrayList<IPropertyTuple> propTup = ct.getValueTuples();
			for (ClassifierValueTuple cvt : cvts) {

				ArrayList<ValueTuple> vts = cvt.getAttributeValues();
				if (vts.size() == 0) {
					Object Class = ct.getClassName(); // size of objects
														// constraint
					Object obj = cvt.getObjectName();
					System.out.print(Class + " ");
					System.out.print(obj + " ");

					List<Object> objList = new ArrayList<Object>();
					objList.add(Class);
					objList.add(obj);
					/*
					 * if(obj.toString().contains("-")){ String
					 * org[]=obj.toString().split("-"); //
					 * System.out.println(org
					 * [0]+"------------"+org[1]+"------------"+org[2]);
					 * objList.add(org[0]); objList.add(org[2]); }
					 */
					QueryTuppleMap.add(objList);
					System.out.println("\n");
				}
				for (ValueTuple vt : vts) {
					if (vt instanceof PrimitiveValueTuple) {
						Object val;
						if (propTup.contains(vt.getRelatedProperty())) {
							PrimitiveValueTuple ivt = (PrimitiveValueTuple) vt;
							if (ivt instanceof EnumerationValueTuple) {
								val = ((EnumerationLiteral) ivt.getValue())
										.getQualifiedName();
							} else {
								val = ivt.getValue();
							}
							Object attribute = ivt.getRelatedProperty()
									.getRoleeName();
							Object type = ivt.getRelatedProperty().getType();
							Object obj = cvt.getObjectName();
							Object Class = ct.getClassName();// primitive type
																// constraints

							System.out.print(Class + " ");
							System.out.print(obj + " ");
							System.out.print(type + " ");
							System.out.print(attribute + " ");
							System.out.print(val + " ");
							System.out.print("\n");

							List<Object> objList = new ArrayList<Object>();
							objList.add(Class);
							objList.add(obj);
							objList.add(type);
							objList.add(attribute);
							objList.add(val);
							if (obj.toString().contains("-")) {
								String org[] = obj.toString().split("-");
								// System.out.println(org[0]+"------------"+org[1]+"------------"+org[2]);
								objList.add(org[0]);
								objList.add(org[2]);
							}

							QueryTuppleMap.add(objList);

						}
					} else {

						if (propTup.contains(vt.getRelatedProperty())) {
							ClassifierValueTuple ivt = (ClassifierValueTuple) vt;
							Object val = ivt.getObjectName();
							Object attribute = ivt.getRelatedProperty()
									.getRoleeName();
							Object type = ivt.getRelatedProperty().getType();
							Object obj = cvt.getObjectName();
							Object Class = ct.getClassName();

							System.out.print(Class + " ");
							System.out.print(obj + " "); // navigational
															// constraints
							System.out.print(type + " ");
							System.out.print(attribute + " ");
							System.out.print(val + " ");
							System.out.print("\n");
							List<Object> objList = new ArrayList<Object>();
							objList.add(Class);
							objList.add(obj);
							objList.add(type);
							objList.add(attribute);
							objList.add(val);
							if (obj.toString().contains("-")) {
								String org[] = obj.toString().split("-");
								// System.out.println(org[0]+"------------"+org[1]+"------------"+org[2]);
								objList.add(org[0]);
								objList.add(org[2]);
							}
							QueryTuppleMap.add(objList);
						}
					}
				}
			}
			System.out.println();

		}
		return QueryTuppleMap;
	}

	
}