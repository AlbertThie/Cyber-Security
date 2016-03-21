import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;


public class InfoPanel extends JPanel implements Observer {
	
	private MainFrame frame;
	private Simulation s;
	
	
	public InfoPanel(Simulation model, MainFrame mf){
		setFrame(mf);
		setS(model);
		Selectioncontroller sc = new Selectioncontroller(s);
		this.addMouseListener(sc);
		this.addMouseMotionListener(sc);
	}
	
	public void paintNodeInfo(Graphics g, int yStart){
		g.setColor(Color.YELLOW);
		int xStart = 10;
		Rectangle rect=new Rectangle(5,yStart,300,120);
        Node sel = s.getNw().getNodeSelected();
		g.drawRoundRect(rect.x, rect.y, rect.width, rect.height, 10, 5);
		g.setColor(new Color(219,211,90));
		g.fillRoundRect(rect.x, rect.y, rect.width, rect.height, 10, 5);
		g.setColor(Color.BLACK);
        g.drawString("Number of nodes:"+s.getNw().getNodes().size(), xStart, yStart+15);
        g.drawString("_________________________________________", xStart, yStart+20);
        g.drawString("Current selected node: "+(sel==null?"no node selected":sel.getID()), xStart, yStart+35);
        if(sel!= null){
        	g.drawString("Detection value: "+sel.getDetect(), xStart, yStart+50);
        	g.drawString("Attacking value: "+sel.getDefAccess(), xStart, yStart+65);
        	g.drawString("Defensive value: "+sel.getDefInjection(), xStart, yStart+80);
        	g.drawString("Asset value: "+sel.getValue(), xStart, yStart+95);
        	g.drawString("Number of neighbouring nodes: "+sel.getNeighbours().size(), xStart, yStart+110);
        	
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
		frame.enableButtons();
		super.paintComponent(g);
		paintNodeInfo(g, 20);
    }
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		this.validate();
		this.repaint();
	}
}
