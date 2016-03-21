import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class GraphSelector implements Observer {

	private Simulation s;
	
	private final int f =1;
	public GraphSelector(Simulation s){
		setS(s);
		final JFrame gSelector = new JFrame("Welcome!");
	    gSelector.setSize(400,200);
	    gSelector.setLocationRelativeTo(null);
		gSelector.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		gSelector.setVisible(true);
		
		JPanel panel = new JPanel();
		gSelector.add(panel);
		JButton graph1 = new JButton("Graph 1");
		panel.add(graph1);
		
		graph1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				makeGraph(null, null, "Graph1");
			}
		}); 
		
		JButton graph2 = new JButton("Graph2");
		panel.add(graph2);
		graph2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				makeGraph(null, null, "Graph2");
			}
		});    
	}
	
	public void makeGraph(ArrayList<Double> data1, ArrayList<Double> data2, String title){
		List<Double> scores = new ArrayList<>();
        Random random = new Random();
        int maxDataPoints = 7;
        int maxScore = 10;
        for (int i = 0; i < maxDataPoints; i++) {
            scores.add((double) random.nextDouble() * maxScore);
        }
        List<Double> scores2 = new ArrayList<>();
        for (int i = 0; i < maxDataPoints; i++) {
            scores2.add((double) random.nextDouble() * maxScore);
        }
        GraphMaker mainPanel = new GraphMaker(scores, scores2);
        mainPanel.setPreferredSize(new Dimension(800, 600));
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	public Simulation getS() {
		return s;
	}

	public void setS(Simulation s) {
		this.s = s;
	}

}
