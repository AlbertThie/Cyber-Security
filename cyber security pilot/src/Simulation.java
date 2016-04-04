import java.util.Observable;


public class Simulation extends Observable {
	
	private DefenderAgent dAgent;
	private AttackAgent aAgent;
	private Network nw;
	private Network startNetwork;
	private double rewardRatio;
	
	public Simulation() {
		nw = new Network();
		nw.addNode(new Node("Start", 0.0, 0.0, 30, 30, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
		startNetwork = new Network();
		startNetwork.addNode(new Node("Start", 0.0, 0.0, 30, 30, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
		//this.setStartNetwork(this.getNw().clone());
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
	
	public void rewardDefender(){
		for(Node n : this.getNw().getNodes()){
			dAgent.setResources((int) Math.round(dAgent.getResources() + (n.getValue() * rewardRatio )));
			
		}
	}
	
	public void updateAttackAgent(double reward){
		String strat = getaAgent().getStrategy();
		switch(strat){
		case("Random"):
			 break;
		case("Q-Learning"):
			double discount = 0.5;
			aAgent.updateQLearn(reward, discount);
			break;
		}
			
	}
	
	public void updateDefendAgent(double reward){
		String strat = getdAgent().getStrategy();
		switch(strat){
		case("Random"):
			 break;
		case("Q-Learning"):
			double discount = 0.5;
			dAgent.updateQLearn(reward, discount);
			break;
		}
			
	}
	
	public void modelStep(){
		getaAgent().attackerStep();
		getdAgent().defenderStep();
		// TO DO compute rewards for defender and attacker
		this.rewardDefender();
		double attReward=0, defReward=0;
		updateAttackAgent(attReward);
		updateDefendAgent(defReward);
		if(checkDetected() || checkEmptyAsset()){
			restart();
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
		   if(n.getValue()>0){
		    return false;
		   }
		}
	    System.out.println("All nodes are empty");
		return true;
	}
	
	public void restart(){
		this.setNw(getStartNetwork().getClone());
		dAgent.setResources(1000);
		dAgent.setNw(nw);
		aAgent.setResource(1000);
		aAgent.setCurrentNode(nw.getNodes().get(0));
	}
	
	public void noti(){
		this.setChanged();
		this.notifyObservers();
	}

	public double getRewardRatio() {
		return rewardRatio;
	}

	public void setRewardRatio(double rewardRatio) {
		this.rewardRatio = rewardRatio;
	}

	public Network getStartNetwork() {
		return startNetwork;
	}

	public void setStartNetwork(Network startNetwork) {
		this.startNetwork = startNetwork;
	}
}
