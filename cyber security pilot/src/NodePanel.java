import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;


public class NodePanel extends JPanel implements Observer {
	
	private MainFrame frame;
	private Simulation s;
	private final int nodesize = 30;
	
	public NodePanel(Simulation model, MainFrame frame){
		setFrame(frame);
		setS(model);
		Selectioncontroller sc = new Selectioncontroller(s);
		this.addMouseListener(sc);
		this.addMouseMotionListener(sc);
	}
	
	public void paintNetwork(Graphics g){
		for(Node n : s.getNw().getNodes()){
			g.setColor(n.equals(s.getNw().getNodeSelected()) ? Color.ORANGE : Color.YELLOW);
			
			if (n.getxPos()<getBounds().getMinX()){
				n.setxPos((int)getBounds().getMinX());
			} 
			if(n.getxPos()>getBounds().getMaxX()-nodesize){
				n.setxPos((int)getBounds().getMaxX()-nodesize);
			}
			if (n.getyPos()<getBounds().getMinY()){
				n.setyPos((int)getBounds().getMinY());
			} 
			/*if(n.getyPos()>getBounds().getMaxY()-nodesize){
				n.setyPos((int)getBounds().getMaxY()-nodesize);
			}*/
			if(n.getyPos()>690){
				n.setyPos(690);
			}
			g.drawOval(n.getxPos(), n.getyPos(), nodesize, nodesize);
			g.fillOval(n.getxPos(), n.getyPos(), nodesize, nodesize);
		}
		paintConnections(g);
	}
	
	public void paintConnections(Graphics g){
		g.setColor(Color.YELLOW);
		for (Node n : s.getNw().getNodes()){
			for (Node n2 : n.getNeighbours()) {
				Rectangle r1 = n.getRect();
				Rectangle r2 = n2.getRect();
				int x1 = r1.x+r1.width/2;
				int y1 = r1.y+r1.height/2;
				int x2 = r2.x+r2.width/2;
				int y2 = r2.y+r2.height/2;
				g.drawLine(x1, y1, x2, y2);
			}
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
		this.validate();
		this.repaint();
	}
}
