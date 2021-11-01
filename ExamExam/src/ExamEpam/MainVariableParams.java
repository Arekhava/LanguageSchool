package ExamEpam;

public class MainVariableParams {
	public static void main(String[] args) {
		VariableParams params = new VariableParams();
		params.method(1,2,3,4);
		params.method();
		int[] arr = {1,3,5};
		params.method(arr);
		params.method(2, 7);
		
		
	}

}
