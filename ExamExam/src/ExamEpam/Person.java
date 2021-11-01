package ExamEpam;

import java.util.Comparator;
import java.util.Objects;

public class Person implements Comparable<Person>{
	private int personId;
	private String name = "";
	
	
	public Person() {
		super();
	}
	
	public static class NameComparator implements Comparator<Person>{

		@Override
		public int compare(Person o1, Person o2) {
			return o1.getName().compareTo(o2.getName());
		}
		
	}
	public Person(int personId, String name) {
		super();
		this.personId = personId;
		this.name = name;
	}


	public int getPersonId() {
		return personId;
	}
	public String getName() {
		return name;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public void setName(String name) {
		this.name = name;
	}


	@Override
	public int hashCode() {
		return Objects.hash(name, personId);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(name, other.name) && personId == other.personId;
	}



	@Override
	public int compareTo(Person o) {
		/*int  result = -(personId - o.personId);
		int value = 0;
		if (result > 0) {
			value = 1;
		}else if (result <0 ) {
			value = -1;
		}*/
		return name.compareTo(o.name);
	}


	@Override
	public String toString() {
		return "Person [personId=" + personId + ", name=" + name + "]";
	}
	

}
