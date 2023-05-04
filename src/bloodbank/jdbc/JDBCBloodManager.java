 package bloodbank.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import bloodbank.ifaces.BloodManager;
import bloodbank.pojos.Blood;
import bloodbank.pojos.Contract;
import bloodbank.pojos.Nurse;

public class JDBCBloodManager implements BloodManager{

	private Connection connection;
	
	public JDBCBloodManager(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public void insertBloodDonation(Blood b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showDonations(int donorId) {
		
		try {
			String sql = "SELECT donor.name, donor.blood_type, donee.name FROM donor JOIN blood ON blood.donor_id = donor.id"
					  +  "JOIN donee ON blood.donee_id= donee.id"
					  +	  "WHERE donee.blood_type= donor.blood_type";
			PreparedStatement p = connection.prepareStatement(sql);
			ResultSet rs = p.executeQuery();
			String donorName = rs.getString(1);
			String bloodType = rs.getString(2);
			String doneeName = rs.getString(3);
			
			System.out.println(b.toString());
			connection.close();
		}
		
		catch (SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
		}
	}

	@Override
	public void showBloodByBloodType() {

		try {
			String sql = "SELECT d.blood_type, b.SUM(amount) "
					+ "FROM donor AS d JOIN blood AS b ON d.blood_id = b.id "
					+ "GROUP BY d.blood_type ";
			PreparedStatement p = connection.prepareStatement(sql);
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
			PreparedStatement p = connection.prepareStatement(sql);
			//p.setString(1, "" + option); //ponemos 1 porque el primer atributo en la clase nurse es name que es por lo que lo queremos buscar
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
