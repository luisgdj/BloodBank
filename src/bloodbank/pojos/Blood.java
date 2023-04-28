package bloodbank.pojos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Blood implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int amount;
	private LocalDate fecha;
	private Donor donor;
	private Donee donee;
	
	public Blood() {
		super();
	}

	public Blood(int id, int amount, LocalDate fecha, Donor donorId) {
		super();
		this.id = id;
		this.amount = amount;
		this.fecha = fecha;
		this.donor = donor;
		this.donee = null;
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