package bloodbank.pojos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import bloodbank.xml.utils.LocalDateAdapter;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Blood")
@XmlType(propOrder = { "amount", "fecha", "donor", "donee" })
public class Blood implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@XmlTransient
	private Integer id;
	@XmlElement
	private Integer amount;
	@XmlElement
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	private LocalDate fecha;
	@XmlElement(name = "Donor")
	private Donor donor;
	@XmlElement(name = "Donee")
	private Donee donee;
	
	public Blood() {
		super();
	}
 
	public Blood(int amount, LocalDate fecha, Donor donor) {
		super();
		this.amount = amount;
		this.fecha = fecha;
		this.donor = donor;
	}

	public Blood(int id, int amount, LocalDate fecha, Donor donor, Donee donee) {
		super();
		this.id = id;
		this.amount = amount;
		this.fecha = fecha;
		this.donor = donor;
		this.donee = donee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	public Donor getDonor() {
		return donor;
	}

	public void setDonor(Donor donor) {
		this.donor = donor;
	}

	public Donee getDonee() {
		return donee;
	}

	public void setDonee(Donee donee) {
		this.donee = donee;
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
		Blood other = (Blood) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Blood [id=" + id + ", amount=" + amount + ", fecha=" + fecha + ", donor=" + donor.toString() + ", donee="
				+ donee.toString() + "]";
	}
}