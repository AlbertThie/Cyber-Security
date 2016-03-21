import java.awt.Rectangle;
import java.util.ArrayList;


public class Node {

	private double value, def, detect;
	private int xPos, yPos;
	private String ID;
	private ArrayList<Node> neighbours = new ArrayList<Node>();
	
	public Node(String ID, double v, double def, double det, int xPos, int yPos){
		this.setID(ID);
		this.value=v;
		this.def=def;
		this.detect=det;
		this.setxPos(xPos);
		this.setyPos(yPos);
	}

	public Rectangle getRect(){
		return new Rectangle(getxPos(), getyPos(), 30, 30);
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

	public double getValue() {
		return value;
	}


	public void setValue(double value) {
		this.value = value;
	}
	
	public double getDef() {
		return def;
	}


	public void setDef(double def) {
		this.def = def;
	}


	public double getDetect() {
		return detect;
	}


	public void setDetect(double detect) {
		this.detect = detect;
	}


	public int getxPos() {
		return xPos;
	}


	public void setxPos(int xPos) {
		this.xPos = xPos;
	}


	public int getyPos() {
		return yPos;
	}


	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	
	public String toString(){
		return value+" "+def+" "+detect+" "+xPos+" "+yPos+" "+ID;
	}
}
