package Threads;

public class PriorityDemo { 
	public static void main(String[] args) {
	WalkThread walk = new WalkThread();
	Thread talk = new Thread(new TalkThread());
	talk.setPriority(1);
	walk.setPriority(10);
	walk.start();
	talk.start();
	}
}
