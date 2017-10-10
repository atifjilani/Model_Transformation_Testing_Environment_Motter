package questlab.motter.CFG;

public class conditionMap {
	private String conditionNodeNumber;
	private String conditionValue;
	private Boolean ConditionStatus=false;
	private String parentConditionNode;
	private int approachLevel=0;
	private String thenNodeNumber;
	private String elseNodeNumber;
	private Node NodeElse;
	private Node NodeThen;
	private Node DecNode;
	private Node ParentCondition;
	private CFG path;
	
	//private ObjectDiagram diagram;
	
	public void print(){
		if(ConditionStatus)
		{
		System.out.println("Node: "+conditionNodeNumber+"  Statement:"+conditionValue+"  Status: "+ConditionStatus
				+"  ThenNodeNo: "+thenNodeNumber+"  ParentCondNode:"+parentConditionNode);
		}
		else{
			System.out.println("Node: "+conditionNodeNumber+"  Statement:"+conditionValue+"  Status: "+ConditionStatus
					+"  ElseNodeNo: "+elseNodeNumber+"  ParentCondNode:"+parentConditionNode);
		}
			
	}
	public  void conditioMap(){
		
	}


	public String getConditionNodeNumber() {
		return conditionNodeNumber;
	}


	public void setConditionNodeNumber(String conditionNodeNumber) {
		this.conditionNodeNumber = conditionNodeNumber;
	}


	public String getConditionValue() {
		return conditionValue;
	}


	public void setConditionValue(String conditionValue) {
		this.conditionValue = conditionValue;
	}


	public Boolean getConditionStatus() {
		return ConditionStatus;
	}


	public void setConditionStatus(Boolean conditionStatus) {
		ConditionStatus = conditionStatus;
	}


	public String getParentConditionNode() {
		return parentConditionNode;
	}


	public void setParentConditionNode(String parentConditionNode) {
		this.parentConditionNode = parentConditionNode;
	}


	public int getApproachLevel() {
		return approachLevel;
	}


	public void setApproachLevel(int approachLevel) {
		this.approachLevel = approachLevel;
	}


	public String getThenNodeNumber() {
		return thenNodeNumber;
	}


	public void setThenNodeNumber(String thenNodeNumber) {
		this.thenNodeNumber = thenNodeNumber;
	}


	public String getElseNodeNumber() {
		return elseNodeNumber;
	}


	public void setElseNodeNumber(String elseNodeNumber) {
		this.elseNodeNumber = elseNodeNumber;
	}


	public Node getNodeElse() {
		return NodeElse;
	}


	public void setNodeElse(Node nodeElse) {
		NodeElse = nodeElse;
	}


	public Node getNodeThen() {
		return NodeThen;
	}


	public void setNodeThen(Node nodeThen) {
		NodeThen = nodeThen;
	}


	public Node getDecNode() {
		return DecNode;
	}


	public void setDecNode(Node decNode) {
		DecNode = decNode;
	}


	public CFG getPath() {
		return path;
	}


	public void setPath(CFG path) {
		this.path = path;
	}


	public Node getParentCondition() {
		return ParentCondition;
	}


	public void setParentCondition(Node parentCondition) {
		ParentCondition = parentCondition;
	}

}
