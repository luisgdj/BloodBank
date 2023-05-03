package bloodbank.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Contract implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer duration;
	private Integer salary;
	private Nurse nurse;

	public Contract() {
		super();
	}

	public Contract(int id, int duration, int salary) {
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

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Nurse getNurse() {
		return nurse;
	}

	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
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
