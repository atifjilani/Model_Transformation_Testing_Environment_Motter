package org.questlab.motter.oclSolver;

import java.util.ArrayList;
import java.util.List;

public class OCLSolverTestDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String umlFile = "OCLSolverInput/StateMachineMetaModel.uml";
		String objDiagramPath = "OCLSolverOutput";
		int noOfIterations = 1000;
		int noOfRuns = 1;
		MOFExperiment.setUpExperiment(noOfIterations, noOfRuns,1, umlFile, objDiagramPath);

	}
	
	@SuppressWarnings("unused")
	private static ArrayList<List<Object>> SetUpDiagram(MOFExperiment mofe1){
		ArrayList<List<Object>> result = null;
		String query1 = "" 
				+ "Class.allInstances()->size()=2" 
				+ " and Property.allInstances()->size()>0" 
				+ " and Association.allInstances()->size()>0" 
			//	+ " and Operation.allInstances()->size()>2" 
				+ " and StateMachine.allInstances()->size()=1"
			//	+ " and Region.allInstances()->size()=1" 
				+ " and State.allInstances()->size()>0"
				+ " and Transition.allInstances()->size()>0" 
				+ " and Trigger.allInstances()->size()>0" 
				+ " and CallEvent.allInstances()->size()=1"
				+ " and ChangeEvent.allInstances()->size()=1"
				+ " and SignalEvent.allInstances()->size()=1" 
				+ " and TimeEvent.allInstances()->size()=1"
				+ " and TimeExpression.allInstances()->size()=1" 
				+ " and Signal.allInstances()->size()=1"
				+ " and Reception.allInstances()->size()=1"
				+ " and Package.allInstances()->size()=1"
				+ " and OpaqueExpression.allInstances()->size()>0"
				+ " and CallEvent.allInstances()->exists(ca|ca.operation.oclIsKindOf(Operation))" 
				+ " and SignalEvent.allInstances()->exists(ca|ca.signal.oclIsKindOf(Signal))" 
				+ " and TimeEvent.allInstances()->exists(ca|ca.when.oclIsKindOf(TimeExpression))"
				+ " and ChangeEvent.allInstances()->exists(ca|ca.changeExpression.oclIsKindOf(OpaqueExpression))"
				+ " and TimeExpression.allInstances()->exists(ca|ca.expr.oclIsTypeOf(OpaqueExpression))"
				;
	              
	//	result = mofe1.SolveQuery(query1);	
		String query2 = "" 
				+ "Class.allInstances()->size() >0" 
				+ " and Property.allInstances()->size()>0" 
				+ " and Association.allInstances()->size()>0" 
				+ " and Operation.allInstances()->size()>2" 
				+ " and StateMachine.allInstances()->size()=1"
			//	+ " and Region.allInstances()->size()=1" 
				+ " and State.allInstances()->size()>0"
				+ " and Transition.allInstances()->size()>0" 
				+ " and Trigger.allInstances()->size()>0" 
				+ " and CallEvent.allInstances()->size()=1"
				+ " and ChangeEvent.allInstances()->size()=1"
				+ " and SignalEvent.allInstances()->size()=1" 
				+ " and TimeEvent.allInstances()->size()=1"
				+ " and TimeExpression.allInstances()->size()=1" 
				+ " and Signal.allInstances()->size()=1"
				+ " and Reception.allInstances()->size()=1"
				+ " and Package.allInstances()->size()=1"
			//	+ " and OpaqueExpression.allInstances()->size()=2"
				+ " and CallEvent.allInstances()->exists(ca|ca.operation.oclIsTypeOf(Operation))" 
				+ " and SignalEvent.allInstances()->exists(ca|ca.signal.oclIsTypeOf(Signal))" 
				+ " and TimeEvent.allInstances()->exists(ca|ca.when.oclIsTypeOf(TimeExpression))"
				+ " and ChangeEvent.allInstances()->exists(ca|ca.changeExpression.oclIsTypeOf(OpaqueExpression))"
				+ " and TimeExpression.allInstances()->exists(ca|ca.expr.oclIsTypeOf(OpaqueExpression))"
				+ " and Operation.allInstances()->forAll(s|s.class.oclIsTypeOf(Class))" 
				+ " and Class.allInstances()->select(c|c.isActive=true and c.ownedOperation->forAll(o|o.oclIsKindOf(Operation))"
				+ " and c.ownedAttribute->exists(a|a.oclIsKindOf(Property)) and c.ownedBehavior->exists(ob| ob.oclIsKindOf(StateMachine))"
				+ ")->size()=1"
				+ " and StateMachine.allInstances()->exists(s |s.region->exists(r|r.name='r1'))" 
                ;
		
		
	//	result=mofe1.SolveQueryWithClassifierTupple(query2, result);
		String query3 = "" 
				+ "Class.allInstances()->size()>0" 
				+ " and Property.allInstances()->size()>0" 
				+ " and Association.allInstances()->size()>0" 
				+ " and Operation.allInstances()->size()>0" 
				+ " and StateMachine.allInstances()->size()=1"
		//		+ " and Region.allInstances()->size()>0" 
				+ " and State.allInstances()->size()>0"
				+ " and Transition.allInstances()->size()>0" 
				+ " and Trigger.allInstances()->size()>0" 
				+ " and CallEvent.allInstances()->size()>=1"
				+ " and ChangeEvent.allInstances()->size()>=1"
				+ " and SignalEvent.allInstances()->size()>=1" 
				+ " and TimeEvent.allInstances()->size()>=1"
				+ " and TimeExpression.allInstances()->size()>0" 
				+ " and Signal.allInstances()->size()>0"
				+ " and Reception.allInstances()->size()>0"
				+ " and Package.allInstances()->size()=1"
				+ " and OpaqueBehavior.allInstances()->size()>0"
				+ " and CallEvent.allInstances()->exists(ca|ca.operation.oclIsTypeOf(Operation))" 
				+ " and SignalEvent.allInstances()->exists(ca|ca.signal.oclIsTypeOf(Signal))" 
				+ " and TimeEvent.allInstances()->exists(ca|ca.when.oclIsTypeOf(TimeExpression))"
				+ " and ChangeEvent.allInstances()->exists(ca|ca.changeExpression.oclIsTypeOf(OpaqueExpression))"
				+ " and TimeExpression.allInstances()->exists(ca|ca.expr.oclIsTypeOf(OpaqueExpression))"
				+ " and Reception.allInstances()->exists(ca|ca.signal.oclIsTypeOf(Signal))"
				+ " and Operation.allInstances()->forAll(s|s.class.oclIsTypeOf(Class))"
				+ " and Class.allInstances()->select(c|c.isActive=false)->size()=1" 
				+ " and Class.allInstances()->select(c|c.isActive=true and c.ownedOperation->exists(o|o.oclIsTypeOf(Operation))"
				+ " and c.ownedAttribute->exists(a|a.oclIsTypeOf(Property)) and c.ownedBehavior->exists(ob| ob.oclIsTypeOf(StateMachine)) and c.ownedReception->exists(ob| ob.oclIsTypeOf(Reception))"
				+ ")->size()=1"
				+ " and StateMachine.allInstances()->exists(s |s.region->exists(r|r.name='r1'))" 
				+ " and Region.allInstances()->select(m|m.name='r1')->exists(r|r.transition->exists(t|t.source.oclIsTypeOf(State) and t.target.oclIsTypeOf(State)))"
		//		+ " and Region.allInstances()->select(m|m.name='r1')->exists(r|r.transition->exists(t|t.source.oclIsTypeOf(State) and t.target.oclIsTypeOf(FinalState)))"
		//		+ " and Region.allInstances()->select(m|m.name='r1')->select(r|r.transition->select(t|t.source.oclIsTypeOf(Vertex) and t.target.oclIsTypeOf(Vertex))->size()=5)->size()=5"
				+ " and Transition.allInstances()->forAll(t|t.effect.oclIsTypeOf(OpaqueBehavior) and t.trigger.oclIsTypeOf(Trigger))"
				+ " and Trigger.allInstances()->forAll(t|t.event.oclIsKindOf(Event))"
				//+ " and Region.allInstances()->forAll(r|r.subvertex->forAll(v|v.oclIsKindOf(Vertex)))"
	             ;
		//result=mofe1.SolveQueryWithClassifierTupple(query3, result);
		MOFExperiment.SolveQuery(query3);
		
		return result;
	
	}

}
