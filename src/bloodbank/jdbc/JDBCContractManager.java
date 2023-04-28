package bloodbank.jdbc;

import java.sql.Connection;

import bloodbank.ifaces.ContractManager;
import bloodbank.pojos.Contract;

public class JDBCContractManager implements ContractManager{
	
	private Connection c;
	
	public JDBCContractManager(Connection c) {
		this.c = c;
	}
	

	@Override
	public void insertContract(Contract c) {
		// TODO Auto-generated method stub
		
	}

}
