import java.util.Random;

public class DefenderAgent extends Agent {

	private int resources; 
	private Network world;
	public final String options[] = {"Injection", "Authentication", "CrossSite", "References", "Misconfiguration", "Exposure", "Access","Forgery", "Vulnerabilities", "Redirects"};
	public DefenderAgent(int monies, Network nw){
		super();
		this.setResources(monies);
		this.setWorld(nw);
	}
	public int getResources() {
		return resources;
	}
	public void setResources(int resources) {
		this.resources = resources;
	}
	public void defend(String type, int investment, int nodeID){
		if( type == "Injection"){
			Node current = this.getWorld().getNodes().get(nodeID);
			
			current.setDefInjection(current.getDefInjection() + investment );
			
			
		}
		if( type == "Authentication"){
			Node current = this.getWorld().getNodes().get(nodeID);
			current.setDefAuthentication(current.getDefAuthentication() + (0.5 * investment));
			
			
		}
		if( type == "CrossSite"){
			Node current = this.getWorld().getNodes().get(nodeID);
			current.setDefCrossSite(current.getDefCrossSite() + (0.5 * investment));
			
			
		}
		if( type == "References"){
			Node current = this.getWorld().getNodes().get(nodeID);
			current.setDefReferences(current.getDefReferences() + investment );
			
			
		}
		if( type == "Misconfiguration"){
			Node current = this.getWorld().getNodes().get(nodeID);
			current.setDefMisconfiguration(current.getDefMisconfiguration() + investment );
			
			
		}
		if( type == "Exposure"){
			Node current = this.getWorld().getNodes().get(nodeID);
			current.setDefExposure(current.getDefExposure() + ( 1.5 * investment ));
			
			
		}
		if( type == "Access"){
			Node current = this.getWorld().getNodes().get(nodeID);
			current.setDefAccess(current.getDefAccess() + investment );
			
			
		}
		if( type == "Forgery"){
			Node current = this.getWorld().getNodes().get(nodeID);
			current.setDefForgery(current.getDefForgery() + investment );
			
			
		}
		if( type == "Vulnerabilities"){
			Node current = this.getWorld().getNodes().get(nodeID);
			current.setDefVulnerabilities(current.getDefVulnerabilities() + ( 0.5 * investment ));
			
			
		}
		if( type == "Injection"){
			Node current = this.getWorld().getNodes().get(nodeID);
			current.setDefRedirects(current.getDefRedirects() + ( 1.5 * investment ));
			
			
		}
		
		
	}
	public void defenderStep(){
		if(this.getStrategy() == "Random"){
			Random rand = new Random();
			int numActions; 
			numActions = rand.nextInt(this.getWorld().getNodes().size());
			for (int i = 0; i < numActions; i++ ){
				int investment = rand.nextInt(25);
				int target = rand.nextInt(this.getWorld().getNodes().size());
				this.setResources(this.getResources() - investment);
				this.defend(this.options[rand.nextInt(10)], investment, target);
				
			}
		}
	}
	public Network getWorld() {
		return world;
	}
	public void setWorld(Network world) {
		this.world = world;
	}
	
	
}
