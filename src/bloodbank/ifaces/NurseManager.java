package bloodbank.ifaces;

import java.util.List;

import bloodbank.pojos.Contract;
import bloodbank.pojos.Nurse;

public interface NurseManager {

	public void insertNurse(Nurse nurse); //Register nurse (manager menu)
	public List<Nurse> searchNurseByName(String name);	//Select nurse (manager menu)
	public void showNurse(int id);	//Check nurse info (select nurse) 
	public void assignContractToNurse(int contractId, int nurseId); //change contract (check nurse info)
}
