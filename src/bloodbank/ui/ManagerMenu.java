package bloodbank.ui;

import java.util.List;

import bloodbank.ifaces.BloodManager;
import bloodbank.ifaces.ContractManager;
import bloodbank.ifaces.DoneeManager;
import bloodbank.ifaces.DonorManager;
import bloodbank.ifaces.NurseManager;
import bloodbank.jdbc.JDBCNurseManager;
import bloodbank.pojos.Nurse;

public abstract class ManagerMenu {

	private static NurseManager nurseMan;
	private static ContractManager contractMan;
	private static DonorManager donorMan;
	private static DoneeManager doneeMan;
	private static BloodManager bloodMan;

	public static void main(String[] args) {

		nurseMan = new JDBCNurseManager(); // nurseMan= nurseManager
		while (true) {

			System.out.println("Blood bank storage unit:" + "\n 1. Register staff" + "\n 2. Register patients"
					+ "\n 3. Access staff information" + "\n 4. Access patient information" + "\n 0. Exit");
			int option = Utilities.readInteger("Choose an option: ");

			switch (option) {
				case 1: {
	
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

	public static void registerNurse() {

		System.out.println("Imput nurse data: ");
		Integer id = Utilities.readInteger(" -ID: ");
		String name = Utilities.readString(" -Name: ");
		String surname = Utilities.readString(" -Surname: ");
		// Finish contract
		Integer contract = Utilities.readInteger(" -Contract Id: ");

		Nurse n = new Nurse(id, name, surname, contract);
		nurseMan.insertNurse(n);
	}

	public static void selectNurse() {

		System.out.println("Search nurse by name: ");
		String name= Utilities.readString(" -Name: ");
		List<Nurse> listNur = nurseMan.searchNurseByName(name);
		System.out.println(listNur);
		System.out.println("Please choose a Nurse.");
		Integer id = Utilities.readInteger(" -Id: ");
		checkNurseInfo(id);
	}
	
	public static void checkNurseInfo(Integer id) {
		
		
		while (true) {

			System.out.println("Check nurse info:" 
					+ "\n 1. Show personal information" 
					+ "\n 2. Show patients"
					+ "\n 3. Change contract" 
					+ "\n 0. Return to menu");
			int option = Utilities.readInteger("Option: ");

			switch (option) {
				case 1: {
					
	
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
	
	public static void stablishContract() {
		//TODO
	}

	public static void viewBloodStorage() {
		
	}
	
	public static void setBloodRetivalLimit() {
		
	}
}
