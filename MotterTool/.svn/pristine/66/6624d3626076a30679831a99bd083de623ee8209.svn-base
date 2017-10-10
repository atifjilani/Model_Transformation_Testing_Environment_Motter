package questlab.motter.CFG;

import java.util.LinkedList;
import java.util.Queue;

public class CFG {

	private Node rootNode;

	public Node getRootNode() {
		return rootNode;
	}

	public void setRootNode(Node rootNode) {
		this.rootNode = rootNode;
	}

	public CFG() {
		rootNode = null;
	}

	public void insert(Node newNode, Node parentNode) {

		if (rootNode == null && parentNode == null) {
			rootNode = newNode;
			rootNode.setParentNode(null);
		} else {
			
			newNode.setParentNode(parentNode);
			parentNode.addChildNode(newNode);
			
				
		}
	}

	public void InOrder() {
		inOrder(this.rootNode);
	}

	public void PreOrder() {
		preOrder(this.rootNode);
	}

	public void PostOrder() {
		postOrder(this.rootNode);
	}
	
	public Node BFS(String NodeId, boolean flag) {
		Queue<Node> q = new LinkedList<Node>();
		Node printNode;
		if (rootNode != null) {
			q.add(rootNode);
			while (!q.isEmpty()) {
				printNode = (Node) q.remove();
				if(printNode.getNodeId().equalsIgnoreCase(NodeId))
					return printNode;
				printNode.setVisited(true);
				if (printNode != null) {
					if (printNode.getChildNodes() != null) {
						for (Node p : printNode.getChildNodes()) {
							if (!p.isVisited())
								q.add(p);
						}
					}

				}
			}
		}
		return null;
	}
	
	public Node BFS(String Code) {
		Queue<Node> q = new LinkedList<Node>();
		Node printNode;
		if (rootNode != null) {
			q.add(rootNode);
			while (!q.isEmpty()) {
				printNode = (Node) q.remove();
				//if(printNode.getAtlCode().equalsIgnoreCase(Code))
				//if(printNode.getAtlCode().toLowerCase().trim().contains(Code.toLowerCase().trim()))
				//if(printNode.getAtlCode().toLowerCase().trim().equals(Code.toLowerCase().trim()))
				String PNIdN = (printNode.getNodeId().split("#"))[0];
				String CN = (Code.split("#"))[0];
				//System.out.println("=="+printNode.getNodeId()+"-------"+CN);
				//if((PNIdN.trim().equals(CN)) || (printNode.getAtlCode().toLowerCase().trim().contains((Code.split("#"))[1].trim())))
			if(printNode.getNodeId().contains("#")){
				if(Integer.parseInt(PNIdN) == Integer.parseInt(CN))
					return printNode;
				printNode.setVisited(true);
				if (printNode != null) {
					if (printNode.getChildNodes() != null) {
						for (Node p : printNode.getChildNodes()) {
							if (!p.isVisited())
								q.add(p);
						}
					}

				}
			}
			}
		}
		return null;
	}
    
	public Node BFS(String id, int i) {
		Queue<Node> q = new LinkedList<Node>();
		Node printNode;
		if (rootNode != null) {
			q.add(rootNode);
			while (!q.isEmpty()) {
				printNode = (Node) q.remove();
				//if(printNode.getAtlCode().equalsIgnoreCase(Code))
				//if(printNode.getAtlCode().toLowerCase().trim().contains(Code.toLowerCase().trim()))
				//if(printNode.getAtlCode().toLowerCase().trim().equals(Code.toLowerCase().trim()))
				String PNId = (printNode.getNodeId().split("#"))[1];
				String PNIdN = (PNId.split(":"))[0];
				//String CN = (Code.split("#"))[0];
				//System.out.println("=="+printNode.getNodeId()+"-------"+CN);
				//if((PNIdN.trim().equals(CN)) || (printNode.getAtlCode().toLowerCase().trim().contains((Code.split("#"))[1].trim())))
			if(printNode.getNodeId().contains("#")){
				if(PNIdN.equalsIgnoreCase(id))
					return printNode;
				printNode.setVisited(true);
				if (printNode != null) {
					if (printNode.getChildNodes() != null) {
						for (Node p : printNode.getChildNodes()) {
							if (!p.isVisited())
								q.add(p);
						}
					}

				}
			}
			}
		}
		return null;
	}
	
	
	public void BFS() {
		Queue<Node> q = new LinkedList<Node>();
		Node printNode;
		if (rootNode != null) {
			q.add(rootNode);
			while (!q.isEmpty()) {
				printNode = (Node) q.remove();
				printNode(printNode);
				printNode.setVisited(true);
				if (printNode != null) {
					if (printNode.getChildNodes() != null) {
						for (Node p : printNode.getChildNodes()) {
							if (!p.isVisited())
								q.add(p);
							else
								printNode(p);
						}
					}

				}
			}
		}
	}

	public void reInitializeIsVisisted(Node node) {
		if (node.isVisited())
			node.setVisited(false);
		else
			return;
		for (int i = 0; i < node.getChildNodes().size(); i++)
			reInitializeIsVisisted(node.getChildNodes().get(i));
	}

	private void inOrder(Node node) {

		if (node != null) {

			for (int i = 0; i < node.getChildNodes().size(); i++)
				inOrder(node.getChildNodes().get(i));
			printNode(node);
			// inOrder(node.right);
		}
	}

	private void preOrder(Node node) {

		if (node != null) {

			printNode(node);
			node.setVisited(true);
			for (int i = 0; i < node.getChildNodes().size(); i++)
				if (!node.getChildNodes().get(i).isVisited())
					preOrder(node.getChildNodes().get(i));
		}

	}

	private void postOrder(Node node) {

		if (node != null) {
			for (int i = node.getChildNodes().size() - 1; i >= 0; i--) {
				if (!node.getChildNodes().get(i).isVisited())
					postOrder(node.getChildNodes().get(i));
			}
			printNode(node);
			node.setVisited(true);
		}
	}

	// public void bfs()
	// {
	// // BFS uses Queue data structure
	// Queue<Node> queue = new LinkedList();
	// queue.add(this.rootNode);
	// printNode(this.rootNode);
	// rootNode.visited = true;
	// while(!queue.isEmpty()) {
	// Node node = (Node)queue.remove();
	// Node child=null;
	// while((child=getUnvisitedChildNode(node))!=null) {
	// child.visited=true;
	// printNode(child);
	// queue.add(child);
	// }
	// }
	// // Clear visited property of nodes
	// clearNodes();
	// }
	//
	// Node getUnvisitedChildNode(Node n)
	// {
	// int j;
	//
	// for ( j = 0; j < NNodes; j++ )
	// {
	// if ( adjMatrix[n][j] > 0 )
	// {
	// if ( ! visited[j] )
	// return(j);
	// }
	// }
	//
	// return(-1);
	// }
	//
	// void clearNodes()
	// {
	// for (int i = 0; i < allVisited.length; i++)
	// allVisited[i] = false;
	// }
	//
	public void printNode(Node n) {
		System.out.print("Node ID : " + n.getNodeId());
		if(n.getParentNode()!=null)
		System.out.print("------>Parent ID: "+n.getParentNode().getNodeId());
		System.out.println();
		// System.out.println("Approach Level : "+n.getApproachLevel());

	}

}
