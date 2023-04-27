package bloodbank.pojos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Donee implements Serializable {

	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String surname;
	private String bloodType;
	private int bloodNeeded;
	private LocalDate dob;
	private long ssn;
	
	public Donee() {
		super();
	}

	public Donee(int id, String name, String surname, String bloodType, int bloodNeeded, LocalDate dob, long ssn) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.bloodType = bloodType;
		this.bloodNeeded = bloodNeeded;
		this.dob = dob;
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

	public int getBloodNeeded() {
		return bloodNeeded;
	}

	public void setBloodNeeded(int bloodNeeded) {
		this.bloodNeeded = bloodNeeded;
	}

	public LocalDate getDob() {
		return dob;
	}
	
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public long getSsn() {
		return ssn;
	}

	public void setSsn(long ssn) {
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
		Donee other = (Donee) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Donee [id=" + id + ", name=" + name + ", surname=" + surname + ", bloodType=" + bloodType
				+ ", bloodNeeded=" + bloodNeeded + ", age=" + dob + ", ssn=" + ssn + "]";
	}
}
