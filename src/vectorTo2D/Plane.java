package vectorTo2D;
import java.util.*;

public class Plane {
	private Vector3Di p1, p2, p3, n;
	private double d;
	private LinkedList<Vector3Di> tracepoints;
	
	public Plane (Vector3Di pP1, Vector3Di pP2, Vector3Di pP3) {
		p1 = pP1;
		p2 = pP2;
		p3 = pP3;
		
		Vector3Di p1p2 = new Vector3Di(p2.getX1() - p1.getX1(), p2.getX2() - p1.getX2(), p2.getX3() - p1.getX3());
		Vector3Di p1p3 = new Vector3Di(p3.getX1() - p1.getX1(), p3.getX2() - p1.getX2(), p3.getX3() - p1.getX3());
		
		n = p1p2.getCrossP(p1p3);
		d = n.getDotP(p1);
		
		tracepoints = new LinkedList<Vector3Di>();
		tracepoints.add(new Vector3Di((int) (d / n.getX1()), 0, 0));
		tracepoints.add(new Vector3Di(0, (int) (d / n.getX2()), 0));
		tracepoints.add(new Vector3Di(0, 0, (int) (d / n.getX3())));
		
		System.out.println(n.toString() + " " + d);
	}
	
	public Plane (Vector3Di pN, double pD) {
		n = pN;
		d = pD;
		
		tracepoints = new LinkedList<Vector3Di>();
		tracepoints.add(new Vector3Di((int) (d / n.getX1()), 0, 0));
		tracepoints.add(new Vector3Di(0, (int) (d / n.getX2()), 0));
		tracepoints.add(new Vector3Di(0, 0, (int) (d / n.getX3())));
	}
	
	public Vector3Di getNormal() {
		return n;
	}
	
	public double getD() {
		return d;
	}
	
	public LinkedList<Vector3Di> getTracepoints() {
		return tracepoints;
	}
	
	public String toString() {
		return "E: " + n.getX1() + "*x1+" + n.getX2() + "*x2+" + n.getX3() + "*x3=" + d;
	}
}
