import javax.swing.JPanel;


public class Panel extends JPanel {

	private Simulation model;
	private MainFrame frame;
	
	public Panel(Simulation s, MainFrame f){
		this.setModel(s);
		this.setFrame(f);
	}

	public Simulation getModel() {
		return model;
	}

	public void setModel(Simulation model) {
		this.model = model;
	}

	public MainFrame getFrame() {
		return frame;
	}

	public void setFrame(MainFrame frame) {
		this.frame = frame;
	}
	
}
