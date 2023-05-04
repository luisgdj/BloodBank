package bloodbank.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import bloodbank.ifaces.DoneeManager;
import bloodbank.pojos.Blood;
import bloodbank.pojos.Contract;
import bloodbank.pojos.Donee;

public class JDBCDoneeManager implements DoneeManager{

	private Connection conection;
	
	public JDBCDoneeManager(Connection conection) {
		this.conection = conection;
	}
	
	@Override
	public void insertDonee(Donee d) {
	
		try {
			Statement s = conection.createStatement();
			String sql = "INSERT INTO contract "
					+ "(name, surname, blood_type,blood_needed,dob,ssn,transfussions) "
					+ "VALUES ('" +  d.getName()+ "','"
					+ d.getSurname() + "'," + d.getBloodType() 
					+ "','" + d.getBloodNeeded() + "','" + d.getDob() 
					+ "','" + d.getSsn() + "','" + d.getTransfusions() + "')";
			s.execute(sql); 
			s.close();
		} catch (SQLException e) {
			System.out.println("Database exception");
			e.printStackTrace();
		}
	
	}

	@Override
	public void showDonee(int id) {
		
		try {
		String sql =  "SELECT * FROM donee where id = ?";
		PreparedStatement p = conection.prepareStatement(sql);
		p.setString(1, "" + id); //ponemos 1 porque el primer atributo en la clase nurse es name que es por lo que lo queremos buscar
		ResultSet rs = p.executeQuery();
		
		String name = rs.getString("name");
		String surname= rs.getString("surname");
		String bloodType = rs.getString("blood_type");
		Integer bloodNeeded = rs.getInt("blood_needed");
		Date dob = rs.getDate("dob"); //ni idea de como hacer esto
		long ssn = rs.getLong("SSN");
		List <Blood> transfussions = (List<Blood>) rs.getArray("tranfussions"); //tampoco se como hacerlo
		conection.close();
		
		
	}catch(SQLException e) {
		System.out.println("Databases error");
		e.printStackTrace();
	}
	}
	

	@Override
	public void assignDoneeToNurse(int doneeId, int nurseId) {
		// TODO Auto-generated method stub
		
	}

}
