package hospital;

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
	private Contract contract_id;

	public Nurse() {
		super();
	}

	public Nurse(int id, String name, String surname, Contract contract_id) {
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

	public Contract getContract_id() {
		return contract_id;
	}

	public void setContract_id(Contract contract_id) {
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

}
