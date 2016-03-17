import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;


public class NodePanel extends JPanel implements Observer {
	
	private MainFrame frame;
	private Simulation s;
	
	public NodePanel(Simulation model, MainFrame frame){
		setFrame(frame);
		setS(model);
		//setLayout( new BorderLayout() );
		//setPreferredSize(new Dimension(200,500));
		setBounds(0, 0, 700, 768);
		System.out.println("Thing of node panel: "+this.getBounds());
		Selectioncontroller sc = new Selectioncontroller(s);
		this.addMouseListener(sc);
		this.addMouseMotionListener(sc);
	}
	
	public void paintNetwork(Graphics g){
		for(Node n : s.getNw().getNodes()){
			g.setColor(n.equals(s.getNw().getNodeSelected()) ? Color.ORANGE : Color.YELLOW);
			g.drawOval(n.getxPos(), n.getyPos(), 30, 30);
			g.fillOval(n.getxPos(), n.getyPos(), 30, 30);
		}
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

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		paintNetwork(g);
    }
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		this.validate();
		this.repaint();
		
	}
	
	
}
