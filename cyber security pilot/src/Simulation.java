import java.util.Observable;


public class Simulation extends Observable {
	
	private DefenderAgent dAgent;
	private AttackAgent aAgent;
	private Network nw;
	private double rewardRatio;
	
	public Simulation() {
		setdAgent(new DefenderAgent());
		setaAgent(new AttackAgent());
		nw = new Network();
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
	public void modelStep(){
		getaAgent().attackerStep();
		getdAgent().defenderStep();
		this.rewardDefender();
		
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
}
