import java.util.ArrayList;
import java.util.Random;


public class AttackAgent extends Agent {
	
	private Node currentNode;
	private int resource;
	
	public AttackAgent(){
		super();
	}
	

	public Node getCurrentNode() {
		return currentNode;
	}


	public void setCurrentNode(Node currentNode) {
		this.currentNode = currentNode;
	}
	
	private void Move(){
		ArrayList<Node> current = currentNode.getNeighbours();
		int randomWidth = current.size();
		Random randomGenerator = new Random();
		setCurrentNode(current.get(randomGenerator.nextInt(randomWidth)));
		
	}
	
	public void  attack(String attackType, double investment){
		if (attackType == "Injection"){
			investment = 1.5 * investment;
			double outcome = Math.min(currentNode.getDefInjection() - investment, 0);
			if(outcome < 0){
				currentNode.flag(1);
				double damage = Math.round(1.5 * outcome);
				currentNode.changeValue(damage);
				this.resource = (int) (-2 * damage) + getResource();
				
				
			}
			
		}
		if (attackType == "Authentication"){
			investment = 1.0 * investment;
			double outcome = Math.min(currentNode.getDefAuthentication() - investment, 0);
			if(outcome < 0){
				currentNode.flag(1);
				double damage = Math.round(1.5 * outcome);
				currentNode.changeValue(damage);
				this.resource = (int) (-2 * damage) + getResource();
				
				
			}
			
		}
		if (attackType == "CrossSite"){
			investment = 1.0 * investment;
			double outcome = Math.min(currentNode.getDefCrossSite() - investment, 0);
			if(outcome < 0){
				currentNode.flag(1.5);
				double damage = Math.round(outcome);
				currentNode.changeValue(damage);
				this.resource = (int) (-2 * damage) + getResource();
				
				
			}
			
		}	if (attackType == "References"){
			investment = 1.5 * investment;
			double outcome = Math.min(currentNode.getDefReferences() - investment, 0);
			if(outcome < 0){
				currentNode.flag(1.5);
				double damage = Math.round(outcome);
				currentNode.changeValue(damage);
				this.resource = (int) (-2 * damage) + getResource();
				
			}
			
		}	if (attackType == "Misconfiguration"){
			investment = 1.5 * investment;
			double outcome = Math.min(currentNode.getDefMisconfiguration() - investment, 0);
			if(outcome < 0){
				currentNode.flag(1.5);
				double damage = Math.round(outcome);
				currentNode.changeValue(damage);
				this.resource = (int) (-2 * damage) + getResource();
				
				
			}
			
		}	if (attackType == "Exposure"){
			investment = 0.5 * investment;
			double outcome = Math.min(currentNode.getDefExposure() - investment, 0);
			if(outcome < 0){
				currentNode.flag(1);
				double damage = Math.round( 1.5 * outcome);
				currentNode.changeValue(damage);
				this.resource = (int) (-2 * damage) + getResource();
				
			}
			
		}	if (attackType == "Access"){
			investment = 1.5 * investment;
			double outcome = Math.min(currentNode.getDefAccess() - investment, 0);
			if(outcome < 0){
				currentNode.flag(1);
				double damage = Math.round(outcome);
				currentNode.changeValue(damage);
				this.resource = (int) (-2 * damage) + getResource();
				
			}
			
		}	if (attackType == "Forgery"){
			investment = 1.0 * investment;
			double outcome = Math.min(currentNode.getDefForgery() - investment, 0);
			if(outcome < 0){
				currentNode.flag(1.5);
				double damage = Math.round(outcome);
				currentNode.changeValue(damage);
				this.resource = (int) (-2 * damage) + getResource();
				
			}
			
		}	if (attackType == "Vulnerabilities"){
			investment = 1.0 * investment;
			double outcome = Math.min(currentNode.getDefVulnerabilities() - investment, 0);
			if(outcome < 0){
				currentNode.flag(0.5);
				double damage = Math.round(outcome);
				currentNode.changeValue(damage);
				this.resource = (int) (-2 * damage) + getResource();
				
				
			}
			
		}	if (attackType == "Redirects"){
			investment = 1.0 * investment;
			double outcome = Math.min(currentNode.getDefRedirects() - investment, 0);
			if(outcome < 0){
				currentNode.flag(1.5);
				double damage = Math.round(outcome);
				currentNode.changeValue(damage);
				this.resource = (int) (-2 * damage) + getResource();
				
				
				
			}
			
		}
		
		
	}


	public int getResource() {
		return resource;
	}


	public void setResource(int resource) {
		this.resource = resource;
	}
}
