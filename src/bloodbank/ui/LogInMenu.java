package bloodbank.ui;

import bloodbank.ifaces.UserManager;
import bloodbank.jpa.JPAUserManager;
import bloodbank.pojos.User;

public abstract class LogInMenu {
	
	private static UserManager userMan;

	public static void main(String[] Args) {
		
		userMan = new JPAUserManager();
		System.out.println("Welcome to the blood bank!");
		
		/* Vamos a tener un manager registrado por defecto para el que tendremos que
		 * iformar del usuario y contraseña en la decripcion de nuestro proyecto.
		 * 
		 * Tendremos una opcion en el menu del mannager para cambiar la contraseña
		 * establecida por defecto (opcion 6)
		 */
		User manager = new User("bloodBankMan", "2012912");
		userMan.register(manager);
		
		while (true) {
			// Ask for the username and password
			String username = Utilities.readString(" -Username: ");
			String password = Utilities.readString(" -Password: ");
			// If they match, go to the owner screen
			User user = userMan.logIn(username, password);
			
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
