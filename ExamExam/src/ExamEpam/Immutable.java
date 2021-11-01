package ExamEpam;

import java.util.Objects;

public class Immutable {
	private int Id;
	private String name;
	public Immutable(int id, String name) {
		super();
		Id = id;
		this.name = name;
	}
	public int getId() {
		return Id;
	}
	public String getName() {
		return name;
	}
	@Override
	public int hashCode() {
		return Objects.hash(Id, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Immutable other = (Immutable) obj;
		return Id == other.Id && Objects.equals(name, other.name);
	}
	
	
	
}
