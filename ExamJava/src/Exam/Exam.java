package Exam;

public class Exam {
public static void main (String[] args) {
		
		int[] arr;
		arr = new int[10];
		arr[1] = 1;
		arr[2] = 2;
		int sum;
		sum = 0;
		for (int i = 0; i < arr.length; i++) {
			arr[i+1] = arr [i] + arr [i-1];
			sum++;
		}
	System.out.println(sum);
	}

}
