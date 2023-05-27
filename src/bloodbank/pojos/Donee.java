package bloodbank.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import bloodbank.xml.utils.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Donee")
@XmlType(propOrder = { "name", "surname", "bloodType", "bloodNeeded", "dob", "ssn"})
public class Donee implements Serializable {

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
	@XmlElement
	private Float bloodNeeded;
	@XmlElement
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date dob;
	@XmlElement
	private Long ssn;
	
	@XmlTransient
	private List<Blood> transfusions;
	@XmlTransient
	private List<Nurse> nurses;

	public Donee() {
		super();
	}

	public Donee(String name, String surname, String bloodType, float bloodNeeded, Date dob, long ssn) {
		super();
		this.name = name;
		this.surname = surname;
		this.bloodType = bloodType;
		this.bloodNeeded = bloodNeeded;
		this.dob = dob;
		this.ssn = ssn;
	}

	public Donee(int id, String name, String surname, String bloodType, float bloodNeeded, Date dob, long ssn,
			List<Blood> transfusions, List<Nurse> nurses) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.bloodType = bloodType;
		this.bloodNeeded = bloodNeeded;
		this.dob = dob;
		this.ssn = ssn;
		this.transfusions = transfusions;
		this.nurses = nurses;
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

	public float getBloodNeeded() {
		return bloodNeeded;
	}

	public void setBloodNeeded(float bloodNeeded) {
		this.bloodNeeded = bloodNeeded;
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

	public List<Blood> getTransfusions() {
		return transfusions;
	}

	public void setTransfusions(List<Blood> transfusions) {
		this.transfusions = transfusions;
	}

	public List<Nurse> getNurses() {
		return nurses;
	}

	public void setNurses(List<Nurse> nurses) {
		this.nurses = nurses;
	}

	public void setBloodNeeded(Float bloodNeeded) {
		this.bloodNeeded = bloodNeeded;
	}

	public void setSsn(Long ssn) {
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
		return " -ID: " + id + "\n -Name: " + name + " " + surname + "\n -Blood type: " + bloodType
				+ "\n -Blood needed: " + bloodNeeded + " mL\n -Birth date: " + dob + "\n -SSN: " + ssn;
	}
}
