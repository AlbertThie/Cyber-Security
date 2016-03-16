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

import javax.swing.AbstractAction;
import javax.swing.Action;
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
	static InfoPanel iPanel;
	static NodePanel nPanel;
	static Simulation model;
	
	public MainFrame(String aStrat, String dStrat){
		model = new Simulation();
        model.getaAgent().setStrategy(aStrat);
		model.getdAgent().setStrategy(dStrat);
	    
	    nPanel = new NodePanel(model, this);
		nPanel.setBackground(Color.GRAY);
	    nPanel.setBounds(0, 0, 700, 768);

	    iPanel = new InfoPanel(model, this);
		iPanel.setBackground(Color.BLUE);
		iPanel.setBounds(701, 0, 500, 768);
		
		setLayout(null);
	    this.add(nPanel);
	    this.add(iPanel);

	    model.addObserver(iPanel);
	    model.addObserver(nPanel);
	    
		setTitle("Cyber Security Simulation");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(1000,768);
	    setLocationRelativeTo(null);
	    setResizable(false);
	    JMenuBar menuBar = new JMenuBar();
	    setJMenuBar(menuBar);
	    
	    // Define and add three drop down menus to the menu bar
	    JMenu fileMenu = new JMenu("File");
	    JMenu editMenu = new JMenu("Edit");
	    //JMenu viewMenu = new JMenu("View");
	    menuBar.add(fileMenu);
	    menuBar.add(editMenu);
	    //menuBar.add(viewMenu);
	    
	    //JMenuItem loadAction = new JMenuItem("Load");
        JMenuItem exitAction = new JMenuItem("Exit");
        JMenuItem addNodeAction = new JMenuItem("Add node");
        //JMenuItem changeLangAction = new JMenuItem("Change Language");
        //JMenuItem rulesAction = new JMenuItem("Rules");
        
        fileMenu.add(exitAction);
        //fileMenu.add(exitAction);
        editMenu.add(addNodeAction);
        //viewMenu.add(rulesAction);
        //editMenu.add(changeLangAction);strategy
        
        //exitAction.setAction(exitActiones);
        exitAction.setAccelerator(KeyStroke.getKeyStroke("control Q"));
        
        //rulesAction.setAction(rulesActiones);
        //rulesAction.setAccelerator(KeyStroke.getKeyStroke("control R"));
        
        addNodeAction.setAction(addNodeActiones);
        addNodeAction.setAccelerator(KeyStroke.getKeyStroke("control shift n"));
        
        //changeLangAction.setAction(languageActiones);
        //changeLangAction.setAccelerator(KeyStroke.getKeyStroke("control L"));
	    setVisible(true);
        
       
	}
	
	private Action addNodeActiones = new AbstractAction("Add a node") {
        public void actionPerformed(ActionEvent e) {
        	System.out.println("NODE ADDED");
            Node node = new Node("new_node",0,0,0,100,100);
            model.getNw().addNode(node);
            model.noti();
        }
    };
    
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
		StartWindow sw = new StartWindow(2);
		while(!sw.isAnswered()){
			System.out.print("");
		}
    	
	    MainFrame frame = new MainFrame(sw.getaStrat(), sw.getdStrat());
	    
      
    }
}
