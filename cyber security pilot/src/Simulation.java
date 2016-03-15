
public class Simulation {
	
	private DefenderAgent dAgent;
	private AttackAgent aAgent;
	
	public Simulation() {
		setdAgent(new DefenderAgent());
		setaAgent(new AttackAgent());
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
}
