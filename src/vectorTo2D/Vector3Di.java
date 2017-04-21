package vectorTo2D;

public class Vector3Di {
	private int x1, x2, x3;
	
	public Vector3Di(int pX1, int pX2, int pX3) {
		x1 = pX1;
		x2 = pX2;
		x3 = pX3;
	}
	
	public void setX1(int pX1) {
		x1 = pX1;
	}
	
	public void setX2(int pX2) {
		x2 = pX2;
	}
	
	public void setX3(int pX3) {
		x3 = pX3;
	}
	
	public int getX1() {
		return x1;
	}
	
	public int getX2() {
		return x2;
	}
	
	public int getX3() {
		return x3;
	}
	
	public int getLength() {
		return (int) Math.sqrt(x1 * x1 + x2 * x2 + x3 * x3);
	}
	
	public int getDotP(Vector3Di v2) {
		return this.x1 * v2.getX1() + this.x2 * v2.getX2() + this.x3 * v2.getX3();
	}
	
	public Vector3Di getCrossP(Vector3Di v2) {
		return new Vector3Di(this.x2 * v2.getX3() - this.x3 * v2.getX2(), this.x3 * v2.getX1() - this.x1 * v2.getX3(), this.x1 * v2.getX2() - this.x2 * v2.getX1());
	}
	
	public Vector3Di add(Vector3Di v) {
		return new Vector3Di(this.x1 + v.getX1(), this.x2 + v.getX2(), this.x3 + v.getX3());
	}
	
	public Vector3Di multiplyScalar(double s) {
		return new Vector3Di((int) (this.x1 * s), (int) (this.x2 * s), (int) (this.x3 * s));
	}
	
	public Vector3Dd toVector3Dd() {
		return new Vector3Dd(x1, x2, x3);
	}
	
	public String toString() {
		return "(" + x1 + ";" + x2 + ";" + x3 + ")";
	}
}
