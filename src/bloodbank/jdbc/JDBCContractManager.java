package bloodbank.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import bloodbank.ifaces.ContractManager;
import bloodbank.pojos.Contract;

public class JDBCContractManager implements ContractManager{
	
	private Connection conection;
	
	public JDBCContractManager(Connection conection) {
		this.conection = conection;
	} 

	@Override
	public void insertContract(Contract c) {
		
		try {
			Statement s = conection.createStatement();
			String sql = "INSERT INTO contract (duration, salary, nurses) "
					+ "VALUES ('" + c.getDuration() + "','"
					+ c.getSalary() + "'," + c.getNurse() + ")";
			s.execute(sql);
			s.close();
		} catch (SQLException e) {
			System.out.println("Database exception");
			e.printStackTrace();
		}
	}
}

