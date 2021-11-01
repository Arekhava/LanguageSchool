package Collections;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MapMain {
	public static void main(String[] args) {
	Map<String, Integer> map = new HashMap<>();
	map.put ("Anna", 5);
	map.put ("Irina", 7);
	map.put ("Ivan", 1);
	map.put ("Arina", 2);
	System.out.println(map);
	int value = map.put("Irina", 1);
	System.out.println(value);
	System.out.println(map);
	Set<String> set = map.keySet();
	System.out.println(set);
	Collection<Integer> collection = map.values();
	System.out.println(collection);
	Set<Map.Entry<String, Integer>> set1 = map.entrySet();
	System.out.println(set1);
	Set<Integer> sets = new HashSet<>(collection);
	System.out.println(sets);
	}
}
