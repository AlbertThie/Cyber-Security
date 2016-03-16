import java.util.ArrayList;


public class Network {

	private ArrayList<Node> nodes = new ArrayList<Node>();
	private Node nodeSelected;
	
	public Network(){
		
	}
	
	public void addNode(Node n){
		nodes.add(n);
	}
	
	public Node getNode(String ID){
		for (Node n : nodes){
			if(n.getID().equals(ID)){
				return n;
			}
		}
		return null;
	}
	
	public ArrayList<Node> getNodes() {
		return nodes;
	}
	
	public void setNodes(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}
	
	public void clearNetwork(){
		nodes.clear();
	}

	public Node getNodeSelected() {
		return nodeSelected;
	}

	public void setNodeSelected(Node nodeSelected) {
		this.nodeSelected = nodeSelected;
	}
	
}
