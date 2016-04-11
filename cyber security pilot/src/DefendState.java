
public class DefendState {

	// STATE
	private Network networkState; // Network?
	private double resources;
	
	// ACTION
	private Node node;
	private String defendType;
	private double investment;
	
	// PARAMS
	private double Qvalue;
	private double alpha;			// learning rate
	
	public DefendState(Network nw, double resources, Node node, String defType, double inv, double alpha){
		this.setNetworkState(nw);
		this.setResources(resources);
		this.setNode(node);
		this.setDefendType(defType);
		this.setInvestment(inv);
		this.setAlpha(alpha);
	}
	
	public void updateQvalue(double reward, double discount){
		double q = getQvalue();
		double alpha = getAlpha();
		double future = 0; // estimate optimal future value: HOE? (misschien nu random?)
		double newq = q + alpha*(reward+discount*future-q);
		setQvalue(newq);
	}

	public double getResources() {
		return resources;
	}

	public void setResources(double resources) {
		this.resources = resources;
	}

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

	public double getAlpha() {
		return alpha;
	}

	public void setAlpha(double alpha) {
		this.alpha = alpha;
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

	public double getInvestment() {
		return investment;
	}

	public void setInvestment(double investment) {
		this.investment = investment;
	}
}
