package bloodbank.ifaces;

import java.util.List;

import bloodbank.pojos.Donor;

public interface DonorManager {
	
	public void insertDonor(Donor d); //register donor (nurse menu)
	public void removeDonor(int id);	//eliminate donor (check donor info)
	public void assignDonorToNurse(int donorId, int nurseId);	
	public List<Donor> searchDonorByName(String name); //select nurse (nurse menu)
	public Donor getDonor(int id);	//select donor (nurse menu)
	public List<Donor> getListOfDonors(int nurseId);
}
