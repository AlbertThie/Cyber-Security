import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;


public class Node {


	boolean value;
	private double attInjection, attAuthentication, attCrossSite, attReferences, attMisconfiguration, attExposure, attAccess, attForgery, attVulnerabilities, attRedirects, defInjection, defAuthentication, defCrossSite, defReferences, defMisconfiguration, defExposure, defAccess, defForgery, defVulnerabilities, defRedirects, detect;
	private int xPos, yPos;
	private String ID;
	private ArrayList<Node> neighbours = new ArrayList<Node>();
	private boolean detected;
	
	public Node(String ID, boolean v, double det, int xPos, int yPos, double defVulnerabilities, double defForgery,double defExposure, double defReferences, double defCrossSite, double defAuthentication, double defInjection, double defMisconfiguration, double defRedirects, double defAccess){
		this.setID(ID);
		this.value=v;
		this.setDefInjection(defInjection);
		this.setDefAuthentication(defAuthentication);
		this.setDefCrossSite(defCrossSite);
		this.setDefReferences(defReferences);
		this.setDefMisconfiguration(defMisconfiguration);
		this.setDefExposure(defExposure);
		this.setDefAccess(defAccess);
		this.setDefForgery(defForgery);
		this.setDefVulnerabilities(defVulnerabilities);
		this.setDefRedirects(defRedirects);
		this.setAttInjection(0);
		this.setAttAuthentication(0);
		this.setAttCrossSite(0);
		this.setAttReferences(0);
		this.setAttMisconfiguration(0);
		this.setAttExposure(0);
		this.setAttAccess(0);
		this.setAttForgery(0);
		this.setAttVulnerabilities(0);
		this.setAttRedirects(0);
		this.setDetect(det);
		this.setxPos(xPos);
		this.setyPos(yPos);
		this.setDefAuthentication(defAuthentication);
		this.setDetected(false);
	}
	
	

	public Rectangle getRect(){
		return new Rectangle(getxPos(), getyPos(), 30, 30);
	}

	public ArrayList<Node> getNeighbours() {
		return neighbours;
	}

	public void addNeighbour(Node n){
		neighbours.add(n);
	}
	
	public void setNeighbours(ArrayList<Node> neighbours) {
		this.neighbours = neighbours;
	}
	

	public String getID() {
		return ID;
	}


	public void setID(String ID) {
		this.ID = ID;
	}
	
	public String drawNeighbours(){
		String s = "";
		for(Node n : neighbours){
			s+=n.getID();
		}
		return s;
	}

	public boolean getValue() {
		return value;
	}


	public void setValue(boolean value) {
		this.value = value;
	}
	
	
	public double getDetect() {
		return detect;
	}


	public void setDetect(double detect) {
		this.detect = detect;
	}
	


	public int getxPos() {
		return xPos;
	}


	public void setxPos(int xPos) {
		this.xPos = xPos;
	}


	public int getyPos(){
		return yPos;
	}


	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public double getDefInjection() {
		return defInjection;
	}

	public void setDefInjection(double defInjection) {
		this.defInjection = defInjection;
	}

	public double getDefAuthentication() {
		return defAuthentication;
	}

	public void setDefAuthentication(double defAuthentication) {
		this.defAuthentication = defAuthentication;
	}

	public double getDefCrossSite() {
		return defCrossSite;
	}

	public void setDefCrossSite(double defCrossSite) {
		this.defCrossSite = defCrossSite;
	}

	public double getDefReferences() {
		return defReferences;
	}

	public void setDefReferences(double defReferences) {
		this.defReferences = defReferences;
	}

	public double getDefMisconfiguration() {
		return defMisconfiguration;
	}

	public void setDefMisconfiguration(double defMisconfiguration) {
		this.defMisconfiguration = defMisconfiguration;
	}

	public double getDefExposure() {
		return defExposure;
	}
	

	public void setDefExposure(double defExposure) {
		this.defExposure = defExposure;
	}

	public double getDefAccess() {
		return defAccess;
	}

