package bloodbank.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bloodbank.ifaces.ContractManager;
import bloodbank.pojos.Contract;
import bloodbank.pojos.Nurse;

public class JDBCContractManager implements ContractManager {
	
	private ConnectionManager conMan;
	private Connection c;
	
	public JDBCContractManager(ConnectionManager conMan) {
		this.conMan = conMan;
		this.c = conMan.getConnection();
	}

	@Override
	public void insertContract(Contract con) {
		
		try {
			Statement s = c.createStatement();
			String sql = "INSERT INTO contract (duration, salary) "
					+ "VALUES (" + con.getDuration() + " , "
					+ con.getSalary() + ")";
			s.execute(sql);
			s.close();
		} catch (SQLException e) {
			System.out.println("Database exception");
			e.printStackTrace();
		}
	}
	
	@Override
	public Contract getContract(int id) {
		
		try {
			String sql = "SELECT * FROM contract WHERE id = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setInt(1, id); 
			ResultSet rs = p.executeQuery();
			
			Integer duration = rs.getInt("duration");
			Integer salary = rs.getInt("salary");
			List<Nurse> nurses = conMan.getNurseMan().getListOfNursesOfContract(id);
			rs.close();
			return new Contract(id, duration, salary, nurses);

		}catch(SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public List<Contract> getListOfContracts() {
		
		List<Contract> contracts = new ArrayList<Contract>();
		try {
			String sql = "SELECT * FROM contract ";
			PreparedStatement p = c.prepareStatement(sql);
			ResultSet rs = p.executeQuery();
			
			while (rs.next()) {
				Integer id = rs.getInt("id");
				Integer duration = rs.getInt("duration"); 
				Float salary = rs.getFloat("salary");
				List<Nurse> nurses = conMan.getNurseMan().getListOfNursesOfContract(id);
				Contract contract = new Contract(id,duration,salary,nurses);
				contracts.add(contract);
			}
			return contracts;
			
		}catch(SQLException e) {
			System.out.println("Databases error");
			e.printStackTrace();
			return null;
		}
	}
}

