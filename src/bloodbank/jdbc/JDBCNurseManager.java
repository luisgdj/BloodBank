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

	Connection c; // we defined fuera because we use it in all the methods

	public JDBCNurseManager() {
		try {
			Class.forName("org.sqlite.JDBC"); // establish a connection with the database
			c = DriverManager.getConnection("jdbc:sqlite:./db/BloodBank.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
			createTables();
		} catch (Exception e) {
			System.out.println("Database access error.");
			e.printStackTrace();
		}

	}

	private void createTables() {
		try {
		
			Statement s = c.createStatement();

			String table = "CREATE TABLE contract(" + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "duration TEXT NOT NULL," + "salary INTEGER);";
			s.executeUpdate(table);
			
			table = "CREATE TABLE nurse(" + "id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT NOT NULL,"
					+ "surname TEXT NOT NULL," + "contract_id INTEGER REFERENCES contract(id));";
			s.executeUpdate(table);
			
			table = "CREATE TABLE donor(" + "id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT NOT NULL,"
					+ "surname TEXT NOT NULL," + "blood_type TEXT NOT NULL," + "age INTEGER NOT NULL,"
					+ "ssnn INTEGER NOT NULL);";
			s.executeUpdate(table);
			
			table = "CREATE TABLE blood(" + "id INTEGER PRIMARY KEY AUTOINCREMENT," + "amount INTEGER NOT NULL,"
					+ "collection_date DATE NOT NULL," + "donor_id INTEGER REFERENCES donor(id));";
			s.executeUpdate(table);
			
			table = "CREATE TABLE donee(" + "id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT NOT NULL,"
					+ "surname TEXT NOT NULL," + "blood_type TEXT NOT NULL," + "blood_needed TEXT NOT NULL,"
					+ "age INTEGER NOT NULL," + "ssnn INTEGER NOT NULL," + "blood_id INTEGER REFERENCES blood(id));";
			s.executeUpdate(table);
			
			table = "CREATE TABLE nurse_donee(" + "nurse_id INTEGER REFERENCES nurse(id),"
					+ "donee_id INTEGER REFERENCES donee(id)" + "PRIMARY KEY(nurse_id, donee_id));";
			s.executeUpdate(table);
			
			table = "CREATE TABLE nurse_donor(" + "nurse_id INTEGER REFERENCES nurse(id),"
					+ "donor_id INTEGER REFERENCES donor(id)" + "PRIMARY KEY(nurse_id, donor_id));";
			s.executeUpdate(table);
			
			
			s.close();
			
		} catch (SQLException e) {
			if(e.getMessage().contains("already exist")) {
				return;
			}
			System.out.println("Database error");
			e.printStackTrace();
		}
	}

	@Override
	public void insertNurse(Nurse nurse) {
		try {
			Statement s = c.createStatement();
			String sql = "INSERT INTO nurse (name, surname, contract) VALUES ('" + nurse.getName() + "','"
					+ nurse.getSurname() + "'," + nurse.getContract_id() + ")";
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
				Integer contract= rs.getInt("contract_id");
				Nurse nurse =new Nurse(id, n, surname, contract);
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
			Integer contract= rs.getInt("contract_id");
			Nurse nurse =new Nurse(id, name, surname, contract);
			System.out.println(nurse.toString());

		}catch(SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
		}
	}

	@Override
	public void assignContractToNurse(int contractId, int nurseId) {
		// TODO Auto-generated method stub
		
	}
}