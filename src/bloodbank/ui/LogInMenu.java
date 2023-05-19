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
			System.out.println("\nLog-In:");
			String email = Utilities.readString(" -Email: ");
			String password = Utilities.readString(" -Password: ");
			
			User user = userMan.logIn(email, password);
			//User user = userMan.logIn("manager@bloodBank.com", "default0");
			
			if (user != null) {
				if (user.getRole().getName().equals("manager")) {
					ManagerMenu.menu(userMan);
				}
				if (user.getRole().getName().equals("nurse")) {
					NurseMenu.menu(user.getEmail());
				}
			}else{
				System.out.println("Error: Wrong username or password.");
			}
		}
	}
}
