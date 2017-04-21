package vectorTo2D;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class Coordinatesystem extends JPanel {
	int angleX1, angleX2, angleX3, offsetX, offsetY;
	double scale;
	LinkedList<Vector3Di> points;
	LinkedList<Plane> planes;
	LinkedList<Line> lines;
	
	public Coordinatesystem() {
		this.setLayout(null);
		this.setBackground(Color.black);
		this.setOpaque(true);
		
		angleX1 = 0;
		angleX2 = -45;
		angleX3 = -45;
		
		scale = 1.0;
		
		points = new LinkedList<Vector3Di>();
		planes = new LinkedList<Plane>();
		lines = new LinkedList<Line>();
		
		lines.add(new Line(new Vector3Di(0, 0, 0), new Vector3Di(100, 100, 100)));
		// planes.add(new Plane(new Vector3Di(150, 50, 100), new Vector3Di(200, 0, 10), new Vector3Di(-50, 150, 200)));
	}
	
	public void paintComponent(Graphics g) {
		double now = System.nanoTime();
		
		super.paintComponent(g);
		
		offsetX = getWidth() / 2;
		offsetY = getHeight() / 2;
		
		Graphics2D g2D = (Graphics2D) g.create();
		// g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		Canvas c = new Canvas(new Dimension(getWidth(), getHeight()), Color.black);
		
		/*
		g2D.setColor(Color.red);
		g2D.drawLine((int) (transform(new Vector3Di(-200, 0, 0)).getX() * scale + offsetX), (int) (transform(new Vector3Di(-200, 0, 0)).getY() * scale + offsetY), (int) (transform(new Vector3Di(200, 0, 0)).getX() * scale + offsetX), (int) (transform(new Vector3Di(200, 0, 0)).getY() * scale + offsetY)); // x1
		g2D.drawLine((int) (transform(new Vector3Di(180, 10, 0)).getX() * scale + offsetX), (int) (transform(new Vector3Di(180, 10, 0)).getY() * scale + offsetY), (int) (transform(new Vector3Di(200, 0, 0)).getX() * scale + offsetX), (int) (transform(new Vector3Di(200, 0, 0)).getY() * scale + offsetY));
		g2D.drawLine((int) (transform(new Vector3Di(180, -10, 0)).getX() * scale + offsetX), (int) (transform(new Vector3Di(180, -10, 0)).getY() * scale + offsetY), (int) (transform(new Vector3Di(200, 0, 0)).getX() * scale + offsetX), (int) (transform(new Vector3Di(200, 0, 0)).getY() * scale + offsetY));
		g2D.drawLine((int) (transform(new Vector3Di(180, 0, 10)).getX() * scale + offsetX), (int) (transform(new Vector3Di(180, 0, 10)).getY() * scale + offsetY), (int) (transform(new Vector3Di(200, 0, 0)).getX() * scale + offsetX), (int) (transform(new Vector3Di(200, 0, 0)).getY() * scale + offsetY));
		g2D.drawLine((int) (transform(new Vector3Di(180, 0, -10)).getX() * scale + offsetX), (int) (transform(new Vector3Di(180, 0, -10)).getY() * scale + offsetY), (int) (transform(new Vector3Di(200, 0, 0)).getX() * scale + offsetX), (int) (transform(new Vector3Di(200, 0, 0)).getY() * scale + offsetY));
		
		g2D.setColor(Color.green);
		g2D.drawLine((int) (transform(new Vector3Di(0, -200, 0)).getX() * scale + offsetX), (int) (transform(new Vector3Di(0, -200, 0)).getY() * scale + offsetY), (int) (transform(new Vector3Di(0, 200, 0)).getX() * scale + offsetX), (int) (int) (transform(new Vector3Di(0, 200, 0)).getY() * scale + offsetY));
		g2D.drawLine((int) (transform(new Vector3Di(0, 180, 10)).getX() * scale + offsetX), (int) (transform(new Vector3Di(0, 180, 10)).getY() * scale + offsetY), (int) (transform(new Vector3Di(0, 200, 0)).getX() * scale + offsetX), (int) (int) (transform(new Vector3Di(0, 200, 0)).getY() * scale + offsetY));
		g2D.drawLine((int) (transform(new Vector3Di(0, 180, -10)).getX() * scale + offsetX), (int) (transform(new Vector3Di(0, 180, -10)).getY() * scale + offsetY), (int) (transform(new Vector3Di(0, 200, 0)).getX() * scale + offsetX), (int) (transform(new Vector3Di(0, 200, 0)).getY() * scale + offsetY));
		g2D.drawLine((int) (transform(new Vector3Di(10, 180, 0)).getX() * scale + offsetX), (int) (transform(new Vector3Di(10, 180, 0)).getY() * scale + offsetY), (int) (transform(new Vector3Di(0, 200, 0)).getX() * scale + offsetX), (int) (transform(new Vector3Di(0, 200, 0)).getY() * scale + offsetY));
		g2D.drawLine((int) (transform(new Vector3Di(-10, 180, 0)).getX() * scale + offsetX), (int) (transform(new Vector3Di(-10, 180, 0)).getY() * scale + offsetY), (int) (transform(new Vector3Di(0, 200, 0)).getX() * scale + offsetX), (int) (transform(new Vector3Di(0, 200, 0)).getY() * scale + offsetY));
		
		g2D.setColor(Color.blue);
		g2D.drawLine((int) (transform(new Vector3Di(0, 0, -200)).getX() * scale + offsetX), (int) (transform(new Vector3Di(0, 0, -200)).getY() * scale + offsetY), (int) (transform(new Vector3Di(0, 0, 200)).getX() * scale + offsetX), (int) (transform(new Vector3Di(0, 0, 200)).getY() * scale + offsetY));
		g2D.drawLine((int) (transform(new Vector3Di(0, 10, 180)).getX() * scale + offsetX), (int) (transform(new Vector3Di(0, 10, 180)).getY() * scale + offsetY), (int) (transform(new Vector3Di(0, 0, 200)).getX() * scale + offsetX), (int) (transform(new Vector3Di(0, 0, 200)).getY() * scale + offsetY));
		g2D.drawLine((int) (transform(new Vector3Di(0, -10, 180)).getX() * scale + offsetX), (int) (transform(new Vector3Di(0, -10, 180)).getY() * scale + offsetY), (int) (transform(new Vector3Di(0, 0, 200)).getX() * scale + offsetX), (int) (transform(new Vector3Di(0, 0, 200)).getY() * scale + offsetY));
		g2D.drawLine((int) (transform(new Vector3Di(10, 0, 180)).getX() * scale + offsetX), (int) (transform(new Vector3Di(10, 0, 180)).getY() * scale + offsetY), (int) (transform(new Vector3Di(0, 0, 200)).getX() * scale + offsetX), (int) (transform(new Vector3Di(0, 0, 200)).getY() * scale + offsetY));
		g2D.drawLine((int) (transform(new Vector3Di(-10, 0, 180)).getX() * scale + offsetX), (int) (transform(new Vector3Di(-10, 0, 180)).getY() * scale + offsetY), (int) (transform(new Vector3Di(0, 0, 200)).getX() * scale + offsetX), (int) (transform(new Vector3Di(0, 0, 200)).getY() * scale + offsetY));	
		*/
		
		c.setColor(Color.red);
		c.drawLine(adjust(transform3D(new Vector3Di(-200, 0, 0))), adjust(transform3D(new Vector3Di(200, 0, 0))));
		c.drawLine(adjust(transform3D(new Vector3Di(180, 10, 0))), adjust(transform3D(new Vector3Di(200, 0, 0))));
		c.drawLine(adjust(transform3D(new Vector3Di(180, -10, 0))), adjust(transform3D(new Vector3Di(200, 0, 0))));
		c.drawLine(adjust(transform3D(new Vector3Di(180, 0, 10))), adjust(transform3D(new Vector3Di(200, 0, 0))));
		c.drawLine(adjust(transform3D(new Vector3Di(180, 0, -10))), adjust(transform3D(new Vector3Di(200, 0, 0))));
		
		c.setColor(Color.green);
		c.drawLine(adjust(transform3D(new Vector3Di(0, -200, 0))), adjust(transform3D(new Vector3Di(0, 200, 0))));
		c.drawLine(adjust(transform3D(new Vector3Di(10, 180, 0))), adjust(transform3D(new Vector3Di(0, 200, 0))));
		c.drawLine(adjust(transform3D(new Vector3Di(-10, 180, 0))), adjust(transform3D(new Vector3Di(0, 200, 0))));
		c.drawLine(adjust(transform3D(new Vector3Di(0, 180, 10))), adjust(transform3D(new Vector3Di(0, 200, 0))));
		c.drawLine(adjust(transform3D(new Vector3Di(0, 180, -10))), adjust(transform3D(new Vector3Di(0, 200, 0))));
		
		c.setColor(Color.blue);
		c.drawLine(adjust(transform3D(new Vector3Di(0, 0, -200))), adjust(transform3D(new Vector3Di(0, 0, 200))));
		c.drawLine(adjust(transform3D(new Vector3Di(0, 10, 180))), adjust(transform3D(new Vector3Di(0, 0, 200))));
		c.drawLine(adjust(transform3D(new Vector3Di(0, -10, 180))), adjust(transform3D(new Vector3Di(0, 0, 200))));
		c.drawLine(adjust(transform3D(new Vector3Di(10, 0, 180))), adjust(transform3D(new Vector3Di(0, 0, 200))));
		c.drawLine(adjust(transform3D(new Vector3Di(-10, 0, 180))), adjust(transform3D(new Vector3Di(0, 0, 200))));
		
		c.setColor(Color.white);
		for(Vector3Di v: points) {
			// g2D.fillOval((int) (transform(v).getX() * scale + offsetX), (int) (transform(v).getY() * scale + offsetY), 8, 8);
			c.fillCircle(adjust(transform3D(v).add(new Vector3Di(0, -4, -4))), (int) (4 * scale));
		}
		
		// g2D.setColor(Color.cyan);
		for(Plane p: planes) {
			c.setColor(Color.cyan);
			for(Vector3Di v: p.getTracepoints()) {
				//g2D.fillOval((int) (transform(v).getX() * scale + offsetX - 4 * scale), (int) (transform(v).getY() * scale + offsetY - 4 * scale), (int) (8 * scale), (int) (8 * (scale)));
				c.fillCircle(adjust(transform3D(v).add(new Vector3Di(0, -4, -4))), (int) (4 * scale));
			}
			
			/*
			int[] xPosPlane = new int[3];
			int[] yPosPlane = new int[3];
			xPosPlane[0] = (int) (transform(p.getTracepoints().get(0)).getX() * scale + offsetX);
			yPosPlane[0] = (int) (transform(p.getTracepoints().get(0)).getY() * scale + offsetY);
			
			xPosPlane[1] = (int) (transform(p.getTracepoints().get(1)).getX() * scale + offsetX);
			yPosPlane[1] = (int) (transform(p.getTracepoints().get(1)).getY() * scale + offsetY);
			
			xPosPlane[2] = (int) (transform(p.getTracepoints().get(2)).getX() * scale + offsetX);
			yPosPlane[2] = (int) (transform(p.getTracepoints().get(2)).getY() * scale + offsetY);
			
			AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.3);
			g2D.setComposite(alphaComposite);
			g2D.fillPolygon(xPosPlane, yPosPlane, 3);
			g2D.setComposite(originalComposite);	
			*/
			
			c.setColor(new Color(Color.cyan.getRed(), Color.cyan.getGreen(), Color.cyan.getBlue(), 77));
			Vector3Di[] plane = new Vector3Di[3];
			plane[0] = adjust(transform3D(p.getTracepoints().get(0)));
			plane[1] = adjust(transform3D(p.getTracepoints().get(1)));
			plane[2] = adjust(transform3D(p.getTracepoints().get(2)));
			c.fillPolygon(plane);
		}
		
		g2D.setColor(Color.magenta);
		c.setColor(Color.magenta);
		for (Line l: lines) {
			/*
			Vector2D p1 = transform(l.getPoint());
			Vector2D p2 = transform(l.getPoint().add(l.getDirection()));	
			
			if (p2.getX() - p1.getX() != 0) {
				double m = (double) (p2.getY() - p1.getY()) / (double) (p2.getX() - p1.getX());
				int b = (p1.getY() + offsetY) - (int) (m * (p1.getX() + offsetX));
				
				g2D.drawLine(0, b, getWidth(), (int) (m * getWidth()) + b);
			}
			*/
			
			Vector3Di v1 = adjust(transform3D(l.getPoint()));
			Vector3Di v2 = adjust(transform3D(l.getPoint().add(l.getDirection())));
			Vector3Di direction = transform3D(l.getDirection());
			
			if (v2.getX2() - v1.getX2() != 0) {			
				double s1 = (double) -v1.getX2() / direction.getX2();
				double s2 = (double) (getWidth() - v1.getX2()) / direction.getX2();
				
				Vector3Di u1 = new Vector3Di(v1.getX1() + (int) (s1 * direction.getX1()), 0, v1.getX3() + (int) (s1 * direction.getX3()));
				Vector3Di u2 = new Vector3Di(v1.getX1() + (int) (s2 * direction.getX1()), getWidth(), v1.getX3() + (int) (s2 * direction.getX3()));
				
				System.out.println(v1.toString() + " " + v2.toString() + "   " + u1.toString() + " " + u2.toString());
				
				// c.drawLine(adjust(new Vector3Di(v1.getX1() + (int) (s2 * direction.getX1()), getWidth() / 2, v1.getX1() + (int) (s2 * direction.getX3()))), adjust(new Vector3Di(v1.getX1() + (int) (-s2 * direction.getX1()), -getWidth() / 2, v1.getX1() + (int) (-s2 * direction.getX3()))));
				c.drawLine(u1, u2);
			}
		}
		
		c.paint(g);
		c = null;
		g2D.dispose();
		
		System.out.println("Total: " + (System.nanoTime() - now) / 1000000);
		System.out.println();
	}
	
	public Vector2D transform(Vector3Di v3D) {
		v3D = new Vector3Di( (int) (v3D.getX1()),
							(int) (v3D.getX2() * Math.cos(Math.toRadians(angleX1)) + v3D.getX3() * Math.sin(Math.toRadians(angleX1))),
							(int) (v3D.getX3() * Math.cos(Math.toRadians(angleX1)) - v3D.getX2() * Math.sin(Math.toRadians(angleX1))));
		
		v3D = new Vector3Di( (int) (v3D.getX1() * Math.cos(Math.toRadians(angleX3)) - v3D.getX2() * Math.sin(Math.toRadians(angleX3))),
							(int) (v3D.getX2() * Math.cos(Math.toRadians(angleX3)) + v3D.getX1() * Math.sin(Math.toRadians(angleX3))),
							(int) v3D.getX3());
		
		v3D = new Vector3Di(	(int) (v3D.getX1() * Math.cos(Math.toRadians(angleX2)) - v3D.getX3() * Math.sin(Math.toRadians(angleX2))), 
							(int) v3D.getX2(), 
							(int) (v3D.getX3() * Math.cos(Math.toRadians(angleX2)) + v3D.getX1() * Math.sin(Math.toRadians(angleX2))));
		

		Vector2D v2D = new Vector2D(v3D.getX2(), -v3D.getX3());
		return v2D;
	}
	
	public Vector3Di transform3D(Vector3Di v3D) {
		v3D = new Vector3Di( (int) (v3D.getX1()),
							(int) (v3D.getX2() * Math.cos(Math.toRadians(angleX1)) + v3D.getX3() * Math.sin(Math.toRadians(angleX1))),
							(int) (v3D.getX3() * Math.cos(Math.toRadians(angleX1)) - v3D.getX2() * Math.sin(Math.toRadians(angleX1))));
		
		v3D = new Vector3Di( (int) (v3D.getX1() * Math.cos(Math.toRadians(angleX3)) - v3D.getX2() * Math.sin(Math.toRadians(angleX3))),
							(int) (v3D.getX2() * Math.cos(Math.toRadians(angleX3)) + v3D.getX1() * Math.sin(Math.toRadians(angleX3))),
							(int) v3D.getX3());
		
		v3D = new Vector3Di(	(int) (v3D.getX1() * Math.cos(Math.toRadians(angleX2)) - v3D.getX3() * Math.sin(Math.toRadians(angleX2))), 
							(int) v3D.getX2(), 
							(int) (v3D.getX3() * Math.cos(Math.toRadians(angleX2)) + v3D.getX1() * Math.sin(Math.toRadians(angleX2))));
		
		return v3D.add(new Vector3Di(0, 0, -2 * v3D.getX3()));
	}
	
	public Vector3Di adjust(Vector3Di v) {
		return v.toVector3Dd().multiplyScalar(scale).toVector3Di().add(new Vector3Di(0, offsetX, offsetY));
	}
	
	public void setAngleX3(int pAngle) {
		angleX3 = pAngle;
	}
	
	public void setAngleX2(int pAngle) {
		angleX2 = pAngle;
	}
	
	public void setAngleX1(int pAngle) {
		angleX1 = pAngle;
	}
	
	public void setVectors(LinkedList<Vector3Di> pPoints) {
		points = pPoints;
	}
	
	public void setPlanes(LinkedList<Plane> pPlanes) {
		planes = pPlanes;
	}
	
	public void setLines(LinkedList<Line> pLines) {
		lines = pLines;
	}
	
	public void setScale(double pScale) {
		scale = pScale;
		
	}
}
