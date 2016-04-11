import java.util.ArrayList;


public class Agent {
	
	private String strategy;
	private Network nw;
	

	public String getStrategy() {
		return strategy;
	}

	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}
	
	public int getRLvalue(){
		int x = 50;
		return x;
	}

	public Network getNw() {
		return nw;
	}

	public void setNw(Network nw) {
		this.nw = nw;
	}
	
	public void updateQLearn(){
		
	}
	
}