	public void setDefAccess(double defAccess) {
		this.defAccess = defAccess;
	}

	public double getDefForgery() {
		return defForgery;
	}

	public void setDefForgery(double defForgery) {
		this.defForgery = defForgery;
	}

	public double getDefVulnerabilities() {
		return defVulnerabilities;
	}

	public void setDefVulnerabilities(double defVulnerabilities) {
		this.defVulnerabilities = defVulnerabilities;
	}

	public double getDefRedirects() {
		return defRedirects;
	}

	public void setDefRedirects(double defRedirects) {
		this.defRedirects = defRedirects;
	}
	
	public void flag(double att, double def){
		if(att>def){
			setValue(false);
		} else {
			Random rand = new Random();
			double flagChance = rand.nextInt(100);
			double diff = def-att;
			if (this.detect * diff > flagChance){ // was <=, leek niet goed
				setDetected(true);
			}
		}
	}
	
	// DETERMINE IF ATTACKER IS CAUGHT, ASSET IS TAKEN, OR NOTHING
	public void checkAttack(String attType){
		switch(attType){
		case "Injection":
			flag(attInjection, defInjection);
			break;
		case "Authentication":
			flag(attAuthentication, defAuthentication);
			break;
		case "CrossSite":
			flag(attCrossSite, defCrossSite);
			break;
		case "References":
			flag(attReferences, defReferences);
			break;
		case "Misconfiguration":
			flag(attMisconfiguration, defMisconfiguration);
			break;
		case "Exposure":
			flag(attExposure, defExposure);
			break;
		case "Access":
			flag(attAccess, defAccess);
			break;
		case "Forgery":
			flag(attForgery, defForgery);
			break;
		case "Vulnerabilities":
			flag(attVulnerabilities, defVulnerabilities);
			break;
		case "Redirects":
			flag(attRedirects, defRedirects);
			break;
		default:
			break;
		}

	}
	public String toString(){
		return value+" "+defInjection+" "+defAuthentication+" "+defCrossSite+" "+defReferences+" "+defMisconfiguration+" "+defExposure+" "+defAccess+" "+defForgery+" "+defVulnerabilities+" "+defRedirects+" "+detect+" "+xPos+" "+yPos+" "+ID;

	}

	public boolean isDetected() {
		return detected;
	}

	public void setDetected(boolean detected) {
		this.detected = detected;
	}

	public double getAttInjection() {
		return attInjection;
	}

	public void setAttInjection(double attInjection) {
		this.attInjection = attInjection;
	}

	public double getAttAuthentication() {
		return attAuthentication;
	}

	public void setAttAuthentication(double attAuthentication) {
		this.attAuthentication = attAuthentication;
	}

	public double getAttCrossSite() {
		return attCrossSite;
	}

	public void setAttCrossSite(double attCrossSite) {
		this.attCrossSite = attCrossSite;
	}

	public double getAttReferences() {
		return attReferences;
	}

	public void setAttReferences(double attReferences) {
		this.attReferences = attReferences;
	}

	public double getAttMisconfiguration() {
		return attMisconfiguration;
	}

	public void setAttMisconfiguration(double attMisconfiguration) {
		this.attMisconfiguration = attMisconfiguration;
	}

	public double getAttExposure() {
		return attExposure;
	}

	public void setAttExposure(double attExposure) {
		this.attExposure = attExposure;
	}

	public double getAttAccess() {
		return attAccess;
	}

	public void setAttAccess(double attAccess) {
		this.attAccess = attAccess;
	}

	public double getAttForgery() {
		return attForgery;
	}

	public void setAttForgery(double attForgery) {
		this.attForgery = attForgery;
	}

	public double getAttVulnerabilities() {
		return attVulnerabilities;
	}

	public void setAttVulnerabilities(double attVulnerabilities) {
		this.attVulnerabilities = attVulnerabilities;
	}

	public double getAttRedirects() {
		return attRedirects;
	}

	public void setAttRedirects(double attRedirects) {
		this.attRedirects = attRedirects;
	}

}