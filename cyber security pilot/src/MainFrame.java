import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;


public class MainFrame extends JFrame {

	// Global scape variables
	static int xSize = 500;
	static int ySize = 500;
	static int epochs = 0;
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
	static Panel panel;
	static Simulation model;
	
	public MainFrame(String aStrat, String dStrat){
		setTitle("Cyber Security Simulation");
	    setSize(1024,768);
	    setResizable(false);
	    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	    JMenuBar menuBar = new JMenuBar();
	    setJMenuBar(menuBar);
	    
	    // Define and add three drop down menus to the menu bar
	    JMenu fileMenu = new JMenu("File");
	    //JMenu editMenu = new JMenu("Edit");
	    //JMenu viewMenu = new JMenu("View");
	    menuBar.add(fileMenu);
	    //menuBar.add(editMenu);
	    //menuBar.add(viewMenu);
	    
	    JMenuItem loadAction = new JMenuItem("Load");
        JMenuItem exitAction = new JMenuItem("Exit");
        //JMenuItem changeNameAction = new JMenuItem("Change Name");
        //JMenuItem changeLangAction = new JMenuItem("Change Language");
        //JMenuItem rulesAction = new JMenuItem("Rules");
        
        fileMenu.add(exitAction);
        fileMenu.add(exitAction);
        //editMenu.add(changeNameAction);
        //viewMenu.add(rulesAction);
        //editMenu.add(changeLangAction);strategy
        
        //exitAction.setAction(exitActiones);
        exitAction.setAccelerator(KeyStroke.getKeyStroke("control Q"));
        
        //rulesAction.setAction(rulesActiones);
        //rulesAction.setAccelerator(KeyStroke.getKeyStroke("control R"));
        
        //changeNameAction.setAction(changeNameActiones);
        //changeNameAction.setAccelerator(KeyStroke.getKeyStroke("control R"));
        
        //changeLangAction.setAction(languageActiones);
        //changeLangAction.setAccelerator(KeyStroke.getKeyStroke("control L"));
        
        model = new Simulation();
        model.getaAgent().setStrategy(aStrat);
		model.getdAgent().setStrategy(dStrat);
	    panel = new Panel(model, this);
	    panel.setBackground(new Color(15,97,20));
	    this.add(panel);
		
		
		
		////////////////////////////////////////
		
		// Contents of the setup frame
		
		
		/*pane.add(create, BorderLayout.NORTH);
		pane.add(body, BorderLayout.CENTER);
		pane.add(ok, BorderLayout.SOUTH);
		
		setup.pack();
		setup.setLocationRelativeTo(null);
		setup.setSize(300, 300);
		JMenuBar menuBar = new JMenuBar();
	    setup.setJMenuBar(menuBar);
	    setup.add(mainPanel);
        setup.setVisible(true);*/
	}
	
	/*public void startWindow(){
		final JFrame setup = new JFrame("Welcome!");
		setup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel body = new JPanel();
		body.setLayout(new GridLayout(6, 2));
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
		model.setaAgent(aAgent);
		model.setdAgent(dAgent);
	}*/
	
	public static void startSimulation() {
		epochs = 0;
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
		StartWindow sw = new StartWindow();
		MainFrame f = new MainFrame(sw.getaStrat(), sw.getdStrat());
	    //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.setVisible(true);
        System.exit(0);
    }
}
