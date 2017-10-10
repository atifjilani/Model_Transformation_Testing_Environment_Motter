/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package motter.driver.experiment;

import java.util.ArrayList;
import snt.oclsolver.tuples.ClassifierTuple;

/**
 * 
 * @author Atif Jilani
 */
public class ConditionTracker {
	String condition;
	String conditionId;

	String falseCondition;
	boolean executedSolutionNotFoundTruePath=false;
	boolean executedSolutionNotFoundFalsePath=false;

	boolean currentStatus; // isTrue or isFalse
	boolean currentExecution; //to define the current execution

	boolean truePathExecuted = false;
	boolean falsePathExecuted = false;

	ConditionTracker truePathCondition = null;
	ConditionTracker falsePathCondition = null;
	ConditionTracker parent = null;

	long truePathevaluationTime;
	long falsePathevaluationTime;

	int truePathnoOfIterations;
	int falsePathnoOfIterations;

	ArrayList<ClassifierTuple> truePathvalues = null;
	ArrayList<ClassifierTuple> falsePathvalues = null;
	
	public boolean isExecutedSolutionNotFoundTruePath() {
		return executedSolutionNotFoundTruePath;
	}

	public void setExecutedSolutionNotFoundTruePath(
			boolean executedSolutionNotFoundTruePath) {
		this.executedSolutionNotFoundTruePath = executedSolutionNotFoundTruePath;
	}

	public boolean isExecutedSolutionNotFoundFalsePath() {
		return executedSolutionNotFoundFalsePath;
	}

	public void setExecutedSolutionNotFoundFalsePath(
			boolean executedSolutionNotFoundFalsePath) {
		this.executedSolutionNotFoundFalsePath = executedSolutionNotFoundFalsePath;
	}


	public String getFalseCondition() {
		return falseCondition;
	}

	public void setFalseCondition(String falseCondition) {
		this.falseCondition = falseCondition;
	}

	public boolean isCurrentExecution() {
		return currentExecution;
	}

	public void setCurrentExecution(boolean currentExecution) {
		this.currentExecution = currentExecution;
	}



	public ConditionTracker getParent() {
		return parent;
	}

	public void setParent(ConditionTracker parent) {
		this.parent = parent;
	}

	public boolean isCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(boolean currentStatus) {
		this.currentStatus = currentStatus;
	}

	public boolean isTruePathExecuted() {
		return truePathExecuted;
	}

	public void setTruePathExecuted(boolean truePathExecuted) {
		this.truePathExecuted = truePathExecuted;
	}

	public boolean isFalsePathExecuted() {
		return falsePathExecuted;
	}

	public void setFalsePathExecuted(boolean falsePathExecuted) {
		this.falsePathExecuted = falsePathExecuted;
	}

	public ConditionTracker getTruePathCondition() {
		return truePathCondition;
	}

	public void setTruePathCondition(ConditionTracker truePathCondition) {
		this.truePathCondition = truePathCondition;
	}

	public ConditionTracker getFalsePathCondition() {
		return falsePathCondition;
	}

	public void setFalsePathCondition(ConditionTracker falsePathCondition) {
		this.falsePathCondition = falsePathCondition;
	}

	public long getTruePathevaluationTime() {
		return truePathevaluationTime;
	}

	public void setTruePathevaluationTime(long truePathevaluationTime) {
		this.truePathevaluationTime = truePathevaluationTime;
	}

	public long getFalsePathevaluationTime() {
		return falsePathevaluationTime;
	}

	public void setFalsePathevaluationTime(long falsePathevaluationTime) {
		this.falsePathevaluationTime = falsePathevaluationTime;
	}

	public int getTruePathnoOfIterations() {
		return truePathnoOfIterations;
	}

	public void setTruePathnoOfIterations(int truePathnoOfIterations) {
		this.truePathnoOfIterations = truePathnoOfIterations;
	}

	public long getFalsePathnoOfIterations() {
		return falsePathnoOfIterations;
	}

	public void setFalsePathnoOfIterations(int falsePathnoOfIterations) {
		this.falsePathnoOfIterations = falsePathnoOfIterations;
	}

	public ArrayList<ClassifierTuple> getTruePathvalues() {
		return truePathvalues;
	}

