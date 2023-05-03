package bloodbank.ui;

import java.util.List;

import bloodbank.ifaces.*;
import bloodbank.pojos.*;
import bloodbank.jdbc.*;

public abstract class ManagerMenu {

	private static NurseManager nurseMan;
	private static ContractManager contractMan;
	private static DonorManager donorMan;
	private static DoneeManager doneeMan;
	private static BloodManager bloodMan;

	public static void menu() {
		
		ConnectionManager conMan = new ConnectionManager();
		nurseMan = new JDBCNurseManager(conMan.getConnection());
		contractMan = new JDBCContractManager(conMan.getConnection());
		bloodMan = new JDBCBloodManager(conMan.getConnection());

		while (true) {
			System.out.println("Blood bank storage unit (manager menu):" 
					+ "\n 1. Register nurse" 
					+ "\n 2. Establish contract"
					+ "\n 3. View blood storage" 
					+ "\n 4. Access nurse information" 
					+ "\n 5. Change blood retreival limit" 
					+ "\n 0. Log out");
			int option = Utilities.readInteger("Choose an option: ");

			switch (option) {
				case 1: {
					System.out.println("Registration of a nurse: ");
					registerNurse();
					break;
				}
				case 2: {
					System.out.println("Sablish contract:");
					registerContract();
					break;
				}
				case 3: {
					System.out.println("Check blood storage:");
					//segun orden (cantidad, tipo)
					break;
				}
				case 4: {
					System.out.println("Access nurse information:");
					selectNurse();
					break;
				}
				case 5: {
					System.out.println("Change blood retreival limit: ");
					//funcion que no permita sacar mas sangre de x
					break;
				}
				case 0: {
					System.out.println("Program terminated");
					return;
				}
				default: {
					System.out.println("ERROR: Invalid option");
				}
			}
		}
	}

	private static void registerNurse() {

		System.out.println("Imput nurse data: ");
		Integer id = Utilities.readInteger(" -ID: ");
		String name = Utilities.readString(" -Name: ");
		String surname = Utilities.readString(" -Surname: ");
		
		Nurse n = new Nurse(id, name, surname);
		nurseMan.insertNurse(n);
	}
	
	private static void registerContract() {

		System.out.println("Imput contract data: ");
		int id = Utilities.readInteger(" -ID: ");
		int duration = Utilities.readInteger("-Duration (months): ");
		int salary = Utilities.readInteger(" -Salary: ");
		
		Contract c = new Contract(id, duration, salary);
		contractMan.insertContract(c);
	}

	private static void selectNurse() {

		System.out.println("Search nurse by name: ");
		String name= Utilities.readString(" -Name: ");
		List<Nurse> listNur = nurseMan.searchNurseByName(name);
		System.out.println(listNur);
		System.out.println("Please choose a Nurse.");
		Integer id = Utilities.readInteger(" -Id: ");
		
		checkNurseInfo(id);
	}
	
	private static void checkNurseInfo(Integer id) {
		
		while (true) {
			System.out.println("Check nurse info:" 
					+ "\n 1. Show personal information" 
					+ "\n 2. Show patients"
					+ "\n 3. Change contract" 
					+ "\n 0. Return to menu");
			int option = Utilities.readInteger("Option: ");

			switch (option) {
				case 1: {
					nurseMan.showNurse(id);
					break;
				}
				case 2: {
	
					break;
				}
				case 3: {
	
					break;
				}
				case 0: {
					System.out.println("Program terminated");
					return;
				}
				default: {
					System.out.println("ERROR: Invalid option");
				}
			}
		}
		
	}
	
	private static void stablishContract() {
		//TODO
	}

	private static void viewBloodStorage() {
		
	}
	
	private static void setBloodRetivalLimit() {
		
	}
}
