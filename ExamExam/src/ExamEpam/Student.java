package ExamEpam;

import java.util.Objects;

public class Student extends Person{
	private int id;
	private String name;
	private int age;
	private int group;
	private Adress adress;
	
	public class Adress{
		private String city;
		private String street;
		private int houseNumber;
		private int apartmentNumber;
		private long phoneNumber;
		
		
		public Adress() {
		}
		
		public void Action() {
		id = 200;
		}

		public String getCity() {
			return city;
		}

		public String getStreet() {
			return street;
		}

		public int getHouseNumber() {
			return houseNumber;
		}

		public int getApartmentNumber() {
			return apartmentNumber;
		}

		public long getPhoneNumber() {
			return phoneNumber;
		}
		
		
	}

	//Student.Adress adress1 = new Student().new Adress();
	
	
	public Student() {	
	}
	
	public void Operation() {
		
		adress.city = "minsk";
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public int getGroup() {
		return group;
	}

	public Adress getAdress() {
		return adress;
	}

	

}
