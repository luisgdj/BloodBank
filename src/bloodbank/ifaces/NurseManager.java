package bloodbank.ifaces;

import java.util.List;

import bloodbank.pojos.Contract;
import bloodbank.pojos.Nurse;

public interface NurseManager {

	public void insertNurse(Nurse nurse); //Register nurse (manager menu)
	public List<Nurse> searchNurseByName(String name);	//Select nurse (manager menu)
	public Nurse getNurse(int id);	//Check nurse info (select nurse) 
	public void updateContract(int contractId, int nurseId); //change contract (check nurse info)
	
	public List<Nurse> getListOfNursesOfContract(int contractId);
	public List<Nurse> getListOfNursesOfDonor(int donorId);
	public List<Nurse> getListOfNursesOfDonee(int doneId);
	
}

