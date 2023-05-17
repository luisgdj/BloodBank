package bloodbank.ifaces;

import java.util.List;

import bloodbank.pojos.Donor;

public interface DonorManager {
	
	public void insertDonor(Donor d);
	public void removeDonor(int id);
	public void assignDonorToNurse(int donorId, int nurseId);	
	public List<Donor> searchDonorByName(String name);
	
	public Donor getDonor(int id);
	public List<Donor> getListOfDonors(int nurseId);
}
