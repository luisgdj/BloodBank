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
import bloodbank.ifaces.DonorManager;
import bloodbank.pojos.Blood;
import bloodbank.pojos.Donee;
import bloodbank.pojos.Donor;
import bloodbank.pojos.Nurse;

public class JDBCDonorManager implements DonorManager{

	private ConnectionManager conMan;
	private Connection c;
	
	public JDBCDonorManager(ConnectionManager conMan) {
		this.conMan = conMan;
		this.c = conMan.getConnection();
	}
	 
	@Override
	public void insertDonor(Donor d) {
		try {
			Statement s = c.createStatement();
			String sql = "INSERT INTO contract "
					+ "(name, surname, blood_type, dob, ssn) "
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
	public void removeDonor(int id) {
		
		try {
			String sql = "DELETE FROM donor WHERE id = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, "" + id); //ponemos 1 porque el primer atributo en la clase nurse es name que es por lo que lo queremos buscar
			
			c.close();
			
		}catch(SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
		}
	}

	@Override
	public void assignDonorToNurse(int donorId, int nurseId) {
		try {
			String sql = "INSERT INTO nurse_donor (donorId, nurseId) VALUES (?,?)";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1,donorId); 
			p.setInt(2, nurseId);
			p.executeUpdate();
			ResultSet rs = p.executeQuery();
			p.close();

		}catch(SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
			
		}
		
	}

	@Override
	public List<Donor> searchDonorByName(String name) {
			
		List<Donor> list= new ArrayList<Donor>();
		
		try {
			String sql= "SELECT * FROM donor WHERE name = ?";
			PreparedStatement p = c.prepareStatement(sql);//cuando haya una slect se usa preparedStatement 
			p.setString(1, "%"+name+ "%"); //el 1 apunta a la interrogacion y lo que va despues (name), apunta al parametro que se le pasa al metodo
			ResultSet rs=p.executeQuery();
			while(rs.next()) {
				//Create a new donor
				String n= rs.getString("name");
				String surname= rs.getString("surname");
				String bloodType= rs.getString("bloodType");
				LocalDate dob = (LocalDate)rs.getObject("dob");
				Long ssn= rs.getLong("ssn");
				Donor donor = new Donor(name, surname, bloodType, dob, ssn);

				list.add(donor);
				
			c.close();
				
			}
		}catch(SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
		}
		return list;
			
	}
	
	public Donor getDonor(int id) {
		
		try {			
			String sql =  "SELECT * FROM donee where id = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, "" + id); //ponemos 1 porque el primer atributo en la clase nurse es name que es por lo que lo queremos buscar
			ResultSet rs = p.executeQuery();
			
			String name = rs.getString("name");
			String surname= rs.getString("surname");
			String bloodType = rs.getString("blood_type");
			LocalDate dob = (LocalDate) rs.getObject("dob");
			long ssn = rs.getLong("ssn");
			List<Blood> donations = conMan.getBloodMan().getDonations(id);
			List<Nurse> nurses = conMan.getNurseMan().getListOfNursesOfDonor(id);
			
			Donor donor = new Donor(id,name,surname,bloodType,dob,ssn,donations,nurses);
			c.close();
			return donor;
		
		}catch(SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Donor> getListOfDonors(int nurseId) {
		
		List<Donor> list = new ArrayList<Donor>();
		try {
			String sql = "SELECT * FROM nurse AS n JOIN nurse_donor AS nd ON n.id = nd.nurse_id"
					+ " JOIN donor AS d ON d.id = nd.donor_id WHERE n.id = ? ";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, "" + nurseId);
			ResultSet rs = p.executeQuery();
			
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String bloodType = rs.getString("blood_type");
				LocalDate dob = (LocalDate) rs.getObject("dob");
				Long ssn = rs.getLong("ssn");
				List<Blood> donations = conMan.getBloodMan().getDonations(id);
				List<Nurse> nurses = conMan.getNurseMan().getListOfNursesOfDonor(id);
				
				Donor donor = new Donor(id,name,surname,bloodType,dob,ssn,donations,nurses);
				list.add(donor);
			}
			c.close();
			return list;
			
		} catch (SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
			return null;
		}
	}
}
