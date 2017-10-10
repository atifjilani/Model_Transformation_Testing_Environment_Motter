package org.questlab.motter.oclSolver.driver;

public class ALLBranchCoverageTest {
	
	public static String Condition1(){
		String query="" 
//				+ "Class.allInstances()->size()=2 "
			    + "StateMachine.allInstances()->size()=1 "
			    + "and Region.allInstances()->size()=1 "
			    + "and Transition.allInstances()->size()=2 "
//			    + "and Trigger.allInstances()->size()=2 "
				+ "and Class.allInstances()->select(c| c.ownedBehavior->forAll(o| o.oclIsTypeOf(StateMachine)) and " +
				   "c.stereotype5='Con' and c.isActive=true and c.ownedOperation->forAll(o|o.oclIsTypeOf(Operation)) and " +
				   "c.ownedAttribute->forAll(o|o.oclIsTypeOf(Property)))->size()=1 " 
				+ "and StateMachine.allInstances()->forAll(sm|sm.region->forAll(r| r.oclIsTypeOf(Region))) "  //and sm.class.oclIsTypeOf(Class)
				+ "and Region.allInstances()->forAll(r|r.transition->forAll(t| t.oclIsTypeOf(Transition))) "
				+ "and Transition.allInstances()->one(t|t.source.oclIsTypeOf(Pseudostate) and t.target.oclIsTypeOf(FinalState) and " +
				  "t.effect.oclIsTypeOf(OpaqueBehavior) and t.trigger.oclIsTypeOf(Trigger)) " 
				+ "and Trigger.allInstances()->forAll(t|t.event.oclIsTypeOf(TimeEvent)) "
				+ "and TimeEvent.allInstances()->exists(ca|ca.when.oclIsTypeOf(TimeExpression)) "
				+ "and TimeExpression.allInstances()->exists(ca|ca.expr.oclIsTypeOf(LiteralString))"
//			    + "and Pseudostate.allInstances()->size()=1 "
//			    + "and FinalState.allInstances()->size()=1 "
                +""
			    ;
		
		String query1="" 
				+ "Class.allInstances()->select(c| c.ownedBehavior->forAll(o| o.oclIsTypeOf(StateMachine)) and " +
				   "c.stereotype5='Con' and c.isActive=true and c.ownedOperation->forAll(o|o.oclIsTypeOf(Operation)))->size()=1 " 
				+ "and StateMachine.allInstances()->forAll(sm|sm.region->forAll(r| r.oclIsTypeOf(Region))) "  //and sm.class.oclIsTypeOf(Class)
				+ "and Region.allInstances()->forAll(r|r.transition->forAll(t| t.oclIsTypeOf(Transition))) "
				+ "and Transition.allInstances()->exists(t1,t2|t1.source.oclIsTypeOf(Pseudostate) and t1.target.oclIsTypeOf(State) and t2.source.oclIsTypeOf(State) and t2.target.oclIsTypeOf(FinalState) and t1<>t2) " 
			    + "and Region.allInstances()->size()=1 "

//			    + "and Transition.allInstances()->size()=2 "
//			    + "and StateMachine.allInstances()->size()=2 "
//			    + "and Pseudostate.allInstances()->size()=1 "
//			    + "and FinalState.allInstances()->size()=1 "
//				+ "and Class.allInstances()->size()=4 "
                +""
			    ;
		
		
		return query;
	}
	
	

}
