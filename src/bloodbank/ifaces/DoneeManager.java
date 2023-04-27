package bloodbank.ifaces;

import bloodbank.pojos.Donee;

public interface DoneeManager {
	
	public void insertDonee(Donee d);
	public void selectDonee(int id);
	public void assignDoneeToNurse(int doneeId, int nurseId);
}
