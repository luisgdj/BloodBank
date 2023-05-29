package bloodbank.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bloodbank.ifaces.DonorManager;
import bloodbank.pojos.Blood;
import bloodbank.pojos.Donor;
import bloodbank.pojos.Nurse;

public class JDBCDonorManager implements DonorManager {

	private ConnectionManager conMan;
	private Connection c;

	public JDBCDonorManager(ConnectionManager conMan) {
		this.conMan = conMan;
		this.c = conMan.getConnection();
	}

	@Override
	public void insertDonor(Donor d) {
		try {
			String sql = "INSERT INTO donor " + "(name, surname, blood_type, dob, ssn)" + " VALUES (?,?,?,?,?)";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, d.getName());
			p.setString(2, d.getSurname());
			p.setString(3, d.getBloodType());
			p.setDate(4, d.getDob());
			p.setLong(5, d.getSsn());

			p.executeUpdate();
			p.close();

		} catch (SQLException e) {
			System.out.println("Database exception");
			e.printStackTrace();
		}
	}
	
	@Override
	public Donor getDonor(int id) {

		try {
			String sql = "SELECT * FROM donor WHERE id = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, "" + id);
			ResultSet rs = p.executeQuery();

			String name = rs.getString("name");
			String surname = rs.getString("surname");
			String bloodType = rs.getString("blood_type");
			Date dob = rs.getDate("dob");
			long ssn = rs.getLong("ssn");

			p.close();
			rs.close();
			return new Donor(id, name, surname, bloodType, dob, ssn, null, null);

		} catch (SQLException e) {
			System.out.println(" ERROR: donor does not exist.");
			return null;
		}
	}

	@Override
	public void assignDonorToNurse(int donorId, int nurseId) {
		try {
			String sql = "INSERT INTO nurse_donor (nurse_id, donor_id) VALUES (?,?)";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, nurseId);
			p.setInt(2, donorId);

			p.executeUpdate();
			p.close();

		} catch (SQLException e) {
			System.out.println("  (WARNING: You have already attended this donor)");
		}
	}

	@Override
	public List<Donor> getDonorsByName(String name) {

		List<Donor> list = new ArrayList<Donor>();
		try {
			String sql = "SELECT * FROM donor WHERE name LIKE ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, "%" + name + "%");
			ResultSet rs = p.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String n = rs.getString("name");
				String surname = rs.getString("surname");
				String bloodType = rs.getString("blood_type");
				Date dob = rs.getDate("dob");
				Long ssn = rs.getLong("ssn");
				List<Blood> donations = conMan.getBloodMan().getDonations(id);
				List<Nurse> nurses = conMan.getNurseMan().getListOfNursesOfDonor(id);

				Donor donor = new Donor(id, n, surname, bloodType, dob, ssn, donations, nurses);
				list.add(donor);
			}
			p.close();
			rs.close();

		} catch (SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Donor> getListOfDonors(int nurseId) {

		List<Donor> list = new ArrayList<Donor>();
		try {
			String sql = "SELECT * FROM nurse AS n JOIN nurse_donor AS nd ON n.id = nd.nurse_id"
					+ " JOIN donor AS d ON d.id = nd.donor_id WHERE n.id = ? ";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, nurseId);
			ResultSet rs = p.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt(8);
				String name = rs.getString(9);
				String surname = rs.getString(10);
				String bloodType = rs.getString(11);
				Date dob = rs.getDate(12);
				Long ssn = rs.getLong(13);

				Donor donor = new Donor(id, name, surname, bloodType, dob, ssn, null, null);
				list.add(donor);
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
	public void removeDonor(int id) {

		try {
			String sql = "DELETE FROM donor WHERE id = ?";
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
