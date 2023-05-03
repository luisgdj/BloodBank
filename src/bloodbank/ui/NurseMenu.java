package bloodbank.ui;

import java.time.LocalDate;
import java.util.List;

import bloodbank.ifaces.BloodManager;
import bloodbank.ifaces.ContractManager;
import bloodbank.ifaces.DoneeManager;
import bloodbank.ifaces.DonorManager;
import bloodbank.ifaces.NurseManager;
import bloodbank.jdbc.ConnectionManager;
import bloodbank.jdbc.JDBCNurseManager;
import bloodbank.pojos.*;

public abstract class NurseMenu {

	private static NurseManager nurseMan;
	private static ContractManager contractMan;
	private static DonorManager donorMan;
	private static DoneeManager doneeMan;
	private static BloodManager bloodMan;

	public static void menu() {

		ConnectionManager conMan = new ConnectionManager();
		nurseMan = new JDBCNurseManager(conMan.getConnection()); // nurseMan= nurseManager
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
					selectDonor();
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

	private static void registerDonor() {

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
	
	private static void registerDonee() {

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

	private static void selectDonor() {
		
		System.out.println("Search donor by name: ");
		String name= Utilities.readString(" -Name: ");
		List<Donor> listDon = donorMan.searchDonorByName(name);
		System.out.println(listDon);
		System.out.println("Please choose a Donor.");
		Integer id = Utilities.readInteger(" -Id: ");
		checkDonorInfo(id);
	}
	
	private static void checkDonorInfo(int id) {
		
		while (true) {
			System.out.println("Blood bank storage unit:"
					+ "\n 1. Register donation" 
					+ "\n 2. View donotations"
					+ "\n 3. Show personal information" 
					+ "\n 4. Eliminate donor"
					+ "\n 0. Return to nurse menu");
			int option = Utilities.readInteger("Choose an option: ");

			switch (option) {
				case 1: {
					registerDonation(id);
					break;
				}
				case 2: {
					bloodMan.showDonations(id);
					break;
				}
				case 3: {
					donorMan.showDonor(id);
					break;
				}
				case 4: {
					donorMan.removeDonor(id);
					break;
				}
				case 0: {
					return;
				}
				default: {
					System.out.println("ERROR: Invalid option");
				}
			}
		}
	}

	private static void registerDonation(int donor_id) {
		
		System.out.println("Imput blood donation data: ");
		Integer blood_id = Utilities.readInteger(" -ID: ");
		Integer amount = Utilities.readInteger(" -Amount: ");
		LocalDate donationDate = Utilities.askDate(" -Donation date: ");

		Blood b = new Blood(blood_id, amount, donationDate, donor_id);
		bloodMan.insertBloodDonation(b);
		
	}
	
	private static void showAvaliableBlood() {
		//TODO
		
	}
	
	private static void retreiveBlood () {
		//TODO
		
	}
}
