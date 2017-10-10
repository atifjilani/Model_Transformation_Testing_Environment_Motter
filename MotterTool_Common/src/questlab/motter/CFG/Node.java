package questlab.motter.CFG;
import java.util.ArrayList; 

public class Node {
	private ArrayList<Node> childNodes;
	private Node parentNode;
	private boolean isTarget;
	private boolean isConnected;
	private String NodeId;
	private int ApproachLevel;
	private boolean visited;
	private String AtlCode;
	
	public Node(){
		childNodes=new ArrayList<Node>();
		isTarget=false;
		isConnected=false;
		NodeId=null;
		ApproachLevel=0;	
		visited=false;
		AtlCode="";
		parentNode=null;
	}
	
	public Node(String Id,String code){
		childNodes=new ArrayList<Node>();
		isTarget=false;
		isConnected=false;
		NodeId=Id;
		ApproachLevel=0;	
		visited=false;
		AtlCode=code;
		parentNode=null;
		
	}
	public void addChildNode(Node e)
	{
		childNodes.add(e);
	}
	public ArrayList<Node> getChildNodes() {
		return childNodes;
	}
	public void setChildNodes(ArrayList<Node> childNodes) {
		this.childNodes = childNodes;
	}
	public Node getParentNode() {
		return parentNode;
	}
	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}
	public boolean isTarget() {
		return isTarget;
	}
	public void setTarget(boolean isTarget) {
		this.isTarget = isTarget;
	}
	public boolean isConnected() {
		return isConnected;
	}
	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}
	public String getAtlCode() {
		return AtlCode;
	}
	public String getNodeId() {
		return NodeId;
	}
	public void setAtlCode(String ATL) {
		 AtlCode=ATL;
	}
	public void setNodeId(String nodeId) {
		NodeId = nodeId;
	}
	public int getApproachLevel() {
		return ApproachLevel;
	}
	public void setApproachLevel(int approachLevel) {
		ApproachLevel = approachLevel;
	}
	public boolean isVisited() {
		return visited;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	public Node(String Id, int Level,boolean isTarg,boolean isConn,String code){
		childNodes=new ArrayList<Node>();
		isTarget=isTarg;
		isConnected=isConn;
		NodeId=Id;
		ApproachLevel=Level;
		AtlCode=code;
	}
}
