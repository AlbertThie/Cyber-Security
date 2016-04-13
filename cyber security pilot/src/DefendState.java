
public class DefendState {

	// STATE
	private Network networkState; 	// Current network?
	
	// ACTION
	private Node node;				// Node on which a value is increased
	private String defendType;		// Defend type that gets its value increased
	
	// PARAMS
	private double Qvalue;
	
	public DefendState(Network nw, Node node, String defType){
		this.setNetworkState(nw);
		this.setNode(node);
		this.setDefendType(defType);
	}
	
	public void updateQvalue(double reward, double alpha){
		double q = getQvalue();
		double newq = q + alpha*(reward-q);
		setQvalue(newq);
	}

	// GETTERS/SETTERS

	public Network getNetworkState() {
		return networkState;
	}

	public void setNetworkState(Network networkState) {
		this.networkState = networkState;
	}
	
	public double getQvalue() {
		return Qvalue;
	}

	public void setQvalue(double qvalue) {
		Qvalue = qvalue;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public String getDefendType() {
		return defendType;
	}

	public void setDefendType(String defendType) {
		this.defendType = defendType;
	}
}

