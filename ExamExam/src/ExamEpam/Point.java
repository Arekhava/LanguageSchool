package ExamEpam;

public class Point {
	private final int MAX_RANGE = 1000;
	private int X;
	private String name;
	final StringBuilder builder = new StringBuilder();
	
	final int length() {
		return Math.abs(X);
	}

	public Point(int x) {
		super();
		X = x;
		builder.append("java");
	}
	
	

}