	public void setTruePathvalues(ArrayList<ClassifierTuple> truePathvalues) {
		this.truePathvalues = truePathvalues;
	}

	public ArrayList<ClassifierTuple> getFalsePathvalues() {
		return falsePathvalues;
	}

	public void setFalsePathvalues(ArrayList<ClassifierTuple> falsePathvalues) {
		this.falsePathvalues = falsePathvalues;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public ConditionTracker(String condition) {
		this.condition = condition;
	}

	public void print() {
		System.out.println("Condition: " + getCondition());
		if (getTruePathCondition() != null)
			System.out.println("True Condition: "
					+ getTruePathCondition().getCondition());
		if (getFalsePathCondition() != null)
			System.out.println("Fale Condition: "
					+ getFalsePathCondition().getCondition());
		if (getParent() != null)
			System.out.println("Parent Condition: "
					+ getParent().getCondition());
		System.out.println();
	}
	
	public void createParentChildConnection(ConditionTracker[] cts){
		for(int i=0; i<cts.length; i++){
			for(int j=0; j<cts.length; j++){
				if(i != j){
					boolean parentMatched = false;
					if(cts[j].parent != null){
						if(cts[j].parent.getCondition().equals(cts[i].getCondition()))
							parentMatched = true;
					}
					if(parentMatched){
						if(cts[i].getTruePathCondition() != null){
							if(cts[i].getTruePathCondition().getCondition().equals(cts[j].getCondition())){
								cts[i].setTruePathCondition(cts[j]);
								cts[j].setParent(cts[i]);
//								System.out.println("Successful Match (True): "+cts[j].getCondition());
							}
						}
						if(cts[i].getFalsePathCondition() != null){
							if(cts[i].getFalsePathCondition().getCondition().equals(cts[j].getCondition())){
								cts[i].setFalsePathCondition(cts[j]);
								cts[j].setParent(cts[i]);
//								System.out.println("Successful Match (False): "+cts[j].getCondition());
							}
						}
					}
				}
			}
		}
	}
	
	public ArrayList<ClassifierTuple> getValues() {
		if (this.parent != null) {
			if(this.parent.getTruePathCondition()!=null)
			if (this.parent.getTruePathCondition().getCondition().equals(this.getCondition()))
				return this.parent.getTruePathvalues();
			else if (this.parent.getFalsePathCondition().getCondition().equals(this.getCondition()))
				return this.parent.getFalsePathvalues();
		}
		return null;
	}
	
	public String getParentCondition() {
		String condition = null;
		if (this.parent != null) {
			if(this.parent.getTruePathCondition()!=null)
			if (this.parent.getTruePathCondition().getCondition()
					.equals(this.getCondition())) {
				condition = getParentConditionRecursion(this.parent);
				if (!condition.equals("")) {
					return condition + " and " + this.parent.getCondition();
				} else
					return this.parent.getCondition();
			} else if (this.parent.getFalsePathCondition().getCondition()
					.equals(this.getCondition())) {
				condition = getParentConditionRecursion(this.parent);
				if (!condition.equals("")) {
					return condition + " and " + this.parent.getFalseCondition();
				} else
					return this.parent.getFalseCondition();
			}
		}
		return condition;
	}

	private String getParentConditionRecursion(ConditionTracker con) {
		String condition = "";
		if (con.parent != null) {
			if(con.parent.getTruePathCondition()!=null)
			if (con.parent.getTruePathCondition().getCondition()
					.equals(con.getCondition())) {
				condition = getParentConditionRecursion(con.parent);
				if (!condition.equals("")) {
					return condition + " and " + con.parent.getCondition();
				} else
					return con.parent.getCondition();
			} else if (con.parent.getFalsePathCondition().getCondition()
					.equals(con.getCondition())) {
				condition = getParentConditionRecursion(con.parent);
				if (!condition.equals("")) {
					return condition + " and " + con.parent.getFalseCondition();
				} else
					return con.parent.getFalseCondition();
			}
		}
		return condition;
	}
	public String getConditionId() {
		return conditionId;
	}

	public void setConditionId(String conditionId) {
		this.conditionId = conditionId;
	}

}
