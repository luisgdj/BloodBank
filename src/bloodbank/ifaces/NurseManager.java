package bloodbank.ifaces;

import java.util.List;

import bloodbank.pojos.Contract;
import bloodbank.pojos.Nurse;

public interface NurseManager {

	public void insertNurse(Nurse nurse);
	public void updateContract(int contractId, int nurseId);
	
	public Nurse getNurse(int id);
	public Nurse getNurseByEmail(String email);
	
	public List<Nurse> getNursesByName(String name);
	public List<Nurse> getListOfNursesOfContract(int contractId);
	public List<Nurse> getListOfNursesOfDonor(int donorId);
	public List<Nurse> getListOfNursesOfDonee(int doneId);
}

