package ExamEpam;

public interface Action {
	static void newService() {}
	public static class StaticService {
		static void service() {}
		void service2() {}
	}
}
