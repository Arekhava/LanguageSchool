package ExamEpam;

public class Point2D extends Point1D{
	
	int Y;

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}

	@Override
	public double length() {
		return Math.hypot(Y, getX());
	}
	
	public void method() {
		System.out.println("without ");	
	}
	
	public void method(String str) {
		System.out.println("string " + str );
	}
	
	public static void method (int value) {
		System.out.println("int " + value );
	}
}
