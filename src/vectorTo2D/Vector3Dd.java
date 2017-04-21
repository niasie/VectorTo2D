package vectorTo2D;

public class Vector3Dd {
	private double x1, x2, x3;
	
	public Vector3Dd(double pX1, double pX2, double pX3) {
		x1 = pX1;
		x2 = pX2;
		x3 = pX3;
	}
	
	public void setX1(double pX1) {
		x1 = pX1;
	}
	
	public void setX2(double pX2) {
		x2 = pX2;
	}
	
	public void setX3(double pX3) {
		x3 = pX3;
	}
	
	public double getX1() {
		return x1;
	}
	
	public double getX2() {
		return x2;
	}
	
	public double getX3() {
		return x3;
	}
	
	public double getLength() {
		return (double) Math.sqrt(x1 * x1 + x2 * x2 + x3 * x3);
	}
	
	public double getDotP(Vector3Dd v2) {
		return this.x1 * v2.getX1() + this.x2 * v2.getX2() + this.x3 * v2.getX3();
	}
	
	public Vector3Dd getCrossP(Vector3Dd v2) {
		return new Vector3Dd(this.x2 * v2.getX3() - this.x3 * v2.getX2(), this.x3 * v2.getX1() - this.x1 * v2.getX3(), this.x1 * v2.getX2() - this.x2 * v2.getX1());
	}
	
	public Vector3Dd add(Vector3Dd v) {
		return new Vector3Dd(this.x1 + v.getX1(), this.x2 + v.getX2(), this.x3 + v.getX3());
	}
	
	public Vector3Dd multiplyScalar(double s) {
		return new Vector3Dd((double) (this.x1 * s), (double) (this.x2 * s), (double) (this.x3 * s));
	}
	
	public Vector3Di toVector3Di() {
		return new Vector3Di((int) x1, (int) x2, (int) x3);
	}
	
	public String toString() {
		return "(" + x1 + ";" + x2 + ";" + x3 + ")";
	}
}
