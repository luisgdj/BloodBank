package bloodbank.ui;

import java.time.LocalDate;
import java.util.List;

import bloodbank.ifaces.BloodManager;
import bloodbank.ifaces.ContractManager;
import bloodbank.ifaces.DoneeManager;
import bloodbank.ifaces.DonorManager;
import bloodbank.ifaces.NurseManager;
import bloodbank.jdbc.JDBCNurseManager;
import bloodbank.pojos.Donee;
import bloodbank.pojos.Donor;
import bloodbank.pojos.Nurse;

public abstract class NurseMenu {

	private static NurseManager nurseMan;
	private static ContractManager contractMan;
	private static DonorManager donorMan;
	private static DoneeManager doneeMan;
	private static BloodManager bloodMan;

	public static void main(String[] args) {

		nurseMan = new JDBCNurseManager(); // nurseMan= nurseManager
		while (true) {

			System.out.println("Blood bank storage unit:"
					+ "\n 1. Register donor" 
					+ "\n 2. Register donee"
					+ "\n 3. Select donor" 
					+ "\n 4. Show avaliable blood "
					+ "\n 5. Retreive blood"
					+ "\n 0. Log out");
			
			int option = Utilities.readInteger("Choose an option: ");

			switch (option) {
				case 1: {
					registerDonor();
					break;
				}
				case 2: {
					registerDonee();
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

	public static void registerDonor() {

		System.out.println("Imput donor data: ");
		Integer id = Utilities.readInteger(" -ID: ");
		String name = Utilities.readString(" -Name: ");
		String surname = Utilities.readString(" -Surname: ");
		String bloodType = Utilities.askBloodType();
		Long ssn = Utilities.readLong(" -Social Security Number: ");
		LocalDate dob = Utilities.askDate(" -Date of birth: ");

		Donor d = new Donor(id, name, surname, bloodType, dob, ssn);
		donorMan.insertDonor(d);
	}
	
	public static void registerDonee() {

		System.out.println("Imput donor data: ");
		Integer id = Utilities.readInteger(" -ID: ");
		String name = Utilities.readString(" -Name: ");
		String surname = Utilities.readString(" -Surname: ");
		String bloodType = Utilities.askBloodType();
		int bloodNedeed = Utilities.readInteger(" -Blood needed: ");
		Long ssn = Utilities.readLong(" -Social Security Number: ");
		LocalDate dob = Utilities.askDate(" -Date of birth: ");

		Donee d = new Donee(id, name, surname, bloodType, bloodNedeed,  dob, ssn);
		doneeMan.insertDonee(d);
	}

	public static void selectDonor() {
		//TODO
		
	}
	
	public static void showAvaliableBlood() {
		//TODO
		
	}
	
	
	
	public static void nurseMenu(Integer id) {
		//TODO
		
	}
}
