package ExamEpam;

public class Point1D {
	int X;
	
	
	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}
	
	public double length() {
		return Math.abs(X);
		
	}

	public void method (double value) {
		System.out.println("double " + value );
	}
	

}
