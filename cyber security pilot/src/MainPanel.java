import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;


public class MainPanel extends JPanel implements Observer {

	private MainFrame frame;
	private NodePanel nPanel;
	private InfoPanel iPanel;
	private Simulation s;
	
	public MainPanel(Simulation s, MainFrame mf){
		this.setLayout(new java.awt.BorderLayout());
		setFrame(mf);
		//nPanel = new NodePanel(mf);
		nPanel.setBounds(0, 0, 250, 100);
		add(nPanel, BorderLayout.WEST);
		//iPanel = new InfoPanel(mf);
		add(iPanel, BorderLayout.EAST);
		mf.getContentPane();
	}
	
	

	public MainFrame getFrame() {
		return frame;
	}

	public void setFrame(MainFrame frame) {
		this.frame = frame;
	}
	
	/*public void drawScoreRect(Graphics g){
		Languages l = game.getL(); 
		Rectangle rect=new Rectangle(700,24,300,180);
		g.drawRoundRect(rect.x, rect.y, rect.width, rect.height, 10, 5);
		g.setColor(new Color(219,211,90));
		g.fillRoundRect(rect.x, rect.y, rect.width, rect.height, 10, 5);
		g.setColor(Color.BLACK);
        g.drawString(l.getLine("eng", 2), 710, 50);
        g.drawString("_________________________________________", 710, 55);
        g.drawString(l.getLine("eng", 3), 710, 75);
        g.drawString(game.getPerson(1).getName()+" :"+game.getPerson(1).getPoints(), 710, 95);
        g.drawString("Jelle :"+game.getPerson(2).getPoints(), 710, 115);
        g.drawString("Cor :"+game.getPerson(3).getPoints(), 710, 135);
        g.drawString("Henry :"+game.getPerson(4).getPoints(), 710, 155);
        g.drawString("_________________________________________", 710, 165);
        g.drawString(this.game.getPerson(1).getMessage(), 710, 190);
	}*/

	public void paintComponent(Graphics g){
        super.paintComponent(g);
		nPanel.paintComponent(g);
		iPanel.paintComponent(g);
    }
	
	@Override
	public void update(Observable arg0, Object arg1) {
        this.validate();
        this.repaint();
    }

	public NodePanel getnPanel() {
		return nPanel;
	}

	public void setnPanel(NodePanel nPanel) {
		this.nPanel = nPanel;
	}

	public Simulation getS() {
		return s;
	}

	public void setS(Simulation s) {
		this.s = s;
	}
}
