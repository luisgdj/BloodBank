 package bloodbank.ui;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import bloodbank.ifaces.BloodManager;
import bloodbank.ifaces.ContractManager;
import bloodbank.ifaces.DoneeManager;
import bloodbank.ifaces.DonorManager;
import bloodbank.ifaces.NurseManager;
import bloodbank.jdbc.ConnectionManager;
import bloodbank.jdbc.JDBCBloodManager;
import bloodbank.jdbc.JDBCDoneeManager;
import bloodbank.jdbc.JDBCDonorManager;
import bloodbank.jdbc.JDBCNurseManager;
import bloodbank.pojos.*;

public abstract class NurseMenu {

	private static DonorManager donorMan;
	private static DoneeManager doneeMan;
	private static BloodManager bloodMan;

	public static void menu(int nurse_id) {

		ConnectionManager conMan = new ConnectionManager();
		donorMan = new JDBCDonorManager(conMan.getConnection());
		doneeMan = new JDBCDoneeManager(conMan.getConnection());
		bloodMan = new JDBCBloodManager(conMan.getConnection(), donorMan, doneeMan);
		
		int retrivalLimit = 0; //POR AHORA (hay que ver como pasarle el limit q pone manager)
		
		while(true) {
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
					System.out.println("Register donor:");
					registerDonor();
					break;
				}
				case 2: {
					System.out.println("Register donee:");
					registerDonee();
					break;
				}
				case 3: {
					System.out.println("Access donor indormation:");
					selectDonor();
					break;
				}
				case 4: {
					System.out.println("Show all abailable blood:");
					showAllAvailableBlood(retrivalLimit);
					break;
				}
				case 5: {
					System.out.println("Retreive blood:");
					String bloodType = Utilities.askBloodType();
					float retrivalAmount = Utilities.readFloat(" -Retreival amount: ");
					retriveBlood(retrivalAmount, bloodType, retrivalLimit);
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

	//OPTION 1:
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
	
	//OPTION 2:
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
	
	//OPTION 3:
	private static void selectDonor() {
			
		System.out.println("Search donor by name: ");
		String name= Utilities.readString(" -Name: ");
		List<Donor> listDon = donorMan.searchDonorByName(name);
		System.out.println(listDon);
		System.out.println("Please choose a Donor.");
		Integer id = Utilities.readInteger(" -Id: ");
		checkDonorInfo(id);
	}
	
	//OPTION 3: (DONOR MENU)
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
					System.out.println("Add new donation:");
					registerDonation(id);
					break;
				}
				case 2: {
					System.out.println("Show all donations:");
					showDonations(id);
					break;
				}
				case 3: {
					System.out.println("Check personal information:");
					donorMan.getDonor(id).toString();
					break;
				}
				case 4: {
					donorMan.removeDonor(id);
					System.out.println("Donor elimminated from the data base");
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

	//OPTION 3.1:
	private static void registerDonation(int donor_id) {
			
		System.out.println("Imput blood donation data: ");
		Integer amount = Utilities.readInteger(" -Amount: ");
		LocalDate donationDate = LocalDate.now();
		Donor donor = donorMan.getDonor(donor_id);
		Blood b = new Blood(amount, donationDate, donor);
		bloodMan.insertBloodDonation(b);
	}
		
	//OPTION 3.2:
	private static void showDonations(int donor_id) {
		
		List<Blood> donations = bloodMan.getDonations(donor_id);
		Iterator<Blood> it = donations.iterator();
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}
	
	//OPTION 4:
	public static void showAllAvailableBlood(float limit) {
		
		System.out.println(" -Type A+: " + (bloodMan.getAmountOfBlood("A+") - limit));
		System.out.println(" -Type A-: " + (bloodMan.getAmountOfBlood("A-") - limit));
		System.out.println(" -Type B+: " + (bloodMan.getAmountOfBlood("B+") - limit));
		System.out.println(" -Type B-: " + (bloodMan.getAmountOfBlood("B-") - limit));
		System.out.println(" -Type AB+: " + (bloodMan.getAmountOfBlood("AB+") - limit));
		System.out.println(" -Type AB-: " + (bloodMan.getAmountOfBlood("AB-") - limit));
		System.out.println(" -Type O+: " + (bloodMan.getAmountOfBlood("O+") - limit));
		System.out.println(" -Type O-: " + (bloodMan.getAmountOfBlood("O-") - limit));
	}
	
	//OPTION 5:
	private static void retriveBlood(float retrivalAmount, String type, float limit) {
		
		float totalStorage = bloodMan.getAmountOfBlood(type);
		
		if(totalStorage>limit) {
			List<Float> amounts = bloodMan.getListOfAmounts(type); 
			List<Integer> ids = bloodMan.getListOfIds(type);
			
			Iterator<Float> it_amount = amounts.iterator();
			Iterator<Integer> it_id = ids.iterator();
			
			while(it_amount.hasNext()) {
				float amount = it_amount.next();
				
				if(retrivalAmount >= amount) {
					retrivalAmount = retrivalAmount - amount;
					bloodMan.deleteDonation(it_id.next());
				} else {
					bloodMan.updateBloodInDonation(it_id.next(), retrivalAmount);
				}
			}
		}else {
			System.out.println("Not allowed. Transfussion exceeds de blood limit.");
		}
	}
}
