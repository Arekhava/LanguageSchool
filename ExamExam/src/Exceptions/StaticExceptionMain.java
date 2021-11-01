package Exceptions;

public class StaticExceptionMain {
	static int value;
	static { 
		value = Integer.parseInt("HI");
		}
	public static void main(String[] args) {
		int a = value; 
	}

}
