package bloodbank.ifaces;

import java.util.List;

import bloodbank.pojos.*;

public interface BloodManager {

	public void insertBloodDonation(Blood b);
	public void deleteDonation (int id);
	public void updateBloodDoneeId(int id, int donee_id);
	
	public List<Float> getListOfAmounts(String bloodType);
	public List<Integer> getListOfIds(String type);
	
	public Float getAmountOfBlood(String bloodType); 
	public Integer getNumberOfDonations(String bloodType);
	
	public List<Blood> getDonations (int donorId); //view donations (donor menu)
	public List<Blood> getTransfusions(int doneeId);
}
