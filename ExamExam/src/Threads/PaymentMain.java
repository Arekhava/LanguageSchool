package Threads;

import java.util.concurrent.TimeUnit;

public class PaymentMain {
public static void main(String[] args) {
	Payment pay = new Payment();
	for (int i = 0; i < 5; i++) {
	new Thread(() -> pay.doPayment()).start();
	}
	try {
		TimeUnit.MILLISECONDS.sleep(100);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	pay.init();
}
}
