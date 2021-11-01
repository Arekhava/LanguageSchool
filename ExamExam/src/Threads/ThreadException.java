package Threads;

import java.util.concurrent.TimeUnit;

public class ThreadException {
	public static void main(String[] args) {
		new Thread (()-> {
			try {
				TimeUnit.MILLISECONDS.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("end of Thread");
		}).start();
		try {
			TimeUnit.MILLISECONDS.sleep(20);
			
		}catch (InterruptedException e) {
			e.printStackTrace();
			}
		if (Boolean.TRUE) {
			throw new RuntimeException();
		}
		System.out.println("end of main");
		}
	}


