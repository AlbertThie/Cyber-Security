import java.util.ArrayList;
import java.util.Random;

public class AttackAgent extends Agent {
	
	private Node currentNode;
	private double resource;
	public final String options[] = {"Move", "Injection", "Authentication", "CrossSite", "References", "Misconfiguration", "Exposure", "Access","Forgery", "Vulnerabilities", "Redirects"};
	private ArrayList<AttackState> states;
	private ArrayList<AttackState> actionSeq;
	
	public AttackAgent(int money, Node n){
		super();
		this.setResource(money);
		this.setCurrentNode(n);
		this.setStates(new ArrayList<AttackState>());
		this.setActionSeq(new ArrayList<AttackState>());
	}

	public Node getCurrentNode() {
		return currentNode;
	}


	public void setCurrentNode(Node currentNode) {
		this.currentNode = currentNode;
	}
	
	// checks if two nodes are the same, defend and detect values do not matter
	public boolean sameNodes(Node a, Node s){
		return    a.getID()==s.getID()
			   && a.getAttAccess()==s.getAttAccess()
			   && a.getAttAuthentication()==s.getAttAuthentication()
			   && a.getAttCrossSite()==s.getAttCrossSite()
			   && a.getAttExposure()==s.getAttExposure()
			   && a.getAttForgery()==s.getAttForgery()
			   && a.getAttInjection()==s.getAttInjection()
			   && a.getAttMisconfiguration()==s.getAttMisconfiguration()
			   && a.getAttRedirects()==s.getAttRedirects()
			   && a.getAttReferences()==s.getAttReferences()
			   && a.getAttVulnerabilities()==s.getAttVulnerabilities();
	}
	

	// tries to find current state-action in the list of previous states
	public AttackState findState(AttackState s){
		for(AttackState a : getStates()){
			if(a.getAttackType()==s.getAttackType() && sameNodes(a.getNode(),s.getNode())){
				return a;
			}
		}
		return null;
	}
	
	// updates q values for all states in current sequence
	public void updateQLearn(double reward, double alpha){
		for (AttackState s : actionSeq){
			AttackState curstate = findState(s);
			if(curstate==null){ // new state-action
				AttackState newState = new AttackState(s.getNode(), s.getAttackType());
				states.add(newState);
				curstate = newState;
			} 
			curstate.updateQvalue(reward, alpha);
		}
	}
	
	
	// add 1 to the chosen attack type on the chosen node
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


	public double getResource() {
		return resource;
	}


	public void setResource(int resource) {
		this.resource = resource;
	}
	
	// Do one move: select the node to attack and the type of attack
	public void attackerStep(){
		switch (this.getStrategy()){
			case "random":
				Random randomGenerator = new Random();
				Node n = currentNode.getNeighbours().get(randomGenerator.nextInt(currentNode.getNeighbours().size()));
				int pick;
				pick = currentNode.getNeighbours().size()>0 ? randomGenerator.nextInt(11) : randomGenerator.nextInt(10)+1;
				if(pick == 0) {
				} else {
					this.attack(this.options[pick], n);
				}
				//TODO STORE SELECTED NODE AND ATTACK TYPE IN ACTIONSEQ
				break;
			case "Q-Learning":
				//TODO SELECT NODE AND ATTACK TYPE, STORE THEM IN ACTIONSEQ
				
				break;
			default:
				break;
		}
	}

	public ArrayList<AttackState> getStates() {
		return states;
	}

	public void setStates(ArrayList<AttackState> states) {
		this.states = states;
	}

	public ArrayList<AttackState> getActionSeq() {
		return actionSeq;
	}

	public void setActionSeq(ArrayList<AttackState> actionSeq) {
		this.actionSeq = actionSeq;
	}
}
