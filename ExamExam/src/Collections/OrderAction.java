package Collections;

import java.util.Iterator;
import java.util.List;

import ExamEpam.Order;

public class OrderAction {
	public void discountAction(float bigAmount, List<Order> orders, int percent) {
		Iterator<Order> iterator = orders.iterator();
		while(iterator.hasNext()) {
			Order order = iterator.next();
			if (bigAmount >= order.getAmount()) {
				iterator.remove();
			}else{
				order.setAmount(order.getAmount()*(100 - percent)/100.0);
			}
		}
	}
}
