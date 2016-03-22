import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;


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
		iPanel.setBounds(700, 0, 300, 768);
		
		setLayout(null);
	    this.add(nPanel);
	    this.add(iPanel);

	    model.addObserver(iPanel);
	    model.addObserver(nPanel);


	    final JTextField numFW = new JTextField(10);
	    numFW.setText("1");
	    numFW.setBounds(240, 500, 50, 30);
	    
	    JButton graphs = new JButton("graph");
	    graphs.setBounds(5, 500, 70, 30);
	    graphs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GraphSelector gs = new GraphSelector(model);
			}
		});
	    
	    JButton stepper = new JButton("step");
	    stepper.setBounds(80, 500, 70, 30);
	    stepper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.modelStep();
				model.noti();
				System.out.println("ONE STEP FOR SIM ..");
			}
		});
	    
	    JButton forward = new JButton("forward");
	    forward.setBounds(155, 500, 80, 30);
	    forward.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		if(isInteger(numFW.getText())){
	    			for(int i=0;i<Double.parseDouble(numFW.getText());i++){
	    				model.modelStep();
	    			}
	    			model.noti();
	    			System.out.println("forwarding "+numFW.getText()+" times");
	    		} else {
	    			System.out.println("INvalid number");
	    		}
	    	}
	    });
	    
	    iPanel.setLayout(null);
	    iPanel.add(stepper);
	    iPanel.add(graphs);
	    iPanel.add(forward);
	    iPanel.add(numFW);
	    
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
	    
	    JMenuItem loadAction = new JMenuItem("Load");
	    JMenuItem saveAction = new JMenuItem("Save");
        //JMenuItem exitAction = new JMenuItem("Exit");
        JMenuItem addNodeAction = new JMenuItem("Add node");
        JMenuItem changeNameAction = new JMenuItem("Change Name");
        JMenuItem connectAction = new JMenuItem("Connect two nodes");
        //JMenuItem rulesAction = new JMenuItem("Rules");
        
        fileMenu.add(loadAction);
        fileMenu.add(saveAction);
        //fileMenu.add(exitAction);
        editMenu.add(addNodeAction);
        editMenu.add(connectAction);
        //viewMenu.add(rulesAction);
        editMenu.add(changeNameAction);
        
        
        loadAction.setAction(loadActiones);
        
        saveAction.setAction(saveActiones);
        //exitAction.setAction(exitActiones);
        //exitAction.setAccelerator(KeyStroke.getKeyStroke("control Q"));
        
        //rulesAction.setAction(rulesActiones);
        //rulesAction.setAccelerator(KeyStroke.getKeyStroke("control R"));
        
        addNodeAction.setAction(addNodeActiones);
        addNodeAction.setAccelerator(KeyStroke.getKeyStroke("control shift n"));
        
        connectAction.setAction(connectActiones);
        connectAction.setAccelerator(KeyStroke.getKeyStroke("control shift k"));
        
        changeNameAction.setAction(changeNameActiones);
        //changeLangAction.setAccelerator(KeyStroke.getKeyStroke("control L"));
	    setVisible(true);
	}
	
	public void enableButtons(){
        this.changeNameActiones.setEnabled(model.getNw().getNodeSelected()!=null);
        this.connectActiones.setEnabled(model.getNw().getNodes().size()>1);
	}
	
	private Action loadActiones = new AbstractAction("Load") {
		public void actionPerformed(ActionEvent e) {
			try {
				JFileChooser fc = new JFileChooser();
		       	fc.showOpenDialog(null);
		       	String p = fc.getSelectedFile().getAbsolutePath();
		       	Maker m = new Maker();
		       	m.load(p);
		       	model.setNw(m.getN());
		        model.noti();
		       	infoMessage("Network loaded");
		    } catch (Exception e1) {
		       	errorMessage("Loading network failed");
		        e1.printStackTrace();
		    }
		}
	};
	
	private Action saveActiones = new AbstractAction("Save") {
		public void actionPerformed(ActionEvent e) {
			try {
				JFileChooser fc = new JFileChooser();
				int k = fc.showSaveDialog(null);
				if (k == JFileChooser.APPROVE_OPTION){
					try {
						Maker m = new Maker();
						m.setN(model.getNw());
						System.out.println("Number of nodes:"+model.getNw().getNodes().size());
						System.out.println("Number of cons:"+model.getNw().getConnections().size());
						m.save(fc.getSelectedFile()+".txt", model.getNw());
					} catch (Exception e1) {
						errorMessage("Saving failed");
						e1.printStackTrace();
					}
				}
			} catch (Exception e1) {
				errorMessage("Saving failed");
				e1.printStackTrace();
			}
		}
	};
	private Action addNodeActiones = new AbstractAction("Add a node") {
        public void actionPerformed(ActionEvent e) {
        	try {
	    		final JFrame setup = new JFrame("Welcome!");
	    	    setup.setSize(400,200);
	    	    setup.setLocationRelativeTo(null);
	    		setup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    		
	    		JPanel body = new JPanel();
	    		body.setLayout(new GridLayout(6, 2));
	    		
	    		JLabel ID = new JLabel("Name of the node:");
	    		final JTextField id_txt = new JTextField(10);
	    		JLabel ass_val = new JLabel("Asset value:");
	    		final JFormattedTextField ass_txt = new JFormattedTextField();
	    		JLabel def_val = new JLabel("Defensive value:");
	    		final JFormattedTextField def_txt = new JFormattedTextField();
	    		JLabel det_val = new JLabel("Detection value:");
	    		final JFormattedTextField det_txt = new JFormattedTextField();
	    		
	    		// Add all the contents to the frame
	    		body.add(ID);
	    		body.add(id_txt);
	    		body.add(ass_val);
	    		body.add(ass_txt);
	    		body.add(def_val);
	    		body.add(def_txt);
	    		body.add(det_val);
	    		body.add(det_txt);
	    		
	    		JButton ok = new JButton("Ok");	
	    		body.add(ok);
	    		ok.addActionListener(new ActionListener() {
	    				public void actionPerformed(ActionEvent e) {
	    					if(id_txt.getText().isEmpty() || ass_txt.getText().isEmpty() || def_txt.getText().isEmpty() || det_txt.getText().isEmpty() ){
	    						infoMessage("Not everything is filled in");
	    					} else {
	    						if(model.getNw().getNode(id_txt.getText())==null){
		    						if(isNumeric(ass_txt.getText()) && isNumeric(def_txt.getText()) && isNumeric(det_txt.getText())){
		    							setup.dispose();
			    						model.getNw().addNode(
			    								new Node(id_txt.getText(), 
			    										 Double.parseDouble(ass_txt.getText()), 
			    										 Double.parseDouble(def_txt.getText()), 
			    										 30, 30,
			    										 Double.parseDouble(det_txt.getText()),Double.parseDouble(det_txt.getText()),Double.parseDouble(det_txt.getText()),Double.parseDouble(det_txt.getText()),Double.parseDouble(det_txt.getText()),Double.parseDouble(det_txt.getText()),Double.parseDouble(det_txt.getText()),Double.parseDouble(det_txt.getText()),Double.parseDouble(det_txt.getText()),Double.parseDouble(det_txt.getText())));
			    						model.noti();
		    						} else {
		    							infoMessage("Not a number");
		    						}
	    						} else {
	    							errorMessage("There is already a node with this name.");
	    						}
	    					}
	    				}
	    			}
	    		);
	    		setup.add(body);
	    	    setup.setVisible(true);
	        } catch (Exception e1){
	        	errorMessage("Adding a node failed");
	        }
        }
	};
    
    private Action changeNameActiones = new AbstractAction("Change Name") {
    	/**
    	 * Try to pop-up a window in which the user can type the name he wants,
    	 * and assign that name to the user in the game.
    	 * @exception If something with changing the name goes wrong.
    	 */
        public void actionPerformed(ActionEvent e) {
            try{
                String[] options = {"OK"};
                JPanel panel = new JPanel();
                JLabel lbl = new JLabel("Enter a new name: ");
                JTextField txt = new JTextField(10);
                txt.setText(model.getNw().getNodeSelected().getID());
                panel.add(lbl);
                panel.add(txt);
                panel.requestFocusInWindow();
                int selectedOption = JOptionPane.showOptionDialog(null, panel, "Change name", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options , txt);
                if(selectedOption == 0){
                	String name = txt.getText();
                	if(model.getNw().getNode(name)==null){
                		model.getNw().getNodeSelected().setID(name);
                	} else {
                		errorMessage("There is already a node with this name");
                	}
                }
             } catch (Exception e1) {
                errorMessage("Changing the name failed");
             }
            model.noti();
        }
    };
    private Action connectActiones = new AbstractAction("Connect two nodes") {
    	/**
    	 * Try to pop-up a window in which the user can type the name he wants,
    	 * and assign that name to the user in the game.
    	 * @exception If something with changing the name goes wrong.
    	 */
        public void actionPerformed(ActionEvent e) {
            try{
        		final JFrame setup = new JFrame("Select two nodes to connect them");
        	    setup.setSize(400,200);
        	    setup.setLocationRelativeTo(null);
        		setup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        		JPanel body = new JPanel();
        		
        		body.setLayout(new GridLayout(6, 2));
        		JLabel defender = new JLabel("First node:");
        		JLabel attacker = new JLabel("Second node:");
        		DefaultComboBoxModel<String> node1 = new DefaultComboBoxModel<String>();
        		DefaultComboBoxModel<String> node2 = new DefaultComboBoxModel<String>();
        		ArrayList<Node> nodes = model.getNw().getNodes();
        		for(Node n : nodes){
        			node1.addElement(n.getID());
        			node2.addElement(n.getID());
        		}
        		final JComboBox s1 = new JComboBox(node1);
        		final JComboBox s2 = new JComboBox(node2);
        		
        		// Add all the contents to the frame
        		body.add(defender);
        		body.add(s1);
        		body.add(attacker);
        		body.add(s2);
        		
        		// The ok button is defined here. It ll extract all the information
        		// from the content and it will continue to the simulation
        		JButton ok = new JButton("Ok");	
        		body.add(ok);
        		ok.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent e) {
        					setup.dispose();
        					Node n1 = model.getNw().getNode((String)s1.getSelectedItem());
        					Node n2 = model.getNw().getNode((String)s2.getSelectedItem());
        					if(n1.equals(n2)){
        						errorMessage("A node can not be connected with itself.");
        					} else {
        						if(n1.getNeighbours().contains(n2)){
        							errorMessage("These nodes are already connected.");
        						} else {
		        					n1.addNeighbour(n2);
		        					n2.addNeighbour(n1);
		        					model.getNw().makeConnection(n1,n2);
		        					model.noti();
        						}
        					}
        				}
        			}
        		);
        		setup.add(body);
        	    setup.setVisible(true);
             } catch (Exception e1) {
                errorMessage("Connecting failed");
             }
            model.noti();
        }
    };
	
	public static void startSimulation() {
		epochs = 0;
	}
    
	public void errorMessage(String error){
        String[] options = {"OK"};
        JPanel panel = new JPanel();
        JLabel lbl = new JLabel(error);
        panel.add(lbl);
        JOptionPane.showOptionDialog(null, panel, "Error", JOptionPane.NO_OPTION, JOptionPane.ERROR_MESSAGE, null, options , "OK");
    }
	
	public void infoMessage(String error){
        String[] options = {"OK"};
        JPanel panel = new JPanel();
        JLabel lbl = new JLabel(error);
        panel.add(lbl);
        JOptionPane.showOptionDialog(null, panel, "Info message", JOptionPane.NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options , "OK");
    }

    
    public static boolean isNumeric(String str) {  
    	return str.matches("-?\\d+(\\.\\d+)?");  
    }
    
    public static boolean isInteger(String str) { 
    	try{
           Integer.parseInt(str);
           return true;
        }
        catch (NumberFormatException ex)
        {
           return false;
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
