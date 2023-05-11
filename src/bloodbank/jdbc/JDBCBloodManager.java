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
<<<<<<< HEAD
		// TODO Auto-generated method stub
=======
		try {
			Statement s = connection.createStatement(); //cuando haya una insert se usa statement 
			String sql = "INSERT INTO blood (amount, fecha, donor, donee) VALUES ('" + b.getAmount() + "','"
					+ b.getFecha() + "'," + b.getDonor()  + "'," + b.getDonee() + ")";
			s.execute(sql);
			s.close(); //las conexiones se cierran asi
			
		} catch (SQLException e) { //poner siempre esta excepcion cuando creemos una sql
			System.out.println("Database exception");
			e.printStackTrace();
		}
>>>>>>> branch 'master' of https://github.com/luisgdj/BloodBank
		
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
<<<<<<< HEAD
			Integer amount = rs.getInt(1);
			Date date = rs.getDate(2);
			Donor donor = getDonor(donorId);
			Integer donee = rs.getInt(3);
			
			Blood b = new Blood(donorId,amount,date,donor, donee);
=======
				Integer id = rs.getInt("id");
				Integer amount = rs.getInt("amount");
				LocalDate date = (LocalDate) rs.getObject("collection_date");
				Integer donor_id = rs.getInt("donor_id");
				Donor donor = donorMan.getDonor(donor_id);
				Integer donee_id = rs.getInt("donee_id");
				Donee donee = doneeMan.getDonee(donee_id);
				
				Blood b = new Blood(id, amount, date, donor, donee);
				// IMPORTANT: I don't have the dogs
				// Add the Owner to the list
				list.add(b);
>>>>>>> branch 'master' of https://github.com/luisgdj/BloodBank
			}
			
			//System.out.println(b.toString());
			connection.close();
<<<<<<< HEAD
		}
		
		catch (SQLException e) {
=======
			return list;
			
		} catch (SQLException e) {
>>>>>>> branch 'master' of https://github.com/luisgdj/BloodBank
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
<<<<<<< HEAD
	
			String blood_type = rs.getString(1); //ponemos un 1 ya que cuando son joins no ponemos el nombre, se pone la posicion de la query en la que está el atriburto
			float amount = rs.getFloat(2);
			String result = "";
			//como imprimir amount y result
=======
			float amount = rs.getFloat(1);//1 refers to the first column
>>>>>>> branch 'master' of https://github.com/luisgdj/BloodBank
			
			
			connection.close();

		} catch(SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
		}
	}
	
	@Override
<<<<<<< HEAD
	public void showBloodTotalAmount() {
=======
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
	public List<Integer> getListOfIds(String type) {
>>>>>>> branch 'master' of https://github.com/luisgdj/BloodBank
		
<<<<<<< HEAD
=======
		List<Integer> ids = new ArrayList<Integer>();
		try {
			String sql = "SELECT id FROM blood WHERE donor_id = (SELECT id FROM donor WHERE blood_type = ?)";
			PreparedStatement p = connection.prepareStatement(sql);
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
>>>>>>> branch 'master' of https://github.com/luisgdj/BloodBank
		try {
<<<<<<< HEAD
			String sql = "SELECT d.blood_type, b.SUM(amount) "
					+ "FROM donor AS d JOIN blood AS b ON d.blood_id = b.id "
					+ "GROUP BY d.blood_type ";
=======
			String sql = "SELECT amount FROM blood WHERE donor_id = (SELECT id FROM donor WHERE blood_type = ?)";
>>>>>>> branch 'master' of https://github.com/luisgdj/BloodBank
			PreparedStatement p = connection.prepareStatement(sql);
			//p.setString(1, "" + option); //ponemos 1 porque el primer atributo en la clase nurse es name que es por lo que lo queremos buscar
			ResultSet rs = p.executeQuery();
<<<<<<< HEAD
			connection.close();

=======
			int cont = 0;
			while (rs.next()) {
				float amount = rs.getFloat(cont);
				amounts.add(amount);
				cont++;
			}
			return amounts;
			
>>>>>>> branch 'master' of https://github.com/luisgdj/BloodBank
		}catch(SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
		}
	}
	
	@Override
<<<<<<< HEAD
	public void retreiveBlood(int amount, String bloodType) {
		// TODO Auto-generated method stub
		String sql = "SELECT SUM(amount) FROM blood WHERE donor_id = (SELECT id FROM donor WHERE blood_type = ?)";
		try {
			PreparedStatement p = connection.prepareStatement(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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

=======
	public void deleteDonation(int id) {
		
		try {
			String sql = "DELETE FROM blood WHERE id = ? ";
			PreparedStatement p = connection.prepareStatement(sql);
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
			PreparedStatement p = connection.prepareStatement(sql);
			p.setInt(1, id);
			p.setFloat(2, amount);
			p.close();
			
		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
	}
>>>>>>> branch 'master' of https://github.com/luisgdj/BloodBank
}














