package bloodbank.ifaces;

import bloodbank.pojos.Donee;

public interface DoneeManager {
	
	public void insertDonee(Donee d);	//register donee (nurse menu)
	public void selectDonee(int id);	//show patinens (check nurse info)
	public void assignDoneeToNurse(int doneeId, int nurseId); 
}
