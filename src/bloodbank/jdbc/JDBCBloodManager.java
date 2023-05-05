 package bloodbank.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bloodbank.ifaces.BloodManager;
import bloodbank.ifaces.DoneeManager;
import bloodbank.ifaces.DonorManager;
import bloodbank.pojos.*;

public class JDBCBloodManager implements BloodManager{

	private Connection connection;
	private static DonorManager donorMan;
	private static DoneeManager doneeMan;
	
	public  JDBCBloodManager(Connection connection, DonorManager donorMan, DoneeManager doneeMan) {
		this.connection = connection;
		this.donorMan = donorMan;
		this.doneeMan = doneeMan;		
	}
	
	@Override
	public void insertBloodDonation(Blood b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Blood> getDonations(int donorId) {
		
		List<Blood> list = new ArrayList<Blood>();
		try {
			String sql = "SELECT * FROM blood WHERE donor_id = ?";
			PreparedStatement p = connection.prepareStatement(sql);
			p.setString(1, "" + donorId);
			ResultSet rs = p.executeQuery();
			
			while (rs.next()) {
				Integer id = rs.getInt("id");
				Integer amount = rs.getInt("amount");
				LocalDate date = (LocalDate) rs.getObject("collection_date")
				Integer donor_id = rs.getInt("donor_id");
				Donor donor = donorMan.getDonor(donor_id);
				Integer donee_id = rs.getInt("donee_id");
				Donee donee = doneeMan.getDonee(donee_id);
				
				Blood b = new Blood(id, amount, date, donor, donee);
				// IMPORTANT: I don't have the dogs
				// Add the Owner to the list
				list.add(o);
			}
			connection.close();
			return ;
		}
		
		catch (SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Float getAmountOfBlood(String bloodType) {

		try {
			String sql = "SELECT SUM(amount) FROM blood WHERE id = (SELECT id FROM donor WHERE blood_type = ?)";
			PreparedStatement p = connection.prepareStatement(sql);
			p.setString(1, bloodType);
			
			ResultSet rs = p.executeQuery();
			float amount = rs.getFloat("amount");
			
			return amount;

		}catch(SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Integer getNumberOfDonations(String bloodType) {

		try {
			String sql = "SELECT COUNT(id) FROM blood WHERE id = (SELECT id FROM donor WHERE blood_type = ?)";
			PreparedStatement p = connection.prepareStatement(sql);
			p.setString(1, bloodType);
			
			ResultSet rs = p.executeQuery();
			int totalDonations = rs.getInt("id");
			
			
			return totalDonations;

		}catch(SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public void retreiveBlood(float reteivalAmount, float limit, String bloodType) {
		
		float totalStorage = getAmountOfBlood(bloodType);
		
		if(totalStorage <= limit) {
			for(int i = 0; i < getNumberOfDonations(bloodType); i++) {
				//recorre todas las donaciones con el mismo tipo de sangre
				//no sabemos como acceder a cada
				
			}
			
		}else {
			System.out.println("Not allowed. Transfussion exceeds de blood limit.");	
		}
	}
	
	@Override
	public void updateDonation(int id) {

		try {
			String sql = "SELECT COUNT(id) FROM blood WHERE id = (SELECT id FROM donor WHERE blood_type = ?)";
			PreparedStatement p = connection.prepareStatement(sql);
			p.setString(1, bloodType);
			
			ResultSet rs = p.executeQuery();
			int totalDonations = rs.getInt("id");
			
			return totalDonations;

		}catch(SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
			return null;
		}
	}
	
}
