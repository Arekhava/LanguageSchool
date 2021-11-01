package Collections;

import java.util.ArrayList;
import java.util.List;

import ExamEpam.Person;
import ExamEpam.Student;

public class WildCard {
	public static void main(String[] args) {
		List <Person> list = new ArrayList<>();
		list.add(new Person());
		list.add(new Student());
		action(list);
		List <Student> list1 = new ArrayList<>();
		action(list1);
		list1.add(new Student());
		
	}
	static void action(List <? extends Person> list) {
		list.remove(0);
		//list.add(new Person());
	}

}
