import javax.swing.JPanel;


public class MainPanel extends JPanel {

	private MainFrame frame;
	
	public MainPanel(MainFrame mf){
		setFrame(mf);
	}

	public MainFrame getFrame() {
		return frame;
	}

	public void setFrame(MainFrame frame) {
		this.frame = frame;
	}
}
