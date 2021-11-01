package ExamEpam;

import java.util.Arrays;

public class VariableParams {
	public void method(int...values) {
		if ( values.length !=0 ) {
			System.out.println(Arrays.toString(values));
		}	else {
			System.out.println("without array");
		}
	}
	public void method(int a, int b) {
		System.out.println(a + " " + b);
		
	}

}
