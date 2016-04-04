public class AttackState {
	
	// Class to represent one state-action for attacker.
	// Defender is (probably) almost the same, only state is different.
	// Attacker has List of state-action pairs with q-values. N different states (number of resources), 
	// 10 different types of attacks, then 10*N^2 different state-actions.
	// Defender has 10 defense values, so D^10 different defense value combinations (D = max def value).
	// Defender has 10 different def values to increment (fixed incr. value), so 10*D^10 different state-actions. 
	
	// STATE
	private Node node;
	private double resources; 	// resources?
	
	// ACTION
	private String attackType;
	private double action; 			// resources ingezet?
	
	// PARAMS
	private double Qvalue;
	private double alpha;			// learning rate
	
	
	public AttackState(Node n, double resources, String attType, double action, double alpha){
		this.setNode(n);
		this.setAlpha(alpha);
		this.setAttackType(attType);
		this.setResources(resources);
		this.setAction(action);
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

	public double getAction() {
		return action;
	}

	public void setAction(double action) {
		this.action = action;
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

	public String getAttackType() {
		return attackType;
	}

	public void setAttackType(String attackType) {
		this.attackType = attackType;
	}
}
