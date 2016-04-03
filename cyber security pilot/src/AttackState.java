public class AttackState {
	
	// Class to represent one state-action for attacker.
	// Defender is (probably) almost the same, only state is different.
	// Attacker has List of state-action pairs with q-values. N different states (number of resources), 
	// 10 different types of attacks, then 10*N^2 different state-actions.
	// Defender has 10 defense values, so D^10 different defense value combinations (D = max def value).
	// Defender has 10 different def values to increment (fixed incr. value), so 10*D^10 different state-actions. 
	
	private double networkState; 	// resources?
	private double action; 			// resources ingezet?
	private double Qvalue; 			// Qvalue voor dit state-actie paar
	
	private double alpha;			// learning rate
	
	public AttackState(double networkState, double action){
		this.setNetworkState(networkState);
		this.setAction(action);
	}

	public void updateQvalue(double reward, double discount){
		double q = getQvalue();
		double alpha = getAlpha();
		double future = 0; // estimate optimal future value: HOE? (misschien nu random?)
		double newq = q + alpha*(reward+discount*future-q);
		setQvalue(newq);
	}
	
	public double getNetworkState() {
		return networkState;
	}

	public void setNetworkState(double networkState) {
		this.networkState = networkState;
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
}
