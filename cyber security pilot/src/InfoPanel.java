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
		Rectangle rect=new Rectangle(5,yStart,300,220);
		DefenderAgent d = s.getdAgent();
        Node sel = s.getNw().getNodeSelected();
		g.drawRoundRect(rect.x, rect.y, rect.width, rect.height, 10, 5);
		g.setColor(new Color(219,211,90));
		g.fillRoundRect(rect.x, rect.y, rect.width, rect.height, 10, 5);
		g.setColor(Color.BLACK);
		int size = d.getActionSeq().size();
		DefendState last = size>0 ? d.getActionSeq().get(d.getActionSeq().size()-1) : null;
        g.drawString((last==null ? "No Defense yet" : "Last defense: on node "+last.getNode().getID() +" with type "+last.getDefendType()), xStart, yStart+15);
        g.drawString("_________________________________________", xStart, yStart+20);
        g.drawString("Current selected node: "+(sel==null?"no node selected":sel.getID()), xStart, yStart+35);
        if(sel!= null){
        	g.drawString("Detection value: "+sel.getDetect(), xStart, yStart+50);
       // 	g.drawString("Attacking value: "+sel.getDefAccess(), xStart, yStart+65);
        	g.drawString("Injection: "+sel.getDefInjection(),xStart, yStart+65);
        	g.drawString("Authentication: "+sel.getDefAuthentication(),xStart, yStart+80);
        	g.drawString("CrossSite: "+sel.getDefCrossSite(),xStart, yStart+95);
        	g.drawString("References: "+sel.getDefReferences(),xStart, yStart+110);
        	g.drawString("Missconfiguration: "+sel.getDefMisconfiguration(),xStart, yStart+125);
        	g.drawString("Exposure: "+sel.getDefExposure(),xStart, yStart+140);
        	g.drawString("Access: "+sel.getDefAccess(),xStart, yStart+155);
        	g.drawString("Forgery: "+sel.getDefForgery(),xStart, yStart+170);
        	g.drawString("Vulnerabilities: "+sel.getDefVulnerabilities(),xStart, yStart+185);
        	g.drawString("Redirects: "+sel.getDefRedirects(),xStart, yStart+200);
        	g.drawString("Asset value: "+sel.getValue(), xStart, yStart+215);
        //	g.drawString("Number of neighbouring nodes: "+sel.getNeighbours().size(), xStart, yStart+110);
        	
        }
	}
	
	public void paintAttackInfo(Graphics g, int yStart){
		g.setColor(Color.YELLOW);
		int xStart = 10;
		Rectangle rect=new Rectangle(5,yStart,300,220);
		AttackAgent a = s.getaAgent();
        Node sel = s.getNw().getNodeSelected();
		g.drawRoundRect(rect.x, rect.y, rect.width, rect.height, 10, 5);
		g.setColor(new Color(219,211,90));
		g.fillRoundRect(rect.x, rect.y, rect.width, rect.height, 10, 5);
		g.setColor(Color.BLACK);
		int size = a.getActionSeq().size();
		AttackState last = size>0 ? a.getActionSeq().get(a.getActionSeq().size()-1) : null;
        g.drawString((last==null ? "No attack yet" : "Last attack: on node "+last.getNode().getID() +" with type "+last.getAttackType()), xStart, yStart+15);
        g.drawString("_________________________________________", xStart, yStart+20);
        g.drawString("Current selected node: "+(sel==null?"no node selected":sel.getID()), xStart, yStart+35);
        if(sel!= null){
        	g.drawString("Injection: "+sel.getAttInjection(),xStart, yStart+65);
        	g.drawString("Authentication: "+sel.getAttAuthentication(),xStart, yStart+80);
        	g.drawString("CrossSite: "+sel.getAttCrossSite(),xStart, yStart+95);
        	g.drawString("References: "+sel.getAttReferences(),xStart, yStart+110);
        	g.drawString("Missconfiguration: "+sel.getAttMisconfiguration(),xStart, yStart+125);
        	g.drawString("Exposure: "+sel.getAttExposure(),xStart, yStart+140);
        	g.drawString("Access: "+sel.getAttAccess(),xStart, yStart+155);
        	g.drawString("Forgery: "+sel.getAttForgery(),xStart, yStart+170);
        	g.drawString("Vulnerabilities: "+sel.getAttVulnerabilities(),xStart, yStart+185);
        	g.drawString("Redirects: "+sel.getAttRedirects(),xStart, yStart+200);
        	
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
		paintAttackInfo(g,250);
    }
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		this.validate();
		this.repaint();
	}
}
