package bloodbank.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {
	
	Connection c; // we define it outside because we use it in all the methods

	public ConnectionManager() {
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
					+ "donee_id INTEGER REFERENCES donee(id)," + "PRIMARY KEY(nurse_id, donee_id));";
			s.executeUpdate(table);
			
			table = "CREATE TABLE nurse_donor(" + "nurse_id INTEGER REFERENCES nurse(id),"
					+ "donor_id INTEGER REFERENCES donor(id)," + "PRIMARY KEY(nurse_id, donor_id));";
			s.executeUpdate(table);
			
			table= "CREATE TABLE blood_retrieval (" + "limit FLOAT )";
			s.execute(table);
			
			s.close();
			
		} catch (SQLException e) {
			if(e.getMessage().contains("already exist")) {
				return;
			}
			System.out.println("Database error");
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return c;
	}

	public void closeConnection () {
		try {
			c.close();
		}
		catch (SQLException e) {
			System.out.println("Database Error.");
			e.printStackTrace();
		}
	}
}
