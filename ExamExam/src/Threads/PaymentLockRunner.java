package Threads;

import java.util.concurrent.TimeUnit;

public class PaymentLockRunner {
	public static void main(String[] args) {
		final PaymentLock pay = new PaymentLock();
		new Thread(() -> pay.doPayment()).start();
		try {
			TimeUnit.MILLISECONDS.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pay.init();
		System.out.println("the end");
	}

}
