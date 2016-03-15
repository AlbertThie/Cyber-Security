
public class AAction extends Action {

	public AAction() {
		// TODO Auto-generated constructor stub
	}
	public Node move(Node current, Node target){
		if (current.getNeighbours().contains(target))
			//return target node, move has succeeded
			return target;
		else
			//return current node, move has failed
			return current;
		
	}
	public double injection (Node current, Double invest){
		//easy explotation and common prevelance
		double diff = 1.5;
		double detectability = 1;
		double value = Math.max(0,(diff * invest -current.getDef()));
		if(value > 0)
			flag(current, detectability);	
		return value;
	}
	
	public double injection (Node current, Double invest){
		//easy explotation and common prevelance
		double diff = 1.5;
		double detectability = 1;
		double value = Math.max(0,(diff * invest -current.getDef()));
		if(value > 0)
			flag(current, detectability);	
		return value;
	}
}
