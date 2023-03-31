package bloodbank.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Donor implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String surname;
	private String bloodType;
	private int age;
	private long ssn;
	
	public Donor() {
		super();
	}

	public Donor(int id, String name, String surname, String bloodType, int age, double ssn) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.bloodType = bloodType;
		this.age = age;
		this.ssn = ssn;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getBloodType() {
		return bloodType;
	}
	
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public double getSsn() {
		return ssn;
	}
	
	public void setSsn(double ssn) {
		this.ssn = ssn;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Donor other = (Donor) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Donor [id=" + id + ", name=" + name + ", surname=" + surname + ", bloodType=" + bloodType + ", age="
				+ age + ", ssn=" + ssn + "]";
	}
	
}