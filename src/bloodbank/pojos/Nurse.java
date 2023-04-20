package bloodbank.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Nurse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String surname;
	private int contract_id; //no sabemos si deberia ser de timpo int o contract

	public Nurse() {
		super();
	}

	public Nurse(int id, String name, String surname, int contract_id) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.contract_id = contract_id;
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

	public int getContract_id() {
		return contract_id;
	}

	public void setContract_id(int contract_id) {
		this.contract_id = contract_id;
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
		return "Nurse [id=" + id + ", name=" + name + ", surname=" + surname + ", contract_id=" + contract_id + "]";
	}
	
	

}
