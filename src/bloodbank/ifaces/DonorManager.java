package bloodbank.ifaces;

import java.util.List;

import bloodbank.pojos.Donor;
import bloodbank.pojos.Nurse;

public interface DonorManager {
	
	public void insertDonor(Donor d);
	public void removeDonor(int id);
	public void assignDonorToNurse(int donorId, int nurseId);	
	
	public Donor getDonor(int id);
	public List<Donor> getDonorsByName(String name);
	public List<Donor> getListOfDonors(int nurseId);
}
