package bloodbank.ui;

import bloodbank.ifaces.UserManager;
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
				System.out.println("Error: Wrong username or password.");
			}
		}
	}
}
