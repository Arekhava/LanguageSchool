package Collections;

import java.util.ArrayDeque;
import java.util.Deque;

public class ArrayDequeMain {
	public static void main(String[] args) {
		Deque<Integer> stack = new ArrayDeque<>();
		stack.push(1);
		stack.push(6);
		stack.push(16);
		stack.push(18);
		while(!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
		Deque<Integer> queue = new ArrayDeque<>();
		queue.offer(34);
		queue.offer(346);
		queue.offer(341);
		queue.offer(134);
		while(!queue.isEmpty()) {
			System.out.println(queue.poll());
		}
	}
}
