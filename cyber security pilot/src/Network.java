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
		Network nw = new Network();
		for (Node n : nodes){
			// add node to nw with the same values as n
			nw.addNode(new Node(n.getID(), n.getValue(), n.getDetect(), n.getxPos(),n.getyPos(),n.getDefVulnerabilities(), n.getDefForgery(), n.getDefExposure(),n.getDefReferences(),n.getDefCrossSite(),n.getDefAuthentication(), n.getDefInjection(), n.getDefMisconfiguration(),n.getDefRedirects(),n.getDefAccess()));	
		}
		for (Connection c : connections){
			
			// add connection to nw with the same values as c
			// add neighbours to the nodes
			Node n1 = nw.getNode(c.n1.getID());
			Node n2 = nw.getNode(c.n2.getID());
			n2.addNeighbour(n1);
			n1.addNeighbour(n2);
			nw.makeConnection(n1, n2);
		}
		nw.setNodeSelected(null);
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
