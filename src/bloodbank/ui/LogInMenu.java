package bloodbank.ui;

import bloodbank.ifaces.BloodManager;
import bloodbank.ifaces.UserManager;
import bloodbank.jdbc.ConnectionManager;
import bloodbank.jdbc.JDBCBloodManager;
import bloodbank.jdbc.JDBCContractManager;
import bloodbank.jdbc.JDBCNurseManager;
import bloodbank.jpa.JPAUserManager;

public abstract class LogInMenu {
	
	private static UserManager userMan;

	public static void main(String[] Args) {
		
		BloodManager bloodMan;
		ConnectionManager conMan = new ConnectionManager();
		bloodMan = new JDBCBloodManager(conMan.getConnection());
		
		bloodMan.showBloodByBloodType();
		/*
		userMan = new JPAUserManager();
		
		System.out.println("Blood bank storage unit log in:");
		String username = Utilities.readString("Username: ");
		String password = Utilities.readString("Password: ");

		//Tendremos que implementar JPA. Accerdera a una de dos:
		NurseMenu.menu();
		ManagerMenu.menu();*/
	}
}
