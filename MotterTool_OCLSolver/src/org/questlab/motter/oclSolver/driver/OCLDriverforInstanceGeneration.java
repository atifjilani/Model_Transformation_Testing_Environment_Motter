package org.questlab.motter.oclSolver.driver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.questlab.motter.oclSolver.MOFExperiment;


 public class OCLDriverforInstanceGeneration {

//	private ArrayList<String> logs = new ArrayList<String>();

	public static void main(String[] args) {
		try {
			String umlFile = null;
			String objDiagramPath=null;
            if(args.length ==0){
            	umlFile = "OCLSolverInput/StateMachineMetaModel.uml";
    			objDiagramPath = "OCLSolverOutput";	
            }
            else{
                umlFile = args[0];
    			objDiagramPath = args[1];
            }

			int noOfIterations = 1000;
			int noOfRuns = 1;
		//	MOFExperiment mofe1 = new MOFExperiment();
	    // random = 0 and AVM =1
			MOFExperiment.setUpExperiment(noOfIterations, noOfRuns,
					1, umlFile, objDiagramPath);
			SetUpDiagram(objDiagramPath);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private static ArrayList<List<Object>> SetUpDiagram(String filepath) throws Exception{
		ArrayList<List<Object>> result = new ArrayList<List<Object>>();
				
		//ConnectionPointReference
		String query1 = "" 
				+"ConnectionPointReference.allInstances()->forAll(fs| (fs.entry->notEmpty() implies fs.entry->forAll(e | e.kind = PseudostateKind::entryPoint)) " 
				+"and (fs.exit->notEmpty() implies fs.exit->forAll(e | e.kind = PseudostateKind::exitPoint)))" 	
				;
		//Final State
		String query2 = "" 
				+"FinalState.allInstances()->forAll(fs|(fs.outgoing->size()=0) and (fs.incoming->size()>1)" 
				+" and (fs.region->size()=0) and (fs.entry->size()=0) and (fs.exit->size()=0) and (fs.submachine->size()=0))"
				;
		//Pseudostate
		String query3 = "" 
				+"Pseudostate.allInstances()->forAll(f|((f.kind = PseudostateKind::initial) implies ((f.outgoing->size() <= 1) and (f.outgoing.guard->isEmpty()) and (f.outgoing.trigger->isEmpty()))) " +
				" and (((f.kind = PseudostateKind::deepHistory) or (f.kind = PseudostateKind::shallowHistory)) implies (f.outgoing->size() <= 1)) " +
				" and ((f.kind = PseudostateKind::join) implies ((f.outgoing->size()= 1) and (f.incoming->size() >= 2)))" +
				" and ((f.kind = PseudostateKind::fork) implies ((f.incoming->size() = 1) and (f.outgoing->size() >= 2)))" +
				" and ((f.kind = PseudostateKind::junction) implies ((f.incoming->size()>= 1) and (f.outgoing->size()>= 1)))" +
				" and ((f.kind = PseudostateKind::choice) implies ((f.incoming->size() >= 1) and (f.outgoing->size()>= 1)))" +
                ")"
				;
		//Region
				String query4 = "" 
						+"Region.allInstances()->forAll(r|(r.subvertex->select(oclIsTypeOf(Pseudostate))->collect(oclAsType(Pseudostate))->select(kind = PseudostateKind::deepHistory)->size() = 1)" +
	              //       "and (r.subvertex->select(oclIsKindOf(Pseudostate))->collect(oclAsType(Pseudostate))->select(kind = PseudostateKind::deepHistory)->size() <= 1)" +
	              //       "and (r.subvertex->select(oclIsKindOf(Pseudostate))->collect(oclAsType(Pseudostate))->select(kind = PseudostateKind::shallowHistory)->size() <= 1))"
						" and ((r.stateMachine.oclIsUndefined() implies r.state.oclIsUndefined()) and (r.state.oclIsUndefined() implies r.stateMachine.oclIsUndefined())))"
	                    ;
		//State
				String query5 = "" 
						+"State.allInstances()->forAll(s| (s.isSubmachineState implies s.connection->notEmpty() )" +
//						"and (s.connectionPoint->forAll(p|(p.kind = PseudostateKind::entryPoint) or (p.kind = PseudostateKind::exitPoint)))" +
//						"and (s.connectionPoint->notEmpty() implies s.isComposite)" +
//						"and (s.isSubmachineState implies (s.connection->forAll (cp | (cp.entry->forAll (ps | ps.stateMachine = s.submachine))and (cp.exit->forAll (ps | ps.stateMachine = s.submachine)) )))" +
						"and (s.isComposite implies (not s.isSubmachineState))" +
						"and (s.isSimple implies (s.region->isEmpty())) " +
						"and (s.isComposite implies (s.region->notEmpty())) " +
//						"and (s.isOrthogonal = (s.region->size() > 1) )" +
//						"and (s.isSubmachineState = s.submachine->notEmpty() )" +
						")"
	                    ;
	  //StateMachine
				String query6 = "" 
						+"StateMachine.allInstances()->forAll(s| s.connectionPoint->forAll(c|c.kind = PseudostateKind::entryPoint or c.kind = PseudostateKind::exitPoint)" +
						")"
	                    ;
	  //Transition
				String query7 = "" 
						+"Transition.allInstances()->forAll(t| ((t.source.oclIsKindOf(Pseudostate) and (t.source.oclAsType(Pseudostate).kind = PseudostateKind::fork)) implies (t.target.oclIsKindOf(State)))" +
						"and ((t.source.oclIsKindOf(Pseudostate) and (t.source.oclAsType(Pseudostate).kind = PseudostateKind::fork)) implies (t.guard= null and t.trigger->isEmpty()))" +
						"and ((t.target.oclIsKindOf(Pseudostate) and (t.target.oclAsType(Pseudostate).kind = PseudostateKind::join)) implies (t.guard= null and t.trigger->isEmpty()))" +
						"and ((t.target.oclIsKindOf(Pseudostate) and (t.target.oclAsType(Pseudostate).kind = PseudostateKind::join)) implies (t.source.oclIsKindOf(State))) " +
						"and ((t.source.oclIsKindOf(Pseudostate) and (t.source.oclAsType(Pseudostate).kind = PseudostateKind::initial)) implies (t.trigger->isEmpty()))" +
						"and ((t.source.oclIsKindOf(Pseudostate) and (t.container.oclAsType(Region).stateMachine<> null)) implies (t.trigger->isEmpty()))" +
						")"
	                    ;
                
	  //TransitionKind
				String query8 = "" 
						+"Transition.allInstances()->forAll(t| ((t.kind = TransitionKind::local) implies((t.source.oclIsKindOf (State) and t.source.oclAsType(State).isComposite) " +
						"or (t.source.oclIsKindOf (Pseudostate) and t.source.oclAsType(Pseudostate).kind = PseudostateKind::entryPoint)))" +
			        	"and ((t.kind = TransitionKind::external) implies not((t.source.oclIsKindOf (Pseudostate) and t.source.oclAsType(Pseudostate).kind = PseudostateKind::entryPoint)))" +
			        	"and ((t.kind = TransitionKind::internal) implies (t.source.oclIsKindOf (State) and t.source=t.target))" +
			        	")"
	                    ;	
                
	  //Association
				String query9 = "" 
						+"Association.allInstances()->forAll(t| t.memberEnd->exists(aggregation <> AggregationKind::none) implies t.memberEnd->size() = 2 "+
			        	"and ( t.memberEnd->size() > 2 implies (t.ownedEnd->includesAll(t.memberEnd)) )" +
			        	")"
	                    ;	
				String query10 = "" 
								+ "Class.allInstances()->size()>0" 
							//	+ " and Property.allInstances()->size()>0" 
							//	+ " and Association.allInstances()->size()>0" 
							//	+ " and Operation.allInstances()->size()>0" 
								+ " and StateMachine.allInstances()->size()=1"
								+ " and Region.allInstances()->size()=10" 
								+ " and State.allInstances()->size()>0"
								+ " and Transition.allInstances()->size()>0" 
						//		+ " and Trigger.allInstances()->size()>0" 
							//	+ " and CallEvent.allInstances()->size()>=1"
							//	+ " and ChangeEvent.allInstances()->size()>=1"
							//	+ " and SignalEvent.allInstances()->size()>=1" 
							//	+ " and TimeEvent.allInstances()->size()>=1"
							//	+ " and TimeExpression.allInstances()->size()>0" 
							//	+ " and Signal.allInstances()->size()>0"
							//	+ " and Reception.allInstances()->size()>0"
								+ " and Package.allInstances()->size()>0"///1
							//	+ " and OpaqueBehavior.allInstances()->size()>0"
							//	+ " and CallEvent.allInstances()->exists(ca|ca.operation.oclIsTypeOf(Operation))" 
							//	+ " and SignalEvent.allInstances()->exists(ca|ca.signal.oclIsTypeOf(Signal))" 
							//	+ " and TimeEvent.allInstances()->exists(ca|ca.when.oclIsTypeOf(TimeExpression))"
							//	+ " and ChangeEvent.allInstances()->exists(ca|ca.changeExpression.oclIsTypeOf(OpaqueExpression))"
							//	+ " and TimeExpression.allInstances()->exists(ca|ca.expr.oclIsTypeOf(LiteralString))"
							//	+ " and Reception.allInstances()->exists(ca|ca.signal.oclIsTypeOf(Signal))"
							//	+ " and Operation.allInstances()->forAll(s|s.class.oclIsTypeOf(Class))"
							//	+ " and Property.allInstances()->forAll(s|s.class.oclIsTypeOf(Class))"
						//		+ " and State.allInstances()->forAll(s| s.stateInvariant.oclIsTypeOf(Constraint))"
						//		+ " and Constraint.allInstances()->forAll(ss|ss.specification.oclIsTypeOf(OpaqueExpression) )"
							//	+ " and Property.allInstances()->forAll(as|as.association.oclIsTypeOf(Association))"
							//	+ " and Association.allInstances()->forAll(as|as.ownedEnd->forAll(o|o.oclIsTypeOf(Property)) and as.memberEnd->forAll(o|o.oclIsTypeOf(Property)))" 
							  + " and Class.allInstances()->forAll(c|c.ownedBehavior->forAll(o|o.oclIsTypeOf(StateMachine)))" 
								+ " and Class.allInstances()->select(c|c.stereotype5='Con' and c.isActive=true and c.ownedOperation->exists(o|o.oclIsTypeOf(Operation))"
								+ " and c.ownedAttribute->exists(a|a.oclIsTypeOf(Property)) and c.ownedReception->exists(ob| ob.oclIsTypeOf(Reception)) and c.ownedBehavior->forAll(o|o.oclIsTypeOf(StateMachine))"
								+ ")->size()=1"
								+ " and StateMachine.allInstances()->select(s |s.region->exists(r|r.name='r1'))->size()=1"
								+ " and Region.allInstances()->select(m|m.name='r1')->exists(r|r.transition->exists(t|t.source.oclIsTypeOf(Pseudostate) and t.target.oclIsTypeOf(FinalState)))"
							//	+ " and Region.allInstances()->select(m|m.name='r1')->exists(r|r.transition->exists(t|t.source.oclIsTypeOf(State) and t.target.oclIsTypeOf(FinalState)))"
								+ " and Transition.allInstances()->forAll(t|t.effect.oclIsTypeOf(OpaqueBehavior) and t.trigger.oclIsTypeOf(Trigger))"
								+ " and Trigger.allInstances()->forAll(t|t.event.oclIsKindOf(Event))"
					             ;
	//	MOFExperiment.SolveQuery(query3);
		//result=OCLTuppleFileReader(filepath);
		MOFExperiment.generateInstance(query7);// Condition 1 coverage
		return result;
	
	}
	
	@SuppressWarnings("unused")
	private static ArrayList<List<Object>> OCLTuppleFileReader(String filepath){
		ArrayList<List<Object>> result = new ArrayList<List<Object>>();		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filepath+"/OCLSolverResult.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    try {
		        StringBuilder sb = new StringBuilder();
		        String line = br.readLine();
		       // System.out.println(line);
		        while (line != null) {
		            sb.append(line);  
		            List<Object> myList=new ArrayList<Object>() ;
		           String array[]= line.split(",");
		          for(String s:array){
		        	  myList.add((Object)s);
		          }
		          if(myList.size()<5)
		        	 myList.add("");
		          result.add(myList);
		            line = br.readLine();
		        }
		     //   String everything = sb.toString();
		    } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    finally {
		        try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		    return result;
	}
}

