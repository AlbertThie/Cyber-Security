import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;


public class Maker {

	private Network network = new Network();
	
	public Maker(){
		
	}
	
	private Action loadActiones = new AbstractAction("Load") {
		public void actionPerformed(ActionEvent e) {
			try {
				JFileChooser fc = new JFileChooser();
		       	fc.showOpenDialog(null);
		       	String p = fc.getSelectedFile().getAbsolutePath();
		       	network.clearNetwork();
		       	load(p);
		       	System.out.println("Loaded");
		    } catch (Exception e1) {
		       	System.out.println("Loading failed");
		        e1.printStackTrace();
		    }
		}
	};
	
	public void load(String str){
		Scanner s = null;
		try{
			s = new Scanner(new FileInputStream(str));
		}
		catch (FileNotFoundException e){
			System.out.println("File not found");
			System.exit(0);
		}
		int numNodes = 0;
		if(s.hasNextLine()){
			numNodes = s.nextInt();
		} else {
			s.close();
		}
		int a=0;
		while(s.hasNextLine() && a++<numNodes){
			int x;
			String n;
			n=s.nextLine();
			x=s.nextInt();
			Node node = new Node(n,x);
			network.getNodes().add(node);
		}
		
		a=0;
		int numConnections = 0;
		if(s.hasNextLine()){
			numConnections = s.nextInt();
		} else {
			s.close();
		}
		while(s.hasNextLine() && a++<numConnections){
			String v1, v2;
			v1=s.nextLine();
			v2=s.nextLine();
			s.nextLine();
			Node n1 = network.getNode(v1);
			Node n2 = network.getNode(v2);
			n1.addNeighbour(n2);
			n2.addNeighbour(n1);
		}
		s.close();
	}

	public Network getN() {
		return network;
	}

	public void setN(Network n) {
		this.network = n;
	}
}
