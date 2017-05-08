package vectorTo2D;

public class Line {
	Vector3Di point, direction;
	
	Line(Vector3Di p, Vector3Di d) {
		point = p;
		direction = d;
	}
	
	public Vector3Di getPoint() {
		return point;
	}
	
	public Vector3Di getDirection() {
		return direction;
	}
	
	public String toString() {
		return "g:x=" + point.toString() + "+s*" + direction.toString();
	}
}
