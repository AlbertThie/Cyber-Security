
public class DAction extends Action {
	public DAction() {
		// TODO Auto-generated constructor stub
	}
	public void AddDetect(Node n, int invest) {
		n.setDetect(n.getDetect() + invest);
		
	}
	public void Adddefence(Node n, int invest ){
		n.setDef(n.getDef() + invest);
	}
}
