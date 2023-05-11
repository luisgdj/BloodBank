package bloodbank.ifaces;

import bloodbank.pojos.*;

public interface BloodManager {

	public void insertBloodDonation(Blood b);	//donation (donor menu)
<<<<<<< HEAD
	public void showDonations (int donorId); //view donations (donor menu)
	public void showBloodByBloodType(); //show available blood (nurse menu)
	public void showBloodTotalAmount();
	public void retreiveBlood (int amount, String bloodType); //retreive blood (nurse menu)
	
	

=======
	public List<Blood> getDonations (int donorId); //view donations (donor menu)
	public Float getAmountOfBlood(String bloodType); //show available blood (nurse menu)
	public Integer getNumberOfDonations(String bloodType);
	public void deleteDonation (int id);
	public List<Float> getListOfAmounts(String bloodType);
	public List<Integer> getListOfIds(String type);
	public void updateBloodInDonation(int id, float retrivalAmount);
>>>>>>> branch 'master' of https://github.com/luisgdj/BloodBank
}
