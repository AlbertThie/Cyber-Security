import java.util.ArrayList;


public class Node {

	private int value;
	private String ID;
	private ArrayList<Node> neighbours = new ArrayList<Node>();
	
	public Node(String ID, int v){
		this.setID(ID);
		this.value=v;
	}


	public int getValue() {
		return value;
	}


	public void setValue(int value) {
		this.value = value;
	}


	public ArrayList<Node> getNeighbours() {
		return neighbours;
	}

	public void addNeighbour(Node n){
		neighbours.add(n);
	}
	
	public void setNeighbours(ArrayList<Node> neighbours) {
		this.neighbours = neighbours;
	}


	public String getID() {
		return ID;
	}


	public void setID(String ID) {
		this.ID = ID;
	}
	
	public String drawNeighbours(){
		String s = "";
		for(Node n : neighbours){
			s+=n.getID();
		}
		return s;
	}
	public String toString(){
		return "Name: "+ID+"/nNeighbours: "+drawNeighbours()+"/nValue: "+value;
	}
}
