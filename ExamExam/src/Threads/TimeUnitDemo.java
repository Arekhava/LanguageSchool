package Threads;

import java.util.concurrent.TimeUnit;

public class TimeUnitDemo {
	public static void main(String[] args) {
		long timeout = 1000*60*(60*25+32);
		try {
			TimeUnit.MINUTES.sleep(timeout);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
