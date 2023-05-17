package bloodbank.ifaces;

import java.util.List;

import bloodbank.pojos.Contract;
import bloodbank.pojos.Nurse;

public interface NurseManager {

	public void insertNurse(Nurse nurse);
	public void updateContract(int contractId, int nurseId);
	public List<Nurse> searchNurseByName(String name);
	
	public Nurse getNurse(int id);
	public List<Nurse> getListOfNursesOfContract(int contractId);
	public List<Nurse> getListOfNursesOfDonor(int donorId);
	public List<Nurse> getListOfNursesOfDonee(int doneId);
	
}

