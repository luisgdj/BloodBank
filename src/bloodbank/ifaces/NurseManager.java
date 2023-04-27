package bloodbank.ifaces;

import java.util.List;

import bloodbank.pojos.Contract;
import bloodbank.pojos.Nurse;

public interface NurseManager {

	public void insertNurse(Nurse nurse);
	public List<Nurse> searchNurseByName(String name);
	public void selectNurse(int id);
	public void assignContractToNurse(int contractId, int nurseId);
}
