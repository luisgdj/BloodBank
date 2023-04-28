package bloodbank.jdbc;

import java.sql.Connection;

import bloodbank.ifaces.BloodManager;
import bloodbank.pojos.Blood;

public class JDBCBloodManager implements BloodManager{

	private Connection c;
	
	public JDBCBloodManager(Connection c) {
		this.c = c;
	}
	
	@Override
	public void insertBloodDonation(Blood b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showDonations(int donorId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showAvailableBlood() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void retreiveBlood(int amount) {
		// TODO Auto-generated method stub
		
	}

}
