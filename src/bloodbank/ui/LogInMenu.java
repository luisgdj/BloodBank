package bloodbank.ui;

import bloodbank.JPA.JPAUserManager;
import bloodbank.ifaces.UserManager;

public abstract class LogInMenu {
	
	private static UserManager userMan;

	public static void main(String[] Args) {

		userMan = new JPAUserManager();
		
		System.out.println("Blood bank storage unit log in:");
		String username = Utilities.readString("Username: ");
		String password = Utilities.readString("Password: ");

		//Tendremos que implementar JPA	
	}
}
