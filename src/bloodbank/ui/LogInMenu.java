package bloodbank.ui;

import bloodbank.ifaces.UserManager;
import bloodbank.jpa.JPAUserManager;
import bloodbank.pojos.Role;
import bloodbank.pojos.User;

public abstract class LogInMenu {
	
	private static UserManager userMan;

	public static void main(String[] Args) {
		
		userMan = new JPAUserManager();
		System.out.println("\nWelcome to the blood bank!");
		
		while (true) {
			/*
			String username = Utilities.readString(" -Username: ");
			String password = Utilities.readString(" -Password: ");
			
			User user = userMan.logIn(username, password);
			*/ 
			User user = userMan.logIn("manager@bloodBank.com", "default0");
			
			if (user != null) {
				if (user.getRole().getName().equals("manager")) {
					ManagerMenu.menu(userMan);
				}
				if (user.getRole().getName().equals("nurse")) {
					NurseMenu.menu(user.getId());
				}
			}else{
				System.out.println("Error: Wrong username or password.");
			}
		}
	}
}
