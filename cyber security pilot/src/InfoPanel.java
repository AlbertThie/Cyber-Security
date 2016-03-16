import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;


public class InfoPanel extends JPanel implements Observer {
	
	private MainFrame frame;
	private Simulation s;
	
	
	public InfoPanel(Simulation model, MainFrame mf){
		
		setFrame(mf);
		setS(model);
		//BorderLayout layout = new BorderLayout();
		//layout.setHgap(10);
		//layout.setVgap(10);
		//setLayout(layout);
		//setVisible(true);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.BLACK);
        g.drawString("fafa", getWidth(), getHeight());
    }

	public MainFrame getFrame() {
		return frame;
	}

	public void setFrame(MainFrame frame) {
		this.frame = frame;
	}

	public Simulation getS() {
		return s;
	}

	public void setS(Simulation s) {
		this.s = s;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
