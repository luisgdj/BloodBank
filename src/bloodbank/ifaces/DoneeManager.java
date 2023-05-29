package bloodbank.ifaces;

import java.util.List;

import bloodbank.pojos.Donee;

public interface DoneeManager {
	
	public void insertDonee(Donee d);
	public Donee getDonee(int id);
	public void assignDoneeToNurse(int doneeId, int nurseId);
	
	public List<Donee> getDoneesByName(String name);
	public List<Donee> getListOfDonees(int id);
	
	public void updateDoneeBloodNeeded(int donee_id, float amount); 
	public void removeDonee(int id);
}
