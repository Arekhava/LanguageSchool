package Threads;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SynchroFirstMain {
public static void main(String[] args) throws Exception {
	try (CommonResourse resourse = new CommonResourse("data\\thread.txt")){
		UseFileThread thread1 = new UseFileThread("First", resourse);
		UseFileThread thread2 = new UseFileThread("Second", resourse);
		thread1.start();
		thread2.start();
		TimeUnit.SECONDS.sleep(5);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
