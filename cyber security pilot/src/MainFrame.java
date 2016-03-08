import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class MainFrame {

	// Global scape variables
	static int xSize = 500;
	static int ySize = 500;
	static int epochs = 0;
	static AttackAgent aAgent = new AttackAgent();
	static DefenderAgent dAgent = new DefenderAgent();
	static Random gen = new Random();

	// Strategy variables
	static String[] strategies = {
		"ALL-D",
		"TIT-FOR-TAT",
		"GRUMPY",
		"JOSS",
		"TRUST",
		"TWISTER"
	};
	
	static int numStrategies = strategies.length;
	
	// Visualization variables
	static JFrame frame;
	static MainFrame mainFrame;
	static MainPanel mainPanel;
	
	public MainFrame(){
	
	}
	
	public static void setup() {
		// Construct the setup frame
		final JFrame setup = new JFrame("Welcome!");
		setup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JComponent pane = (JComponent)setup.getContentPane();
		pane.setLayout(new BorderLayout());
		pane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		JLabel create = new JLabel ("Create your dilemma", SwingConstants.LEFT);
		create.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		
		JPanel body = new JPanel();
		body.setLayout(new GridLayout(6, 2));
		
		// Contents of the setup frame
		JLabel defender = new JLabel("Strategy for the defender:");
		JLabel attacker = new JLabel("Strategy for the attacker:");
		final JTextField[] getStrat = new JTextField[numStrategies];
		String content = "";
		DefaultComboBoxModel<String> strat1 = new DefaultComboBoxModel<String>();
		DefaultComboBoxModel<String> strat2 = new DefaultComboBoxModel<String>();
		String values[] = {"Strategy 1","Strategy 2", "Strategy 3"};
		for(String value : values){
			strat1.addElement(value);
			strat2.addElement(value);
		}
		final JComboBox s1 = new JComboBox(strat1);
		final JComboBox s2 = new JComboBox(strat2);
		
		final JCheckBox check = new JCheckBox("Visualisation");
		// Add all the contents to the frame
		body.add(defender);
		body.add(s1);
		body.add(attacker);
		body.add(s2);
		body.add(check);
				
		// The ok button is defined here. It ll extract all the information
		// from the content and it will continue to the simulation
		JButton ok = new JButton("Ok");	
		ok.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setup.dispose();
					dAgent.setStrategy((String)s2.getSelectedItem());
					aAgent.setStrategy((String)s2.getSelectedItem());
					Boolean vis = check.isSelected();
					System.out.println("Visual: "+vis);
					startSimulation();
				}
			}
		);
		
		pane.add(create, BorderLayout.NORTH);
		pane.add(body, BorderLayout.CENTER);
		pane.add(ok, BorderLayout.SOUTH);
		
		setup.pack();
		setup.setLocationRelativeTo(null);
		setup.setSize(300, 300);
        setup.setVisible(true);
	}
	
	public static void startSimulation() {
		epochs = 0;
	
		MainFrame controller = new MainFrame();
		createAndShowGUI(controller);
	}
		
	// Function involved in showing the simulation.
    private static void createAndShowGUI(MainFrame controller) {
        //Create and set up the window.
        frame = new JFrame("Scape");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setSize(xSize, ySize);
        
        //Set up the content pane.
        //controller.buildUI(frame.getContentPane());

        //Display the window.
        //frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    // Function involved in showing the simulation.
    private void buildUI(Container pane) {
        pane.setLayout(new FlowLayout());
        mainPanel = new MainPanel(this);
        pane.add(mainPanel);
    }
    
    private static int getValue(String s) {
    	if(s.equals("")) {
    		return 0;
    	}
    	else {
    		Integer num = new Integer(s);
    		return num.intValue();
    	}
    }
	
	public static void main(String args[]){
		setup();
	}
}
