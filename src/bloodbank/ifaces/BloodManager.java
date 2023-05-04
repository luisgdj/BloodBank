package bloodbank.ifaces;

import bloodbank.pojos.*;

public interface BloodManager {

	public void insertBloodDonation(Blood b);	//donation (donor menu)
	public void showDonations (int donorId); //view donations (donor menu)
	public void showBloodByBloodType(); //show available blood (nurse menu)
	public void showBloodTotalAmount();
	public void retreiveBlood (int amount); //retreive blood (nurse menu)
	
	

}
