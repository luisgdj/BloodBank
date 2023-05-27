package bloodbank.pojos;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Nurse")
@XmlType(propOrder = { "name", "surname", "email", "contract"})
public class Nurse implements Serializable {

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
	private String email;
	@XmlElement(name = "Contract")
	private Contract contract;
	
	@XmlTransient
	private List<Donor> donors;
	@XmlTransient
	private List<Donee> donees;

	public Nurse() {
		super();
	}

	public Nurse(String name, String surname, String email, Contract contract) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.contract = contract;
	}

	public Nurse(int id, String name, String surname, String email, Contract contract, List<Donor> donors,
			List<Donee> donees) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.contract = contract;
		this.donors = donors;
		this.donees = donees;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public List<Donor> getDonors() {
		return donors;
	}

	public void setDonors(List<Donor> donors) {
		this.donors = donors;
	}

	public List<Donee> getDonees() {
		return donees;
	}

	public void setDonees(List<Donee> donees) {
		this.donees = donees;
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
		Nurse other = (Nurse) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return " -ID: " + id + "\n -Name: " + name + " " + surname + "\n -Email: " + email + "\n -Contract "
				+ contract.toString();
	}
}
