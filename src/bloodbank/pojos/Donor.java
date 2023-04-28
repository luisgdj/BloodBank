package bloodbank.pojos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Donor implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String surname;
	private String bloodType;
	private LocalDate dob;
	private long ssn;
	private List<Blood> donations;
	 
	public Donor() {
		super();
	}

	public Donor(int id, String name, String surname, String bloodType, LocalDate dob, long ssn) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.bloodType = bloodType;
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

	public List<Blood> getDonations() {
		return donations;
	}

	public void setDonations(List<Blood> donations) {
		this.donations = donations;
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
				+ dob + ", ssn=" + ssn + "]";
	}
	
}