package org.questlab.motter.uml2instance;

import java.io.File;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.AnyReceiveEvent;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.ChangeEvent;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.FinalState;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.MessageEvent;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
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
//import org.eclipse.uml2.uml.profile.standard.Specification;
import org.eclipse.uml2.uml.resource.UMLResource;



public class ProfiledStateMachine {
	//public  ResourceSet RESOURCE_SET;
	public  enum MessageEventkind {
		AnyReciveEvent, SignalEvent, CallEvent
	};
    public ProfiledStateMachine(){
/*    	String JAR_FILE_ECLIPSE_UML2_UML_RESOURCES = "jar:file:I:/workspace-xp/MotterTool_InstanceGenerator/Lib_Instance2.5-3.1/org.eclipse.uml2.uml.resources_3.1.1.v201008191505.jar!/";
		File test = new File(JAR_FILE_ECLIPSE_UML2_UML_RESOURCES.substring(9,JAR_FILE_ECLIPSE_UML2_UML_RESOURCES.length()-2)).getAbsoluteFile();
		if (!test.exists()){
		          throw new NullPointerException("JAR_FILE_ECLIPSE_UML2_UML_RESOURCES PATH ERROR, "+test.toString()+"not found!");
		}
		URI baseUri = 
				URI.createURI(JAR_FILE_ECLIPSE_UML2_UML_RESOURCES);//("jar:file:/C:<PATH>\\org.eclipse.uml2.uml.resources-3.1.0.v201005031530.jar!/");
				URIConverter.URI_MAP.put(URI.createURI( UMLResource.LIBRARIES_PATHMAP ), 
				baseUri.appendSegment( "libraries" ).appendSegment( "" ));
				URIConverter.URI_MAP.put(URI.createURI( UMLResource.METAMODELS_PATHMAP 
				), baseUri.appendSegment( "metamodels" ).appendSegment( "" ));
				URIConverter.URI_MAP.put(URI.createURI( UMLResource.PROFILES_PATHMAP ), 
				baseUri.appendSegment( "profiles" ).appendSegment( "" ));*/
	
    }
	public MessageEventkind GetMessageKind(){
		MessageEventkind kk=MessageEventkind.AnyReciveEvent;
		return kk;
	}
	public static String path = "GenStateMachine";

	public  void importPrimitivePackage(Package package_, String uri) {
		//UMLReader reader = new UMLReader();
		ProfileTest PT=new ProfileTest();
		Model umlLibrary = (Model) PT.load(URI.createURI(uri));
		package_.createPackageImport(umlLibrary);
		System.out.println("Primitive package '" + umlLibrary.getQualifiedName()
				+ "' imported.");
	}
	
	public  PrimitiveType createPrimitiveType(String name) {
		//UMLReader reader = new UMLReader();
		ProfileTest PT=new ProfileTest();
		Model umlLibrary = (Model) PT.load(URI.createURI(UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI));
		PrimitiveType primitiveType = (PrimitiveType) umlLibrary.getOwnedType(name);
		System.out.println("Primitive type '" + primitiveType.getQualifiedName()+ "' created.");
		return primitiveType;
	}

	public Model createModel(String name) {
		Model model = UMLFactory.eINSTANCE.createModel();
		model.setName(name);
		System.out.println("Model '" + model.getQualifiedName() + "' created.");

		return model;
	}

	public  org.eclipse.uml2.uml.Package createPackage(
			org.eclipse.uml2.uml.Package nestingPackage, String name) {
		org.eclipse.uml2.uml.Package package_ = nestingPackage
				.createNestedPackage(name);

		System.out.println("Package '" + package_.getQualifiedName()
				+ "' created.");

		return package_;
	}

	public  PrimitiveType createPrimitiveType(
			org.eclipse.uml2.uml.Package package_, String name) {
		PrimitiveType primitiveType = (PrimitiveType) package_
				.createOwnedPrimitiveType(name);

		System.out.println("Primitive type '"
				+ primitiveType.getQualifiedName() + "' created.");

		return primitiveType;
	}


	public  Enumeration createEnumeration(
			org.eclipse.uml2.uml.Package package_, String name) {
		Enumeration enumeration = (Enumeration) package_
				.createOwnedEnumeration(name);

		System.out.println("Enumeration '" + enumeration.getQualifiedName()
				+ "' created.");

		return enumeration;
	}

	public  EnumerationLiteral createEnumerationLiteral(
			Enumeration enumeration, String name) {
		EnumerationLiteral enumerationLiteral = enumeration
				.createOwnedLiteral(name);

		System.out.println("Enumeration literal '"
				+ enumerationLiteral.getQualifiedName() + "' created.");

		return enumerationLiteral;
	}

