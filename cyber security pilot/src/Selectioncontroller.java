import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class Selectioncontroller implements MouseListener, MouseMotionListener {

	int xSel=0, ySel=0;
	private Simulation s;
	
	public Selectioncontroller(Simulation s){
		setS(s);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		Network nw = s.getNw();
		if(nw.getNodeSelected()!=null){
            xSel=e.getX();
            ySel=e.getY();
            Node n = nw.getNodeSelected();
            Rectangle r=n.getRect();
            n.setxPos(xSel-r.width/2);
            n.setyPos(ySel-r.height/2);
            //gm.setRect(g, new Rectangle(xSel-r.width/2, ySel-r.height/2, r.width, r.height));
            
        }
		s.noti();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
        xSel=e.getX();
        ySel=e.getY();
        Network nw = s.getNw();
        int b=0;
        boolean f=false;
        //System.out.println("Clicked on "+xSel+" "+ySel);
        while(b<nw.getNodes().size() && !f){
            Node n = nw.getNodes().get(b);
            Rectangle r = n.getRect();
            if (r.x<=xSel && xSel<=(r.x+r.width) && r.y<=ySel && ySel<=(r.y+r.height)){
            	n.setxPos(xSel-r.width/2);
            	n.setyPos(ySel-r.height/2);
                //gm.setRect(nw.getNodes().get(b), new Rectangle(xSel-r.width/2, ySel-r.height/2, r.width, r.height));
                nw.setNodeSelected(n);
                f=true;
            }
            b++;
        }
        if(!f){ // Press event was not on a node
            if(nw.getNodeSelected()!=null){ // some node was selected
                nw.setNodeSelected(null);
            }
        }
        s.noti();
    }

	@Override
	public void mouseReleased(MouseEvent e) {
		int b=0;
        boolean f=false;
        Network nw = s.getNw();
        if(xSel==e.getX() && ySel==e.getY()){
            f=true;
        }
        while(b<nw.getNodes().size() && !f){
        	Node n = nw.getNodes().get(b);
            Rectangle r = n.getRect();
            if (r.x<=xSel && xSel<=(r.x+r.width) && r.y<=ySel && ySel<=(r.y+r.height)){
            	n.setxPos(xSel-r.width/2);
            	n.setyPos(ySel-r.height/2);
                //gm.setRect(gm.verts.get(b), new Rectangle(e.getX()-r.width/2, e.getY()-r.height/2, r.width, r.height));
                f=true;
            }
            b++;
        }
        s.noti();
	}
	public Simulation getS() {
		return s;
	}
	public void setS(Simulation s) {
		this.s = s;
	}

}
