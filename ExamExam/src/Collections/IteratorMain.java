package Collections;

import java.util.ArrayList;

import ExamEpam.Order;

public class IteratorMain {
	public static void main(String[] args) {
		ArrayList<Order> orders = new ArrayList<Order>();
		orders.add(new Order(23, 5.7));
		orders.add(new Order(53, 1235.7));
		orders.add(new Order(63, 5235.7));
		orders.add(new Order(238, 9235.7));
		
		System.out.println(orders);
		float bigAmount = 10;
		int percent = 5;
		orders.removeIf(o -> o.getAmount() <= bigAmount);
		orders.forEach(o->o.setAmount(o.getAmount() * (100-percent)/100.0));
		System.out.println(orders);
	}
}
