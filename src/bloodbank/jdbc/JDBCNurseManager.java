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
				String n= rs.getString("name");
				String surname= rs.getString("surname");
				Integer contract_id = rs.getInt("contract_id");
				Contract contract = getContract(contract_id);
				
				Nurse nurse = new Nurse(id, n, surname, contract);
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
			p.setString(1, "" + id); //ponemos 1 porque el primer atributo en la clase nurse es name que es por lo que lo queremos buscar
			ResultSet rs = p.executeQuery();
			
			String name = rs.getString("name");
			String surname= rs.getString("surname");
			Integer contract_id = rs.getInt("contract_id");
			Contract contract = getContract(contract_id);
			
			connection.close();
			return new Nurse(name,surname,contract);
			
		}catch(SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
		}
		
		return null;

		
	}

	private Contract getContract(int contract_id) {
		
		try {
			String sql = "SELECT * FROM nurse WHERE id = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, "" + contract_id); 
			ResultSet rs = p.executeQuery();
			
			Integer duration = rs.getInt("duration");
			Integer salary = rs.getInt("salary");
			return new Contract(contract_id, duration, salary);
			connection.close();

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