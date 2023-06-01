package bloodbank.ifaces;

import java.util.List;

import bloodbank.pojos.*;

public interface BloodManager {

	public void insertBloodDonation(Blood b);
	public Blood getBlood(int id);
	
	public List<Blood> getDonations (int donorId);
	public List<Blood> getTransfusions(int doneeId);
	public List<Blood> getBloodByBloodType(String bloodType);
	public Float getTotalAmountOfBlood(String bloodType); 
	
	public void assignDoneeToBlood(int id, int donee_id);
	public void updateBloodStorage(int id, float amount);
}
