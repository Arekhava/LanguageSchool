package Collections;

import java.util.ArrayList;
import java.util.List;

public class ListMain {
public static void main(String[] args) {
	List<Integer> list = new ArrayList<>();
	list.add(4);
	list.add(6);
	list.add(67);
	list.add(null);
	list.add(67);
	list.add(990);
	System.out.println(list);
	list.add(5,80);
	System.out.println(list );
	Integer value = list.get(3);
	System.out.println(value );
	int index = list.indexOf(990);
	System.out.println(index );
	list.remove(new Integer(67));
	System.out.println(list);
	System.out.println(list.subList(3, 5));
	}
}
