import java.util.ArrayList;
import java.util.Random;

public class AttackAgent extends Agent {
	
	private Node currentNode;
	public final String options[] = {"Injection", "Authentication", "CrossSite", "References", "Misconfiguration", "Exposure", "Access","Forgery", "Vulnerabilities", "Redirects"};
	private ArrayList<AttackState> states;
	
	public AttackAgent(int money, Node n){
		super();
		this.setResource(money);
		this.setCurrentNode(n);
		this.setStates(new ArrayList<AttackState>());
	}

	public Node getCurrentNode() {
		return currentNode;
	}


	public void setCurrentNode(Node currentNode) {
		this.currentNode = currentNode;
	}
	
	public AttackState findState(){
		for(AttackState a : getStates()){
			if(a.getNode().getID().equals(getCurrentNode().getID()) && a.getResources()==getResource() ){
				return a;
			}
		}
		return null;
	}
	
	public void updateQLearn(double reward, double discount){
		AttackState curstate = findState();
		if(curstate==null){
			double alpha = 1;
			String attType = ""; // attack type
			double inv = 0; // attack value
			AttackState newState = new AttackState(getCurrentNode(), resource, attType, inv, alpha);
		} else {
			curstate.updateQvalue(reward, discount);
		}
	}
	
	public void Move() {
		ArrayList<Node> current = currentNode.getNeighbours();
		if(current.size()>0){
			int randomWidth = current.size();
			Random randomGenerator = new Random();
			setCurrentNode(current.get(randomGenerator.nextInt(randomWidth)));
		}
	}
	
	public void  attack(String attackType) {
		if (attackType == "Injection"){
			double outcome = Math.min(currentNode.getDefInjection() - investment, 0);
			if(outcome < 0){
				currentNode.flag(1);
				double damage = Math.round(1.5 * outcome);
				if(currentNode.getValue() < damage ) {
					this.resource = (int) (getResource() + currentNode.getValue());
					currentNode.setValue(0);
				} else {
					currentNode.changeValue(damage);
					this.resource = (int) (-2 * damage) + getResource();
				}
			}
		}
			
		if (attackType == "Authentication"){
			investment = 1.0 * investment;
			double outcome = Math.min(currentNode.getDefAuthentication() - investment, 0);
			if(outcome < 0){
				currentNode.flag(1);
				double damage = Math.round(1.5 * outcome);
				if(currentNode.getValue() < damage ){
					this.resource = (int) (getResource() + currentNode.getValue());
					currentNode.setValue(0);
				} else {
					currentNode.changeValue(damage);
					this.resource = (int) (-2 * damage) + getResource();
				}
			}
		}
		if (attackType == "CrossSite"){
			investment = 1.0 * investment;
			double outcome = Math.min(currentNode.getDefCrossSite() - investment, 0);
			if(outcome < 0){
				currentNode.flag(1.5);
				double damage = Math.round(outcome);
				if(currentNode.getValue() < damage ){
					this.resource = (int) (getResource() + currentNode.getValue());
					currentNode.setValue(0);
				} else {
					currentNode.changeValue(damage);
					this.resource = (int) (-2 * damage) + getResource();
				}
			}
		}	
		if (attackType == "References"){
			investment = 1.5 * investment;
			double outcome = Math.min(currentNode.getDefReferences() - investment, 0);
			if(outcome < 0){
				currentNode.flag(1.5);
				double damage = Math.round(outcome);
				if(currentNode.getValue() < damage ){
					this.resource = (int) (getResource() + currentNode.getValue());
					currentNode.setValue(0);
				} else {
					currentNode.changeValue(damage);
					this.resource = (int) (-2 * damage) + getResource();
				}
			}
		}	
		if (attackType == "Misconfiguration") {
			investment = 1.5 * investment;
			double outcome = Math.min(currentNode.getDefMisconfiguration() - investment, 0);
			if (outcome < 0) {
				currentNode.flag(1.5);
				double damage = Math.round(outcome);
				if (currentNode.getValue() < damage ) {
					this.resource = (int) (getResource() + currentNode.getValue());
					currentNode.setValue(0);
				} else {
					currentNode.changeValue(damage);
					this.resource = (int) (-2 * damage) + getResource();
				}
			}
		}	
		if (attackType == "Exposure") {
			investment = 0.5 * investment;
			double outcome = Math.min(currentNode.getDefExposure() - investment, 0);
			if(outcome < 0) {
				currentNode.flag(1);
				double damage = Math.round( 1.5 * outcome);
				if (currentNode.getValue() < damage ) {
					this.resource = (int) (getResource() + currentNode.getValue());
					currentNode.setValue(0);
				} else {
					currentNode.changeValue(damage);
					this.resource = (int) (-2 * damage) + getResource();
				}
			}
		}	
		if (attackType == "Access") {
			investment = 1.5 * investment;
			double outcome = Math.min(currentNode.getDefAccess() - investment, 0);
			if(outcome < 0) {
				currentNode.flag(1);
				double damage = Math.round(outcome);
				if(currentNode.getValue() < damage ) {
					this.resource = (int) (getResource() + currentNode.getValue());
					currentNode.setValue(0);
				} else {
					currentNode.changeValue(damage);
					this.resource = (int) (-2 * damage) + getResource();
				}
			}
		}	
		if (attackType == "Forgery") {
			investment = 1.0 * investment;
			double outcome = Math.min(currentNode.getDefForgery() - investment, 0);
			if(outcome < 0) {
				currentNode.flag(1.5);
				double damage = Math.round(outcome);
				if(currentNode.getValue() < damage ) {
					this.resource = (int) (getResource() + currentNode.getValue());
					currentNode.setValue(0);
				} else {
					currentNode.changeValue(damage);
					this.resource = (int) (-2 * damage) + getResource();
				}
			}
		}	
		if (attackType == "Vulnerabilities") {
			investment = 1.0 * investment;
			double outcome = Math.min(currentNode.getDefVulnerabilities() - investment, 0);
			if(outcome < 0) {
				currentNode.flag(0.5);
				double damage = Math.round(outcome);
				if(currentNode.getValue() < damage ) {
					this.resource = (int) (getResource() + currentNode.getValue());
					currentNode.setValue(0);
				} else {
					currentNode.changeValue(damage);
					this.resource = (int) (-2 * damage) + getResource();
				}
			}
		}	
		if (attackType == "Redirects"){
			investment = 1.0 * investment;
			double outcome = Math.min(currentNode.getDefRedirects() - investment, 0);
			if(outcome < 0) {
				currentNode.flag(1.5);
				double damage = Math.round(outcome);
				if(currentNode.getValue() < damage ) {
					this.resource = (int) (getResource() + currentNode.getValue());
					currentNode.setValue(0);
				} else {
					currentNode.changeValue(damage);
					this.resource = (int) (-2 * damage) + getResource();
				}
			}
		}
	}


	public double getResource() {
		return resource;
	}


	public void setResource(int resource) {
		this.resource = resource;
	}
	
	public void attackerStep(){
		if (this.getStrategy() == "Random" ) {
			Random randomGenerator = new Random();
			int pick;
			pick = currentNode.getNeighbours().size()>0 ? randomGenerator.nextInt(11) : randomGenerator.nextInt(10)+1;
			if(pick == 0) {
				this.Move();
			} else {
				int invest = randomGenerator.nextInt(100);
				this.resource = this.getResource() - invest;
				this.attack(this.options[pick], invest);
			}
		}
	}

	public ArrayList<AttackState> getStates() {
		return states;
	}

	public void setStates(ArrayList<AttackState> states) {
		this.states = states;
	}
}
