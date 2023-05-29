package bloodbank.ifaces;

import java.util.List;

import bloodbank.pojos.Nurse;

public interface NurseManager {

	public void insertNurse(Nurse nurse);
	public Nurse getNurse(int id);
	public Nurse getNurseByEmail(String email);
	
	public List<Nurse> getAllNurses();
	public List<Nurse> getNursesByName(String name);
	public List<Nurse> getListOfNursesOfContract(int contractId);
	public List<Nurse> getListOfNursesOfDonor(int donorId);
	public List<Nurse> getListOfNursesOfDonee(int doneeId);
	
	public void assignContractToNurse(int contractId, int nurseId);
}

