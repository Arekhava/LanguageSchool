package Collections;

import java.util.LinkedList;
import java.util.Queue;

public class QueueMain {
	public static void main(String[] args) {
		Queue<String> queue = new LinkedList<String>() {
			{
			this.offer("jeans");	
			}
		};
		queue.offer("dress");
		queue.add("shooes");
		System.out.println(queue);
		//queue.remove("dress");
		queue.forEach(System.out::println);
		//queue.clear();
		//queue.element();
		queue.stream().filter(s -> !s.endsWith("s")).forEach(System.out::println);
	}
}
