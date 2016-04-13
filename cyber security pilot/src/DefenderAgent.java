import java.util.ArrayList;
import java.util.Random;

public class DefenderAgent extends Agent {

	private int resources; 
	private Network world;
	public final String options[] = {"Injection", "Authentication", "CrossSite", "References", "Misconfiguration", "Exposure", "Access","Forgery", "Vulnerabilities", "Redirects", "Detection"};
	
	private ArrayList<DefendState> states;
	private ArrayList<DefendState> actionSeq;
	
	public DefenderAgent(int monies, Network nw){
		super();
		this.setResources(monies);
		this.setWorld(nw);
		this.setStates(new ArrayList<DefendState>());
		this.setActionSeq(new ArrayList<DefendState>());
	}
	
	// tries to find state-action pair in list of previous states
	public DefendState findState(DefendState s){
		for(DefendState d : getStates()){
			//TODO properly compare states d and s on the networks and chosen node
			if(d.getDefendType()==s.getDefendType() && d.getNetworkState().equals(getWorld())){
				return d;
			}
		}
		return null;
	}
	
	// updates q values for all state-actions in actionSeq
	public void updateQLearn(double reward, double alpha){
		for(DefendState d : getActionSeq()){
			DefendState curstate = findState(d);
			if(curstate==null){ // new state-action
				// this might be wrong
				Network nw = d.getNetworkState();
				Node node = d.getNode();
				String deftype = d.getDefendType();
				curstate = new DefendState(nw, node, deftype);
			}
			curstate.updateQvalue(reward, alpha);
		}
	}
	
	public void defend(String type, int nodeID){
		if( type == "Injection"){
			Node current = this.getWorld().getNodes().get(nodeID);
			current.setDefInjection(current.getDefInjection() + 1 );
		}
		if( type == "Authentication"){
			Node current = this.getWorld().getNodes().get(nodeID);
			current.setDefAuthentication(current.getDefAuthentication() + 1);
			
			
		}
		if( type == "CrossSite"){
			Node current = this.getWorld().getNodes().get(nodeID);
			current.setDefCrossSite(current.getDefCrossSite() + 1);
			
			
		}
		if( type == "References"){
			Node current = this.getWorld().getNodes().get(nodeID);
			current.setDefReferences(current.getDefReferences() + 1 );
			
			
		}
		if( type == "Misconfiguration"){
			Node current = this.getWorld().getNodes().get(nodeID);
			current.setDefMisconfiguration(current.getDefMisconfiguration() + 1 );
			
			
		}
		if( type == "Exposure"){
			Node current = this.getWorld().getNodes().get(nodeID);
			current.setDefExposure(current.getDefExposure() + 1);
			
			
		}
		if( type == "Access"){
			Node current = this.getWorld().getNodes().get(nodeID);
			current.setDefAccess(current.getDefAccess() + 1 );
			
			
		}
		if( type == "Forgery"){
			Node current = this.getWorld().getNodes().get(nodeID);
			current.setDefForgery(current.getDefForgery() + 1 );
			
			
		}
		if( type == "Vulnerabilities"){
			Node current = this.getWorld().getNodes().get(nodeID);
			current.setDefVulnerabilities(current.getDefVulnerabilities() + 1);
			
			
		}
		if( type == "Redirects"){
			Node current = this.getWorld().getNodes().get(nodeID);
			current.setDefRedirects(current.getDefRedirects() + 1);
		}
		
		if( type == "Detection"){
			Node current = this.getWorld().getNodes().get(nodeID);
			current.setDetect(current.getDetect() + 1);
			
			
		}	
	}
	
	public void defenderStep(){
		switch(this.getStrategy()){
		case "Random":
			Random rand = new Random();
			int target = rand.nextInt(this.getWorld().getNodes().size());
			String action = this.options[rand.nextInt(11)];
			this.defend(action, target);
			//TODO STORE CHOSEN NODE AND DEFEND TYPE ALONG WITH CURRENT NETWORK IN ACTIONSEQ
			break;
		case "Q-Learning":
			//TODO SELECT NODE AND DEFEND TYPE AND PERFORM DEFEND
			//TODO STORE ALONG WITH CURRENT NETWORK IN ACTIONSEQ
			break;
		default:
			break;
		}
	}
	
	public Network getWorld() {
		return world;
	}
	
	public void setWorld(Network world) {
		this.world = world;
	}

	public int getResources() {
		return resources;
	}
	
	public void setResources(int resources) {
		this.resources = resources;
	}

	public ArrayList<DefendState> getStates() {
		return states;
	}

	public void setStates(ArrayList<DefendState> states) {
		this.states = states;
	}

	public ArrayList<DefendState> getActionSeq() {
		return actionSeq;
	}

	public void setActionSeq(ArrayList<DefendState> actionSeq) {
		this.actionSeq = actionSeq;
	}
}
