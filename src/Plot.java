import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;

public class Plot extends JLabel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	List<Point> pnts = new ArrayList<Point>();
	
	int x,h=400,w=100;
	

	public Plot() {
		super();
		this.setPreferredSize(new Dimension(h,w));
		this.setBackground(Color.BLUE);
		this.setForeground(Color.BLUE);
		Point tmp;
		for(int i=0;i<2000;i++){
			tmp = new Point();
			tmp.x = 0; tmp.y=0;
			pnts.add(tmp);
		}
		x = 10;
	}
	
	public void newp(int val){
		w = getWidth();
		h = getHeight();
		val = val*h/65400;
		if (x>=w-10){
			x = 10;
			this.repaint();
			//synchronized(pnts){pnts.clear();}
		}
		int y = h/2;
		Point cur = new Point();
		cur.x = x; cur.y = y+val;
		//synchronized(pnts){pnts.add(cur);}
		synchronized(pnts){pnts.get(x).x=cur.x;pnts.get(x).y=cur.y;}
		x = x+1;
		//if(x>44100) done = true;
		//this.repaint();
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(new Color(23,232,61));
		g.drawLine(0, h/2, w, h/2);
		Point cur=null,prv=new Point();
		synchronized(pnts){
			g.setColor(new Color(54,80,230));
			for(int i=10;i<pnts.size();i++){
					cur = pnts.get(i);
					if (Math.abs(prv.x-cur.x)>1) prv = cur;
					g.drawLine(prv.x,prv.y,cur.x,cur.y);
					prv = cur;
			}
		}
	}
}
	
	


	

	
	
	
	
