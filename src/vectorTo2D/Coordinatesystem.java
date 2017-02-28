package vectorTo2D;
import java.awt.*;
import java.util.*;

public class Coordinatesystem extends Canvas {
	int angleX1, angleX2, angleX3, offsetX, offsetY;
	LinkedList<Vector3D> points;
	
	public Coordinatesystem() {
		angleX1 = 0;
		angleX2 = 0;
		angleX3 = 0;
		
		points = new LinkedList<Vector3D>();
	}
	
	public void paint(Graphics g) {
		offsetX = getWidth() / 2;
		offsetY = getHeight() / 2;
		g.setColor(Color.red);
		g.drawLine(offsetX, offsetY, transform(new Vector3D(200, 0, 0)).getX() + offsetX, transform(new Vector3D(200, 0, 0)).getY() + offsetY); // x1
		g.setColor(Color.green);
		g.drawLine(offsetX, offsetY, transform(new Vector3D(0, 200, 0)).getX() + offsetX, transform(new Vector3D(0, 200, 0)).getY() + offsetY);
		g.setColor(Color.blue);
		g.drawLine(offsetX, offsetY, transform(new Vector3D(0, 0, 200)).getX() + offsetX, transform(new Vector3D(0, 0, 200)).getY() + offsetY);
		
		g.setColor(Color.white);
		for(Vector3D v: points) {
			g.fillOval(transform(v).getX() + offsetX, transform(v).getY() + offsetY, 5, 5);
		}
		
		
		System.out.println();
	}
	
	public Vector2D transform(Vector3D v3D) {
		v3D = new Vector3D( (int) (v3D.getX1()),
							(int) (v3D.getX2() * Math.cos(Math.toRadians(angleX1)) + v3D.getX3() * Math.sin(Math.toRadians(angleX1))),
							(int) (v3D.getX3() * Math.cos(Math.toRadians(angleX1)) - v3D.getX2() * Math.sin(Math.toRadians(angleX1))));
		
		v3D = new Vector3D( (int) (v3D.getX1() * Math.cos(Math.toRadians(angleX3)) - v3D.getX2() * Math.sin(Math.toRadians(angleX3))),
							(int) (v3D.getX2() * Math.cos(Math.toRadians(angleX3)) + v3D.getX1() * Math.sin(Math.toRadians(angleX3))),
							(int) v3D.getX3());
		
		v3D = new Vector3D(	(int) (v3D.getX1() * Math.cos(Math.toRadians(angleX2)) - v3D.getX3() * Math.sin(Math.toRadians(angleX2))), 
							(int) v3D.getX2(), 
							(int) (v3D.getX3() * Math.cos(Math.toRadians(angleX2)) + v3D.getX1() * Math.sin(Math.toRadians(angleX2))));
		

		Vector2D v2D = new Vector2D(v3D.getX2(), -v3D.getX3());
		return v2D;
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
	
	public void setVectors(LinkedList<Vector3D> pPoints) {
		points = pPoints;
	}
}
