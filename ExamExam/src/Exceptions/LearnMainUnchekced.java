package Exceptions;

public class LearnMainUnchekced {
	public static void main(String[] args) {
		String [] numbers = {"32", "0", "L ", "87"};
			int result;
			int value = 0;
			int sum = 0;
			for (int i = 0; i < numbers.length; i++) {
		try { 
			result = Integer.parseInt(numbers[i]);
		try {
			sum += 100/result;
			Integer.parseInt("");
		}catch( ArithmeticException e) {
			System.out.println(e);
			//value = -2;
		//} catch (RuntimeException e){
			
		}
		}catch(NumberFormatException e ) {
			//result = -1;
			System.out.println(e);
		}
			}
		System.out.println(sum);	
	}

}