	public  org.eclipse.uml2.uml.Class createClass(
			Package package_, String name,
			boolean isAbstract, boolean isActive, VisibilityKind kind_,
			Stereotype t) {
		Class class_ = package_.createOwnedClass(name,
				isAbstract);
		class_.setIsActive(isActive);
		class_.setVisibility(kind_);
		if (t != null)
			class_.applyStereotype(t);

		System.out
				.println("Class '" + class_.getQualifiedName() + "' created.");
        Operation op=class_.createOwnedOperation(class_.getName(), null , null,null);
        
		return class_;
	}

	public Generalization createGeneralization(
			Classifier specificClassifier, Classifier generalClassifier) {
		Generalization generalization = specificClassifier
				.createGeneralization(generalClassifier);

		System.out.println("Generalization "
				+ specificClassifier.getQualifiedName() + " ->> "
				+ generalClassifier.getQualifiedName() + " created.");

		return generalization;
	}
	public  Property createAttributeSimple(String name,Type type,int lowerBound, int upperBound){		
		Property p=UMLFactory.eINSTANCE.createProperty();
		p.setName(name);
		if(type!=null)
		p.setType(type);
		p.setLower(lowerBound);
		p.setUpper(upperBound);
		return p;
	}
	
	public  Association createAssociationSimple(String name, Package k){		
		Association p=UMLFactory.eINSTANCE.createAssociation();
		p.setName(name);
		//Package k;
		k.getPackagedElements().add(p);
		k.createPackagedElement(p.getName(), p.eClass());
		return p;
	}
	
	public  Property createAttribute(org.eclipse.uml2.uml.Class class_,
			String name, Type type, int lowerBound, int upperBound) {
		

		Property attribute = class_.createOwnedAttribute(name, type,
				lowerBound, upperBound);

		StringBuffer sb = new StringBuffer();

		sb.append("Attribute '");

		sb.append(attribute.getQualifiedName());

		sb.append("' : ");

		sb.append(type.getQualifiedName());

		sb.append(" [");
		sb.append(lowerBound);
		sb.append("..");
		sb.append(LiteralUnlimitedNatural.UNLIMITED == upperBound ? "*"
				: String.valueOf(upperBound));
		sb.append("]");

		sb.append(" created.");

		System.out.println(sb.toString());

		return attribute;
	}

	public Association createAssociation(String name,Type type1,
			boolean end1IsNavigable, AggregationKind end1Aggregation,
			String end1Name, int end1LowerBound, int end1UpperBound,
			Type type2, boolean end2IsNavigable,
			AggregationKind end2Aggregation, String end2Name,
			int end2LowerBound, int end2UpperBound) {

		Association association = type1.createAssociation(end1IsNavigable,
				end1Aggregation, end1Name, end1LowerBound, end1UpperBound,
				type2, end2IsNavigable, end2Aggregation, end2Name,
				end2LowerBound, end2UpperBound);
		
		association.setName(name);

		StringBuffer sb = new StringBuffer();

		sb.append("Association ");

		if (null == end1Name || 0 == end1Name.length()) {
			sb.append('{');
			sb.append(type1.getQualifiedName());
			sb.append('}');
		} else {
			sb.append("'");
			sb.append(type1.getQualifiedName());
			sb.append(NamedElement.SEPARATOR);
			sb.append(end1Name);
			sb.append("'");
		}

		sb.append(" [");
		sb.append(end1LowerBound);
		sb.append("..");
		sb.append(LiteralUnlimitedNatural.UNLIMITED == end1UpperBound ? "*"
				: String.valueOf(end1UpperBound));
		sb.append("] ");

		sb.append(end2IsNavigable ? '<' : '-');
		sb.append('-');
		sb.append(end1IsNavigable ? '>' : '-');
		sb.append(' ');

		if (null == end2Name || 0 == end2Name.length()) {
			sb.append('{');
			sb.append(type2.getQualifiedName());
			sb.append('}');
		} else {
			sb.append("'");
			sb.append(type2.getQualifiedName());
			sb.append(NamedElement.SEPARATOR);
			sb.append(end2Name);
			sb.append("'");
		}

		sb.append(" [");
		sb.append(end2LowerBound);
		sb.append("..");
		sb.append(LiteralUnlimitedNatural.UNLIMITED == end2UpperBound ? "*"
				: String.valueOf(end2UpperBound));
		sb.append("]");

		sb.append(" created.");

		System.out.println(sb.toString());

		return association;
	}

//	public  void save(org.eclipse.uml2.uml.Package package_, URI uri) {
//		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
//		Map<String, Object> m = reg.getExtensionToFactoryMap();
//		m.put("uml", new UMLResourceFactoryImpl());
//
//		Resource resource = new ResourceSetImpl().createResource(uri);
//		resource.getContents().add(package_);
//
//		try {
//			resource.save(null);
//			System.out.println("Saved .....Diagram " + uri.toFileString());
//		} catch (Exception ioe) {
//			System.out.println(ioe);
//		}
//	}

