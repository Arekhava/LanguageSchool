package Collections;

import java.util.TreeSet;

import ExamEpam.Person;


public class ComparableMain {
	public static void main(String[] args) {
		TreeSet<Person> set = new TreeSet<>();
		set.add(new Person(89, " Anna"));
		set.add(new Person(189, " TAlina"));
		set.add(new Person(809, " KAnita"));
		set.add(new Person(891, " MAnfisa"));
		System.out.println(set);
	}
}
