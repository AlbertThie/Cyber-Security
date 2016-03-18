import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;


public class Maker {

	private Network network;
	
	public Maker(){
		network = new Network();
	}
	
	public void load(String str){
		Scanner s = null;
		try{
			s = new Scanner(new FileInputStream(str));
		}
		catch (FileNotFoundException e){
			System.out.println("File not found");
			System.exit(0);
		}
		int numNodes = 0, numCons = 0;
		if(s.hasNextLine()){
			numNodes = s.nextInt();
			numCons = s.nextInt();
		} else {
			s.close();
		}
		int a=0;
		while(s.hasNextLine() && a++<numNodes){
			double x,y,z;
			int w,v;
			String n;
			x=s.nextDouble();
			y=s.nextDouble(); 
			z=s.nextDouble();
			w=s.nextInt();
			v=s.nextInt();
			s.skip(" ");
			n= s.nextLine();
			Node node = new Node(n,x,y,z,w,v);
			network.getNodes().add(node);
		}
		a=0;
		while(s.hasNextLine() && a++<numCons){
			String v1, v2;
			v1=s.nextLine();
			v2=s.nextLine();
			s.nextLine();
			Node n1 = network.getNode(v1);
			Node n2 = network.getNode(v2);
			n1.addNeighbour(n2);
			n2.addNeighbour(n1);
			network.makeConnection(n1, n2);
		}
		s.close();
	}

	public void save(String str, Network nw){
		PrintWriter o = null;
		try{
			o = new PrintWriter(new FileOutputStream(str));
		}
		catch (FileNotFoundException e){
			System.out.println("Error saving the .txt file: file not found.");
			System.exit(0);
		}
		ArrayList<Node> nodes = network.getNodes();
		int cons = network.getConnections().size();
		o.println(nodes.size()+" "+cons);
		int a=0;
		while(a<nodes.size()){
			Node n = nodes.get(a);
			o.println(n.toString());
			a++;
		}
		a=0;
		while(a<cons){
			Node n1 = network.getConnections().get(a).n1;
			Node n2 = network.getConnections().get(a).n2;
			o.println(n1.getID());
			o.println(n2.getID());
			o.println();
			a++;
		}
		o.close();
	}

	public Network getN() {
		return network;
	}

	public void setN(Network n) {
		this.network = n;
	}
}