	public  org.eclipse.uml2.uml.Behavior createDoActivity(String name,
			String body, String language) {
		org.eclipse.uml2.uml.OpaqueBehavior doActivity_ = UMLFactory.eINSTANCE
				.createOpaqueBehavior();
		doActivity_.setName(name);
		doActivity_.getBodies().add(body);
		doActivity_.getLanguages().add(language);
		System.out.println("DoActivity '" + doActivity_.getQualifiedName()
				+ "' created.");
		return doActivity_;
	}

	public  org.eclipse.uml2.uml.StateMachine createStateMachine(
			org.eclipse.uml2.uml.Class class_, String name) {
		org.eclipse.uml2.uml.StateMachine sm1 = UMLFactory.eINSTANCE
				.createStateMachine();
		sm1.setName(name);
		org.eclipse.uml2.uml.StateMachine stateMachine_=sm1;
		if(class_!=null)
		 stateMachine_ = (StateMachine) class_.createOwnedBehavior(name, sm1.eClass());
		
		System.out.println("Statemachine '" + stateMachine_.getQualifiedName()
				+ "' created.");
		return stateMachine_;
	}
	
	public  org.eclipse.uml2.uml.Region createRegionSimple(StateMachine stateMachine_, String name) {
		
		Region region_ = UMLFactory.eINSTANCE.createRegion();
		region_.setName(name);
		if(stateMachine_!=null)
			region_.setStateMachine(stateMachine_); 
		    //region_.setVisibility(VisibilityKind.PUBLIC_LITERAL);
		return region_;
	}
	public  org.eclipse.uml2.uml.Region createRegion(
			org.eclipse.uml2.uml.StateMachine stateMachine_, String name) {
		org.eclipse.uml2.uml.Region region_ = stateMachine_.createRegion(name);
		System.out.println("Region '" + region_.getQualifiedName()
				+ "' created.");
		return region_;
	}

	public  org.eclipse.uml2.uml.Region designRegion(
			org.eclipse.uml2.uml.Region region_,
			org.eclipse.uml2.uml.Vertex subVertex_,
			org.eclipse.uml2.uml.State state_,
			org.eclipse.uml2.uml.Transition transition_) {
		if (subVertex_ != null)
			region_.getSubvertices().add(subVertex_);
		// region_.createSubvertex(subVertex_.getName(), subVertex_.eClass());
		if (transition_ != null)
			region_.getTransitions().add(transition_);
		// region_.createTransition(transition_.getName(),transition_.eClass());
		if (state_ != null)
			region_.setState(state_);
		// System.out.println("RegionDesign '" + region_.getQualifiedName() +
		// "' created.");
		return region_;
	}

	public  org.eclipse.uml2.uml.State createState(String name,
			Behavior exit_, Behavior doactivity_, Behavior entry_,
			Region region_, Trigger defferabaleTrigger_,
			StateMachine subMachine_, Pseudostate connectionPoint_,
			Constraint stateInvariant_) {
		State state_ = UMLFactory.eINSTANCE.createState();
		state_.setName(name);
		if (doactivity_ != null)
			state_.setDoActivity(doactivity_);
		if (exit_ != null)
			state_.setExit(exit_);
		if (entry_ != null)
			state_.setEntry(entry_);
		if (region_ != null)
			state_.setContainer(region_);
		if (connectionPoint_ != null)
			state_.getConnectionPoints().add(connectionPoint_);
		// state_.createConnectionPoint(connectionPoint_.getName());
		if (defferabaleTrigger_ != null)
			state_.getDeferrableTriggers().add(defferabaleTrigger_);
		// state_.createDeferrableTrigger(defferabaleTrigger_.getName());
		if (subMachine_ != null)
			state_.setSubmachine(subMachine_);
		if (stateInvariant_ != null)
			state_.setStateInvariant(stateInvariant_);
		
		System.out
				.println("State '" + state_.getQualifiedName() + "' created.");
		return state_;
	}

	public  org.eclipse.uml2.uml.Pseudostate createPseudoState(
			String name, PseudostateKind kind_, StateMachine subMachine_,
			State state_) {
		Pseudostate Pseudostate_ = UMLFactory.eINSTANCE.createPseudostate();
		//kind_=PseudostateKind.INITIAL_LITERALL;
		//Pseudostate_.set
		Pseudostate_.setName(name);
		if (kind_ != null)
			Pseudostate_.setKind(kind_);
		if (state_ != null)
			Pseudostate_.setState(state_);
		if (subMachine_ != null)
			Pseudostate_.setStateMachine(subMachine_);

		System.out.println("PseudoState '" + Pseudostate_.getQualifiedName()
				+ "' created.");
		return Pseudostate_;
	}

