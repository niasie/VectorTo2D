package vectorTo2D;
import java.awt.*;
import java.util.concurrent.TimeUnit;
import java.awt.image.*;
import java.util.*;

public class Canvas {
	public class Point {
		int x1;
		Color color;
		Point alpha;
		
		public Point() {
			x1 = Integer.MIN_VALUE;
			color = Color.black;
		}
		
		public Point(int pX1, Color pColor) {
			x1 = pX1;
			color = pColor;
		}
		
		public Point(int pX1, Color pColor, Point pAlpha) {
			this(pX1, pColor);
			alpha = pAlpha;
		}
		
		public int getX1() {
			return x1;
		}
		
		public Color getColor() {
			if (alpha == null) {
				return color;
			} else {				
				return new Color((int) (color.getAlpha() * 1/255.0 * (color.getRGB() & 0x00FFFFFF) + (255 - color.getAlpha()) * 1/255.0 * (alpha.getColor().getRGB() & 0x00FFFFFF)));
			}
		}
		
		public Point getAlpha() {
			return alpha;
		}
		
		public int countAlpha(int p) {
			if (alpha == null) {
				return p;
			} else {
				return alpha.countAlpha(p + 1);
			}
		}
		
		public void addPoint(Point p) {
			if (p.getColor().getAlpha() == 255 && p.getX1() > x1) {
				x1 = p.getX1();
				color = p.getColor();
				alpha = null;
			} else if (p.getColor().getAlpha() != 255 && p.getX1() > x1) {
				alpha = new Point(x1, color, alpha);
				x1 = p.getX1();
				color = p.getColor();
			} else if (color.getAlpha() != 255 && p.getX1() <= x1) {
				if (alpha == null) {
					alpha = p;
				} else  {
					alpha.addPoint(p);
				}
			}
		}
	}
	
	Point[][] pixels;
	BufferedImage image;
	Color bgcolor, color;
	Dimension size;
	
	public Canvas(Dimension pSize, Color pBgcolor) {
		bgcolor = pBgcolor;
		pixels = new Point[(int) pSize.getWidth()][(int) pSize.getHeight()];
		image = new BufferedImage((int) pSize.getWidth(), (int) pSize.getHeight(), BufferedImage.TYPE_INT_ARGB);
		size = pSize;
		color = Color.black;
		
		for (int i = 0; i < pSize.getWidth(); i++) {
			for (int e = 0; e < pSize.getHeight(); e++) {
				pixels[i][e] = new Point();
			}
		}
	}
	
	public void setColor(Color pColor) {
		color = pColor;
	}
	
	public void fillRect(Vector3Di v, int w, int h) {
		for (int i = v.getX2(); i < v.getX2() + w; i++) {
			for (int e = v.getX3(); e < v.getX3() + h; e++) {
				if (pixels[i][e].getX1() <= v.getX1() && !isOutOfBounds(i, e)) {
					pixels[i][e].addPoint(new Point(v.getX1(), color));
					image.setRGB(i, e, pixels[i][e].getColor().getRGB());
				}

			}
		}
	}
	
	public void fillRect(int x1, int x2, int x3, int w, int h) {
		fillRect(new Vector3Di(x1, x2, x3), w, h);
	}
	
	public void drawLine(Vector3Di v, Vector3Di u) {
		double now = System.nanoTime();
	
		Vector3Dd vu = new Vector3Dd(u.getX1() - v.getX1(), u.getX2() - v.getX2(), u.getX3() - v.getX3());
		double len = vu.getLength();
		// vu = vu.multiplyScalar(1 / vu.getLength());
		
		boolean[][] x1Visited = new boolean[(int) size.getWidth()][(int) size.getHeight()];

		for(double i = 0; i <= 1; i = i + 1.0 / len) {
			Vector3Di temp = v.add(vu.multiplyScalar(i).toVector3Di());
			if (!isOutOfBounds(temp.getX2(), temp.getX3()) && !x1Visited[temp.getX2()][temp.getX3()]) {
				pixels[temp.getX2()][temp.getX3()].addPoint(new Point(temp.getX1(), color));
				image.setRGB(temp.getX2(), temp.getX3(), pixels[temp.getX2()][temp.getX3()].getColor().getRGB());
				x1Visited[temp.getX2()][temp.getX3()] = true;
			}			
		}
		
		System.out.println("Line: " + (System.nanoTime() - now) / 1000000);
	}
	
	public void fillCircle(Vector3Di v, int r) {
		double now = System.nanoTime();
		
		for (int i = 0; i <= 2 * r; i++) {
			int val = (int) -Math.sqrt(r * r - (r - i) * (r - i));			
			for (int e = val + r; e <= -val + r; e++) {
				if (!isOutOfBounds(i + v.getX2(), e + v.getX3())) {
					pixels[i + v.getX2()][e + v.getX3()].addPoint(new Point(v.getX1(), color));
					image.setRGB(i + v.getX2(), e + v.getX3(), pixels[i + v.getX2()][e + v.getX3()].getColor().getRGB());
				}
			}
		}
		
		System.out.println("Circle: " + (System.nanoTime() - now) / 1000000);
	}
	
	public void fillPolygon(Vector3Di[] coor) {
		double now = System.nanoTime();
		
		if (coor.length == 3) {
			Vector3Dd ab = coor[1].add(coor[0].multiplyScalar(-1)).toVector3Dd();
			double lenAB = ab.getLength();
			
			Vector3Dd ac = coor[2].add(coor[0].multiplyScalar(-1)).toVector3Dd();
			double lenAC = ac.getLength();
			
			boolean[][] x1Visited = new boolean[(int) size.getWidth()][(int) size.getHeight()];
			
			for (double r = 0; r <= 1; r = r + 1.0 / lenAB) {
				for (double s = 0; r + s <= 1; s = s + 1.0 / lenAC) {
					int x1 = coor[0].getX1() + (int) (r * ab.getX1() + s * ac.getX1());
					int x2 = coor[0].getX2() + (int) (r * ab.getX2() + s * ac.getX2());
					int x3 = coor[0].getX3() + (int) (r * ab.getX3() + s * ac.getX3());
					
					if (!isOutOfBounds(x2, x3) && !x1Visited[x2][x3]) {
						pixels[x2][x3].addPoint(new Point(x1, color));
						image.setRGB(x2, x3, pixels[x2][x3].getColor().getRGB());				
						x1Visited[x2][x3] = true;
						// System.out.println(x1 + " " + x2 + " " + x3 + " " + pixels[x2][x3].getColor().getRGB() + " " + pixels[x2][x3].countAlpha(0));
					}
				}
			}		
		}
		
		System.out.println("Triangle: " + (System.nanoTime() - now) / 1000000);
	}
	
	public boolean isOutOfBounds(int x, int y) {
		return x < 0 || x >= size.getWidth() || y < 0 || y >= size.getHeight();
	}
	
	public void paint(Graphics g) {
		double now = System.nanoTime();
		
		Graphics2D g2D = (Graphics2D) g.create();
		//g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//g2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g2D.drawImage(image, null, null);
		g2D.dispose();
		
		System.out.println("Paint: " + (System.nanoTime() - now) / 1000000);
	}
}
