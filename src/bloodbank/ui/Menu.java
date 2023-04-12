package bloodbank.ui;

import bloodbank.ifaces.*;
import bloodbank.pojos.*;

public abstract class Menu{
	
	private static NurseManager nurseMan;
	private static ContractManager contractMan;
	private static DonorManager donorMan;
	private static DoneeManager doneeMan;
	private static BloodManager bloodMan;
	
	public static void registerNurse() {
		
		System.out.println("Imput nurse data: ");
		int id = Utilities.readInteger(" -ID: ");
		String name = Utilities.readString(" -Name: ");
		String surname = Utilities.readString(" -Surname: ");
		//Finish contract
		Contract contract = null;
		
		Nurse n = new Nurse(id,name,surname,contract);
		nurseMan.insertNurse(n);
	}

	public static void main(String[] args) {
		
		while(true) {
			System.out.println("Blood bank storage unit:"
					+ "\n 1. Register staff"
					+ "\n 2. Register patients"
					+ "\n 3. Access staff information"						
					+ "\n 4. Access patient information"
					+ "\n 0. Exit");
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
				default:{
					System.out.println("ERROR: Invalid option");
				}
			}
		}
	}
}