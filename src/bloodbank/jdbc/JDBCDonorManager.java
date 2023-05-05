package bloodbank.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import bloodbank.ifaces.DonorManager;
import bloodbank.pojos.Blood;
import bloodbank.pojos.Donee;
import bloodbank.pojos.Donor;

public class JDBCDonorManager implements DonorManager{

	private Connection conection;
	
	public JDBCDonorManager(Connection conection) {
		this.conection = conection;
	}
	
	@Override
	public void insertDonor(Donor d) {
		try {
			Statement s = conection.createStatement();
			String sql = "INSERT INTO contract "
					+ "(name, surname, blood_type,,dob,ssn) "
					+ "VALUES ('" +  d.getName()+ "','"
					+ d.getSurname() + "'," + d.getBloodType() 
					+ "','"  + d.getDob() 
					+ "','" + d.getSsn() + "')";
			s.execute(sql); 
			s.close();
		} catch (SQLException e) {
			System.out.println("Database exception");
			e.printStackTrace();
		}
	
	}

	@Override
	public void showDonor(int id) {
	
		try {
			String sql =  "SELECT * FROM donee where id = ?";
			PreparedStatement p = conection.prepareStatement(sql);
			p.setString(1, "" + id); //ponemos 1 porque el primer atributo en la clase nurse es name que es por lo que lo queremos buscar
			ResultSet rs = p.executeQuery();
			
			String name = rs.getString("name");
			String surname= rs.getString("surname");
			String bloodType = rs.getString("blood_type");
			Date dob = rs.getDate("dob"); //ni idea de como hacer esto
			long ssn = rs.getLong("SSN");
			conection.close();
			
			
		}catch(SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
		}
		}
		

	@Override
	public void removeDonor(int id) {
		// TODO Auto-generated method stub
		
		try {
			
			String sql = "DELETE FROM donor WHERE id = ?";
			PreparedStatement p = conection.prepareStatement(sql);
			p.setString(1, "" + id); //ponemos 1 porque el primer atributo en la clase nurse es name que es por lo que lo queremos buscar
			ResultSet rs = p.executeQuery();
			conection.close();
			
		}catch(SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
		}
		
		
		
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
	
public Donor getDonor(int id) {
		
		try {
			String sql =  "SELECT * FROM donee where id = ?";
			PreparedStatement p = conection.prepareStatement(sql);
			p.setString(1, "" + id); //ponemos 1 porque el primer atributo en la clase nurse es name que es por lo que lo queremos buscar
			ResultSet rs = p.executeQuery();
			
			String name = rs.getString("name");
			String surname= rs.getString("surname");
			String bloodType = rs.getString("blood_type");
			LocalDate dob = (LocalDate) rs.getObject("dob");
			long ssn = rs.getLong("ssn");
			Donor d = new Donor(id, name, surname, bloodType, dob, ssn);
			
			//creo que no habria que poner que devolviese la lista de transfusiones
			List <Blood> donations = (List<Blood>) rs.getArray("donations"); //tampoco se como hacerlo
			d.setDonations(donations);
			
			conection.close();
			return d;
		
		}catch(SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
			return null;
		}
	}

}