	public  org.eclipse.uml2.uml.Constraint createSpecificationConstraint(
			String name, String language, String body) {

		Constraint constraint_ = UMLFactory.eINSTANCE.createConstraint();
		constraint_.setName(name);
		OpaqueExpression op = UMLFactory.eINSTANCE.createOpaqueExpression();
		if (language != null)
			op.getLanguages().add(language);
		if (body != null)
			op.getBodies().add(body);

		constraint_.setSpecification(op);
		System.out.println("Constraint '" + constraint_.getQualifiedName()
				+ "' created.");
		return constraint_;
	}
	public  org.eclipse.uml2.uml.Constraint createConstraint(
			String name, ValueSpecification op) {

		Constraint constraint_ = UMLFactory.eINSTANCE.createConstraint();
		constraint_.setName(name);
		if(op!=null)
		constraint_.setSpecification(op);
		return constraint_;
	}

	public  org.eclipse.uml2.uml.OpaqueExpression createOpaqueExpression(
			String name, String language, String body) {

		OpaqueExpression op = UMLFactory.eINSTANCE.createOpaqueExpression();
		op.setName(name);
		if (language != null)
			op.getLanguages().add(language);
		if (body != null)
			op.getBodies().add(body);

		System.out.println("OpaqueExpression '" + op.getQualifiedName()
				+ "' created.");
		return op;
	}
	public  org.eclipse.uml2.uml.LiteralString createLiteralString(
			String name, String value) {

		LiteralString op = UMLFactory.eINSTANCE.createLiteralString();
		op.setName(name);
		if (value != null)
			op.setValue(value);

		System.out.println("LiteralString'" + op.getQualifiedName()
				+ "' created.");
		return op;
	}

	public  org.eclipse.uml2.uml.OpaqueBehavior createOpaqueBehavior(
			String name, String language, String body) {

		OpaqueBehavior op_ = UMLFactory.eINSTANCE.createOpaqueBehavior();
		op_.setName(name);
		if (language != null)
			op_.getLanguages().add(language);
		if (body != null)
			op_.getBodies().add(body);

		System.out.println("OpaqueBehavior '" + op_.getQualifiedName()
				+ "' created.");
		return op_;
	}

	public  org.eclipse.uml2.uml.Trigger createTrigger(String name,
			Event event_) {

		Trigger trigger_ = UMLFactory.eINSTANCE.createTrigger();
		trigger_.setName(name);
		if(event_!=null)
		trigger_.setEvent(event_);
		System.out.println("Trigger '" + trigger_.getQualifiedName()
				+ "' created.");
		return trigger_;
	}

	public  org.eclipse.uml2.uml.Transition createTransition(String name,
			Vertex source_, Vertex target_, TransitionKind kind_,
			Trigger trigger_, Behavior effect_, Constraint ownedRule_) {
		Transition transition_ = UMLFactory.eINSTANCE.createTransition();
		transition_.setName(name);
		if(source_!=null)
		transition_.setSource(source_);
		if(target_!=null)
		transition_.setTarget(target_);
		if(kind_!=null)
		transition_.setKind(kind_);
		if (trigger_ != null)
			transition_.getTriggers().add(trigger_);
		if (effect_ != null)
			transition_.setEffect(effect_);
		if (ownedRule_ != null) {
			transition_.getOwnedRules().add(ownedRule_);
			ownedRule_.getConstrainedElements().add(transition_);
		}
		System.out.println("Transition '" + transition_.getQualifiedName()
				+ "' created.");
		return transition_;
	}

	public  org.eclipse.uml2.uml.FinalState createFinalState(String name,
			Behavior exit_, Behavior doactivity_, Behavior entry_,
			Region region_, Trigger defferabaleTrigger_,
			StateMachine subMachine_, Pseudostate connectionPoint_) {
		FinalState finalState_ = UMLFactory.eINSTANCE.createFinalState();
		// state_.s
		finalState_.setName(name);

		if (doactivity_ != null)
			finalState_.setDoActivity(doactivity_);
		if (exit_ != null)
			finalState_.setExit(exit_);
		if (entry_ != null)
			finalState_.setEntry(entry_);
		if (region_ != null)
			finalState_.setContainer(region_);
		if (connectionPoint_ != null)
			finalState_.getConnectionPoints().add(connectionPoint_);
		if (defferabaleTrigger_ != null)
			finalState_.getDeferrableTriggers().add(defferabaleTrigger_);
		if (subMachine_ != null)
			finalState_.setSubmachine(subMachine_);

		System.out.println("FinalState '" + finalState_.getQualifiedName()
				+ "' created.");
		return finalState_;
	}

	public  org.eclipse.uml2.uml.ChangeEvent createChangeEvent(
			String name, ValueSpecification changeExpression_, Package pack,
			VisibilityKind kind) {

		ChangeEvent chEvent_ = UMLFactory.eINSTANCE.createChangeEvent();
		chEvent_.setName(name);
		if (kind != null)
			chEvent_.setVisibility(kind);

		pack.getPackagedElements().add(chEvent_);
		if(changeExpression_!=null)
		chEvent_.setChangeExpression(changeExpression_);
		System.out.println("ChangeEvent '" + chEvent_.getQualifiedName()
				+ "' created.");
		return chEvent_;
	}

