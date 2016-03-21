
public class DefenderAgent extends Agent {

	private int resources; 
	public DefenderAgent(){
		super();
	}
	public int getResources() {
		return resources;
	}
	public void setResources(int resources) {
		this.resources = resources;
	}
	public void defend(String type, int investment, int nodeID){
		if( type == "Injection"){
			Node current = this.getNw().getNodes().get(nodeID);
			current.setDefInjection(current.getDefInjection() + investment );
			
			
		}
		if( type == "Authentication"){
			Node current = this.getNw().getNodes().get(nodeID);
			current.setDefAuthentication(current.getDefAuthentication() + (0.5 * investment));
			
			
		}
		if( type == "CrossSite"){
			Node current = this.getNw().getNodes().get(nodeID);
			current.setDefCrossSite(current.getDefCrossSite() + (0.5 * investment));
			
			
		}
		if( type == "References"){
			Node current = this.getNw().getNodes().get(nodeID);
			current.setDefReferences(current.getDefReferences() + investment );
			
			
		}
		if( type == "Misconfiguration"){
			Node current = this.getNw().getNodes().get(nodeID);
			current.setDefMisconfiguration(current.getDefMisconfiguration() + investment );
			
			
		}
		if( type == "Exposure"){
			Node current = this.getNw().getNodes().get(nodeID);
			current.setDefExposure(current.getDefExposure() + ( 1.5 * investment ));
			
			
		}
		if( type == "Access"){
			Node current = this.getNw().getNodes().get(nodeID);
			current.setDefAccess(current.getDefAccess() + investment );
			
			
		}
		if( type == "Forgery"){
			Node current = this.getNw().getNodes().get(nodeID);
			current.setDefForgery(current.getDefForgery() + investment );
			
			
		}
		if( type == "Vulnerabilities"){
			Node current = this.getNw().getNodes().get(nodeID);
			current.setDefVulnerabilities(current.getDefVulnerabilities() + ( 0.5 * investment ));
			
			
		}
		if( type == "Injection"){
			Node current = this.getNw().getNodes().get(nodeID);
			current.setDefRedirects(current.getDefRedirects() + ( 1.5 * investment ));
			
			
		}
		
		
	}
}
