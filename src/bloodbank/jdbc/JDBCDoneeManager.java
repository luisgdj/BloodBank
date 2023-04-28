package bloodbank.jdbc;

import java.sql.Connection;

import bloodbank.ifaces.DoneeManager;
import bloodbank.pojos.Donee;

public class JDBCDoneeManager implements DoneeManager{

	private Connection c;
	
	public JDBCDoneeManager(Connection c) {
		this.c = c;
	}
	
	@Override
	public void insertDonee(Donee d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectDonee(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void assignDoneeToNurse(int doneeId, int nurseId) {
		// TODO Auto-generated method stub
		
	}

}
