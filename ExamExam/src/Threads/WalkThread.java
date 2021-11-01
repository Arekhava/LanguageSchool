package Threads;

import java.util.concurrent.TimeUnit;

public class WalkThread extends Thread {
	public void run() {
		for (int i = 0; i <100; i++) {
			System.out.println("Walk " + i);
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}finally {
				System.out.println("end of WalkThread");
			}
		}
	}
}
