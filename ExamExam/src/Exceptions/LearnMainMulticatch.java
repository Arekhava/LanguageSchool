package Exceptions;

public class LearnMainMulticatch {
	public static void main(String[] args) {
		String [] numbers = {"32", "0", "L ", "87"};
		int sum = 0;
		int counter = 0;
		for (int i = 0; i < numbers.length; i++) {
			try { 
				sum += 100/Integer.parseInt(numbers[i]);
			}catch( ArithmeticException | NumberFormatException e) {
				System.out.println(e);
			}finally {
				System.out.println("counter = " + ++counter );
			}
		}
		System.out.println(sum);	
	}
}
