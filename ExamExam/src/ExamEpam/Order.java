package ExamEpam;

import java.util.Objects;

public class Order {
	private long orderId;
	private double amount;

	
	

	public Order(long orderId, double amount) {
		super();
		this.orderId = orderId;
		this.amount = amount;
	}



	public long getOrderId() {
		return orderId;
	}

	public double getAmount() {
		return amount;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}



	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", amount=" + amount + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(amount, orderId);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount) && orderId == other.orderId;
	}
	
	
	
	
}
