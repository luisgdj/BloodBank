package bloodbank.ifaces;

import java.util.List;

import bloodbank.pojos.Contract;

public interface ContractManager {

	public void insertContract(Contract c);	//stablish contract (manager menu)
	
	public Contract getContract(int id);
	public List<Contract> getListOfContracts();
}