	public  org.eclipse.uml2.uml.MessageEvent createMessageEvent(
			String name, Package pack, MessageEventkind kind, Signal signal_,
			Operation operation_) {
		MessageEvent a = UMLFactory.eINSTANCE.createAnyReceiveEvent();
		if (kind == MessageEventkind.AnyReciveEvent) {
			AnyReceiveEvent anyRecEvent_ = UMLFactory.eINSTANCE
					.createAnyReceiveEvent();
			anyRecEvent_.setName(name);
			pack.getPackagedElements().add(anyRecEvent_);
			System.out.println("AnyRecieveEvent '"
					+ anyRecEvent_.getQualifiedName() + "' created.");
			a = anyRecEvent_;
		}

		if (kind == MessageEventkind.CallEvent) {
			CallEvent callEvent_ = UMLFactory.eINSTANCE.createCallEvent();
			callEvent_.setName(name);
			if(operation_!=null)
			callEvent_.setOperation(operation_);
			pack.getPackagedElements().add(callEvent_);
			System.out.println("CallEvent '" + callEvent_.getQualifiedName()
					+ "' created.");
			a = callEvent_;
		}

		if (kind == MessageEventkind.SignalEvent) {
			SignalEvent signalEvent_ = UMLFactory.eINSTANCE.createSignalEvent();
			signalEvent_.setName(name);
			if(signal_!=null)
			signalEvent_.setSignal(signal_);
			pack.getPackagedElements().add(signalEvent_);
			System.out.println("SignalEvent '"
					+ signalEvent_.getQualifiedName() + "' created.");
			a = signalEvent_;
		}

		return a;

	}

	public  org.eclipse.uml2.uml.TimeEvent createTimeEvent(String name,
			Package pack, TimeExpression timeExpression_, boolean isrelative) {

		TimeEvent timeEvent_ = UMLFactory.eINSTANCE.createTimeEvent();
		timeEvent_.setName(name);
		timeEvent_.setIsRelative(isrelative);
		timeEvent_.setWhen(timeExpression_);
		pack.getPackagedElements().add(timeEvent_);

		System.out.println("TimeEvent '" + timeEvent_.getQualifiedName()
				+ "' created.");
		return timeEvent_;
	}

	public  org.eclipse.uml2.uml.TimeExpression createTimeExpression(
			String name, ValueSpecification valueSpecification_) {

		TimeExpression timeExpression_ = UMLFactory.eINSTANCE
				.createTimeExpression();
		timeExpression_.setName(name);
		if(valueSpecification_!=null)
		timeExpression_.setExpr(valueSpecification_);
		System.out.println("TimeExpression '"
				+ timeExpression_.getQualifiedName() + "' created.");
		return timeExpression_;
	}

	public  org.eclipse.uml2.uml.Operation createOperation(Class class1,
			String name, VisibilityKind kind_) {

		Operation operation_ = UMLFactory.eINSTANCE.createOperation();
		operation_.setName(name);
		operation_.setVisibility(kind_);
		if(class1!=null)
		class1.getOwnedOperations().add(operation_);
		System.out.println("Operation '" + operation_.getQualifiedName()
				+ "' created.");
		return operation_;
	}

	public  org.eclipse.uml2.uml.Signal createSignal(String name,
			Package pack_) {

		Signal signal_ = UMLFactory.eINSTANCE.createSignal();
		signal_.setName(name);
		signal_.setPackage(pack_);
		pack_.getPackagedElements().add(signal_);
		System.out.println("Signal '" + signal_.getQualifiedName()
				+ "' created.");
		return signal_;
	}

	public  org.eclipse.uml2.uml.Reception createReception(String name,
			Class class_, Signal Sig_) {

		Reception rec_ = UMLFactory.eINSTANCE.createReception();
		rec_.setName(name);
		if(Sig_!=null)
		rec_.setSignal(Sig_);
		if(class_!=null)
		class_.getOwnedReceptions().add(rec_);
		System.out.println("Reception '" + rec_.getQualifiedName()
				+ "' created.");
		return rec_;
	}
	
    public void applyProfile(org.eclipse.uml2.uml.Package package_, Profile profile) {
        if(package_.applyProfile(profile)!=null){
        	System.out.printf("Profile '%s' applied to package '%s'.", profile.getQualifiedName(), package_.getQualifiedName());
            System.out.println();
        }
    }
    
    public EObject applyStereotype(NamedElement namedElement, Stereotype stereotype) {
    	EObject eob=null;

            eob=namedElement.applyStereotype(stereotype);
            if(eob!=null){
            System.out.printf("Stereotype '%s' applied to element '%s'.", stereotype.getQualifiedName(), namedElement.getQualifiedName());
            System.out.println();  
            }
        return eob;
    }
    
    public Stereotype retrieveStereoType(Profile profile_, final String stereotype){
    			Stereotype conceptStereotype = profile_.getOwnedStereotype(stereotype);
    	  return conceptStereotype;
    }
    
