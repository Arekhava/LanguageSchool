package Threads;

public class DaemonDemo {
	public static void main(String[] args) {
		WalkThread walk = new WalkThread();
		Thread talk = new Thread(new TalkThread());
		talk.setDaemon(true);
		walk.start();
		talk.start();
		System.out.println("end of Main");
		}
	}