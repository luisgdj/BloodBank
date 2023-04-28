package bloodbank.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bloodbank.ifaces.NurseManager;
import bloodbank.pojos.Contract;
import bloodbank.pojos.Nurse;

public class JDBCNurseManager implements NurseManager {

	private Connection c;
	
	public JDBCNurseManager(Connection c) {
		this.c = c;
	}

	@Override
	public void insertNurse(Nurse nurse) {
		try {
			Statement s = c.createStatement();
			String sql = "INSERT INTO nurse (name, surname, contract) VALUES ('" + nurse.getName() + "','"
					+ nurse.getSurname() + "'," + nurse.getContract() + ")";
			s.execute(sql);
			s.close();
		} catch (SQLException e) {
			System.out.println("Database exception");
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Nurse> searchNurseByName(String name){
		
		List<Nurse> list= new ArrayList<Nurse>();
		
		try {
			String sql= "SELECT * FROM nurse WHERE name = ?";
			PreparedStatement p=c.prepareStatement(sql);
			p.setString(1, "%"+name+ "%"); //ponemos 1 porque el primer atributo en la clase nurse es name que es por lo que lo queremos buscar
			ResultSet rs=p.executeQuery();
			while(rs.next()) {
				//Create a new nurse
				Integer id= rs.getInt("id");
				String n= rs.getString("name");
				String surname= rs.getString("surname");
				Integer contract_id = rs.getInt("contract_id");
				Contract contract = getContract(contract_id);
				
				Nurse nurse = new Nurse(id, n, surname, contract);
				list.add(nurse);
				
			}
		}catch(SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
		}
		return list;
	}
	
	public void selectNurse(int id) { //show personal information  (check nurse info)
		
		try {
			String sql = "SELECT * FROM nurse WHERE id = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, "" + id); //ponemos 1 porque el primer atributo en la clase nurse es name que es por lo que lo queremos buscar
			ResultSet rs = p.executeQuery();
			
			String name = rs.getString("name");
			String surname= rs.getString("surname");
			Integer contract_id = rs.getInt("contract_id");
			Contract contract = getContract(contract_id);
			
			Nurse nurse = new Nurse(id, name, surname, contract);
			contract.setNurse(nurse);
			System.out.println(nurse.toString());

		}catch(SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
		}
	}

	private Contract getContract(int contract_id) {
		
		try {
			String sql = "SELECT * FROM nurse WHERE id = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, "" + contract_id); 
			ResultSet rs = p.executeQuery();
			
			String duration = rs.getString("duration");
			Integer salary = rs.getInt("salary");
			return new Contract(contract_id, duration, salary);

		}catch(SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void assignContractToNurse(int contractId, int nurseId) {
		// TODO Auto-generated method stub
		
	}
}