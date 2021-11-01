package Collections;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityMain {
	public static void main(String[] args) {
		Queue<String> prior = new PriorityQueue<>(Comparator.reverseOrder());
		prior.offer("N");
		prior.offer("A");
		prior.offer("T");
		prior.offer("A");
		prior.offer("L");
		prior.offer("L");
		prior.offer("I");
		prior.offer("A");
		prior.offer("1");
		while (!prior.isEmpty()) {
			System.out.println(prior.poll());
		}
	} 
}
