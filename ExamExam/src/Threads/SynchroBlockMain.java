package Threads;

import java.util.concurrent.TimeUnit;

public class SynchroBlockMain {
	static int counter; 
	public static void main(String[] args) {
		StringBuilder info = new StringBuilder();
		new Thread(() -> {
		synchronized (info) {
			do {
			info.append('A');
		System.out.println(info);
		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}while(counter ++ < 2);
		}
		}).start();
		new Thread(() -> {
			synchronized (info) {
				while(counter++ <6) {
				info.append('Z');
				System.out.println(info);
			}	
			}
		}).start();
	}

}
