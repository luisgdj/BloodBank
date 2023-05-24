package bloodbank.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bloodbank.ifaces.NurseManager;
import bloodbank.pojos.Blood;
import bloodbank.pojos.Contract;
import bloodbank.pojos.Donee;
import bloodbank.pojos.Donor;
import bloodbank.pojos.Nurse;

public class JDBCNurseManager implements NurseManager {

	private ConnectionManager conMan;
	private Connection c;

	public JDBCNurseManager(ConnectionManager conMan) {
		this.conMan = conMan;
		this.c = conMan.getConnection();
	}

	@Override
	public void insertNurse(Nurse nurse) {

		try {
			String sql = "INSERT INTO nurse (name, surname, email, contract_id)" + " VALUES (?,?,?,?)";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, nurse.getName());
			p.setString(2, nurse.getSurname());
			p.setString(3, nurse.getEmail());
			p.setInt(4, nurse.getContract().getId());

			p.executeUpdate();
			p.close();

		} catch (SQLException e) {
			System.out.println("Database exception");
			e.printStackTrace();
		}
	}

	@Override
	public List<Nurse> searchNurseByName(String name) {

		List<Nurse> list = new ArrayList<Nurse>();

		try {
			String sql = "SELECT * FROM nurse WHERE name = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, "%" + name + "%");
			ResultSet rs = p.executeQuery();

			while (rs.next()) {
				// Create a new nurse
				Integer id = rs.getInt("id");
				String n = rs.getString("name");
				String surname = rs.getString("surname");
				String email = rs.getString("surname");
				Integer contract_id = rs.getInt("contract_id");
				Contract contract = conMan.getContractMan().getContract(contract_id);
				List<Donor> donors = conMan.getDonorMan().getListOfDonors(id);
				List<Donee> donees = conMan.getDoneeMan().getListOfDonees(id);

				Nurse nurse = new Nurse(id, n, surname, email, contract, donors, donees);
				list.add(nurse);
			}
			p.close();
			rs.close();

		} catch (SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
		}
		return list;
	}

	public Nurse getNurse(int id) { // show personal information (check nurse info)

		try {
			String sql = "SELECT * FROM nurse WHERE id = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();

			String name = rs.getString("name");
			String surname = rs.getString("surname");
			String email = rs.getString("email");
			Integer contract_id = rs.getInt("contract_id");
			Contract contract = conMan.getContractMan().getContract(contract_id);
			List<Donor> donors = conMan.getDonorMan().getListOfDonors(id);
			List<Donee> donees = conMan.getDoneeMan().getListOfDonees(id);

			p.close();
			rs.close();
			return new Nurse(id, name, surname, email, contract, donors, donees);

		} catch (SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
			return null;
		}
	}

	public Nurse getNurseByEmail(String email) {

		try {
			String sql = "SELECT * FROM nurse WHERE email = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, email);
			ResultSet rs = p.executeQuery();

			Integer id = rs.getInt("id");
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			Integer contract_id = rs.getInt("contract_id");
			Contract contract = conMan.getContractMan().getContract(contract_id);
			List<Donor> donors = conMan.getDonorMan().getListOfDonors(id);
			List<Donee> donees = conMan.getDoneeMan().getListOfDonees(id);

			p.close();
			rs.close();
			return new Nurse(id, name, surname, email, contract, donors, donees);

		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void updateContract(int contractId, int nurseId) {
		try {
			String sql = "UPDATE nurse SET contract_id = ? WHERE id = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, contractId);
			p.setInt(2, nurseId);

			p.executeUpdate();
			p.close();

		} catch (SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
		}
	}

	@Override
	public List<Nurse> getListOfNursesOfContract(int contractId) {

		List<Nurse> nurses = new ArrayList<Nurse>();
		try {
			String sql = "SELECT * FROM nurse WHERE contract_id = ? ";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, contractId);
			ResultSet rs = p.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String email = rs.getString("email");
				Integer contract_id = rs.getInt("contract_id");
				Contract contract = conMan.getContractMan().getContract(contract_id);
				List<Donor> donors = conMan.getDonorMan().getListOfDonors(id);
				List<Donee> donees = conMan.getDoneeMan().getListOfDonees(id);

				Nurse nurse = new Nurse(id, name, surname, email, contract, donors, donees);
				nurses.add(nurse);
			}
			p.close();
			rs.close();
			return nurses;

		} catch (SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Nurse> getListOfNursesOfDonor(int donorId) {

		List<Nurse> nurses = new ArrayList<Nurse>();

		try {
			String sql = "SELECT * FROM nurse AS n JOIN nurse_donor AS nd ON n.id = nd.nurse_id WHERE nd.donor_id = ? ";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, donorId);
			ResultSet rs = p.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String email = rs.getString("email");
				Integer contract_id = rs.getInt("contract_id");
				Contract contract = conMan.getContractMan().getContract(contract_id);
				List<Donor> donors = conMan.getDonorMan().getListOfDonors(id);
				List<Donee> donees = conMan.getDoneeMan().getListOfDonees(id);

				Nurse nurse = new Nurse(id, name, surname, email, contract, donors, donees);
				nurses.add(nurse);
			}
			p.close();
			rs.close();
			return nurses;

		} catch (SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Nurse> getListOfNursesOfDonee(int doneeId) {

		List<Nurse> nurses = new ArrayList<Nurse>();
		try {
			String sql = "SELECT * FROM nurse AS n JOIN nurse_donee AS nd ON n.id = nd.nurse_id WHERE nd.donee_id = ? ";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, doneeId);
			ResultSet rs = p.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String email = rs.getString("email");
				Integer contract_id = rs.getInt("contract_id");
				Contract contract = conMan.getContractMan().getContract(contract_id);
				List<Donor> donors = conMan.getDonorMan().getListOfDonors(id);
				List<Donee> donees = conMan.getDoneeMan().getListOfDonees(id);
				Nurse nurse = new Nurse(id, name, surname, email, contract, donors, donees);
				nurses.add(nurse);
			}
			p.close();
			rs.close();
			return nurses;

		} catch (SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Create the list of all nurse objects
	 * 
	 * @return ArrayList of all nurse objects
	 */
	@Override
	public List<Nurse> getNurses() {

		List<Nurse> list = new ArrayList<>();
		try {
			String sql = "SELECT * FROM nurse ";
			PreparedStatement p = c.prepareStatement(sql);
			ResultSet rs = p.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String email = rs.getString("email");
				Integer contract_id = rs.getInt("contract_id");
				Contract contract = conMan.getContractMan().getContract(contract_id);
				List<Donor> donors = conMan.getDonorMan().getListOfDonors(id);
				List<Donee> donees = conMan.getDoneeMan().getListOfDonees(id);
				Nurse nurse = new Nurse(id, name, surname, email, contract, donors, donees);
				list.add(nurse);
			}
			p.close();
			rs.close();

		} catch (SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
			return null;
		}
		return list;
	}
}