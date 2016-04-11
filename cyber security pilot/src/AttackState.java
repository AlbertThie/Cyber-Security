public class AttackState {
	
	// Class to represent one state-action for attacker.
	// Defender is (probably) almost the same, only state is different.
	// Attacker has List of state-action pairs with q-values. N different states (number of resources), 
	// 10 different types of attacks, then 10*N^2 different state-actions.
	// Defender has 10 defense values, so D^10 different defense value combinations (D = max def value).
	// Defender has 10 different def values to increment (fixed incr. value), so 10*D^10 different state-actions. 
	
	// STATE
	private Node node;			// current node (attacker only knows ID)
	
	// ACTION
	private String attackType;	// type of attack
	
	// PARAMS
	private double Qvalue;
	
	
	public AttackState(Node n, String attType){
		this.setNode(n);
		this.setAttackType(attType);
		this.setQvalue(0);
	}

	public void updateQvalue(double reward, double alpha){
		double q = getQvalue();
		
		double newq = q+alpha*(reward-q);
		setQvalue(newq);
	}
	
	// GETTERS/SETTERS
	
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

	public String getAttackType() {
		return attackType;
	}

	public void setAttackType(String attackType) {
		this.attackType = attackType;
	}
}
