package ExamEpam;

public class InterfaceMain {
	public static void main(String[] args) {
		Action.StaticService.service();
		Action.newService();
		new Action.StaticService().service2();
	}

}
