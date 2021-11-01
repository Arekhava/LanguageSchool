package ExamEpam;

import java.util.Comparator;

public class Owner {
	private int value = 1;
	static int statValue = 5;
	static class ValueComparator implements Comparator<Owner> {
		static int param;
		static int param1 = statValue;
		//static int param2 = value;
		@Override
		public int compare(Owner o1, Owner o2) {
			
			return o2.value-o1.value;
		}
		
	}
	
	
	
	/*void action(){
		int a = 2; 
		
		class InnerAction{
			int inner  = a; 
			int inner1 = value;
		}
		
	}
	
	
	static void staticAction(){
		int b = 4; 
		class staticInnerAction{
			int s = b;
			//int inner = value;
			}
	}*/
	
}
