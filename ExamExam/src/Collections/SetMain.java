package Collections;

import java.util.HashSet;
import java.util.Set;

public class SetMain {
	public static void main(String[] args) {
		Set<String> set = new HashSet<String>() {
			{
				this.add("one");
				this.add("two");
				this.add("five");
			}
		};
		
		System.out.println(set);
		//value = set.add("five");
		//System.out.println(set +" " + value);
		//set.add(new String("one"));
		//System.out.println(set);
	}
}
