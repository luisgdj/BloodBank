package bloodbank.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bloodbank.ifaces.*;
import bloodbank.pojos.*;
import bloodbank.jdbc.*;

public abstract class ManagerMenu {

	private static NurseManager nurseMan;
	private static ContractManager contractMan;
	private static BloodManager bloodMan;
	private static UserManager userMan;
	private static DonorManager donorMan;
	private static DoneeManager doneeMan;
	private static BloodRetrievalLimitManager retrievalMan;
	private static XMLManager xmlMan;

	public static void menu(UserManager man) {

		ConnectionManager conMan = new ConnectionManager(); // creamos la conexion con el jdbc
		// xmlMan = new XMLManager();

		nurseMan = conMan.getNurseMan();
		contractMan = conMan.getContractMan();
		donorMan = conMan.getDonorMan();
		doneeMan = conMan.getDoneeMan();
		bloodMan = conMan.getBloodMan();
		retrievalMan = conMan.getRetrievalMan();
		userMan = man;

		while (true) {
			System.out.println("\nBlood bank storage unit (manager menu):" 
					+ "\n 1. Register nurse"
					+ "\n 2. Establish contract" 
					+ "\n 3. View blood storage" 
					+ "\n 4. Access nurse information"
					+ "\n 5. Change blood retreival limit" 
					+ "\n 6. Change password"
					+ "\n 7. Save blood information in an XML file" 
					+ "\n 8. Save blood information in an HTML file"
					+ "\n 9. Load blood information from an XML file" 
					+ "\n 10. Save nurse information in an XML file" 
					+ "\n 11. Save nurse information in an HTML file"
					+ "\n 12. Load nurse information from an XML file" 
					+ "\n 0. Log out");
			int option = Utilities.readInteger("Choose an option: ");

			switch (option) {
			case 1: {
				System.out.println("\nRegistration of a nurse: ");
				registerNurse();
				break;
			}
			case 2: {
				System.out.println("\nStablish contract:");
				registerContract();
				break;
			}
			case 3: {
				System.out.println("\nCheck blood storage:");
				String bloodType = Utilities.askBloodType();
				float amount = bloodMan.getAmountOfBlood(bloodType);
				System.out.println(" Amount of " + bloodType + " blood: " + amount);
				break;
			}
			case 4: {
				System.out.println("\nAccess nurse information:");
				selectNurse();
				break;
			}
			case 5: {
				System.out.println("\nChange blood retreival limit: ");
				float limit = Utilities.readFloat(" -Limit: ");
				retrievalMan.updateBloodRetrievalLimit(limit);
				break;
			}
			case 6: {
				System.out.println("Change password:");
				// crear funcion para cambiar la contraseña establecida por defecto
				break;
			}
			case 7: {
				System.out.println("Save blood information XML:");

				// ask witch blood wants to convert in xml and print all bloods
				ArrayList<Blood> bloods = (ArrayList<Blood>) bloodMan.getBloods();
				System.out.println("Witch blood do you want to convert in xml: \n" + bloodArrayToString(bloods));
				int option7 = Utilities.readInteger("Insert the ID: ");

				// search blood id
				Blood blood = null;
				for (Blood b : bloods)
					if (b.getId() == option7)
						blood = b;

				// create xml file
				File f = null;
				if (blood != null)
					f = xmlMan.makeBloodXML(blood);
				break;
			}
			case 8: {
				System.out.println("Save blood information HTML:");
				// ask witch blood wants to convert in HTML and print all bloods
				ArrayList<Blood> bloods = (ArrayList<Blood>) bloodMan.getBloods();
				System.out.println("Witch blood do you want to convert in HTML: \n" + bloodArrayToString(bloods));
				int option8 = Utilities.readInteger("Insert the ID: ");

				// search blood id
				Blood blood = null;
				for (Blood b : bloods)
					if (b.getId() == option8)
						blood = b;

				// create HTML file
				File f = null;
				if (blood != null)
					xmlMan.makeBloodHTML(blood);
				break;
			}
			case 9: {
				// create the .xml file folder
				File folder = new File("./xmls");
				ArrayList<String> fileNames = (ArrayList<String>) getXMLFilenamesInFolder(folder);

				// check if there aren't file .xml
				if (fileNames.size() == 0) {
					System.out.println("You don't have XML file available");
					break;
				}

				// print all the file .xml out
				System.out.println("Load blood information:");
				System.out.println("Witch file do you want to convert:" + formatArrayList(fileNames));
				int option9 = Utilities.readInteger("Insert the number: ") - 1;

				// if the option is not
				if (option9 < 0 || option9 >= fileNames.size()) {
					System.out.println("Incorrect number");
					break;
				}

				// extract the name from the list and create the file
				File fileName = new File(fileNames.get(option9));
				Blood b = xmlMan.getBloodXML(fileName);

				// add the new blood to the database
				bloodMan.insertBloodDonation(b);
				break;
			}			
			case 10: {
				System.out.println("Save nurse information XML:");

				// ask witch nurse wants to convert in xml and print all the nurses
				ArrayList<Nurse> nurses = (ArrayList<Nurse>) nurseMan.getNurses();
				System.out.println("Witch nurse do you want to convert in xml: \n" + nurseArrayToString(nurses));
				int option10 = Utilities.readInteger("Insert the ID: ");

				// search nurse id
				Nurse nurse = null;
				for (Nurse n : nurses)
					if (n.getId() == option10)
						nurse = n;

				// create xml file
				File f = null;
				if (nurse != null)
					f = xmlMan.makeNurseXML(nurse);
				break;
			}
			case 11: {
				System.out.println("Save nurse information HTML:");
				
				// ask witch nurse wants to convert in HTML and print all nurses
				ArrayList<Nurse> nurses = (ArrayList<Nurse>) nurseMan.getNurses();
				System.out.println("Witch nurse do you want to convert in HTML: \n" + nurseArrayToString(nurses));
				int option11 = Utilities.readInteger("Insert the ID: ");

				// search nurse id
				Nurse nurse = null;
				for (Nurse n : nurses)
					if (n.getId() == option11)
						nurse = n;

				// create HTML file
				File f = null;
				if (nurse != null)
					xmlMan.makeNurseHTML(nurse);
				break;
			}
			case 12: {
				// create the .xml file folder
				File folder = new File("./xmls");
				ArrayList<String> fileNames = (ArrayList<String>) getXMLFilenamesInFolder(folder);

				// check if there aren't file .xml
				if (fileNames.size() == 0) {
					System.out.println("You don't have XML file available");
					break;
				}

				// print all the file .xml name out
				System.out.println("Load nurse information:");
				System.out.println("Witch file do you want to convert:" + formatArrayList(fileNames));
				int option9 = Utilities.readInteger("Insert the number: ") - 1;

				// if the option is not
				if (option9 < 0 || option9 >= fileNames.size()) {
					System.out.println("Incorrect number.");
					break;
				}

				// extract the name from the list and create the file
				File fileName = new File(fileNames.get(option9));
				Nurse n = xmlMan.getNurseXML(fileName);

				// add the new nurse to the database
				nurseMan.insertNurse(n);
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
	private static void registerNurse() {

		System.out.println("Input nurse data: ");
		String name = Utilities.readString(" -Name: ");
		String surname = Utilities.readString(" -Surname: ");
		String email = Utilities.readString(" -Email: ");
		System.out.println(" -Default contract stablished");
		Contract contract = contractMan.getContract(1);
		contract.setNurses(nurseMan.getListOfNursesOfContract(1));

		System.out.println(" -Stablish login information: ");
		String username = Utilities.readString("   Username: ");
		String password = Utilities.readString("   Password: ");

		Nurse n = new Nurse(name, surname, email, contract);
		nurseMan.insertNurse(n);

		User user = new User(username, password, email);
		userMan.register(user);
		Role role = userMan.getRole("nurse");
		userMan.assignRole(user, role);
	}

	// OPTION 2:
	private static void registerContract() {

		System.out.println("Imput contract data: ");
		int duration = Utilities.readInteger(" -Duration (months): ");
		float salary = Utilities.readInteger(" -Salary: ");

		Contract c = new Contract(duration, salary);
		contractMan.insertContract(c);
	}

	// OPTION 4:
	private static void selectNurse() {

		System.out.println("Search nurse by name: ");
		String name = Utilities.readString(" -Name: ");
		List<Nurse> listNur = nurseMan.searchNurseByName(name);
		System.out.println(listNur);
		System.out.println("Please choose a Nurse.");
		Integer id = Utilities.readInteger(" -Id: ");

		checkNurseInfo(id);
	}

	// OPTION 4: (NURSE INFO)
	private static void checkNurseInfo(Integer id) {

		while (true) {
			System.out.println("\nCheck nurse info:" + "\n 1. Check personal information" + "\n 2. Show patients"
					+ "\n 3. Change contract" + "\n 0. Return to menu");
			int option = Utilities.readInteger("Option: ");

			switch (option) {
			case 1: {
				System.out.println("Check personal information");
				Nurse n = nurseMan.getNurse(id);
				System.out.println(n.toString());
				break;
			}
			case 2: {
				System.out.println("Show patients:");
				Iterator<Donor> donorIt = donorMan.getListOfDonors(id).iterator();
				Iterator<Donee> doneeIt = doneeMan.getListOfDonees(id).iterator();

				System.out.println(" -List of donors: ");
				while (donorIt.hasNext()) {
					System.out.println("   " + donorIt.next().getName() + " " + donorIt.next().getSurname());
				}
				System.out.println(" -List of donees: ");
				while (doneeIt.hasNext()) {
					System.out.println("   " + doneeIt.next().getName() + " " + doneeIt.next().getSurname());
				}
				break;
			}
			case 3: {
				System.out.println("Change contract:");
				List<Contract> contracts = contractMan.getListOfContracts();
				Iterator<Contract> it = contracts.iterator();
				while (it.hasNext()) {
					System.out.println("  Contract " + it.next().toString());
				}
				int contract_id = Utilities.readInteger("Choose a contract: ");
				nurseMan.updateContract(contract_id, id);
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

	/**
	 * Convert an ArrayList of bloods to a string
	 * 
	 * @param ArrayList of bloods to print
	 * @return String of bloods created
	 */
	private static String bloodArrayToString(ArrayList<Blood> bloods) {
		String result = "";
		for (Blood blood : bloods) {
			result += blood.toString();
		}
		return result;
	}	
	
	/**
	 * Convert an ArrayList of nurses to a string
	 * 
	 * @param ArrayList of nurses to print
	 * @return String of nurses created
	 */
	private static String nurseArrayToString(ArrayList<Nurse> nurses) {
		String result = "";
		for (Nurse nurse : nurses) {
			result += nurse.toString();
		}
		return result;
	}

	/**
	 * Retrieves the names of all XML files present in a given folder.
	 *
	 * @param folder The folder for which XML file names are to be retrieved.
	 * @return A List of String containing the names of XML files in the folder.
	 */
	public static List<String> getXMLFilenamesInFolder(File folder) {
		List<String> xmlFileNames = new ArrayList<>();

		if (folder.isDirectory()) {
			File[] files = folder.listFiles();

			if (files != null) {
				for (File file : files) {
					if (file.isFile() && file.getName().toLowerCase().endsWith(".xml")) {
						xmlFileNames.add(file.getName());
					}
				}
			}
		}

		return xmlFileNames;
	}

	/**
	 * Formats an ArrayList of strings into a single formatted string with a hyphen
	 * "-" prefix for each string.
	 *
	 * @param strings The ArrayList of strings to be formatted.
	 * @return A formatted string with each string preceded by a hyphen and new line
	 *         character.
	 */
	public static String formatArrayList(ArrayList<String> strings) {
		StringBuilder sb = new StringBuilder();
		int count = 1;

		for (String s : strings) {
			sb.append(count++).append(" - ").append(s).append(System.lineSeparator());
		}

		return sb.toString();
	}
}
