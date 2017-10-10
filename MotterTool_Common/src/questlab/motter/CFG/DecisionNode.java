package questlab.motter.CFG;

public class DecisionNode extends Node {
	private Condition condi;
	private Condition falseCondition;
	public Condition getCondi() {
		return condi;
	}

	public void setCondi(Condition condi) {
		this.condi = condi;
	}

	public Condition getFalseCondition() {
		return falseCondition;
	}

	public void setFalseCondition(Condition falseCondition) {
		this.falseCondition = falseCondition;
	}

	private int parentBlock; //0= noOne 1= then 2 =else
	
	public DecisionNode(){
		condi=null;
		parentBlock=0;
	}
	
	public int getParentBlock() {
		return parentBlock;
	}

	public void setParentBlock(int parentBlock) {
		this.parentBlock = parentBlock;
	}

	public DecisionNode(String Id,String code){
		super(Id,code);
	}
	public void setcondition(Condition c){
		condi=c;
	}
	public Condition getcondition(){
		return condi;
	}
	
	private boolean isThenPart(String id){
		//String id = "38#R11:then:R8";
		String[] splits = id.split(":");
		if(splits.length == 3){
			if(splits[1].equals("then"))
				return true;
		}
		return false;
	}
	private boolean isElsePart(String id){
		//String id = "38#R11:then:R8";
		String[] splits = id.split(":");
		if(splits.length == 3){
			if(splits[1].equals("else"))
				return true;
		}
		return false;
	}
	
	public Node getThenNode() {
		for (Node n1 : this.getChildNodes()) {
			if (this.isThenPart(n1.getNodeId()))
				return n1;
		}
		return null;
	}
	
	public Node getElseNode() {
		for (Node n1 : this.getChildNodes()) {
			if (this.isElsePart(n1.getNodeId()))
				return n1;
		}
		return null;
	}
	
	public DecisionNode getNextDecisionNode(Node n){
		if(n instanceof DecisionNode)
			return (DecisionNode)n;
		else{
			for(Node n1: n.getChildNodes())
				return getNextDecisionNode(n1);
		}
		return null;
	}
}
