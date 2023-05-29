package bloodbank.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import bloodbank.ifaces.*;
import bloodbank.xml.utils.XMLManagerImpl;

public class ConnectionManager {

	Connection c; // we define it outside because we use it in all the methods
	private static NurseManager nurseMan;
	private static ContractManager contractMan;
	private static BloodManager bloodMan;
	private static DonorManager donorMan;
	private static DoneeManager doneeMan;
	private static BloodRetrievalLimitManager retrievalMan;
	private static XMLManager xmlMan;

	public ConnectionManager() {
		try {
			Class.forName("org.sqlite.JDBC"); // establish a connection with the database
			c = DriverManager.getConnection("jdbc:sqlite:./db/BloodBank.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
			createTables();

			this.nurseMan = new JDBCNurseManager(this);
			this.contractMan = new JDBCContractManager(this);
			this.bloodMan = new JDBCBloodManager(this);
			this.donorMan = new JDBCDonorManager(this);
			this.doneeMan = new JDBCDoneeManager(this);
			this.retrievalMan = new JDBCBloodRetrievalLimitManager(this);
			this.xmlMan = new XMLManagerImpl();

		} catch (Exception e) {
			System.out.println("Database access error.");
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return c;
	}

	public static NurseManager getNurseMan() {
		return nurseMan;
	}

	public static ContractManager getContractMan() {
		return contractMan;
	}

	public static BloodManager getBloodMan() {
		return bloodMan;
	}

	public static DonorManager getDonorMan() {
		return donorMan;
	}

	public static DoneeManager getDoneeMan() {
		return doneeMan;
	}

	public static BloodRetrievalLimitManager getRetrievalMan() {
		return retrievalMan;
	}
	
	public static XMLManager getXmlMan() {
		return xmlMan;
	}

	private void createTables() {

		try {
			Statement s = c.createStatement();
			String table;
			
			table = "CREATE TABLE contract(" 
						+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
						+ "duration TEXT NOT NULL," 
						+ "salary INTEGER);";
			s.executeUpdate(table);

			table = "CREATE TABLE nurse(" 
						+ "id INTEGER PRIMARY KEY AUTOINCREMENT," 
						+ "name TEXT NOT NULL,"
						+ "surname TEXT NOT NULL," 
						+ "email TEXT NOT NULL,"
						+ "contract_id INTEGER,"
						+ "FOREIGN KEY (contract_id) REFERENCES contract(id) ON DELETE SET NULL);";
			s.executeUpdate(table);

			table = "CREATE TABLE donor(" 
						+ "id INTEGER PRIMARY KEY AUTOINCREMENT," 
						+ "name TEXT NOT NULL,"
						+ "surname TEXT NOT NULL," 
						+ "blood_type TEXT NOT NULL," 
						+ "dob DATE NOT NULL,"
						+ "ssn INTEGER NOT NULL);";
			s.executeUpdate(table);

			table = "CREATE TABLE blood(" 
						+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
						+ "amount INTEGER NOT NULL,"
						+ "collection_date DATE NOT NULL," 
						+ "donor_id INTEGER,"
						+ "donee_id INTEGER,"
						+ "FOREIGN KEY (donor_id) REFERENCES donor(id) ON DELETE SET NULL,"
						+ "FOREIGN KEY (donee_id) REFERENCES donee(id) ON DELETE SET NULL);";
			s.executeUpdate(table);

			table = "CREATE TABLE donee(" 
						+ "id INTEGER PRIMARY KEY AUTOINCREMENT," 
						+ "name TEXT NOT NULL,"
						+ "surname TEXT NOT NULL," 
						+ "blood_type TEXT NOT NULL," 
						+ "blood_needed TEXT NOT NULL,"
						+ "dob INTEGER NOT NULL," 
						+ "ssn INTEGER NOT NULL);";
			s.executeUpdate(table);

			table = "CREATE TABLE nurse_donee(" 
						+ "nurse_id INTEGER,"
						+ "donee_id INTEGER,"
						+ "FOREIGN KEY (nurse_id) REFERENCES nurse(id) ON DELETE CASCADE,"
						+ "FOREIGN KEY (donee_id) REFERENCES donee(id) ON DELETE CASCADE," 
						+ "PRIMARY KEY(nurse_id, donee_id));";
			s.executeUpdate(table);

			table = "CREATE TABLE nurse_donor(" 
						+ "nurse_id INTEGER,"
						+ "donor_id INTEGER,"
						+ "FOREIGN KEY (nurse_id) REFERENCES nurse(id) ON DELETE CASCADE,"
						+ "FOREIGN KEY (donor_id) REFERENCES donor(id) ON DELETE CASCADE," 
						+ "PRIMARY KEY(nurse_id, donor_id));";
			s.executeUpdate(table);

			table = "CREATE TABLE blood_retrieval(" 
						+ "blood_limit FLOAT NOT NULL)";
			s.executeUpdate(table);

			// Default values:
			String sql;
			sql = "INSERT INTO blood_retrieval(blood_limit) VALUES(0)";
			s.executeUpdate(sql);
			sql = "INSERT INTO contract (duration, salary) VALUES (12,2500)";
			s.executeUpdate(sql);

			s.close();

		} catch (SQLException e) {
			if (e.getMessage().contains("already exist")) {
				return;
			}
			System.out.println("Database error");
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			c.close();
		} catch (SQLException e) {
			System.out.println("Database Error.");
			e.printStackTrace();
		}
	}
}
