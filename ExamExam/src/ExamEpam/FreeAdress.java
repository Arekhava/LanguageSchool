package ExamEpam;

public class FreeAdress extends Student.Adress {

	public FreeAdress() {
		new Student().super();
	}
	
	public FreeAdress(Student student) {
		student.super();
	}

}
