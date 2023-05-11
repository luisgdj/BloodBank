package bloodbank.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import bloodbank.ifaces.DoneeManager;
import bloodbank.pojos.Blood;
import bloodbank.pojos.Donee;

public class JDBCDoneeManager implements DoneeManager{

	private Connection connection;
	private JDBCDonorManager donorMan;
	private JDBCDonorManager doneeMan;
	
	public JDBCDoneeManager(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public void insertDonee(Donee d) {
	
		try {
			Statement s = connection.createStatement();
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

	public Donee getDonee(int id) {
		
		try {
			String sql =  "SELECT * FROM donee where id = ?";
			PreparedStatement p = connection.prepareStatement(sql);
			p.setString(1, "" + id); //ponemos 1 porque el primer atributo en la clase nurse es name que es por lo que lo queremos buscar
			ResultSet rs = p.executeQuery();
			
			String name = rs.getString("name");
			String surname= rs.getString("surname");
			String bloodType = rs.getString("blood_type");
			Integer bloodNeeded = rs.getInt("blood_needed");
			LocalDate dob = (LocalDate) rs.getObject("dob");
			long ssn = rs.getLong("ssn");
			Donee d = new Donee(id, name, surname, bloodType, bloodNeeded, dob, ssn);
			
			//creo que no habria que poner que devolviese la lista de transfusiones
			List <Blood> transfussions = (List<Blood>) rs.getArray("tranfussions"); //tampoco se como hacerlo
			d.setTransfusions(transfussions);
			
			connection.close();
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
			PreparedStatement p = connection.prepareStatement(sql);
			p.setInt(1,doneeId); 
			p.setInt(2, nurseId);
			p.executeUpdate();
			ResultSet rs = p.executeQuery();
			p.close();

		}catch(SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
			
		}
		
	}

}