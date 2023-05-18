 package bloodbank.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			String sql = "INSERT INTO blood (amount, fecha, donor_id, donee_id)"
					+ " VALUES (?,?,?,?)";
			PreparedStatement p = c.prepareStatement(sql);
			p.setFloat(1, b.getAmount());
			p.setObject(2, (LocalDate) b.getFecha());
			p.setInt(3, b.getDonor().getId());
			p.setInt(4, b.getDonee().getId());
			p.close();
			
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
			p.close();
			
			while (rs.next()) {
				Integer id = rs.getInt("id");
				Integer amount = rs.getInt("amount");
				LocalDate date = (LocalDate) rs.getObject("collection_date");
				Integer donor_id = rs.getInt("donor_id");
				Donor donor = conMan.getDonorMan().getDonor(donor_id);
				Integer donee_id = rs.getInt("donee_id");
				Donee donee = conMan.getDoneeMan().getDonee(donee_id);
				
				Blood b = new Blood(id, amount, date, donor, donee);
				list.add(b);
			}
			rs.close();
			return list;
			
		} catch (SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<Blood> getTransfusions(int doneeId) {
		
		List<Blood> list = new ArrayList<Blood>();
		try {
			String sql = "SELECT * FROM blood WHERE donee_id = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, "" + doneeId);
			ResultSet rs = p.executeQuery();
			p.close();
			
			while (rs.next()) {
				Integer id = rs.getInt("id");
				Integer amount = rs.getInt("amount");
				LocalDate date = (LocalDate) rs.getObject("collection_date");
				Integer donor_id = rs.getInt("donor_id");
				Donor donor = conMan.getDonorMan().getDonor(donor_id);
				Integer donee_id = rs.getInt("donee_id");
				Donee donee = conMan.getDoneeMan().getDonee(donee_id);
				
				Blood b = new Blood(id, amount, date, donor, donee);
				list.add(b);
			}
			rs.close();
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
			float amount = rs.getFloat(1);
			p.close();
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
			p.close();
			
			int totalDonations = rs.getInt("id");
			rs.close();
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
			p.close();
			
			int cont = 0;
			while (rs.next()) {
				int id = rs.getInt(cont);
				ids.add(id);
				cont++;
			}
			rs.close();
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
			p.close();
			
			int cont = 0;
			while (rs.next()) {
				float amount = rs.getFloat(cont);
				amounts.add(amount);
				cont++;
			}
			rs.close();
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
	public void updateBloodDoneeId(int id, int donee_id) {
		
		try {
			String sql = "UPDATE blood SET donee_id = ? WHERE id = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, donee_id);
			p.setInt(2, id);
			p.close();
			
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
	}
}