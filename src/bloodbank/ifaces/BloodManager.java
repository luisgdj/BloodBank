package bloodbank.ifaces;

import java.util.List;

import bloodbank.pojos.*;

public interface BloodManager {

	public void insertBloodDonation(Blood b);
	public void deleteDonation (int id);
	public void updateBloodDoneeId(int id, int donee_id);
	
	/**
	 * Create the donor id list with the entered blood type
	 * 
	 * @param type of blood
	 * @return  donor id ArrayList with the entered blood type
	 */
	public List<Integer> getListOfIds(String type);
	public List<Float> getListOfAmounts(String bloodType);
	
	public Float getAmountOfBlood(String bloodType); 
	public Integer getNumberOfDonations(String bloodType);
	public Blood getBlood(int id);
	
	public List<Blood> getAllBlood();
	public List<Blood> getDonations (int donorId); //view donations (donor menu)
	public List<Blood> getTransfusions(int doneeId);
	public List<Blood> getBloodsByBloodType(String bloodType);
}
