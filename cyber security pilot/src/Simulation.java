import java.util.Observable;


public class Simulation extends Observable {
	
	private DefenderAgent dAgent;
	private AttackAgent aAgent;
	private Network nw;
	private Network startNetwork;
	private int games = 1;
	private final double alpha = 0.5; 			// learning rate
	
	public Simulation() {
		nw = new Network();
		nw.addNode(new Node("Start", true, 0.0, 30, 30, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
		startNetwork = new Network();
		startNetwork.addNode(new Node("Start", true, 0.0, 30, 30, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
		setdAgent(new DefenderAgent(1000, nw));
		setaAgent(new AttackAgent(1000,nw.getNodes().get(0)));
	}

	public DefenderAgent getdAgent() {
		return dAgent;
	}

	public void setdAgent(DefenderAgent dAgent) {
		this.dAgent = dAgent;
	}

	public AttackAgent getaAgent() {
		return aAgent;
	}

	public void setaAgent(AttackAgent aAgent) {
		this.aAgent = aAgent;
	}

	public Network getNw() {
		return nw;
	}

	public void setNw(Network nw) {
		this.nw = nw;
	}
	
	
	public void updateAttackAgent(double reward){
		String strat = getaAgent().getStrategy();
		switch(strat){
		case("Random"):
			 break;
		case("Q-Learning"):
			aAgent.updateQLearn(reward, alpha);
			break;
		}
			
	}
	
	public void updateDefendAgent(double reward){
		String strat = getdAgent().getStrategy();
		switch(strat){
		case("Random"):
			 break;
		case("Q-Learning"):
			dAgent.updateQLearn(reward, alpha);
			break;
		}
			
	}
	
	public void modelStep(){
		getaAgent().attackerStep();
		getdAgent().defenderStep();
		if(checkDetected() || checkEmptyAsset()){
			restart();
			games ++;
			System.out.println("Gamenumber: "+games);
			// Update q values
			if(checkDetected()){
				// Punish attacker 100
				updateDefendAgent(100);
				updateAttackAgent(-100);
			}else{
				// Reward attacker 100
				updateDefendAgent(-100);
				updateAttackAgent(100);
			}
			//TODO clear action sequences of both agents
		
		}
	}
	
	public boolean checkDetected(){
		for(Node n : this.getNw().getNodes()){
		   if(n.isDetected()){
		    System.out.println("Attacker detected in node "+n.getID());
		    return true;
		   }
		}
		return false;
	}
	
	public boolean checkEmptyAsset(){
		// check if the value of all nodes is zero
		for(Node n : this.getNw().getNodes()){
		   if(n.getValue()){
		    return false;
		   }
		}
	    System.out.println("All nodes are empty");
		return true;
	}
	
	public void restart(){
		this.setNw(getStartNetwork().getClone());
		dAgent.setNw(nw);
		aAgent.setCurrentNode(nw.getNodes().get(0));
	}
	
	public void noti(){
		this.setChanged();
		this.notifyObservers();
	}

	public Network getStartNetwork() {
		return startNetwork;
	}

	public void setStartNetwork(Network startNetwork) {
		this.startNetwork = startNetwork;
	}
}
