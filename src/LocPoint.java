package src;

public class LocPoint {

	private double x, y;

	public LocPoint(String LAT, String LON) {
		this.x = Double.parseDouble(LAT);
		this.y = Double.parseDouble(LON);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "LocPoint [x=" + x + ", y=" + y + "]";
	}

	public boolean pointInCircle(LocPoint Other, double radius) {
		double C = 40075.04, A = 360*radius/C, B = A/Math.cos(Math.toRadians(this.x));
	    return Math.pow((this.x-Other.x)/A, 2) + Math.pow((this.y-Other.y)/B, 2) < 1;
	}
}
