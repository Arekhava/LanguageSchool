package Collections;

import java.util.ArrayList;
import java.util.List;

import ExamEpam.Person;
import ExamEpam.PersonNameComparator;
import ExamEpam.Student;

public class WildSuperMain {
	public static void main(String[] args) {
		List <Student> list = new ArrayList<>();
		list.add(new Student());
		list.sort(new PersonNameComparator());
		/*action(list);
		List <Person> list1 = new ArrayList<>();
		action(list1);*/
		
	}
	static void action(List <? super Student> list) {
		list.remove(0);
		list.add(new Student());
		// list.add(new Person());
		}
}