package Collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ExamEpam.Person;
import ExamEpam.PersonNameComparator;

public class ComparatorMain {
	public static void main(String[] args) {
		ArrayList<Person> list = new ArrayList<>();
		list.add(new Person(78, "Anna"));
		list.add(new Person(127, "PAlina"));
		list.add(new Person(157, "PAlina"));
		list.add(new Person(178, "PAlina"));
		list.add(new Person(786, "LAnfisa"));
		list.add(new Person(768, "NArina"));
		/*System.out.println(list);
		list.sort(new Person.NameComparator());
		System.out.println(list);
		Collections.sort(list, new PersonNameComparator());*/
		//list.sort((o1, o2) -> o1.getPersonId() - o2.getPersonId());
		list.sort(Comparator.comparing(Person::getName).thenComparing(Person::getPersonId));
		System.out.println(list);
	}

}