    public Object getStereotypePropertyValue(Class namedElement, Stereotype stereotype, Property property) {
        Object value = namedElement.getValue(stereotype, property.getName());
        System.out.printf("Value of stereotype property '%s' on element '%s' is %s.", property.getQualifiedName(), namedElement.getQualifiedName(), value);
        System.out.println();
        return value;
    }
    
    public void setStereotypePropertyValue(NamedElement namedElement, Stereotype stereotype, Property property, Object value) {
        Object valueToSet = value;

        if ((value instanceof String) && (property.getType() instanceof Enumeration)) {
            // Get the corresponding enumeration literal
            valueToSet = ((Enumeration) property.getType()).getOwnedLiteral((String) value);
        }
      try{
        namedElement.setValue(stereotype, property.getName(), valueToSet);
      }
      catch (Exception e){
    	  e.printStackTrace();
      }
        System.out.printf("Value of stereotype property '%s' on element '%s' set to %s.", property.getQualifiedName(),
                namedElement.getQualifiedName(), value);
        System.out.println();
    }
    
    public Profile loadApplyProfile(String profile_Path,Package pkg_)
    {  ProfileTest PT=new ProfileTest();
	 Profile profile = PT.loadProfile(profile_Path);
	 System.out.println("Profile : "+profile.getQualifiedName()+" is Loaded Sucessfully");
	 applyProfile(pkg_, profile);
	 return profile;
    }
    public Profile loadApplyStandardProfile(String profile_Path,Package pkg_)
    {  ProfileTest PT=new ProfileTest();
	 Profile profile = PT.loadProfileURI(profile_Path);
	 System.out.println("Profile : "+profile.getQualifiedName()+" is Loaded Sucessfully");
	 applyProfile(pkg_, profile);
	 return profile;
    }
    public void saveModel(String save_Path,Package pkg_,String file_Name)
    {
		URI outputURI2 = URI.createFileURI(new File(save_Path).getAbsolutePath())
				.appendSegment(file_Name)
				.appendFileExtension(UMLResource.FILE_EXTENSION);
			System.out.printf("Saving the Model to %s", outputURI2.toFileString());
			System.out.println();
			ProfileTest PT=new ProfileTest();
			PT.save(pkg_, outputURI2);
    }
    
