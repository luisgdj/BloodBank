package bloodbank.ifaces;

import java.util.List;

import bloodbank.pojos.Donee;
import bloodbank.pojos.Donor;
import bloodbank.pojos.Nurse;

public interface DoneeManager {
	
	public void insertDonee(Donee d);
	public void removeDonee(int id);
	public void updateDoneeBloodNeeded(int donee_id, float amount); 
	public void assignDoneeToNurse(int doneeId, int nurseId);
	
	public Donee getDonee(int id);
	public List<Donee> getDoneesByName(String name);
	public List<Donee> getListOfDonees(int id);
}
