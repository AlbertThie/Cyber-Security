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
	
	public void  attack(String attackType, Node attack) {
		if (attackType == "Injection"){
			attack.setAttInjection(attack.getAttInjection()+1);
		}
			
		if (attackType == "Authentication"){
			attack.setAttAuthentication(attack.getAttAuthentication()+1);
		}
		if (attackType == "CrossSite"){
			attack.setAttCrossSite(attack.getAttCrossSite()+1);	
		}	
		if (attackType == "References"){
			attack.setAttReferences(attack.getAttReferences()+1);
		}	
		if (attackType == "Misconfiguration") {
			attack.setAttMisconfiguration(attack.getAttMisconfiguration()+1);
		}	
		if (attackType == "Exposure") {
			attack.setAttExposure(attack.getAttExposure()+1);
		}	
		if (attackType == "Access") {
			attack.setAttAccess(attack.getAttAccess()+1);
		}	
		if (attackType == "Forgery") {
			attack.setAttForgery(attack.getAttForgery()+1);
		}	
		if (attackType == "Vulnerabilities") {
			attack.setAttVulnerabilities(attack.getAttVulnerabilities()+1);
		}	
		if (attackType == "Redirects"){
			attack.setAttRedirects(attack.getAttRedirects()+1);
		}
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