	public static void main(String args[]) throws Exception {
		
	test2();
	   	
	}
	public static void Test1(){
		ProfiledStateMachine SM=new ProfiledStateMachine();
		Model epo2Model = SM.createModel("epo2");

		Package fooPackage  = SM.createPackage(epo2Model,"foo");
	    SM.createPackage(fooPackage,"bar");

		//PrimitiveType stringPrimitiveType = SM.createPrimitiveType(epo2Model,"String");
		//PrimitiveType intPrimitiveType = SM.createPrimitiveType(epo2Model,"Integer");
		
		PrimitiveType stringPrimitiveType = SM.createPrimitiveType("String");
		PrimitiveType intPrimitiveType = SM.createPrimitiveType("Integer");

		Enumeration orderStatusEnumeration = SM.createEnumeration(epo2Model,"OrderStatus");
		SM.createEnumerationLiteral(orderStatusEnumeration, "Pending");
		Class supplierClass = SM.createClass(epo2Model,	"Supplier", false, true, VisibilityKind.PUBLIC_LITERAL, null);
		
		//EObject b=SM.applyStereotype(supplierClass, stereo);
		
		Class supplierAddressClass = SM.createClass(epo2Model, "SupplierAddress", false, true,VisibilityKind.PUBLIC_LITERAL, null);
		SM.createGeneralization(supplierClass, supplierAddressClass);

		SM.createAttribute(supplierClass, "name", stringPrimitiveType, 0, 1);
		SM.createAttribute(supplierClass, "Nummber", intPrimitiveType, 0, 1);
		SM.createAttribute(supplierAddressClass, "AddressName",	stringPrimitiveType, 0, 1);

		SM.createAssociation("Supplier-SupplierAddress",supplierClass, true,
				AggregationKind.COMPOSITE_LITERAL, "addr", 0,
				LiteralUnlimitedNatural.UNLIMITED, supplierAddressClass, false,
				AggregationKind.NONE_LITERAL, "", 1, 1);
		
		 
		StateMachine stmachine = SM.createStateMachine(supplierClass,
				"SupplierMachine");

		Region r = SM.createRegion(stmachine, stmachine.getName());
		State s1 = SM.createState("Run", null, null, null, null, null, null, null,null);
		State s2 = SM.createState("Stop", null, null, null, null, null, null,null, null);
		FinalState fs = SM.createFinalState("Dead", null, null, null, null, null,null, null);
		Pseudostate ps =SM.createPseudoState("Slow",PseudostateKind.JOIN_LITERAL, null, null);
		SM.designRegion(r, s1, null, null);
		SM.designRegion(r, s2, null, null);
		SM.designRegion(r, fs, null, null);
		SM.designRegion(r, ps, null, null);

		Constraint c = SM.createSpecificationConstraint("Const", "OCL",	"mybody.action");
		OpaqueExpression opExpress = SM.createOpaqueExpression("OPEXPR1", "OCL","body.action");
		OpaqueBehavior beha = SM.createOpaqueBehavior("OpaqBehavior1", "OCL","mybody.action");

		Event chEvent = SM.createChangeEvent("ChangeEvent", opExpress, epo2Model,VisibilityKind.PRIVATE_LITERAL);
		Event anyEvent = SM.createMessageEvent("AnyReceieveEvent", epo2Model,MessageEventkind.AnyReciveEvent, null, null);

		Signal signal_ = SM.createSignal("Signal1", epo2Model);
		Event signalEvent = SM.createMessageEvent("SignalEvent", epo2Model,MessageEventkind.SignalEvent, signal_, null);

		Operation operation = SM.createOperation(supplierClass, "Op1",VisibilityKind.PUBLIC_LITERAL);
		Event callEvent = SM.createMessageEvent("CallEvent", epo2Model,MessageEventkind.CallEvent, null, operation);

		ValueSpecification valueSpecification_ = opExpress;
		TimeExpression timeExpression_ = SM.createTimeExpression("TimeExpression",valueSpecification_);
		Event timEvent = SM.createTimeEvent("TimeEvent", epo2Model,timeExpression_, true);

		Trigger trig0 = SM.createTrigger("Mytrigger0", chEvent);
		Trigger trig1 = SM.createTrigger("Mytrigger1", anyEvent);
		Trigger trig2 = SM.createTrigger("Mytrigger2", signalEvent);
		Trigger trig3 = SM.createTrigger("Mytrigger3", callEvent);
		Trigger trig4 = SM.createTrigger("Mytrigger4", timEvent);

		Transition trans0 = SM.createTransition("trans0", s1, s1,TransitionKind.LOCAL_LITERAL, trig0, null, null);
		SM.designRegion(r, null, null, trans0);

		Transition trans1 = SM.createTransition("trans1", s1, s2,TransitionKind.LOCAL_LITERAL, trig1, beha, c);
		SM.designRegion(r, null, null, trans1);

		Transition trans2 = SM.createTransition("trans2", s2, ps,TransitionKind.LOCAL_LITERAL, trig2, beha, null);
		SM.designRegion(r, null, null, trans2);

		Transition trans3 = SM.createTransition("trans3", ps, fs,TransitionKind.LOCAL_LITERAL, trig3, beha, null);
		SM.designRegion(r, null, null, trans3);

		Transition trans4 = SM.createTransition("trans4", ps, s1,TransitionKind.LOCAL_LITERAL, trig4, beha, null);
		SM.designRegion(r, null, null, trans4);

		SM.createReception("Reception1", supplierClass, signal_);
	
		SM.importPrimitivePackage(epo2Model, UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI);

		 Profile profile=SM.loadApplyProfile("GenStateMachine/Profile.profile.uml",epo2Model);
	
		Stereotype stereo= SM.retrieveStereoType(profile, "System");
		Stereotype stereo1= SM.retrieveStereoType(profile, "Context");
		
		SM.applyStereotype(supplierClass, stereo);
		SM.applyStereotype(supplierAddressClass, stereo1);
		
		SM.saveModel(path, epo2Model, "StateMachine_Profile");

	}
	@SuppressWarnings("unused")
	public static void test2(){
		ProfiledStateMachine SM=new ProfiledStateMachine();
		Model epo2Model = SM.createModel("epo2");

		PrimitiveType stringPrimitiveType = SM.createPrimitiveType("String");
		PrimitiveType intPrimitiveType = SM.createPrimitiveType("Integer");

		Class supplierClass = SM.createClass(epo2Model,	"Supplier", false, true, VisibilityKind.PUBLIC_LITERAL, null);
		Class supplierAddressClass = SM.createClass(epo2Model, "SupplierAddress", false, false ,VisibilityKind.PUBLIC_LITERAL, null);

		Property Att1=SM.createAttribute(supplierClass, "name", stringPrimitiveType, 0, 1);
		SM.createAttribute(supplierClass, "Nummber", intPrimitiveType, 0, 1);
		SM.createAttribute(supplierAddressClass, "AddressName",	stringPrimitiveType, 0, 1);
		
		SM.createOperation(supplierClass, "add", VisibilityKind.PUBLIC_LITERAL);
		SM.createOperation(supplierAddressClass, "add", VisibilityKind.PUBLIC_LITERAL);

		SM.createAssociation("Supplier-SupplierAddress",supplierClass, true,
				AggregationKind.NONE_LITERAL, "addr", 0,
				LiteralUnlimitedNatural.UNLIMITED, supplierAddressClass, false,
				AggregationKind.NONE_LITERAL, "", 1, 1);
		
		 
		StateMachine stmachine = SM.createStateMachine(supplierClass,
				"SupplierMachine");

		Region r = SM.createRegion(stmachine, stmachine.getName());
		State s1 = SM.createState("Run", null, null, null, null, null, null, null,null);
		State s2 = SM.createState("Stop", null, null, null, null, null, null,null, null);
		FinalState fs = SM.createFinalState("Dead", null, null, null, null, null,null, null);
	//	Pseudostate ps =SM.createPseudoState("Slow",PseudostateKind.JOIN_LITERAL, null, null);
		SM.designRegion(r, s1, null, null);
		SM.designRegion(r, s2, null, null);
		SM.designRegion(r, fs, null, null);
	//	SM.designRegion(r, ps, null, null);

		Constraint c = SM.createSpecificationConstraint("Const", "OCL",	"mybody.action");
		OpaqueExpression opExpress = SM.createOpaqueExpression("OPEXPR1", "OCL","self.default");
		LiteralString litStr = SM.createLiteralString("Litst1", "after, 5s");
		OpaqueBehavior beha = SM.createOpaqueBehavior("OpaqBehavior1", "OCL","self.default");

		SM.createChangeEvent("ChangeEvent", opExpress, epo2Model,VisibilityKind.PRIVATE_LITERAL);
		Event anyEvent = SM.createMessageEvent("AnyReceieveEvent", epo2Model,MessageEventkind.AnyReciveEvent, null, null);

		Signal signal_ = SM.createSignal("Signal1", epo2Model);
		Event signalEvent = SM.createMessageEvent("SignalEvent", epo2Model,MessageEventkind.SignalEvent, signal_, null);

		Operation operation = SM.createOperation(supplierClass, "Op1",VisibilityKind.PUBLIC_LITERAL);
		Event callEvent = SM.createMessageEvent("CallEvent", epo2Model,MessageEventkind.CallEvent, null, operation);

		//ValueSpecification valueSpecification_ = opExpress;
		//TimeExpression timeExpression_ = SM.createTimeExpression("TimeExpression",valueSpecification_);
		//Event timEvent = SM.createTimeEvent("TimeEvent", epo2Model,timeExpression_, true);

		//Trigger trig0 = SM.createTrigger("Mytrigger0", chEvent);
		Trigger trig1 = SM.createTrigger("Mytrigger1", anyEvent);
		Trigger trig2 = SM.createTrigger("Mytrigger2", signalEvent);
		Trigger trig3 = SM.createTrigger("Mytrigger3", callEvent);
	//	Trigger trig4 = SM.createTrigger("Mytrigger4", timEvent);

//		Transition trans0 = SM.createTransition("trans0", s1, s1,TransitionKind.LOCAL_LITERAL, trig0, null, null);
//		SM.designRegion(r, null, null, trans0);

		Transition trans1 = SM.createTransition("trans1", s1, s2,TransitionKind.LOCAL_LITERAL, trig1, beha, c);
		SM.designRegion(r, null, null, trans1);

		Transition trans2 = SM.createTransition("trans2", s2, s1,TransitionKind.LOCAL_LITERAL, trig2, beha, null);
		SM.designRegion(r, null, null, trans2);

		Transition trans3 = SM.createTransition("trans3", s2, fs,TransitionKind.LOCAL_LITERAL, trig3, beha, null);
		SM.designRegion(r, null, null, trans3);

//		Transition trans4 = SM.createTransition("trans4", ps, s1,TransitionKind.LOCAL_LITERAL, trig4, beha, null);
//		SM.designRegion(r, null, null, trans4);

		SM.createReception("Reception1", supplierClass, signal_);
	
		SM.importPrimitivePackage(epo2Model, UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI);
		Profile profile1=SM.loadApplyStandardProfile(UMLResource.STANDARD_PROFILE_URI,epo2Model);
		Profile profile2=SM.loadApplyProfile("GenStateMachine/Default.profile.uml",epo2Model);
		//Profile profile1=SM.loadApplyProfile("GenStateMachine/ProfileBase.profile.uml",epo2Model);
		//Profile profile3=SM.loadApplyProfile("GenStateMachine/Deployment.profile.uml",epo2Model);
		Profile profile=SM.loadApplyProfile("GenStateMachine/Profile.profile.uml",epo2Model);
	
		Stereotype stereo= SM.retrieveStereoType(profile, "System");
		Stereotype stereo1= SM.retrieveStereoType(profile, "Context");
		Stereotype stereo11= SM.retrieveStereoType(profile, "NonDeterministic");
		Stereotype stereo12= SM.retrieveStereoType(profile, "TimeProbability");
		
		SM.applyStereotype(supplierClass, stereo1);
		SM.applyStereotype(supplierAddressClass, stereo);
		
		SM.applyStereotype(Att1, stereo11);
		SM.applyStereotype(trans1, stereo12);
		
		SM.saveModel(path, epo2Model, "StateMachine_ProfileNew");
		
	}


}
