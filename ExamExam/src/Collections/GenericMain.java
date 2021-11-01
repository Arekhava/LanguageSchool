package Collections;

import java.util.ArrayList;
import java.util.List;

import ExamEpam.Person;
import ExamEpam.Student;

public class GenericMain {
	public static void main(String[] args) {
	List<Person> list = new ArrayList<>();
	list.add(new Student());
	list.add(new Person());
	//list.add("wsaufb");
	Person person = list.get(44);
	
	}
}
