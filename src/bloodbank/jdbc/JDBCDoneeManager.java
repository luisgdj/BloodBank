package bloodbank.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bloodbank.ifaces.DoneeManager;
import bloodbank.pojos.Blood;
import bloodbank.pojos.Donee;

public class JDBCDoneeManager implements DoneeManager{

	private ConnectionManager conMan;
	private Connection c;
	
	public JDBCDoneeManager(ConnectionManager conMan) {
		this.conMan = conMan;
		this.c = conMan.getConnection();
	}
	
	@Override
	public void insertDonee(Donee d) {
	
		try {
			String sql = "INSERT INTO donee "
					+ "(name, surname, blood_type, blood_needed, dob, ssn)"
					+ " VALUES (?,?,?,?,?,?)";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, d.getName());
			p.setString(2, d.getSurname());
			p.setString(3, d.getBloodType());
			p.setFloat(4, d.getBloodNeeded());
			p.setObject(5, (LocalDate) d.getDob());
			p.setLong(6, d.getSsn());
			p.close();
			
		} catch (SQLException e) {
			System.out.println("Database exception");
			e.printStackTrace();
		}
	}
	
	@Override
	public void removeDonee(int id) {
		
		try {
			String sql = "DELETE FROM donee WHERE id = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, id);
			p.close();
			
		}catch(SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
		}
	}

	public Donee getDonee(int id) {
		
		try {
			String sql =  "SELECT * FROM donee where id = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, id); 
			ResultSet rs = p.executeQuery();
			p.close();
			
			String name = rs.getString("name");
			String surname= rs.getString("surname");
			String bloodType = rs.getString("blood_type");
			Integer bloodNeeded = rs.getInt("blood_needed");
			LocalDate dob = (LocalDate) rs.getObject("dob");
			long ssn = rs.getLong("ssn");
			List<Blood> transfusions = conMan.getBloodMan().getTransfusions(id);
			
			Donee d = new Donee(id,name,surname,bloodType,bloodNeeded,dob,ssn,transfusions);
			rs.close();
			return d;
		
		}catch(SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
			return null;
		}
	} 

	@Override
	public void assignDoneeToNurse(int doneeId, int nurseId) {
		try {
			String sql = "INSERT INTO nurse_donee (doneeId, nurseId) VALUES (?,?)";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, doneeId); 
			p.setInt(2, nurseId);
			p.executeUpdate();
			p.close();

		}catch(SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();	
		}
	}

	public List<Donee> getListOfDonees(int nurseId) {
		
		List<Donee> list = new ArrayList<Donee>();
		try {
			String sql = "SELECT * FROM nurse AS n JOIN nurse_donee AS nd ON n.id = nd.nurse_id"
					+ " JOIN donee AS d ON d.id = nd.donee_id WHERE n.id = ? ";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, "" + nurseId);
			ResultSet rs = p.executeQuery();
			p.close();
			
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String bloodType = rs.getString("blood_type");
				float bloodNeeded = rs.getFloat("blood_needed");
				LocalDate dob = (LocalDate) rs.getObject("dob");
				Long ssn = rs.getLong("ssn");
				List<Blood> transfussions = conMan.getBloodMan().getTransfusions(id);
				
				Donee donee = new Donee(id,name,surname,bloodType,bloodNeeded,dob,ssn,transfussions);
				list.add(donee);
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
	public void updateDoneeBloodNeeded(int donee_id, float amount) {
		
		try {
			String sql = "UPDATE donee SET blood_needed = ? WHERE id = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setFloat(1, amount);
			p.setInt(2, donee_id);
			p.close();
			
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
	}
}