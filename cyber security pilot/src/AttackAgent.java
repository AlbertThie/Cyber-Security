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
	

	public AttackState findState(AttackState s){
		for(AttackState a : getStates()){
			if(a.getAttackType()==s.getAttackType() && sameNodes(a.getNode(),s.getNode())){
				return a;
			}
		}
		return null;
	}
	
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
	
	public void attackerStep(){
		if (this.getStrategy() == "Random" ) {
			Random randomGenerator = new Random();
			int pick;
			pick = currentNode.getNeighbours().size()>0 ? randomGenerator.nextInt(11) : randomGenerator.nextInt(10)+1;
			if(pick == 0) {
			} else {
				//TODO ATTACK OTHER NODE THAN CURRENT
				this.attack(this.options[pick], currentNode);
			}
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
