package bloodbank.ifaces;

import java.util.List;

import bloodbank.pojos.*;

public interface BloodManager {

	public void insertBloodDonation(Blood b);	//donation (donor menu)
	public List<Blood> getDonations (int donorId); //view donations (donor menu)
	public Float getAmountOfBlood(String bloodType); //show available blood (nurse menu)
	public Integer getNumberOfDonations(String bloodType);
	public void retreiveBlood(float amount, float limit, String blood_type); //retreive blood (nurse menu)

}
