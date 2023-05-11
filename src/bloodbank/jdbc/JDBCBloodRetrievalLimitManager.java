package bloodbank.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bloodbank.ifaces.BloodRetrievalLimitManager;

public class JDBCBloodRetrievalLimitManager implements BloodRetrievalLimitManager{

	private Connection connection;
	
	public JDBCBloodRetrievalLimitManager(Connection connection) {
		this.connection = connection;
	} 
	
	@Override
	public void setBloodRetrievalLimit(float limit) {
		
		try {
			Statement s = connection.createStatement(); //cuando haya una insert se usa statement 
			String sql = "INSERT INTO blood_retrieval_limit (limit) VALUES (" + limit + ")";
			s.execute(sql);
			s.close(); //las conexiones se cierran asi
			
		} catch (SQLException e) { //poner siempre esta excepcion cuando creemos una sql
			System.out.println("Database exception");
			e.printStackTrace();
		}
		
	}

	@Override
	public float getBloodRetrievalLimit() {
		try {
			String sql = "SELECT limit FROM blood_retrieval_limit ";
			PreparedStatement p = connection.prepareStatement(sql);
			
			ResultSet rs = p.executeQuery();
			float limit = rs.getFloat(1);//1 refers to the first column
			
			return limit;

		} catch(SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
			return 0;
		}
	}

}
