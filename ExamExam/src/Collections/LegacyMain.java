package Collections;

import java.util.Enumeration;
import java.util.Hashtable;

public class LegacyMain {
	public static void main(String[] args) {
		Hashtable <String, Integer> table = new Hashtable<>();
		table.put("asasf", 40);
		table.put("asafasf", 43);
		table.put("asasaff", 46);
		table.compute("adfca",(k,v)-> 346);
		table.computeIfPresent("adfca",(k,v)-> v+k.length());
		Enumeration<String> keys = table.keys();
		while (keys.hasMoreElements()) {
			System.out.println(keys.nextElement());
		}
	
		System.out.println(table);
	}

}
