package bloodbank.ifaces;

import java.util.List;

import bloodbank.pojos.Donor;

public interface DonorManager {
	
	public void insertDonor(Donor d);
	public Donor getDonor(int id);
	public void assignDonorToNurse(int donorId, int nurseId);	
	
	public List<Donor> getDonorsByName(String name);
	public List<Donor> getListOfDonors(int nurseId);
	
	public void removeDonor(int id);
}
