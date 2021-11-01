package Collections;

import java.util.HashSet;
import java.util.TreeSet;

public class HashSetMain {
	public static void main(String[] args) {
		HashSet<String> words = new HashSet<>(1000);
		words.add("38G");
		words.add("138G");
		words.add("K38G");
		words.add("938G");
		words.add("L38G");
		words.add("K38G");
		words.add("4438G");
		words.add("M38G");
		System.out.println(words);
		//TreeSet<String> words1 = new TreeSet<>(words);
		//System.out.println(words1);
		for (String element : words) {
			System.out.print(element.hashCode() + "  ");
		}
	}

}
