import java.util.Random;

public class DefenderAgent extends Agent {

	private int resources; 
	private Network world;
	public final String options[] = {"Injection", "Authentication", "CrossSite", "References", "Misconfiguration", "Exposure", "Access","Forgery", "Vulnerabilities", "Redirects","Detection"};
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
			current.setDefExposure(current.getDefExposure() + 1 );
			
			
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
		
		if( type == " Detection"){
			Node current = this.getWorld().getNodes().get(nodeID);
			current.setDetect(current.getDetect() + 1);
			
			
		}
		
		
	}
	public void defenderStep(){
		if(this.getStrategy() == "Random"){
			Random rand = new Random();
			int target = rand.nextInt(this.getWorld().getNodes().size());
			String action = this.options[rand.nextInt(11)];
			this.defend(action, target);
			//write action + target
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
