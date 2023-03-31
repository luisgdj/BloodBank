package bloodbank.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Contract implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String duration;
	private int salary;

	public Contract() {
		super();
	}

	public Contract(int id, String duration, int salary) {
		super();
		this.id = id;
		this.duration = duration;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
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
		Contract other = (Contract) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Contract [id=" + id + ", duration=" + duration + ", salary=" + salary + "]";
	}
}
