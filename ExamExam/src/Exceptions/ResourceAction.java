package Exceptions;

public class ResourceAction {

	public static void load (Resourse resourse) throws ResourseException {
		if (resourse == null || !resourse.exist() || !resourse.isCreate()) {
			throw new ResourseException("");
		
		}
		
	}
}
