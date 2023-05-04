package bloodbank.ui;

import java.io.IOException;


import bloodbank.ifaces.BloodManager;
import bloodbank.ifaces.UserManager;
import bloodbank.jdbc.ConnectionManager;
import bloodbank.jdbc.JDBCBloodManager;
import bloodbank.jdbc.JDBCContractManager;
import bloodbank.jdbc.JDBCNurseManager;
import bloodbank.jpa.JPAUserManager;
import bloodbank.pojos.User;

public abstract class LogInMenu {
	
	private static UserManager userMan;

	public static void main(String[] Args) {
		
		userMan = new JPAUserManager();
		System.out.println("Welcome to the blood bank!");
		
		while (true) {
			// Ask for the username and password
			String username = Utilities.readString(" -Username: ");
			String password = Utilities.readString(" -Password: ");
			// If they match, go to the owner screen
			User user = userMan.logIn(username, password);
			
			if (user != null) {
				if (user.getRole().getName().equals("manager")) {
					ManagerMenu.menu();
				}
				if (user.getRole().getName().equals("nurse")) {
					NurseMenu.menu(user.getId());
				}
			}else{
				System.out.println("Wrong username/password combination.");
			}
		}
	}
	
	public static void logIn() throws IOException {
		while (true) {
			// Ask for the username and password
			String username = Utilities.readString(" -Username: ");
			String password = Utilities.readString(" -Password: ");
			// If they match, go to the owner screen
			User user = userMan.logIn(username, password);
			
			if (user != null) {
				if (user.getRole().getName().equals("owner")) {
					ownerMenu(user.getEmail());
					//NurseMenu.menu();
				}
				else if (user.getRole().getName().equals("vet")) {
					selectVet();
					//ManagerMenu.menu();
				}
			}else{
				System.out.println("Wrong username/password combination.");
			}
		}
	}
		
		
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
