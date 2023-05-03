package bloodbank.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bloodbank.ifaces.BloodManager;
import bloodbank.pojos.Blood;
import bloodbank.pojos.Contract;
import bloodbank.pojos.Nurse;

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
	public void showBloodByBloodType() {

		try {
			String sql = "SELECT d.blood_type, b.SUM(amount) "
					+ "FROM donor AS d JOIN blood AS b ON d.blood_id = b.id "
					+ "GROUP BY d.blood_type ";
			PreparedStatement p = c.prepareStatement(sql);
			ResultSet rs = p.executeQuery();
			
			String blood_type = rs.getString("blood_type");
			float amount = rs.getFloat("amount");
			//imprimir datos???

		}catch(SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
		}
	}
	
	@Override
	public void showBloodTotalAmount() {
		
		try {
			String sql = "SELECT d.blood_type, b.SUM(amount) "
					+ "FROM donor AS d JOIN blood AS b ON d.blood_id = b.id "
					+ "GROUP BY d.blood_type ";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, "" + option); //ponemos 1 porque el primer atributo en la clase nurse es name que es por lo que lo queremos buscar
			ResultSet rs = p.executeQuery();

		}catch(SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
		}
	}
	
	@Override
	public void retreiveBlood(int amount) {
		// TODO Auto-generated method stub
		
	}

}
