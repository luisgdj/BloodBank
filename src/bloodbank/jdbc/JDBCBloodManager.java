 package bloodbank.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;

import bloodbank.ifaces.BloodManager;
import bloodbank.pojos.Blood;
import bloodbank.pojos.Contract;
import bloodbank.pojos.Donor;
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
	/*public void List<Blood> showDonations(int donorId) {	//tutoria con esto entero
		
		try {
			String sql = "SELECT donor.amount, donor.date,donor.name, donee.name FROM donor JOIN blood ON blood.donor_id = donor.id"
					  +  "JOIN donee ON blood.donee_id= donee.id"
					  +	  "WHERE donee.blood_type= donor.blood_type";
			PreparedStatement p = connection.prepareStatement(sql);
			ResultSet rs = p.executeQuery();
			
			while (rs.next()) {
			Integer amount = rs.getInt(1);
			Date date = rs.getDate(2);
			Donor donor = getDonor(donorId);
			Integer donee = rs.getInt(3);
			
			Blood b = new Blood(donorId,amount,date,donor, donee);
			}
			
			//System.out.println(b.toString());
			connection.close();
		}
		
		catch (SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
		}
	}
*/
	@Override
	public void showBloodByBloodType() {

		try {
			String sql = "SELECT d.blood_type, b.SUM(amount) "
					+ "FROM donor AS d JOIN blood AS b ON d.blood_id = b.id "
					+ "GROUP BY d.blood_type ";
			PreparedStatement p = connection.prepareStatement(sql);
			ResultSet rs = p.executeQuery();
			
			//String blood_type = rs.getString(1); //ponemos un 1 ya que cuando son joins no ponemos el nombre, se pone la posicion de la query en la que está el atriburto
			//float amount = rs.getFloat(2);
			connection.close();

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
			connection.close();

		}catch(SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
		}
	}
	
	@Override
	public void retreiveBlood(int amount) {
		// TODO Auto-generated method stub
		
		
	}
	
	public Donor getDonor (int id) {
		try {
			String sql = "SELECT * FROM donor WHERE id = ?";
			PreparedStatement p = connection.prepareStatement(sql);
			p.setString(1, "" + id); 
			ResultSet rs = p.executeQuery();
			
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			String bloodType = rs.getString("blood_type");
			Date dob = rs.getDate("dob");
			long ssn = rs.getLong("ssn");
			
			return new Donor(name,surname,bloodType,dob,ssn);
			connection.close();

		}catch(SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
			
		}
		return null;
	}

}
