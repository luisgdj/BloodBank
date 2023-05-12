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
import bloodbank.pojos.*;

public class JDBCBloodManager implements BloodManager{

	private ConnectionManager conMan;
	private Connection c;
	
	public JDBCBloodManager(ConnectionManager conMan) {
		this.conMan = conMan;
		this.c = conMan.getConnection();
	}
	
	@Override
	public void insertBloodDonation(Blood b) {
		try {
			Statement s = c.createStatement(); //cuando haya una insert se usa statement 
			String sql = "INSERT INTO blood (amount, fecha, donor, donee) VALUES ('" + b.getAmount() + "','"
					+ b.getFecha() + "'," + b.getDonor()  + "'," + b.getDonee() + ")";
			s.execute(sql);
			s.close(); //las conexiones se cierran asi
			
		} catch (SQLException e) { //poner siempre esta excepcion cuando creemos una sql
			System.out.println("Database exception");
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Blood> getDonations(int donorId) {
		
		List<Blood> list = new ArrayList<Blood>();
		try {
			String sql = "SELECT * FROM blood WHERE donor_id = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, "" + donorId);
			ResultSet rs = p.executeQuery();
			
			while (rs.next()) {
				Integer id = rs.getInt("id");
				Integer amount = rs.getInt("amount");
				LocalDate date = (LocalDate) rs.getObject("collection_date");
				Integer donor_id = rs.getInt("donor_id");
				Donor donor = conMan.getDonorMan().getDonor(donor_id);
				Integer donee_id = rs.getInt("donee_id");
				Donee donee = conMan.getDoneeMan().getDonee(donee_id);
				
				Blood b = new Blood(id, amount, date, donor, donee);
				// IMPORTANT: I don't have the dogs
				// Add the Owner to the list
				list.add(b);
			}
			c.close();
			return list;
			
		} catch (SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Float getAmountOfBlood(String bloodType) {

		try {
			String sql = "SELECT SUM(amount) FROM blood WHERE id = (SELECT id FROM donor WHERE blood_type = ?)";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, bloodType);
			
			ResultSet rs = p.executeQuery();
			float amount = rs.getFloat(1);//1 refers to the first column
			
			return amount;

		} catch(SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Integer getNumberOfDonations(String bloodType) {

		try {
			String sql = "SELECT COUNT(id) FROM blood WHERE id = (SELECT id FROM donor WHERE blood_type = ?)";
			PreparedStatement p = c.prepareStatement(sql);
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
	public List<Integer> getListOfIds(String type) {
		
		List<Integer> ids = new ArrayList<Integer>();
		try {
			String sql = "SELECT id FROM blood WHERE donor_id = (SELECT id FROM donor WHERE blood_type = ?)";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, type);
			
			ResultSet rs = p.executeQuery();
			int cont = 0;
			while (rs.next()) {
				int id = rs.getInt(cont);
				ids.add(id);
				cont++;
			}
			return ids;
			
		}catch(SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Float> getListOfAmounts(String bloodType) {

		List<Float> amounts = new ArrayList<Float>();
		try {
			String sql = "SELECT amount FROM blood WHERE donor_id = (SELECT id FROM donor WHERE blood_type = ?)";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, bloodType);
			
			ResultSet rs = p.executeQuery();
			int cont = 0;
			while (rs.next()) {
				float amount = rs.getFloat(cont);
				amounts.add(amount);
				cont++;
			}
			return amounts;
			
		}catch(SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public void deleteDonation(int id) {
		
		try {
			String sql = "DELETE FROM blood WHERE id = ? ";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, id);
			p.executeUpdate();
			p.close();	
		
		}catch(SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();	
		}
	}
	
	@Override
	public void updateBloodInDonation(int id, float amount) {
		
		try {
			String sql = "UPDATE blood SET amount = ? WHERE id = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setFloat(1, amount);
			p.setInt(2, id);
			p.close();
			
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
	}

	@Override
	public List<Blood> getTransfusions(int doneeId) {
		// TODO Auto-generated method stub
		return null;
	}
}