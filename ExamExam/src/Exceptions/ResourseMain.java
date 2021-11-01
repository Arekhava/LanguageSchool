package Exceptions;

public class ResourseMain {
	public static void main(String[] args)  {
		Resourse resourse = new Resourse();
		try {
			ResourceAction.load(resourse);
		} catch (ResourseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
