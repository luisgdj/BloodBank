package bloodbank.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bloodbank.ifaces.BloodRetrievalLimitManager;

public class JDBCBloodRetrievalLimitManager implements BloodRetrievalLimitManager {

	private ConnectionManager conMan;
	private Connection c;

	public JDBCBloodRetrievalLimitManager(ConnectionManager conMan) {
		this.conMan = conMan;
		this.c = conMan.getConnection();
	}

	@Override
	public float getBloodRetrievalLimit() {
		try {
			String sql = "SELECT blood_limit FROM blood_retrieval ";
			PreparedStatement p = c.prepareStatement(sql);
			ResultSet rs = p.executeQuery();
			float limit = rs.getFloat(1);

			p.close();
			rs.close();
			return limit;

		} catch (SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public void updateBloodRetrievalLimit(float limit) {
		try {
			String sql = "UPDATE blood_retrieval SET blood_limit = ? ";
			PreparedStatement p = c.prepareStatement(sql);
			p.setFloat(1, limit);

			p.executeUpdate();
			p.close();

		} catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}
	}
}
