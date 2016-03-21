import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class StartWindow {

	private String aStrat;
	private String dStrat;
	private Boolean vis;
	private boolean answered;
	
	public StartWindow(int numStrat){
		setAnswered(false);
		final JFrame setup = new JFrame("Welcome!");
	    setup.setSize(400,200);
	    setup.setLocationRelativeTo(null);
		setup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel body = new JPanel();
		
		body.setLayout(new GridLayout(6, 2));
		JLabel defender = new JLabel("Strategy for the defender:");
		JLabel attacker = new JLabel("Strategy for the attacker:");
		DefaultComboBoxModel<String> strat1 = new DefaultComboBoxModel<String>();
		DefaultComboBoxModel<String> strat2 = new DefaultComboBoxModel<String>();
		String values[] = {"Random","Strategy 2", "Strategy 3"};
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
		body.add(ok);
		ok.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setup.dispose();
					setdStrat((String)s1.getSelectedItem());
					setaStrat((String)s2.getSelectedItem());
					setVis(check.isSelected());
					setAnswered(true);
				}
			}
		);
		setup.add(body);
	    setup.setVisible(true);
	}
	
	public Boolean getVis(){
		return vis;
	}
	
	public void setVis(Boolean b){
		vis=b;
	}

	public String getaStrat() {
		return aStrat;
	}

	public void setaStrat(String aStrat) {
		this.aStrat = aStrat;
	}

	public String getdStrat() {
		return dStrat;
	}

	public void setdStrat(String dStrat) {
		this.dStrat = dStrat;
	}

	public boolean isAnswered() {
		return answered;
	}

	public void setAnswered(boolean answered) {
		this.answered = answered;
	}
	
}
