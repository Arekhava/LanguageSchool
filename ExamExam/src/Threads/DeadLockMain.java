package Threads;

public class DeadLockMain {
	public static void main(String[] args) {
		InviteAction inv1 = new InviteAction("First");
		InviteAction inv2 = new InviteAction("Second");
		new Thread(()->inv1.invite(inv2)).start();
		new Thread(()->inv2.invite(inv1)).start();
	}

}
