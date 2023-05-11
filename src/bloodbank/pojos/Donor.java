package bloodbank.pojos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import bloodbank.xml.utils.LocalDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Donor")
@XmlType(propOrder = { "name", "surname", "bloodType","dob","ssn", "donations" })
public class Donor implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@XmlTransient
	private Integer id;
	@XmlElement
	private String name;
	@XmlElement
	private String surname;
	@XmlElement
	private String bloodType;
<<<<<<< HEAD
	private Date dob;
=======
	@XmlElement
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	private LocalDate dob;
	@XmlElement
>>>>>>> branch 'master' of https://github.com/luisgdj/BloodBank
	private long ssn;
	@XmlElement(name = "Blood")
    @XmlElementWrapper(name = "Donations")
	private List<Blood> donations;
	 
	public Donor() {
		super();
	}
	

	public Donor(String name, String surname, String bloodType, Date dob, long ssn) {
		super();
		this.name = name;
		this.surname = surname;
		this.bloodType = bloodType;
		this.dob = dob;
		this.ssn = ssn;
	}


	public Donor(int id, String name, String surname, String bloodType, Date dob, long ssn) {
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
	
	public Date getDob() {
		return dob;
	}
	
	public void setDob(Date dob) {
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