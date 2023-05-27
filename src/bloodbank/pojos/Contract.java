package bloodbank.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Contract")
@XmlType(propOrder = { "duration", "salary" })
public class Contract implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlTransient
	private Integer id;
	@XmlElement
	private Integer duration;
	@XmlElement
	private float salary;
	
	@XmlTransient
	private List<Nurse> nurses;

	public Contract() {
		super();
	}

	public Contract(int duration, float salary) {
		super();
		this.duration = duration;
		this.salary = salary;
		this.nurses = new ArrayList<Nurse>();
	}

	public Contract(int id, int duration, float salary, List<Nurse> nurses) {
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

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public List<Nurse> getNurses() {
		return nurses;
	}

	public void setNurses(List<Nurse> nurses) {
		this.nurses = nurses;
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
		return id + ":\n   Duration: " + duration + " months\n   Salary: " + salary + "$";
	}
}