package bloodbank.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bloodbank.ifaces.DoneeManager;
import bloodbank.pojos.Blood;
import bloodbank.pojos.Donee;
import bloodbank.pojos.Nurse;

public class JDBCDoneeManager implements DoneeManager {

	private ConnectionManager conMan;
	private Connection c;

	public JDBCDoneeManager(ConnectionManager conMan) {
		this.conMan = conMan;
		this.c = conMan.getConnection();
	}

	@Override
	public void insertDonee(Donee d) {

		try {
			String sql = "INSERT INTO donee " + "(name, surname, blood_type, blood_needed, dob, ssn)"
					+ " VALUES (?,?,?,?,?,?)";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, d.getName());
			p.setString(2, d.getSurname());
			p.setString(3, d.getBloodType());
			p.setFloat(4, d.getBloodNeeded());
			p.setDate(5, d.getDob());
			p.setLong(6, d.getSsn());

			p.executeUpdate();
			p.close();

		} catch (SQLException e) {
			System.out.println("Database exception");
			e.printStackTrace();
		}
	}

	public Donee getDonee(int id) {

		try {
			String sql = "SELECT * FROM donee WHERE id = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();

			String name = rs.getString("name");
			String surname = rs.getString("surname");
			String bloodType = rs.getString("blood_type");
			Float bloodNeeded = rs.getFloat("blood_needed");
			Date dob = rs.getDate("dob");
			long ssn = rs.getLong("ssn");

			p.close();
			rs.close();
			return new Donee(id, name, surname, bloodType, bloodNeeded, dob, ssn, null, null);

		} catch (SQLException e) {
			System.out.println(" ERROR: donee does not exist.");
			return null;
		}
	}
	
	@Override
	public void assignDoneeToNurse(int doneeId, int nurseId) {
		try {
			String sql = "INSERT INTO nurse_donee (nurse_id, donee_id) VALUES (?,?)";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, nurseId);
			p.setInt(2, doneeId);

			p.executeUpdate();
			p.close();

		} catch (SQLException e) {
			System.out.println("  (WARNING: You have already attended this donee)");
		}
	}
	
	@Override
	public List<Donee> getDoneesByName(String name) {

		List<Donee> list = new ArrayList<Donee>();
		try {
			String sql = "SELECT * FROM donee WHERE name LIKE ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, "%" + name + "%");
			ResultSet rs = p.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String n = rs.getString("name");
				String surname = rs.getString("surname");
				String bloodType = rs.getString("blood_type");
				Float bloodNeeded = rs.getFloat("blood_needed");
				Date dob = rs.getDate("dob");
				Long ssn = rs.getLong("ssn");
				List<Blood> transfusions = conMan.getBloodMan().getTransfusions(id);
				List<Nurse> nurses = conMan.getNurseMan().getListOfNursesOfDonor(id);

				Donee donee = new Donee(id, n , surname, bloodType, bloodNeeded, dob, ssn, transfusions, nurses);
				list.add(donee);
			}
			p.close();
			rs.close();

		} catch (SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
		}
		return list;
	}

	public List<Donee> getListOfDonees(int nurseId) {

		List<Donee> list = new ArrayList<Donee>();
		try {
			String sql = "SELECT * FROM nurse AS n JOIN nurse_donee AS nd ON n.id = nd.nurse_id"
					+ " JOIN donee AS d ON d.id = nd.donee_id WHERE n.id = ? ";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, "" + nurseId);
			ResultSet rs = p.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt(8);
				String name = rs.getString(9);
				String surname = rs.getString(10);
				String bloodType = rs.getString(11);
				float bloodNeeded = rs.getFloat(12);
				Date dob = rs.getDate(13);
				Long ssn = rs.getLong(14);
				List<Blood> transfussions = conMan.getBloodMan().getTransfusions(id);
				List<Nurse> nurses = conMan.getNurseMan().getListOfNursesOfDonor(id);

				Donee donee = new Donee(id, name, surname, bloodType, bloodNeeded, dob, ssn, transfussions, nurses);
				list.add(donee);
			}
			p.close();
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

			p.executeUpdate();
			p.close();

		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
	}
	
	@Override
	public void removeDonee(int id) {

		try {
			String sql = "DELETE FROM donee WHERE id = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, id);

			p.executeUpdate();
			p.close();

		} catch (SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
		}
	}
}