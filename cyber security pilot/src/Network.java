import java.util.ArrayList;


public class Network {

	private ArrayList<Node> nodes = new ArrayList<Node>();
	private ArrayList<Connection> connections = new ArrayList<Connection>();
	private Node nodeSelected;
	
	class Connection{
		public Node n1, n2;
		
		public Connection(Node n1, Node n2){
			this.n1=n1;
			this.n2=n2;
		}
	}
	
	public Network(){
	}
	
	public Network getClone(){
		Network nw = null;
		try {
			nw = (Network)this.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nw;
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
		this.nodes.clear();
		this.nodeSelected = null;
	}

	public Node getNodeSelected() {
		return nodeSelected;
	}

	public void setNodeSelected(Node nodeSelected) {
		this.nodeSelected = nodeSelected;
	}
	
	public void makeConnection(Node n1, Node n2){
		connections.add(new Connection(n1,n2));
	}

	public ArrayList<Connection> getConnections() {
		return connections;
	}

	public void setConnections(ArrayList<Connection> connections) {
		this.connections = connections;
	}
	
}
