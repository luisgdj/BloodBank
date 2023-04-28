package bloodbank.jdbc;

import java.sql.Connection;
import java.util.List;

import bloodbank.ifaces.DonorManager;
import bloodbank.pojos.Donor;

public class JDBCDonorManager implements DonorManager{

	private Connection c;
	
	public JDBCDonorManager(Connection c) {
		this.c = c;
	}
	
	@Override
	public void insertDonor(Donor d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectDonor(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeDonor(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void assignDonorToNurse(int donorId, int nurseId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Donor> searchDonorByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
