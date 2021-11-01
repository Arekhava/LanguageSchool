package Exceptions;

public class LearnMainThrow {
	public static void main(String[] args) {

	int a = 42;
	if (a <= 0) {
		//Exception exception = new IllegalArgumentException();
		throw new IllegalArgumentException();
	}
	double res = Math.pow(a, 2);
		System.out.println(res);
	}
	
}
