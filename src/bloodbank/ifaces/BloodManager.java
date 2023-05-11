package bloodbank.ifaces;

import java.util.List;

import bloodbank.pojos.*;

public interface BloodManager {

	public void insertBloodDonation(Blood b);	//donation (donor menu)
	public List<Blood> getDonations (int donorId); //view donations (donor menu)
	public Float getAmountOfBlood(String bloodType); //show available blood (nurse menu)
	public Integer getNumberOfDonations(String bloodType);
	public void retreiveBlood(float retreivalAmount, float amount, String bloodType, float limit); //retreive blood (nurse menu)
	public void deleteDonation (int id);
	public List<Float> getListOfAmounts(String bloodType);
	public List<Integer> getListOfIds(String type);
	public void updateBloodInDonation(int id, float retrivalAmount);
}
