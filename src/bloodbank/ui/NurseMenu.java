package bloodbank.ui;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import bloodbank.ifaces.*;
import bloodbank.jdbc.*;
import bloodbank.pojos.*;

public abstract class NurseMenu {

	private static NurseManager nurseMan;
	private static DonorManager donorMan;
	private static DoneeManager doneeMan;
	private static BloodManager bloodMan;
	private static BloodRetrievalLimitManager retrievalMan;

	private static Nurse nurse;

	public static void menu(String email) {

		ConnectionManager conMan = new ConnectionManager();

		nurseMan = conMan.getNurseMan();
		donorMan = conMan.getDonorMan();
		doneeMan = conMan.getDoneeMan();
		bloodMan = conMan.getBloodMan();
		retrievalMan = conMan.getRetrievalMan();

		nurse = nurseMan.getNurseByEmail(email);
		float retrievalLimit = retrievalMan.getBloodRetrievalLimit();

		while (true) {
			System.out.println("\nBlood bank storage unit (nurse menu: " + email + ")" 
					+ "\n 1. Register donor"
					+ "\n 2. Register donee" 
					+ "\n 3. Select donor" 
					+ "\n 4. Select donee"
					+ "\n 5. Show avaliable blood" 
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
					System.out.println("Access donor menu:");
					Integer id = selectDonor();
					if(id != null) {
						checkDonorInfo(id);
					}
					break;
				}
				case 4: {
					System.out.println("Access donee menu:");
					Integer id = selectDonee();
					if(id != null) {
						checkDoneeInfo(id);
					}
					break;
				}
				case 5: {
					System.out.println("Show all available blood:");
					showAllAvailableBlood(retrievalLimit);
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

	// OPTION 1:
	private static void registerDonor() {

		System.out.println("Imput donor data: ");
		String name = Utilities.readString(" -Name: ");
		String surname = Utilities.readString(" -Surname: ");
		String bloodType = Utilities.askBloodType(" -Choose blood type: ");
		Long ssn = Utilities.readLong(" -Social Security Number: ");
		Date dob = Utilities.askDate(" -Date of birth: ");

		Donor d = new Donor(name, surname, bloodType, dob, ssn);
		donorMan.insertDonor(d);
	}

	// OPTION 2:
	private static void registerDonee() {

		System.out.println("Imput donor data: ");
		String name = Utilities.readString(" -Name: ");
		String surname = Utilities.readString(" -Surname: ");
		String bloodType = Utilities.askBloodType(" -Choose blood type: ");
		int bloodNedeed = Utilities.readInteger(" -Blood needed: ");
		Long ssn = Utilities.readLong(" -Social Security Number: ");
		Date dob = Utilities.askDate(" -Date of birth: ");

		Donee d = new Donee(name, surname, bloodType, bloodNedeed, dob, ssn);
		doneeMan.insertDonee(d);
	}

	// OPTION 3:
	private static Integer selectDonor() {
		
		String name = Utilities.readString(" -Search donor by name: ");
		List<Donor> list = donorMan.getDonorsByName(name);
		Iterator<Donor> it = list.iterator();
		while(it.hasNext()) {
			Donor d = it.next();
			System.out.println("   (" + d.getId() + ") "+ d.getName() + " " + d.getSurname());
		}
		
		Integer id = Utilities.readInteger(" -Choose an id: ");
		if (donorMan.getDonor(id) != null){
			return id;
		}
		return null;
	}

	// OPTION 3: (DONOR MENU)
	private static void checkDonorInfo(int id) {

		while (true) {
			System.out.println("\nCheck donor: (" + id + ")" + "\n 1. Donate blood" + "\n 2. View donotations"
					+ "\n 3. Show personal information" + "\n 4. Remove donor" + "\n 0. Return to nurse menu");
			int option = Utilities.readInteger("Choose an option: ");

			switch (option) {
				case 1: {
					System.out.println("Stablish new donation:");
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
					Donor d = donorMan.getDonor(id);
					d.setDonations(bloodMan.getDonations(id));
					d.setNurses(nurseMan.getListOfNursesOfDonor(id));
					System.out.println(d.toString());
					break;
				}
				case 4: {
					donorMan.removeDonor(id);
					System.out.println("Donor removed from the data base");
					System.out.println("Returned to nurse menu");
					return;
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

	// OPTION 3.1:
	private static void registerDonation(int donor_id) {

		System.out.println("Imput blood donation data: ");
		Integer amount = Utilities.readInteger(" -Amount: ");
		Date donationDate = Date.valueOf(LocalDate.now());
		Donor donor = donorMan.getDonor(donor_id);
		donor.setDonations(bloodMan.getDonations(donor_id));
		donor.setNurses(nurseMan.getListOfNursesOfDonor(donor_id));

		Blood b = new Blood(amount, donationDate, donor);
		bloodMan.insertBloodDonation(b);
		donorMan.assignDonorToNurse(donor_id, nurse.getId());
	}

	// OPTION 3.2:
	private static void showDonations(int donor_id) {

		List<Blood> donations = bloodMan.getDonations(donor_id);
		Iterator<Blood> it = donations.iterator();
		while (it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}

	// OPTION 4:
	private static Integer selectDonee() {

		String name = Utilities.readString(" -Search donee by name: ");
		List<Donee> list = doneeMan.getDoneesByName(name);
		Iterator<Donee> it = list.iterator();
		while(it.hasNext()) {
			Donee d = it.next();
			System.out.println("   (" + d.getId() + ") "+ d.getName() + " " + d.getSurname());
		}
		
		Integer id = Utilities.readInteger(" -Choose an id: ");
		if (doneeMan.getDonee(id) != null){
			return id;
		}
		return null;
	}

	// OPTION 4: (DONEE MENU)
	private static void checkDoneeInfo(int id) {

		while (true) {
			System.out.println("\nCheck donee: (" + id + ")" + "\n 1. Transfuse blood" + "\n 2. View transfusions"
					+ "\n 3. Show personal information" + "\n 4. Remove donee" + "\n 0. Return to nurse menu");
			int option = Utilities.readInteger("Choose an option: ");
			switch (option) {
				case 1: {
					System.out.println("Stablish new transfusion:");
					stablishTransfusion(id);
					break;
				}
				case 2: {
					System.out.println("Show all tranfusions:");
					showTransfusions(id);
					break;
				}
				case 3: {
					System.out.println("Check personal information:");
					Donee d = doneeMan.getDonee(id);
					d.setTransfusions(bloodMan.getTransfusions(id));
					d.setNurses(nurseMan.getListOfNursesOfDonee(id));
					System.out.println(d.toString());
					break;
				}
				case 4: {
					doneeMan.removeDonee(id);
					System.out.println("Donee removed from the data base");
					System.out.println("Returned to nurse menu");
					return;
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

	// OPTION 4.1:
	public static void stablishTransfusion(int donee_id) {

		System.out.println("Imput blood tranfusion data: ");
		float retrivalAmount = Utilities.readInteger(" -Amount needed: ");
		String bloodType = Utilities.askBloodType(" -Choose blood type: ");

		float limit = retrievalMan.getBloodRetrievalLimit();
		float totalStorage = bloodMan.getAmountOfBlood(bloodType);

		if (totalStorage > limit) {
			List<Float> amounts = bloodMan.getListOfAmounts(bloodType);
			List<Integer> ids = bloodMan.getListOfIds(bloodType);

			Iterator<Float> it_amount = amounts.iterator();
			Iterator<Integer> it_id = ids.iterator();

			while (it_amount.hasNext() && retrivalAmount != 0) {
				float amount = it_amount.next();
				if (retrivalAmount >= amount) {
					retrivalAmount = retrivalAmount - amount;
					bloodMan.updateBloodDoneeId(it_id.next(), donee_id);
				}
				doneeMan.updateDoneeBloodNeeded(donee_id, retrivalAmount);
			}
			doneeMan.assignDoneeToNurse(donee_id, nurse.getId());
		} else {
			System.out.println("Not allowed. The transfussion needed exceeds de blood limit.");
		}
	}

	// OPTION 4.2:
	public static void showTransfusions(int id) {

		List<Blood> transfusions = bloodMan.getTransfusions(id);
		Iterator<Blood> it = transfusions.iterator();
		while (it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}

	// OPTION 5:
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
}