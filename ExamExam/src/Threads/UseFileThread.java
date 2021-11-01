package Threads;

public class UseFileThread extends Thread {
	private CommonResourse resourse; 
	
	public UseFileThread (String name, CommonResourse resourse) {
		super(name);
		this.resourse = resourse;
	}
	public void run() {
		for (int i = 0; i < 5; i++) {
			resourse.writing(getName(), i);
		}
	}
}
