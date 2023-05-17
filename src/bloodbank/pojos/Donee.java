package bloodbank.pojos;

import java.io.Serializable;
import java.time.LocalDate;
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
@XmlRootElement(name = "Donee")
@XmlType(propOrder = { "name", "surname", "bloodType", "bloodNeeded","dob","ssn", "transfusions" })
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
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	private LocalDate dob;
	@XmlElement
	private Long ssn;
	@XmlTransient
	private List<Blood> transfusions;
	@XmlTransient
	private List<Nurse> nurses;
	
	
	public Donee() {
		super();
	}
 
	public Donee(String name, String surname, String bloodType, float bloodNeeded, LocalDate dob, long ssn) {
		super();
		this.name = name;
		this.surname = surname;
		this.bloodType = bloodType;
		this.bloodNeeded = bloodNeeded;
		this.dob = dob;
		this.ssn = ssn;
	}
	
	public Donee(int id, String name, String surname, String bloodType, float bloodNeeded, LocalDate dob, long ssn, List<Blood> transfusions) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.bloodType = bloodType;
		this.bloodNeeded = bloodNeeded;
		this.dob = dob;
		this.ssn = ssn;
		this.transfusions = transfusions;
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

	public List<Blood> getTransfusions() {
		return transfusions;
	}

	public void setTransfusions(List<Blood> transfusions) {
		this.transfusions = transfusions;
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
