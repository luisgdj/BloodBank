package bloodbank.ui;

import bloodbank.jdbc.JDBCNurseManager;

public abstract class LogInMenu {

	public static void main(String[] Args) {

			System.out.println("Blood bank storage unit log in:");
			String username = Utilities.readString("Username: ");
			String password = Utilities.readString("Password: ");

			/*
			 * NO SABEMOS SI EN EL SWITCH HAY QUE PONER EL USERNAME PARA QUE LO IDENTIFIQUE Y LE LLEVE AL MENU DE NURSE O EL DE MANAGER
			 */
			switch (username) {
				case 1: {
	
					break;
				}
				case 2: {
	
					break;
				}
				default: {
					System.out.println("ERROR: Invalid option");
				}
			}
		
	}
	
}
