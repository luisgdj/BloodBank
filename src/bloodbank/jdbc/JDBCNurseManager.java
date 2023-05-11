package bloodbank.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bloodbank.ifaces.NurseManager;
import bloodbank.pojos.Contract;
import bloodbank.pojos.Donee;
import bloodbank.pojos.Donor;
import bloodbank.pojos.Nurse;

public class JDBCNurseManager implements NurseManager {

	private Connection connection;
	
	public JDBCNurseManager(Connection connection) { //constructor para crear la conexion con la base de datos 
		this.connection = connection;
	}

	@Override
	public void insertNurse(Nurse nurse) {
		try {
			Statement s = connection.createStatement(); //cuando haya una insert se usa statement 
			String sql = "INSERT INTO nurse (name, surname, contract) VALUES ('" + nurse.getName() + "','"
					+ nurse.getSurname() + "'," + nurse.getContract() + ")";
			s.execute(sql);
			s.close(); //las conexiones se cierran asi
		} catch (SQLException e) { //poner siempre esta excepcion cuando creemos una sql
			System.out.println("Database exception");
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Nurse> searchNurseByName(String name){
		
		List<Nurse> list= new ArrayList<Nurse>();
		
		try {
			String sql= "SELECT * FROM nurse WHERE name = ?";
			PreparedStatement p=connection.prepareStatement(sql);//cuando haya una slect se usa preparedStatement 
			p.setString(1, "%"+name+ "%"); //el 1 apunta a la interrogacion y lo que va despues (name), apunta al parametro que se le pasa al metodo
			ResultSet rs=p.executeQuery();
			
			while(rs.next()) {
				//Create a new nurse
				Integer id= rs.getInt("id");
				String n = rs.getString("name");
				String surname= rs.getString("surname");
				Integer contract_id = rs.getInt("contract_id");
				Contract contract = contractMan.getContract(contract_id);
				List<Donor> donors = donorMan.getListOfDonors(id);
				List<Donee> donees = doneeMan.getListOfDonees(id);
				
				
				Nurse nurse = new Nurse(id, n, surname, contract, donors, donees);
				list.add(nurse);
				connection.close();
				
			}
		}catch(SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
		}
		return list;
	}
	
	public Nurse getNurse(int id) { //show personal information  (check nurse info)
		
		try {
			String sql = "SELECT * FROM nurse WHERE id = ?";
			PreparedStatement p = connection.prepareStatement(sql);
			p.setInt(1, id); //ponemos 1 porque el primer atributo en la clase nurse es name que es por lo que lo queremos buscar
			ResultSet rs = p.executeQuery();
			
			String name = rs.getString("name");
			String surname= rs.getString("surname");
			Integer contract_id = rs.getInt("contract_id");
			Contract contract = contractMan.getContract(contract_id);
			List<Donor> donors = donorMan.getListOfDonors(id);
			List<Donee> donees = doneeMan.getListOfDonees(id);
			
			connection.close();
			return new Nurse(id,name,surname,contract,donors,donees);
			
		}catch(SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
		}
		
		return null;

		
	}

	@Override
	public void updateContract(int contractId, int nurseId) {
		try {
			String sql = "UPDATE nurse SET contract_id = ? WHERE id = ?";
			PreparedStatement p = connection.prepareStatement(sql);
			p.setInt(1, contractId);
			p.setInt(2, nurseId);
			p.close();
		}catch(SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
		}
	}

	@Override
	public List<Nurse> getListOfNurses(int contractId) {
		List<Nurse> nurses = new ArrayList<Nurse>();
		try {
			String sql = "SELECT * FROM nurse WHERE contract_id = ? ";
			PreparedStatement p = connection.prepareStatement(sql);
			p.setInt(1, contractId);
			
			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				Integer contract_id = rs.getInt("contract_id");
				Contract contract = contractMan.getContract(contract_id);
				List<Donor> donors = donorMan.getListOfDonors(id);
				List<Donee> donees = doneeMan.getListOfDonees(id);
				Nurse nurse = new Nurse(id,name,surname,contract,donors,donees);
			}
			return nurses;
			
		}catch(SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
			return null;
		}

	}
}